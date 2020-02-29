package io.github.charloncyril.constants;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

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

	/**
	 * list containing all host email part corresponding to Paris Dauphine email
	 * address
	 */
	public static final List<String> PATTERN_MAIL = Arrays.asList("lamsade.dauphine.fr", "dauphine.eu",
			"dauphine.psl.eu");

	/**
	 * RESSOURCES_FOLDER get the path for src/main/resources
	 */

	public static final Path RESSOURCES_FOLDER = Paths.get("src", "main", "resources");

	/**
	 * OUTPUT_DIRECTORY is the directory where result will be stored
	 */
	public static final String OUTPUT_DIRECTORY = "/output_directory";

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
