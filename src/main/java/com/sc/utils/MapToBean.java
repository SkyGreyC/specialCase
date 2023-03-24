package com.sc.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.Collections;
import java.util.List;

public class MapToBean {

    /*
     * 将Map类型转换成T类型
     * */
    public static <T> T toBean(Object object, Class<T> clazz){
        if(null == object){
            return null;
        }
        String jsonString = JSONObject.toJSONString(object);
        return (T)JSONObject.parseObject(jsonString, clazz);
    }

    /*
     * 将Map类型转换成List<T>类型
     * */
    public static <T> List<T> toList(Object object, Class<T> clazz){
        if(null == object){
            return Collections.emptyList();
        }
        String jsonString = JSONObject.toJSONString(object);
        return JSONObject.parseArray(jsonString, clazz);
    }

}
