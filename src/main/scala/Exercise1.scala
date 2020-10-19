import scala.util.Random

object Exercise1 {

  def pow2(x: Double): Double = x*x

  def findPiValue(N: Int): Double = {
    val generator = new Random()
    var insideN = 0

    // Generate sequence of tuples (points)
    val points = for(_ <- Range(0, N)) yield (generator.nextDouble(), generator.nextDouble())

    // Check if points are inside a circle
    for (i <- points) {
      if (scala.math.sqrt( pow2(i._1) + pow2(i._2) ) < 1.0) {
        insideN += 1
      }
    }

    // Calculate pi value
    4.0 * (insideN.toDouble / N.toDouble)
  }

  def main(args: Array[String]): Unit = {
    val N: Int = if (args.length == 1) args(0).toInt else 50
    val g = for(x <- 1 to N if (x == 1 || !(scala.math.sqrt(x).isValidInt))) yield x

    println(g.mkString(" "))
    println(findPiValue(5000).toString)
    println(findPiValue(10000).toString)
    println(findPiValue(20000).toString)
    println(findPiValue(100000).toString)
    println(findPiValue(200000).toString)
  }
}
