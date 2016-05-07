package insano10.textbook.actors

import akka.actor._
import akka.pattern.Patterns
import akka.routing.{RoundRobinPool, Broadcast, RoundRobinRouter}

object RoutingActors {

  sealed trait Message
  case class AddTheseNumbers(nums: List[Int]) extends Message
  case class Result(value: Long) extends Message

  //this actor will serve out the addition of a list of integers to 5 worker actors and add up the results coming back
  class MasterActor extends Actor {

    val sliceSize = 10
    val workerRouter = context.actorOf(Props[WorkerActor].withRouter(RoundRobinPool(5)), "workerRouter")

    var total: Long = 0

    context.watch(workerRouter)

    override def receive: Receive = {
      case AddTheseNumbers(nums) =>
        for (i <- 0 to nums.length by sliceSize) workerRouter ! AddTheseNumbers(nums.slice(i, i + sliceSize))
        workerRouter ! Broadcast(PoisonPill)

      case Result(value) => total += value

      case Terminated(subject) =>
        println("Got terminated notification: " + subject)
        println("Total is: " + total + " (should be " + (1 to 100000).toList.sum + ")")
        //the total is always 5,000,050,000 but it should be 705,082,704 (why is it bigger?)
    }
  }

  //this actor will add up lists of numbers and send them back to the sender
  class WorkerActor extends Actor {
    override def receive: Receive = {
      case AddTheseNumbers(nums) =>
        println(self + ": adding up nums " + nums)
        sender ! new Result(nums.sum)
    }
  }

  def main(args: Array[String]): Unit = {

    val system = ActorSystem("ReallyLongWindedWayOfAddingUpAListOfIntegers")

    val masterActor = system.actorOf(Props[MasterActor], "masterActor")

    //tell the master actor to add up all the numbers from 1 to 1000
    masterActor ! new AddTheseNumbers((1 to 100000).toList)
  }
}
