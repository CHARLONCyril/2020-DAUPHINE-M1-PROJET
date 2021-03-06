package io.github.charloncyril.csv_utils;

import static org.junit.Assert.*;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import io.github.charloncyril.file_utils.FileUtils;

public class CSVUtilsTest {

	private List<String[]> csvRows;
	private List<String[]> csvColumns;

	@Test
	public void test_readRowOrientedFile() {
		csvRows = CSVUtils.readRowOrientedFile(FileUtils.getFile("src/test/resources/", "info.csv"));
		assertNotNull(csvRows);
	}

	@Test
	public void test_readColumnOrientedFile() {
		csvColumns = CSVUtils.readColumnOrientedFile(FileUtils.getFile("src/test/resources/", "column.csv"));
		assertNotNull(csvColumns);
	}

	@Test
	public void test_writeIntoCSVFile() {
		csvRows = CSVUtils.readRowOrientedFile(FileUtils.getFile("src/test/resources/", "info.csv"));
		Assertions.assertThatCode(
				() -> CSVUtils.writeIntoCSVFile(FileUtils.getFile("src/test/resources/", "info.csv"), csvRows, false))
				.doesNotThrowAnyException();
	}

}
