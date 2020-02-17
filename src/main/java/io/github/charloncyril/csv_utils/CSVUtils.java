package io.github.charloncyril.csv_utils;

import static io.github.charloncyril.constants.Constants.EMPTY_VALUE;
import static io.github.charloncyril.constants.Constants.LINE_DELIMITER_FOR_WRITING;

import com.univocity.parsers.common.processor.RowListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVUtils {

	/**
	 * FIELD_DELIMITER is the field delimiter character during the parsing
	 */
	private static final char FIELD_DELIMITER = setFieldDelimitter();

	/**
	 * LINE_DELIMITER_FOR_PARSING is the line separator sequence during the parsing
	 */
	private static final String LINE_DELIMITER_FOR_PARSING = setLineDelimitter();

	/**
	 * parserSettings represents the different options used during the parsing
	 */
	private static CsvParserSettings parserSettings = getParserSetting();

	/**
	 * settings represents the different options used during the writing
	 */
	private static CsvWriterSettings settings = getParserWritingSetting();

	private CSVUtils() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * @return CsvParserSettings object with its custom parameters
	 */
	public static CsvParserSettings getParserSetting() {
		CsvParserSettings parserSettings = new CsvParserSettings();
		parserSettings.getFormat().setDelimiter(FIELD_DELIMITER);
		parserSettings.getFormat().setLineSeparator(LINE_DELIMITER_FOR_PARSING);
		return parserSettings;
	}

	/**
	 * CsvParserSettings object with its custom parameters
	 */
	public static CsvWriterSettings getParserWritingSetting() {
		CsvWriterSettings parserSettings = new CsvWriterSettings();
		parserSettings.setEmptyValue(EMPTY_VALUE);
		parserSettings.getFormat().setDelimiter(FIELD_DELIMITER);
		parserSettings.getFormat().setLineSeparator(LINE_DELIMITER_FOR_WRITING);
		return parserSettings;
	}

	/**
	 * @param fileName is the file to parse
	 * @return a list where the first element is the header and other element are
	 *         each line of the file parsed
	 */
	public static List<String[]> readRowOrientedFile(File fileName) {
		RowListProcessor rowProcessor = new RowListProcessor();
		parserSettings.setHeaderExtractionEnabled(true);
		parserSettings.setProcessor(rowProcessor);
		CsvParser parser = new CsvParser(parserSettings);
		parser.parse(fileName);
		List<String[]> rows = new ArrayList<>();
		rows.add(rowProcessor.getHeaders());
		rows.addAll(rowProcessor.getRows());
		return rows;
	}

	/**
	 * @param fileName is the output file
	 * @param rows     data to write
	 */
	public static void writeIntoCSVFile(File fileName, List<String[]> rows) {
		settings.setHeaders(rows.remove(0));
		CsvWriter writer = new CsvWriter(fileName, settings);
		writer.writeHeaders();
		for (String[] row : rows) {
			writer.writeRow(row);
		}
		writer.close();
	}

	/**
	 * @return the field delimiter chosen by user
	 */
	@SuppressWarnings("resource")
	public static char setFieldDelimitter() {
		System.out.println("Please choose a field delimitter before starting (a sequence of 1 characters): ");
		Scanner s = new Scanner(System.in);
		return s.nextLine().charAt(0);
	}

	/**
	 * @return the line delimiter chosen by user
	 */
	@SuppressWarnings("resource")
	public static String setLineDelimitter() {
		String lineDel = null;
		boolean isValid = false;
		while (!isValid) {
			System.out.println(
					"Please choose a line delimitter before starting to parse (a sequence of 1 to 2 characters):");
			Scanner s = new Scanner(System.in);
			lineDel = s.nextLine();
			if (lineDel.length() == 1 || lineDel.length() == 2) {
				isValid = true;
			}
		}
		return lineDel;
	}
}
