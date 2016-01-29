/*

  Making a trait usable by only 1 type of class

 */

//some behaviour
trait Angry {

  this: Person =>
  def scream() = println("ARGH!")
  def shout() = println("AAAAAAH")
  def letItAllOut() = println("Screw you guys")
}
//a class
class Person(val name: String) {

  def sayHi() = println("hi, my name is " + name)
}

//a class with some behaviour
class AngryPerson(n: String) extends Person(n) with Angry {
}

//not a person
class Hippy {
}

//a regular person
val jenny = new Person("Jenny")
jenny.sayHi()
//an on-the-fly angry person
val james = new Person("James") with Angry
james.sayHi()
james.shout()
//a concrete angry person
val kate = new AngryPerson("Kate")
kate.sayHi()
kate.scream()

//cannot have an angry hippy
//val angryHippy = new Hippy with Angry


/*

  Making a class have a trait without choosing the exact implementation
  (Like a type parameter but for traits)

 */

//a sneaky trait
trait Sneaky {
  def beSneaky() = println("shhhh")
}

trait ReallySneaky extends Sneaky {
  override def beSneaky() = println("shhhhhhhhhhhhh")
}

//a cat class that has a sneaky trait that is not yet bound to any implementation of sneaky
class Cat {
  this: Sneaky =>
  def sayHi() = println("hi, I am a sneaky cat")
}
val reallySneakyCat = new Cat with ReallySneaky
reallySneakyCat.sayHi()
reallySneakyCat.beSneaky()
val sneakyCat = new Cat with Sneaky
sneakyCat.sayHi()
sneakyCat.beSneaky()


/*

  Stacking traits upon each other
  (decorating a class with a chain of behaviour)

 */

class IntTransformer {

  def transform(x: Int): Int = x
}

trait Incrementing extends IntTransformer {

  abstract override def transform(x: Int): Int = super.transform(x + 1)
}

trait Filtering extends IntTransformer {

  abstract override def transform(x: Int): Int = {
    if(x > 0) super.transform(x) else -1
  }
}

//traits get evaluated right to left, so first we filter then increment
val t = new IntTransformer with Incrementing with Filtering
t.transform(0)
t.transform(5)

//now we increment then filter
val t2 = new IntTransformer with Filtering with Incrementing
t2.transform(0)
t2.transform(5)