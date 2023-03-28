package com.sc.utils;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections.MapUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

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

    /*
     * 从RequestBody中map取出数据转换成对象
     * */
    public static <T> T getObject(Map<String,Object> map, String key, Class<T> clazz){
        Object data = MapUtils.getObject(map,key);
        String jsonString = JSONObject.toJSONString(data);
        return JSONObject.parseObject(jsonString,clazz);
    }

    /*
     * 获取分页信息
     * */
    public static <T> Page<T> getPage(Map<String,Object> map, String key, Class<T> clazz){
        Map<String,Object> data = (Map<String,Object>)MapUtils.getObject(map,key);
        if(null == data||data.isEmpty()){
            return null;
        }
        Page<T> page = new Page<T>();
        page.setSize(MapUtils.getLong(data,"size",page.getSize()));
        page.setCurrent(MapUtils.getLong(data,"current",page.getCurrent()));
        return page;
    }

}
