package insano10.coursera.naturalnumbers

object NatMain {

  def main(args: Array[String]): Unit = {

    println(Zero)
    println(new Succ(Zero))
    println(Zero.successor)

    val two = new Succ(new Succ(Zero))
    val three = new Succ(new Succ(new Succ(Zero)))

    val five = two + three

    /*

    3 + 2

    = new (Suc(new Suc(new Suc(Zero))) + new Suc(new Suc(Zero))
    = new Suc( new Suc(new Suc(Zero)) + new Suc(new Suc(Zero)) )
    = new Suc( new Suc( new Suc(Zero) + new Suc(new Suc(Zero)) ) )
    = new Suc( new Suc( new Suc( Zero + new Suc(new Suc(Zero)) ) ) )
    = new Suc( new Suc( new Suc( new Suc(new Suc(Zero)) ) ) )
    = new Suc(new Suc(new Suc(new Suc(new Suc(Zero)))))

     */

    two - three
  }
}
