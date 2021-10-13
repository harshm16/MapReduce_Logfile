package HelperUtils

import org.apache.hadoop.io.{IntWritable, LongWritable, Text}
import org.apache.hadoop.mapreduce.Mapper

class Map3 extends Mapper[LongWritable, Text, Text, IntWritable] {

  val logger = CreateLogger(classOf[Map2])

  //flips key values for desending order
  override def map(key: LongWritable, rowLine: Text, context: Mapper[LongWritable, Text, IntWritable, IntWritable]#Context) = {
    val line = rowLine.toString
    if (!line.isEmpty) {

      val Array(bin, count) = line.split("\t")

      //logger.info(s"Key is ${error_type}, value is ${string_length}")
      context.write(new IntWritable(count.toInt), new IntWritable(bin.toInt))
    }
  }
}