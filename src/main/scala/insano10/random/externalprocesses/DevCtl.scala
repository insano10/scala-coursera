package insano10.random.externalprocesses

import java.io.File

import insano10.random.typesafeconfig.MyConfig

import scala.io.Source
import scala.sys.process._

//use an Output trait so you can capture the console output in the test to assert against
class DevCtl extends Output {

  def run(args: Array[String], configLocation: String): Unit = {

    val config = MyConfig.getConfig(configLocation)

    args match {
      case Array(one) => oneArgCommands(one)
      case Array(one, two) => twoArgCommands(one, two, config)
      case _ => printHelp()
    }
  }

  private def oneArgCommands(arg: String): Unit = {

    print("running one arg")
    print(arg)

    arg match {
      case "list" => printCommands()
    }
  }

  private def twoArgCommands(arg1: String, arg2: String, config: MyConfig): Unit = {

    print("running two arg")
    print(arg1)
    print(arg2)

    arg1 match {
      case "start" => startService(arg2, config)
      case "stop" => stopService(arg2, config)
    }
  }

  private def printHelp(): Unit = print("Unrecognised command. Use devctl list to show available commands")

  def printCommands(): Unit = {
    print("Available Commands:")
    print("devctl list                = show available commands")
    print("devctl start <service nam> = start service <service name> according to parameters in ~/.devctl")
    print("devctl stop <service nam>  = stop service <service name> according to parameters in ~/.devctl")
  }

  private def startService(serviceName: String, config: MyConfig): Unit = {

    val service = config.service(serviceName)

    service match {
      case Some(s) =>
        /*
        Process.run will start the process in the background.
        ProcessIO lets you redirect input/output
         */
        val process = Process(s.startCmd, new File(s.location)).run(
          new ProcessIO(
            stdin => (),
            stdout => Source.fromInputStream(stdout).getLines.foreach(print),
            stderr => Source.fromInputStream(stderr).getLines.foreach(print)))
      case None => print(s"Unknown service [$serviceName]")
    }
  }

  private def stopService(serviceName: String, config: MyConfig): Unit ={

    val service = config.service(serviceName)

    service match {
      case Some(s) =>
        val process = Process(s.stopCmd).run(
          new ProcessIO(
            stdin => (),
            stdout => Source.fromInputStream(stdout).getLines.foreach(print),
            stderr => Source.fromInputStream(stderr).getLines.foreach(print)))
      case None => print(s"Unknown service [$serviceName]")
    }
  }
}

object DevCtl {

  def run(args: Array[String], configLocation: String) = new DevCtl().run(args, configLocation)
}
