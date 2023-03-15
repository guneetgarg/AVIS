package pageObjects;

import static com.avis.qa.utilities.CommonUtils.TWO_SECONDS;
import static com.avis.qa.utilities.CommonUtils.threadSleep;
import static org.testng.Assert.assertTrue;

import com.avis.qa.core.AbstractBasePage;
import com.avis.qa.utilities.HelperFunctions;

import java.util.Map;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PaylessSignUp extends AbstractBasePage {


	public PaylessSignUp(WebDriver driver) {
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
	
	
	
	
	
	
	public void signup(Map testDataMap) {
		assertTrue(payLessBrandText.getText().toString().contains("Payless"));
		if (testDataMap.get("UserType").toString().equalsIgnoreCase("Signin")) {
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
			String paylessProfile = "Payless"+HelperFunctions.getAlphaNumericString(2)+"183";
			username.click();
			username.sendKeys(paylessProfile);
			fillText(password, testDataMap.get("Password").toString());
			clickOn(sumbitButton);
			assertTrue(dashboardVerify.getText().toString().contains("Dashboard"));
		}
		
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


	@Override
	public void isOnPage() {
		// TODO Auto-generated method stub
		
	}
}
