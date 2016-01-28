package insano10.coursera.lists

trait List[T] {

  def isEmpty: Boolean
  def head: T
  def tail: List[T]
}
