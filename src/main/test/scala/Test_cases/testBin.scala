package Test_cases

import HelperUtils.timetoEpoch.bin_config
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class testBin extends AnyFlatSpec with Matchers {
  behavior of "Check if bin is an Integer"

  it should "obtains the bin intervals" in {
    bin_config.getInt("user_split.Bins") shouldBe a [Int]
  }
}
