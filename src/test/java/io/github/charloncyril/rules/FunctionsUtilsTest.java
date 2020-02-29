package io.github.charloncyril.rules;

import static org.junit.Assert.*;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import io.github.charloncyril.file_utils.FileUtils;
import io.github.charloncyril.models.TypeData;

public class FunctionsUtilsTest {

	private static final Path RESSOURCES_FOLDER_TEST = Paths.get("src", "test", "resources");
	private List<String[]> myCsv = new ArrayList<>();
	private Map<String, TypeData> typeColumn = new HashMap<String, TypeData>();
	private Map<String, List<String>> rulesColumn = new HashMap<String, List<String>>();
	private Map<String, String> anonymizeColumn = new HashMap<String, String>();
	private String[] row = new String[1];
	private String[] header = new String[1];

	public FunctionsUtilsTest() {
		row[0] = "xxx@gmail.com";
		header[0] = "email";
		myCsv.add(row);
		typeColumn.put(header[0], TypeData.STRING);
		rulesColumn.put(header[0], Arrays.asList("beAnEmail"));
		anonymizeColumn.put(header[0], "randomLetter");
	}

	@Test
	public void test_isInt() {
		assertTrue(FunctionsUtils.isInt("10"));
		assertTrue(FunctionsUtils.isInt("-10"));
		assertTrue(FunctionsUtils.isInt("0"));
		assertFalse(FunctionsUtils.isInt("0.0"));
		assertFalse(FunctionsUtils.isInt("i am a number"));
	}

	@Test
	public void test_isDouble() {
		assertTrue(FunctionsUtils.isDouble("-10.0"));
		assertTrue(FunctionsUtils.isDouble("10.0"));
		assertTrue(FunctionsUtils.isDouble("- 0.0"));
		assertTrue(FunctionsUtils.isDouble("-	0.0"));
		assertTrue(FunctionsUtils.isDouble("	0.0"));
		assertFalse(FunctionsUtils.isDouble("0"));
		assertFalse(FunctionsUtils.isDouble("i am a number"));
	}

	@Test
	public void test_checkColumnType() {
		assertTrue(FunctionsUtils.checkColumnType("5", TypeData.INT));
		assertTrue(FunctionsUtils.checkColumnType("5.0", TypeData.DOUBLE));
		assertTrue(FunctionsUtils.checkColumnType("hello", TypeData.STRING));
	}

	@Test
	public void test_convertCollectionToArray() {
		assertTrue(FunctionsUtils.convertCollectionToArray(new ArrayList<>()).getClass().isArray());
	}

	@Test
	public void test_checkColumnRules() {
		try {
			assertTrue((FunctionsUtils.checkColumnRules("5", Arrays.asList("beAnAge"))));
			assertFalse((FunctionsUtils.checkColumnRules("5", Arrays.asList("beAnEmail"))));
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			fail("An error occured during checkColumnRules test : \n " + e.toString());
		}

	}

	@Test
	public void test_dataAnonymized() {
		try {
			assertTrue((!FunctionsUtils.dataAnonymized("xxx@gmail.com", "randomLetterForLocalPart").isEmpty()));
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			fail("An error occured during checkColumnRules test : \n " + e.toString());
		}
	}

	@Test
	public void test_verifData() {
		FunctionsUtils.verifData(myCsv, typeColumn, rulesColumn, RESSOURCES_FOLDER_TEST.toString(),
				"testVerifyData.csv", false);
		assertTrue(new File(RESSOURCES_FOLDER_TEST + "/testVerifyData.csv").exists());
	}

	@Test
	public void test_checkDataBeforeInsertInCSV() {
		assertTrue(FunctionsUtils.checkDataTypeBeforeInsertInCSV(myCsv.get(0), header, typeColumn));
	}

	@Test
	public void test_anonimyzeData() {
		myCsv.add(0, header);
		FunctionsUtils.anonimyzeData(myCsv, typeColumn, anonymizeColumn, RESSOURCES_FOLDER_TEST.toString(),
				"testAnonymizeData.csv", false);
		assertTrue(new File(RESSOURCES_FOLDER_TEST + "/testAnonymizeData.csv").exists());
	}

	public void test_allRulesValid() {
		assertTrue(FunctionsUtils.allRulesValid(row, header, rulesColumn));
	}

}
