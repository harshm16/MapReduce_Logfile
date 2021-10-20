package HelperUtils

import org.apache.hadoop.io.Text
import org.apache.hadoop.mapreduce.Reducer
import scala.collection.JavaConverters.*
import java.lang

/** Reducer class used for initial testing, to see how a map reduce job works.
 * Not used in any of the subtasks in hand.*/
class test_Reduce extends Reducer[Text, Text, Text, Text]{

  //val logger = CreateLogger(classOf[Reduce])
  /**
   * Input Key values => A-B, A-C converted to output key values => A-B,C
   * @param key
   * @param values
   * @param context
   */
  override def reduce(key: Text, values: lang.Iterable[Text],
                      context: Reducer[Text, Text, Text, Text]#Context): Unit = {

    //logger.debug("Reducer input - " + key.toString)
    println(s"value in reduce : ${values.toString}")

    val error_Count = values.asScala.foldLeft("")(_ + _).substring(1)
    context.write(key, new Text(error_Count))

  }

}

