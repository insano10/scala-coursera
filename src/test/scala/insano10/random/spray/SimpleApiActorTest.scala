package insano10.random.spray

import akka.actor.ActorRefFactory
import org.scalatest.{Matchers, FreeSpec, FunSuite}
import spray.http._
import spray.httpx.SprayJsonSupport
import spray.testkit.ScalatestRouteTest
import MyJsonProtocol._

//Note: don't forget to mix in SprayJsonSupport if you want to use responseAs[T]
class SimpleApiActorTest extends FreeSpec with SimpleApiRoutes with ScalatestRouteTest with SprayJsonSupport with Matchers {

  //use the test actor system from spray testkit
  def actorRefFactory: ActorRefFactory = system


  "Simple API Service" - {

    "when calling GET /api/path1 with header Content-Type: application/pdf" - {
      "should return HTTP 406" in {
        Get("/api/path1").withHeaders(HttpHeaders.`Content-Type`(MediaTypes.`application/pdf`)) ~> apiRoutes ~> check {
          status.intValue should equal(406)
        }
      }
    }

    "when calling GET /api/path1 with header Content-Type: application/vnd.type.a" - {
      "should return Bob Person object and OK" in {
        Get("/api/path1").withHeaders(HttpHeaders.`Content-Type`(MediaType.custom("application/vnd.type.a"))) ~> apiRoutes ~> check {
          status.intValue should equal(200)
          responseAs[Person] should equal(Person("Bob", "Type A", 30))
        }
      }
    }
  }
}
