package io.github.charloncyril.rules;

import static org.junit.Assert.*;

import org.junit.Test;

public class AnonymizeDataTest {

	@Test
	public void test_randomLetter() {
		String initialData = "On aime le JAVA";
		String actualData = AnonymizeData.randomLetter(initialData);
		assertTrue(initialData.length() == actualData.length());
		assertNotEquals(initialData, actualData);
	}
	
	@Test
	public void test_randomLetterForLocalPart() {
		String initialData = "xxxxx@gmail.com";
		int index = initialData.indexOf('@');
		String actualData = AnonymizeData.randomLetterForLocalPart(initialData);
		assertTrue(initialData.length() == actualData.length());
		assertEquals(initialData.substring(index), actualData.substring(index));
		assertNotEquals(initialData.substring(0,index), actualData);
	}
}
