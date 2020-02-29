package io.github.charloncyril.log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import com.google.common.collect.ImmutableMap;

public class Logger {

	/**
	 * Represent the different log level
	 */
	public static final Map<LogLevel, String> LEVEL_MESSAGE = ImmutableMap.<LogLevel, String>builder()
			.put(LogLevel.ERROR, "ERROR").put(LogLevel.WARN, "WARNING").put(LogLevel.INFO, "INFO")
			.put(LogLevel.DEBUG, "DEBUG").put(LogLevel.TRACE, "TRACE").build();

	private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger("Log");

	private Logger() {
		throw new IllegalStateException("Utility class");
	}

	public static void logMsg(Class<?> className, String msg, LogLevel level) {
		log(level, className, msg, null);
	}

	public static void logMsg(Class<?> className, String msg, LogLevel level, Throwable throwable) {
		log(level, className, msg, throwable);
	}

	private static void log(LogLevel level, Class<?> className, String msg, Throwable throwable) {
		String message = String.format("[%s] [%s] : %s", LEVEL_MESSAGE.get(level), className.getTypeName(), msg);
		switch (level) {
		case INFO:
			log.info(message, throwable);
			break;
		case WARN:
			log.warn(message, throwable);
			break;
		case ERROR:
			log.error(message, throwable);
			break;
		case DEBUG:
			log.debug(message, throwable);
			break;
		default:
			log.trace(message, throwable);
		}
	}

	@SafeVarargs
	public static <T> void logContentParse(Class<?> className, T... array) {
		for (T line : array) {
			if (line instanceof ArrayList) {
				logContentParse(className, ((ArrayList<?>) line).toArray());
			} else if (line instanceof String[]) {
				Logger.logMsg(className, "'[FROM CSV]'" + Arrays.asList((String[]) line), LogLevel.INFO);

			} else {
				Logger.logMsg(className, "'[Object created]'" + line, LogLevel.INFO);
			}
		}
	}

}
