package insano10.random.spray

import akka.actor.Actor
import spray.routing._
import spray.http._
import MediaTypes._
import spray.httpx.SprayJsonSupport._
import MyJsonProtocol._

// simple actor that handles the routes.
class SimpleApiActor extends Actor with SimpleApiRoutes {

  // required as implicit value for the HttpService
  // included from SJService
  def actorRefFactory = context

  // we don't create a receive function ourselves, but use
  // the runRoute function from the HttpService to create
  // one for us, based on the supplied routes.
  def receive = runRoute(apiRoutes)

}

trait SimpleApiRoutes extends HttpService {

  val apiRoutes =
    pathPrefix("api") {
      path("path1") {
        get {

          // Get the value of the content-header. Spray provides multiple ways to do this.
          headerValue({
            case x@HttpHeaders.`Content-Type`(value) => Some(value)
            case default => None
          }) {
            // the header is passed in containing the content type
            // we match the header using a case statement, and depending
            // on the content type we return a specific object
            header => header match {

              // if we have this content type we create a custom response
              case ContentType(MediaType("application/vnd.type.a"), _) =>
                respondWithMediaType(`application/json`) {
                  complete {
                    Person("Bob", "Type A", 30)
                  }
                }

              // if we have another content-type we return a different type.
              case ContentType(MediaType("application/vnd.type.b"), _) =>
                respondWithMediaType(`application/json`) {
                  complete {
                    Person("Bob", "Type B", 31)
                  }
                }

              // if content-types do not match, return an error code
              case default => complete(HttpResponse(406))
            }
          }
        }
      } ~
        path("path2") {
          get {
            // respond with text/html.
            respondWithMediaType(`text/html`) {
              complete {
                // respond with a set of HTML elements
                <html>
                  <body>
                    <h1>Path 2</h1>
                  </body>
                </html>
              }
            }
          }
        }
    }
}