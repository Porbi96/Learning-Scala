import akka.actor._

import scala.collection.mutable.ListBuffer

final case class InitBox(bounds: (Int, Int), amount: Int)
final case class MoveRequest()
final case class GetPosRequest()
final case class GetPosResponse(val x: Int, val y: Int)
final case class ConfirmKnowledgeRequest()
final case class ConfirmKnowledgeResponse(val particleEnergy: Double)

final case class RandomMoveRequest()
final case class RandomMoveResponse(old_x: Int, old_y: Int, x: Int, y: Int, movedParticle: ActorRef)
final case class RandomMoveAcknowledged(deltaEnergy: Double)
final case class RestoreMove()
final case class PositionOccupiedRequest()

final case class BoxContext() {
  var particlesAmount: Int = 0
  var particlesKnownByOthers: Int = 0
  var particlesWhichKnowEveryOther: Int = 0
  var particlesUpToDate: Int = 0
  var particleMoved: ActorRef = _
  var initComplete: Boolean = false
  var restoringPos: Boolean = false
  var worsePosCnt: Int = 0
  var bounds: (Int, Int) = (0, 0)
}


class Box extends Actor {
  var globalEnergy: Double = 0.0
  var oldGlobalEnergy: Double = 0.0
  val boxContext: BoxContext = BoxContext()
  var particlesIndeces: ListBuffer[(Int, Int)] = new ListBuffer[(Int, Int)]

  def receive: PartialFunction[Any, Unit] = {
    case init: InitBox =>
      println("[DEBUG] Box got InitBox msg.")
      initializeBox(init)
  }

  def initializing: Receive = {
    case p: GetPosResponse =>  {
      broadcast_msg(p, withSender = false)
      boxContext.particlesKnownByOthers += 1
      if (boxContext.particlesKnownByOthers >= boxContext.particlesAmount) {
        broadcast_msg(ConfirmKnowledgeRequest(), withSender = true)
      }
    }

    case k: ConfirmKnowledgeResponse =>
      boxContext.particlesWhichKnowEveryOther += 1
      globalEnergy += k.particleEnergy
      if (boxContext.particlesWhichKnowEveryOther >= boxContext.particlesAmount) {
        boxContext.initComplete = true
        context.become(annealing)
        println("[DEBUG] End of box initialization.")
        println(s"!!!!! Initial potential energy: $globalEnergy !!!!!")
        sendRandomMoveRequest()
      }
  }

  def annealing: Receive = {
    case mr: RandomMoveResponse =>
      if (isPosPermitted((mr.x, mr.y))) {
        particlesIndeces -= ((mr.old_x, mr.old_y))
        particlesIndeces += ((mr.x, mr.y))
        boxContext.particleMoved = mr.movedParticle
        boxContext.particlesUpToDate = 0
        oldGlobalEnergy = globalEnergy
        broadcast_msg(mr, withSender = false)
      }
      else {
        println("Position already occupied")
        context.sender() ! PositionOccupiedRequest()
        sendRandomMoveRequest()
      }

    case ma: RandomMoveAcknowledged =>
      boxContext.particlesUpToDate += 1
      globalEnergy += ma.deltaEnergy*2
      boxContext.particleMoved ! ma

      if (boxContext.particlesUpToDate >= boxContext.particlesAmount - 1) {
        if (globalEnergy == oldGlobalEnergy) {
          sendRandomMoveRequest()
        }
        else if (globalEnergy < oldGlobalEnergy) {
          println(s"Found better distribution. New global energy: $globalEnergy")
          Thread.sleep(10)
          sendRandomMoveRequest()
          if (boxContext.restoringPos) boxContext.restoringPos = false
          else {
            println(s"Found better distribution. New global energy: $globalEnergy")
            boxContext.worsePosCnt = 0
          }
        }
        else {
          boxContext.restoringPos = true
          boxContext.particleMoved ! RestoreMove()
          boxContext.worsePosCnt += 1
          if (boxContext.worsePosCnt >= 50) {
            println(particlesIndeces.mkString("\n"))
            System.exit(0)
          }
        }
      }

    case _ => println("Got unexpected msg.")
  }

  def isPosPermitted(pos: (Int, Int)): Boolean = {
    (!particlesIndeces.contains(pos) &&
      pos._1 > 0 &&
      pos._2 > 0 &&
      pos._1 < boxContext.bounds._1 &&
      pos._2 < boxContext.bounds._2)
  }

  def initializeBox(properties: InitBox): Unit = {
    context.become(initializing)
    boxContext.particlesAmount = properties.amount
    boxContext.bounds = properties.bounds
    spawnParticles(properties.bounds, properties.amount)
    broadcast_msg(GetPosRequest(), withSender = true)
  }

  def spawnParticles(bounds: (Int, Int), amount: Int): Unit = {
    var i: Int = 0
    var j: Int = 0
    do {
      val pos = getRandomPos(bounds)
      if (!particlesIndeces.contains(pos)) {
        particlesIndeces += pos
        context.actorOf(Props(new Particle(pos._1, pos._2)), name = s"particle$i")
        i += 1
      }
      else {
        j += 1
      }
    } while (i < amount && j < 10000)
  }

  def sendRandomMoveRequest(): Unit = {
    val r = scala.util.Random
    context.children.toIndexedSeq.apply(r.nextInt(context.children.size-1)) ! RandomMoveRequest()
  }

  def broadcast_msg(msg: Any, withSender: Boolean): Unit = {
    for (particle <- context.children if (withSender || particle != context.sender())) {
      particle ! msg
    }
  }

  private def getRandomPos(bounds: (Int, Int)): (Int, Int) = {
    val r = scala.util.Random
    (r.nextInt(bounds._1), r.nextInt(bounds._2))
  }
}
