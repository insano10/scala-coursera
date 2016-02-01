package insano10.coursera.naturalnumbers

import scala.annotation.tailrec

abstract class Nat {
  def isZero: Boolean

  def predecessor: Nat

  def successor: Nat = new Succ(this)

  def +(that: Nat): Nat

  def -(that: Nat): Nat
}

object Zero extends Nat {

  override def isZero: Boolean = true

  override def predecessor: Nat = throw new IllegalStateException("Cannot get predecessor of Zero")

  override def +(that: Nat): Nat = that

  override def -(that: Nat): Nat = if(that.isZero) this else throw new IllegalStateException("Cannot subtract from zero")
}

class Succ(n: Nat) extends Nat {

  override def isZero: Boolean = false

  override def predecessor: Nat = n

  //he ingredients to the recursion (+) must be smaller than where we are now, hence passing in 'n' rather than 'this'
  override def +(that: Nat): Nat = new Succ(n + that)

  //this was my first try, it works but is a bit long winded
  def myPlus(that: Nat): Nat = {

    @tailrec
    def add(acc: Nat, counter: Nat): Nat =
      if (counter.isZero) acc
      else add(new Succ(acc), counter.predecessor)

    add(this, that)
  }

  //ditto
  override def -(that: Nat): Nat =  if(that.isZero) this else n - that.predecessor

  //ditto
  def myMinus(that: Nat): Nat = {

    @tailrec
    def sub(acc: Nat, counter: Nat) : Nat =
      if(counter.isZero) acc
      else sub(acc.predecessor, counter.predecessor)

    sub(this, that)
  }
}