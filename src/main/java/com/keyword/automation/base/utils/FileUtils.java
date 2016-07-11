package com.keyword.automation.base.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import org.apache.log4j.Logger;

/**
 * 文件工具类
 * 
 * @author airpy
 *
 */
public class FileUtils {
	private static final Logger log = Logger.getLogger(FileUtils.class);

	/**
	 * 创建目录(若父目录不存在则一并创建)
	 * 
	 * @param dir
	 *            目录
	 */
	public static void mkdir(String dir) {
		try {
			String tempDir = dir;
			File dirPath = new File(tempDir);
			if (!dirPath.exists()) {
				dirPath.mkdirs();
			}
		} catch (Exception e) {
			log.error("创建目录[" + dir + "]操作失败: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 新建文件
	 * 
	 * @param fileName
	 *            包含绝对路径的文件名(E:\project\src\123.txt)
	 * @param content
	 *            文件内容
	 */
	public static void createNewFile(String fileName, String content) {
		try {
			String tempFileName = fileName;
			File filePath = new File(tempFileName);
			if (!filePath.exists()) {
				filePath.createNewFile();
			}
			FileWriter fileWriter = new FileWriter(filePath);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			String strContent = content;
			printWriter.println(strContent);
			printWriter.flush();
			printWriter.close();
			fileWriter.close();
		} catch (Exception e) {
			log.error("新建文件[" + fileName + "]操作失败: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param fileName
	 *            包含绝对路径的文件名(E:\project\src\123.txt)
	 */
	public static void deleteFile(String fileName) {
		try {
			String tempFileName = fileName;
			File deleteFile = new File(tempFileName);
			deleteFile.delete();
		} catch (Exception e) {
			log.error("删除文件[" + fileName + "]操作失败: " + e.getMessage());
			e.printStackTrace();
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
			//File.separator与系统有关的默认名称分隔符
            //在UNIX系统上，此字段的值为'/'；在Microsoft Windows系统上，它为 '\'
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
			// TODO: handle exception
		}
	}
}
