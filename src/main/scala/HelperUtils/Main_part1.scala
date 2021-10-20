package HelperUtils

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.hadoop.io.{IntWritable, Text}
import org.apache.hadoop.mapreduce.Job
import org.apache.hadoop.mapreduce.lib.input.{FileInputFormat, TextInputFormat}
import org.apache.hadoop.mapreduce.lib.output.{FileOutputFormat, TextOutputFormat}

import java.util.StringTokenizer

class Main_part1{}

/**
 * Main program to run the map reduce jobs to solve Subtask 1.
 * Map Reduce execution starts here
 */
object Main_part1 {

  //val logger = CreateLogger(classOf[Main])

  def main(args: Array[String]): Unit = {

   
    val configuration = new Configuration()
      
    /** While running on a Hadoop Sandbox VM, 
     * you can uncomment the following to delete an already existing output directory. */
      
//    val outPut = new Path(args(1))

//    val fs = FileSystem.get(configuration)
//    if (fs.exists(outPut))
//      fs.delete(outPut, true)


    /** Separator between key value in output set to comma */
    configuration.set("mapreduce.output.textoutputformat.separator",",")

    val job = Job.getInstance(configuration, "log type count")

    /** Setting configurations for the job  */
    job.setJarByClass(this.getClass)

    job.setMapperClass(classOf[Map_part1])
    job.setReducerClass(classOf[Reduce_part1])

    job.setInputFormatClass(classOf[TextInputFormat])
    job.setOutputFormatClass(classOf[TextOutputFormat[Text, Text]])

    job.setOutputKeyClass(classOf[Text])
    job.setOutputValueClass(classOf[IntWritable])
    FileInputFormat.addInputPath(job, new Path(args(0)))
    FileOutputFormat.setOutputPath(job, new Path(args(1)))
    System.exit(if(job.waitForCompletion(true))  0 else 1)

  }

}
