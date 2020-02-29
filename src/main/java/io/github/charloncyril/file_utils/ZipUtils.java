package io.github.charloncyril.file_utils;
import java.io.File;
import org.zeroturnaround.zip.ZipUtil;

public class ZipUtils {

	private ZipUtils() {
		throw new IllegalStateException("Utility class");
	}
	
	/**
	 * 
	 * @param inputFile file to ZIP
	 * @param zipFilePath is the name of the new archive ZIP
	 */
	public static void zipFile(File inputFile, String zipFilePath) {
		ZipUtil.packEntry(inputFile, new File(zipFilePath));
	}
	
	/**
	 * 
	 * @param zipFilePath path to the archive ZIP
	 * @param inputFile file to extract
	 */
	public static void unzipFile(String zipFilePath, File inputFile) {
		ZipUtil.unpackEntry(new File(zipFilePath), inputFile.getName(), new File(inputFile.getParent()+"/unziped_"+inputFile.getName()));
	}
}
