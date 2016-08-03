package com.keyword.automation.base.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.openqa.selenium.Cookie;

/**
 * 文件工具类
 *
 * @author airpy
 */
public class FileUtils {
    private FileUtils() {

    }

    private static final Logger log = Logger.getLogger(FileUtils.class);

    /**
     * 新建目录(若父目录不存在则一并新建)
     *
     * @param dir 目录(E:\\project\\src\\)
     * @return 创建成功返回true，否则返回false
     */
    public static boolean createDirectory(String dirName) {
        File dirPath = new File(dirName);
        if (null == dirName || dirName.isEmpty()) {
            LogUtils.error("您传入的目录名称为空!");
            return false;
        }
        if (dirPath.exists()) {
            LogUtils.warn("创建目录[" + dirName + "]失败，目标目录已经存在!");
            return true;
        }
        // 如果没有目录分隔符，则加上分隔符
        if (!dirName.endsWith(File.separator)) {
            dirName = dirName + File.separator;
        }
        // 创建目录
        try {
            // 不管父目录是否存在统一创建
            dirPath.mkdirs();
            LogUtils.info("创建目录[" + dirName + "]成功!");
            return true;
        } catch (Exception e) {
            LogUtils.error("创建目录[" + dirName + "]失败: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 创建文件(若文件所在目录不存在则一并创建)
     *
     * @param fileName 包含绝对路径的文件名(E:\project\src\123.txt)
     * @return 创建成功返回true，否则返回false
     */
    public static boolean createFile(String fileName) {
        File file = new File(fileName);
        if (null == fileName || fileName.isEmpty()) {
            LogUtils.error("您传入的文件名称为空!");
            return false;
        }
        if (file.exists() || file.isDirectory() || fileName.endsWith(File.separator)) {
            LogUtils.error("创建文件[" + fileName + "]失败，目标文件已经存在或您输入的为目录!");
            return false;
        }
        // 判断目标文件所在目录是否存在
        if (!file.getParentFile().exists()) {
            LogUtils.warn("目标文件所在目录不存在，现在为您自动创建!");
            if (!file.getParentFile().mkdirs()) {
                LogUtils.error("创建目标文件所在目录失败!");
                return false;
            }
        }
        // 创建目标文件
        try {
            file.createNewFile();
            LogUtils.info("创建文件[" + fileName + "]成功!");
            return true;
        } catch (Exception e) {
            LogUtils.error("创建文件[" + fileName + "]失败: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 新建文件
     *
     * @param fileName 包含绝对路径的文件名(E:\project\src\123.txt)
     * @param content  文件内容
     */
    public static void writeFile(String fileName, String content) {
        try {
            File file = new File(fileName);
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(content);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            bufferedWriter.close();
            fileWriter.close();
            LogUtils.info("写文件[" + fileName + "]操作成功!");
        } catch (Exception e) {
            LogUtils.error("写文件[" + fileName + "]操作失败: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void writeFile(String fileName, Set<Cookie> cookies) {
        try {
            File file = new File(fileName);
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Cookie cookie : cookies) {
                String strCookie = cookie.getName() + ";" + cookie.getValue() + ";" + cookie.getDomain() + ";"
                        + cookie.getPath() + ";" + cookie.getExpiry() + ";" + cookie.isSecure() + ";"
                        + cookie.isHttpOnly();
                bufferedWriter.write(strCookie);
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            bufferedWriter.close();
            fileWriter.close();
            LogUtils.info("写文件[" + fileName + "]操作成功");
        } catch (Exception e) {
            LogUtils.error("写文件[" + fileName + "]操作失败: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @SuppressWarnings("finally")
    public static Set<Cookie> getAllCookiesFromFile(String fileName) {
        Cookie cookie = null;
        Set<Cookie> cookies = new HashSet<Cookie>();
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while (null != (line = bufferedReader.readLine())) {
                StringTokenizer stringTokenizer = new StringTokenizer(line, ";");
                while (stringTokenizer.hasMoreTokens()) {
                    String name = stringTokenizer.nextToken();
                    String value = stringTokenizer.nextToken();
                    String domain = stringTokenizer.nextToken();
                    String path = stringTokenizer.nextToken();
                    Date expiry = null;
                    String date;
                    if (!(date = stringTokenizer.nextToken()).equals("null")) {
                        expiry = new Date(date);
                    }
                    Boolean isSecure = new Boolean(stringTokenizer.nextToken()).booleanValue();
                    Boolean isHttpOnly = new Boolean(stringTokenizer.nextToken()).booleanValue();
                    cookie = new Cookie(name, value, domain, path, expiry, isSecure, isHttpOnly);
                    cookies.add(cookie);
                }
            }
            bufferedReader.close();
            fileReader.close();
            LogUtils.info("读取文件[" + fileName + "]成功!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return cookies;
        }
    }

    /**
     * 删除文件
     *
     * @param fileName 包含绝对路径的文件名(E:\project\src\123.txt)
     */
    public static boolean deleteFile(String fileName) {
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                LogUtils.warn("您输入的文件不存在[" + fileName + "]");
                return false;
            }
            file.delete();
            LogUtils.info("删除文件[" + fileName + "]操作成功!");
            return true;
        } catch (Exception e) {
            LogUtils.error("删除文件[" + fileName + "]操作失败: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public static void deleteAllFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            log.error("传入的参数不存在[" + path + "]");
            return;
        }
        if (!file.isDirectory()) {
            log.error("传入的参数为目录[" + path + "]");
            return;
        }
        String[] childFiles = file.list();
        File temp = null;
        for (String tempChild : childFiles) {
            // File.separator与系统有关的默认名称分隔符
            // 在UNIX系统上，此字段的值为'/'；在Microsoft Windows系统上，它为 '\'
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempChild);
            } else {
                temp = new File(path + File.separator + tempChild);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                deleteAllFile(path + "/" + childFiles);
                deleteFolder(path + "/" + childFiles);
            }
        }
    }

    public static void deleteFolder(String folderPath) {
        try {
            deleteAllFile(folderPath);
        } catch (Exception e) {

        }
    }
}
