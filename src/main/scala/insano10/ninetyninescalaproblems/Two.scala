package insano10.ninetyninescalaproblems

/*
Find the last but one element in a list
 */
object Two {

  def lastButOne[T](xs: List[T]): T = xs match {

    case x :: y :: Nil => x
    case x :: xs1 => lastButOne(xs1)
    case _ => throw new IllegalArgumentException("List.length < 2")
  }
}
