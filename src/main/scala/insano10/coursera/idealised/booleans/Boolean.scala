package insano10.coursera.idealised.booleans

abstract class Boolean {

  def ifThenElse[T](t: => T, e: => T): T

  def &&(x: => Boolean): Boolean = ifThenElse(x, myFalse)

  def ||(x: Boolean): Boolean = ifThenElse(myTrue, x)

  def unary_! : Boolean = ifThenElse(myFalse, myTrue)

  def ==(x: Boolean): Boolean = ifThenElse(x, !x)

  def !=(x: Boolean): Boolean = ! ==(x)

  def <(x: Boolean): Boolean = ifThenElse(myFalse, x)
}

object myTrue extends Boolean {
  override def ifThenElse[T](t: => T, e: => T): T = t

  override def toString = "true"
}

object myFalse extends Boolean {
  override def ifThenElse[T](t: => T, e: => T): T = e

  override def toString = "false"

}