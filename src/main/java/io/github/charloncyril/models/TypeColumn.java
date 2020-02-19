package io.github.charloncyril.models;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class TypeColumn extends Column {

	private TypeData dataType; // Data type for this column

	public TypeColumn(String name, TypeData dataType) {
		super(name);
		this.dataType = dataType;
	}

	public TypeData getDataType() {
		return dataType;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).appendSuper(super.toString()).append("Type column", dataType).toString();
	}

	/**
	 * @param array represent all objects generated after JSON parsing
	 * @return a map from @param array with the name of the column as key and the
	 *         field type as value
	 */
	public static Map<String, TypeData> getMapFromArray(TypeColumn[] array) {
		Map<String, TypeData> myMap = new HashMap<>();
		for (TypeColumn typeColumn : array) {
			myMap.put(typeColumn.getName(), typeColumn.getDataType());
		}
		return myMap;
	}
}
