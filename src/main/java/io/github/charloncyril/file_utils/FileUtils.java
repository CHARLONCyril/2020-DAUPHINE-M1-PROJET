package io.github.charloncyril.file_utils;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import static io.github.charloncyril.constants.Constants.RESSOURCES_FOLDER;
import com.google.common.base.Preconditions;

import io.github.charloncyril.log.LogLevel;
import io.github.charloncyril.log.Logger;

public class FileUtils {
	private FileUtils() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * @param folder   is the folder where @param fileName is located
	 * @param fileName to read for parse or to create for writing result
	 * @return the path of the file @param fileName
	 */
	public static Path getPath(String folder, String fileName) {
		Preconditions.checkNotNull(folder);
		Preconditions.checkArgument(!folder.isEmpty());
		Preconditions.checkNotNull(fileName);
		Preconditions.checkArgument(!fileName.isEmpty());
		Logger.logMsg(FileUtils.class, "Path : " + folder + "/" + fileName, LogLevel.INFO);
		return Paths.get(folder, fileName);
	}

	/**
	 * @param folder   is the folder where @param fileName is located
	 * @param fileName to read for parse or to create for writing result
	 * @return the file expected
	 */
	public static File getFile(String folder, String fileName) {
		Preconditions.checkNotNull(fileName);
		Preconditions.checkArgument(!fileName.isEmpty());
		folder = (folder == null || folder.isEmpty()) ? RESSOURCES_FOLDER.toString() : folder;
		return new File(getPath(folder, fileName).toString());
	}

}
