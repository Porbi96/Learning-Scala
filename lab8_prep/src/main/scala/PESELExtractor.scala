object PESELExtractor {
  object PESELExtr {

    def isAllDigit(x: String): Boolean = x.forall(p => p.isDigit)

    def isValid(pesel: String): Boolean = {
      if (pesel.length == 11 && isAllDigit(pesel)) {
        val weights: List[Int] = List[Int](1, 3, 7, 9, 1, 3, 7, 9, 1, 3)
        var helper = 0;
        for (i <- 0 to 9) {
          helper += pesel(i).asDigit * weights(i)
        }
        helper = 10 - helper % 10
        if (helper == pesel.takeRight(1).toInt) true
        else false
      }
      else false
    }

    def unapply(pesel: String): Option[(Int, Int, Int, String)] = {
      if (isValid(pesel)) {
        val year = 1900 + pesel.substring(0, 2).toInt
        val month = pesel.substring(2, 4).toInt
        val day = pesel.substring(4, 6).toInt
        val sex = if (pesel(9).asDigit % 2 == 0) "M" else "F"

        Some(year, month, day, sex)
      }
      else None
    }
  }

//  def main(args: Array[String]): Unit = {
//    val p = "91051123893"
//    val PESELExtr(year, month, day, sex) = p
//    println(year)
//    println(month)
//    println(day)
//    println(sex)
//  }
}
