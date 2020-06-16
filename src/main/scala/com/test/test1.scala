package com.test

import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.hive.HiveContext;
object test1 {
    val conf = new SparkConf().setAppName("WordCount").setMaster("local");
    val sc = new SparkContext(conf)
    def test(): Unit ={
        val logFile ="D:\\data\\a.txt";


        val logData = sc.textFile(logFile).cache()
        val numAs = logData.filter(line => line.contains("p")).count()
        val numBs = logData.filter(line => line.contains("h")).count()
        println("Lines with a: %s, Lines with b: %s".format(numAs, numBs))
        print("---------------");
        val lines =  sc.textFile(logFile)
        val pairs = lines.map(s=>(s,1))
        pairs.reduceByKey((x, y)=>x+y).foreach(println)
    }
    def t1(): Unit ={
        var num=sc.parallelize(List(1,2,3,4))
        //persist持久化
//        num.persist(StorageLevel.MEMORY_AND_DISK_SER)
//        num.map(x =>x*x).collect().mkString(",").foreach(x =>print(x))
//        num.unpersist()
        var tup=sc.parallelize(List((1,2),(3,4),(3,6)))
//        var r=tup.reduceByKey((x,y) =>x+y).collect().foreach(x => print(x))
//        var res=tup.flatMapValues(x =>(x to 5))
        val c = tup.groupByKey().foreach(x =>print(x))
    }
    def test_hive_sparlsql(): Unit ={
        var hivectx=new HiveContext(sc)
        val rows=hivectx.sql("selct 1,2")
        var firstrow=rows.first()
        println(firstrow.getString(0))
    }
    def main(args: Array[String]) {
//    t1()
        test_hive_sparlsql


    }


}
