object GenericContainter {
  class A {
    override def toString: String = "A"
  }

  class B (val x: Int) extends A {
    override def toString: String = "B: " + x.toString
  }

  class C (x: Int) extends B(x) {
    override def toString: String = "C: " + x.toString
  }

  class TwistedMonoPair[+T] (val elem: (T, T)) {
    def apply(i: Int): T = if (i == 0) elem._1 else elem._2

    def replace[U >: T](i: Int)(e: U): TwistedMonoPair[U] = {
      if (i == 0) new TwistedMonoPair[U](elem.copy(_1 = e)) 
      else new TwistedMonoPair[U](elem.copy(_2 = e))
    }

    override def toString: String = elem._1.toString + " " + elem._2.toString
  }

  object TwistedMonoPair {
    def apply[T](elem: (T, T)): TwistedMonoPair[T] = new TwistedMonoPair(elem)
  }

//  def main(args: Array[String]): Unit = {
//    val a: TwistedMonoPair[A] = TwistedMonoPair[A](new B(7), new A)
//    println(a(0))
//    println(a(1))
//    println(a)
//
//    val b: TwistedMonoPair[A] = TwistedMonoPair[B](new B(9), new C(77)) // covariance
//    println(b)
//
////    val c: TwistedMonoPair[A] = TwistedMonoPair[B](new B(9), new A) // won't compile
//
//    val d1 = b.replace(0)(new A) // conversion to TwistedMonoPair[A]
//    println(d1)
//
//    val tA: TwistedMonoPair[A] = d1
////    val tB: TwistedMonoPair[B] = d1 // won't compile
//
//    val d2 = b.replace(1)(new A) // conversion to TwistedMonoPair[A]
//    println(d2)
//  }

}
