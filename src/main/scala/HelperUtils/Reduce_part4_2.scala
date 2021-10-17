package HelperUtils

import org.apache.hadoop.io.{IntWritable, Text}
import org.apache.hadoop.mapreduce.Reducer

import scala.collection.JavaConverters.*
import scala.collection.mutable
import scala.util.control.Breaks.break

class Reduce_part4_2 extends Reducer[Text, IntWritable, Text, IntWritable] {
  private val count: IntWritable = new IntWritable(1)
  val logger = CreateLogger(classOf[Reduce_part4_2])

  override def reduce(key: Text, values: java.lang.Iterable[IntWritable], context: Reducer[Text, IntWritable, Text, IntWritable]#Context): Unit = {


    var max = 0
    for (value <- values.asScala) {
      if (value.get > max) max = value.get
    }
    logger.info(s"Key is ${key}, value is ${max}")
    context.write(key, new IntWritable(max))
  }
}