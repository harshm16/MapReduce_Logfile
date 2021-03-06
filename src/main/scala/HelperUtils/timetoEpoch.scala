package HelperUtils
import com.typesafe.config.{Config, ConfigFactory}

import scala.io.Source
import org.apache.hadoop.io.{IntWritable, LongWritable, Text}
import org.apache.hadoop.mapreduce.Mapper

import scala.collection.mutable.ListBuffer
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.ISODateTimeFormat

/** Used for initial testing, to see how the given timestamp can be converted to epoch time. 
 * Not used in any of the subtasks in hand.*/
object timetoEpoch {

  var cols : String = ""
  var target_List = new ListBuffer[String]()

  val bin_config: Config = ConfigFactory.load("User_Split.conf")
  val bin = bin_config.getInt("user_split.Bins")

  //for(line <- Source.fromFile("C:/Users/Harsh Mishra/Desktop/UIC/Sem1/CC/Assignment2/Git repo/MapReduce_LogFile//log/LogFileGenerator.2021-10-03.log").getLines()){
  for(line <- Source.fromFile("log/LogFileGenerator.2021-10-08.log").getLines()){
    cols = line.split(" ")(0)
    //var cols : Array[String] = line.split(" ")
    //println(s"${cols}")
    target_List += cols
  }

  //println(target_List)
  val mapped_List = {target_List}.map(word => (DateTimeFormat.forPattern("HH:mm:ss.SSS").parseDateTime(word).getMillis()/1000)/bin%bin)
  
  println(mapped_List)

  //println(mapped_List.reduce(__))

}