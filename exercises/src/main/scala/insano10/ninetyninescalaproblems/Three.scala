package insano10.ninetyninescalaproblems

/*
Find the kth element in a list
 */
object Three {

  def kthElement[T](k: Int, xs: List[T]): T = xs match {

    case x :: _ if k == 0 => x
    case x :: xs1 => kthElement(k - 1, xs1)
    case _ => throw new NoSuchElementException
  }
}
