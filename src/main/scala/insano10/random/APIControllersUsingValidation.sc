import scala.concurrent.ExecutionContext
import scalaz.Scalaz._
import scalaz._
import scalaz.Validation.FlatMap._

//Note: you need to import flatmap if you want to use it on Validation
//this is because Validation does not accumulate errors and so shouldn't be used
//in for comprehensions

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


