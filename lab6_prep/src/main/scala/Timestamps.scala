import java.util.{Calendar, Date}

object Timestamps {
  trait TimeStamp {
    val today: Date = Calendar.getInstance().getTime

    override def toString: String = today.toString + " " + super.toString
  }

  trait Quote {
    override def toString: String = "\"" + super.toString + "\""
  }

  abstract class Log () {
    def pprint(str: String): Unit
  }

  class Logger extends Log {
    var s = ""
    override def pprint(str: String): Unit = {
      s = str
      println(this)
    }

    override def toString: String = s
  }

  def main(args: Array[String]): Unit = {
    val l = new Logger with Quote with TimeStamp
    l.pprint("Hello world")
    println("")
    val ql = new Logger with Quote
    ql.pprint("No date, just quote")
    println("")
    val tl = new Logger with TimeStamp
    tl.pprint("Some time stamped message")
    println("")
    val plain = new Logger
    plain.pprint("Just he text")
    println("")
  }
}
