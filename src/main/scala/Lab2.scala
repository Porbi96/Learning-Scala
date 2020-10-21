import scala.util.Random

object Lab2 {

  def pow2(x: Double): Double = x*x

  def generateNumbersExceptFromSquares(n: Int): Array[Int] = {
    val g = for(x <- 1 to n if (x == 1 || !scala.math.sqrt(x).isValidInt)) yield x
    g.toArray
  }

  def findPiValue(N: Int): Double = {
    val generator = new Random()
    var insideN = 0

    // Generate sequence of tuples (points)
    val points = for(_ <- Range(0, N)) yield (generator.nextDouble(), generator.nextDouble())

    // Check if points are inside a circle
    for (i <- points) {
      if (scala.math.hypot(i._1, i._2) < 1.0) {
        insideN += 1
      }
    }

    // Calculate pi value
    4.0 * (insideN.toDouble / N.toDouble)
  }

  def solvePythagoreanTriangle(args: Array[String]): Double = {
    val sortedArgs = args.sorted
    val x1: Int = sortedArgs(0).toInt
    val x2: Int = sortedArgs(1).toInt

    if (sortedArgs.contains("c")) {
      val x3: Double = scala.math.sqrt(x2*x2 - x1*x1)
      println("Result is " + x3)
      x3
    }
    else {
      val x3: Double = scala.math.sqrt(x2*x2 + x1*x1)
      println("Result is " + x3)
      x3
    }
  }

  def niceRowPrint(row: Array[Int]): Unit = {
    println("|" + row.mkString(" ") + "|")
  }

  def nicePrint(matrix: Array[Array[Int]]): Unit = {
    matrix.foreach(row => niceRowPrint(row))
  }

//  def main(args: Array[String]): Unit = {
//    if (args.length == 0) {
//      val vals = generateNumbersExceptFromSquares(50)
//    }
//    else if (args.length == 1) {
//      val vals = generateNumbersExceptFromSquares(args(0).toInt)
//    }
//    else if (args.length == 4) {
//      solvePythagoreanTriangle(args)
//    }
//
//    val x = Array.ofDim[Int](4, 4)
//    for (i <- Range(0, 4); j <- Range(0,4)) x(i)(j) = i+j
//
//    nicePrint(x)


//    println(g.mkString(" "))
//    println(findPiValue(5000).toString)
//    println(findPiValue(10000).toString)
//    println(findPiValue(20000).toString)
//    println(findPiValue(100000).toString)
//    println(findPiValue(200000).toString)
//  }
}
