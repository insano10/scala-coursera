package insano10.textbook.actors

import akka.actor.{Props, ActorSystem, Actor}
import akka.actor.Actor.Receive

object MultiMessageActors {

  sealed trait FoodMessage
  case class BaconMessage(smoked: Boolean) extends FoodMessage
  case class MackerelMessage(marinade: String) extends FoodMessage
  case class CrumpetMessage(toppings: List[String]) extends FoodMessage

  class MultiMessageActor extends Actor {
    override def receive: Receive = {

      case BaconMessage(smoked) =>
        val baconType = if (smoked) "smoked" else "unsmoked"
        println(s"Mmmmm $baconType bacon")
      case MackerelMessage(marinade) =>
        println(s"Mmmmm $marinade mackerel")
      case CrumpetMessage(toppings) =>
        println("Mmmmm, crumpets with " + toppings.mkString(" and "))
    }
  }

  def main(args: Array[String]): Unit = {

    val system = ActorSystem("CanYouTellItsLunchTime")

    val hungryActor = system.actorOf(Props[MultiMessageActor], "hungryActor")

    hungryActor ! new BaconMessage(true)
    hungryActor ! new MackerelMessage("soy and honey")
    hungryActor ! new CrumpetMessage(List("butter"))
  }
}
