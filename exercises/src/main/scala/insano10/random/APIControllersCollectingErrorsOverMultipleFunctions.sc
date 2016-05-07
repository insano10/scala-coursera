import scala.concurrent.ExecutionContext
import scalaz.Scalaz._
import scalaz._

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

  (authenticate(requesterUserId) |@| getUser(requestedUserId)) {
    case(auth, user) => user.name
  }.fold(e => e.toString, u => u)
}

val success = getUserJson("bob", "alice")
val authFailure = getUserJson("charlie", "alice")
val unknownUserFailure = getUserJson("bob", "john")

val allTheFail = getUserJson("foo", "bar")


