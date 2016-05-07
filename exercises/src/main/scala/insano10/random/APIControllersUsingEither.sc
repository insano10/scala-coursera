/*
This is like the Result handling we used in the API controllers
except in scala the types and functions are all built in, yey!
 */


//generic types
type Errors = List[String]
type Result[T] = Either[Errors, T]

//type specific to our APIs to get a user's age
type AuthenticationResult = Result[String]
type AgeResult = Result[Int]


def authenticateThatWillSucceed(userId: String): AuthenticationResult = Right("sure, come on in")
def authenticateThatWillFail(userId: String): AuthenticationResult =
  Left(List("you shall not pass", "who do you think you are anyway"))

def getAgeThatWillSucceed(userId: String): AgeResult = Right(42)
def getAgeThatWillFail(userId: String): AgeResult = Left(List("unknown user"))


def getUserAge(userId: String, requestedUserId: String)
              (authFunc: String => AuthenticationResult, getAgeFunc: String => AgeResult): String = {

  //as long as there is a Right (success) the for expression will continue
  val result = for {
    authentication <- authFunc(userId).right
    ageResult <- getAgeFunc(requestedUserId).right
  } yield ageResult

  //then convert either the errors or the success object into the end type
  result.fold(
    e => e.mkString("\n"),
    s => s.toString
  )
}

val partiallyGetAge = getUserAge("1", "2") _

val success = partiallyGetAge(authenticateThatWillSucceed, getAgeThatWillSucceed)
val failOnAuth = partiallyGetAge(authenticateThatWillFail, getAgeThatWillSucceed)
val failOnGetAge = partiallyGetAge(authenticateThatWillSucceed, getAgeThatWillFail)

