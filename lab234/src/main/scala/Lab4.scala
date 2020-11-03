object Lab4 {

  val p1 = (x: Double) => x+1
  val p2 = (x: Double) => x+2
  val p3 = (x: Double) => x+3

  val pn = (n: Double) => {(x: Double) => scala.math.pow(x, n)}

  def eval(l: List[(Double) => Double], x: Double): Double = {
      var res = x

      for (func <- l.reverse) {
        res = func(res)
      }
      res
  }

  def sumFrac(eps: Double): Double = {
    @scala.annotation.tailrec
    def _sumFrac(currentSum: Double, n: Int): Double = {
      val nextFrac: Double = scala.math.pow(0.5, n)
      if (nextFrac > eps) _sumFrac(currentSum+nextFrac, n+1) else currentSum
    }

    _sumFrac(1.0, 1)
  }

  def sumFrac2(eps: Double): Double = {
    var counter: Double = 0
    @scala.annotation.tailrec def acc(current: Double): Unit = {
      if (current > eps) {
        counter += current
        acc(current / 2.0)
      }
    }
    acc(1.0)
    counter
  }

  def sumFrac3(eps: Double): Double = {
    var res = 0.0
    var frac = 1.0
    while (frac > eps) {
      res += frac
      frac = frac/2.0
    }
    res
  }

  def time[R](block: => R): R = {
    val t0 = System.nanoTime()
    val result = block    // call-by-name
    val t1 = System.nanoTime()
    println("Elapsed time: " + (t1 - t0) + "ns")
    result
  }

  def main(args: Array[String]): Unit = {
    val l1 = List(p1, p2, pn(3))
    val l2 = List(pn(3), p2, p1)

//    println(eval(l1, 1).toString)
//    println(eval(l2, 1).toString)
//    println(sumFrac(1e-3).toString)
//    println(sumFrac(1e-9).toString)

    time {sumFrac(1e-10)}
    time {sumFrac2(1e-10)}
    time {sumFrac3(1e-10)}
  }
}
