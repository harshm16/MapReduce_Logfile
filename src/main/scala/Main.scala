import HelperUtils.{CreateLogger, Main_part1, Main_part2, Main_part3, Main_part4}
import com.typesafe.config.ConfigFactory
import org.slf4j.LoggerFactory


class Main{}

object Main:
  val logger = CreateLogger(classOf[Main])

  def main(args: Array[String]): Unit = {
    logger.info("Starting Map reduce tasks.")

    if (args.length == 3){
      if (args(2) == "1"){
        logger.info("Starting Part1.")
        Main_part1.main(args)
      }
      else if (args(2) == "3"){
        logger.info("Starting Part3.")
        Main_part3.main(args)
      }
      else{
        logger.error("You passed the wrong arguments. Please go through the READ ME and try again.")
        sys.error("You passed the wrong arguments. Please go through the READ ME and try again.")
      }
    }
    else if(args.length == 4){
      if (args(3) == "4"){
        logger.info("Starting Part 4.")
        Main_part4.main(args)
      }
      else{
        logger.error("You passed the wrong arguments. Please go through the READ ME and try again.")
        sys.error("You passed the wrong arguments. Please go through the READ ME and try again.")
      }
    }
    else if(args.length == 5){
      if (args(4) == "2"){
        logger.info("Starting Part 2.")
        Main_part2.main(args)
      }
      else{
        logger.error("You passed the wrong arguments. Please go through the READ ME and try again.")
        sys.error("You passed the wrong arguments. Please go through the READ ME and try again.")
      }
    }
    else {
      logger.error("You passed the wrong arguments. Please go through the READ ME and try again.")
      sys.error("You passed the wrong arguments. Please go through the READ ME and try again.")
    }


    logger.info("Finished running Map reduce tasks")
  }