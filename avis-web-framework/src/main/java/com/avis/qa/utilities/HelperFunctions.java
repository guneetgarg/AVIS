package com.avis.qa.utilities;

import java.util.Random;

import org.openqa.selenium.WebDriver;

import lombok.extern.log4j.Log4j2;

//import static core.Configuration.DEFAULT_IMPLICIT_TIMEOUT;

/**
 * Helper class contains all the element related generic methods
 *
 * @author ikumar
 */
@Log4j2
public class HelperFunctions {

	private final WebDriver driver;

	public HelperFunctions(WebDriver driver) {
		this.driver = driver;
	}

	public static String getAlphaNumericString(int n) {

		// lower limit for LowerCase Letters
		int lowerLimit = 97;
		// lower limit for LowerCase Letters
		int upperLimit = 122;
		Random random = new Random();
		// Create a StringBuffer to store the result
		StringBuffer r = new StringBuffer(n);
		for (int i = 0; i < n; i++) {
			// take a random value between 97 and 122
			int nextRandomChar = lowerLimit + (int) (random.nextFloat() * (upperLimit - lowerLimit + 1));

			// append a character at the end of bs
			r.append((char) nextRandomChar);
		}

		// return the resultant string
		return r.toString();
	}
}