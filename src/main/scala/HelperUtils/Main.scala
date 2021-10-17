package HelperUtils

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.hadoop.io.{IntWritable, Text}
import org.apache.hadoop.mapreduce.Job
import org.apache.hadoop.mapreduce.lib.input.{FileInputFormat, TextInputFormat}
import org.apache.hadoop.mapreduce.lib.output.{FileOutputFormat, TextOutputFormat}

import java.util.StringTokenizer

class Main{}

/**
 * Main program
 * Map Reduce execution starts here
 */
object Main {

  //val logger = CreateLogger(classOf[Main])

  def main(args: Array[String]): Unit = {

    val outPut = new Path(args(1))
    val configuration = new Configuration()
    val fs = FileSystem.get(configuration)
    if (fs.exists(outPut))
      fs.delete(outPut, true)


    /** Separator between key value in output set to comma */
    //configuration.set("mapreduce.output.textoutputformat.separator",";")

    val job1 = Job.getInstance(configuration, "String_frequency")

    /** Setting configurations for the job  */
    job1.setJarByClass(this.getClass)

    job1.setMapperClass(classOf[Map1])
    job1.setReducerClass(classOf[Reduce1])

    job1.setInputFormatClass(classOf[TextInputFormat])
    job1.setOutputFormatClass(classOf[TextOutputFormat[Text, Text]])

    job1.setOutputKeyClass(classOf[Text])
    job1.setOutputValueClass(classOf[IntWritable])
    FileInputFormat.addInputPath(job1, new Path(args(0)))
    FileOutputFormat.setOutputPath(job1, new Path(args(1)))
    job1.waitForCompletion(true)

    val conf2 = new Configuration()
    val job2 = Job.getInstance(conf2, "string_length_sort")

    /** Setting configurations for the job  */
    job2.setJarByClass(this.getClass)

    job2.setMapperClass(classOf[Map2])
    job2.setReducerClass(classOf[Reduce1])

    job2.setInputFormatClass(classOf[TextInputFormat])
    job2.setOutputFormatClass(classOf[TextOutputFormat[Text, Text]])

    job2.setOutputKeyClass(classOf[Text])
    job2.setOutputValueClass(classOf[IntWritable])
    FileInputFormat.addInputPath(job2, new Path(args(1)))
    FileOutputFormat.setOutputPath(job2, new Path(args(2)))
    job2.waitForCompletion(true)


    val conf3 = new Configuration()
    val job3 = Job.getInstance(conf3, "swap_keys")

    /** Setting configurations for the job  */
    job3.setJarByClass(this.getClass)

    job3.setMapperClass(classOf[Map3])
    job3.setReducerClass(classOf[Reduce2])

    job3.setInputFormatClass(classOf[TextInputFormat])
    job3.setOutputFormatClass(classOf[TextOutputFormat[IntWritable, IntWritable]])
    job3.setSortComparatorClass(classOf[Custom_comparator])

    job3.setOutputKeyClass(classOf[IntWritable])
    job3.setOutputValueClass(classOf[IntWritable])
    FileInputFormat.addInputPath(job3, new Path(args(2)))
    FileOutputFormat.setOutputPath(job3, new Path(args(3)))

    System.exit(if(job3.waitForCompletion(true))  0 else 1)

  }

}
