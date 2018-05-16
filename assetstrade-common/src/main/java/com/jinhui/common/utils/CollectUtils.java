package com.jinhui.common.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2018/4/11 0011.
 */
public class CollectUtils {

    public static String formatList(
            List<String> list, String delimiter, String prefix, String suffix) {

        return list.stream().collect(Collectors.joining(delimiter, prefix, suffix));
    }

    public static String formatList( List<String> list, String delimiter) {

        return list.stream().collect(Collectors.joining(delimiter, "", ""));
    }


    public static void main(String[] args) throws Exception {
        List<String> list = Arrays.asList("a", "b", "c", "d", "e", "f", "g");

        System.out.println("使用 Collectors.joining：");
        String format = formatList(list, ",");
        System.out.println(format);
        String format2 = formatList(list, ",","{","}");
        System.out.println(format2);

    }



}
