package com.keyword.automation.customer;

import com.alibaba.fastjson.JSONObject;
import com.keyword.automation.base.common.Constants;
import com.keyword.automation.base.utils.JSONUtils;
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

    public static Map<String, Map<String, Object>> initData(String jsonFilePath) {
        Map<String, Map<String, Object>> map = new HashMap<>();
        Map<String, Object> dataMap = new HashMap<>();
        String jsonString = readJsonFile(jsonFilePath);
        JSONObject jsonObject = JSONUtils.toJSONObjectFromJSONString(jsonString);
        Set<String> keys = jsonObject.keySet();
        for (String key : keys) {
            JSONObject testData = (JSONObject) jsonObject.get(key);
            Set<String> dataKeys = testData.keySet();
            for (String dataKey : dataKeys) {
                if (!dataKey.equalsIgnoreCase("result")) {
                    if (dataKey.equalsIgnoreCase("BillWhole")) {
                        dataMap.put(dataKey, JSONUtils.toBeanFromJSONString(testData.get(dataKey).toString(),
                                BillWhole.class));
                    } else {
                        dataMap.put(dataKey, JSONUtils.toBeanFromJSONString(testData.get(dataKey).toString(), Goods
                                .class));
                    }
                } else {
                    dataMap.put(dataKey, testData.get(dataKey));
                }
            }
            map.put(key, dataMap);
        }
        return map;
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
//        String jsonString = "{\n" +
//                "      \"billHeader\": {\n" +
//                "        \"consumerName\": \"测试客户\",\n" +
//                "        \"supplierName\": null,\n" +
//                "        \"warehouseName\": \"测试仓库\",\n" +
//                "        \"workOperName\": \"刘振峰\",\n" +
//                "        \"workTime\": null,\n" +
//                "        \"isBaseUnit\": false,\n" +
//                "        \"billRemark\": \"这是一个单据备注\"\n" +
//                "      },\n" +
//                "      \"billCellList\": [\n" +
//                "        {\n" +
//                "          \"goodsName\": \"商品档案A\",\n" +
//                "          \"currUnitName\": \"瓶\",\n" +
//                "          \"quantity\": \"10\",\n" +
//                "          \"realPrice\": 8.8,\n" +
//                "          \"subAmount\": 88,\n" +
//                "          \"remark\": \"这是商品A小包单位备注\"\n" +
//                "        },\n" +
//                "        {\n" +
//                "          \"goodsName\": \"商品档案A\",\n" +
//                "          \"currUnitName\": \"箱\",\n" +
//                "          \"quantity\": \"10\",\n" +
//                "          \"realPrice\": 88.8,\n" +
//                "          \"subAmount\": 888,\n" +
//                "          \"remark\": \"这是商品A大包单位备注\"\n" +
//                "        },\n" +
//                "        {\n" +
//                "          \"goodsName\": \"商品档案B\",\n" +
//                "          \"currUnitName\": \"瓶\",\n" +
//                "          \"quantity\": \"10\",\n" +
//                "          \"realPrice\": 9.9,\n" +
//                "          \"subAmount\": 99,\n" +
//                "          \"remark\": \"这是商品B大包单位备注\"\n" +
//                "        }\n" +
//                "      ],\n" +
//                "      \"billFooter\": {\n" +
//                "        \"discountAmount\": 1.10,\n" +
//                "        \"cash\": 2.2,\n" +
//                "        \"bank\": 0,\n" +
//                "        \"prePay\": 3.3,\n" +
//                "        \"preCharge\": 0,\n" +
//                "        \"others\": 0\n" +
//                "      }\n" +
//                "    }";
        Map<String, Map<String, Object>> map = JsonKeyword.initData("bill/销售单.json");
        BillWhole billWhole = (BillWhole) map.get("addSaleOrder_002").get("billWhole");
        System.out.println(billWhole.getBillCellList().get(0).getGoodsName());
    }
}
