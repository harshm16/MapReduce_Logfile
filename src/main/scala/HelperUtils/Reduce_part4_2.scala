package HelperUtils

import org.apache.hadoop.io.{IntWritable, Text}
import org.apache.hadoop.mapreduce.Reducer

import scala.collection.JavaConverters.*
import scala.collection.mutable
import scala.math.Ordering.Implicits.infixOrderingOps
import scala.util.control.Breaks.break

/** Second Reducer Class for subtask 4.
 * Finds the max value out of all the values for a particular key. */
class Reduce_part4_2 extends Reducer[Text, IntWritable, Text, IntWritable] {
  private val count: IntWritable = new IntWritable(1)
  val logger = CreateLogger(classOf[Reduce_part4_2])

  override def reduce(key: Text, values: java.lang.Iterable[IntWritable], context: Reducer[Text, IntWritable, Text, IntWritable]#Context): Unit = {

    /** Old implementation using a for loop and a var. */
//    var max = 0
//    for (value <- values.asScala) {
//      if (value.get > max) max = value.get
//    }

    /** Convert the values iterable list to an array and use reduceLeft (_ max _)
     * to find the highest element of the array.*/
    val valueArray = values.asScala.map(value => value.get)

    if(!valueArray.isEmpty) {
      val maxi = valueArray.reduceLeft(_ max _)
      logger.info(s"maxi values is ${maxi}")
      context.write(key, new IntWritable(maxi))
    }

    //logger.info(s"Key is ${key}, value is ${max}")
    //context.write(key, new IntWritable(max))
  }
}