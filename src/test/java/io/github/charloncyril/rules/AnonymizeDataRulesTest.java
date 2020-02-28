package io.github.charloncyril.rules;

import static org.junit.Assert.*;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class AnonymizeDataRulesTest {

	@Test
	public void test_randomLetter() {
		String initialData = "On aime le JAVA";
		String actualData = AnonymizeDataRules.randomLetter(initialData);
		assertTrue(initialData.length() == actualData.length());
		assertNotEquals(initialData, actualData);
	}
	
	@Test
	public void test_randomLetterForLocalPart() {
		String initialData = "xxxxx@gmail.com";
		int index = initialData.indexOf('@');
		String actualData = AnonymizeDataRules.randomLetterForLocalPart(initialData);
		assertTrue(initialData.length() == actualData.length());
		assertEquals(initialData.substring(index), actualData.substring(index));
		assertNotEquals(initialData.substring(0,index), actualData);
	}
	
	@Test 
	public void test_getMethod() {
		Assertions.assertThatCode(() -> AnonymizeDataRules.getMethod("randomLetter", String.class))
		.doesNotThrowAnyException();
		assertThrows(NoSuchMethodException.class, () -> {
			AnonymizeDataRules.getMethod("unknowMethod", String.class);
		});
	}
}
