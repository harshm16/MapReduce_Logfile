package HelperUtils

import com.typesafe.config.{Config, ConfigFactory}
import org.apache.hadoop.io.{IntWritable, LongWritable, Text}
import org.apache.hadoop.mapreduce.Mapper

/** First Mapper Class for subtask 4.
 * (key,value) :: ((log_type:length_of_matched_string),1) */
class Map_part4 extends Mapper[LongWritable, Text, Text, IntWritable] {
  private val frequency: IntWritable = new IntWritable (1)
  private val key_map: Text = new Text
  
  val logger = CreateLogger(classOf[Map_part4])
  //  val pattern = "([a-c][e-g][0-3]|[A-Z][5-9][f-w]){5,15}".r

  //Read the pattern from the Application conf file.
  val config: Config = ConfigFactory.load("application.conf")
  val pattern = (config.getString("randomLogGenerator.Pattern")).r

  override def map(key: LongWritable, rowLine: Text, context: Mapper[LongWritable, Text, Text, IntWritable]#Context) = {
    val line = rowLine.toString
    if (!line.isEmpty) {

      val random_string: String = line.split(" - ")(1)

      val error: Array[String] = line.split("] ")
      val error_string = error(1).split(" ")(0)

      val matches = pattern.findAllIn(random_string).toList

      //in case there are multiple matches
      if (matches.nonEmpty) {

        val key = error_string + ":" + matches.mkString("").length()
        key_map.set(key)
        logger.info(s"Key is ${key_map}, value is ${frequency}")
        context.write(key_map, frequency)
      }
    }
  }
}