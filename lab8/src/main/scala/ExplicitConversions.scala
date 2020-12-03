object ExplicitConversions {

  class StringWrap (val str: String) {
    def /(delimiter: Char): List[String] = {
      str.split(delimiter).toList
    }
  }

  class IntWrap (val i: Int) {
    def *(s: String): String = s * i
  }

  def |(s: String): String = s.trim

  implicit def int2IntWrap(i: Int): IntWrap = new IntWrap(i)

  implicit def string2StringWrap(s: String): StringWrap = new StringWrap(s)


  def main(args: Array[String]): Unit = {
    val r = 4 * "ala"
    println(r)
    println("ala ma kota"/' ')
    println( "+"+ |(" hello people ") +"+" )

  }

}
