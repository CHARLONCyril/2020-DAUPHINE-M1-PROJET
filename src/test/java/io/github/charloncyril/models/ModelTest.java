package io.github.charloncyril.models;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import io.github.charloncyril.json_utils.JSONUtils;

public class ModelTest {

	private Map<String, TypeData> typeColumn;
	private Map<String, List<String>> rulesColumn;
	private Map<String, String> anonymizeColumn;

	public ModelTest() throws FileNotFoundException {
		typeColumn = TypeColumn.getMapFromArray(TypeColumn[].class
				.cast(JSONUtils.readJson(new File("src/test/resources/csv_description.json"), TypeColumn[].class)));
		rulesColumn = RulesColumn.getMapFromArray(RulesColumn[].class.cast(
				JSONUtils.readJson(new File("src/test/resources/csv_verification_rules.json"), RulesColumn[].class)));
		anonymizeColumn = AnonymizeColumn.getMapFromArray(AnonymizeColumn[].class.cast(JSONUtils
				.readJson(new File("src/test/resources/csv_anonymization_rules.json"), AnonymizeColumn[].class)));
	}

	@Test
	public void test_getMapFromArray() {
		Map<String, TypeData> typeColumnActual = ImmutableMap.<String, TypeData>builder().put("NOM", TypeData.STRING)
				.put("AGE", TypeData.INT).put("DATE_DE_NAISSANCE", TypeData.STRING).put("EMAIL_PRO", TypeData.STRING)
				.put("EMAIL_PERSO", TypeData.STRING).build();

		assertTrue(Maps.difference(this.typeColumn, typeColumnActual).areEqual());

		Map<String, List<String>> rulesColumnActual = ImmutableMap.<String, List<String>>builder()
				.put("AGE", Lists.newArrayList("BE_AN_AGE"))
				.put("EMAIL_PRO", Lists.newArrayList("BE_AN_EMAIL", "BE_AN_DAUPHINE_EMAIL"))
				.put("EMAIL_PERSO", Lists.newArrayList("BE_AN_EMAIL")).build();

		assertTrue(Maps.difference(this.rulesColumn, rulesColumnActual).areEqual());

		Map<String, String> anonymizeColumnActual = ImmutableMap.<String, String>builder().put("NOM", "RANDOM_LETTER")
				.put("EMAIL_PERSO", "RANDOM_LETTER_FOR_LOCAL_PART").build();

		assertTrue(Maps.difference(this.anonymizeColumn, anonymizeColumnActual).areEqual());
	}

}
