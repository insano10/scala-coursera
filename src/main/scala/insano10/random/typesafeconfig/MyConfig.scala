package insano10.random.typesafeconfig

import com.typesafe.config.{Config, ConfigFactory}

import scala.collection.JavaConversions._
//import JavaConversions to automatically convert from java -> scala collections


class MyConfig(services: Map[String, MyService]) {

  def service(serviceName: String): Option[MyService] = services.get(serviceName)

  override def toString = services.toString()
}

object MyConfig {

  def getConfig(configLocation: String): MyConfig = {

    val config = ConfigFactory.load(configLocation)
    val serviceList = config.getConfigList("services").map(buildService)
    val serviceTuples = for (service <- serviceList) yield (service.name, service)

    new MyConfig(serviceTuples.toMap)
  }

  private def buildService(config: Config): MyService = {

    new MyService(config.getString("name"), config.getString("location"), config.getString("startCmd"), config.getString("stopCmd"))
  }

}

case class MyService(val name: String, val location: String, val startCmd: String, val stopCmd: String)
