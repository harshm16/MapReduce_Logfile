import HelperUtils.{CreateLogger, regexMatch, timetoEpoch}
import com.typesafe.config.{Config, ConfigFactory}

object Try1 {

  @main def runTry1 =

    val convertTimetoEpoch = timetoEpoch
    convertTimetoEpoch

//    val regexFind = regexMatch
//    regexFind

//    val config: Config = ConfigFactory.load("application.conf")
//    val pattern = config.getString("randomLogGenerator.Pattern")
//    println(pattern)
//
//    val bin_config: Config = ConfigFactory.load("User_Split.conf")
//    val bin = bin_config.getInt("user_split.Bins")
//    println(bin)
}