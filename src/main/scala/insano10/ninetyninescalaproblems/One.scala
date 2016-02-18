package insano10.ninetyninescalaproblems

/*
Find the last element in a list
 */
object One {

  def last[T](xs: List[T]): T = xs match {
    case Nil => throw new IllegalArgumentException("Nil.last")
    case x :: Nil => x
    case x :: xs1 => last(xs1)
  }
}
