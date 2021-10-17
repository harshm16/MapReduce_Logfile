package HelperUtils

import org.apache.hadoop.io
import org.apache.hadoop.io.{IntWritable, Text}
import org.apache.hadoop.mapreduce.Reducer

import scala.collection.JavaConverters.*

class Reduce_part2 extends Reducer[IntWritable, Text, IntWritable, Text] {
  //private val count: Text = new Text()
  override def reduce(key: IntWritable, values: java.lang.Iterable[IntWritable], context: Reducer[IntWritable, Text, IntWritable, Text]#Context): Unit = {

    //count.set(values.asScala.map(m => m.get()).toString())

    val new_value = values.asScala.foldLeft(",")(_ + _).substring(1)
    context.write(key, new Text(new_value))
  }
}
