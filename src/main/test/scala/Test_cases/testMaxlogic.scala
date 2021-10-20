package Test_cases

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class testMaxlogic extends AnyFlatSpec with Matchers {
  behavior of "Check if max Logic works"

  it should "obtains the bin intervals" in {

    val test_Array = Array(1, 2, 3, 4)
    val maxi = test_Array.reduceLeft(_ max _)

    maxi shouldBe 4
  }
}