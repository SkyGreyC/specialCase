package com.sc.utils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;

public class CommonUtils {

    /*
     * 获取32位随机码
     * */
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

    /*
     * 是否为空
     * */
    public static Boolean isNull(Object value){
        if(value == null){
            return true;
        }else if(value instanceof String){
            if(((String)value).trim().replaceAll("\\s","").equals("")){
                return true;
            }
        }else if(value instanceof Collection){
            if(((Collection)value).isEmpty()){
                return true;
            }
        }else if(value.getClass().isArray()){
            if(Array.getLength(value)==0){
                return true;
            }
        }else if(value instanceof Map){
            if(((Map)value).isEmpty()){
                return true;
            }
        }else{
            return false;
        }
        return false;
    }

    /*
     * 是否多个参数是否存在空值
     * */
    public static Boolean isAnyNull(Object value,Object... items){
        if(isNull(value)||isNull(items)){
            return true;
        }
        for(Object item:items){
            if(isNull(item)){
                return true;
            }
        }
        return false;
    }

    /*
     * 判断不为null
     * */
    public static Boolean isNotNull(Object value){
        return !isNull(value);
    }

}
