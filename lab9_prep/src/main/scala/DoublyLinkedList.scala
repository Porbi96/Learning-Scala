object DoublyLinkedList {
  class DL[A] {
    var item: A = _
    var previous: DL[A] = _
    var next: DL[A] = _

    def add(item: A): Unit = {
      val newNode = new DL[A]
      newNode.item = this.item
      this.item = item

    }
  }

//  def main(args: Array[String]): Unit = {
//    val dli = new DL[Int]
//  }
}
