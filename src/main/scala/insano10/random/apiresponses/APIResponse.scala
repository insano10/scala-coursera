package insano10.random.apiresponses

import insano10.random.apiresponses.APIResponse.APIErrors

import scala.concurrent.{ExecutionContext, Future}
import scalaz._
import scalaz.Scalaz._


//the private keyword here makes the default constructor private
case class APIResponse[A] private(underlying: Future[Validation[APIErrors, A]]) {

  //turn an A into a B inside the Future[Validation... using flatMap
  def map[B](f: A => B)(implicit ec: ExecutionContext): APIResponse[B] =
    flatMap(a => new APIResponse[B](
      Future.successful(f(a).success)
    ))

  //turn an A into an APIResponse[B]
  def flatMap[B](f: A => APIResponse[B])(implicit ec: ExecutionContext): APIResponse[B] =

  //construct a new APIResponse from this one
  //it takes a Future as a parameter
    APIResponse {

      //if this future turns out successful, apply the transformation function
      //else make the future succeed anyway but give it the APIErrors value rather than
      //the transformed value
      asFuture.flatMap {
        case Success(a) => f(a).asFuture
        case Failure(e) => Future.successful(e.failure)
      }
    }

  //use the 2 functions to turn the contents of this future (Validation[APIErrors, A])
  //into a Future[B]
  def fold[B](failure: APIErrors => B, success: A => B)(implicit ec: ExecutionContext): Future[B] =
    asFuture.map(_.fold(failure, success))

  def asFuture(implicit ec: ExecutionContext): Future[Validation[APIErrors, A]] = {

    //if the future fails, translate it into an APIErrors value
    underlying.recover {
      case err => List(err.toString).failure
    }
  }
}

//use a companion object to provide constructor functions
//(companion objects can use the private constructor)
object APIResponse {

  type APIErrors = List[String]

  def success[A](a: A): APIResponse[A] = APIResponse(Future.successful(a.success))
  def failure[A](e: APIErrors): APIResponse[A] = APIResponse(Future.successful(e.failure))
}
