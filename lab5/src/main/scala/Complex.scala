class Complex(val re: Double, val im: Double) {

  def +(other: Complex): Complex = {
    Complex(re + other.re, im + other.im)
  }

  def -(other: Complex): Complex = {
    Complex(re - other.re, im - other.im)
  }

  def r: Double = math.hypot(re, im)

  def angle: Double = math.atan(im / re)

  override def toString: String = {
    "Re:{" + re + "} Im:{" + im + "}"
  }
}

object Complex {
  def apply(re: Double, im: Double): Complex = new Complex(re, im)

  def polar(r: Double, angle: Double): Complex = {
    Complex(math.abs(r) * math.cos(angle), math.abs(r) * math.sin(angle))
  }
}
