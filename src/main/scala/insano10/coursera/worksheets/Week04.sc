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
