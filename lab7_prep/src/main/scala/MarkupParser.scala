object MarkupParser {


  def main(args: Array[String]): Unit = {
    val input = """p>p>h>Header<<p> t>Par 1<h>Par title<b>bold<i>italic<<p>t>simple par with more text<<<""".stripMargin.replaceAll("[\n]", "").replaceAll("< +(.)>", "<$1>").replaceAll("p> +", "p>").replaceAll("< +<", "<<")

    println( input )

  }
}
