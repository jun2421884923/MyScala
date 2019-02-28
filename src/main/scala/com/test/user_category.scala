package com.test

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * var可以多次赋值，val只能赋值一次
  * 改写select  a,sum(b),sum(c)
  */
object user_category {
  def start(input_path:String,output_path:String): Unit ={
    println(input_path)
    val conf = new SparkConf().setAppName("user_category").setMaster("local[1]")
    val sc = new SparkContext(conf)
    val inputFile = sc.textFile(input_path).filter(_.length > 0).map{line =>
      line.split("\t")}
    println(inputFile.count())
//    inputFile.take(5).foreach(println)
    // 根据数据数据类型,转化为(key, (value1, value2))形式的键值对
    println(inputFile.getClass.getSimpleName)
    var maped:RDD[(String,(Int,Int))]=inputFile.map(x=> {
      if (x.length==3){
      val key = x(0).toString
      val v1 = x(1).toString.toInt
      val v2 = x(2).toString.toInt
        (key, (v1, v2))
      }
      else {
        print("x===")
        x.foreach(println)
        ("", (0, 0))
      }

    }
    )
    // 根据key进行合并, value1与value1合并, value2与value2合并
    val reduced:RDD[(String,(Int,Int))]=maped.reduceByKey((lastvalue,thisvalue)
      =>{
      (lastvalue._1+thisvalue._1,lastvalue._2+thisvalue._2)
    }
    )
//    val result:RDD[List[Any]]=reduced.map(x =>List(x._1,x._2._1,x._2._2))
    val result:RDD[String]=reduced.map(x =>x._1+","+x._2._1.toString+","+x._2._2.toString)
//    result.foreach(println)
    result.saveAsTextFile(output_path)


  }
  def main(args: Array[String]): Unit = {
    var input_path="D:\\data\\click_show"
    var output_path="D:\\data\\click_show"
    if (args.length==2){
       input_path=args(0)
       output_path=args(1)
    }

    input_path=input_path+"\\*data"
    start(input_path,output_path)
  }
}
