package insano10.random.spray

import akka.actor.{Props, ActorSystem}
import akka.io.IO
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._
import spray.can.Http

object SprayServer extends App {

    // create our actor system
    implicit val system = ActorSystem("simpleSpray")
    val service = system.actorOf(Props[SimpleApiActor], "simple-rest-service")

    // IO requires an implicit ActorSystem, and ? requires an implicit timeout
    // Bind HTTP to the specified service.
    implicit val timeout = Timeout(5.seconds)
    IO(Http) ? Http.Bind(service, interface = "localhost", port = 8080)

}
