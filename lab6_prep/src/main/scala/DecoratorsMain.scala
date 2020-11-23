object DecoratorsMain {

  trait htmlH2 {
    override def toString: String = "<H2>" + super.toString + "</H2>"
  }

  trait htmlI {
    override def toString: String = "<I>" + super.toString + "</I>"
  }

  class StringWrap (val s: String) {
    override def toString: String = s
  }

  class Header (val text: String) extends StringWrap(text) with htmlH2 with htmlI

  def main(args: Array[String]): Unit = {
    val h = new Header("This is header")
    val it = new StringWrap("Another text") with htmlI
    println(h)
    println(it)
  }
}
