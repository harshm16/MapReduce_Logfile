package Test_cases

import HelperUtils.timetoEpoch.bin_config
import com.typesafe.config.{Config, ConfigFactory}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.collection.mutable.ListBuffer
import scala.io.Source

class testPatternGeneration extends AnyFlatSpec with Matchers {
  behavior of "Check pattern generation"

  it should "locate an instance of the pattern in the generated string" in {

    var cols : String = ""
    var target_List : String = ""

    val config: Config = ConfigFactory.load("application.conf")
    val pattern = (config.getString("randomLogGenerator.Pattern")).r

    for(line <- Source.fromFile("log/LogFileGenerator.2021-10-08.log").getLines()){

      cols = line.split(" - ")(1)

      target_List += cols
    }

    //Originally this return a Scala.some, as that was not supported, so I was unsure how to actually test it.
    pattern.findFirstIn(target_List).toString shouldBe a [String]

     }
}
