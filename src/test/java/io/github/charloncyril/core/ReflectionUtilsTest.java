package io.github.charloncyril.core;

import static org.junit.Assert.*;

import org.junit.Test;

public class ReflectionUtilsTest {

	@Test
	public void tes_methodWithStringParamst() {
		assertEquals(String.class, ReflectionUtils.methodWithStringParams()[0]);
	}

}
