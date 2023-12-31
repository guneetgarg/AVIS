package pageObjects;

import static com.avis.qa.utilities.CommonUtils.TEN_SECONDS;
import static com.avis.qa.utilities.CommonUtils.TWO_SECONDS;
import static com.avis.qa.core.Configuration.URL;
import static com.avis.qa.utilities.CommonUtils.FIVE_SECONDS;

import static com.avis.qa.utilities.CommonUtils.threadSleep;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.avis.qa.core.AbstractBasePage;
import com.avis.qa.core.Configuration;
import com.avis.qa.utilities.HelperFunctions;

public class BudgetHomePage extends AbstractBasePage {

	public BudgetHomePage(WebDriver driver) throws InterruptedException {
		super(driver);
	}

	private static final String COUPON = "Coupon";

	@FindBy(xpath = "//a[@class='navbar-brand']")
	private WebElement budgetBrandText;

	@FindBy(xpath = "//div[@class='bx-wrap']")
	private WebElement AdOverLayDiv;

	@FindBy(xpath = "//a[contains(text(),'Reservations')]")
	private WebElement reservation;

	@FindBy(xpath = "//a[@href='/en/reservation/make-reservation.html']")
	private WebElement makeaReservation;

	@FindBy(xpath = "//a[@href='/en/reservation/make-reservation']")
	private WebElement makeaReservation1;

	@FindBy(xpath = "(//input[@id='PicLoc_value'])[1]")
	private WebElement pickUpLocation;

	@FindBy(xpath = "((//div[@class='angucomplete-results'])[1]//div[@class='angucomplete-description'])[1] | ((//div[@id='PicLoc_dropdown'])[1]//div[@class='angucomplete-row'])[1]")
	private WebElement suggestionLocation;

//	@FindBy(xpath = "//div[@class='col-lg-12 res-inputFldFst']//div[@class='col-xs-3 res-inputFld dateImg']//input[@type='text']")
//	private WebElement pickUpDate;
	String pickUpDate="//div[@class='col-lg-12 res-inputFldFst']//div[@class='col-xs-3 res-inputFld dateImg']//input[@type='text']";

	String selectDate = "//td[@data-handler='selectDay']//a[contains(text(),'$DNM')]";
	
	@FindBy(xpath = "//*[contains(@name,'reservationModel.pickUpTime')]")
	private WebElement pickUpTime;
	
	String returnDatePath="//input[@ng-model='vm.reservationModel.dropDateDisplay']";

	@FindBy(xpath = "//*[contains(@name,'reservationModel.dropTime')]")
	private WebElement dropOffTime;

	@FindBy(xpath = "//*[contains(@id,'DropLoc_value')]")
	private WebElement dropOffLocation;

	@FindBy(id = "DropLoc_value")
	private WebElement enterReturnLocation;

	@FindBy(xpath = "(//div[@class='angucomplete-description'])[1]")
	private WebElement dropOffSuggestion;

	@FindBy(xpath = "(//*[contains(@ng-click,'getVehicles.submit')])[1]")
	private WebElement selectMyCarButton;

	@FindBy(id = "reservationModel.personalInfoRQ.age")
	private WebElement ageDropDown;

	@FindBy(xpath = "(//*[@id='reservationModel.personalInfoRQ.residency'])[1]")
	private WebElement selectCountry;

	@FindBy(id = "couponpdn")
	private WebElement CouponCheckBox;

	@FindBy(id = "coupon")
	private WebElement CouponTextField;

	@FindBy(id = "awd")
	private WebElement pdnTextField;

	@FindBy(xpath = "//div[@class='res-discFld form-controlD']")
	private WebElement offerCodes;

	@FindBy(xpath = "//div[@class='res-wizardFld form-controlD']")
	private WebElement addCustomerID;

	@FindBy(xpath = "//input[@aria-label='Res-wizard Number']")
	private WebElement enterCustomerID;

	@FindBy(xpath = "//input[@id='res-home-lastName']")
	private WebElement enterLastName;

	@FindBy(xpath = "//*[@id='awd']")
	private WebElement AWDOrBCDOrPDN_TextField;

	@FindBy(xpath = "//input[@id='partnerMembershipId']")
	private WebElement membershipTextField;

