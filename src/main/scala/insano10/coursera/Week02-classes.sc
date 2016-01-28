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

val r = new Rational(1, 2)
r.numer
r.denom
r + new Rational(1, 2)
r - new Rational(1, 4)
-r

val x = new Rational(1, 3)
val y = new Rational(5, 7)
val z = new Rational(3, 2)

x - y - z
x < z
z < x
x.max(z)

val num = new Rational(3, 2)
assert(num.numer == 3, "numerator should be 3")

//alternate constructor
new Rational(4)

//infix method calls
x + x
x.+(x)

//declaring fields of classes using parameter 'val' prefix
class Foo(val x: Int, y: Int) {

}

new Foo(1,2).x
//new Foo(1,2).y <- this doesn't work as y is not a field

