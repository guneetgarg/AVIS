package com.avis.qa.pages;

import static com.avis.qa.utilities.CommonUtils.TWO_SECONDS;
import static com.avis.qa.utilities.CommonUtils.FIVE_SECONDS;
import static com.avis.qa.utilities.CommonUtils.TEN_SECONDS;
import static com.avis.qa.utilities.CommonUtils.threadSleep;
import static org.testng.Assert.assertTrue;

import com.avis.qa.constants.TextComparison;
import com.avis.qa.core.AbstractBasePage;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaylessHomePage extends AbstractBasePage {

	private static final String COUPON = "Coupon";

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

	@FindBy(id = "residency")
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

	public void getRates(Map testDataMap) {
		assertTrue(payLessBrandText.getText().toString().contains("Payless"));
		if(testDataMap.get("UserType").toString().equalsIgnoreCase("Guest")) {
		clickOn(pickUpLocation);
		fillText(pickUpLocation,testDataMap.get("PickUpLocation").toString());
		clickOn(suggestionLocation);
		fillText(pickUpDate,testDataMap.get("PickUpDate").toString());
		fillText(pickUpTime,testDataMap.get("PickUpTime").toString());
		fillText(returnDatePath,testDataMap.get("DropOffDate").toString());
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
		if (!testDataMap.get(COUPON).toString().equalsIgnoreCase("NA")) {
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
		if (!testDataMap.get("AGE1").toString().equalsIgnoreCase("NA")) {
			clickOn(getRatesButton);
			assertTrue(warningMessage.getText().toString().contains("Based on your age selection, there are no cars available at this location"));			
			clickOn(ageDropDown);
			fillText(ageDropDown, testDataMap.get("AGE1").toString());

		}
		clickOn(getRatesButton);
	}
		if (testDataMap.get("UserType").toString().equalsIgnoreCase("Signin")) {
			clickOn(LoginLink);
			fillText(UserName,testDataMap.get("Username").toString());
			fillText(Password,testDataMap.get("Password").toString());
			clickOn(LoginButton);
			threadSleep(TEN_SECONDS);
			clickOn(reservationTab);
			clickOn(makeReservation);
			clickOn(pickUpLocation);
			fillText(pickUpLocation,testDataMap.get("PickUpLocation").toString());
			clickOn(suggestionLocation);
			fillText(pickUpDate,testDataMap.get("PickUpDate").toString());
			fillText(pickUpTime,testDataMap.get("PickUpTime").toString());
			fillText(returnDatePath,testDataMap.get("DropOffDate").toString());
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
		}
		}

	@Override
	public void isOnPage() {

		// TODO Auto-generated method stub

	}

}
