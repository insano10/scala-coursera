package insano10.random.externalprocesses

import org.scalatest.Matchers
import org.scalatest.concurrent.Eventually
import org.scalatest.time.{Seconds, Span}
import scala.sys.process._

class DevCtlTest extends org.scalatest.FunSuite with Matchers with Eventually {

  //override the default wait period of 150ms in 'eventually'
  implicit override val patienceConfig = PatienceConfig(timeout = scaled(Span(10, Seconds)))

  val configLocation: String = "externalprocesses/application.conf"

  test("should print help message when no arguments are provided") {

    //replace the Output trait with MockOutput so we capture the output lines
    val devCtl = new DevCtl with MockOutput

    devCtl.run(Array(), configLocation)
    devCtl.messages should contain("Unrecognised command. Use devctl list to show available commands")
  }

  test("should print commands when given 'list'") {

    val devCtl = new DevCtl with MockOutput

    devCtl.run(Array("list"), configLocation)
    devCtl.messages should contain("Available Commands:")
  }

  test("should execute startCmd when given 'start <known service>'") {

    val devCtl = new DevCtl with MockOutput

    devCtl.run(Array("start", "sandwich-service"), configLocation)

    //this is like a waiter where it will check the condition periodically until it times out or succeeds
    eventually {
      devCtl.messages should contain("mmm sandwiches")
    }
  }

  test("should execute startCmd referencing a script when given 'start <known service>'") {

    val devCtl = new DevCtl with MockOutput

    devCtl.run(Array("start", "ham-service"), configLocation)
    eventually {
      devCtl.messages should contain("mmm ham")
    }
  }

  test("should execute startCmd referencing a long running script when given 'start <known service>'") {

    val devCtl = new DevCtl with MockOutput

    devCtl.run(Array("start", "cheese-service"), configLocation)
    eventually {
      atLeast(3, devCtl.messages) should equal("say cheese")
    }
  }

  test("should print error when given 'start <unknown service>'") {

    val devCtl = new DevCtl with MockOutput

    devCtl.run(Array("start", "foo"), configLocation)
    devCtl.messages should contain("Unknown service [foo]")
  }

  ignore("should stop a service using the stopCmd when given 'start <known service'") {

    val devCtl = new DevCtl with MockOutput

    devCtl.run(Array("start", "cheese-service"), configLocation)
    Thread.sleep(1000)
    devCtl.run(Array("stop", "cheese-service"), configLocation)

    //there is currently no way of telling if the service was stopped
    eventually {
      devCtl.messages should contain("Successfully stopped [cheese-service]")
    }
  }

  test("should show how to synchronously get the output of a single command"){

    // !! gets the output as a String (does not direct to console)
    println("ls -l".!!)

    // ! gets the exit code of the process (stdout is directed to the console)
    println("ls -l".!)
  }

  test("should show how to synchronously get the output of a pipeline of commands") {

    // #| chains commands together
    println(("ls -l" #| "grep README").!!)

    //use Process to handle command where the arguments may have spaces and so be treated as multiple arguments
    println(("ls -l" #| "grep README" #| Process("awk", Seq("{ print $9 }"))).!!)
  }
}
