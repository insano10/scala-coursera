package insano10.random.typesafeconfig

object TypesafeMain {

  def main(args: Array[String]): Unit = {
    val conf = MyConfig.getConfig("application.conf")
    println(conf.service("cheese-service"))
  }
}
