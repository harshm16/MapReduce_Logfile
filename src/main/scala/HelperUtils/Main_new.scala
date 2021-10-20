package HelperUtils

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.hadoop.io.{IntWritable, Text}
import org.apache.hadoop.mapreduce.Job
import org.apache.hadoop.mapreduce.lib.input.{FileInputFormat, TextInputFormat}
import org.apache.hadoop.mapreduce.lib.output.{FileOutputFormat, TextOutputFormat}

import java.util.StringTokenizer

class Main_new{}

/**
 * Main program to test the flow of a Map reduce job. Not used in solving any of the 4 subtasks in hand.


 * Map Reduce execution starts here
 */
object Main_new {

  //val logger = CreateLogger(classOf[Main])

  def main(args: Array[String]): Unit = {

    val outPut = new Path(args(1))
    val configuration = new Configuration()
    val fs = FileSystem.get(configuration)
    if (fs.exists(outPut))
      fs.delete(outPut, true)


    /** Separator between key value in output set to comma */
    //configuration.set("mapreduce.output.textoutputformat.separator",";")

    val job = Job.getInstance(configuration, "log type count")

    /** Setting configurations for the job  */
    job.setJarByClass(this.getClass)
    //
    //    job.setMapperClass(classOf[Map_new])
    //    job.setReducerClass(classOf[Reduce_new])

    job.setMapperClass(classOf[Map_part4])
    job.setReducerClass(classOf[Reduce_part4])

    job.setInputFormatClass(classOf[TextInputFormat])
    job.setOutputFormatClass(classOf[TextOutputFormat[Text, Text]])

    job.setOutputKeyClass(classOf[Text])
    job.setOutputValueClass(classOf[IntWritable])
    FileInputFormat.addInputPath(job, new Path(args(0)))
    FileOutputFormat.setOutputPath(job, new Path(args(1)))
    System.exit(if(job.waitForCompletion(true))  0 else 1)

  }

}
