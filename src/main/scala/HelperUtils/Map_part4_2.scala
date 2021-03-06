package HelperUtils

import org.apache.hadoop.io.{IntWritable, LongWritable, Text}
import org.apache.hadoop.mapreduce.Mapper

/** Second Mapper Class for subtask 4.
 * (key,value) :: (log_type,length_of_matched_string) */
class Map_part4_2 extends Mapper[LongWritable, Text, Text, IntWritable] {

  val logger = CreateLogger(classOf[Map_part4_2])


  override def map(key: LongWritable, rowLine: Text, context: Mapper[LongWritable, Text, Text, IntWritable]#Context) = {
    val line = rowLine.toString
    if (!line.isEmpty) {

      val map1_key: Array[String] = line.split(",")

      val Array(error_type, string_length) = map1_key(0).split(":")

      logger.info(s"Key is ${error_type}, value is ${string_length}")
      context.write(new Text(error_type), new IntWritable(string_length.toInt))
    }
  }
}