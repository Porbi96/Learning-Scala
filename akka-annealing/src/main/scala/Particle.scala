import akka.actor._

class Particle (var x: Int, var y: Int) extends Actor {
  var energy: Double = 0.0
  val a: Double = 2.0
  val b: Double = 155.0

  var old_x: Int = 0;
  var old_y: Int = 0;

  def receive: PartialFunction[Any, Unit] = {
    case _: GetPosRequest => {
      context.sender ! GetPosResponse(x, y)
    }
    case p: GetPosResponse => {
      if (p.x != x && p.y != y) {
        energy += calculatePartialEnergy(p.x, p.y)
      }
    }

    case _: ConfirmKnowledgeRequest =>
      println(this + " knows every other Particle.")
      context.become(working)
      context.sender() ! ConfirmKnowledgeResponse(energy)
  }

  def working: Receive = {
    case _: RandomMoveRequest => moveParticleRandomly()
    case mr: RandomMoveResponse =>
      val deltaEnergy = calculatePartialEnergy(mr.x, mr.y) - calculatePartialEnergy(mr.old_x, mr.old_y)
      energy += deltaEnergy
      context.sender() ! RandomMoveAcknowledged(deltaEnergy)

    case ma: RandomMoveAcknowledged => energy += ma.deltaEnergy

    case _: PositionOccupiedRequest =>
      x = old_x
      y = old_y
    case _: RestoreMove => restoreParticlePosition()

  }

  def moveParticleRandomly(): Unit = {
    val r = scala.util.Random

    old_x = x
    old_y = y
    x = x + r.nextInt(4) - 2
    y = y + r.nextInt(4) - 2

    context.sender() ! RandomMoveResponse(old_x, old_y, x, y, context.self)
  }

  def restoreParticlePosition(): Unit = {
    val helper_x: Int = old_x
    val helper_y: Int = old_y

    old_x = x
    old_y = y
    x = helper_x
    y = helper_y

    context.sender() ! RandomMoveResponse(old_x, old_y, x, y, context.self)
  }

  private def calculatePartialEnergy(x: Int, y: Int): Double = {
    b * ( math.pow(a / math.hypot((this.x - x).toDouble, (this.y - y).toDouble), 12.0) - math.pow(a / math.hypot((this.x - x).toDouble, (this.y - y).toDouble), 6.0) )
  }

  override def toString: String = f"Particle(x:$x y:$y energy: $energy%.5f)"
}