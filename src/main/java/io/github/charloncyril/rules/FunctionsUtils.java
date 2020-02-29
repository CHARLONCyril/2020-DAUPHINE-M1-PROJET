package io.github.charloncyril.rules;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import com.google.common.base.Preconditions;

import io.github.charloncyril.core.ReflectionUtils;
import io.github.charloncyril.csv_utils.CSVUtils;
import io.github.charloncyril.file_utils.FileUtils;
import io.github.charloncyril.models.TypeData;

public class FunctionsUtils {

	private FunctionsUtils() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * 
	 * @param myCollection is the collection to convert into array
	 * @return the array obtain after conversion
	 */
	public static String[] convertCollectionToArray(Collection<?> myCollection) {
		Preconditions.checkNotNull(myCollection);
		return myCollection.toArray(new String[0]);
	}

	/**
	 * 
	 * @param s the string to check for
	 * @return true if it is an integer
	 */
	public static boolean isInt(String s) {
		Preconditions.checkNotNull(s);
		return s.matches("-?\t? ?[0-9]+");
	}

	/**
	 * 
	 * @param s the string to check for
	 * @return true if it is a double
	 */
	public static boolean isDouble(String s) {
		Preconditions.checkNotNull(s);
		return s.matches("-?\t? ?[0-9]+\\.[0-9]+");
	}

	/**
	 * 
	 * @param val      is the value to verify
	 * @param typeData is a enum corresponding to the authorized valued that each
	 *                 column must take
	 * @return true if @param val could be converted to @param typeData value
	 */
	public static boolean checkColumnType(String val, TypeData typeData) {
		Preconditions.checkNotNull(val);
		Preconditions.checkNotNull(typeData);
		switch (typeData) {
		case INT:
			return isInt(val);
		case DOUBLE:
			return isDouble(val);
		case STRING:
			return !isInt(val) && !isDouble(val);
		default:
			return false;
		}
	}

	/**
	 * 
	 * @param val   is the value which need to respect all the rules
	 * @param rules is the list of all the rule that each values of a column must
	 *              respect
	 * @return true if @param val respect each rule containing in rules
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("rawtypes")
	public static boolean checkColumnRules(String val, List<String> rules)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

		Preconditions.checkNotNull(val);
		Preconditions.checkNotNull(rules);
		Class[] paramString = ReflectionUtils.methodWithStringParams();
		for (String rule : rules) {
			Method method = VerifyDataRules.getMethod(rule, paramString);
			boolean res = (boolean) method.invoke(null, val);
			if (!res)
				return res;
		}
		return true;
	}

	/**
	 * 
	 * @param val  the value to anonymize
	 * @param rule the rule to apply
	 * @return the value obtain after applying the rule
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("rawtypes")
	public static String dataAnonymized(String val, String rule)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

		Preconditions.checkNotNull(val);
		Preconditions.checkNotNull(rule);
		Class[] paramString = ReflectionUtils.methodWithStringParams();
		Method method = AnonymizeDataRules.getMethod(rule, paramString);
		return (String) method.invoke(null, val);

	}

	/**
	 * 
	 * @param myCsv    all the rows from CSV file
	 * @param TC       array of objects corresponding to a Column type
	 * @param RC       array of objects containing all rules for each column
	 * @param fileName the file were we will write the result
	 */
	public static void verifData(List<String[]> myCsv, Map<String, TypeData> typeColumn,
			Map<String, List<String>> rulesColumn, String folder, String fileName, boolean choice) {
		ArrayList<String[]> rowToWrite = new ArrayList<>();
		Preconditions.checkNotNull(myCsv);
		Preconditions.checkNotNull(typeColumn);
		Preconditions.checkNotNull(rulesColumn);
		Preconditions.checkNotNull(fileName);
		Preconditions.checkArgument(!fileName.isEmpty());
		Preconditions.checkNotNull(choice);
		rowToWrite.add(myCsv.remove(0)); // add the header of the CSV to our list
		for (String[] row : myCsv) {
			if (checkDataTypeBeforeInsertInCSV(row, rowToWrite.get(0), typeColumn)) {
				if (allRulesValid(row, rowToWrite.get(0), rulesColumn)) {
					rowToWrite.add(row);
				} else {
					rowToWrite.add(new String[] { "" });
				}
			} else {
				rowToWrite.add(new String[] { "" }); // we set empty value, like that we will write the default value
				// during writing process
			}
		}
		// Logger.logContentParse(FunctionsUtils.class, rowToWrite);
		CSVUtils.writeIntoCSVFile(FileUtils.getFile(folder, fileName), rowToWrite, choice);
	}

