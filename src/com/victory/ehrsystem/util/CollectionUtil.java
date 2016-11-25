package com.victory.ehrsystem.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义集合工具类
 *
 * @author ajkx_Du
 * @create 2016-11-23 10:11
 */
public class CollectionUtil {

    public static List getObjectFields(Class clazz){
        Field[] fs = clazz.getDeclaredFields();
        List<String> list = new ArrayList<>();
        for (int i = 0;i < fs.length;i++) {
            Field f = fs[i];
            f.setAccessible(true);
            if(f.getName().equals("id")){
                continue;
            }
            list.add(f.getName());
        }
        return list;
    }
}
