package io.github.charloncyril.csv_utils;

import com.google.common.base.Preconditions;
import com.univocity.parsers.common.processor.ColumnProcessor;
import com.univocity.parsers.common.processor.RowListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;
import io.github.charloncyril.constants.Constants;
import io.github.charloncyril.log.Logger;
import io.github.charloncyril.rules.FunctionsUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CSVUtils {

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
		parserSettings.getFormat().setDelimiter(Constants.getFieldDelimitterForParsing());
		parserSettings.getFormat().setLineSeparator(Constants.getLineDelimitterForParsing());
		return parserSettings;
	}

	/**
	 * CsvParserSettings object with its custom parameters
	 */
	public static CsvWriterSettings getParserWritingSetting() {
		CsvWriterSettings parserSettings = new CsvWriterSettings();
		parserSettings.setEmptyValue(Constants.getDefaultValue());
		parserSettings.getFormat().setDelimiter(Constants.getFieldDelimitterForWriting());
		parserSettings.getFormat().setLineSeparator(Constants.getLineDelimitterForWriting());
		return parserSettings;
	}

	/**
	 * @param fileName is the file to parse
	 * @return a list where the first element is the header and other element are
	 *         each line of the file parsed
	 */
	public static List<String[]> readRowOrientedFile(File fileName) {
		Preconditions.checkNotNull(fileName);
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
	
	public static List<String[]> readColumnOrientedFile(File fileName) {

		ColumnProcessor columnProcessor = new ColumnProcessor();
		parserSettings.setProcessor(columnProcessor);
		CsvParser parser = new CsvParser(parserSettings);
		parser.parse(fileName);
		List<String[]> csv = new ArrayList<>();
		List<List<String>> column = new ArrayList<>();
		column.addAll(columnProcessor.getColumnValuesAsList());
		for (List<String> c : column) {
			csv.add(FunctionsUtils.convertCollectionToArray(c));
		}
		Logger.logContentParse(CSVUtils.class, csv);
		return csv;

	}

	/**
	 * @param fileName is the output file
	 * @param rows     data to write
	 */
	public static void writeIntoCSVFile(File fileName, List<String[]> rows) {
		Preconditions.checkNotNull(fileName);
		Preconditions.checkNotNull(rows);
		Preconditions.checkArgument(!rows.isEmpty());
		settings.setHeaders(rows.remove(0));
		CsvWriter writer = new CsvWriter(fileName, settings);
		writer.writeHeaders();
		for (String[] row : rows) {
			writer.writeRow(row);
		}
		writer.close();
	}

	/**
	 * @param custom is the name of the custom param to modify
	 * @param val    is the new val for @param custom
	 */
	public static void setFieldSeparatorParams(CustomParsing custom, String val) {
		Preconditions.checkNotNull(val, "The custom param value to modify can't be null ");
		Preconditions.checkArgument(!val.isEmpty(), "The custom param value can't be empty ");
		switch (custom) {
		case DEFAULT_VALUE:
			Constants.setDefaultValue(val);
			break;
		case FIELD_DELIMITER_FOR_PARSING:
			Preconditions.checkArgument(val.length() == 1, "Field delimitter must be a sequence of 1 character");
			Constants.setFieldDelimitterForParsing(val.charAt(0));
			break;
		case FIELD_DELIMITER_FOR_WRITING:
			Preconditions.checkArgument(val.length() == 1, "Field delimitter must be a sequence of 1 character");
			Constants.setFieldDelimitterForWriting(val.charAt(0));
			break;
		case LINE_DELIMITER_FOR_PARSING:
			Preconditions.checkArgument(val.length() <= 2, "Line delimitter must be a sequence of 1 to 2 characters");
			Constants.setLineDelimitterForParsing(val);
			break;
		case LINE_DELIMITER_FOR_WRITING:
			Preconditions.checkArgument(val.length() <= 2, "Line delimitter must be a sequence of 1 to 2 characters");
			Constants.setLineDelimitterForWriting(val);
			break;
		}
	}
}
