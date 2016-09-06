package com.keyword.automation.base.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * JSON操作工具类
 *
 * @author Amio_
 */
public class JSONUtils {
    /**
     * 将JSON字符串序列化成JSONObject对象或JSONArray对象
     *
     * @param jsonString JSON字符串
     * @return JSONObject对象或JSONArray对象
     */
    public static Object toObjectFromJSONString(String jsonString) {
        Object object = null;
        try {
            object = JSON.parse(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    /**
     * 将byte数组序列化成JSONObject对象或JSONArray对象
     *
     * @param bytes byte数组
     * @return JSONObject对象或JSONArray对象
     */
    public static Object toObjectFromByteArray(byte[] bytes) {
        Object object = null;
        try {
            object = JSON.parse(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    /**
     * 将JSON字符串序列化成JSONObject对象
     *
     * @param jsonString JSON字符串
     * @return JSONObject对象
     */
    public static JSONObject toJSONObjectFromJSONString(String jsonString) {
        JSONObject jsonObject = null;
        try {
            jsonObject = JSON.parseObject(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 将JSON字符串序列化成JSONArray对象
     *
     * @param jsonString JSON字符串(以"["开头，如"[{"aaa":"bbb"}]")
     * @return JSONArray对象
     */
    public static JSONArray toJSONArrayFromJSONString(String jsonString) {
        JSONArray jsonArray = null;
        try {
            jsonArray = JSON.parseArray(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    /**
     * 将JSON字符串反序列化成JavaBean
     *
     * @param jsonString JSON字符串
     * @param clazz      Java类
     * @return 对应类JavaBean
     */
    public static <T> T toBeanFromJSONString(String jsonString, Class<T> clazz) {
        T t = null;
        try {
            t = JSON.parseObject(jsonString, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 将JSON字符串反序列化成JavaBean集合
     *
     * @param jsonString JSON字符串
     * @param clazz      Java类
     * @return 对应类JavaBean集合
     */
    public static <T> List<T> toListBeanFromJSONString(String jsonString, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        try {
            list = JSON.parseArray(jsonString, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 将JSON字符串序列化成MAP集合
     *
     * @param jsonString JSON字符串(以"["开头，如"[{"aaa":"bbb"}]")
     * @return MAP集合
     */
    public static List<Map<String, Object>> toListMapFromJSONString(String jsonString) {
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            list = JSON.parseObject(jsonString, new TypeReference<List<Map<String, Object>>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 从输入流中反序列化成JavaBean
     *
     * @param is    输入流
     * @param clazz Java类
     * @return 对应类JavaBean
     */
    public static <T> T toJavaBeanFromJSONFile(InputStream is, Type clazz) {
        T t = null;
        try {
            t = JSON.parseObject(is, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 将对象(JSONArray对象、JSONObject对象、JavaBean对象)格式化成JSON字符串
     *
     * @param object JSONArray对象、JSONObject对象、JavaBean对象
     * @return JSON字符串
     */
    public static String toJSONString(Object object) {
        String jsonString = null;
        try {
            jsonString = JSON.toJSONString(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    /**
     * 将对象(JSONArray对象、JSONObject对象、JavaBean对象)格式化成JSON字符串(带格式化)
     *
     * @param object       JSONArray对象、JSONObject对象、JavaBean对象
     * @param prettyFormat 是否格式化，格式化则为True，否则为False
     * @return JSON字符串
     */
    public static String toJSONString(Object object, boolean prettyFormat) {
        String jsonString = null;
        try {
            jsonString = JSON.toJSONString(object, prettyFormat);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    /**
     * 将JavaBean格式化成JSONObject对象或JSONArray对象
     *
     * @param javaObject JavaBean
     * @return JSONObject或JSONArray
     */
    public static Object toJSON(Object javaObject) {
        Object object = null;
        try {
            object = JSON.toJSON(javaObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }
}
