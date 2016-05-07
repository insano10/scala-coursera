package insano10.ninetyninescalaproblems

import java.util.NoSuchElementException

import org.scalatest.FunSuite

class ThreeTest extends FunSuite {

  ignore("an example of how to ignore a test"){
    assert(false)
  }

  test("an example of a pending test")(pending)

  test("should return the 0th element of a list") {

    assertResult(1) {
      Three.kthElement(0, List(1, 2, 3))
    }
  }

  test("should return the last element of a list") {

    assertResult(3) {
      Three.kthElement(2, List(1, 2, 3))
    }
  }

  test("should return an element of a list") {

    assertResult(4) {
      Three.kthElement(3, List(1, 2, 3, 4, 5))
    }
  }

  test("should throw NoSuchElementException when kthElement is given an empty list") {

    intercept[NoSuchElementException] {
      Three.kthElement(0, List())
    }
  }

  test("should throw NoSuchElementException when kthElement is given an index outside the list") {

    intercept[NoSuchElementException] {
      Three.kthElement(2, List("a", "b"))
    }
  }

}
