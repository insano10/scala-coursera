package insano10.coursera.lists

class Cons[T](val head: T, val tail: List[T]) extends List[T] {

  def isEmpty: Boolean = false
}
