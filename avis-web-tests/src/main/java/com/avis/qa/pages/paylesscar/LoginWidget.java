package com.avis.qa.pages.paylesscar;

import com.avis.qa.core.AbstractBasePage;
import lombok.extern.log4j.Log4j2;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.avis.qa.utilities.CommonUtils.TWO_SECONDS;
import static com.avis.qa.utilities.CommonUtils.threadSleep;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Log4j2
public class LoginWidget extends AbstractBasePage {

	@FindBy(xpath = "(//a[text()='Sign In'])[2]")
	private WebElement HeaderLoginButton;

	@FindBy(id = "username")
	private WebElement UserName;

	@FindBy(id = "password")
	private WebElement Password;

	@FindBy(xpath = "//button[@id='res-login-profile']")
	private WebElement LoginButton;

	public LoginWidget(WebDriver driver) {
		super(driver);
	}

	public LoginWidget login(Map<?, ?> testDataMap) {
		String emailNew = testDataMap.get("Username").toString();
		String passwordNew = testDataMap.get("Password").toString();

		waitForVisibilityOfElement(UserName);
		UserName.clear();
		UserName.sendKeys(emailNew);
		System.out.println(emailNew);
		waitForVisibilityOfElement(Password);
		Password.clear();
		Password.sendKeys(passwordNew);
		LoginButton.click();
		threadSleep(TWO_SECONDS);
		return this;
	}

	@Override
	public void isOnPage() {
		log.info("Verify Login Widget");
		waitForVisibilityOfElement(HeaderLoginButton);
	}
}
