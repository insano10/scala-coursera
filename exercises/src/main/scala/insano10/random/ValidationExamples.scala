package insano10.random

import scala.util.{Failure, Success, Try}
import scalaz.{Semigroup, ListT, ValidationNel, Validation}

object ValidationExamples {

  /*
  We want a method that converts a list of strings into a list of integers
   */

  // attempt 1: this blows up if any elements are not convertable to an integer
  def toInts1(l: List[String]): List[Int] = {
    l.map(_.toInt)
  }

  //attempt 2: this is better as it doesn't blow up but returns a Try (like an Option in Java)
  //but it fails fast and only tells us of the first element that failed to be converted
  def toInts2(l: List[String]): Try[List[Int]] = {
    Try(l.map(_.toInt))
  }

  //attempt 3: this version collects all the errors inside the validation
  //each element in the list is mapped to a validation (which is either a success or a failure)
  //these validations are then reduced together using the +++ operator on Validation that squashes all the successes and errors together
  def toInts3(l: List[String]): ValidationNel[Throwable, List[Int]] = {

    //this is needed for Validation.+++ to define how to combine the success type of List[Int]
    //why do I have to define this myself? Surely there is a Semigroup defined somewhere for combining Lists???
    implicit val wtf = new Semigroup[List[Int]] {
      override def append(f1: List[Int], f2: => List[Int]): List[Int] = f1 ::: f2
    }

    val validationInts: List[ValidationNel[Throwable, List[Int]]] =
      l.map(e => Validation.fromTryCatchNonFatal(List(e.toInt)).toValidationNel)

    validationInts.reduce(_ +++ _)
  }


  def showDoubling(doubling: Try[List[Int]]): Unit = {
    doubling match {
      case Success(x) => println("Doubling Success: " + x)
      case Failure(e) => println("Doubling Failure: " + e)
    }
  }

  def main(args: Array[String]): Unit = {

    try {
      toInts1(List("1", "2", "3"))
      toInts1(List("1", "hi", "3"))
    } catch {
      case e: NumberFormatException => println("Failure: " + e)
    }

    val goodList = toInts2(List("1", "2", "3"))
    val badList = toInts2(List("1", "bye", "3"))

    //we can match on Trys
    badList match {
      case Success(x) => println("Success: " + x)
      case Failure(e) => println("Failure: " + e)
    }

    //and do for comprehensions to chain together different functions if successful (like we've done with Either)

    //in this case the Try contains 'success' so the for comprehension yields the desired result (multiplying all elements by 2)
    val goodDoubling: Try[List[Int]] = for{
      result <- goodList
    } yield result.map(_ * 2)

    showDoubling(goodDoubling)

    //in this case the Try contains 'failure' so the for comprehension short circuits and returns the same failed Try as before
    val badDoubling : Try[List[Int]] = for{
      result <- badList
    } yield result.map(_ * 2)

    showDoubling(badDoubling)


    val goodValidation: ValidationNel[Throwable, List[Int]] = toInts3(List("1", "2", "3"))
    goodValidation match {
      case scalaz.Success(x) => println("Validation Success: " + x)
      case scalaz.Failure(e) => println("Validation Failure: " + e)
    }

    val badValidation: ValidationNel[Throwable, List[Int]] = toInts3(List("hi", "bye", "3"))
    badValidation match {
      case scalaz.Success(x) => println("Validation Success: " + x)
      case scalaz.Failure(e) => println("Validation Failures: " + e)
    }
  }
}
