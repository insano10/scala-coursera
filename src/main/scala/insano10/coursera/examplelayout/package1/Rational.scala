package insano10.coursera.examplelayout.package1

class Rational(x: Int, y: Int) {

  require(y > 0, "denominator must be positive")

  def this(x: Int) = this(x, 1)

  private def gcd(x: Int, y: Int): Int = if (y == 0) x else gcd(y, x % y)
  private val g = gcd(x, y)

  val numer = x / Math.abs(g)
  val denom = y / Math.abs(g)

  def +(that: Rational): Rational =
    new Rational(
      this.numer * that.denom + that.numer * this.denom,
      this.denom * that.denom
    )

  def unary_- : Rational =
    new Rational(-numer, denom)

  def -(that: Rational): Rational =
    this + -that

  def <(that: Rational): Boolean =
    this.numer * that.denom < that.numer * this.denom

  def max(that: Rational): Rational =
    if(this < that) that else this

  override def toString: String =
    numer + "/" + denom
}
