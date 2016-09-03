package com.keyword.automation.customer;

import com.keyword.automation.base.common.Constants;
import com.keyword.automation.base.utils.JsonUtils;
import com.keyword.automation.bean.BillWhole;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
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

    public static Map<String, Object> convertToMapFromJsonFile(String jsonPath) {
        String jsonString = readJsonFile(jsonPath);
        return JsonUtils.toMapFromJson(jsonString);
    }

    public static Object getTargetTestData(Map<String, Object> initData, String testCaseName) {
        Object obj = initData.get(testCaseName);
        Set set = ((JSONObject) obj).keySet();
        String className = null;
        for (Object key : set) {
            String tempKey = (String) key;
            if (!tempKey.equalsIgnoreCase("result")) {
                className = tempKey;
            }
        }
        if (className != null && className.equalsIgnoreCase("billWhole")) {
            return JsonUtils.toBeanFromJsonString(((JSONObject) obj), BillWhole.class);
        } else {
            return null;
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
//        String jsonString = JsonKeyword.readJsonFile("bill/销售单.json");
//        System.out.println(jsonString);
        Map<String, Object> map = JsonKeyword.convertToMapFromJsonFile("bill/销售单.json");
        Object object = JsonKeyword.getTargetTestData(map, "addSaleOrder_002");
        System.out.println(((BillWhole) object).getBillHeader().getWarehouseName());
    }
}
