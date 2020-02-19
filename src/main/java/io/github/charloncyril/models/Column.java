package io.github.charloncyril.models;

import org.apache.commons.lang3.builder.ToStringBuilder;

public abstract class Column {

	private String name; // name to the csv column currently parse

	public Column(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return new ToStringBuilder(this).append("Column name", name).toString();
	}

}
