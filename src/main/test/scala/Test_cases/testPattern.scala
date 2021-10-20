package Test_cases

import HelperUtils.regexMatch.config
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class testPattern extends AnyFlatSpec with Matchers {
  behavior of "Check if pattern is a String"

  it should "obtains the bin intervals" in {
    config.getString("randomLogGenerator.Pattern") shouldBe a [String]
  }
}
