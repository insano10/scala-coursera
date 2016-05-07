package insano10.textbook.classesandcompanions

//the class
class ChecksumAccumulator {

  private var sum = 0

  def add(b: Byte) = sum += b

  def checksum(): Int = ~(sum & 0xFF) + 1
}

//the companion object
//it must have the same name and be in the same file
//the class and companion object can access eachother's private fields
object ChecksumAccumulator {

  def calculate(s: String): Int = {

    val acc = new ChecksumAccumulator

    s.foreach(c => acc.add(c.toByte))
    acc.checksum()
  }

}

object ChecksumAccumulatorMain {

  def main(args: Array[String]): Unit = {

    println(ChecksumAccumulator.calculate("hello world"))
  }
}
