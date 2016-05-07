package insano10.random.externalprocesses

trait MockOutput extends Output {

  var messages: Seq[String] = Seq()

  override def print(x: Any) = {
    messages = messages :+ x.toString
  }
}
