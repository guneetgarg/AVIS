package pageObjects;

import static com.avis.qa.utilities.CommonUtils.TWO_SECONDS;
import static com.avis.qa.utilities.CommonUtils.FIVE_SECONDS;
import static com.avis.qa.utilities.CommonUtils.threadSleep;
import static org.testng.Assert.assertTrue;

import com.avis.qa.core.AbstractBasePage;
import com.avis.qa.utilities.HelperFunctions;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PaylessSignUpPage extends AbstractBasePage {

	public static String paylessProfiles;

	public PaylessSignUpPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//h1[@class='blue']")
	private WebElement payLessBrandText;

	@FindBy(xpath = "(//a[text()='Sign In'])[2]")
	private WebElement LoginLink;

	@FindBy(xpath = "//button[contains(text(),'Create an Account')]")
	private WebElement createAccount;

	@FindBy(id = "PicLoc_value")
	private WebElement pickUpLocation;

	@FindBy(xpath = "//input[@name='firstname']")
	private WebElement firstName;

	@FindBy(xpath = "//input[@name='lastname']")
	private WebElement lastName;

	@FindBy(id = "addressline1")
	private WebElement mailingAddress;

	@FindBy(xpath = "//input[@name='city']")
	private WebElement city;

	@FindBy(id = "state")
	private WebElement state;

	@FindBy(id = "zip")
	private WebElement zipCode;

	@FindBy(id = "dobMonth")
	private WebElement month;

	@FindBy(id = "dobDate")
	private WebElement date;

	@FindBy(id = "dobYear")
	private WebElement year;

	@FindBy(id = "email")
	private WebElement email;

	@FindBy(xpath = "//div[@id='manualUserName']/input[@name='username']")
	private WebElement username;

	@FindBy(id = "PasswordHide")
	private WebElement password;

	@FindBy(xpath = "//a[contains(text(),'Submit')]")
	private WebElement sumbitButton;

	@FindBy(xpath = "//ul[@class='nav navbar-nav']")
	private WebElement dashboardVerify;

	@FindBy(id = "username")
	private WebElement UserName;

	@FindBy(id = "password")
	private WebElement Password;

	@FindBy(xpath = "//button[@id='res-login-profile']")
	private WebElement LoginButton;
	

	public void signup(Map testDataMap) {
		assertTrue(payLessBrandText.getText().toString().contains("Payless"));
		if (testDataMap.get("UserType").toString().equalsIgnoreCase("Signup")) {
			clickOn(LoginLink);
			threadSleep(TWO_SECONDS);
			clickOn(createAccount);
			fillText(firstName, testDataMap.get("FirstName").toString());
			fillText(lastName, testDataMap.get("LastName").toString());
			fillText(mailingAddress, testDataMap.get("MailingAddress").toString());
			fillText(city, testDataMap.get("City").toString());
			fillText(state, testDataMap.get("State").toString());
			fillText(zipCode, testDataMap.get("ZipCode").toString());
			fillText(month, testDataMap.get("Month").toString());
			fillText(date, testDataMap.get("Date").toString());
			fillText(year, testDataMap.get("Year").toString());
			String emailAddress = "Payless"+HelperFunctions.getAlphaNumericString(5)+"@getnada.com";
			email.click();
			email.sendKeys(emailAddress);
			String paylessProfile = "Payless"+HelperFunctions.getAlphaNumericString(2)+"123";
			username.click();
			username.sendKeys(paylessProfile);
			paylessProfiles = paylessProfile;
			System.out.println(paylessProfile);
			fillText(password, testDataMap.get("Password").toString());
			clickOn(sumbitButton);
			assertTrue(dashboardVerify.getText().toString().contains("Dashboard"));

		}if (testDataMap.get("UserType").toString().equalsIgnoreCase("Signin")) {
			clickOn(LoginLink);
			threadSleep(TWO_SECONDS);
			clickOn(UserName);
			UserName.sendKeys(paylessProfiles);
			clickOn(Password);
			fillText(Password,testDataMap.get("Password").toString());
			clickOn(LoginButton);
			threadSleep(FIVE_SECONDS);

		}
	}

	@Override
	public void isOnPage() {

	}
}
