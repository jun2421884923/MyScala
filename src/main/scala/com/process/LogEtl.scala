package com.process

import com.operating.DealLog.convLog
import org.apache.spark.{SparkConf, SparkContext}
import com.operating.DirDeal.DeleteFolder
object LogEtl {


  def main(args: Array[String]): Unit  = {
    var input_path="D:\\data\\testdata.txt"
    var output_path="D:\\data\\testdata1"
    if(args.length==2){
        input_path = args(0).toString
        output_path = args(1).toString
    }
    var local_output_path=output_path
    if(output_path.contains("file")){
      local_output_path=output_path.toLowerCase.replace("file:","")
    }
    if (!output_path.contains("bos") && !output_path.contains("hdfs")){
      val bool = DeleteFolder(local_output_path)
      if(! bool){
        println("输出目录删除fail")
        sys.exit(1)
      }
    }

    println("input_path="+input_path)
    println("output_path="+output_path)

    val conf = new SparkConf().setAppName("LogEtl");
    val sc = new SparkContext(conf)
    val logData = sc.textFile(input_path).cache()
    println("start take")
    logData.take(3).foreach(x =>println(x))
    println("end take")
    logData.map(x => {var res =convLog(x)
      if(res!=null){
       (res.getCuid+"\t"+res.getImei+"\t"+res.getAndroid_id+"\t"+res.getOperator_type+"\t"+res.getModel
         +"\t"+res.getVendor+"\t"+res.getOs_version+"\t"+res.getOs+"\t"+
         res.getDpi+"\t"+res.getDevice+"\t"+res.getConnection_type+"\t"+res.getOrientation
         +"\t"+res.getScreen_width+"\t"+res.getScreen_height+"\t"+res.getIp+"\t"+
         res.getImei+"\t"+res.getLongitude+"\t"+res.getLatitude+"\t"+
         res.getMac+"\t"+res.getUseragent)}
      else{

      }
    })
//      .foreach(x =>print(x))
      .saveAsTextFile(output_path)

  }

}
