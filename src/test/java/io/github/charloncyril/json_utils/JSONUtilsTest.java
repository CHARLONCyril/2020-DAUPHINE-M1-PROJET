package io.github.charloncyril.json_utils;

import static org.junit.Assert.*;
import java.io.FileNotFoundException;
import org.junit.Test;

import io.github.charloncyril.file_utils.FileUtils;
import io.github.charloncyril.models.AnonymizeColumn;
import io.github.charloncyril.models.RulesColumn;
import io.github.charloncyril.models.TypeColumn;

public class JSONUtilsTest {

	@SuppressWarnings("unchecked")
	@Test
	public <T> void test_readJson() throws FileNotFoundException {
		T[] array = (T[]) JSONUtils.readJson(FileUtils.getFile("src/test/resources/", "csv_description.json"),
				TypeColumn[].class);
		assertNotNull(array);
		assertTrue(array instanceof TypeColumn[]);

		array = (T[]) JSONUtils.readJson(FileUtils.getFile("src/test/resources/", "csv_verification_rules.json"),
				RulesColumn[].class);
		assertNotNull(array);
		assertTrue(array instanceof RulesColumn[]);

		array = (T[]) JSONUtils.readJson(FileUtils.getFile("src/test/resources/", "csv_anonymization_rules.json"),
				AnonymizeColumn[].class);
		assertNotNull(array);
		assertTrue(array instanceof AnonymizeColumn[]);

	}

}
