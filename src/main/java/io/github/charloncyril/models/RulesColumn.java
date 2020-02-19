package io.github.charloncyril.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class RulesColumn extends Column {

	private List<String> should = new ArrayList<>(); // List containing all the rules that the associated column
														// must respect

	public RulesColumn(String name, List<String> rules) {
		super(name);
		should = rules;
	}

	public List<String> getRulesToVerify() {
		return should;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).appendSuper(super.toString()).append("Rules to Verify", should).toString();
	}

	/**
	 * @param array represent all objects generated after JSON parsing
	 * @return a map from @param array with the name of the column as key and a list
	 *         of rules associated to the field as value
	 */
	public static Map<String, List<String>> getMapFromArray(RulesColumn[] array) {
		Map<String, List<String>> myMap = new HashMap<>();
		for (RulesColumn rulesColumn : array) {
			myMap.put(rulesColumn.getName(), rulesColumn.getRulesToVerify());
		}
		return myMap;
	}

}
