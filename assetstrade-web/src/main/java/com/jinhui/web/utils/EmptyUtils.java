package com.jinhui.web.utils;

/**
 * Created by luozl on 2017/3/6.
 */
public class EmptyUtils {
    public static boolean isEmpty(String str) {
        return str==null||str.isEmpty();
    }
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
}
