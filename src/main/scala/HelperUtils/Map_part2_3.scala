package HelperUtils

import org.apache.hadoop.io.{IntWritable, LongWritable, Text}
import org.apache.hadoop.mapreduce.Mapper

/** Third Mapper Class for subtask 2.
 * Swaps the key-value pairs received from the Second reducer.
 * (key,value) :: (number_of_matched_instances,binned_time_step) */
class Map_part2_3 extends Mapper[LongWritable, Text, IntWritable, Text] {

  val logger = CreateLogger(classOf[Map_part2_3])

  //flips key values for desending order
  override def map(key: LongWritable, rowLine: Text, context: Mapper[LongWritable, Text, IntWritable, Text]#Context) = {
    val line = rowLine.toString
    if (!line.isEmpty) {

      val Array = line.split(",")

      //logger.info(s"Key is ${error_type}, value is ${string_length}")
      context.write(new IntWritable(Array(1).toInt), new Text(Array(0)))
    }
  }
}