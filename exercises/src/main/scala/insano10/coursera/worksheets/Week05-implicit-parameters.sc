//if a parameter is implicit you don't always have to specify it
def sort[T](xs: List[T])(implicit ord: Ordering[T]): List[T] = xs.sorted(ord)

/*
An implicit value has the same type as required and is either:
- directly in scope of the call site
- defined in the companion object of the implicit type itself
 */

//here, we need an Ordering with type parameter Int.
//this is defined in the Ordering companion object as Ordering.Int so we don't have to supply it
sort(List(3, 5, 1, 6))
sort(List(3, 5, 1, 6))(Ordering.Int)


//here we need a String
def repeat(x: Int)(implicit s: String): String = {
  if (x == 1) s else s + repeat(x - 1)
}

//as long as there is only 1 candidate in scope (marked as implicit) it will be used
implicit val defaultStr: String = "bye"

repeat(5)("hi")
repeat(5)
