package io.github.charloncyril.csv_utils;

import static org.junit.Assert.*;
import java.io.File;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class CSVUtilsTest {

	private List<String[]> csvRows;

	public CSVUtilsTest() {
		csvRows = CSVUtils.readRowOrientedFile(new File("src/test/resources/info.csv"));
	}

	@Test
	public void test_readRowOrientedFile() {
		assertNotNull(csvRows);
	}

	@Test
	public void test_writeIntoCSVFile() {
		Assertions.assertThatCode(() -> CSVUtils.writeIntoCSVFile(new File("src/test/resources/info.csv"), csvRows))
				.doesNotThrowAnyException();
	}

}
