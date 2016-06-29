package com.keyword.automation.base.utils;

import org.apache.log4j.Logger;

/**
 * @ClassName: LogUtils
 *
 * @DESCR:
 *
 * 		对log4j的 Logger 进行封装，使不级别的日志，记录在相应的文件<br/>
 *         在需要要使的代码中直接写：<br/>
 *         Log.info("需要记录的信息");即可<br/>
 *
 *         代码原理说明：<br/>
 *         通过异常类获取当前栈信息，再从栈信息中取到调用日志打印的类及该类中的方法。<br/>
 *         异常处理的开销应该是比较大<br/>
 *         每个线程都有自己的堆栈，java 有 java.lang.StackTraceElement 保存堆栈信息。<br/>
 *         java 出Exception的时候， 一系列的堆栈信息被保存在 java.lang.StackTraceElement 组中，<br/>
 *         可以通过 StackTraceElement[] stes = new Exception().getStackTrace();进行获取，
 *         <br/>
 *         stes[0]... stes[n]是线程的镶嵌调用，从里到的关系。<br/>
 *         这样可以得到每层的堆栈信息（如：类名，方法名，行数等）。<br/>
 *         当系统抛出Exception的时候，总是打印出一串的信息，<br/>
 *         告诉我们Exception发生的位置，和一层一层的调用关系（ stes[0]... stes[n]）。就是通过这种方式实现的。 <br/>
 *         同理Java程序中使用日志功能（JDK Log或者Log4J）的时候，会发现Log系统会自动帮我们打印出丰富的信息，<br/>
 *         其中的[运行时间] [当前类名] [方法名]也是通过这种方法获取的<br/>
 * 
 * @author airpy
 * @date 2016年06月29日
 *
 */
public class LogUtils {
	// 使有在 log4j.properties 定义的logger 进行日志的输出
	private static final Logger LOG_DEBUG = Logger.getLogger("LOGDEBUG");
	private static final Logger LOG_INFO = Logger.getLogger("LOGINFO");
	private static final Logger LOG_WARN = Logger.getLogger("LOGWARN");
	private static final Logger LOG_ERROR = Logger.getLogger("LOGERROR");
	private static final Logger LOG_FATAL = Logger.getLogger("LOGFATAL");

	/**
	 *
	 *
	 * @MethodName: getMessage
	 * @DESCR:
	 *
	 * 		getMessage 方法本身占用一层:0;<br/>
	 *         info 方法调用 getMessage 时，又占用一层：1;<br/>
	 *         所使用Log.info方法的类，占用的是第二：2;<br/>
	 *         提取第二层的 StackTrace 信息是对我有用的。<br/>
	 *
	 * @param @param
	 *            message
	 * @param @return
	 * @return String
	 * @throws @author
	 * @date 2016年06月29日
	 */
	private static String getMessage(Object message) {
		// String prodir = System.getProperty("user.dir") +
		// "/src/main/resources/log4j.properties";
		// PropertyConfigurator.configure(prodir);
		StackTraceElement[] stes = new Exception().getStackTrace();
		if (stes.length < 3) {
			LogUtils.error("Cannot log:" + message);
			return null;
		} else {
			StackTraceElement target = stes[2];

			String prefix = "[" + target.getFileName() + ":" + target.getLineNumber() + "] : ";
			return prefix + message;
		}
	}

	// 对各级别的输出信息进行封装
	// debug
	public static void debug(Object message) {
		LOG_DEBUG.debug(getMessage(message));
	}

	public static void debug(Throwable throwable) {
		LOG_DEBUG.debug(getMessage(throwable), throwable);
	}

	public static void debug(Object message, Throwable throwable) {
		LOG_DEBUG.debug(getMessage(message), throwable);
	}

	// info
	public static void info(Object message) {
		LOG_INFO.info(getMessage(message));
	}

	public static void info(Throwable throwable) {
		LOG_INFO.info(getMessage(throwable), throwable);
	}

	public static void info(Object message, Throwable throwable) {
		LOG_INFO.info(getMessage(message), throwable);
	}

	// warn
	public static void warn(Object message) {
		LOG_WARN.warn(getMessage(message));
	}

	public static void warn(Throwable throwable) {
		LOG_WARN.warn(getMessage(throwable), throwable);
	}

	public static void warn(Object message, Throwable throwable) {
		LOG_WARN.warn(getMessage(message), throwable);
	}

	// error
	public static void error(Object message) {
		LOG_ERROR.error(getMessage(message));
	}

	public static void error(Throwable throwable) {
		LOG_ERROR.error(getMessage(throwable), throwable);
	}

	public static void error(Object message, Throwable throwable) {
		LOG_ERROR.error(getMessage(message), throwable);
	}

	// fatal
	public static void fatal(Object message) {
		LOG_FATAL.fatal(getMessage(message));
	}

	public static void fatal(Throwable throwable) {
		LOG_FATAL.fatal(getMessage(throwable), throwable);
	}

	public static void fatal(Object message, Throwable throwable) {
		LOG_FATAL.fatal(getMessage(message), throwable);
	}
}
