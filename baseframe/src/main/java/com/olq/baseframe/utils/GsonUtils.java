package com.olq.baseframe.utils;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Administrator on 2017/1/23.
 */

public class GsonUtils {

    private static Gson gson=new Gson();

    //解析json数据
    public static <T> T getBeanFromJson(String json, Type type){
        return gson.fromJson(json, type);
    }

    public static <T> T getBeanFromJson(String json, Class<T> clazz){
        return gson.fromJson(json, clazz);
    }

    //对象转换成json数据
    public static String getBeanToJson(Object json)
    {
        return gson.toJson(json);
    }

    public static String listToString(List<String> list){
        if(list==null){
            return null;
        }
        StringBuilder result = new StringBuilder();
        boolean first = true;
        //第一个前面不拼接","
        for(String string :list) {
            if(first) {
                first=false;
            }else{
                result.append(",");
            }
            result.append(string);
        }
        return result.toString();
    }

}
