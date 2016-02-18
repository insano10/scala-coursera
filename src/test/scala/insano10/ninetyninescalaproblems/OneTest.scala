package insano10.ninetyninescalaproblems

import org.scalatest.FunSuite

class OneTest extends FunSuite {

  test("should find the last element in a list") {

    assertResult(5) {
      One.last(List(1, 2, 3, 4, 5))
    }
  }

  test("should throw IllegalArgumentException when calling last on an empty list") {

    val expectedException = intercept[IllegalArgumentException] {
      One.last(List())
    }

    assert(expectedException.getMessage == "Nil.last")
  }
}
