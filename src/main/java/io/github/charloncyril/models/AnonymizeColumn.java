package io.github.charloncyril.models;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class AnonymizeColumn extends Column {

	private String changeTo; // Rule of anonymization

	public AnonymizeColumn(String name, String functionName) {
		super(name);
		changeTo = functionName;
	}

	public String getTypeOfAnonymization() {
		return changeTo;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).appendSuper(super.toString()).append("Rule to Anonimize", changeTo).toString();
	}

	/**
	 * @param array represent all objects generated after JSON parsing
	 * @return a map from @param array with the name of the column as key and the
	 *         kind of anonymization as value
	 */
	public static Map<String, String> getMapFromArray(AnonymizeColumn[] array) {
		Map<String, String> myMap = new HashMap<>();
		for (AnonymizeColumn anonymizeColumn : array) {
			myMap.put(anonymizeColumn.getName(), anonymizeColumn.getTypeOfAnonymization());
		}
		return myMap;
	}
}
