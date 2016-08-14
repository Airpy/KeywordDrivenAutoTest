package com.keyword.automation.base.utils;

import java.io.*;
import java.util.Enumeration;
import java.util.Properties;

/**
 * 读写Properties配置文件工具类
 *
 * @author Amio_
 */
public class PropsUtils {
    private PropsUtils() {

    }
    /**
     * 根据key获取value
     *
     * @param propsFilePath properties文件路径(绝对路径)
     * @param key           属性的key
     * @return 属性的value
     */
    public static String getValueByKey(String propsFilePath, String key) {
        Properties pps = new Properties();
        try {
            InputStream inputStream = new FileInputStream(propsFilePath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            pps.load(bufferedReader);
            String value = pps.getProperty(key);
            System.out.println(key + "=" + value);
            inputStream.close();
            return value;
        } catch (IOException e) {
            LogUtils.error("读取Propertis文件失败,失败原因为:" + e.getMessage());
            return null;
        }
    }

    /**
     * 获取properties文件所有属性的key跟value
     *
     * @param propsFilePath properties文件路径(绝对路径)
     */
    public static void getAllProperties(String propsFilePath) {
        try {
            Properties pps = new Properties();
            InputStream in = new BufferedInputStream(new FileInputStream(propsFilePath));
            pps.load(in);
            Enumeration en = pps.propertyNames();
            while (en.hasMoreElements()) {
                String strKey = (String) en.nextElement();
                String strValue = pps.getProperty(strKey);
                System.out.println(strKey + "=" + strValue);
            }
        } catch (IOException e) {
            LogUtils.error("读取Propertis文件失败,失败原因为:" + e.getMessage());
        }
    }

    /**
     * 在properties文件写入属性的key跟value
     *
     * @param propsFilePath properties文件路径(绝对路径)
     * @param pKey          写入属性的key
     * @param pValue        写入属性的value
     */
    public static void writeProperties(String propsFilePath, String pKey, String pValue) {
        try {
            Properties pps = new Properties();
            OutputStream out = new FileOutputStream(propsFilePath);
            pps.setProperty(pKey, pValue);
            pps.store(out, "更新key:[" + pKey + "],value:[" + pValue + "].");
            out.close();
        } catch (IOException e) {
            LogUtils.error("写入Propertis文件失败,失败原因为:" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String propsFilePath = System.getProperty("user.dir") + "/src/main/resources/static/data/goods.properties";
        System.out.println(getValueByKey(propsFilePath,"goods.firstGoodsType.brand"));
    }
}
