package io.github.charloncyril.file_utils;

import java.io.File;
import org.zeroturnaround.zip.ZipUtil;

import io.github.charloncyril.log.LogLevel;
import io.github.charloncyril.log.Logger;

public class ZipUtils {

	private ZipUtils() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * 
	 * @param inputFile   file to ZIP
	 * @param zipFilePath is the name of the new archive ZIP
	 */
	public static void zipFile(File inputFile, String zipFilePath) {
		Logger.logMsg(ZipUtils.class, "Generating archive " + zipFilePath + " containing " + inputFile, LogLevel.INFO);
		ZipUtil.packEntry(inputFile, new File(zipFilePath));
	}

	/**
	 * 
	 * @param zipFilePath path to the archive ZIP
	 * @param inputFile   file to extract
	 */
	public static void unzipFile(String zipFilePath, File inputFile) {
		Logger.logMsg(ZipUtils.class, "Extracting " + "/unziped_" + inputFile.getName() + " from " + zipFilePath,
				LogLevel.INFO);
		ZipUtil.unpackEntry(new File(zipFilePath), inputFile.getName(),
				new File(inputFile.getParent() + "/unziped_" + inputFile.getName()));
	}
}
