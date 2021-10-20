package HelperUtils
import com.typesafe.config.{Config, ConfigFactory}

import scala.io.Source
import org.apache.hadoop.io.{IntWritable, LongWritable, Text}

import scala.collection.mutable.ListBuffer
import scala.io.Source

object regexMatch {

  var cols : String = ""
  var target_List = new ListBuffer[String]()
  //val pattern = "([a-c][e-g][0-3]|[A-Z][5-9][f-w]){5,15}".r

  val config: Config = ConfigFactory.load("application.conf")
  val pattern = (config.getString("randomLogGenerator.Pattern")).r

  for(line <- Source.fromFile("C:/Users/Harsh Mishra/Desktop/UIC/Sem1/CC/Assignment2/Git repo/MapReduce_LogFile/log/LogFileGenerator.2021-10-12.log").getLines()){
    cols = line.split(" - ")(1)
    //var cols : Array[String] = line.split(" ")
    //println(s"${cols}")
    target_List += cols

  }

  //val string_log = "asdasd"
  println(target_List)

  //val matches = pattern.findAllIn(string_log).toList

  //println(matches)

  //val string = "[h!Q9PEY>L(NpKLBO\"Gjo:=4kRXQ_tZ!"
  //pattern.findAllMatchIn()
  //println(mapped_List.reduce(__))

}