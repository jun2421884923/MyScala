package com.test

//select pageid,age,count(1) from pv_users group by pageid,age;

import org.apache.spark.{SparkConf, SparkContext}

object CountPVByGroup {
        def main(args: Array[String]): Unit = {
        val conf = new SparkConf()
        .setAppName(CountPVByGroup.getClass.getSimpleName)
        .setMaster("local")
//    Logger.getLogger("org.apache.spark").setLevel(Level.OFF)
//    Logger.getLogger("org.apache.hadoop").setLevel(Level.OFF)
        val sc = new SparkContext(conf)
        val lines = sc.textFile("file:///e:/pv_users.txt")
        //拼接成（1_25,1）的形式
        val newKeyValue =  lines.map(_.split(",")).map(pvdata => ((pvdata(0)+ "_" + pvdata(1)),1))
        //对上述KV进行统计
        val pvcount = newKeyValue.reduceByKey(_ + _)
        //将拼接符号去掉，组合成形如(1,25,1)的形式
        val pvid_age_count = pvcount.map(newkv => (newkv._1.split("_")(0),newkv._1.split("_")(1),newkv._2))
        //结果输出
//    (1,25,1)
//    (2,25,2)
//    (1,32,1)
        pvid_age_count.collect().foreach(println)
        }

        }