	/**
	 * 
	 * @param row        is the line currently analyzed
	 * @param header     is the header of the CSV file
	 * @param typeColumn correspond to type of each column
	 * @return true if all data are in the good type false otherwise
	 */
	public static boolean checkDataTypeBeforeInsertInCSV(String[] row, String[] header,
			Map<String, TypeData> typeColumn) {
		if (header.length > typeColumn.size()) {
			return false;
		}
		for (int i = 0; i < header.length; i++) {
			try {
				System.out.println(header[i] + " => " + row[i]);
				if ((typeColumn.get(header[i]) == null) || !checkColumnType(row[i], typeColumn.get(header[i]))) {
					System.out.println("cyril");
					return false;
				}
			} catch (NullPointerException e) {
				// TODO: Add log for each exceptions when it will be implemented
				System.out.println("charlon");
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @param row         is the line currently analyzed
	 * @param header      is the header of the CSV file
	 * @param rulesColumn correspond to the rules needed to be verify for each field
	 * @return true if all rules are satisfied for each column false otherwise
	 */
	public static boolean allRulesValid(String[] row, String[] header, Map<String, List<String>> rulesColumn) {
		for (int i = 0; i < header.length; i++) {
			try {
				if ((rulesColumn.get(header[i]) != null) && !checkColumnRules(row[i], rulesColumn.get(header[i]))) {
					return false;
				}
			} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
				// TODO: Add log for each exceptions when it will be implemented
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @param myCsv    all the rows from CSV file
	 * @param TC       array of objects corresponding to a Column type
	 * @param AC       array of objects containing all anonymization rules for each
	 *                 column
	 * @param fileName the file were we will write the result
	 */
	public static void anonimyzeData(List<String[]> myCsv, Map<String, TypeData> typeColumn,
			Map<String, String> anonymizeColumn, String folder, String fileName, boolean choice) {
		Preconditions.checkNotNull(myCsv);
		Preconditions.checkNotNull(anonymizeColumn);
		Preconditions.checkNotNull(fileName);
		Preconditions.checkNotNull(typeColumn);
		Preconditions.checkArgument(!fileName.isEmpty());
		Preconditions.checkNotNull(choice);
		ArrayList<String[]> rowToWrite = new ArrayList<>();
		String[] csvHeader = myCsv.remove(0);
		rowToWrite.add(csvHeader); // Default header
		LinkedHashSet<String> headerColumnAnonymised = new LinkedHashSet<>();
		for (String[] row : myCsv) {
			ArrayList<String> rowValueAnonymized = new ArrayList<>();
			for (int i = 0; i < csvHeader.length; i++) {
				if (anonymizeColumn.get(csvHeader[i]) != null) {
					headerColumnAnonymised.add(csvHeader[i]);
					try {
						if (checkDataTypeBeforeInsertInCSV(convertCollectionToArray(Arrays.asList(row[i])),
								convertCollectionToArray(Arrays.asList(csvHeader[i])), typeColumn)) {
							rowValueAnonymized.add(dataAnonymized(row[i], anonymizeColumn.get(csvHeader[i])));
						} else {
							rowValueAnonymized.add("");
						}
					} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
						rowValueAnonymized.add("");
					}
				}
			}
			rowToWrite.add(convertCollectionToArray(rowValueAnonymized));
		}
		rowToWrite.set(0, convertCollectionToArray(headerColumnAnonymised));
		CSVUtils.writeIntoCSVFile(FileUtils.getFile(folder, fileName), rowToWrite, choice);
	}

}
