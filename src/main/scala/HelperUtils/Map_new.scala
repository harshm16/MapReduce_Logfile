package HelperUtils

import org.apache.hadoop.io.{IntWritable, LongWritable, Text}
import org.apache.hadoop.mapreduce.Mapper

class Map_new extends Mapper[LongWritable, Text, Text, IntWritable] {
  private val one: IntWritable = new IntWritable(1)
  private val time: Text = new Text
  private val error_type: Text = new Text
  private val error_log: Text = new Text
  private val key_text: Text = new Text


  override def map(key: LongWritable, rowLine: Text, context: Mapper[LongWritable, Text, Text, IntWritable]#Context) = {
    val line = rowLine.toString
    //val time = "([0-9]+.:.[0-9]+.[0-9]+.\\..[0-9]+)".r
    if (!line.isEmpty) {
      //val tokens: Array[String] = line.split("\\]\\s(.*?)\\s")
      val tokens: Array[String] = line.split(" ")

//      tokens.foreach(f => {
//        if (f == "ERROR" | f == "WARN" | f == "INFO" | f == "DEBUG") {
//          time.set(f)
//          context.write(time, one)
//        }
//      })

      time.set(tokens(0))
      //context.write(word, one)

      val random_string: Array[String] = line.split(" - ")
      error_log.set(random_string(1))
      //context.write(error_log, one)

      val error: Array[String] = line.split("] ")
      val error_string = error(1).split(" ")(0)
      error_type.set(error_string)

      val key = error_string + ":" + tokens(0) + ":" + random_string(1)
      key_text.set(key)
      context.write(key_text, one)


//        tokens.foreach(f => {
//          val time(time_string) = f
//
//        })
    }
  }
}