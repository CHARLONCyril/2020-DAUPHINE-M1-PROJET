package io.github.charloncyril.rules;

import static org.junit.Assert.*;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class VerifyDataRulesTest {

	@Test
	public void test_beAnAge() {
		assertTrue(VerifyDataRules.beAnAge("0"));
		assertTrue(VerifyDataRules.beAnAge("120"));
		assertTrue(VerifyDataRules.beAnAge("50"));
		assertFalse(VerifyDataRules.beAnAge("-1"));
	}
	
	@Test
	public void test_beAnEmail() {
		assertTrue(VerifyDataRules.beAnEmail("xxxx@gmail.com"));
		assertFalse(VerifyDataRules.beAnEmail("xxxxgmail.com"));
		assertFalse(VerifyDataRules.beAnEmail("xxxx@"));
	}
	
	@Test
	public void test_beAnEmailDauhpine() {
		assertTrue(VerifyDataRules.beAnDauphineEmail("xxxx@dauphine.eu"));
		assertTrue(VerifyDataRules.beAnDauphineEmail("xxxx@dauphine.psl.eu"));
		assertTrue(VerifyDataRules.beAnDauphineEmail("xxxx@lamsade.dauphine.fr"));
		assertFalse(VerifyDataRules.beAnDauphineEmail("xxxx@gmail.com"));
	}
	
	@Test 
	public void test_getMethod() {
		Assertions.assertThatCode(() -> VerifyDataRules.getMethod("beAnDauphineEmail", String.class))
		.doesNotThrowAnyException();
		assertThrows(NoSuchMethodException.class, () -> {
			VerifyDataRules.getMethod("unknowMethod", String.class);
		});
	}

}
