package com.jinhui.common.utils;


import java.util.Date;

/**
 * Created by Administrator on 2018/4/11 0011.
 */
public class UnixStampUtils {


    public static long Date2UnixStamp(Date date) {
        return date.getTime() / 1000;
    }


    public static Date unixStamp2Date(long unixStamp) {
        return new Date(unixStamp * 1000);
    }


}
