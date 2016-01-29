abstract class IntSet {

  def inc(x: Integer): IntSet

  def contains(x: Integer): Boolean

  def union(s: IntSet): IntSet

  def intersection(s: IntSet): IntSet

  def visit(visitor: (Int, IntSet) => IntSet, acc: IntSet): IntSet
}

object Empty extends IntSet {

  def inc(x: Integer): IntSet = new NonEmpty(x, Empty, Empty)

  def contains(x: Integer): Boolean = false

  def union(s: IntSet): IntSet = s

  def intersection(s: IntSet): IntSet = Empty

  def visit(visitor: (Int, IntSet) => IntSet, acc: IntSet): IntSet = acc

  override def toString: String = ""
}

class NonEmpty(elem: Integer, left: IntSet, right: IntSet) extends IntSet {

  def inc(x: Integer): IntSet = {

    if (x < elem) new NonEmpty(elem, left inc x, right)
    else if (x > elem) new NonEmpty(elem, left, right inc x)
    else this
  }

  def contains(x: Integer): Boolean = {

    if (x < elem) left contains x
    else if (x > elem) right contains x
    else true
  }

  def union(s: IntSet): IntSet = {

    left union s union right inc elem
  }

  def intersection(s: IntSet): IntSet = {

    this.visit((num, acc) => {
      if (s.contains(num)) acc.inc(num) else acc
    }, Empty)
  }

  def visit(visitor: (Int, IntSet) => IntSet, acc: IntSet): IntSet = {

    right.visit(visitor, left.visit(visitor, visitor(elem, acc)))
  }

  override def toString: String = "{" + left + elem + right + "}"
}

val set1 = new NonEmpty(5, new NonEmpty(3, Empty, Empty), new NonEmpty(7, Empty, Empty))
val set2 = new NonEmpty(5, new NonEmpty(3, Empty, Empty), new NonEmpty(8, Empty, Empty))

val intersection = set1 intersection set2

//persistent data structure behaviour
//do not mutate, return a new data structure on modification
val intSet = Empty
val i2 = intSet inc 5
val i3 = i2 inc 10
val i4 = i3 inc 3

i4 contains 5
i4 contains 3
i4 contains 10
i4 contains 2

i2 contains 10

val setA = new NonEmpty(5, new NonEmpty(1, Empty, Empty), new NonEmpty(7, Empty, Empty))
val setB = new NonEmpty(7, new NonEmpty(3, Empty, Empty), Empty)
setA union setB

//singleton
object Thing {
  def num = 5
}

val x = Thing
x.num
Thing.num


//week04 code

def assertAllPos(s: IntSet): IntSet = {

  s.visit(
    (x, acc) => if (x < 0) throw new IllegalStateException(s"$x is negative") else s,
    s)
}

val posSet = new NonEmpty(5, new NonEmpty(1, Empty, Empty), new NonEmpty(7, Empty, Empty))
val negSet = new NonEmpty(5, new NonEmpty(-1, Empty, Empty), new NonEmpty(7, Empty, Empty))
assertAllPos(posSet)
assertAllPos(negSet)