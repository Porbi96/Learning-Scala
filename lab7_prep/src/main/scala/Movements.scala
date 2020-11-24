object Movements {
  class Car (var xpos: Double, var ypos: Double, var direction: Double) {
    override def toString: String = s"Car x: $xpos, y: $ypos, direction: $direction"
  }

  class Horse (var mypos: (Double, Double), var whereHeading: Double) {
    var xpos: Double = mypos._1
    var ypos: Double = mypos._2
    var direction: Double = whereHeading

    override def toString: String = s"Horse x: $xpos, y: $ypos, heading: $direction"
  }

  trait RichMoves {
    var xpos: Double
    var ypos: Double
    var direction: Double

    def validateDirection: Unit = {
      if (direction >= 360) {direction = direction - 360}
      else if (direction < 0) {direction = direction + 360}
    }

    def turnLeft: Unit = {
      direction = direction + 90
      validateDirection
    }

    def turnRight: Unit = {
      direction = direction - 90
      validateDirection
    }

    def turnBack: Unit = {
      direction = direction + 180
      validateDirection
    }

    def forward(p: Double): Unit = {
      xpos = xpos + p*math.cos(direction*math.Pi/180.0)
      ypos = ypos + p*math.sin(direction*math.Pi/180.0)
    }
  }

//  def main(args: Array[String]): Unit = {
//    val car1 = new Car(0, 0, 0)
//    val car2 = new Car(1, 0, 90)
//    println(car1)
//    println(car2)
//
//    val betterCar = new Car(0, 0, 0) with RichMoves
//    betterCar.forward(2)
//    betterCar.turnLeft
//    betterCar.forward(2)
//    println(betterCar)
//
//    var betterHorse = new Horse((1, 1), 180) with RichMoves
//    betterHorse.forward(5)
//    println(betterHorse)
//  }
}
