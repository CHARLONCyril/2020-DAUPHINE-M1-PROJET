package io.github.charloncyril.rules;

import static io.github.charloncyril.constants.Constants.PATTERN_MAIL;
import java.lang.reflect.Method;
import org.apache.commons.validator.routines.EmailValidator;

import com.google.common.base.Preconditions;

/**
 * 
 * Class bringing all the rules used for verify data
 *
 */
public class VerifyDataRules {

	private VerifyDataRules() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * Using the reflection:
	 * 
	 * @param name           is the name of the method to call
	 * @param parameterTypes the parameters expected to call the method @param name
	 * @return a method object which will be called at runtime
	 * @throws NoSuchMethodException
	 */
	public static Method getMethod(String name, Class<?>... parameterTypes) throws NoSuchMethodException {
		Preconditions.checkNotNull(name);
		Preconditions.checkArgument(!name.isEmpty());
		Preconditions.checkNotNull(parameterTypes);
		return VerifyDataRules.class.getMethod(name, parameterTypes);

	}

	/**
	 * 
	 * @param s the string to verify
	 * @return true if s is an age
	 */
	public static boolean beAnAge(String s) {
		Preconditions.checkNotNull(s);
		int age = Integer.parseInt(s);
		return (age >= 0 && age <= 120);

	}

	/**
	 * 
	 * @param email check if email correspond to email pattern
	 * @return
	 */
	public static boolean beAnEmail(String email) {
		Preconditions.checkNotNull(email);
		Preconditions.checkArgument(!email.isEmpty());
		return EmailValidator.getInstance().isValid(email);
	}

	/**
	 * 
	 * @param email the string to check
	 * @return true if @email end with one value of patterMail list
	 */
	private static boolean isDauphineEmail(String email) {
		Preconditions.checkNotNull(email);
		Preconditions.checkArgument(!email.isEmpty());
		for (String pattern : PATTERN_MAIL) {
			if (email.endsWith(pattern))
				return true;
		}
		return false;
	}

	/**
	 * 
	 * @param email the string to check
	 * @return true if it is an email and the host part correpsond to dauphine host
	 *         part
	 */
	public static boolean beAnDauphineEmail(String email) {
		Preconditions.checkNotNull(email);
		Preconditions.checkArgument(!email.isEmpty());
		if (!beAnEmail(email))
			return false;
		return isDauphineEmail(email);
	}

}
