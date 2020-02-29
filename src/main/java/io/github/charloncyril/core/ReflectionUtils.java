package io.github.charloncyril.core;

public class ReflectionUtils {
	private ReflectionUtils() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * 
	 * @return paramString which is used to called the method at run time with
	 *         reflection and specified that we expected string parameters
	 */
	@SuppressWarnings("rawtypes")
	public static Class[] methodWithStringParams() {

		Class[] paramString = new Class[1];
		paramString[0] = String.class;
		return paramString;
	}
}
