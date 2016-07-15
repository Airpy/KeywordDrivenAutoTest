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
	private FileUtils() {

	}

	private static final Logger log = Logger.getLogger(FileUtils.class);

	/**
	 * 创建文件(若文件所在目录不存在则一并创建)
	 * 
	 * @param fileName
	 *            包含绝对路径的文件名(E:\project\src\123.txt)
	 * @return
	 */
	public static boolean createNewFile(String fileName) {
		File file = new File(fileName);
		if (file.exists()) {
			log.error("创建文件[" + fileName + "]失败，文件已经存在!");
			return false;
		}
		if (fileName.endsWith(File.separator)) {
			log.error("创建文件[" + fileName + "]失败，目标文件不能为目录!");
			return false;
		}
		// 判断目标文件所在目录是否存在
		if (!file.getParentFile().exists()) {
			log.warn("目标文件所在目录不存在，现在为您自动创建!");
			if (!file.getParentFile().mkdirs()) {
				log.error("创建目标文件所在目录失败!");
				return false;
			}
		}
		// 创建目标文件
		try {
			if (file.createNewFile()) {
				log.info("创建文件[" + fileName + "]成功!");
				return true;
			} else {
				log.error("创建文件[" + fileName + "]失败或该目标文件已经存在!");
				return false;
			}
		} catch (Exception e) {
			log.error("创建文件[" + fileName + "]失败: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 新建目录(若父目录不存在则一并新建)
	 * 
	 * @param dir
	 *            目录
	 */
	public static void makeDirctory(String dir) {
		File dirPath = new File(dir);
		File parentPath = dirPath.getParentFile();
		if (null == dir || dir.isEmpty() || dirPath.exists()) {
			log.warn("参数错误或目录已经存在");
			return;
		}
		try {
			if (null != parentPath && !parentPath.exists()) {
				parentPath.mkdirs();
			}
		} catch (Exception e) {
			log.error("新建目录[" + dir + "]操作失败: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// /**
	// * 新建文件
	// *
	// * @param fileName
	// * 包含绝对路径的文件名(E:\project\src\123.txt)
	// */
	// public static void createNewFile(String fileName) {
	// try {
	// File file = new File(fileName);
	// deleteFile(fileName);
	// file.createNewFile();
	// } catch (Exception e) {
	// log.error("新建文件[" + fileName + "]操作失败: " + e.getMessage());
	// e.printStackTrace();
	// }
	// }

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
			File file = new File(fileName);
			deleteFile(fileName);
			file.createNewFile();
			FileWriter fileWriter = new FileWriter(file);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			printWriter.println(content);
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
			File file = new File(fileName);
			if (!file.exists()) {
				log.warn("您输入的文件不存在[" + fileName + "]");
				return;
			}
			file.delete();
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
