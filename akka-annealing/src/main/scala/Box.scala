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
  var worsePosCnt: Int = 0
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
        println(s"!!!!! Initial potential energy: $globalEnergy !!!!!")
        sendRandomMoveRequest()
      }
  }

  def annealing: Receive = {
    case mr: RandomMoveResponse =>
      boxContext.particleMoved = mr.movedParticle
      if (particlesIndeces.contains((mr.x, mr.y))) {
        println("Position already occupied")
        context.sender() ! PositionOccupiedRequest()
        sendRandomMoveRequest()
      }
      else {
        particlesIndeces -= ((mr.old_x, mr.old_y))
        particlesIndeces += ((mr.x, mr.y))
        broadcast_msg(mr, withSender = false)
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
          Thread.sleep(100)
          sendRandomMoveRequest()
          boxContext.worsePosCnt = 0
        }
        else {
          boxContext.particleMoved ! RestoreMove()
          boxContext.worsePosCnt += 1
          if (boxContext.worsePosCnt >= 1000000) {
            println(particlesIndeces.mkString("\n"))
            System.exit(0)
          }
        }
      }

    case _ => println("Got unexpected msg.")
  }

  def initializeBox(properties: InitBox): Unit = {
    context.become(initializing)
    boxContext.particlesAmount = properties.amount
    spawnParticles(properties.bounds, properties.amount)
    broadcast_msg(GetPosRequest(), withSender = true)
  }

  def spawnParticles(bounds: (Int, Int), amount: Int): Unit = {
    var i: Int = 0
    do {
      val pos = getRandomPos(bounds)
      if (!particlesIndeces.contains(pos)) {
        particlesIndeces += pos
        context.actorOf(Props(new Particle(pos._1, pos._2)), name = s"particle$i")
        i += 1
      }
    } while (i < amount)
  }

  def sendRandomMoveRequest(): Unit = {
    val r = scala.util.Random

    oldGlobalEnergy = globalEnergy
    boxContext.particlesUpToDate = 0
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


object Main extends App {
  val system = ActorSystem("AnnealingSystem")

  println("[DEBUG] Creating box.")
  val box = system.actorOf(Props[Box], name = "box")
  println("[DEBUG] Sending init msg.")
  box ! InitBox((100, 100), 20)

}
