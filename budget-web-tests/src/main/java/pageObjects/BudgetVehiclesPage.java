package pageObjects;

import static com.avis.qa.utilities.CommonUtils.TEN_SECONDS;
import static com.avis.qa.utilities.CommonUtils.threadSleep;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.avis.qa.core.AbstractBasePage;
import com.avis.qa.core.Configuration;
import com.avis.qa.utilities.HelperFunctions;

public class BudgetVehiclesPage extends AbstractBasePage {

	public BudgetVehiclesPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(css = "li[title='Select a Car']")
	private WebElement selectACarText;

	String PayLater = "//*[@ng-bind='car.carGroup' and text()='$DNM']/../parent::div/following-sibling::div//a[@id='res-vehicles-pay-later']";
	String selectString = "//*[@ng-bind='car.carGroup' and text()='$DNM']/../parent::div/following-sibling::div//a";
	String PayNow = "//*[@ng-bind='car.carGroup' and text()='$DNM']/../parent::div/following-sibling::div//a[@id='res-vehicles-pay-now']";

	//	@FindBy(xpath = "//p[@class='payamntp']")
	//	private WebElement carTypeamount;

	//	@FindBy(xpath = "(//a[@id='res-vehicles-pay-now'])[1] | (//a[contains(text(),'Select')])[4]|(//a[contains(text(),'Select')])[3]")
	//	private WebElement PayNow;

	@FindBy(xpath = "(//div[@class='location-info'])[1] | (//div[@class='summary-location'])[1]")
	private WebElement pickUpLocationVerify;

	@FindBy(xpath = "(//p[@class='payamntp']/span)[1] | (//sup[@class='currencySymbol'])[1]")
	private WebElement currencySymbol;

	@FindBy(xpath = "(//div[@class='location-info'])[1]")
	private WebElement PickUpLocValue;

	@FindBy(xpath = "(//div[@class='location-info'])[2]")
	private WebElement ReturnLocValue;

	@FindBy(xpath = "(//div[@class='day-time-info'])[1]")
	private WebElement PickupDateTime;

	@FindBy(xpath = "(//div[@class='day-time-info'])[2]")
	private WebElement ReturnDateTime;

	@FindBy(xpath = "//div[@class='info-key-drop-text']")
	private WebElement verifyKeydropmessage;

	//	@FindBy(xpath = "//div[@class='payatcntr col-xs-6']//span[@ng-bind-html='car.payLaterRate.currencySymbol']")
	@FindBy(xpath = "//span[@ng-bind-html='car.payLaterRate.currencySymbol']")
	private WebElement verifySymbol;

	@FindBy(xpath = "//a[@ng-bind='vm.userCurrency']")
	private WebElement Currency;

	@FindBy(xpath = "//li[@ng-repeat='option in vm.uniqueCurrencylist'][2]")
	private WebElement CurrencyList;

	@FindBy(xpath = "//a[@class='custon-drop-off-btn']")
	private WebElement errorMsgLocation;

	@FindBy(xpath = "//h3[@ng-bind='car.carGroup']")
	private WebElement CarType;

	@FindBy(xpath = "//p[@class='payamntp']")
	private WebElement Carprice;

	@FindBy(xpath = "//span[@class='four-seats-feat']")
	private WebElement NumberofSeats; 

	@FindBy(xpath = "//a[@class='custon-drop-off-btn']")
	private WebElement couponKeydropMsg;

	@FindBy(xpath = "//p[@class='coupnelg']")
	private WebElement couponEligible;

	@FindBy(xpath = "//div[@class='couponelghdr for-no-cou-align-img']")
	private WebElement USAASavingMessage;

	@FindBy(xpath = "//div[@class='pull-right vehTxtAlnRgt veh-LtR-gtPad-Null vehSelectBox currencyBoxdrop']")
	private WebElement locationResidenceverify;

	@FindBy(id = "res-vehicles-filter-by-vehicle-type")
	private WebElement allVehiclesClick;

	@FindBy(xpath = "//li[@vehiclecode='all']")
	private WebElement allVehicles;

	@FindBy(xpath = "//li[@vehiclecode='small']")
	private WebElement smallVehicles;

	@FindBy(xpath = "//li[@vehiclecode='luxury']")
	private WebElement luxuryVehicles;

	@FindBy(xpath = "//li[@vehiclecode='suv']")
	private WebElement suvVehicles;

	@FindBy(xpath = "//li[@vehiclecode='truck']")
	private WebElement truckVehicles;

	@FindBy(id = "res-vehicles-sort")
	private WebElement vehiclesSort;

	@FindBy(xpath = "//li[@ng-click='vm.sortVehicle(sort)'][2]")
	private WebElement mileageHightoLow;

	@FindBy(xpath = "//li[@ng-click='vm.sortVehicle(sort)'][1]")
	private WebElement mileageLowtoHigh;

