package com.keyword.automation.base.utils;

import com.keyword.automation.bean.BillCell;
import com.keyword.automation.bean.BillFooter;
import com.keyword.automation.bean.BillHeader;
import com.keyword.automation.bean.BillWhole;
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
        JSONObject jsonObject = JSONObject.fromObject(object);
        return jsonObject.toString();
//        JSONArray jsonArray = JSONArray.fromObject(object);
//        return jsonArray.toString();
    }

    /**
     * 将对象序列化成List
     * before: Student{username='小明',sex='男',tel=['13999999991','02588888888'],Score{chinese=99,math=100}}
     * after: [小明, 男, ["13999999991","02588888888"], {"chinese":99,"math":100}]
     *
     * @param object 指定对象
     * @return List
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

    /**
     * 将JSON对象数组序列化List
     * before: [{"username":"小明","sex":"男","tel":["13999999991","02588888888"],"score":{"chinese":99,"math":100}}]
     * after: [{score={math=100, chinese=99}, sex=男, username=小明, tel=[13999999991, 02588888888]}]
     *
     * @param jsonArray JSON对象数组
     * @return List
     */
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


    /**
     * 将JSON字符串序列化Map
     * before: {"username":"小明","sex":"男","tel":["13999999991","02588888888"],"score":{"chinese":99,"math":100}}
     * after: {score={math=100, chinese=99}, sex=男, username=小明, tel=[13999999991, 02588888888]}
     *
     * @param json JSON字符串
     * @return Map
     */
    public static Map<String, Object> toMapFromJson(String json) {
        JSONObject obj = JSONObject.fromObject(json);
        return toMapFromJson(obj);
    }


    /**
     * 将JSON对象序列化Map
     * before: {"username":"小明","sex":"男","tel":["13999999991","02588888888"],"score":{"chinese":99,"math":100}}
     * after: {score={math=100, chinese=99}, sex=男, username=小明, tel=[13999999991, 02588888888]}
     *
     * @param obj JSON对象
     * @return Map
     */
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

    /**
     * 将JSON字符串转化为java对象
     * before: {"username":"小明","sex":"男","tel":["13999999991","02588888888"],"score":{"chinese":99,"math":100}}
     * after: Student{username='小明',sex='男',tel=['13999999991','02588888888'],Score{chinese=99,math=100}}
     *
     * @param jsonString JSON字符串
     * @param clazz      java类
     * @return java对象
     */
    public static Object toBeanFromJsonString(String jsonString, Class clazz) {
        JSONObject jsonObject = null;
        try {
            jsonObject = JSONObject.fromObject(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.toBean(jsonObject, clazz);
    }

    /**
     * 将JSON对象转化为java对象
     * before: {"username":"小明","sex":"男","tel":["13999999991","02588888888"],"score":{"chinese":99,"math":100}}
     * after: Student{username='小明',sex='男',tel=['13999999991','02588888888'],Score{chinese=99,math=100}}
     *
     * @param jsonObject JSON对象
     * @param clazz      java类
     * @return java对象
     */
    public static Object toBeanFromJsonString(JSONObject jsonObject, Class clazz) {
        return JSONObject.toBean(jsonObject, clazz);
    }

//    public static void main(String[] args) {
//        JSONObject jsonObject = new JSONObject();
//        JSONObject subJSON = new JSONObject();
//        JSONArray jsonArray = new JSONArray();
//        jsonObject.put("username", "小明");
//        jsonObject.put("sex", "男");
//        jsonArray.add("13999999991");
//        jsonArray.add("02588888888");
//        jsonObject.put("tel", jsonArray);
//        subJSON.put("chinese", 99);
//        subJSON.put("math", 100);
//        jsonObject.put("score", subJSON);
//
//        JSONArray jsonArray1 = new JSONArray();
//        jsonArray1.add(jsonObject);
//        System.out.println(JsonUtils.toListFromJson(jsonArray1));
//
//        String jsonStr = "{\"username\":\"小明\",\"sex\":\"男\",\"tel\":[\"13999999991\",\"02588888888\"]," +
//                "\"score\":{\"chinese\":99,\"math\":100}}";
//        System.out.println(JsonUtils.toMapFromJson(jsonStr));


//        List<BillCell> billCellList = new ArrayList<>();
//        BillHeader billHeader = new BillHeader("测试客户", null, "测试仓库", "刘振峰", null, false, "这是一个单据备注");
//        BillCell billCellA_BASE = new BillCell("商品档案A", "瓶", "10", 8.80, 88.00, "这是商品A小包单位备注");
//        BillCell billCellA_PKG = new BillCell("商品档案A", "箱", "10", 88.80, 888.00, "这是商品A大包单位备注");
//        BillCell billCellB_BASE = new BillCell("商品档案B", "瓶", "10", 9.90, 99.00, "这是商品B小包单位备注");
//        BillFooter billFooter = new BillFooter(1.10, 2.20, 0.0, 3.30, 0.0, 0.0);
//
//        billCellList.add(billCellA_BASE);
//        billCellList.add(billCellA_PKG);
//        billCellList.add(billCellB_BASE);
//
//        BillWhole billWhole = new BillWhole(billHeader, billCellList, billFooter);
//        String jsonString = JsonUtils.toJsonStringFromObject(billWhole);
//        System.out.println(jsonString);

//        String jsonString = "{\"billCellList\":[{\"currUnitName\":\"瓶\",\"goodsName\":\"商品档案A\",\"quantity\":\"10\"," +
//                "\"realPrice\":8.8,\"remark\":\"这是商品A小包单位备注\",\"subAmount\":88},{\"currUnitName\":\"箱\"," +
//                "\"goodsName\":\"商品档案A\",\"quantity\":\"10\",\"realPrice\":88.8,\"remark\":\"这是商品A大包单位备注\"," +
//                "\"subAmount\":888},{\"currUnitName\":\"瓶\",\"goodsName\":\"商品档案B\",\"quantity\":\"10\"," +
//                "\"realPrice\":9.9,\"remark\":\"这是商品B大包单位备注\",\"subAmount\":99}],\"billFooter\":{\"bank\":0," +
//                "\"cash\":2.2,\"others\":0,\"preCharge\":0,\"prePay\":3.3}," +
//                "\"billHeader\":{\"billRemark\":\"这是一个单据备注\",\"consumerName\":\"测试客户\",\"isBaseUnit\":false," +
//                "\"supplierName\":\"\",\"warehouseName\":\"测试仓库\",\"workOperName\":\"刘振峰\"}}";
//        Object object = JsonUtils.toBeanFromJsonString(jsonString, BillWhole.class);
//        System.out.println(((BillWhole) object).getBillHeader().getWarehouseName());
//    }
}
