package HelperUtils

import org.apache.hadoop.io.{IntWritable, Text}
import org.apache.hadoop.mapreduce.Reducer

import scala.collection.JavaConverters.*

class Reduce_part3 extends Reducer[Text, IntWritable, Text, IntWritable] {
  private val count: IntWritable = new IntWritable()

  override def reduce(key: Text, values: java.lang.Iterable[IntWritable], context: Reducer[Text, IntWritable, Text, IntWritable]#Context): Unit = {

    count.set(values.asScala.map(m => m.get()).sum)
    context.write(key, count)
  }
}