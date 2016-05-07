package insano10.coursera.idealised.booleans

object BooleanMain {

  def main(args: Array[String]): Unit = {

    println("&&")
    println(myTrue && myTrue)
    println(myTrue && myFalse)
    println(myFalse && myTrue)
    println(myFalse && myFalse)
    println()

    println("||")
    println(myTrue || myTrue)
    println(myTrue || myFalse)
    println(myFalse || myTrue)
    println(myFalse || myFalse)
    println()

    println("!")
    println(!myTrue)
    println(!myFalse)
    println()

    println("==")
    println(myTrue == myTrue)
    println(myTrue == myFalse)
    println(myFalse == myFalse)
    println()

    println("!=")
    println(myTrue != myTrue)
    println(myTrue != myFalse)
    println(myFalse != myFalse)
    println()

    println("<")
    println(myTrue < myTrue)
    println(myTrue < myFalse)
    println(myFalse < myTrue)
    println(myFalse < myFalse)
    println()
  }
}
