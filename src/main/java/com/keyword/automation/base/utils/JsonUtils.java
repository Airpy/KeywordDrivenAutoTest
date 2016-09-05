package com.keyword.automation.base.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
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
}
