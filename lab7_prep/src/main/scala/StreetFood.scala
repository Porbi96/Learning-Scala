import java.util

object StreetFood {
  class Sandwich () {
    def price: Double = 1.5
    def content: List[String] = List[String]("Sandwich")
  }

  trait Mayonnaise extends Sandwich {
    override def price: Double = super.price + 5

    override def content: List[String] = super.content :+ "Mayonnaise"
  }

  trait Salad extends Sandwich {
    override def price: Double = super.price + 4

    override def content: List[String] = super.content :+ "Salad"
  }

  trait Egg extends Sandwich {
    override def price: Double = super.price + 10

    override def content: List[String] = super.content :+ "Egg"
  }

  class ChefBests extends Sandwich with Salad with Egg with Mayonnaise

//  def main(args: Array[String]): Unit = {
//    val myLunch = new Sandwich with Mayonnaise with Salad with Egg
//    println(myLunch.price)
//    println(myLunch.content.mkString(", "))
//
//    val yourLunch = new ChefBests
//    println(yourLunch.price)
//    println(yourLunch.content)
//  }
}
