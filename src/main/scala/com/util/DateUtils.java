package com.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static Date StringToDate(String time) {
        //注意：SimpleDateFormat构造函数的样式与strDate的样式必须相符
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
             //必须捕获异常
        Date date = null;
        try {
             date=simpleDateFormat.parse(time);
        } catch(ParseException px) {
            new RuntimeException("can not parse time,make sure your time format is like 'yyyy-MM-dd'!!! ");
        }
        return date;
    }

    public static String getNowDateTime() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd ");//设置日期格式
        String now = df.format(date);// new Date()为获取当前系统时间
        return now;
    }

    public static Date getDayBeginTime(Date d) {

        try {
            // 通过SimpleDateFormat获得指定日期的零时刻
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd 00:00:00");

            return sdf.parse(sdf.format(d));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


        public static String getWeekBeginTime(Date d) {

            try {
                //得到指定日期d的零时刻
                Date beginDate = getDayBeginTime(d);
                Calendar c = Calendar.getInstance();
                c.setTime(beginDate);

                int day_of_week = c.get(Calendar. DAY_OF_WEEK) - 1;
                if (day_of_week == 0 ) {
                    day_of_week = 7 ;
                }
                c.add(Calendar.DATE , -day_of_week + 1 );
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd ");//设置日期格式
                return df.format(c.getTime());// new Date()为获取当前系统时间
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }



    public static String getWeekEndTime(String weekStartTime) {

        Date startTime = StringToDate(weekStartTime);
        Calendar c = Calendar.getInstance();
        c.setTime(startTime);
        c.add(Calendar.DATE,6);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd ");//设置日期格式
        return df.format(c.getTime());// new Date()为获取当前系统时间

    }

    public static int calcDayOffset(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2) {  //同一年
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {  //闰年
                    timeDistance += 366;
                } else {  //不是闰年

                    timeDistance += 365;
                }
            }
            return timeDistance + (day2 - day1);
        } else { //不同年
            return day2 - day1;
        }
    }


    public static int getWeekOffset(String endTimeStr) {
        Date startTime =StringToDate("2018-10-28");
        Date endTime = StringToDate(endTimeStr);
        Calendar cal = Calendar.getInstance();
        cal.setTime(startTime);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        dayOfWeek = dayOfWeek - 1;
        if (dayOfWeek == 0) dayOfWeek = 7;

        int dayOffset = calcDayOffset(startTime, endTime);

        int weekOffset = dayOffset / 7;
        int a;
        if (dayOffset > 0) {
            a = (dayOffset % 7 + dayOfWeek > 7) ? 1 : 0;
        } else {
            a = (dayOfWeek + dayOffset % 7 < 1) ? -1 : 0;
        }
        weekOffset = weekOffset + a;
        return weekOffset;
    }

    public static Date getYesterday(Date oldDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(oldDate);
        cal.add(Calendar.DATE,-1);
        return  cal.getTime();
    }


    public static Date get2DaysBefore(Date oldDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(oldDate);
        cal.add(Calendar.DATE,-2);
        return  cal.getTime();
    }
    public static Date getMonthBefore(Date oldDate){
        Calendar cal = Calendar.getInstance();
        cal.setTime(oldDate);
        cal.add(Calendar.MONTH,-1);
        return  cal.getTime();
    }

    public static final int daysBetween(String early, String late) {

        DateFormat df = DateFormat.getDateInstance();
        Calendar calst = Calendar.getInstance();
        Calendar caled = Calendar.getInstance();
        try {
            calst.setTime(df.parse(early));
            caled.setTime(df.parse(late));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //设置时间为0时
        calst.set(Calendar.HOUR_OF_DAY, 0);
        calst.set(Calendar.MINUTE, 0);
        calst.set(Calendar.SECOND, 0);
        caled.set(Calendar.HOUR_OF_DAY, 0);
        caled.set(Calendar.MINUTE, 0);
        caled.set(Calendar.SECOND, 0);
        //得到两个日期相差的天数
        int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst
                .getTime().getTime() / 1000)) / 3600 / 24;

        return days;
    }

}
