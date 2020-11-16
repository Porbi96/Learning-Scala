object ColorsShapesHierarchy {

  class Shape(center: (Int, Int), size: Double) {
    override def toString: String = s"of side size $size, at $center"
  }

  case class Triangle(center: (Int, Int), rotation: Double, size: Double) extends Shape(center, size) {
    override def toString: String = s"Triangle of side size $size, rotation $rotation, at $center"
  }

  case class Square(center: (Int, Int), size: Double) extends  Shape(center, size) {
    override def toString: String = s"Square of side size $size at $center"
  }

  class ColorTriangle(center: (Int, Int), rotation: Double, size: Double, var color: (Double, Double, Double))
    extends Triangle(center, rotation, size) with Color {
    red = color._1
    green = color._2
    blue = color._3
  }

  object ColorTriangle {
    def apply(center: (Int, Int), rotation: Double, size: Double, color: (Double, Double, Double)): ColorTriangle = {
      new ColorTriangle(center, rotation, size, color)
    }
  }

  class ColorSquare(center: (Int, Int), size: Double, var color: (Double, Double, Double))
    extends Square(center, size) with Color {
    red = color._1
    green = color._2
    blue = color._3
  }

  object ColorSquare {
    def apply(center: (Int, Int), size: Double, color: (Double, Double, Double)): ColorSquare = {
      new ColorSquare(center, size, color)
    }
  }

  trait Color {
    var red: Double = 0
    var green: Double = 0
    var blue: Double = 0

    def setColor(red: Double, green: Double, blue: Double): Unit = {
      this.red = red
      this.green = green
      this.blue = blue
    }

    private def checkColorValue(value: Double): Double = {
      if (value > 255) 255
      else if (value < 0) 0
      else value
    }

    def increaseColorBy(cname: Char, percentage: Double): Unit = {
      cname.toLower match {
        case 'r' =>
          red += red*percentage
          red = checkColorValue(red)

        case 'g' =>
          green += green*percentage
          green = checkColorValue(green)

        case 'b' =>
          blue += blue*percentage
          blue = checkColorValue(blue)
      }
    }

    def decreaseColorBy(cname: Char, percentage: Double): Unit = {
      cname.toLower match {
        case 'r' =>
          red -= red * percentage
          red = checkColorValue(red)

        case 'g' =>
          green -= green * percentage
          green = checkColorValue(green)

        case 'b' =>
          blue -= blue * percentage
          blue = checkColorValue(blue)
      }
    }

    override def toString: String = super.toString + s" with color r:$red g:$green b:$blue"
  }

//  def main(args: Array[String]): Unit = {
//    val a = ColorTriangle( center = (0,0), rotation = 30, size = 25, color = (20, 20, 120) )
//    println(a)
//    a.setColor(100,0,70)
//    println(a)
//    a.increaseColorBy('r', 2.0)// red = red + red * 2
//    println(a)
//    val b = ColorSquare( center = (3,3), size = 12, color = (20, 20, 120))
//    b.setColor(10, 255, 0)
//    b.decreaseColorBy('g', 0.3)
//    val shapes = List[Shape](Triangle( (0,0), 90, 50), a, Square( (3,-3), 10), b)
//    println( shapes.mkString("\n"))
//  }
}
