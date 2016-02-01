import insano10.coursera.lists.{Cons, Nil}

object List {

  def apply[T](): Nil[T] = new Nil[T]

  def apply[T](x: T): Cons[T] = new Cons[T](x, new Nil[T])

  def apply[T](x1: T, x2: T): Cons[T] = new Cons[T](x1, new Cons[T](x2, new Nil[T]))
}

List()
List(5)
List(2, 3)

//List(1,2)   => List.apply(1,2)


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
