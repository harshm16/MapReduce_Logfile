package HelperUtils

import org.apache.hadoop.io.{IntWritable, Text}
import org.apache.hadoop.mapreduce.Reducer

import scala.collection.JavaConverters._

/** Reducer Class for subtask 1.
 * Also used as the reducer class for subtask 2's first two mappers.
 * Sums up all the values for a particular key. */
class Reduce_part1 extends Reducer[Text, IntWritable, Text, IntWritable] {
  private val count: IntWritable = new IntWritable()

  override def reduce(key: Text, values: java.lang.Iterable[IntWritable], context: Reducer[Text, IntWritable, Text, IntWritable]#Context): Unit = {

    count.set(values.asScala.map(m => m.get()).sum)
    context.write(key, count)
  }
}