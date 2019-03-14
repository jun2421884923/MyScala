//package com.test
//
//import java.text.SimpleDateFormat
//import java.util.Date
//
//import com.util.DateUtils
//import org.apache.spark.SparkConf
//import org.apache.spark.rdd.RDD
//import org.apache.spark.sql.SparkSession
//
//object DataStatisticByDay {
//
//
//  def main(args: Array[String]): Unit = {
//
//    var inputDate: Date = DateUtils.getYesterday(new Date())
//    val dateFormat: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd")
//    var referPage = "predefine2"
//    if (args.length == 2) {
//      inputDate = DateUtils.StringToDate(args(0))
//      referPage = args(1).toString
//    }
//
//    val calDate = dateFormat.format(inputDate) //计算当日的时间
//
//    val calBtwDays = DateUtils.calcDayOffset(DateUtils.StringToDate("1970-01-01"),DateUtils.StringToDate(calDate))
//    val yesterBtwDays = calBtwDays-1
//
//
//
//    val sparkConf = new SparkConf().setAppName("DataStatisticByDay")
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
//
//    //获得view,click,play表,缓存
//    val newDayView = spark.sql(s"select event_cuid , event_exp_id, event_cj_id ,event_works_id from recommend.recommend_event_view where day ='$calDate' and event_refer_page = '$referPage' and event_cuid is not null ").repartition(1000).rdd.map {
//      info =>
//        val exp_id  = if(info(1).toString.length > 0) info(1).toString else "null"
//        val cj_id  = if(info(2).toString.length > 0) info(2).toString else "null"
//        (info(0).toString, exp_id, cj_id, info(3).toString)
//    }
//    newDayView.cache()
//
//    val newDayClick = spark.sql(s"select event_cuid , NVL(event_exp_id,'null'), NVL(event_cj_id,'null') from recommend.recommend_event_click where day ='$calDate' and event_refer_page = '$referPage' and event_cuid is not null ").repartition(1000).rdd.map {
//      info =>
//        val exp_id  = if(info(1).toString.length > 0) info(1).toString else "null"
//        val cj_id  = if(info(2).toString.length > 0) info(2).toString else "null"
//        (info(0).toString, exp_id+"___"+cj_id)
//    }
//    newDayClick.cache()
//
//    val newDayPlay = spark.sql(s"select event_cuid ,event_watchtime from recommend.recommend_event_play where day ='$calDate' and event_refer_page = '$referPage' and event_cuid is not null ").repartition(1000).rdd.map {
//      info =>
//        (info(0).toString, info(1).toString())
//    }
//
//    //获取策略覆盖率统计信息
//   val strategyCoverageInfo =  getStrategyCoverage(newDayView)
//
//    //获取今日Click的userId
//   val allClickUserThisDay = newDayClick.map(_._1).distinct(500)
//    allClickUserThisDay.cache()
//
//    val allViewPVThisDay = newDayView.count() //当日进行View操作的PV
//    val allViewUVThisDay = newDayView.map(_._1).distinct(500).count() //当日进行View操作的UV
//    val allClickPVThisDay = newDayClick.count() //当日进行Click操作的PV
//    val allClickUVThisDay = allClickUserThisDay.count() //当日进行Click操作的UV
//
//
//
//    val dailyPVAndUVInfo = "allViewPVThisDay="+allViewPVThisDay+"_"+"allViewUVThisDay="+allViewUVThisDay+"_"+"allClickPVThisDay="+allClickPVThisDay+"_"+"allClickUVThisDay="+allClickUVThisDay
//
//
//    //从bos中获取历史用户点击计数
//    val clickPredefine2 = sc.textFile(s"bos://******")
//
//    val clickPre2Rdd = clickPredefine2.filter(_.length > 0).map { line =>
//      val info = line.split("_")
//      (info(0).toString, info(1).toInt)
//    }.repartition(1000)
//
//    //通过cuid区分新老用户
//    val allClickDistByTimes = allClickUserThisDay.map(x=>(x,1)).leftOuterJoin(clickPre2Rdd).map{
//      case (cuid,(num1,num2))=>
//        var flag = false //为true则是老用户，false为新用户
//
//        num2 match {
//          case None => flag = false
//          case a => if(a.get >= 20){flag = true} else {flag = false}
//        }
//        (cuid,flag)
//    }
//
//
//    allClickDistByTimes.cache()
//    //Click信息去重
//    val newDayClickDistinct = newDayClick.map(info =>info._1+"___"+info._2).distinct(500).map{
//      info =>
//        val arr = info.split("___")
//        (arr(0),arr(1)+"___"+arr(2))
//    }
//
//
//    //join userClick表，是为了得到用户对应的cj_id，exp_id
//    val allStrageInfoDistByTime = allClickDistByTimes.join(newDayClickDistinct).map{
//      case(cuid,(flag,info))=>
//        (cuid,flag+"___"+info)
//    }
//
//    //join Play表，不去重，得到所有用户的Play行为信息
//   val  allStragePlayInfoDistByTime = allStrageInfoDistByTime.join(newDayPlay).map{
//      case(cuid,(flagAndInfo,watchTime)) =>
//        (cuid,flagAndInfo+"___"+watchTime)
//    }
//
//    //得到区分新老用户的用户点击行为
//    val  allStrageClickInfoDistByTime =allClickDistByTimes.join(newDayClick).map{
//      case(cuid,(flag,info))=>
//        (cuid,flag+"___"+info+"___"+"0")
//    }
//
//
//    val retainUserIdRddPlay = allStragePlayInfoDistByTime.filter(_._2.split("___")(0).toBoolean == true).repartition(1000)
//    retainUserIdRddPlay.cache()
//    val retainPlayNum = retainUserIdRddPlay.count()
//    val retainUserNumPlay = retainUserIdRddPlay.map(_._1).distinct(500).count()                        //获取老用户总数-play
//
//
//    val retainUserIdRddClick = allStrageClickInfoDistByTime.filter(_._2.split("___")(0).toBoolean == true).repartition(1000)
//    retainUserIdRddClick.cache()
//    val retainClickNum = retainUserIdRddClick.count()    //留存用户点击总数
//    val retainUserNumClick = retainUserIdRddClick.map(_._1).distinct(500).count()                        //获取老用户总数-click
//
//
//    val newUserIdRddPlay = allStragePlayInfoDistByTime.filter(_._2.split("___")(0).toBoolean == false).repartition(1000)
//    newUserIdRddPlay.cache()
//    val newPlayNum = newUserIdRddPlay.count()
//    val newUserNumPlay = newUserIdRddPlay.map(_._1).distinct(500).count()                          //获取新用户总数-play
//
//
//
//    val newUserIdRddClick = allStrageClickInfoDistByTime.filter(_._2.split("___")(0).toBoolean == false).repartition(1000)
//    newUserIdRddClick.cache()
//    val newClickNum = newUserIdRddClick.count()
//    val newUserNumClick = newUserIdRddClick.map(_._1).distinct(500).count()                                   //获取新用户总数-click
//
//    val retainUserPlayAndWatchTimeInfo = "retainPlayNum="+retainPlayNum+"_"+"retainUserNumPlay="+retainUserNumPlay+"_"+getClickAndWatchTimeInfo(retainUserIdRddPlay)
//    val newUserPlayAndWatchTimeInfo = "newPlayNum="+newPlayNum+"_" +"newUserNumPlay="+newUserNumPlay+"_"+getClickAndWatchTimeInfo(newUserIdRddPlay)
//    val retainUserClickInfo = "retainClickNum="+retainClickNum+"_"+"retainUserNumClick="+retainUserNumClick+"_" +getClickAndWatchTimeInfo(retainUserIdRddClick)
//    val newUserClickInfo = "newClickNum="+newClickNum+"_"+"newUserNumClick="+newUserNumClick+"_"+getClickAndWatchTimeInfo(newUserIdRddClick)
//
//    println(dailyPVAndUVInfo)
//    println(strategyCoverageInfo)
//    println(retainUserPlayAndWatchTimeInfo)
//    println(newUserPlayAndWatchTimeInfo)
//    println(retainUserClickInfo)
//    println(newUserClickInfo)
//    spark.close()
//
//  }
//
//
//  def getStrategyCoverage(newDayView: RDD[(String, String, String,String)]):String = {
//    newDayView.cache()
//    val allViewVidsCj_2 = newDayView.filter(_._3.equals("cj_2")).map{_._4}.distinct(500).count()
//    val allViewVidsCj_3 = newDayView.filter(_._3.equals("cj_3")).map{_._4}.distinct(500).count()
//    val allViewVidsScence = newDayView.filter(_._2.equals("scene")).map{_._4}.distinct(500).count()
//    val allViewVidsDoudi = newDayView.filter{ info => !((info._2.contains("scene")) || (info._3.contains("cj_"))) }.map{_._4}.distinct(500).count()
//    "allViewVidsCj2="+allViewVidsCj_2+"_"+"allViewVidsCj3="+allViewVidsCj_3+"_"+"allViewVidsScence="+allViewVidsScence+"_"+"allViewVidsDoudi="+allViewVidsDoudi
//  }
//
//  def getDetailInfoByStragegy(userInfo: RDD[(String, String, String, Int)]): _root_.scala.Predef.String = {
//    userInfo.cache()
//    //过滤掉非法用户
//   val validUser =  userInfo.filter(_._4<3600).map{
//      case (cuid,exp_id,cj_id,watchTime) =>
//        (cuid,1)
//    }.reduceByKey(_+_).filter(_._2<500)
//
//   val validUserInfo =  userInfo.map{
//      case (cuid,exp_id,cj_id,watchTime) =>
//        (cuid,watchTime)
//    }.join(validUser).map{
//      case (cuid,(watchTime,nu))=>
//        (cuid,watchTime)
//    }
//    val userCount = validUser.count()
//    val userClickAndWatchInfo = validUserInfo.map { info => (info._2, 1) }.reduce((x,y) =>(x._1+y._1,x._2+y._2))
//    val avgWatch = (userClickAndWatchInfo._1.toDouble/userCount).formatted("%.2f")
//    val avgClick = (userClickAndWatchInfo._2.toDouble/userCount).formatted("%.2f")
//    "avgClick="+avgClick+"|"+"avgWatch="+avgWatch
//  }
//
//  def getClickAndWatchTimeInfo(userInfoRdd: RDD[(String, String)]) = {
//    val userInfoDetailRdd = userInfoRdd.map {
//      case (cuid, info) =>
//        val infoArr = info.split("___")
//        val exp_id = infoArr(1)
//        val cj_id = infoArr(2)
//        val watchTime = infoArr(3).toInt
//        (cuid, exp_id, cj_id, watchTime)
//    }
//    val userCj_2 = userInfoDetailRdd.filter(_._3.equals("cj_2"))
//    val userCj_3 = userInfoDetailRdd.filter(_._3.equals("cj_3"))
//    val userScene = userInfoDetailRdd.filter(_._2.equals("scene"))
//    val userDoudi = userInfoDetailRdd.filter { info => !((info._3.contains("scene")) || (info._2.contains("cj_"))) }
//    val userInfoCj_2:String = getDetailInfoByStragegy(userCj_2)
//    val userInfoCj_3:String = getDetailInfoByStragegy(userCj_3)
//    val userInfoScene:String = getDetailInfoByStragegy(userScene)
//    val userInfoDoudi:String = getDetailInfoByStragegy(userDoudi)
//
//
//    "userInfoCj2:"+userInfoCj_2+"_"+"userInfoCj3:"+userInfoCj_3+"_"+"userInfoScene:"+userInfoScene+"_"+"userInfoDoudi:"+userInfoDoudi
//
//  }
//}
