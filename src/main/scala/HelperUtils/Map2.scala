package HelperUtils

import org.apache.hadoop.io.{IntWritable, LongWritable, Text}
import org.apache.hadoop.mapreduce.Mapper

class Map2 extends Mapper[LongWritable, Text, Text, IntWritable] {

  val logger = CreateLogger(classOf[Map2])


  override def map(key: LongWritable, rowLine: Text, context: Mapper[LongWritable, Text, Text, IntWritable]#Context) = {
    val line = rowLine.toString
    if (!line.isEmpty) {

      val map1_key: Array[String] = line.split("\t")

      val Array = map1_key(0).split(":")

      val bin = Array(0)
      val error_type = Array(1)

      if (error_type == "ERROR"){
        context.write(new Text(bin.toString), new IntWritable(1))
      }
    }
  }
}