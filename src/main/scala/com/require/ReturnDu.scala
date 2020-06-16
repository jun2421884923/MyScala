package com.require
import java.util.{Calendar, Date}
import java.text.SimpleDateFormat

import com.test.test1.sc
import org.apache.spark.rdd.RDD
import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ListBuffer

object ReturnDu {
    var sep=","

    def concatStrings(str1: String, str2: String): String = {
        (str1, str2) match {
            case (s1: String, s2: String) => Seq(s1, s2).filter(_ != "").mkString(sep)
            case (null, s: String) => s
            case (s: String, null) => s
            case _ => ""
        }
    }
    def getDaysSub(beginday:Int,endday:Int): Int ={
        val stringDateBegin: String = beginday.toString
        val stringDateEnd: String = endday.toString

        val dateFormat: SimpleDateFormat = new SimpleDateFormat("yyyyMMdd")
        val dateBegin: Date = dateFormat.parse(stringDateBegin)
        val dateEnd: Date = dateFormat.parse(stringDateEnd)
        val calendarBegin: Calendar = Calendar.getInstance()
        val calendarEnd: Calendar = Calendar.getInstance()

        calendarBegin.setTime(dateBegin)
        calendarEnd.setTime(dateEnd)

        // 计算日期间隔天数
        val diff = calendarEnd.getTimeInMillis() - calendarBegin.getTimeInMillis()
        val diffDay = (diff / (1000 * 60 * 60 * 24)).toInt
//        println(diffDay)
        return diffDay
    }
    def getListMax(list : List[Int]):ListBuffer[Int]={
        val tmp_listbuffer: ListBuffer[Int] = ListBuffer.empty[Int]
        if(list.length<=1){
            return tmp_listbuffer
        }
        for( i <- 0 to list.length-2){
            tmp_listbuffer.append(getDaysSub(list(i),list(i+1)))
        }
    return    tmp_listbuffer
    }
    def isTrue(x:ListBuffer[Int],minday:Int,maxday:Int): Boolean ={
        if(x.length<1){
            return false
        }
        for (a <- x){
            if (a>=minday && a<=maxday){
//            if (a==days){
                return true
            }

        }
        return false
    }
    def returnDu(sc:SparkContext,sFile:String,output_path:String): Unit ={
        val sData = sc.textFile(sFile)
        var df=sData.map(_.split("\t")).filter(_.length==3).map(x => (x(0),x(1)))
                .reduceByKey((x,y)=>(x+","+y)).mapValues(x => x.split(",").toList.distinct.map(x=>x.toInt).sortWith( _.compareTo(_) <0))
//                .repartition(5)
//        df.mapValues(x=>getListMax(x))
//                .filter(_._2.nonEmpty).foreach(x=>println(x))
        println("---------------")
        //计算两两差值
        var df_dealv=df.mapValues(x=>getListMax(x))
                        .filter(_._2.nonEmpty).map(x=>(x._2.max,1)).reduceByKey((x,y)=>x+y)
//                .mapValues(x=>x.count())
//                .map((x,y) =>(y,x))
//        df_dealv.foreach(println)
        df_dealv.persist(StorageLevel.MEMORY_ONLY_SER )
        println(df_dealv.count())
        println("-------------")
        val result:RDD[String]=df_dealv.sortByKey().map(x =>x._1.toString+"\t"+x._2.toString)
        println(result.count())
        result.foreach(x=>println(x))
        result.saveAsTextFile(output_path)
//        df_dealv.saveAsTextFile("file")
//        var df_final_30=df_dealv.filter(x=>isTrue(x._2,1,30))
//        var df_final_60=df_dealv.filter(x=>isTrue(x._2,31,60))
//        var df_final_90=df_dealv.filter(x=>isTrue(x._2,61,90))
////        df_final_30.take(10).foreach(x=>println(x))
//        println("1-30天的数量有："+df_final_30.count().toString)
//        println("31-60天的数量有："+df_final_60.count().toString)
//        println("61-90天的数量有："+df_final_90.count().toString)

        println("end ...")
    }
    def test(): Unit ={
        var x="12,43,12,67,6,3"
        x.split(",").toList.distinct.map(x=>x.toInt).sortWith( _.compareTo(_) <0).map(x=>(1,x)).foreach(x=>println(x))
    }
  def main(args: Array[String]): Unit = {
      val conf = new SparkConf().setAppName("returndu")
//              .setMaster("local");
      val sc = new SparkContext(conf)
      var sFile ="D:\\idea_projects\\MyScala\\src\\main\\scala\\com\\require\\data\\test.tx";
      var output_path="/data/res"
      if (args.length==1){
          sFile=args(0)
      }
      println(sFile)
      returnDu(sc,sFile,output_path)
//      println(getDaysSub(20180801,20190425))
  }

}
