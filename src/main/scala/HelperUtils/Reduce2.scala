package HelperUtils

import org.apache.hadoop.io.{IntWritable, Text}
import org.apache.hadoop.mapreduce.Reducer

import scala.collection.JavaConverters._

class Reduce2 extends Reducer[IntWritable, IntWritable, IntWritable, IntWritable] {
  private val count: IntWritable = new IntWritable()

  override def reduce(key: IntWritable, values: java.lang.Iterable[IntWritable], context: Reducer[IntWritable, IntWritable, IntWritable, IntWritable]#Context): Unit = {

    count.set(values.asScala.map(m => m.get()).sum)
    context.write(key, count)
  }
}
