package com.example.yzbkaka.kakaAndroid.utils;

/**
 * Created by yzbkaka on 20-1-1.
 */


import java.text.SimpleDateFormat;

/**
 * 日期工具类
 */
public class DateUtils {

    public static String parseTime(long publishTime){
        long time = (System.currentTimeMillis() - publishTime) / 1000;  //间隔时间,ms
        //天
        int day = (int) (time / 3600 / 24);
        if(day == 1){
            return "1天前";
        }
        if(day > 1){  //可以进行改进
            return new SimpleDateFormat("yyyy-MM-dd").format(publishTime);  //xxxx-xx-xx
        }
        //小时
        int hour = (int)(time / 3600);
        if(hour > 0){
            return String.valueOf(hour) + "小时前";
        }
        //分钟
        int minute = (int)(time / 60);
        if(minute > 0){
            return String.valueOf(minute) + "分钟前";
        }
        //秒
        int second = (int)time;
        if(second >= 0){
            return String.valueOf(second) + "秒前";
        }
        return "";
    }
}
