package com.operating;

import com.entity.AdverLog;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.nio.charset.Charset;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DealLog {
    public static  AdverLog convLog(String line){
        AdverLog  al =null;

        String regexp = "(?<ip>[0-9\\.]+[0-9]+)\\s+(?<l>[\\S]+)\\s+(?<u>[\\S]+)\\s+\\[(?<time>[^\\]]+)\\]\\s*\"(GET|POST)\\s*.*\\?(?<uri>.*) HTTP/1\\.[01]\"\\s*(?<code>[0-9]+)\\s*(?<code1>[0-9]+)\\s*\".*\"\\s*\".*\"\\s*\".*\"\\s*\"(?<agent>[^\"]*)\".*\"(?<ip1>[0-9\\.]*[0-9]*)\".*";
        Pattern p = Pattern.compile(regexp);
        Matcher matcher = p.matcher(line);
        if(matcher.find()){
            al =new AdverLog();
            if (line.contains("server")){
                al.setIp(matcher.group("ip1"));
            }else {
                al.setIp(matcher.group("ip"));
            }
            al.setUseragent(matcher.group("agent"));
            List<NameValuePair> uri = URLEncodedUtils.parse(matcher.group("uri"), Charset.forName("UTF-8"));
//            System.out.println(uri);
            for (NameValuePair pair :uri){
                switch (pair.getName()){
                    case "cuid":
                        al.setCuid(pair.getValue());
                        break;
                    case "imei":
                        al.setImei(pair.getValue());
                        break;
                    case "android_id":
                        al.setAndroid_id(pair.getValue());
                        break;
                    case "operator_type":
                        al.setOperator_type(pair.getValue());
                        break;
                    case "model":
                        al.setModel(pair.getValue());
                        break;
                    case "vendor":
                        al.setVendor(pair.getValue());
                        break;
                    case "os_version":
                        al.setOs_version(pair.getValue());
                        break;
                    case "os":
                        al.setOs(pair.getValue());
                        break;
                    case "dpi":
                        al.setDpi(pair.getValue());
                        break;
                    case "device":
                        al.setDevice(pair.getValue());
                        break;
                    case "connection_type":
                        al.setConnection_type(pair.getValue());
                        break;
                    case "orientation":
                        al.setOrientation(pair.getValue());
                        break;
                    case "screen_width":
                        al.setScreen_width(pair.getValue());
                        break;
                    case "screen_height":
                        al.setScreen_height(pair.getValue());
                        break;
                    case "imsi":
                        al.setImei(pair.getValue());
                        break;
                    case "longitude":
                        al.setLongitude(pair.getValue());
                        break;
                    case "latitude":
                        al.setLatitude(pair.getValue());
                        break;
                    case "mac":
                        al.setMac(pair.getValue());
                        break;
                    default:
                        break;
                }

            }
        }


        return al;
    }
}
