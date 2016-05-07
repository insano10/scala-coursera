package insano10.textbook.actors

import akka.actor.{Props, ActorSystem, Actor}

object EchoActors {

  //this actor prints out a single message then stops itself
  class SingleEchoActor extends Actor {
    override def receive: Receive = {
      case x =>
        println(x)
        context.stop(self)
    }
  }

  //this actor will continue printing out messages forever
  class ForeverEchoActor extends Actor {
    override def receive: Receive = {
      case x => println(x)
    }
  }

  def main(args: Array[String]): Unit = {

    val system = ActorSystem("simpleSystem")

    val singleEchoActor = system.actorOf(Props[SingleEchoActor], "singleEchoActor")
    val foreverEchoActor = system.actorOf(Props[ForeverEchoActor], "foreverEchoActor")

    singleEchoActor ! "hello"
    singleEchoActor ! "world"

    foreverEchoActor ! "hello"
    foreverEchoActor ! "world"

    system.shutdown()
  }
}
