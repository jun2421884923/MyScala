package com.test
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

//todo:利用spark计算运营商访问url最多的前n位=====TopN
object topN {
  def main(args: Array[String]): Unit = {


      //创建sparkConf对象
       val sparkConf: SparkConf = new SparkConf().setAppName("PV").setMaster("local[2]")
      //创建SparkContext对象
       val sc: SparkContext = new SparkContext(sparkConf)
      //设置输出的日志级别
      sc.setLogLevel("WARN")
      //读取日志数据
       val dataRDD: RDD[String] = sc.textFile("E:\\access.log")
      //对每一行的日志信息进行切分并且过滤清洗掉不符合规则的数据
      //通过对日志信息的分析，我们知道按照空格切分后，下标为10的是url，长度小于10的暂且认为是不符合规则的数据
       val urlAndOne: RDD[(String, Int)] = dataRDD.filter(_.split(" ").size>10).map(x=>(x.split(" ")(10),1))
      //相同url进行累加
       val result: RDD[(String, Int)] = urlAndOne.reduceByKey(_+_)
      //访问最多的url并进行倒叙排序
       val sortResult: RDD[(String, Int)] = result.sortBy(_._2,false)
      //取前五位
       val finalResult: Array[(String, Int)] = sortResult.take(5)
      //打印输出
      finalResult.foreach(println)
      sc.stop()
    
  }
}
