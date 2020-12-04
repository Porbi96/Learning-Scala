import akka.actor.{ActorSystem, Props}

object Main extends App {
  val system = ActorSystem("AnnealingSystem")

  println("[DEBUG] Creating box.")
  val box = system.actorOf(Props[Box], name = "box")
  println("[DEBUG] Sending init msg.")
  box ! InitBox((200, 200), 15000)
}