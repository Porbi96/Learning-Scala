object UseOfSeqCollection {
  def repeated(s: Seq[Any]): List[Any] = {
    s.diff(s.distinct).distinct.toList
  }

  def condMerge: Unit = {
    // TODO: implement
  }

  def main(args: Array[String]): Unit = {
    println(repeated(List(-8, 5, 6, 1, 4, 4, 2, 5, -1, 9, 9)))
    println(repeated("Hello World"))
  }
}
