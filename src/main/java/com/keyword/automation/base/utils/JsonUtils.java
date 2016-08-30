package com.keyword.automation.base.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JSON操作工具类
 *
 * @author Amio_
 */
public class JsonUtils {
    /**
     * 将泛型List对象虚列成JSON字符串
     *
     * @param list List对象
     * @param <T>  泛型形参
     * @return JSON字符串
     */
    public static <T> String toJson(List<T> list) {
        JSONArray jsonArray = JSONArray.fromObject(list);
        return jsonArray.toString();
    }

    /**
     * 将指定对象序列化成JSON字符串
     *
     * @param object 指定对象
     * @return JSON字符串
     */
    public static String toJson(Object object) {
        JSONObject jsonObject = JSONObject.fromObject(object);
        return jsonObject.toString();
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        System.out.println(list.toString());
        String temp = JsonUtils.toJson(list);
        System.out.println(temp);
    }
}
