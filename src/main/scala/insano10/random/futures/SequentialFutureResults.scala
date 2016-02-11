package insano10.random.futures

import java.util.concurrent.TimeUnit

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.util.Success

object SequentialFutureResults {

  implicit val ec = ExecutionContext.global

  def main(args: Array[String]): Unit = {


    //use the 'andThen' method to chain side affects to happen after the future completes
    //the return value of the 'andThen' method is the original future with the original value
    //which is why it is only used for side effects
    val f: Future[String] =
      Future {
        "hello world"
      }.andThen {
        case Success(result) => println("side effect 1: " + result)
      }.andThen {
        case Success(sameResult) => println("side effect 2: " + sameResult)
      }

    println(Await.result(f, Duration(5, TimeUnit.SECONDS)))
  }
}
