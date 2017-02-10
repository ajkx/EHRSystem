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

    public static String nullString(String str) {
        if (str == null) {
            return "";
        }else{
            return str;
        }
    }

    public static String getScript(String topic, String prop, boolean hasPermission) {
        if (hasPermission) {
            return "return \"<a href='javascript:void(0)' onclick=\\\"showEditModal('/"+topic+"/view/\"+"+prop+".id+\"')\\\" class='font-color'>\"+"+prop+".name+\"</a>\";";
        }else{
            return "return \"<a disable='' href='javascript:void(0)' onclick=\\\"showEditModal('/"+topic+"/view/\"+"+prop+".id+\"')\\\" class='font-color'>\"+"+prop+".name+\"</a>\";";
        }
    }

    public static String getMultiScript(String topic, String prop, boolean hasPermission) {
        if (hasPermission) {
            return "var el = '';" +
                    "$("+prop+").each(function(index,element){" +
                    "el += \"<a href='javascript:void(0)' onclick=\\\"showEditModal('/"+topic+"/view/\"+element.id+\"')\\\" class='font-color'>\"+element.name+\"</a>\";});return el;";
        }else{
            return "var el = '';" +
                    "$("+prop+").each(function(index,element){" +
                    "el += \"<a disable='' href='javascript:void(0)' onclick=\\\"showEditModal('/"+topic+"/view/\"+element.id+\"')\\\" class='font-color'>\"+element.name+\"</a>\";});return el;";

        }
    }
}
