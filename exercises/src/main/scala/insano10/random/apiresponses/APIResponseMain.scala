package insano10.random.apiresponses

import java.util.concurrent.TimeUnit

import insano10.random.apiresponses.APIResponse._

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future, ExecutionContext}
import scala.util.{Failure, Success}
import scalaz.Validation

object APIResponseMain {

  def main(args: Array[String]): Unit = {


    implicit def ec = ExecutionContext.global

    //define 2 arbitrary API functions
    def a(): APIResponse[Int] = APIResponse.success(42)
    def b(): APIResponse[Int] = APIResponse.success(43)
    def c(): APIResponse[Int] = APIResponse.failure(List("ruh roh"))
    def d(): APIResponse[Int] = APIResponse.failure(List("seriously"))

    //collect up all the responses from the multiple calls
    val multipleResponses = for {
      a <- a()
      b <- b()
    } yield (a, b)

    //get the future containing all the results
    val responsesFuture: Future[Validation[APIErrors, (Int, Int)]] = multipleResponses.asFuture

    responsesFuture.onComplete{
      case Success(x) => x.fold(
        e => println("FAIL: " + e),
        s => println("SUCCESS: " + s._1 + " " + s._2)
      )
      case Failure(e) => println("This should never happen as the failure future gets turned into " +
        "a Validation failure")
    }

    Await.ready(responsesFuture, Duration(5, TimeUnit.SECONDS))

    //Note: calling 'c' and 'd' does not collect up their errors, it only returns the first
    //this is probably ok though as you wouldn't want to make subsequent calls if an earlier one failed
  }
}
