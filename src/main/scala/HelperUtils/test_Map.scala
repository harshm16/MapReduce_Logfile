package HelperUtils

import org.apache.hadoop.io.{LongWritable, Text}
import org.apache.hadoop.mapreduce.Mapper

import scala.collection.mutable.ListBuffer

/** Mapper class used for initial testing, to see how a map reduce job works.
 * Not used in any of the subtasks in hand.*/
class test_Map extends Mapper[LongWritable, Text, Text, Int] {
  /**
   * maps from input key-value to output key-value format
   * @param key
   * @param value
   * @param context
   */
  override def map(key: LongWritable, value: Text, context:
  Mapper[LongWritable, Text, Text, Int]#Context): Unit = {

    //logger.debug("Mapper input - " + value.toString)
    //var cols : String = ""
    var target_List = new ListBuffer[String]()
    var line : String = value.toString
    //var current_Word : Text = new Text()

    //var cols = line.split(" ")
    //cols = value.toString
    var cols : Array[String] = line.split(" ")

    cols(2).foreach(error_type => {
      context.write(new Text(error_type.toString()), 1)
    })

    //println(s"value in mapper: ${cols}")
    //target_List += cols


    //println(target_List)
    //val mapped_List = {target_List}.map(word => (word,1))

  }

}

object test_Map {


}

