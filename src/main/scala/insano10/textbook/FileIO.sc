import scala.io.Source

for (line <- Source.fromFile(".bashrc").getLines())
  println(line)