	@FindBy(xpath = "//li[@ng-click='vm.sortVehicle(sort)'][3]")
	private WebElement mileageNoOfSeats;

	@FindBy(xpath = "//p[@class='coupnelg']")
	private WebElement bcdEligible;

	@FindBy(xpath = "//p[@class='discount-dropdown']")
	private WebElement bcdDiscountDropdown;

	@FindBy(xpath = "//span[@class='addinlinfo']")
	private WebElement strikeThroughDiscount;

	@FindBy(xpath = "//div[@class='modify-rental step2summary hidden-xs']//a[@class='modify-link']")
	private WebElement modifyRentalDetails;

	@FindBy(xpath = "//div[@class='modal-dialog modal-popup reservation-modal renter-summary-detail']")
	private WebElement modifyRentalDetailsPopup;

	@FindBy(xpath = "(//input[@id='PicLoc_value'])[1]")
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

	@FindBy(xpath = "//*[contains(@id,'DropLoc_value')]")
	private WebElement dropOffLocation;

	@FindBy(id = "DropLoc_value")
	private WebElement enterReturnLocation;

	@FindBy(xpath = "(//div[@class='angucomplete-description'])[1]")
	private WebElement dropOffSuggestion;

	@FindBy(xpath = "//div[@class='res-mainContent resDesktopView']//div[@class='modal-footer offers-landing-selbtn']//div[@class='step2-renter-summary-detail-btn hidden-xs']//button[@aria-label='Update']")
	private WebElement updateButton;

	@FindBy(xpath = "//div[@class='bx-row bx-row-submit bx-row-submit-no  bx-row-9shqINo bx-element-1819757-9shqINo']//button[@data-click='close']")
	private WebElement popupClose;

	String carType = "//div[@ng-class=\"{'dualPayOption':!car.displayRate.displaySelect}\"]//h3[text()='$DNM']";

	String carAmountPayLater= "//*[@ng-bind='car.carGroup' and text()='$DNM']/../parent::div/following-sibling::div//child::p";

	String carAmountPayNow= "//*[@ng-bind='car.carGroup' and text()='$DNM']/../parent::div/following-sibling::div//child::p[@class='payamntr']";

	static String SELECTPRICE;

