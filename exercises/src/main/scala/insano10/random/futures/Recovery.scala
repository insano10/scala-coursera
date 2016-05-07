package insano10.random.futures

import java.util.concurrent.TimeUnit

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}

object Recovery {

  implicit val ec = ExecutionContext.global

  def helloWorld(): Future[String] =
    Future {
      "hello world"
    }

  //use the 'recover' method to return a default value if the future was to fail
  def defaultHelloWorld(): Future[String] =
    Future {
      throw new RuntimeException("BOOM")
    }.recover {
      case x => "alternative world"
    }

  //use the 'recoverWith' method to recover using another future
  //in this case we fall back to the original helloWorld future
  def dynamicHelloWorld(): Future[String] =
    Future {
      throw new RuntimeException("BOOM")
    }.recoverWith {
      case x => helloWorld()
    }

  //if the 'recoverWith' future also fails, the failure result of the
  //second future is returned
  def unrecoveredWorld(): Future[String] =
    Future {
      throw new RuntimeException("BOOM")
    }.recoverWith {
      case x => Future {
        throw new RuntimeException("It's the end of the world as we know it")
      }
    }

  //use the 'fallbackTo' method to fallback to another future if the first fails
  //In the case that the fallback future succeeds, it behaves the same as recoverWith
  def fallbackWorld(): Future[String] =
    Future {
      throw new RuntimeException("BOOM")
    }.fallbackTo(helloWorld())

  //if the 'fallbackTo' future also fails, the failure result of the
  //original future is returned
  def unfallBackedWorld(): Future[String] =
    Future {
      throw new RuntimeException("ORIGINAL BOOM")
    }.fallbackTo(
      Future {
        throw new RuntimeException("NEW BOOM")
      })

  def main(args: Array[String]): Unit = {

    val successfulFuture = helloWorld()

    val defaultRecoveredFuture = defaultHelloWorld()
    val dynamicRecoveredFuture = dynamicHelloWorld()
    val unrecoveredFuture = unrecoveredWorld()

    val fallbackFuture = fallbackWorld()
    val unfallbackedFuture = unfallBackedWorld()

    println(Await.result(successfulFuture, Duration(5, TimeUnit.SECONDS)))
    println(Await.result(defaultRecoveredFuture, Duration(5, TimeUnit.SECONDS)))
    println(Await.result(dynamicRecoveredFuture, Duration(5, TimeUnit.SECONDS)))
    println(Await.result(fallbackFuture, Duration(5, TimeUnit.SECONDS)))

    try {
      println(Await.result(unrecoveredFuture, Duration(5, TimeUnit.SECONDS)))
    } catch {
      case e: RuntimeException => println(e)
    }

    try {
      println(Await.result(unfallbackedFuture, Duration(5, TimeUnit.SECONDS)))
    } catch {
      case e: RuntimeException => println(e)
    }

  }
}
