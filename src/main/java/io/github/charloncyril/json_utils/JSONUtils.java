package io.github.charloncyril.json_utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import io.github.charloncyril.log.Logger;

public class JSONUtils {

	private JSONUtils() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * 
	 * @param <T>       represent the type of the object that will be returned and
	 *                  the type of the object used to parse the JSON file
	 * @param fileName  correspond to the JSON file to parse
	 * @param className represent an instance of Class T used to parse the JSON file
	 *                  and transform it into java object
	 * @return an array of object of class T
	 * @throws JsonSyntaxException
	 * @throws JsonIOException
	 * @throws FileNotFoundException
	 */
	public static <T> T[] readJson(File fileName, Class<T> className) throws FileNotFoundException {
		Preconditions.checkNotNull(fileName);
		Preconditions.checkArgument(fileName.toString().endsWith(".json"));
		Preconditions.checkNotNull(className);
		Gson gson = new Gson();
		@SuppressWarnings("unchecked")
		T[] jsonFile = (T[]) gson.fromJson(new FileReader(fileName), className);
		Logger.logContentParse(JSONUtils.class, jsonFile);
		return jsonFile;
	}

}
