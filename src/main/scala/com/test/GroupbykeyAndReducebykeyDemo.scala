package com.test

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * import org.apache.log4j.{ Level, Logger }

Logger.getLogger("org").setLevel(Level.WARN)

Logger.getLogger("org.apache.spark").setLevel(Level.WARN)

Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.WARN)

spark.sparkContext.setLogLevel("WARN")


  */
object GroupbykeyAndReducebykeyDemo {
  def main(args: Array[String]): Unit = {
    Logger.getLogger("test").setLevel(Level.WARN)
    val config = new SparkConf().setAppName("GroupyKeyAndReduceByKeyDemo").setMaster("local")
    val sc = new SparkContext(config)
    val arr = Array("val config", "val arr val val arr")
    val socketDS = sc.parallelize(arr).flatMap(_.split(" ")).map((_, 1))
    socketDS.foreach(x =>{print(x)})
    //groupByKey 和reduceByKey 的区别：
    //他们都是要经过shuffle的，groupByKey在方法shuffle之间不会合并原样进行shuffle，
    //reduceByKey进行shuffle之前会先做合并,这样就减少了shuffle的io传送，所以效率高一点
    socketDS.groupByKey().map(tuple => (tuple._1, tuple._2.sum)).foreach(x => {
      println(x._1 + " " + x._2)
    })
    println("----------------------")
    socketDS.reduceByKey(_+_+1).foreach(x => {
      println(x._1 + " " + x._2)
    })
    sc.stop()
  }

}
