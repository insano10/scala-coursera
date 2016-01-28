package insano10.coursera.lists

object ListMain {

  def singleton[T](elem: T): List[T] = new Cons[T](elem, new Nil[T])

  def nth[T](idx: Int, list: List[T]): T = {

    if(list.isEmpty) throw new IndexOutOfBoundsException
    else if (idx == 0) list.head
    else nth(idx - 1, list.tail)
  }

  def main(args: Array[String]): Unit = {

    val s = singleton(5)
    val s2 = singleton("foo")

    //exercise nth
    val list = new Cons(0, new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Cons(5, new Nil))))))

    println(nth(0, list))
    println(nth(2, list))
    println(nth(4, list))

    println(nth(-1, list))
    println(nth(6, list))
  }
}