	public void chooseVehicles(Map testDataMap) {
		WebDriverWait wait= new WebDriverWait(driver, 60);
		if(!testDataMap.get("PickUpLocation").toString().equalsIgnoreCase("NA")) {
			try {
				wait.until(ExpectedConditions.visibilityOf(selectACarText));
				assertTrue(selectACarText.getText().toString().contains("Select a Car"));
				assertTrue(pickUpLocationVerify.getText().toString().contains(testDataMap.get("PickUpLocation").toString()));
				//				Validate CAr Type
				CarType.isDisplayed();
				Carprice.isDisplayed();
				NumberofSeats.isDisplayed();
				if(testDataMap.get("VerifyKeyDropMessage").toString().equalsIgnoreCase("Yes")) {
					assertTrue(couponKeydropMsg.getText().contains("The location you selected is closed at the time of drop off, but a key-drop box is available for your convenience."));
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			if(Configuration.DOMAIN.equalsIgnoreCase("US") ) {
				verifySymbol.isDisplayed();
			}
		}
		if(!testDataMap.get("DropOffLocation").toString().equalsIgnoreCase("NA")) {
			assertTrue(ReturnLocValue.getText().toString().contains(testDataMap.get("DropOffLocation").toString()));
		}
		if(!testDataMap.get("Coupon").toString().equalsIgnoreCase("NA")) {
			couponEligible.isDisplayed();
		}
		if(!testDataMap.get("Currency").toString().equalsIgnoreCase("NA")) {
			wait.until(ExpectedConditions.visibilityOf(Currency));
			clickOn(Currency);
			clickOn(CurrencyList);
			wait.until(ExpectedConditions.visibilityOf(Currency));
		}
		if (testDataMap.get("BCD").toString().contains("Y1")) {
			System.out.println("BCD Displayed");
			USAASavingMessage.getText().contains("Your USAA savings are reflected below.");
			//			USAASavingMessage.isDisplayed();
			bcdDiscountDropdown.isDisplayed();
			bcdEligible.getText().contains("BCD Eligible");
			strikeThroughDiscount.isDisplayed();
			locationResidenceverify.isDisplayed();
		} 
		if(testDataMap.get("ModifyRentalDetails").toString().equalsIgnoreCase("Yes")) {
			modifyRentalDetails.isDisplayed();
			modifyRentalDetails.click();
			modifyRentalDetailsPopup.isDisplayed();
			clickOn(pickUpLocation);
			pickUpLocation.clear();
			fillText(pickUpLocation, testDataMap.get("ModifyPickUpLoc").toString());
			clickOn(suggestionLocation);
			clickOn(pickUpDate);
			pickUpDate.clear();
			fillText(pickUpDate, testDataMap.get("ModifyPickUpDate").toString());
			clickOn(returnDatePath);
			returnDatePath.clear();
			fillText(returnDatePath, testDataMap.get("ModifyDropOffDate").toString());
			try {
				popupClose.click();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Popup not present");
			}
			//			popupClose.click();

			updateButton.click();
		}

		if(testDataMap.get("Paylater&Paynow").toString().equalsIgnoreCase("Paylater")) {
			System.out.println("******************88");
			System.out.println(testDataMap.get("CarType").toString());
			
			String payLaterXpathString=HelperFunctions.createDynamicLocator(PayLater, testDataMap.get("CarType").toString());
			String selectXpathString=HelperFunctions.createDynamicLocator(selectString, testDataMap.get("CarType").toString());
			
			List<WebElement> payLaterList = driver.findElements(By.xpath(payLaterXpathString));
			List<WebElement> selectList = driver.findElements(By.xpath(selectXpathString));
			WebElement payLater = null;
			if(payLaterList.size()>0) {
				
				payLater=driver.findElement(By.xpath(payLaterXpathString));
			}
			
			if(selectList.size()>0) {
				payLater=driver.findElement(By.xpath(selectXpathString));
				
			}
			
			driver.findElement(By.xpath(HelperFunctions.createDynamicLocator(carType,testDataMap.get("CarType").toString()))).click();

//			WebElement payLater = driver.findElement(By.xpath(HelperFunctions.createDynamicLocator(PayLater, testDataMap.get("CarType").toString())));

			wait.until(ExpectedConditions.visibilityOf(payLater));
			Actions actions = new Actions(driver);
			actions.moveToElement(payLater);
			actions.perform();
			payLater.isDisplayed();
			payLater.isEnabled();
			if((Configuration.DOMAIN).equalsIgnoreCase("US")) {
				SELECTPRICE = driver.findElement(By.xpath(HelperFunctions.createDynamicLocator(carAmountPayLater,testDataMap.get("CarType").toString()))).getText().replace("$", "");
				System.out.println(SELECTPRICE);
			}
			clickOn(payLater);

		}
		if(testDataMap.get("Paylater&Paynow").toString().equalsIgnoreCase("PayNow")) {
			driver.findElement(By.xpath(HelperFunctions.createDynamicLocator(carType,testDataMap.get("CarType").toString()))).click();
			WebElement payNow = driver.findElement(By.xpath(HelperFunctions.createDynamicLocator(PayNow, testDataMap.get("CarType").toString())));
			wait.until(ExpectedConditions.visibilityOf(payNow));
			Actions actions = new Actions(driver);
			actions.moveToElement(payNow);
			actions.perform();
			payNow.isDisplayed();
			payNow.isEnabled();
			if((Configuration.DOMAIN).equalsIgnoreCase("US")) {
				SELECTPRICE = driver.findElement(By.xpath(HelperFunctions.createDynamicLocator(carAmountPayNow,testDataMap.get("CarType").toString()))).getText().replace("$", "");
				System.out.println(SELECTPRICE);
			}
			clickOn(payNow);
		}
		if(!testDataMap.get("Age1").toString().equalsIgnoreCase("NA")) {
			allVehiclesClick.click();
			allVehicles.isDisplayed();
			smallVehicles.isDisplayed();
			luxuryVehicles.isDisplayed();
			suvVehicles.isDisplayed();
			truckVehicles.isDisplayed();
			allVehiclesClick.click();
			threadSleep(TEN_SECONDS);
			vehiclesSort.click();
			vehiclesSort.isDisplayed();
			//			vehiclesSort.click();
			mileageHightoLow.click();
			vehiclesSort.click();
			mileageLowtoHigh.click();
			vehiclesSort.click();
			mileageNoOfSeats.click();

		}

		//		if(testDataMap.get("ModifyRentalDetails").toString().equalsIgnoreCase("Yes")) {
		//			modifyRentalDetails.isDisplayed();
		//			modifyRentalDetails.click();
		//			modifyRentalDetailsPopup.isDisplayed();
		//			clickOn(pickUpLocation);
		//			pickUpLocation.clear();
		//			fillText(pickUpLocation, testDataMap.get("").toString());
		//			wait.until(ExpectedConditions.visibilityOf(suggestionLocation));
		//			clickOn(suggestionLocation);
		//			updateButton.click();
		//			
		//		}

	}

	public void extras(Map testDataMap) {
		if(testDataMap.get("Extras").toString().equalsIgnoreCase("Yes")){
			modifyRentalDetails.click();
			WebElement payLater = driver.findElement(By.xpath(HelperFunctions.createDynamicLocator(PayLater, testDataMap.get("CarType").toString())));
			wait.until(ExpectedConditions.visibilityOf(payLater));
			helper.scrollToElement(payLater);
			payLater.isDisplayed();
			payLater.isEnabled();
			payLater.click();
		}
	}
	@Override
	public void isOnPage() {
		// TODO Auto-generated method stub
	}
}