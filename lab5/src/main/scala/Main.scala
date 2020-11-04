object Main {

  def filterList(toFilt: List[String], predicate: (String) => Boolean): List[String] = {

    @scala.annotation.tailrec
    def _filterList(_toFilt: List[String], filtered: List[String] = Nil): List[String] = {
      if (_toFilt.isEmpty) { return filtered }

      if(predicate(_toFilt.head)) {
        _filterList(_toFilt.tail, filtered :+ _toFilt.head)
      } else {
        _filterList(_toFilt.tail, filtered)
      }
    }

    _filterList(toFilt)
  }

  def main(args: Array[String]): Unit = {

    val lista = filterList(List("Hello", "there", "people"), _.contains('l'))
    println(lista)

    val x = Complex(1,2)
    val y = Complex(3,2)
    println("x " + x + " y " + y + " x+y " +(x+y) + " x-y " +(x-y))
    val f = Complex.polar(r=3, angle=math.Pi)
    println(f)
    println( "|x| "+ x.r + " angle " +x.angle)
  }
}