package insano10.ninetyninescalaproblems

import org.scalatest.FunSuite

class TwoTest extends FunSuite {

  test("should return the last but one element of a list") {

    assertResult(4) {
      Two.lastButOne(List(1, 2, 3, 4, 5))
    }

    assertResult(1) {
      Two.lastButOne(List(1, 2))
    }
  }

  test("should throw IllegalArgumentException is list has less than 2 elements"){

    val expectedException = intercept[IllegalArgumentException]{
      Two.lastButOne(List("a"))
    }

    assert(expectedException.getMessage == "List.length < 2")
  }
}
