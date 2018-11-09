package com.keizyi.smartqq.kit;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Alex_
 * Date: 2018/11/9
 * Time: 11:25
 */
public class JsonKit {
    private static final SerializerFeature[] FEATURES = {
            // 输出空置字段
            SerializerFeature.WriteMapNullValue,
            // list字段如果为null，输出为[]，而不是null
            SerializerFeature.WriteNullListAsEmpty,
            // 数值字段如果为null，输出为0，而不是null
            SerializerFeature.WriteNullNumberAsZero,
            // Boolean字段如果为null，输出为false，而不是null
            SerializerFeature.WriteNullBooleanAsFalse,
            // 字符类型字段如果为null，输出为""，而不是null
            SerializerFeature.WriteNullStringAsEmpty
    };

    private static SerializeConfig CONFIG = null;

    static {
        CONFIG = new SerializeConfig();
        // 使用和json-lib兼容的日期输出格式
        CONFIG.put(java.util.Date.class, new JSONLibDataFormatSerializer());
        CONFIG.put(java.sql.Date.class, new JSONLibDataFormatSerializer());
    }

    /**
     * Object to string
     *
     * @param object
     * @return
     */
    public static String toFeatureJson(Object object) {
        return JSON.toJSONString(object, CONFIG, FEATURES);
    }

    /**
     * obj to String
     *
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        return JSON.toJSONString(object, CONFIG);
    }

    public static Object parse(String jsonString) {
        return JSON.parse(jsonString);
    }

    /**
     * json String to map
     *
     * @param s
     * @return
     */
    public static Map stringToMap(String s) {
        Map m = JSONObject.parseObject(s);
        return m;
    }

    /**
     * map to String
     *
     * @param m
     * @return
     */
    public static String mapToString(Map m) {
        String s = JSONObject.toJSONString(m);
        return s;
    }

    /**
     * json to JavaBean
     *
     * @param jsonString
     * @param cls
     * @return
     */
    public static <T> T parse(String jsonString, Class<T> cls) {
        T t = null;
        try {
            t = JSON.parseObject(jsonString, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * jsonString to List<JavaBean> or List<String>
     *
     * @param jsonString
     * @param cls
     * @return
     */
    public static <T> List<T> parseList(String jsonString, Class<T> cls) {
        List<T> list = new ArrayList<T>();
        try {
            list = JSON.parseArray(jsonString, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * jsonString to List<JavaBean> or List<String>
     *
     * @param jsonString
     * @return
     */
    public static <T> List<T> parseList(String jsonString) {
        List<T> list = new ArrayList<T>();
        try {
            list = (List<T>) JSON.parseArray(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * jsonString to List<Map<String,Object>>
     *
     * @param jsonString
     * @return
     */
    public static List<Map<String, Object>> parseListMap(String jsonString) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            list = JSON.parseObject(jsonString, new TypeReference<List<Map<String, Object>>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
