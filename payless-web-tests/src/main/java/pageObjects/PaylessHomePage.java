package pageObjects;
import static com.avis.qa.utilities.CommonUtils.TEN_SECONDS;
import static com.avis.qa.utilities.CommonUtils.TWO_SECONDS;
import static com.avis.qa.utilities.CommonUtils.threadSleep;
import static org.testng.Assert.assertTrue;

import com.avis.qa.core.AbstractBasePage;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaylessHomePage extends AbstractBasePage {

	private static final String COUPON = "Coupon";

	public static String location;
	public PaylessHomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//h1[@class='blue']")
	private WebElement payLessBrandText;

	@FindBy(id = "PicLoc_value")
	private WebElement pickUpLocation;

	@FindBy(xpath = "((//div[@class='angucomplete-results'])[1]//div[@class='angucomplete-description'])[1] | ((//div[@id='PicLoc_dropdown'])[1]//div[@class='angucomplete-row'])[1]")
	private WebElement suggestionLocation;

	@FindBy(xpath = "//input[@ng-model='vm.reservationModel.pickUpDateDisplay']")
	private WebElement pickUpDate;

	@FindBy(xpath = "//*[contains(@name,'reservationModel.pickUpTime')]")
	private WebElement pickUpTime;

	@FindBy(xpath = "//input[@ng-model='vm.reservationModel.dropDateDisplay']")
	private WebElement returnDatePath;

	@FindBy(xpath = "//*[contains(@name,'reservationModel.dropTime')]")
	private WebElement dropOffTime;

	@FindBy(id = "diffLoc")
	private WebElement returnLocationCheckbox;

	@FindBy(id = "DropLoc_value")
	private WebElement enterReturnLocation;

	@FindBy(xpath = "(//div[@class='angucomplete-description'])[1]")
	private WebElement dropOffSuggestion;

	@FindBy(id = "age")
	private WebElement ageDropDown;

	@FindBy(xpath = "//select[@id='residency']")
	private WebElement residenceCountryDropdown;

	@FindBy(id = "couponpdn")
	private WebElement CouponCheckBox;

	@FindBy(id = "coupon")
	private WebElement CouponTextField;

	@FindBy(id = "awd")
	private WebElement pdnTextField;

	@FindBy(xpath = "(//*[contains(@ng-click,'getVehicles.submit')])[1]")
	private WebElement getRatesButton;

	@FindBy(xpath = "//span[@ng-bind-html='error.message | htmlFilter']")
	private WebElement warningMessage;

	@FindBy(xpath = "(//a[text()='Sign In'])[2]")
	private WebElement LoginLink;

	@FindBy(xpath = "//button[@id='res-login-profile']")
	private WebElement LoginButton;

	@FindBy(id = "username")
	private WebElement UserName;

	@FindBy(id = "password")
	private WebElement Password;

	@FindBy(xpath = "//a[text()='Reservations']")
	private WebElement reservationTab;

	@FindBy(xpath = "//a[text()='Make a Reservation']")
	private WebElement makeReservation;

	@FindBy(id = "//select[@id='residency']")
	private WebElement Residency;

	@FindBy(xpath = "//li[contains(@class,'welcome-menu')]/a[text()='Welcome, TEST ']")
	private WebElement signinVerify;

	@FindBy(id = "footer")
	private WebElement footerText;


	public void getRates(Map<?, ?> testDataMap) throws InterruptedException {
		assertTrue(payLessBrandText.getText().toString().contains("Payless"));

		if(testDataMap.get("HeadersVerify").toString().equalsIgnoreCase("Yes")) {
			System.out.println("headers verify");
			headers(testDataMap);
		}

		if(testDataMap.get("UserType").toString().equalsIgnoreCase("Guest")) {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOf(pickUpLocation));
			clickOn(pickUpLocation);
			String pickupLocations[] = testDataMap.get("PickUpLocation").toString().split(",");
			for (int i = 0; i < pickupLocations.length; i++) {
				if(i>0) {
					pickUpLocation.click();
					pickUpLocation.clear();
				}
				fillText(pickUpLocation,pickupLocations[i]);
				location = pickupLocations[i];
				clickOn(suggestionLocation);

				if (!testDataMap.get("PickUpDate").toString().equalsIgnoreCase("NA")) {
					clickOn(pickUpDate);
					pickUpDate.clear();
					fillText(pickUpDate,testDataMap.get("PickUpDate").toString());
				}
				if (!testDataMap.get("DropOffDate").toString().equalsIgnoreCase("NA")) {
					clickOn(returnDatePath);
					returnDatePath.clear();
					fillText(returnDatePath,testDataMap.get("DropOffDate").toString());
				}

				fillText(dropOffTime,testDataMap.get("DropOffTime").toString());
				if (!testDataMap.get("DropOffLocation").toString().equalsIgnoreCase("NA")) {
					clickOn(returnLocationCheckbox);
					fillText(enterReturnLocation,testDataMap.get("DropOffLocation").toString() );
					clickOn(dropOffSuggestion);
				}
				if (!testDataMap.get("Age").toString().equalsIgnoreCase("NA")) {
					clickOn(ageDropDown);
					fillText(ageDropDown,testDataMap.get("Age").toString());
				}
				if (!testDataMap.get("Country").toString().equalsIgnoreCase("NA")) {
					clickOn(residenceCountryDropdown);
					fillText(residenceCountryDropdown,testDataMap.get("Country").toString() );
					clickOn(residenceCountryDropdown);
				}
				if (!testDataMap.get(COUPON).toString().equalsIgnoreCase("NA")) {
					clickOn(CouponCheckBox);
					fillText(CouponTextField,testDataMap.get(COUPON).toString() );
				}
				if (!testDataMap.get("PDN").toString().equalsIgnoreCase("NA")) {
					clickOn(CouponCheckBox);
					clickOn(pdnTextField);
					fillText(pdnTextField,testDataMap.get("PDN").toString() );

				}
				if (!testDataMap.get("CouponPDN").toString().equalsIgnoreCase("NA")) {
					clickOn(CouponCheckBox);
					fillText(CouponTextField,testDataMap.get("Coupon1").toString() );
					clickOn(pdnTextField);
					fillText(pdnTextField,testDataMap.get("PDN1").toString());

				}
				if (!testDataMap.get("AGE1").toString().equalsIgnoreCase("NA")) {
					clickOn(getRatesButton);
//					Thread.sleep(768478);
					wait.until(ExpectedConditions.visibilityOf(warningMessage));
					assertTrue(warningMessage.getText().toString().contains("Based on your age selection, there are no cars available at this location"));			
					clickOn(ageDropDown);
					fillText(ageDropDown, testDataMap.get("AGE1").toString());
				}
				clickOn(getRatesButton);
				if (isVehicleAvailable()) {
					break;
				}



			}
		}
		if (testDataMap.get("UserType").toString().equalsIgnoreCase("Signin")) {
			clickOn(LoginLink);
			clickOn(UserName);
			fillText(UserName,testDataMap.get("Username").toString());
			clickOn(Password);
			fillText(Password,testDataMap.get("Password").toString());
			clickOn(LoginButton);
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOf(signinVerify));
			assertTrue(signinVerify.getText().toString().contains("Welcome"));	
			clickOn(makeReservation);
			clickOn(pickUpLocation);
			String pickupLocations[] = testDataMap.get("PickUpLocation").toString().split(",");
			System.out.println(pickupLocations.length);
			for (int i = 0; i < pickupLocations.length; i++) {
				if(i>0) {
					threadSleep(TEN_SECONDS);
					pickUpLocation.click();
					pickUpLocation.clear();
				}
				fillText(pickUpLocation,pickupLocations[i]);
				location = pickupLocations[i];
				clickOn(suggestionLocation);
				if (!testDataMap.get("PickUpDate").toString().equalsIgnoreCase("NA")) {
					clickOn(pickUpDate);
					pickUpDate.clear();
					fillText(pickUpDate,testDataMap.get("PickUpDate").toString());
				}
				if (!testDataMap.get("DropOffDate").toString().equalsIgnoreCase("NA")) {
					clickOn(returnDatePath);
					returnDatePath.clear();
					fillText(returnDatePath,testDataMap.get("DropOffDate").toString());
				}

				if (!testDataMap.get("PickUpTime").toString().equalsIgnoreCase("NA")) {
					fillText(pickUpTime,testDataMap.get("PickUpTime").toString());
				}
				if (!testDataMap.get("DropOffTime").toString().equalsIgnoreCase("NA")) {
					fillText(dropOffTime,testDataMap.get("DropOffTime").toString());
				}
				if (!testDataMap.get("DropOffLocation").toString().equalsIgnoreCase("NA")) {
					clickOn(returnLocationCheckbox);
					fillText(enterReturnLocation,testDataMap.get("DropOffLocation").toString() );
					clickOn(dropOffSuggestion);
				}
				if (!testDataMap.get("Age").toString().equalsIgnoreCase("NA")) {
					clickOn(ageDropDown);
					fillText(ageDropDown,testDataMap.get("Age").toString());
				}
				if (!testDataMap.get("Country").toString().equalsIgnoreCase("NA")) {
					clickOn(residenceCountryDropdown);
					threadSleep(TWO_SECONDS);
					fillText(residenceCountryDropdown,testDataMap.get("Country").toString() );
					clickOn(residenceCountryDropdown);
				}
				if (!testDataMap.get(COUPON).toString().equalsIgnoreCase("NA")) {
					clickOn(CouponCheckBox);
					fillText(CouponTextField,testDataMap.get(COUPON).toString() );
				}
				if (!testDataMap.get("PDN").toString().equalsIgnoreCase("NA")) {
					clickOn(CouponCheckBox);
					clickOn(pdnTextField);
					fillText(pdnTextField,testDataMap.get("PDN").toString() );

				}
				if (!testDataMap.get("AGE1").toString().equalsIgnoreCase("NA")) {
					clickOn(getRatesButton);
					threadSleep(TEN_SECONDS);
					assertTrue(warningMessage.getText().toString().contains("Based on your age selection, there are no cars available at this location.!"));			
					clickOn(ageDropDown);
					fillText(ageDropDown, testDataMap.get("AGE1").toString());

				}
				if (!testDataMap.get("Country").toString().equalsIgnoreCase("NA")) {
					clickOn(Residency);
				}
				clickOn(getRatesButton);
				if (isVehicleAvailable()) {
					break;
				}
			}
			if (testDataMap.get("UserType").toString().equalsIgnoreCase("Verify")) {
				headers(testDataMap);
			}
		}
	}

	@Override
	public void isOnPage() {

	}

	public boolean isVehicleAvailable() throws InterruptedException {
		boolean isVehiclePresent = false;
		Thread.sleep(10000);
		List<WebElement> errorMessage = driver.findElements(By.id("warning-msg-err"));
		List<WebElement> selectCarTitle = driver.findElements(By.xpath("(//a[@class='modify-link']/span)[2]"));
		System.out.println(errorMessage.size());
		System.out.println(selectCarTitle.size());
		if(errorMessage.size()==0 && selectCarTitle.size()>0) {
			isVehiclePresent = true;
		}
		return isVehiclePresent;

	}


	public void headers(Map<?, ?> testDataMap) {
		if (!testDataMap.get("Headers").toString().equalsIgnoreCase("NA")) {
			List<WebElement> elements = driver.findElements(By.cssSelector("#nav-offcanvas li.dropdown>a"));
			String[] header = testDataMap.get("Headers").toString().split(",");
			for (int i = 0; i < elements.size(); i++) {
				WebElement listOfHeader = elements.get(i);
				for (int j = 0; j < header.length; j++) {
					if (listOfHeader.getText().trim().equals(header[j])) {
						assertTrue(listOfHeader.getText().toString().contains(header[j]));
						listOfHeader.click();
						break;
					}else {
					}
				}
			}
			boolean FTText = footerText.isDisplayed();
			System.out.println("Footer Text is :"+ FTText);
		}

	}
}