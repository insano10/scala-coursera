import scala.concurrent.ExecutionContext
import scalaz.Scalaz._
import scalaz._
import scalaz.Validation.FlatMap._

/*
Note: Validation flatMap does not collect errors across multiple functions
(which is why you are discouraged to use it in for comprehensions and need to import it)
So you only get the errors from the first function that failed then the rest are passed through

If you want to continue calling methods all collect all the errors using applicative functors
(I have no idea what this phrase means) as shown in APIControllersCollectingErrorsOverMultipleFunctions

 */


type APIError = List[String]


class AuthenticationToken(val authenticated: Boolean)
case class User(val name: String)
implicit val ec = ExecutionContext.global

def authenticate(userId: String): Validation[APIError, AuthenticationToken] =
  if (userId == "bob") new AuthenticationToken(true).success
  else List(s"Unknown user: $userId").failure

def getUser(userId: String): Validation[APIError, User] =
  if (userId == "alice") new User("Alice").success
  else List(s"User $userId does not exist").failure


def getUserJson(requesterUserId: String, requestedUserId: String): String = {

  val userResponse: Validation[APIError, User] = for {
    authentication <- authenticate(requesterUserId)
    user <- getUser(requestedUserId)
  } yield user

  userResponse.fold(
    e => e.mkString(". "),
    u => u.toString)
}

val success = getUserJson("bob", "alice")
val authFailure = getUserJson("charlie", "alice")
val unknownUserFailure = getUserJson("bob", "john")
val allTheFail = getUserJson("foo", "bar")


