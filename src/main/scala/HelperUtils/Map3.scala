package HelperUtils

import org.apache.hadoop.io.{IntWritable, LongWritable, Text}
import org.apache.hadoop.mapreduce.Mapper

class Map3 extends Mapper[LongWritable, Text, IntWritable, IntWritable] {

  val logger = CreateLogger(classOf[Map3])

  //flips key values for desending order
  override def map(key: LongWritable, rowLine: Text, context: Mapper[LongWritable, Text, IntWritable, IntWritable]#Context) = {
    val line = rowLine.toString
    if (!line.isEmpty) {

      val Array = line.split("\t")

      //logger.info(s"Key is ${error_type}, value is ${string_length}")
      context.write(new IntWritable(Array(1).toInt), new IntWritable(Array(0).toInt))
    }
  }
}