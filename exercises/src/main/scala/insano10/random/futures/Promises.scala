package insano10.random.futures

import java.util.concurrent.TimeUnit.SECONDS

import scala.concurrent.duration.Duration
import scala.concurrent.{ExecutionContext, Await, Future, Promise}


object Promises {

  implicit val ec = ExecutionContext.global

  def successfulPromiseFulfillment() = {
    val p = Promise[String]
    val f = p.future

    //the future 'f' is completed by calling 'success' or 'failure' on the parent promise
    val producer = Future {

      val result = "hello world"
      p.success(result)
    }

    val consumer = Future {

      f.onSuccess { case s => println(s) }
    }

    Await.ready(consumer, Duration(5, SECONDS))
  }

  def failedPromiseFulfillment() = {
    val p = Promise[String]
    val f = p.future

    val producer = Future {
      p.failure(new RuntimeException("Jupiter was not aligned with Saturn"))
    }

    val consumer = Future {

      f.onFailure { case s => println(s) }
    }

    Await.ready(consumer, Duration(5, SECONDS))
  }

  def fulfillingAPromiseWithAnotherFuture() = {
    val p = Promise[String]
    val f = Future {
      "another future"
    }

    //the promise 'p' is completed with the result of the future 'f'
    val producer = Future {
      p.completeWith(f)
    }

    val consumer = Future {

      p.future.onSuccess { case s => println(s) }
    }

    Await.ready(consumer, Duration(5, SECONDS))
  }

  def main(args: Array[String]): Unit = {

    successfulPromiseFulfillment()
    failedPromiseFulfillment()
    fulfillingAPromiseWithAnotherFuture()
  }
}
