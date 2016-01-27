class Rational(x: Int, y: Int) {

  require(y > 0, "denominator must be positive")

  private def gcd(x: Int, y: Int): Int = if (y == 0) x else gcd(y, x % y)
  private val g = gcd(x, y)

  val numer = x / Math.abs(g)
  val denom = y / Math.abs(g)

  def add(that: Rational): Rational =
    new Rational(
      this.numer * that.denom + that.numer * this.denom,
      this.denom * that.denom
    )

  def neg: Rational =
    new Rational(-numer, denom)

  def sub(that: Rational): Rational =
    add(that.neg)

  def less(that: Rational): Boolean =
    this.numer * that.denom < that.numer * this.denom

  def max(that: Rational): Rational =
    if(this.less(that)) that else this

  override def toString: String =
    numer + "/" + denom
}

val r = new Rational(1, 2)
r.numer
r.denom
r.add(new Rational(1, 2))
r.sub(new Rational(1, 4))
r.neg

val x = new Rational(1, 3)
val y = new Rational(5, 7)
val z = new Rational(3, 2)

x.sub(y).sub(z)
x.less(z)
z.less(x)
x.max(z)

val num = new Rational(1, 2)
assert(num.numer == 3, "numerator should be 3")
