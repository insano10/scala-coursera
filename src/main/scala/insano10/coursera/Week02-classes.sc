class Rational(x: Int, y: Int) {
  def numer = x
  def denom = y

  def add(other: Rational): Rational =
    new Rational(
      numer * other.denom + other.numer * denom,
      denom * other.denom
    )

  def neg: Rational =
    new Rational(-numer, denom)

  def sub(other: Rational): Rational =
    add(other.neg)

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


