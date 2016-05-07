def sum(xs: List[Int]): Int = xs.reduce((x, y) => x + y)
def sumShort(xs: List[Int]): Int = xs.reduce(_ + _)
def sumFold(xs: List[Int]): Int = xs.fold(0)(_ + _)
sum(List(1, 2, 3))
sumShort(List(1, 2, 3))
sumFold(List(1, 2, 3))
List(1, 2, 3).sum

def product(xs: List[Int]): Int = xs.reduce((x, y) => x * y)
def productShort(xs: List[Int]): Int = xs.reduce(_ * _)
def productFold(xs: List[Int]): Int = xs.fold(1)(_ * _)
product(List(1, 2, 3))
productShort(List(1, 2, 3))
productFold(List(1, 2, 3))
List(1, 2, 3).product

def stringify[T](xs: List[T]): String = xs.foldLeft("")(_ + _)
stringify(List(1,2,3))

//if you foldRight the first param is the new element and the second is the accumulated list
//as functions ending in colon apply to the RHS, this works as cons belongs to List
def concatRight[T](xs: List[T], ys: List[T]): List[T] =
  (xs foldRight ys) (_ :: _)

//if you foldLeft the first param is the accumulated list and the second is the new element
//so you have to swap the elements before applying cons as the List needs to be on the RHS
def concatLeft[T](xs: List[T], ys: List[T]): List[T] =
  (xs foldLeft ys) ((x, y) => y :: x)

def mapFun[T, U](xs: List[T], f: T => U): List[U] =
  (xs foldRight List[U]())(f(_) :: _ )

def lengthFun[T](xs: List[T]): Int =
  (xs foldRight 0)((_,length) => length + 1)

//the compiler cannot infer the types here so we need to add an explicit method type
mapFun[Int, String](List(1, 2, 3), x => "'" + x.toString + "'")
lengthFun(List(1,2,3))
