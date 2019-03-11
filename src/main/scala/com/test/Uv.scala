package com.test

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

//todo:利用spark统计运营商uv总量
object Uv {
  def main(args: Array[String]): Unit = {
    //创建sparkConf对象
     val sparkConf=new SparkConf().setAppName("PV").setMaster("local[2]")
    //创建SparkContext对象
     val sc: SparkContext = new SparkContext(sparkConf)
    //设置输出的日志级别
    sc.setLogLevel("WARN")
    //读取日志数据
     val dataRDD: RDD[String] = sc.textFile("E:\\access.log")
    //切分每一行，获取对应的ip地址
     val ips: RDD[String] = dataRDD.map(_.split(" ")(0))
    //去重
     val ipNum: Long = ips.distinct().count()
    println(ipNum)
    //g关闭资源
    sc.stop()
  }

}
