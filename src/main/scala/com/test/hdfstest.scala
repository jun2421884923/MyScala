package com.test

import org.apache.spark.{SparkConf, SparkContext}

object hdfstest {
  def main(args: Array[String]): Unit = {
    val config = new SparkConf().setAppName("test").setMaster("local")
    val sc = new SparkContext(config)

    var hv=sc.textFile("hdfs://192.168.15.30:8020/user/hive/warehouse/ycapp.db/appindex")
    var hivedata=hv.map(_.split("\t")).map(e => ( e(1), e(2),e(0).toInt))
//    (String, String, String) = (9,2017-07-26,all_posts)
    //访问第一行的第一列元素
      hivedata.first()._1
    //把三列数据中的第一列筛选出来
    var hivedata1=hivedata.map(res=>res._1)
    //第一列数据的排重数量
      hivedata1.distinct().count()
    //把第一列数据转化成键值对
    var hivedata1_map= hivedata1.map(res=>(res,1))
    //统计第一列数据的频数
    var hivedata1_mapv=hivedata1_map.reduceByKey((x,y)=>x+y)
    //取出数据中的第一列和第三列
    var hivedata2=hivedata.map(res=>(res._1,res._3))
    //统计第三列数据在第一列分组中的求和
    var hivedata2_mv =hivedata2.reduceByKey((x,y)=>x+y)
    //统计多个key的groupby操作
    var hivedata3=hivedata.map(res=>(res._1+res._2,res._3))
    var hivedata3_mv=hivedata3.reduceByKey((x,y)=>x+y)
    var hivedata3_mv2=hivedata3_mv.map(res=>(res._1.substring(0,10),res._1.substring(10),res._2))
  }
 
}
