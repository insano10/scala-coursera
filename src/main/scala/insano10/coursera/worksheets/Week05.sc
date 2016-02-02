
object Lists {

  def last[T](xs: List[T]): T = xs match {
    case Nil => throw new IllegalArgumentException("nil.last")
    case List(x) => x
    case y :: ys => last(ys)
  }

  def init[T](xs: List[T]): List[T] = xs match {
    case Nil => throw new IllegalArgumentException("nil.init")
    case List(x) => Nil
    case y :: ys => y :: init(ys)
  }

  /*
  xs ::: ys
  ys.:::(xs)
   */
  def concat[T](xs: List[T], ys: List[T]): List[T] = xs match {

    case Nil => ys
    case z :: zs => z :: concat(zs, ys)
  }

  def reverse[T](xs: List[T]): List[T] = xs match {

    case Nil => Nil
    case y :: ys => reverse(ys) ::: List(y)
  }

  def removeAt[T](n: Int, xs: List[T]): List[T] = xs match {

    case Nil => xs
    case y :: ys => if (n == 0) ys else y :: removeAt(n - 1, ys)
  }

  def flatten(xs: List[Any]): List[Any] = xs match {

    case Nil => Nil
    case (y: List[Any]) :: ys => flatten(y) ::: flatten(ys)
    case (y: Any) :: ys => y :: flatten(ys)
  }

  last(List(1, 2, 3, 4, 5))
  init(List(1, 2, 3, 4, 5))
  concat(List(1, 2, 3), List(4, 5))
  reverse(List(1, 2, 3, 4, 5))

  removeAt(0, List(1, 2, 3, 4, 5))
  removeAt(2, List(1, 2, 3, 4, 5))
  removeAt(4, List(1, 2, 3, 4, 5))
  removeAt(5, List(1, 2, 3, 4, 5))
  flatten(List(List(1, 1), 2, List(3, List(5, 8))))
}