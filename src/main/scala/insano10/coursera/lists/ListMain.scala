package insano10.coursera.lists

object ListMain {

  def singleton[T](elem: T): List[T] = new Cons[T](elem, new Nil[T])

  def main(args: Array[String]): Unit = {

    val s = singleton(5)
    val s2 = singleton("foo")
  }
}