	@FindBy(xpath = "(//a[text()='Reservations'])[1]")
	private WebElement ReservationsTab;

	@FindBy(xpath = "//a[text()='View / Modify / Cancel']")
	private WebElement Reservation_ViewModifyCancelLink;

	@FindBy(xpath = "//a[@title='Next']")
	private WebElement nextMonth;

	@FindBy(xpath = "//input[@aria-label='corporate email address']")
	private WebElement corporateEmailId;

	@FindBy(xpath = "((//a[@id='res-login-profile'])[2]) | ((//a[contains(text(),'Sign In')])[1])")
	private WebElement HeaderLoginButton2;

	@FindBy(xpath = "(//a[contains(text(),'Sign In')])[2] | (//a[@id='res-login-profile'])[2]")
	private WebElement HeaderLoginButton;

	@FindBy(xpath = "(//ul[@class='header-secondary']//li//a[@id='res-login-profile'])[1]")
	private WebElement HeaderLoginButtonBudget;

	@FindBy(id = "username")
	private WebElement UserName;

	@FindBy(id = "password")
	private WebElement Password;

	@FindBy(id = "res-login-profile")
	private WebElement LoginButton;

	@FindBy(xpath = "//input[@id='rememberme']")
	private WebElement RememberMeToggle;

	@FindBy(xpath = "//span[@class='close-FC g-icon']")
	public WebElement PopupBudget;

	@FindBy(xpath = "//input[@name='otp']")
	private WebElement OTPtextBox;

	@FindBy(xpath = "//button[@id='otp_submit']")
	private WebElement OTPSubmitButton;

	@FindBy(id = "warning-msg-err")
	private WebElement soldOutWarningMessage;

	@FindBy(xpath = "(//span[@class='step-title'])[2]")
	private WebElement selectCarTitle;

	@FindBy(xpath = "//div[@class='col-lg-12 res-PageError']//span[@class='mainErrorText info-error-msg-text'][1]")
	private WebElement errorMessage90days;

	@FindBy(xpath = "//div[@ng-show='vm.getVehicles.validated']//span[@class='mainErrorText info-error-msg-text']")
	private WebElement errorMessageverify;

	@FindBy(xpath = "//div[@class='info-key-drop-text']")
	private WebElement verifyKeydropmessage;
	
	@FindBy(xpath = "//div[@class='row res-inputFldPrt res-inputFldBack']")
	private WebElement ReservationWidget;
	
	@FindBy(xpath = "//span[contains(text(),'Sorry!, You are Younger than the minimum AGE required for Renting at this location.')]")
	private WebElement minimumAgeError;
	
	static int TotalDays;

