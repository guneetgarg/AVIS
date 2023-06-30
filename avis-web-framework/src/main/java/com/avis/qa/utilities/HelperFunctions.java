package com.avis.qa.utilities;

import java.util.Random;
import org.openqa.selenium.WebDriver;
import lombok.extern.log4j.Log4j2;

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

		int lowerLimit = 97;
		int upperLimit = 122;
		Random random = new Random();
		StringBuffer r = new StringBuffer(n);
		for (int i = 0; i < n; i++) {
			int nextRandomChar = lowerLimit + (int) (random.nextFloat() * (upperLimit - lowerLimit + 1));
			r.append((char) nextRandomChar);
		}

		return r.toString();
	}


	public static String createDynamicLocator(String locator, String dynamicValue) {
		if (locator.contains("$DNM")) {
			locator = locator.replace("$DNM", dynamicValue);
		}
		return locator;
	}
}