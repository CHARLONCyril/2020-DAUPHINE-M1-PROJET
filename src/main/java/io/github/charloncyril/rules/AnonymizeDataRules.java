package io.github.charloncyril.rules;

import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

import com.google.common.base.Preconditions;
import com.google.common.hash.Hashing;


/**
 * 
 * Class bringing all the rules used for anonymization
 *
 */
public class AnonymizeDataRules {

	private AnonymizeDataRules() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * Using the reflection:
	 * 
	 * @param name           is the name of the method to call
	 * @param parameterTypes the parameters expected to call the method @param name
	 * @return a method object which will be called at runtime
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public static Method getMethod(String name, Class<?>... parameterTypes) throws NoSuchMethodException {
		Preconditions.checkNotNull(name);
		Preconditions.checkArgument(!name.isEmpty());
		Preconditions.checkNotNull(parameterTypes);
		return AnonymizeDataRules.class.getMethod(name, parameterTypes);
	}

	/**
	 * 
	 * @param s the string to anonymize
	 * @return @param s which is anonymized
	 */
	public static String randomLetter(String s) {
		Preconditions.checkNotNull(s);
		Preconditions.checkArgument(!s.isEmpty());
		return Hashing.sha256()
		  .hashString(s, StandardCharsets.UTF_8)
		  .toString().substring(0, s.length());
	}

	/**
	 * 
	 * @param email that we want to anonymize local part
	 * @return @param email with the local part anonymized
	 */
	public static String randomLetterForLocalPart(String email) {
		Preconditions.checkNotNull(email);
		Preconditions.checkArgument(!email.isEmpty());
		int index = email.indexOf('@');
		return randomLetter(email.substring(0, index)) + email.substring(index);
	}

}
