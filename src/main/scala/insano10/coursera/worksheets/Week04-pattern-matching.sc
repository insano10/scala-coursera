
trait LivingThing {

  //you can have pattern matching functions in the type hierarchy itself
  def drink(): Unit = this match {
    case Person(n) => println("mmm, refreshing")
    case Dog(n) => println("slurp, slurp")
  }
}

case class Person(name: String) extends LivingThing {}
case class Dog(name: String) extends LivingThing {}

//case classes get an implicit companion object with an apply method so you can do the following
Person("Jenny")


//you can also have pattern matching functions externally to the hierarchy
def speak(x: LivingThing): String = x match {
  case Person("Kate") => "hi I'm special Kate"
  case Person(name) => "hi I'm a person called " + name
  case Dog(name) => "woof, my name is " + name
}

speak(Person("Sue"))
speak(Person("Kate"))
speak(Dog("Bob"))

Person("Jenny").drink()
Dog("Spot").drink()
