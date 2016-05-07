import insano10.coursera.lists.{Cons, Nil}

object MyList {

  def apply[T]() = Nil

  def apply[T](x: T): Cons[T] = new Cons[T](x, Nil)

  def apply[T](x1: T, x2: T): Cons[T] = new Cons[T](x1, new Cons[T](x2, Nil))
}

MyList()
MyList(5)
MyList(2, 3)

//List(1,2)   => List.apply(1,2)


//List insertion sort
def isort(xs: List[Int]): List[Int] = xs match {
  case List() => xs
  case y :: ys => insert(y, isort(ys))
}
def insert(x: Int, xs: List[Int]): List[Int] = xs match {
  case List() => List(x)
  case y :: ys =>
    if(x < y) x :: xs
    else y :: insert(x, ys)
}
val unsorted = List(2,6,8,4,6,1,8,5)
val sorted = isort(unsorted)


//covariance and contravariance
class Animal {}
class Cat extends Animal {}
class Leopard extends Cat {}
// +R = covariant
// -T = contravariant
class Foo[-T, +R] {

  def apply(t: T): R = ???
}

//this class.apply takes a Cat and gives you a Leopard
val f = new Foo[Cat, Leopard]()
//you can pass a subclass into a contravariant
//you can assign into a superclass from a covariant
val c: Animal = f.apply(new Leopard())
