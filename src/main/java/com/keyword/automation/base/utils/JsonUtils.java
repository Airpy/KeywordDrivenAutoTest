package com.keyword.automation.base.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.*;

/**
 * JSON操作工具类
 *
 * @author Amio_
 */
public class JsonUtils {
    /**
     * 将泛型List对象序列成JSON字符串
     * before: [aaa, bbb, ccc]
     * after: ["aaa","bbb","ccc"]
     *
     * @param list List对象
     * @param <T>  泛型形参
     * @return JSON字符串
     */
    public static <T> String toJsonStringFromList(List<T> list) {
        JSONArray jsonArray = JSONArray.fromObject(list);
        return jsonArray.toString();
    }

    /**
     * 将指定对象序列化成JSON字符串
     * before: Student{username='小明',sex='男',tel=['13999999991','02588888888'],Score{chinese=99,math=100}}
     * after: [{"username":"小明","sex":"男","tel":["13999999991","02588888888"],"score":{"chinese":99,"math":100}}]
     *
     * @param object 指定对象
     * @return JSON字符串
     */
    public static String toJsonStringFromObject(Object object) {
        JSONArray jsonArray = JSONArray.fromObject(object);
        return jsonArray.toString();
    }

    /**
     * 将对象序列化成List数组
     * before: Student{username='小明',sex='男',tel=['13999999991','02588888888'],Score{chinese=99,math=100}}
     * after: [小明, 男, ["13999999991","02588888888"], {"chinese":99,"math":100}]
     *
     * @param object 指定对象
     * @return List数组
     */
    public static List toListFromObject(Object object) {
        List<Object> list = new ArrayList<>();
        JSONArray jsonArray = JSONArray.fromObject(object);
        for (Object aJsonArray : jsonArray) {
            JSONObject jsonObject = (JSONObject) aJsonArray;
            Iterator keys = jsonObject.keys();
            while (keys.hasNext()) {
                Object key = keys.next();
                Object value = jsonObject.get(key);
                list.add(value);
            }
        }
        return list;
    }

    public static List toListFromJson(JSONArray jsonArray) {
        List<Object> list = new ArrayList<>();
        for (Object obj : jsonArray) {
            if (obj instanceof JSONArray) {
                list.add(toListFromJson((JSONArray) obj));
            } else if (obj instanceof JSONObject) {
                list.add(toMapFromJson((JSONObject) obj));
            } else {
                list.add(obj);
            }
        }
        return list;
    }


    public static Map<String, Object> toMapFromJson(String json) {
        JSONObject obj = JSONObject.fromObject(json);
        return toMapFromJson(obj);
    }


    public static Map<String, Object> toMapFromJson(JSONObject obj) {
        Set<?> set = obj.keySet();
        Map<String, Object> map = new HashMap<>(set.size());
        for (Object key : obj.keySet()) {
            Object value = obj.get(key);
            if (value instanceof JSONArray) {
                map.put(key.toString(), toListFromJson((JSONArray) value));
            } else if (value instanceof JSONObject) {
                map.put(key.toString(), toMapFromJson((JSONObject) value));
            } else {
                map.put(key.toString(), obj.get(key));
            }

        }
        return map;
    }

    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        JSONObject subJSON = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        jsonObject.put("username", "小明");
        jsonObject.put("sex", "男");
        jsonArray.add("13999999991");
        jsonArray.add("02588888888");
        jsonObject.put("tel", jsonArray);
        subJSON.put("chinese", 99);
        subJSON.put("math", 100);
        jsonObject.put("score", subJSON);

        JSONArray jsonArray1 = new JSONArray();
        jsonArray1.add(jsonObject);
        System.out.println(JsonUtils.toListFromJson(jsonArray1));
    }
}
