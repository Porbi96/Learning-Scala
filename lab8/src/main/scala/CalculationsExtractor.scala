object CalculationsExtractor {
  object Addition {
    def unapply(str: String): Option[(Double, Double)] = {
      val strArr = str.split("[-+*/]")
      Some(strArr(0).toDouble, strArr(1).toDouble)
    }
  }

  def evalEx(str: String): Double = {
    val Addition(a,b) = str
    str match {
      case s: String if s.contains("+") => a + b
      case s: String if s.contains("-") => a - b
      case s: String if s.contains("/") => a / b
      case s: String if s.contains("*") => a * b
    }
  }

  def main(args: Array[String]): Unit = {
    val Addition(a, b) = "2 + 6.5";
    println(a)
    println(b)

    println(evalEx("2 +5"))
    println(evalEx("2- 125"))
    println(evalEx("2*5"))
    println(evalEx("25 / 5.9"))
  }
}
