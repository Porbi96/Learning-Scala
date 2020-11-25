object MovementsInt {
  class Car (var xpos: Int, var ypos: Int) {
    override def toString: String = "Car x: "+xpos.toString + " y:"+ypos.toString

    def setX(p: Int): Unit = {xpos = p}
    def setY(p: Int): Unit = {ypos = p}
    def getX: Int = xpos
    def getY: Int = ypos
  }

  class Horse (var mypos: (Int, Int)) {
    override def toString: String = "Horse x: "+mypos._1.toString + " y:"+mypos._2.toString

    def setX(p: Int): Unit = {mypos = (p, mypos._2)}
    def setY(p: Int): Unit = {mypos = (mypos._1, p)}
    def getX: Int = mypos._1
    def getY: Int = mypos._2
  }

  trait IntRichMoves {
    def setX(p: Int): Unit
    def setY(p: Int): Unit
    def getX: Int
    def getY: Int

    def zero(): Unit = {
      setX(0)
      setY(0)
    }

    def here(other: IntRichMoves): Unit = {
      setX(other.getX)
      setY(other.getY)
    }

    def left(p: Int = 1): Unit = {
      setX(getX - p)
    }

    def right(p: Int = 1): Unit = {
      setX(getX + p)
    }

    def down(p: Int = 1): Unit = {
      setY(getY - p)
    }

    def up(p: Int = 1): Unit = {
      setY(getY + p)
    }
  }

//  def main(args: Array[String]): Unit = {
//    val car1 = new Car(0, 0)
//    println(car1)
//    val car2 = new Car(1, 0)
//    println(car2)
//    val betterCar = new Car ( 0, 0) with IntRichMoves
//    println(betterCar)
//    betterCar.up()
//    println(betterCar)
//    betterCar.right(3)
//    println(betterCar)
//    betterCar.down(5)
//    println(betterCar)
//    betterCar.left(5)
//    println(betterCar)
//    val runner = new Horse( (1,1) ) with IntRichMoves
//    println(runner)
//    runner.here(betterCar)
//    println(runner)
//    runner.zero()
//    println(runner)
//  }
}
