//package test;
//
//import com.conf.conf;
//import com.entity.AdverLog;
//import entity.Student;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.utils.URLEncodedUtils;
//import org.dom4j.Document;
//import org.dom4j.DocumentException;
//import org.dom4j.Element;
//import org.dom4j.io.SAXReader;
//import org.junit.Before;
//import org.junit.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.*;
//import java.nio.charset.Charset;
//import java.text.SimpleDateFormat;
//import java.util.*;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//import java.util.stream.Collectors;
//
//import static com.operating.DealLog.convLog;
//
//public class UnitTest {
//    private static Logger logger = LoggerFactory.getLogger(UnitTest.class);
//
//    @Test
//    public  void testSplit(){
//        Pattern p=Pattern.compile("\\d+");
//        String[] strarr=p.split("我的QQ是:456456我的电话是:0532214我的邮箱是:aaa@aaa.com");
//        ArrayList< String> arrayList = new ArrayList<String>(strarr.length);
//        Collections.addAll(arrayList, strarr);
//        arrayList.stream().forEach(System.out::println);
//
//    }
//
//
//
//    @Test
//    public void testDateReg(){
//        String regexp="(?<year>\\d{4})-(?<date>\\d{2})-(?<day>\\d{2})";
//        Pattern p=Pattern.compile(regexp);
//        Matcher matcher = p.matcher("qewqe2019-02-25tqeqe");
//        if(matcher.find()){
//            System.out.println(matcher.group("year"));
//            System.out.println(matcher.group("date"));
//            System.out.println(matcher.group("day"));
//        }
//    }
//
//    @Test
//    public void testpRep(){
//        String str="61.135.168.22 - - [25/Feb/2018:10:00:02 +0800] \"GET /adver/?a=53&b=adx_1046&rp=17&m=adx_1046_nafb&transaction_id=304f3fb927d354c51bf590a4deb635d0&sellerid=6000&impid=1054&p=dsp&terminal=iphnative&position=shortVideoDetailFeed&works_id=&block_sign=search&video_type=&play_channel=&site=baishi.baidu.com&online=1&cuid=02%3A00%3A00%3A00%3A00%3A00_039acbe955b5a3031ca66fcfc1607f46a0352ae0&version=7.9.3&device=iphone&os=2&os_version=10.3.3&locale=zh_CN&vendor=APPLE&model=iPhone7%2C2&screen_width=750&screen_height=1334&idfa=48AB89E1-4E0C-46CF-868D-45D2C920CD34&android_id=&mac=&imei=&connection_type=2&operator_type=0&orientation=p&dpi=0&d=start&ts_s=1519524001.7719&e=24ddfda96e65ffc9eaa5caa47521e06461c1fd3251d280395907f42e4ae3f9ea HTTP/1.1\" 200 54 \"-\" \"-\" \"BAIDUID=22232326C4B772DD9ABE61D45B2695E0:FG=1\" \"VideoiPhone\" 0.000 0002374443 192.168.80.53 - yy.videoclick.baidu.com \"223.73.237.5\" 00023744430894478528022510 1519524002.374";
//        String regexp = "(?<ip>[0-9\\.]+[0-9]+)\\s+(?<l>[\\S]+)\\s+(?<u>[\\S]+)\\s+\\[(?<time>[^\\]]+)\\]\\s*\"(GET|POST)\\s*.*\\?(?<uri>.*) HTTP/1\\.[01]\"\\s*(?<code>[0-9]+)\\s*.*BAIDUID=(?<baiduid>[\\S]+)\".*";
//        Pattern p = Pattern.compile(regexp);
//        Matcher matcher = p.matcher(str);
//        if(matcher.find()){
////            System.out.println(matcher.group("ip"));
////            System.out.println(matcher.group("time"));
//            System.out.println(matcher.group("uri"));
////            System.out.println(matcher.group("code"));
////            System.out.println(matcher.group("baiduid"));
//            List<NameValuePair> uri = URLEncodedUtils.parse(matcher.group("uri"), Charset.forName("UTF-8"));
//            System.out.println(uri);
//            for (NameValuePair pair :uri){
//                if (pair.getName().equals("a")) {
//                    System.out.println(pair.getValue());
//                }
//            }
//
////            uri.stream().map(x -> x.getName()+":"+x.getValue()).collect(Collectors.toList()).forEach(System.out::println);
//
//        }
//    }
//    @Before
//    public void init() {
//
//    }
//
//
//
//    //1列出班上超过85分的学生姓名，并按照分数降序输出用户名字
//    @Test
//    public void test1() {
//        Random random = new Random();
//
//        ArrayList<Student> stuList = new ArrayList<Student>() {
//            {
//                for (int i = 0; i < 100; i++) {
//                    add(new Student("student" + i, random.nextInt(50) + 50));
//                }
//            }
//        };
//        List<String> studentList = stuList.stream()
//                .filter(x->x.getScore()>85)
//                .sorted(Comparator.comparing(Student::getScore).reversed())
//                .map(Student::getName)
//                .collect(Collectors.toList());
//        System.out.println(studentList);
//    }
//
//    /**
//     * 读任意位置的配置文件
//     * @throws IOException
//     */
//    @Test
//    public void testReadProperties() throws IOException {
//        Properties properties = new Properties();
//// 使用InPutStream流读取properties文件
//        BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\data\\config.properties"));
//        properties.load(bufferedReader);
//// 获取key对应的value值
//        String key = properties.getProperty("testkey");
//        System.out.println(key);
//        bufferedReader.close();
//    }
//
//    /**
//     * 配置文件在类中，方便读取
//     * @throws IOException
//     */
//    @Test
//    public void testReadClassPro() throws IOException {
//        Properties properties = new Properties();
//        // 使用ClassLoader加载properties配置文件生成对应的输入流
//        InputStream in = UnitTest.class.getClassLoader().getResourceAsStream("log4j.properties");
//        // 使用properties对象加载输入流
//        properties.load(in);
//        //获取key对应的value值
//        String property = properties.getProperty("log4j.rootLogger");
//        System.out.println(property);
//        in.close();
//    }
//
//    /**
//     * 读取xml配置文件
//     * @param
//     */
//    @Test
//    public void testReadXml(){
//        InputStream inputstream=null;
//        try {
//            SAXReader reader=new SAXReader();
//            inputstream=ClassLoader.getSystemResourceAsStream(conf.CONF_FILE);
//            Document document  = reader.read(inputstream);
//            Element rootElement = document.getRootElement();
//            // 查找指定节点名称QName的所有子节点elements
//            List<Element> list = rootElement.elements("property");
//            for (Element elt:list){
//                if(elt.getName().equals("property")){
////                    System.out.println(elt.getTextTrim());
//                    String name = elt.attributeValue("name");
//                    String value = elt.attributeValue("value");
//                    String text=elt.getTextTrim();
//                    System.out.println("name:"+name+"  value="+value+"   text="+text);
//                }
//            }
//
//
//        } catch (DocumentException e) {
//            logger.error("Read XML config File failed!", e);
//            e.printStackTrace();
//        }
//        finally {
//            if (inputstream!=null){
//                try{
//                    inputstream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    /**
//     * 解析adver原始日志
//     */
//    @Test
//    public void testAdverLog(){
//        String str="220.181.50.74 - - [05/Mar/2018:00:00:02 +0800] \"GET /adver/?sellerid=6020&impid=1549&srequest=&sresponse=&sanswer=&d=server&transaction_id=eccdfe1fd326903da57538943aab4a78&bf=1300&terminal=adnative&os=android&os_version=5.1&vendor=HUAWEI&model=HUAWEI+TAG-TL00&idfa=&android_id=bc367256ea21cc98&imei=860774036532686&connection_type=2 HTTP/1.1\" 200 54 \"-\" \"-\" \"-\" \"-\" 0.005 0002262933 192.168.80.53 - yy.videoclick.baidu.com \"\" 00022629330894478528030500 1520179202.267";
//        String regexp = "(?<ip>[0-9\\.]+[0-9]+)\\s+(?<l>[\\S]+)\\s+(?<u>[\\S]+)\\s+\\[(?<time>[^\\]]+)\\]\\s*\"(GET|POST)\\s*.*\\?(?<uri>.*) HTTP/1\\.[01]\"\\s*(?<code>[0-9]+)\\s*(?<code1>[0-9]+)\\s*\".*\"\\s*\".*\"\\s*\".*\"\\s*\".*\".*\"(?<ip1>[0-9\\.]*[0-9]*)\".*";
//        Pattern p = Pattern.compile(regexp);
//        Matcher matcher = p.matcher(str);
//        if(matcher.find()){
////            System.out.println(matcher.group("ip"));
////            System.out.println(matcher.group("time"));
//            System.out.println(matcher.group("uri"));
//            System.out.println(matcher.group("code"));
//            System.out.println(matcher.group("code1"));
//            System.out.println(matcher.group("ip1"));
////            System.out.println(matcher.group("baiduid"));
//            List<NameValuePair> uri = URLEncodedUtils.parse(matcher.group("uri"), Charset.forName("UTF-8"));
//            System.out.println(uri);
//            for (NameValuePair pair :uri){
//                if (pair.getName().equals("a")) {
//                    System.out.println(pair.getValue());
//                }
//            }
//
////            uri.stream().map(x -> x.getName()+":"+x.getValue()).collect(Collectors.toList()).forEach(System.out::println);
//
//        }
//    }
//    @Test
//    public void  test123(){
//        String str="112.34.110.135 - - [05/Mar/2018:06:00:02 +0800] \"GET /adver/?a=308&b=6&rp=0&m=105&transaction_id=b44dd9a1d545a93ce8ec58b5b7f8bf54&sellerid=6000&impid=1142&p=vs&terminal=pc&position=detailPageMiddleRightPromotion&works_id=&block_sign=&video_type=&play_channel=&site=http%3A%2F%2Fv.baidu.com%2Ftv%2F19828.htm%3Ffr%3Dps_twala&online=1&cuid=34ACF2467B38DF9DE1C429A7AE980052&version=0.0.0&device=&os=0&os_version=&locale=&vendor=&model=&screen_width=0&screen_height=0&idfa=&android_id=&mac=&imei=&connection_type=2&operator_type=0&orientation=&dpi=0&d=show&ts_s=1520200797.5305&e=a3118e633d3dcd2453e904fbd5ccf298c78f4db84b76a1403395630469113a48&.stamp=0.606362012874242 HTTP/1.1\" 200 54 \"-\" \"http://v.baidu.com/tv/19828.htm?fr=ps_twala\" \"BAIDUID=34ACF2467B38DF9DE1C429A7AE980052:FG=1; BIDUPSID=34ACF2467B38DF9DE1C429A7AE980052; PSTM=1520171056; PSINO=6; H_PS_PSSID=1420_21117; BDORZ=FFFB88E999055A3F8A630C64834BD6D0\" \"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36\" 0.000 0002035849 192.168.80.53 - yy.videoclick.baidu.com \"58.42.248.202\" 00020358490894478528030506 1520200802.035";
//        AdverLog adverLog = convLog(str);
//        System.out.println(adverLog);
//    }
//    public static void main(String[] args) {
//        Calendar cal =Calendar.getInstance();
//        System.out.println(cal.get(Calendar.MONTH));//+1月
//        System.out.println(cal.getTime());
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(sdf.format(cal.getTime()));
//
//    }
//}
