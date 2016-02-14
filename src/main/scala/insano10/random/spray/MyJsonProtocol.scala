package insano10.random.spray

import spray.json.DefaultJsonProtocol

object MyJsonProtocol extends DefaultJsonProtocol {
  /*
  Provide some implicit conversions from T -> RootJsonFormat[T] so you can just complete
  an HTTP request with an object and spray will marshall it to JSON using these methods
   */
  implicit val personFormat = jsonFormat3(Person)
}

case class Person(name: String, firstName: String, age: Long)