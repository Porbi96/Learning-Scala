object lab6ex1 {
  class Angle private(val angle: Double) {
    def +(other: Angle): Angle = Angle(angle + other.angle)

    def -(other: Angle): Angle = Angle(angle - other.angle)

    def *(multiplicand: Double): Angle = Angle(angle * multiplicand)

    def /(divisor: Double): Angle = Angle(angle / divisor)

    override def toString: String = "Angle: " + (angle/3.14).toString + " pi"
  }

  object Angle {
    def apply(angle: Double): Angle = new Angle(rescaleAngle(angle))

    @scala.annotation.tailrec
    final def rescaleAngle(a: Double): Double = {
      if (a < -math.Pi) {rescaleAngle(a + 2*math.Pi)}
      else if (a >= math.Pi) {rescaleAngle(a - 2*math.Pi)}
      else {a}
    }
  }

//  def main(args: Array[String]): Unit = {
//    val a = Angle(211.5)
//    val b = Angle(3.1)
//    val c = a + b
//    val d = b * 2
//    println(a)
//    println(b)
//    println(c)
//    println(d)
//  }
}

//object lab6ex1 {
//
//  class Angle private(val angle: Double) {
//    //    val rescaledAngle = rescaleAngle(angle)
//
//    def this(a: Double) = this(rescaleAngle(a))
//
//    def +(other: Angle): Angle = Angle(rescaleAngle(this.angle + other.angle))
//
//    def -(other: Angle): Angle = Angle(rescaleAngle(this.angle - other.angle))
//
//    def *(multiplicand: Double): Angle = Angle(rescaleAngle(this.angle * multiplicand))
//
//    def /(divisor: Double): Angle = Angle(rescaleAngle(this.angle / divisor))
//
//    @scala.annotation.tailrec
//    final def rescaleAngle(a: Double): Double = {
//      if (a < -math.Pi) {
//        rescaleAngle(a + 2 * math.Pi)
//      }
//      else if (a >= math.Pi) {
//        rescaleAngle(a - 2 * math.Pi)
//      }
//      else {
//        a
//      }
//    }
//
//    override def toString: String = "Angle: " + (angle / 3.14).toString + " pi"
//  }
//
//  object Angle {
//    def apply(angle: Double): Angle = {
//      new Angle()
//    }
//  }
//
//
//  def main(args: Array[String]): Unit = {
//    val a = Angle(211.5)
//    val b = Angle(3.1)
//    val c = a + b
//    val d = b * 2
//    val e = new Angle(50.5)
//    println(a)
//    println(b)
//    println(c)
//    println(d)
//  }
//}
