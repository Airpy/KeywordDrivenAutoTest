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
public class JSONKeyword {
    private JSONKeyword() {
    }

    /**
     * 解析JSON文件获取测试用例与测试数据MAP键值对
     *
     * @param jsonFilePath JSON文件路径，格式:目录/文件名.例如:bill/销售单.json
     * @return 测试用例与测试数据键值对
     */
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
                        dataMap.put(dataKey, JSONUtils.toBeanFromJSONString(JSONUtils.toJSONString(testData.get
                                (dataKey)), BillWhole.class));
                    } else {
                        dataMap.put(dataKey, JSONUtils.toBeanFromJSONString(JSONUtils.toJSONString(testData.get
                                (dataKey)), Goods.class));
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
}
