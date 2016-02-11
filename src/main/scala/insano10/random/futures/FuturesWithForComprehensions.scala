package insano10.random.futures

import java.util.concurrent.TimeUnit

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.util.{Failure, Success}

object FuturesWithForComprehensions {

  implicit val ec = ExecutionContext.global

  def hello(): Future[String] =
    Future {
      "hello"
    }

  def world(): Future[String] =
    Future {
      "world"
    }

  def explode(): Future[String] =
    Future {
      throw new RuntimeException("BOOM")
    }


  //all futures complete successfully
  def successfulAggregate(): Unit = {

    //chain the futures together to form an aggregate future
    val aggregateFuture: Future[(String, String)] = for {
      res1 <- hello()
      res2 <- world()
    } yield (res1, res2)

    //do something once that aggregate future has completed
    aggregateFuture.onComplete {
      case Success(pair) => println(s"${pair._1} ${pair._2}")
      case Failure(e) => println("FAIL: " + e)
    }

    //stop the program exiting early before the async result comes back
    Await.ready(aggregateFuture, Duration(5, TimeUnit.SECONDS))
  }

  //the first future completes with failure
  def totallyUnsuccessfulAggregate(): Unit = {

    val aggregateFuture: Future[(String, String)] = for {
      res1 <- explode() // <- ruh roh
      res2 <- world()
    } yield (res1, res2)

    aggregateFuture.onComplete {
      case Success(pair) => println(s"${pair._1} ${pair._2}")
      case Failure(e) => println("FAIL: " + e)
    }

    Await.ready(aggregateFuture, Duration(5, TimeUnit.SECONDS))
  }

  //a subsequent future completes with failure
  def partiallyUnsuccessfulAggregate(): Unit = {

    val aggregateFuture: Future[(String, String)] = for {
      res1 <- hello()
      res2 <- explode() // <- ruh roh
    } yield (res1, res2)

    aggregateFuture.onComplete {
      case Success(pair) => println(s"${pair._1} ${pair._2}")
      case Failure(e) => println("FAIL: " + e)
    }

    Await.ready(aggregateFuture, Duration(5, TimeUnit.SECONDS))
  }

  def main(args: Array[String]): Unit = {

    successfulAggregate()
    totallyUnsuccessfulAggregate()
    partiallyUnsuccessfulAggregate()
  }
}
