object Serialization {

  def checkType(x: Any): String = x match {
    case n: Int => "Int(" + n + ")"
    case d: Double => "Double(" + d + ")"
    case s: String => "String(" + s + ")"
  }

  def stream(l: List[Any]): String = {
    @scala.annotation.tailrec
    def _stream(l: List[Any], result: String = ""): String = l match {
      case Nil => result
      case head :: tail => _stream(tail, result + checkType(head) + " ")
    }

    _stream(l)
  }

//  def main(args: Array[String]): Unit = {
//    val l = List(1, "hello", 2.56, 0x45, "key")
//
//    val streamed = stream(l)
//    print(streamed)
//  }
}
