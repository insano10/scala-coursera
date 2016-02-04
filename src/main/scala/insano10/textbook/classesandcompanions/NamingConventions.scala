package insano10.textbook.classesandcompanions

class NamingConventions(val x: Int, val y: Int) {

  //for private fields, prepend with an underscore so we can create methods with the real name
  private var _z: Int = 0

  //or just have a public field
  //you can easily make this private later by converting it to the style like 'z'
  var z2: Int = 0

  //getters should have the name of the field
  def z: Int = _z

  //setters should have the name of the field with _= appended which scala will convert into regular x = y syntax
  //by replacing the underscore with a space
  def z_=(i: Int): Unit = _z = i

}

object NamingConventions {

  //for constants, only the first letter needs to be capitalised
  val Magic = 123
}

object Main {

  def main(args: Array[String]): Unit = {

    println(NamingConventions.Magic)

    val nc = new NamingConventions(1,2)

    //private accessing through methods
    println(nc.z)
    nc.z = 5
    println(nc.z)

    //public accessing directly
    println(nc.z2)
    nc.z2 = 6
    println(nc.z2)
  }
}