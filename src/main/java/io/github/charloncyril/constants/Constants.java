package io.github.charloncyril.constants;

public final class Constants {

	private Constants() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * EMPTY_VALUE is the default message to write in output file if data field is
	 * empty
	 */
	private static String DEFAULT_VALUE = "NULL";

	/**
	 * LINE_DELIMITER_FOR_WRITTING is the line separator sequence during writing
	 * process
	 * 
	 */
	private static String LINE_DELIMITER_FOR_WRITING = "\n";

	/**
	 * FIELD_DELIMITER_FOR_WRITTING is the field delimiter character for writing
	 */
	private static char FIELD_DELIMITER_FOR_WRITING = ',';

	/**
	 * FIELD_DELIMITER_FOR_PARSING is the field delimiter character during the
	 * parsing
	 */
	private static char FIELD_DELIMITER_FOR_PARSING = ',';

	/**
	 * LINE_DELIMITER_FOR_PARSING is the line separator sequence during the parsing
	 */
	private static String LINE_DELIMITER_FOR_PARSING = "\n";

	public static char getFieldDelimitterForParsing() {
		return FIELD_DELIMITER_FOR_PARSING;
	}

	public static void setFieldDelimitterForParsing(char fieldDelimitterForParsing) {
		FIELD_DELIMITER_FOR_PARSING = fieldDelimitterForParsing;
	}

	public static char getFieldDelimitterForWriting() {
		return FIELD_DELIMITER_FOR_WRITING;
	}

	public static void setFieldDelimitterForWriting(char fieldDelimitterForWriting) {
		FIELD_DELIMITER_FOR_WRITING = fieldDelimitterForWriting;
	}

	public static String getLineDelimitterForParsing() {
		return LINE_DELIMITER_FOR_PARSING;
	}

	public static void setLineDelimitterForParsing(String lineDelimitterForParsing) {
		LINE_DELIMITER_FOR_PARSING = lineDelimitterForParsing;
	}

	public static String getLineDelimitterForWriting() {
		return LINE_DELIMITER_FOR_WRITING;
	}

	public static void setLineDelimitterForWriting(String lineDelimitterForWriting) {
		LINE_DELIMITER_FOR_WRITING = lineDelimitterForWriting;
	}

	public static String getDefaultValue() {
		return DEFAULT_VALUE;
	}

	public static void setDefaultValue(String defaultValue) {
		DEFAULT_VALUE = defaultValue;
	}

}
