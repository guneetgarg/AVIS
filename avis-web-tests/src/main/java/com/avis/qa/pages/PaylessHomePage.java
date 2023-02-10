package com.avis.qa.pages;

import static com.avis.qa.utilities.CommonUtils.TWO_SECONDS;
import static com.avis.qa.utilities.CommonUtils.FIVE_SECONDS;
import static com.avis.qa.utilities.CommonUtils.TEN_SECONDS;
import static com.avis.qa.utilities.CommonUtils.threadSleep;
import static org.testng.Assert.assertTrue;
import com.avis.qa.core.AbstractBasePage;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PaylessHomePage extends AbstractBasePage {

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

	public void getRates(Map testDataMap) {
		assertTrue(payLessBrandText.getText().toString().contains("Payless"));
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
		}
		if (!testDataMap.get("Coupon").toString().equalsIgnoreCase("NA")) {
			clickOn(residenceCountryDropdown);
			fillText(residenceCountryDropdown,testDataMap.get("Country").toString() );
			clickOn(residenceCountryDropdown);
		}
		if (!testDataMap.get("Coupon").toString().equalsIgnoreCase("NA")) {
			clickOn(CouponCheckBox);
			fillText(CouponTextField,testDataMap.get("Coupon").toString() );
		}
		if (!testDataMap.get("PDN").toString().equalsIgnoreCase("NA")) {
			clickOn(pdnTextField);
			fillText(pdnTextField,testDataMap.get("PDN").toString() );

		}
		clickOn(getRatesButton);
	}

	@Override
	public void isOnPage() {

		// TODO Auto-generated method stub

	}

}
