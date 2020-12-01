object GenericContainerV2 {

  class T (val l: String) {
    override def toString: String = f"T($l)"
  }
  class T1I (val key: Int) extends T(key.toString) {
    override def toString: String = f"T1I($key)"
  }

  class SPair[+T1, +T2](val one: T1, val two: T2) {
    def _1[U >: T1](e: U): SPair[U, T2] = new SPair(e, two)
    def _2[U >: T2](e: U): SPair[T1, U] = new SPair(one, e)
  }

  def <<(t: SPair[T, T]): Unit = {
    println("" + t.one + " " + t.two)
  }

  def main(args: Array[String]): Unit = {
    val r = new SPair(1, 3)
    val z = new SPair(new T("k1"), new T("k2"))
    val k = new SPair(new T("k1"), new T1I(67))
    val kk = new SPair(new T1I(88765), new T1I(67))
    val krep1 = k._1(new T1I(1))
    val krep2 = krep1._2(new T("One"))

    println("" + r.one + " " + r.two)
    <<(z)
    <<(k)
    <<(kk)
    <<(krep1)
    <<(krep2)


  }

}
