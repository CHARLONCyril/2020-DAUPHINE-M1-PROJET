package io.github.charloncyril.json_utils;

import static org.junit.Assert.*;
import java.io.File;
import java.io.FileNotFoundException;
import org.junit.Test;
import io.github.charloncyril.models.TypeColumn;

public class JSONUtilsTest {

	@SuppressWarnings("unchecked")
	@Test
	public <T> void test_readJson() throws FileNotFoundException {
		T[] array = (T[]) JSONUtils.readJson(new File("src/test/resources/csv_description.json"), TypeColumn[].class);
		assertNotNull(array);
		assertTrue(array instanceof TypeColumn[]);
	}

}
