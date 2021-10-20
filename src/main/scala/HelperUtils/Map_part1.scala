package HelperUtils

import com.typesafe.config.{Config, ConfigFactory}
import org.apache.hadoop.io.{IntWritable, LongWritable, Text}
import org.apache.hadoop.mapreduce.Mapper
import org.joda.time.format.DateTimeFormat

/** Mapper Class for subtask 1.
 * (key,value) :: ((binned_time_step,log_type,matched_string),1) */
class Map_part1 extends Mapper[LongWritable, Text, Text, IntWritable] {
  private val frequency: IntWritable = new IntWritable (1)
  private val key_map: Text = new Text
  val logger = CreateLogger(classOf[Map_part1])

  //Read the pattern from the Application conf file.
  val config: Config = ConfigFactory.load("application.conf")
  val pattern = (config.getString("randomLogGenerator.Pattern")).r

  //Read the number of bins from the user conf file.
  val bin_config: Config = ConfigFactory.load("User_Split.conf")
  val bin = bin_config.getInt("user_split.Bins")
  //val pattern = "([a-c][e-g][0-3]|[A-Z][5-9][f-w]){5,15}".r

  override def map(key: LongWritable, rowLine: Text, context: Mapper[LongWritable, Text, Text, IntWritable]#Context) = {
    val line = rowLine.toString
    if (!line.isEmpty) {

      //log string
      val random_string: String = line.split(" - ")(1)

      //error type
      val error: Array[String] = line.split("] ")
      val error_string = error(1).split(" ")(0)

      //timestamp
      val time_stamp: String = line.split(" ")(0)
      val bin_time_step = (DateTimeFormat.forPattern("HH:mm:ss.SSS").parseDateTime(time_stamp).getMillis() / 1000) / bin % bin

      //check if log contains regex:
      val matches = pattern.findAllIn(random_string).toList

      //in case there are multiple matches concatenate all the matches into a single string.
      if (matches.nonEmpty) {

        val key = bin_time_step + "," + error_string + "," + matches.mkString("+")
        key_map.set(key)
        logger.info(s"Key is ${key_map}, value is ${frequency}")
        context.write(key_map, frequency)
      }
    }
  }
}