object SimpleClasses {
//// =====================================================================================================================
//  class Angle(val angle: Double) {
//    def +(other: Angle): Angle = Angle(rescaleAngle(this.angle + other.angle))
//
//    def -(other: Angle): Angle = Angle(rescaleAngle(this.angle - other.angle))
//
//    def *(multiplicand: Double): Angle = Angle(rescaleAngle(this.angle * multiplicand))
//
//    def /(divisor: Double): Angle = Angle(rescaleAngle(this.angle / divisor))
//
//    @scala.annotation.tailrec
//    final private def rescaleAngle(a: Double): Double = {
//      if (a < -math.Pi) {rescaleAngle(a + 2*math.Pi)}
//      else if (a >= math.Pi) {rescaleAngle(a - 2*math.Pi)}
//      else {a}
//    }
//
//    override def toString: String = "Angle: " + (angle/3.14).toString + " pi"
//  }
//
//  object Angle {
//    def apply(angle: Double): Angle = new Angle(angle)
//  }

// =====================================================================================================================

  class Expense(val amount: Double, val name: String, val category: String) {
    override def toString: String = s"$name ($category): $amount"
  }

  object Expense {
    def apply(amount: Double, name: String, category: String): Expense = new Expense(amount, name, category)
  }

  class ExpensesList() {
    var expensesList: List[Expense] = Nil

    def +=(elem: Expense): Unit = {
      expensesList = elem :: expensesList
    }

    def sum: Double = expensesList.map(_.amount).sum

    def max: Expense = expensesList.maxBy(_.amount)

    def printList(): Unit = println(expensesList.mkString("\n"))
  }

  object ExpensesList {
    def apply(): ExpensesList = new ExpensesList()
  }

// =====================================================================================================================

  class Complex(val re: Double, val im: Double) {

    def +(other: Complex): Complex = {
      Complex(re + other.re, im + other.im)
    }

    def -(other: Complex): Complex = {
      Complex(re - other.re, im - other.im)
    }

    def r: Double = math.hypot(re, im)

    def angle: Double = math.atan(im / re)

    override def toString: String = {
      "Re:{" + re + "} Im:{" + im + "}"
    }
  }

  object Complex {
    def apply(re: Double, im: Double): Complex = new Complex(re, im)

    def polar(r: Double, angle: Double): Complex = {
      Complex(math.abs(r) * math.cos(angle), math.abs(r) * math.sin(angle))
    }
  }

// =====================================================================================================================

//  def main(args: Array[String]): Unit = {
//    val a = Angle(2)
//    val b = a+Angle(1.15)
//    println(b)
//
//    var el = ExpensesList()
//    el += Expense(5, "Bread", "food")
//    el += Expense(7, "Butter", "food")
//    el += Expense(3.2, "Tomatoes", "food")
//    el += Expense(18, "Star Wars ticket", "entertainment")
//    el.printList()
//    println(el.sum)
//    println(el.max)
//
//    val x = Complex(1,2)
//    val y = Complex(3,2)
//    println("x " + x + " y " + y + " x+y " +(x+y) + " x-y " +(x-y))
//    val f = Complex.polar(r=3, angle=math.Pi)
//    println(f)
//    println( "|x| "+ x.r + " angle " +x.angle)
//  }
}


