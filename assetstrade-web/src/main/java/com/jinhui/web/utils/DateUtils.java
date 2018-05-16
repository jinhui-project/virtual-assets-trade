package com.jinhui.web.utils;

import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @autor wsc
 * @create 2018-01-30 15:24
 */
public class DateUtils {
    private static SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");
    private static SimpleDateFormat yyyy_MM_dd_HH_mm_ss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
    /**
     *  获取自然日
     * @param dateStr
     * @param days 倒推天数
     * @return
     */
    public static String getNatureDateStr(String dateStr,int days) {
        if(EmptyUtils.isEmpty(dateStr)) {
            return "";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String natureDateStr = "";
        try {
            Date date = simpleDateFormat.parse(dateStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE,days);
            natureDateStr = simpleDateFormat.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return natureDateStr;
    }

    /**
     * 格式化日期
     * @param yyyyMMdd
     * @return
     */
    public static String formatDate(String yyyyMMdd){
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyyMMdd").parse(yyyyMMdd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    /**
     * 获取上一个日期
     * @param yyyyMMdd
     * @return
     */
    public static String getPreDate(String yyyyMMdd){
        Calendar calendar = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyyMMdd").parse(yyyyMMdd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(date);
        calendar.add(Calendar.DATE,-1);
        return new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
    }

    /**
     * 获取下一个日期
     * @param yyyyMMdd
     * @return
     */
    public static String getNextDate(String yyyyMMdd){
        Calendar calendar = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyyMMdd").parse(yyyyMMdd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(date);
        calendar.add(Calendar.DATE,1);
        return new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
    }

    /**
     * 获取当前时间的年月日时分秒
     * @return
     */
    public static String getCurrentDatetime(){
        //new日期对象
        Date date = new Date();
        //转换时间输出格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateFormat.format(date);
    }

    /**
     * 获取当前时间的年月日
     * @return
     */
    public static String getCurrentDate(){
        //new日期对象
        Date date = new Date();
        //转换时间输出格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.format(date);
    }

    /**
     * 获取当前时间的时分秒
     * @return
     */
    public static String getCurrentTime(){
        //new日期对象
        Date date = new Date();
        //转换时间输出格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("HHmmss");
        return dateFormat.format(date);
    }

    /**
     *  yyyyMMddHHmmss
     * @param date
     * @return
     */
    public static String formatFull(Date date) {
        if(date == null)    return StringUtils.EMPTY;
        return yyyyMMddHHmmss.format(date);
    }

    /**
     *  yyyyMMddHHmmss
     * @param date
     * @return
     */
    public static String formatYYMMDD(Date date) {
        if(date == null)    return StringUtils.EMPTY;
        return yyyyMMdd.format(date);
    }

    /**
     * 格式化 yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static String formatYMDHMS(Date date) {
        if(date == null)    return StringUtils.EMPTY;
        return yyyy_MM_dd_HH_mm_ss.format(date);
    }

}
