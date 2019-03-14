//package com.test
//
//import org.apache.spark.SparkConf
//import org.apache.spark.sql.{SaveMode, SparkSession}
//
//object InsertDataTest {
//  def main(args: Array[String]): Unit = {
//
//    val sparkConf = new SparkConf().setAppName("InsertDataTest")
//    val spark = SparkSession.builder().config(sparkConf).enableHiveSupport().getOrCreate()
//    val sc = spark.sparkContext
//
//    spark.sql("set spark.core.connection.ack.wait.timeout=300")
//    spark.sql("set spark.shuffle.io.retryWait=60s")
//    spark.sql("set spark.shuffle.io.maxRetries=60")
//    spark.sql("set spark.shuffle.io.maxRetries=60")
//    spark.sql("set spark.reducer.maxSizeInFlight=96")
//    spark.sql("set spark.shuffle.file.buffer=64")
//
//    val partition1 = spark.sql("select userid ,time from test.newuser_predefine2 where timebucket = 1 limit 10")
//    partition1.write.mode(SaveMode.Overwrite).insertInto(" test.newuser_predefine2 Partition (timebucket = 99)")
//    spark.close()
//
//  }
//}
