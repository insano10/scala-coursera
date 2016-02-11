package insano10.random.futures

import java.util.concurrent.TimeUnit

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future, ExecutionContext}
import scala.util.{Failure, Success}

object FutureCallbacks {

  //this is an implicit parameter to Future
  implicit val ec = ExecutionContext.global

  def successful(): Future[String] = {

    val f: Future[String] = Future {
      "hello world"
    }

    f.onComplete{
      case Success(str) => println("SUCCESS: " + str)
      case Failure(e) => println("FAIL: " + e)
    }

    f
  }

  def unsuccessful(): Future[String] = {

    val f: Future[String] = Future {
      throw new RuntimeException("computer says no")
    }

    f.onFailure{
      case e => println("TOTAL FAIL: " + e)
    }

    f
  }


  def main(args: Array[String]): Unit = {

    val successfulFuture = successful()
    val unsuccessfulFuture = unsuccessful()


    Await.ready(successfulFuture, Duration(5, TimeUnit.SECONDS))
    Await.ready(unsuccessfulFuture, Duration(5, TimeUnit.SECONDS))

  }
}
