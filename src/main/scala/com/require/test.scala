package com.require
import java.io.FileWriter
import java.io.FileReader

import scala.util.Random
import java.io.File
import scala.util.matching.Regex
import scala.io.Source
/*
val xs=Seq(1,5,3,4,6,2)

    println("==============sorted排序=================")
   println(xs.sorted) //升序
   println(xs.sorted.reverse) //降序
    println("==============sortBy排序=================")
   println( xs.sortBy(d=>d) ) //升序
   println( xs.sortBy(d=>d).reverse ) //降序
    println("==============sortWith排序=================")
   println( xs.sortWith(_<_) )//升序
   println( xs.sortWith(_>_) )//降序
 */
object test {
    def genfile(): Unit ={


        val writer = new FileWriter(new File(System.getProperty("user.dir"),System.getProperty("file.separator")+"out"+System.getProperty("file.separator")+"sample_age_data.txt"),false)
        val rand = new Random()
        for ( i <- 1 to 10000) {
            writer.write( i + " " + rand.nextInt(100))
            writer.write(System.getProperty("line.separator"))
        }
        writer.flush()
        writer.close()
    }
    def test1(): Unit ={
        println(System.getProperty("user.dir"))
        println(System.getProperty("file.separator"))
        println(System.getProperty("path.separator"))
        val PATH = getClass.getResource("").getPath
        val file="D:\\idea_projects\\MyScala\\src\\main\\scala\\com\\require\\data\\test"
        val filename=new File(PATH+System.getProperty("file.separator")+"data"+System.getProperty("file" +
          ".separator")+"test")
        val fileLines = Source.fromFile(file).getLines.toArray.filter(x => x.contains("ad/pandora")).count(x =>x
          .contains("xdpandora-adx_fill"))
        print(fileLines)
//        .take(5).foreach(println)
//        ad/pandora
    }
    def re_test(): Unit ={
        val pattern = new Regex("adx_fill_[0-9]*")  // 首字母可以是大写 S 或小写 s
        val impid_pattern = new Regex("imp\":\\[\\{\"id\":\"\\d*")  // 首字母可以是大写 S 或小写 s
        val num_pattern = new Regex("[0-9]+")  // 首字母可以是大写 S 或小写 s

        val s="""NOTICE: 19-12-25 14:00:00 [/home/video/odp/php/phplib/saf/base/Log.php:28] errno[0] logId[3599893517] " +
          "uri[/ad/pandora/?sspid=6078] refer[] cookie[]  optime[1577253599.894] client_ip[47.100.78.74] " +
          "local_ip[192.168.83.88] product[ORP] subsys[ORP] adx_fill_18qweq module[ad] uniqid[0] cgid[0] uid[0]" +
          " cntscs_use_time[0.31089782714844ms] adx_fill_1858123  cntlocal_use_time[0ms]
          getappconf_use_time[0.0081062316894531ms] " +
          "parseBidRequest_use_time[0.032901763916016ms] oriRequest_6078[{"id":"1848bc03-ca39-4a3c-9951-035f84b87ccf","imp":[{"id":"2822","native":{"assets":[{"id":0,"required":false,"title":{"len":30,"text":null},"img":null,"data":null,"link":null},{"id":1,"required":false,"title":null,"img":null,"data":{"type":2,"len":50,"value":null},"link":null},{"id":2,"required":false,"title":null,"img":{"type":3,"w":600,"h":300,"url":null},"data":null,"link":null}]}}],"device":{"ua":"Mozilla\/5.0 (Linux; Android 7.0; MI 4S Build\/NRD90M; wv) AppleWebKit\/537.36 (KHTML, like Gecko) Version\/4.0 Chrome\/64.0.3282.137 Mobile Safari\/537.36","dnt":0,"geo":null,"ip":"112.49.244.143","deviceType":4,"make":"Xiaomi","model":"MI 4S","os":"android","osv":"7.0","h":1920,"w":1080,"carrier":1,"connectiontype":2,"did":"868869028239829","dpid":"2b2ab19f28234802","idfa":null,"mac":"B0:E2:35:41:15:C7"},"app":{"id":"2822","name":"点点新闻","ver":"1.96","bundle":"com.screenlockshow.android"}}] getimpdata_use_time[0.13089179992676ms] getFormatRequest_use_time[0.17905235290527ms] xdpandora-clientip[112.49.244.143] effectiveRequest_6078[{"id":"1848bc03-ca39-4a3c-9951-035f84b87ccf","imp":[{"id":"2822","native":{"assets":[{"id":0,"required":false,"title":{"len":30,"text":null},"img":null,"data":null,"link":null},{"id":1,"required":false,"title":null,"img":null,"data":{"type":2,"len":50,"value":null},"link":null},{"id":2,"required":false,"title":null,"img":{"type":3,"w":600,"h":300,"url":null},"data":null,"link":null}]}}],"device":{"ua":"Mozilla\/5.0 (Linux; Android 7.0; MI 4S Build\/NRD90M; wv) AppleWebKit\/537.36 (KHTML, like Gecko) Version\/4.0 Chrome\/64.0.3282.137 Mobile Safari\/537.36","dnt":0,"geo":null,"ip":"112.49.244.143","deviceType":4,"make":"Xiaomi","model":"MI 4S","os":"Android","osv":"7.0","h":1920,"w":1080,"carrier":1,"connectiontype":2,"did":"868869028239829","dpid":"2b2ab19f28234802","idfa":null,"mac":"B0:E2:35:41:15:C7","devicetype":4},"app":{"id":"2822","name":"点点新闻","ver":"1.96","bundle":"com.screenlockshow.android"},"others":{"transactionId":"6c6f893af6a574277f4e36a4dc5a608e","sspid":6078,"ip":"112.49.244.143","impType":"native","test":null}}] xdpandora-impid_bidfloor[2822_0] getAdverMetaData_use_time[0.51617622375488ms] xdpandora-rtFreqData[{"2_8_xdadver_advertotalfreq:12-25:18465:0:0":"199366","2_8_xdadver_advertotalfreq:12-25:18582:0:0":"2989854"}] xdpandora-freq_limit_18465[{"num":1500000,"type":"0","cycleType":"0","startdate":"2019-05-23","enddate":"2028-05-30"}] xdpandora-freq_limit_18582[{"num":6000000,"type":"0","cycleType":"0","startdate":"2019-05-23","enddate":"2028-05-30"}] curl_multi_connecttion_info[{"url":"http:\/\/100.66.36.115:80\/bidding\/stdssp?sspid=6078&reqid=6c6f893af6a574277f4e36a4dc5a608e","content_type":null,"http_code":200,"header_size":361,"request_size":770,"filetime":-1,"ssl_verify_result":0,"redirect_count":0,"local_port":51208,"total_time":0.207533,"namelookup_time":1.0e-5,"connect_time":0.001317,"pretransfer_time":0.001349,"size_upload":505,"size_download":8236,"speed_download":39685,"speed_upload":2433,"download_content_length":-1,"upload_content_length":505,"starttransfer_time":0.20748,"redirect_time":0}] xdpandora-adx_fill_18582[1] getdsp_use_time[209.18798446655ms] xdpandora-good_price_18582[18582_2822_200_211] effectiveResponse_6078[{"id":"1848bc03-ca39-4a3c-9951-035f84b87ccf","status":0,"msg":"success","bid":[{"impid":"2822","admnative":{"assets":[{"id":0,"required":false,"title":{"text":"双眼皮全切过程"}},{"id":1,"required":false,"data":{"value":"双眼皮咋割的"}},{"id":2,"required":false,"img":{"url":"https:\/\/lupic.cdn.bcebos.com\/20191130\/3000005115%2320.jpg","w":1280,"h":720}}],"link":{"url":"https:\/\/cpro.baidu.com\/cpro\/ui\/uijs.php?en=mywWUA71T1Yknzu9TZKxpyfqn1b4nHmdrjmzniu9TZKxTv-b5yNbuyF-njIbFhPC5H0huAbqrauGUyNG5H6vrj6vrH0zrjc1rH6zriuGUyNGgvPs5H0hpgPxmgKs5HDhph_quhPBuWnLPW6YPHnzrjm1miuo5iNjfBNKfBNDniNDfBNjPBNKPaNDPiNKPBNaraN7wiNaPiNjPau_IyVG5HDhUyPsUHY4nWmsFhq15y78uZFEpyfhTHd-uANBuH0Lu7qWTZchThcqniuzT1YkFMPbpdqv5HfhTvwogLu-TMPGUv3qPi3zQW0hTvN_UANzgv-b5HchTv-b5HP9rjm1nWfdPW61PvFhuhnhTLwGujYvnj0kFMfqIZKWUA-WpvNbndqVUvFxmgPsg1nhIAd15HDdP1TzPHndrHbhIZRqPWfLrHmdPzud5y9YIZ0-nYD-nbm-nbmYmhwBPAD-nbf-nbw-uANBuH0LuaRzwyPEUiudpydGujYYnRR4rRDYfW6swHI7rHmzfRR3wjTLn19KnHIaPjc4ngs4nW64n1c3nW04PW63PW6hIWYzFhbqrARvPyRkryf&adx=1&c=news&cf=1&cp=fc_middle_page_app&cvrq=40152&ds=fmp&eid_list=106247_106249_1060_201994_202986_207126_209051&expid=200490_200604_201130_201877_201994_203338_207126_207991_209051&fr=5&fv=0&haacp=1172&img_typ=2146&itm=0&lu_idc=yf&lukid=1&lus=3a8632456837bffc&lust=5e02fadf&lvl=6&mscf=0&mtids=3000005115&n=10&nttp=3&p=baidu&sh=1920&sr=81&ssp2=0&sw=1080&swi=1&tpl=template_inlay_all_mobile_lu_native_ad_app_feed&tsf=dtp:2&tt_sign=%CB%AB%D1%DB%C6%A4%C8%AB%C7%D0%B9%FD%B3%CC&tt_src=1&u=&uicf=lurecv&urlid=0&wi=4&eot=1","clicktrackers":["http:\/\/adx.xiaodutv.com\/click\/stdssp?p=eyJzaWQiOiJBQkZBQTMzQjZGMDE2MTEyQzBBODQ2MUYwNDAwQUMyQyIsInJlcWlkIjoiNmM2Zjg5M2FmNmE1NzQyNzdmNGUzNmE0ZGM1YTYwOGUiLCJzZWxsZXJpZCI6NjA3OCwib3JpZ2luX3NlbGxlcmlkIjo2MDc4LCJzZWxsZXJ1aWQiOiIiLCJhcHBpZCI6IjU3NWZkMTA2Iiwib3JpZ2luX2ltcGlkIjoiMjgyMiIsImltcGlkIjoiMjgyMiIsImRzcF9pbXBpZCI6IjY0Nzk2NTciLCJidXllcmlkIjoxMDYyLCJiaWRpZCI6IiIsInNlYXRpZCI6IiIsImNyaWQiOiI0MzMyMjcwZDAzNGQwNmM1M2QxYmE5MzZjZDRiMGEzZCIsImJ1eWVyX2JpZHR5cGUiOjAsImJ1eWVyX3ByaWNlIjoyMTEsImJ1eWVyX2dzcCI6MjExLCJleHRkYXRhIjoiIiwiZmxvdyI6ImYwX2EsZjFfcG1wLGYyX290aGVyIiwiZGVhbGlkIjoiMTEzNjIiLCJhZHhfYmlkdHlwZSI6MCwiYWR4X3ByaWNlIjoyMTEsImlwIjoiMTEyLjQ5LjI0NC4xNDMiLCJkZXZpY2Vfb3MiOiJBbmRyb2lkIn0=&sid=ABFAA33B6F016112C0A8461F0400AC2C&buyerid=1062&impid=2822&lp=aHR0cHM6Ly9jcHJvLmJhaWR1LmNvbS9jcHJvL3VpL3VpanMucGhwP2VuPW15d1dVQTcxVDFZa256dTlUWkt4cHlmcW4xYjRuSG1kcmptem5pdTlUWkt4VHYtYjV5TmJ1eUYtbmpJYkZoUEM1SDBodUFicXJhdUdVeU5HNUg2dnJqNnZySDB6cmpjMXJINnpyaXVHVXlOR2d2UHM1SDBocGdQeG1nS3M1SERocGhfcXVoUEJ1V25MUFc2WVBIbnpyam0xbWl1bzVpTmpmQk5LZkJORG5pTkRmQk5qUEJOS1BhTkRQaU5LUEJOYXJhTjd3aU5hUGlOalBhdV9JeVZHNUhEaFV5UHNVSFk0bldtc0ZocTE1eTc4dVpGRXB5ZmhUSGQtdUFOQnVIMEx1N3FXVFpjaFRoY3FuaXV6VDFZa0ZNUGJwZHF2NUhmaFR2d29nTHUtVE1QR1V2M3FQaTN6UVcwaFR2Tl9VQU56Z3YtYjVIY2hUdi1iNUhQOXJqbTFuV2ZkUFc2MVB2Rmh1aG5oVEx3R3VqWXZuajBrRk1mcUlaS1dVQS1XcHZOYm5kcVZVdkZ4bWdQc2cxbmhJQWQxNUhEZFAxVHpQSG5kckhiaElaUnFQV2ZMckhtZFB6dWQ1eTlZSVowLW5ZRC1uYm0tbmJtWW1od0JQQUQtbmJmLW5idy11QU5CdUgwTHVhUnp3eVBFVWl1ZHB5ZEd1allZblJSNHJSRFlmVzZzd0hJN3JIbXpmUlIzd2pUTG4xOUtuSElhUGpjNG5nczRuVzY0bjFjM25XMDRQVzYzUFc2aElXWXpGaGJxckFSdlB5UmtyeWYmYWR4PTEmYz1uZXdzJmNmPTEmY3A9ZmNfbWlkZGxlX3BhZ2VfYXBwJmN2cnE9NDAxNTImZHM9Zm1wJmVpZF9saXN0PTEwNjI0N18xMDYyNDlfMTA2MF8yMDE5OTRfMjAyOTg2XzIwNzEyNl8yMDkwNTEmZXhwaWQ9MjAwNDkwXzIwMDYwNF8yMDExMzBfMjAxODc3XzIwMTk5NF8yMDMzMzhfMjA3MTI2XzIwNzk5MV8yMDkwNTEmZnI9NSZmdj0wJmhhYWNwPTExNzImaW1nX3R5cD0yMTQ2Jml0bT0wJmx1X2lkYz15ZiZsdWtpZD0xJmx1cz0zYTg2MzI0NTY4MzdiZmZjJmx1c3Q9NWUwMmZhZGYmbHZsPTYmbXNjZj0wJm10aWRzPTMwMDAwMDUxMTUmbj0xMCZudHRwPTMmcD1iYWlkdSZzaD0xOTIwJnNyPTgxJnNzcDI9MCZzdz0xMDgwJnN3aT0xJnRwbD10ZW1wbGF0ZV9pbmxheV9hbGxfbW9iaWxlX2x1X25hdGl2ZV9hZF9hcHBfZmVlZCZ0c2Y9ZHRwOjImdHRfc2lnbj0lQ0IlQUIlRDElREIlQzYlQTQlQzglQUIlQzclRDAlQjklRkQlQjMlQ0MmdHRfc3JjPTEmdT0mdWljZj1sdXJlY3YmdXJsaWQ9MCZ3aT00JmVvdD0x","http:\/\/app.video.baidu.com\/adver\/?reqid=1848bc03-ca39-4a3c-9951-035f84b87ccf&a=18582&d=click&transaction_id=6c6f893af6a574277f4e36a4dc5a608e&sellerid=6078&impid=2822&p=pmp&bw=1&b=1546&terminal=adnative&os=Android&os_version=7.0&vendor=Xiaomi&model=MI+4S&idfa=&android_id=2b2ab19f28234802&imei=868869028239829&connection_type=2&ref=&m=pmp-xiaoduadx-baiduunion_4332270d034d06c53d1ba936cd4b0a3d"]},"imptrackers":["http:\/\/wn.pos.baidu.com\/adx.php?c=d25pZD0zYTg2MzI0NTY4MzdiZmZjAHM9M2E4NjMyNDU2ODM3YmZmYwB0PTE1NzcyNTM1OTkAc2U9MgBidT00AHByaWNlPVhnTDYzd0FPTFVCN2pFcGdXNUlBOHRtY0VCU1pYZGxvUEgxb2F3AGNoYXJnZV9wcmljZT0zMzIAc2hhcmluZ19wcmljZT0zMzIwMDAAd2luX2RzcD00AGNobWQ9MQBiZGlkPQBjcHJvaWQ9AHdkPTAAdHU9NjQ3OTY1NwBhZGNsYXNzPTEzAHNyY3Q9NABwb3M9MABsb2M9MQBlaWQ9MABiY2htZD0wAHRtPTI3NTAyNDE1NgBhcHA9ZWRlYmUwN2QAdj0xAGk9NzQzNzY3ZmM","http:\/\/adx.xiaodutv.com\/impression\/stdssp?p=eyJzaWQiOiJBQkZBQTMzQjZGMDE2MTEyQzBBODQ2MUYwNDAwQUMyQyIsInJlcWlkIjoiNmM2Zjg5M2FmNmE1NzQyNzdmNGUzNmE0ZGM1YTYwOGUiLCJzZWxsZXJpZCI6NjA3OCwib3JpZ2luX3NlbGxlcmlkIjo2MDc4LCJzZWxsZXJ1aWQiOiIiLCJhcHBpZCI6IjU3NWZkMTA2Iiwib3JpZ2luX2ltcGlkIjoiMjgyMiIsImltcGlkIjoiMjgyMiIsImRzcF9pbXBpZCI6IjY0Nzk2NTciLCJidXllcmlkIjoxMDYyLCJiaWRpZCI6IiIsInNlYXRpZCI6IiIsImNyaWQiOiI0MzMyMjcwZDAzNGQwNmM1M2QxYmE5MzZjZDRiMGEzZCIsImJ1eWVyX2JpZHR5cGUiOjAsImJ1eWVyX3ByaWNlIjoyMTEsImJ1eWVyX2dzcCI6MjExLCJleHRkYXRhIjoiIiwiZmxvdyI6ImYwX2EsZjFfcG1wLGYyX290aGVyIiwiZGVhbGlkIjoiMTEzNjIiLCJhZHhfYmlkdHlwZSI6MCwiYWR4X3ByaWNlIjoyMTEsImlwIjoiMTEyLjQ5LjI0NC4xNDMiLCJkZXZpY2Vfb3MiOiJBbmRyb2lkIn0=&sid=ABFAA33B6F016112C0A8461F0400AC2C&buyerid=1062&impid=2822","http:\/\/app.video.baidu.com\/adver\/?reqid=1848bc03-ca39-4a3c-9951-035f84b87ccf&a=18582&d=show&transaction_id=6c6f893af6a574277f4e36a4dc5a608e&sellerid=6078&impid=2822&p=pmp&bw=1&b=1546&terminal=adnative&os=Android&os_version=7.0&vendor=Xiaomi&model=MI+4S&idfa=&android_id=2b2ab19f28234802&imei=868869028239829&connection_type=2&ref=&sp=${AUCTION_PRICE}&m=pmp-xiaoduadx-baiduunion_4332270d034d06c53d1ba936cd4b0a3d&abp=211&pbp=200"]},"action":1,"price":200,"nurl":"http:\/\/app.video.baidu.com\/adver\/?sc=${AUCTION_PRICE}&transaction_id=6c6f893af6a574277f4e36a4dc5a608e&a=18582&b=1546&d=win&sellerid=6078&impid=2822&m=4332270d034d06c53d1ba936cd4b0a3d&st=0&oc=200&ot=0&bf=0&bw=1"}]}] scounting_use_time[2.2799968719482ms] un[] mobilephone[] email[] baiduid[] url[/ad/pandora/?sspid=6078] refer[] uip[47.100.78.74] ua[] host[bj.pandora.xiaodutv.com] cost[228]"
"""
        var streagy=(pattern findAllIn s).mkString("#")
        var impid=(impid_pattern findAllIn s).mkString("#")
        println(impid)   // 使用逗号 , 连接返回结果
        println(streagy)   // 使用逗号 , 连接返回结果
        println((num_pattern findAllIn impid).mkString(",") )   // 使用逗号 , 连接返回结果
        println((num_pattern findAllIn streagy ).mkString(","))   // 使用逗号 , 连接返回结果

    }
    def deal_line(line:String): Unit ={
        val pattern = new Regex("adx_fill_[0-9]*")  // 首字母可以是大写 S 或小写 s
        val impid_pattern = new Regex("imp\":\\[\\{\"id\":\"\\d*")  // 首字母可以是大写 S 或小写 s
        val num_pattern = new Regex("[0-9]+")  // 首字母可以是大写 S 或小写 s
        var impid=(impid_pattern findAllIn line).mkString("#")
        var strategy=(pattern findAllIn line).mkString("#")
        println(impid)   // 使用逗号 , 连接返回结果
        println(strategy)   // 使用逗号 , 连接返回结果

        print((num_pattern findAllIn strategy).mkString("@"))


        print((num_pattern findAllIn impid).mkString("@"))

    }
    def getsid(x:String )={

        val pattern="""sid=[A-Za-z0-9]+""".r
        (pattern findAllIn x).mkString("#").split("=")(1)

    }
    //rdd_final=rdd_res.map(x =>(getsid(x),x)).collectAsMap()  对key去重
    //al usedatardd = sc.parallelize(rdd_final.values.toSeq)   map转rdd
    def deal_odplog(filepath:String): Unit ={
        val filename=new File(filepath)
        val fileLines = Source.fromFile(filename).getLines.toArray.filter(x => x.contains("ad/pandora")).take(10).map(x
        =>deal_line(x))
//        fileLines.take(5).foreach(println)
//        var res_list:List[Map[String,Int]]=List()
    }
    var impid_map:Map[String,Int]=Map()
    var impid_strategy_map:Map[String,Int]=Map()

    def main(args: Array[String]): Unit = {
//        val start = System.nanoTime()
//        var filepath="D:\\idea_projects\\MyScala\\src\\main\\scala\\com\\require\\data\\test"
//        var outfilepath="D:\\idea_projects\\MyScala\\src\\main\\scala\\com\\require\\data\\res"
//
//        if (args.length==2){
//            filepath=args(0)
//            outfilepath=args(1)
//        }
//        println(filepath)
//        println(outfilepath)
//
////        deal_odplog(filepath)
//
//        val end = System.nanoTime()
//        println("程序运行时间:"+((end - start)/1000000000d).formatted("%.4f")+"s")
//        genfile()
//        test1()
        re_test()
    }


}