	public void selectYourCar(Map<?, ?> testDataMap) throws InterruptedException {
		driver.findElement(By.xpath("//button[@aria-label='Close']")).click();
		WebDriverWait wait = new WebDriverWait(driver, 40);
		String dropOffdate1=testDataMap.get("DropOffDate").toString();
		String pickUpDate1=testDataMap.get("PickUpDate").toString();
		try {
			wait.until(ExpectedConditions.visibilityOf(budgetBrandText));
		} catch (Exception e) {
			System.out.println(e);
			driver.navigate().refresh();
			wait.until(ExpectedConditions.visibilityOf(budgetBrandText));
		}
		
		Thread.sleep(TEN_SECONDS);
		List<WebElement> ele = driver.findElements(By.xpath("//button[@data-click='close']"));

		if(ele.size()>0) {
			threadSleep(TEN_SECONDS); 
			driver.findElement(By.xpath("//button[@data-click='close']")).click();
		}


		if (testDataMap.get("UserType").toString().equalsIgnoreCase("Guest")) {	
			wait.until(ExpectedConditions.visibilityOf(reservation));
			clickOn(reservation);
			if(Configuration.ENVIRONMENT.equalsIgnoreCase("qa")){
				wait.until(ExpectedConditions.visibilityOf(makeaReservation1));
				clickOn(makeaReservation1);		
			}
			if(Configuration.ENVIRONMENT.equalsIgnoreCase("uat")){
				wait.until(ExpectedConditions.visibilityOf(makeaReservation1));
				clickOn(makeaReservation1);	
			}
			if(Configuration.ENVIRONMENT.equalsIgnoreCase("ci11")){
				wait.until(ExpectedConditions.visibilityOf(makeaReservation));
				clickOn(makeaReservation);
			}
			if (!testDataMap.get("PickUpLocation").toString().equalsIgnoreCase("NA")) {
				try {
					//Validation of Reservation Widget
					wait.until(ExpectedConditions.visibilityOf(ReservationWidget));
					ReservationWidget.isDisplayed();
					wait.until(ExpectedConditions.visibilityOf(pickUpLocation));
					clickOn(pickUpLocation);
					fillText(pickUpLocation, testDataMap.get("PickUpLocation").toString());
					wait.until(ExpectedConditions.visibilityOf(suggestionLocation));
					clickOn(suggestionLocation);
				} catch (Exception e) {
					// TODO: handle exception
					threadSleep(FIVE_SECONDS);
					//Validation of Reservation Widget
					wait.until(ExpectedConditions.visibilityOf(ReservationWidget));
					ReservationWidget.isDisplayed();
					wait.until(ExpectedConditions.visibilityOf(pickUpLocation));
					clickOn(pickUpLocation);
					fillText(pickUpLocation, testDataMap.get("PickUpLocation").toString());
					wait.until(ExpectedConditions.visibilityOf(suggestionLocation));
					clickOn(suggestionLocation);
				}
			}
			if (!testDataMap.get("PickUpDate").toString().equalsIgnoreCase("NA")) {
				driver.findElement(By.xpath(pickUpDate)).click();
				nextMonth.click();
				nextMonth.click();
				nextMonth.click();
				nextMonth.click();
				driver.findElement(By.xpath(pickUpDate)).clear();
				driver.findElement(By.xpath(HelperFunctions.createDynamicLocator(selectDate,testDataMap.get("PickUpDate").toString()))).click();
			}
			if (!testDataMap.get("PickUpTime").toString().equalsIgnoreCase("NA")) {
				fillText(pickUpTime, testDataMap.get("PickUpTime").toString());
			}
			if (!testDataMap.get("DropOffDate").toString().equalsIgnoreCase("NA")) {
				driver.findElement(By.xpath(returnDatePath)).click();
				driver.findElement(By.xpath(returnDatePath)).clear();
				driver.findElement(By.xpath(HelperFunctions.createDynamicLocator(selectDate,testDataMap.get("DropOffDate").toString()))).click();
			}
			if (!testDataMap.get("DropOffTime").toString().equalsIgnoreCase("NA")) {
				fillText(dropOffTime, testDataMap.get("DropOffTime").toString());
			}
			if (!testDataMap.get("DropOffLocation").toString().equalsIgnoreCase("NA")) {
				fillText(enterReturnLocation, testDataMap.get("DropOffLocation").toString());
				clickOn(dropOffSuggestion);
			}
			if (!testDataMap.get("Age").toString().equalsIgnoreCase("NA")) {
				clickOn(ageDropDown);
				fillText(ageDropDown, testDataMap.get("Age").toString());
//				if(!testDataMap.get("Age1").toString().equalsIgnoreCase("NA")) {
//					minimumAgeError.isDisplayed();		
//				}
			}
			if (!testDataMap.get("Country").toString().equalsIgnoreCase("NA")) {
				clickOn(selectCountry);
				fillText(selectCountry, testDataMap.get("Country").toString());
				clickOn(selectCountry);
			}
			if (!testDataMap.get(COUPON).toString().equalsIgnoreCase("NA")) {
				clickOn(offerCodes);
				clickOn(CouponCheckBox);
				fillText(CouponTextField, testDataMap.get(COUPON).toString());
			}
				
			if (!testDataMap.get("CustomerID").toString().equalsIgnoreCase("NA")) {
				wait.until(ExpectedConditions.visibilityOf(offerCodes));
				clickOn(offerCodes);
				clickOn(addCustomerID);
				wait.until(ExpectedConditions.visibilityOf(enterCustomerID));
				clickOn(enterCustomerID);
				fillText(enterCustomerID, testDataMap.get("CustomerID").toString());
				fillText(enterLastName, testDataMap.get("LastName1").toString());
			}
			if (!testDataMap.get("BCD").toString().equalsIgnoreCase("NA")) {
				wait.until(ExpectedConditions.visibilityOf(offerCodes));
				clickOn(offerCodes);
				wait.until(ExpectedConditions.visibilityOf(AWDOrBCDOrPDN_TextField));
				clickOn(AWDOrBCDOrPDN_TextField);
				AWDOrBCDOrPDN_TextField.clear();
				AWDOrBCDOrPDN_TextField.sendKeys(testDataMap.get("BCD").toString(), Keys.TAB);
				if(Configuration.DOMAIN.equalsIgnoreCase("NZ") ) {
					wait.until(ExpectedConditions.visibilityOf(selectMyCarButton));
					clickOn(selectMyCarButton);
				}
				if (!testDataMap.get("CorporateEmailID").toString().equalsIgnoreCase("NA")) {
					corporateEmailId.sendKeys(Keys.TAB);
					fillText(corporateEmailId, testDataMap.get("CorporateEmailID").toString());
				} 
				else {
					if (!testDataMap.get("MemberNumber").toString().equalsIgnoreCase("NA")) {
						membershipTextField.sendKeys(Keys.TAB);
						fillText(membershipTextField, testDataMap.get("MemberNumber").toString());
					}
//					if (testDataMap.get("BCD").toString().contains("W8")) {
//						membershipTextField.sendKeys(Keys.TAB);
//						fillText(membershipTextField, testDataMap.get("MemberNumber").toString());
//					} 
//					if (!testDataMap.get("MemberNumber").toString().equalsIgnoreCase("NA")) {
//						membershipTextField.sendKeys(Keys.TAB);
//						fillText(membershipTextField, testDataMap.get("MemberNumber").toString());
//					}
				}
			}
			System.out.println();
			System.out.println(dropOffdate1+ '-'+ pickUpDate1);
			int pickupDate=Integer.parseInt(pickUpDate1);
			int dropoffDate=Integer.parseInt(dropOffdate1);
			TotalDays=dropoffDate-pickupDate;
			System.out.println(TotalDays);
			if(testDataMap.get("SelectMyCar").toString().equalsIgnoreCase("Yes")) {
				
//				TotalDays =((Integer.valueOf(testDataMap.get("DropOffDate").toString())-(Integer.valueOf(testDataMap.get("PickUpDate").toString()))));
//				System.out.println(TotalDays);
				wait.until(ExpectedConditions.visibilityOf(selectMyCarButton));
				clickOn(selectMyCarButton);
			}
		}

		if (testDataMap.get("UserType").toString().equalsIgnoreCase("Signin")) {
			clickOn(HeaderLoginButton);
			waitForVisibilityOfElement(UserName);
			UserName.sendKeys(testDataMap.get("Username").toString());
			waitForVisibilityOfElement(Password);
			Password.sendKeys(testDataMap.get("Password").toString());
			threadSleep(TWO_SECONDS);
			clickUsingJS(LoginButton);
		}

		if (testDataMap.get("UserType").toString().equalsIgnoreCase("Verify")) {
			budgetBrandText.isDisplayed();
			wait.until(ExpectedConditions.visibilityOf(reservation));
			clickOn(reservation);
			if(Configuration.ENVIRONMENT.equalsIgnoreCase("qa")){
				wait.until(ExpectedConditions.visibilityOf(makeaReservation1));
				clickOn(makeaReservation1);		
			}
			if(Configuration.ENVIRONMENT.equalsIgnoreCase("uat")){
				wait.until(ExpectedConditions.visibilityOf(makeaReservation1));
				clickOn(makeaReservation1);	
			}
			if(Configuration.ENVIRONMENT.equalsIgnoreCase("ci11")){
				wait.until(ExpectedConditions.visibilityOf(makeaReservation));
				clickOn(makeaReservation);
			}
			
			try {
				//Validation of Reservation Widget
				wait.until(ExpectedConditions.visibilityOf(ReservationWidget));
				ReservationWidget.isDisplayed();
				wait.until(ExpectedConditions.visibilityOf(pickUpLocation));
				clickOn(pickUpLocation);
				fillText(pickUpLocation, testDataMap.get("PickUplocation1").toString());
				wait.until(ExpectedConditions.visibilityOf(suggestionLocation));
				clickOn(suggestionLocation);
			} catch (Exception e) {
				// TODO: handle exception
				threadSleep(FIVE_SECONDS);
				//Validation of Reservation Widget
				wait.until(ExpectedConditions.visibilityOf(ReservationWidget));
				ReservationWidget.isDisplayed();
				wait.until(ExpectedConditions.visibilityOf(pickUpLocation));
				clickOn(pickUpLocation);
				fillText(pickUpLocation, testDataMap.get("PickUplocation1").toString());
				wait.until(ExpectedConditions.visibilityOf(suggestionLocation));
				clickOn(suggestionLocation);
			}
//			fillText(pickUpLocation, testDataMap.get("PickUplocation1").toString());
//			clickOn(suggestionLocation);

			if (!testDataMap.get("PickUpDate").toString().equalsIgnoreCase("NA")) {
				driver.findElement(By.xpath(pickUpDate)).click();
				driver.findElement(By.xpath(pickUpDate)).clear();
				driver.findElement(By.xpath(HelperFunctions.createDynamicLocator(selectDate,testDataMap.get("PickUpDate").toString() ))).click();
//				clickOn(pickUpDate);
//				pickUpDate.clear();
//				fillText(pickUpDate, testDataMap.get("PickUpDate").toString());
			}
			if (!testDataMap.get("DropOffDate").toString().equalsIgnoreCase("NA")) {
				driver.findElement(By.xpath(returnDatePath)).click();
				nextMonth.click();
				nextMonth.click();
				nextMonth.click();
				driver.findElement(By.xpath(returnDatePath)).clear();
				driver.findElement(By.xpath(HelperFunctions.createDynamicLocator(selectDate,testDataMap.get("DropOffDate").toString() ))).click();
//				clickOn(returnDatePath);
//				returnDatePath.clear();
//				fillText(returnDatePath, testDataMap.get("DropOffDate").toString());
			}
			if (!testDataMap.get("DropOffTime").toString().equalsIgnoreCase("NA")) {
				fillText(dropOffTime, testDataMap.get("DropOffTime").toString());
			}
			if (testDataMap.get("ErrorMessage90").toString().equalsIgnoreCase("Yes")) {
				clickOn(selectMyCarButton);
				threadSleep(TEN_SECONDS);
				System.out.println("wait till error 90 days");
				assertTrue(errorMessage90days.isDisplayed());
				System.out.println("verified 90 days");
			}
			if(!Configuration.DOMAIN.equalsIgnoreCase("US") && !Configuration.DOMAIN.equalsIgnoreCase("NZ") && !Configuration.DOMAIN.equalsIgnoreCase("CA") ) {
				if (testDataMap.get("ErrorMessageVerify").toString().equalsIgnoreCase("Yes")) {
					clickOn(selectMyCarButton);
					assertTrue(errorMessageverify.isDisplayed());
				}
			}
			if(testDataMap.get("VerifyKeyDropMessage").toString().equalsIgnoreCase("Yes")) {
				clickOn(selectMyCarButton);
				threadSleep(FIVE_SECONDS);
				verifyKeydropmessage.isDisplayed();
			}
			if (!testDataMap.get("Age").toString().equalsIgnoreCase("NA")) {
				clickOn(ageDropDown);
				fillText(ageDropDown, testDataMap.get("Age").toString());
//				if(!testDataMap.get("Age1").toString().equalsIgnoreCase("NA")) {
//					minimumAgeError.isDisplayed();		
//				}
			}
			if(!testDataMap.get("Age1").toString().equalsIgnoreCase("NA")) {
				clickOn(selectMyCarButton);
				wait.until(ExpectedConditions.visibilityOf(minimumAgeError));
				minimumAgeError.isDisplayed();
				clickOn(ageDropDown);
				fillText(ageDropDown, testDataMap.get("Age1").toString());
				clickOn(selectMyCarButton);
			}
		}
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

	public void viewModify(Map<?, ?> testDataMap) {
		clickOn(ReservationsTab);
		clickOn(Reservation_ViewModifyCancelLink);
	}

	public void launchUrl(String uRL) {
		launchUrl(Configuration.URL);
	}

	private Object getBrowserInstance() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void isOnPage() {

	}

}
