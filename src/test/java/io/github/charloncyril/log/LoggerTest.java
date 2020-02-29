package io.github.charloncyril.log;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import io.github.charloncyril.file_utils.FileUtils;

public class LoggerTest {

	private static final Path RESSOURCES_FOLDER_TEST_LOG = Paths.get("src", "main", "resources", "logs");

	@Test
	public void test_logContentParse() {
		Logger.logContentParse(LoggerTest.class, "test logger function");
		assertTrue(FileUtils.getFile(RESSOURCES_FOLDER_TEST_LOG.toString(), "/syslog").exists());
	}

}
