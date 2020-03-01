package io.github.charloncyril.application;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.google.common.collect.ImmutableList;
import com.univocity.parsers.csv.CsvParserSettings;

import io.github.charloncyril.constants.Constants;
import io.github.charloncyril.csv_utils.CSVUtils;
import io.github.charloncyril.csv_utils.CustomParsing;
import io.github.charloncyril.file_utils.FileUtils;
import io.github.charloncyril.file_utils.ZipUtils;
import io.github.charloncyril.json_utils.JSONUtils;
import io.github.charloncyril.log.LogLevel;
import io.github.charloncyril.log.Logger;
import io.github.charloncyril.models.AnonymizeColumn;
import io.github.charloncyril.models.RulesColumn;
import io.github.charloncyril.models.TypeColumn;
import io.github.charloncyril.models.TypeData;
import io.github.charloncyril.rules.FunctionsUtils;
import static io.github.charloncyril.constants.Constants.RESSOURCES_FOLDER;
import static io.github.charloncyril.constants.Constants.OUTPUT_DIRECTORY;

public class App {

	public static final List<String> EXTENSIONS_AUTORIZED = ImmutableList.of("zip", "csv");
	public static final String INPUT_DIRECTORY = "input_directory";

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		Logger.logMsg(App.class, "Hello welcome to our application please give a file name or zip archive. \n",
				LogLevel.TRACE);
		String archiveOrFileName = sc.nextLine();
		String fileName = null;
		if (EXTENSIONS_AUTORIZED.contains(archiveOrFileName.substring(archiveOrFileName.lastIndexOf('.') + 1))) {
			if (archiveOrFileName.endsWith(".zip")) {
				Logger.logMsg(App.class,
						"You want to extract file from zip, please give us the name of the file to extract",
						LogLevel.TRACE);
				fileName = sc.nextLine();
				ZipUtils.unzipFile(RESSOURCES_FOLDER + "/" + INPUT_DIRECTORY + "/" + archiveOrFileName,
						FileUtils.getFile(RESSOURCES_FOLDER + "/" + INPUT_DIRECTORY, fileName));
				fileName = "unziped_" + fileName;
			} else {
				fileName = archiveOrFileName;
			}
			boolean res = false;
			List<String[]> myCsv = null;
			Map<String, TypeData> typeColumn = null;
			Map<String, List<String>> rulesColumn = null;
			Map<String, String> anonymizeColumn = null;
			String rep = "";
			do {
				Logger.logMsg(App.class,
						"Please choose the format of your file :1) Row Oriented File \n2) Column Oriented File",
						LogLevel.TRACE);
				rep = sc.nextLine();
				if (rep.equals("1")) {
					myCsv = CSVUtils.readRowOrientedFile(
							FileUtils.getFile(RESSOURCES_FOLDER + "/" + INPUT_DIRECTORY, fileName));
					res = true;
				} else if (rep.equals("2")) {
					myCsv = CSVUtils.readColumnOrientedFile(
							FileUtils.getFile(RESSOURCES_FOLDER + "/" + INPUT_DIRECTORY, fileName));
					res = true;
				}
			} while (!res);
			res = false;
			do {
				Logger.logMsg(App.class,
						"Please choose the action that you want to perfom :1) Verify data \n2) Annonymize data",
						LogLevel.TRACE);
				rep = sc.nextLine();
				if (rep.equals("1")) {
					typeColumn = TypeColumn.getMapFromArray(TypeColumn[].class.cast(JSONUtils.readJson(
							FileUtils.getFile(RESSOURCES_FOLDER + "/" + INPUT_DIRECTORY, "csv_description.json"),
							TypeColumn[].class)));

					rulesColumn = RulesColumn.getMapFromArray(RulesColumn[].class.cast(JSONUtils.readJson(
							FileUtils.getFile(RESSOURCES_FOLDER + "/" + INPUT_DIRECTORY, "csv_verification_rules.json"),
							RulesColumn[].class)));
					Logger.logMsg(App.class, "Would you like a zip archive (yes or not)", LogLevel.TRACE);
					rep = sc.nextLine();
					boolean choice = rep.equalsIgnoreCase("yes");
					FunctionsUtils.verifData(myCsv, typeColumn, rulesColumn, RESSOURCES_FOLDER + "/" + OUTPUT_DIRECTORY,
							fileName, choice);
					res = true;
				} else if (rep.equals("2")) {
					typeColumn = TypeColumn.getMapFromArray(TypeColumn[].class.cast(JSONUtils.readJson(
							FileUtils.getFile(RESSOURCES_FOLDER + "/" + INPUT_DIRECTORY, "csv_description.json"),
							TypeColumn[].class)));
					anonymizeColumn = AnonymizeColumn.getMapFromArray(AnonymizeColumn[].class
							.cast(JSONUtils.readJson(FileUtils.getFile(RESSOURCES_FOLDER + "/" + INPUT_DIRECTORY,
									"csv_anonymization_rules.json"), AnonymizeColumn[].class)));
					Logger.logMsg(App.class, "Would you like a zip archive (yes or not)", LogLevel.TRACE);
					rep = sc.nextLine();
					boolean choice = rep.equalsIgnoreCase("yes");
					FunctionsUtils.anonimyzeData(myCsv, typeColumn, anonymizeColumn,
							RESSOURCES_FOLDER + "/" + OUTPUT_DIRECTORY, fileName, choice);
					res = true;
				}
			} while (!res);
		} else {
			Logger.logMsg(CSVUtils.class, "The extension "
					+ archiveOrFileName.substring(archiveOrFileName.lastIndexOf('.') + 1) + " is not yet implemented",
					LogLevel.TRACE);
		}
		Logger.logMsg(CSVUtils.class, "Good bye see you soon.", LogLevel.TRACE);
		sc.close();

	}

}
