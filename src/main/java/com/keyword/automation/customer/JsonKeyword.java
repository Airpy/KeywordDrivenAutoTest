package com.keyword.automation.customer;

import com.keyword.automation.base.common.Constants;
import com.keyword.automation.base.utils.JsonUtils;
import com.keyword.automation.bean.BillWhole;
import com.keyword.automation.database.domain.Goods;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 自定义Json操作关键字
 *
 * @author Amio_
 */
public class JsonKeyword {
    private JsonKeyword() {
    }

    /**
     * 将指定JSON文件转换为map对象
     *
     * @param jsonPath 指定JSON文件
     * @return map对象
     */
    public static Map<String, Object> convertToMapFromJsonFile(String jsonPath) {
        String jsonString = readJsonFile(jsonPath);
        return JsonUtils.toMapFromJson(jsonString);
    }

    /**
     * 从初始化数据中取出对应测试用例场景的数据并转化为对应Java对象
     *
     * @param initData     初始化数据
     * @param testCaseName 测试用例场景key
     * @return java对象
     */
    public static Object convertToJavaBeanObject(Map<String, Object> initData, String testCaseName) {
        Object obj = initData.get(testCaseName);
        Set set = ((HashMap) obj).keySet();
        String classKey = null;
        String classValue = null;
        for (Object key : set) {
            String tempKey = (String) key;
            if (!tempKey.equalsIgnoreCase("result")) {
                classKey = tempKey;
                classValue = JsonUtils.toJsonStringFromObject(((HashMap) obj).get(classKey));
            }
        }
        if (classKey != null) {
            if (classKey.equalsIgnoreCase("billWhole")) {
                return JsonUtils.toBeanFromJsonString(classValue, BillWhole.class);
            } else if (classKey.equalsIgnoreCase("goods")) {
                return JsonUtils.toBeanFromJsonString(classValue, Goods.class);
            } else {
                throw new RuntimeException("没有找到对应的Java Bean.");
            }
        } else {
            throw new RuntimeException("没有找到对应的Java Bean.");
        }
    }

    /**
     * 读取指定JSON文件
     *
     * @param jsonFilePath JSON文件路径,格式:目录/文件名.例如:bill/销售单.json
     * @return JSON字符串
     */
    private static String readJsonFile(String jsonFilePath) {
        String path = System.getProperty("user.dir") + Constants.JSON_FILE_PATH + jsonFilePath;
        BufferedReader reader = null;
        String lastStr = "";
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "utf-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString;
            while ((tempString = reader.readLine()) != null) {
                lastStr += tempString;
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return lastStr;
    }

    public static void main(String[] args) {
        String jsonString = "{\n" +
                "      \"billHeader\": {\n" +
                "        \"consumerName\": \"测试客户\",\n" +
                "        \"supplierName\": null,\n" +
                "        \"warehouseName\": \"测试仓库\",\n" +
                "        \"workOperName\": \"刘振峰\",\n" +
                "        \"workTime\": null,\n" +
                "        \"isBaseUnit\": false,\n" +
                "        \"billRemark\": \"这是一个单据备注\"\n" +
                "      },\n" +
                "      \"billCellList\": [\n" +
                "        {\n" +
                "          \"goodsName\": \"商品档案A\",\n" +
                "          \"currUnitName\": \"瓶\",\n" +
                "          \"quantity\": \"10\",\n" +
                "          \"realPrice\": 8.8,\n" +
                "          \"subAmount\": 88,\n" +
                "          \"remark\": \"这是商品A小包单位备注\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"goodsName\": \"商品档案A\",\n" +
                "          \"currUnitName\": \"箱\",\n" +
                "          \"quantity\": \"10\",\n" +
                "          \"realPrice\": 88.8,\n" +
                "          \"subAmount\": 888,\n" +
                "          \"remark\": \"这是商品A大包单位备注\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"goodsName\": \"商品档案B\",\n" +
                "          \"currUnitName\": \"瓶\",\n" +
                "          \"quantity\": \"10\",\n" +
                "          \"realPrice\": 9.9,\n" +
                "          \"subAmount\": 99,\n" +
                "          \"remark\": \"这是商品B大包单位备注\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"billFooter\": {\n" +
                "        \"discountAmount\": 1.10,\n" +
                "        \"cash\": 2.2,\n" +
                "        \"bank\": 0,\n" +
                "        \"prePay\": 3.3,\n" +
                "        \"preCharge\": 0,\n" +
                "        \"others\": 0\n" +
                "      }\n" +
                "    }";
        Object object = JsonUtils.toBeanFromJsonString(jsonString, BillWhole.class);
        System.out.println(111);
        System.out.println(((BillWhole) object).getBillCellList().get(0).getGoodsName());
    }
}
