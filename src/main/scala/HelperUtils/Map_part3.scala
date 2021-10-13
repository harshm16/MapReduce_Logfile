package HelperUtils

import org.apache.hadoop.io.{IntWritable, LongWritable, Text}
import org.apache.hadoop.mapreduce.Mapper

class Map_part3 extends Mapper[LongWritable, Text, Text, IntWritable] {
  private val frequency: IntWritable = new IntWritable (1)
  private val key_map: Text = new Text
  val logger = CreateLogger(classOf[Map_part3])


  override def map(key: LongWritable, rowLine: Text, context: Mapper[LongWritable, Text, Text, IntWritable]#Context) = {
    val line = rowLine.toString
    if (!line.isEmpty) {

      val random_string: Array[String] = line.split(" - ")

      val error: Array[String] = line.split("] ")
      val error_string = error(1).split(" ")(0)

      val key = error_string + ":" + random_string(1).length()
      key_map.set(key)
      logger.info(s"Key is ${key_map}, value is ${frequency}")
      context.write(key_map, frequency)
    }
  }
}