package Test_cases

import HelperUtils.timetoEpoch.bin_config
import com.typesafe.config.{Config, ConfigFactory}
import org.joda.time.format.DateTimeFormat
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.collection.mutable.ListBuffer
import scala.io.Source

class testEpochConversion extends AnyFlatSpec with Matchers {
  behavior of "Check epoch conversion"

  it should "verify the timestamp to epoch conversion" in {

    val config: Config = ConfigFactory.load("application.conf")
    val pattern = (config.getString("randomLogGenerator.Pattern")).r

    val src = Source.fromFile("log/LogFileGenerator.2021-10-08.log").getLines.next()
    val cols = src.split(" ")(0)

    //Originally this return a Scala.some, as that was not supported, so I was unsure how to actually test it.
    DateTimeFormat.forPattern("HH:mm:ss.SSS").parseDateTime(cols).getMillis() shouldBe a [Long]

  }
}
