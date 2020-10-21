object Lab3 {
  def rowPrint(row: Array[Int]): Unit = {
    println("|" + row.mkString(" ") + "|")
  }

  def arrayPrint(matrix: Array[Array[Int]]): Unit = {
    matrix.foreach(row => rowPrint(row))
  }

  def sumRows(matrix: Array[Array[Int]]): Array[Int] = {
    val sumR = for (row <- matrix) yield row.sum
    sumR
  }

  def sumCols(matrix: Array[Array[Int]]): Array[Int] = {
    val matrixT = matrix.transpose
    val sumC = for (row <- matrixT) yield row.sum
    sumC
  }

  def takeLarger(m1: Array[Array[Int]], m2: Array[Array[Int]]): Array[Array[Int]] = {
    val m = Array.ofDim[Int](4,5)
    for (i <- Range(0, 4); j <- Range(0, 5)) {
      m(i)(j) = if (m1(i)(j) > m2(i)(j)) m1(i)(j) else m2(i)(j)
    }
    m
  }

  def pi(multiplicand: Double): Double = {
    val pi = 3.1415
    pi * multiplicand
  }

  def pi: Double = {
    3.1415
  }

  def repN(N: Int, func: Int => Int, first: Int): Int = {
    var res = func(first)
    for (_ <- Range(1, N)) res = func(res)
    res
  }

  @scala.annotation.tailrec
  def repNtail(N: Int, func: Int => Int, first: Int): Int = {
    if (N == 1) return func(first)
    repNtail(N-1, func, func(first))
  }


  def main(args: Array[String]): Unit = {
    val x = Array.ofDim[Int](4,5)
    val y = Array.ofDim[Int](4,5)
    for (i <- 0 until 4; j <- 0 until 5) x(i)(j) = i+j
    for (i <- 0 until 4; j <- 0 until 5) y(i)(j) = i*j

    // 1)
    arrayPrint(x)
    println(" ")
    arrayPrint(y)

    // 2)
    val sumRow = sumRows(x)
    val sumCol = sumCols(x)
    println("Sums in each row: " + sumRow.mkString(" "))
    println("Sums in each column: " + sumCol.mkString(" "))

    // 3)
    val x_transposed = x.transpose

    // 4)
    val z = takeLarger(x, y)
    arrayPrint(z)

    // ======================================================================
    println("pi:" + pi)
    println("pi(pi): " + pi(pi))
    println("pi(pi(pi)): " + pi(pi(pi)))
    println("repN(5, (x: Int) => 2*x: " + repN(5, (x: Int) => 2*x, 1))
    println("repN(5, (x: Int) => 2*x: " + repNtail(5, (x: Int) => 2*x, 1))

  }
}
