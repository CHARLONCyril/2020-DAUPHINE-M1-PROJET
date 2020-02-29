package io.github.charloncyril.file_utils;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

public class ZipUtilsTest {
	
	private static final Path RESSOURCES_FOLDER_TEST_ZIP = Paths.get("src", "test", "resources");
	
	@Test
	public void test_zipFile() {
		ZipUtils.zipFile(FileUtils.getFile(RESSOURCES_FOLDER_TEST_ZIP.toString(), "info.csv"), RESSOURCES_FOLDER_TEST_ZIP.toString()+"/info.zip");
		assertTrue(FileUtils.getFile(RESSOURCES_FOLDER_TEST_ZIP.toString(), "/info.zip").exists());
	}
	
	@Test
	public void test_unzipFile() {
		ZipUtils.unzipFile(RESSOURCES_FOLDER_TEST_ZIP.toString()+"/info.zip", FileUtils.getFile(RESSOURCES_FOLDER_TEST_ZIP.toString(), "info.csv" ));
		assertTrue(FileUtils.getFile(RESSOURCES_FOLDER_TEST_ZIP.toString(), "/unziped_info.csv").exists());
	}


}
