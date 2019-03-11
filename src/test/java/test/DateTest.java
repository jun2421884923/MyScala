package test;

import entity.Student;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DateTest {
    @Test
    public  void testSplit(){
        Pattern p=Pattern.compile("\\d+");
        String[] strarr=p.split("我的QQ是:456456我的电话是:0532214我的邮箱是:aaa@aaa.com");
        ArrayList< String> arrayList = new ArrayList<String>(strarr.length);
        Collections.addAll(arrayList, strarr);
        arrayList.stream().forEach(System.out::println);

    }
    @Test
    public void testpRep(){
        String str="61.135.168.22 - - [25/Feb/2018:10:00:02 +0800] \"GET /adver/?a=53&b=adx_1046&rp=17&m=adx_1046_nafb&transaction_id=304f3fb927d354c51bf590a4deb635d0&sellerid=6000&impid=1054&p=dsp&terminal=iphnative&position=shortVideoDetailFeed&works_id=&block_sign=search&video_type=&play_channel=&site=baishi.baidu.com&online=1&cuid=02%3A00%3A00%3A00%3A00%3A00_039acbe955b5a3031ca66fcfc1607f46a0352ae0&version=7.9.3&device=iphone&os=2&os_version=10.3.3&locale=zh_CN&vendor=APPLE&model=iPhone7%2C2&screen_width=750&screen_height=1334&idfa=48AB89E1-4E0C-46CF-868D-45D2C920CD34&android_id=&mac=&imei=&connection_type=2&operator_type=0&orientation=p&dpi=0&d=start&ts_s=1519524001.7719&e=24ddfda96e65ffc9eaa5caa47521e06461c1fd3251d280395907f42e4ae3f9ea HTTP/1.1\" 200 54 \"-\" \"-\" \"BAIDUID=22232326C4B772DD9ABE61D45B2695E0:FG=1\" \"VideoiPhone\" 0.000 0002374443 192.168.80.53 - yy.videoclick.baidu.com \"223.73.237.5\" 00023744430894478528022510 1519524002.374";


    }
    @Before
    public void init() {

    }



    //1列出班上超过85分的学生姓名，并按照分数降序输出用户名字
    @Test
    public void test1() {
        Random random = new Random();

        ArrayList<Student> stuList = new ArrayList<Student>() {
            {
                for (int i = 0; i < 100; i++) {
                    add(new Student("student" + i, random.nextInt(50) + 50));
                }
            }
        };
        List<String> studentList = stuList.stream()
                .filter(x->x.getScore()>85)
                .sorted(Comparator.comparing(Student::getScore).reversed())
                .map(Student::getName)
                .collect(Collectors.toList());
        System.out.println(studentList);
    }
    public static void main(String[] args) {
        Calendar cal =Calendar.getInstance();
        System.out.println(cal.get(Calendar.MONTH));//+1月
        System.out.println(cal.getTime());
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(cal.getTime()));

    }
}
