import StringDecorators._

object DecoratorsMain {
  class StringWrap (val s: String) {
    override def toString: String = s
  }

  class Pre (val text: String) extends StringWrap(text) with htmlH2 with htmlI {}

  class X (val text: String, override val llen: Int) extends StringWrap(text) with Capitalisation with PageStretch {}

  def main(args: Array[String]): Unit = {
    val h = new Pre("Paragraph header")
    println(h)

    println(new X("abra ka dabra", 50))
    println(new X("abra ka dabra", 30))
    println(new X("abra ka dabra", 20))

    val z = new StringWrap("a tricky text with tricky content") with PageStretch
    println(z)
  }
}
