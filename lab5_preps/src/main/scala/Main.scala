object Main {
  def filterList(toFilt: List[String], predicate: (String) => Boolean): List[String] = {
    var filtered = List[String]()

    @scala.annotation.tailrec
    def _filterList(toFilt: List[String]): Unit = {
      if (toFilt.isEmpty) return

      if(predicate(toFilt.head)) {
        filtered = filtered :+ toFilt.head
      }
      _filterList(toFilt.tail)
    }

    _filterList(toFilt)
    filtered
  }

  def reverseList(toReverse: List[String]): List[String] = {
    var reversed = List[String]()

    @scala.annotation.tailrec
    def _reverseList(toReverse: List[String]): Unit = {
      if (toReverse.isEmpty) {return}

      reversed = toReverse.head :: reversed
      _reverseList(toReverse.tail)
    }

    _reverseList(toReverse)
    reversed
  }

  def merge(l1: List[Int], l2: List[Int]): List[Int] = {
    var merged = List[Int]()

    @scala.annotation.tailrec
    def _merge(l1: List[Int], l2: List[Int]): Unit = {
      if (l1.isEmpty || l2.isEmpty) {return}

      merged = merged :+ l1.head :+ l2.head
      _merge(l1.tail, l2.tail)
    }

    _merge(l1, l2)
    merged
  }



  def main(args: Array[String]): Unit = {

    val lista = filterList(List("Hello", "there", "people"), _.contains('e'))
    val lista2 = reverseList(List("Hello", "there", "people"))
    val lista3 = merge(List(1,5,6,7), List(2,2,9))
    println("Hello world!")
    println(lista.mkString(" "))
    println(lista2.mkString(" "))
    println(lista3.mkString(" "))
  }
}
