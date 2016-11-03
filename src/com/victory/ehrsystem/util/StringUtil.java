package com.victory.ehrsystem.util;

/**
 * 字符串工具类
 *
 * @author ajkx_Du
 * @create 2016-10-21 16:12
 */
public class StringUtil {

    public static int[] getIds(String str) {
        String[] temp = str.split(",");
        int[] tempint = new int[temp.length];
        for (int i = 0; i < temp.length; i++) {
            tempint[i] = Integer.parseInt(temp[i]);
        }
        return tempint;
    }

    public static boolean isEmpty(Object str) {
        return str == null || "".equals(str);
    }
}
