package pageObjects;

import static com.avis.qa.core.Configuration.URL;
import static com.avis.qa.utilities.CommonUtils.FIVE_SECONDS;
import static com.avis.qa.utilities.CommonUtils.TEN_SECONDS;
import static com.avis.qa.utilities.CommonUtils.threadSleep;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.RefreshPage;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.avis.qa.core.AbstractBasePage;
import com.avis.qa.core.Configuration;

public class BudgetReviewReservePage extends AbstractBasePage{

	public BudgetReviewReservePage(WebDriver driver) {
		super(driver);
	}


	@FindBy(xpath = "//*[contains(@id,'firstname')]")
	private WebElement firstName;

	@FindBy(id = "lastname")
	private WebElement lastName;

	@FindBy(id = "email")
	private WebElement emailField;

	@FindBy(id = "phone")
	private WebElement phoneField;

	@FindBy(id = "termsCheck")
	private WebElement termsCheck;

	@FindBy(xpath = "//*[contains(@id,'submit_button')]")
	private WebElement SubmitButton;

	@FindBy(css = "button[type='reset']")
	private WebElement continueReservationButton;

	@FindBy(xpath = "//div[@ng-if=\"!vm.isModifyFlow\"]")
	private WebElement YourInformation;//verify

	@FindBy(xpath = "(//div[@class='location-info'])[1] | (//div[@class='summary-location'])[1]")
	private WebElement pickUpLocationVerify;

	@FindBy(xpath = "(//div[@class='location-info'])[2]")
	private WebElement ReturnLocValue;

	@FindBy(xpath = "//*[contains(@for,'creditcard')] | //*[contains(@for,'ccCard')]")
	private WebElement creditCardCheckBox;

	@FindBy(xpath = "//input[contains(@id,'cardnumber')]")
	private WebElement cardNumber;

	@FindBy(id = "expirydate")
	private WebElement creditCardExpiryDateField;

	@FindBy(xpath = "//input[contains(@id,'securitycode')]")
	private WebElement step4_CVV;

	@FindBy(xpath = "//*[contains(@id,'address1')]")
	private WebElement address1;

	@FindBy(xpath = "//*[@id='States']")
	private WebElement state;

	@FindBy(xpath = "(//*[contains(@id,'city')])|(//*[contains(@name,'city')])")
	private WebElement city;

	@FindBy(xpath = "//*[contains(@id,'zip')]")
	private WebElement zip;

	@FindBy(id = "iatanumber")
	private WebElement IataTextFiled;

	@FindBy(xpath="//input[@name='address3']")
	private WebElement Address3;

	//	@FindBy(xpath = "//div[@data-funding-source='paypal'] | //span[@class='c-icon paypal-logo']")
	//	private WebElement PaypalButton;
	//label[@for='payPal']

	@FindBy(xpath = "//span[@class='c-icon paypal-logo']")
	private WebElement PaypalButton;

	//	@FindBy(xpath = "//div[@class='paypal-button-label-container']")
	//	private WebElement Budget_PaypalRadioButton;

	@FindBy(xpath = "//img[@class='paypal-button-logo paypal-button-logo-paypal paypal-button-logo-gold']")
	private WebElement Budget_PaypalRadioButton;

	@FindBy(xpath = "//input[@id='email']")
	private WebElement emailTextField;

	@FindBy(xpath = "//button[@id='btnNext']")
	private WebElement NextButton;

	@FindBy(xpath = "//input[@id='password']")
	private WebElement passwordTextField;

	@FindBy(xpath = "//button[@id='btnLogin']")
	private WebElement LoginButton;

	@FindBy(xpath = "//button[@id='consentButton']")
	private WebElement AgreeAndContinueButton;

	@FindBy(xpath = "(//div[contains(div/text(),'Your Information')]//child::p)")
	private List<WebElement> userInfoList;

	@FindBy(xpath = "//span[@class='confirmation-num']")
	private WebElement confirmationNumber;

	@FindBy(xpath = "//h1[@class='confirmation-msg']")
	private WebElement reservationConfirmation;


	@FindBy(xpath = "//a[@ng-click='vm.modifyVehicle()']")
	private WebElement modifyVehicle;

	@FindBy(xpath = "//a[@ng-click='vm.modifyTimePlace()']")
	private WebElement modifyLocation;

	@FindBy(xpath = "(//a[contains(text(),'Select')])[4]|(//a[contains(text(),'Select')])[3]|(//a[@id='res-vehicles-pay-later'])[1] | (//a[contains(text(),'Pay Later')])[2]|(//a[contains(text(),'Pay at Counter')])[1]|(//a[contains(text(),'Paiement au comptoir')])[1]|(//a[contains(text(),'Pagar en el mostrador')])[1]|((//p[contains(@class,'totalPay')]/../a)[1])")
	private WebElement PayLater;

	@FindBy(xpath = "//button[@class='btn btn-primary-avis pull-right'] | //button[contains(@id,'res-extras-continue-bottom')] | (//button[contains(text(),'Continue')])[2] | (//button[contains(text(),'Continue')])[1]")
	private WebElement CONTINUEBUTTON;

	@FindBy(xpath = "//button[text()='Review Modifications']")
	private WebElement reviewModificationsButton;

	@FindBy(xpath = "//button[text()='Keep Modifications']")
	private WebElement keepModificationButton;

	@FindBy(id = "res-conf-makeNewRes")
	private WebElement makeNewReservationButton;

	@FindBy(xpath = "//a[contains(.,'Modify your reservation')]")
	private WebElement viewOrModifyReservationButton;

	@FindBy(id = "VMlastName")
	private WebElement lastNameTextField;

	@FindBy(id = "VMCConfirmationNumber")
	private WebElement confirmationNumberTextField;

	@FindBy(id = "VMCForm")
	private WebElement submitButton;

	@FindBy(id = "res-cancelReservation")
	private WebElement cancelReservationButton;

	@FindBy(id = "res-cancelreservation-popUp")
	private WebElement confirmCancelReservationButton;

	@FindBy(xpath = "//label[@for='termsCheck']")
	private WebElement cancelTermscheckbox;

	@FindBy(xpath = "//button[@ng-click='vm.cancelReservation.submit(cancelform)']")
	private WebElement cancelReservation;

	@FindBy(xpath = "//h1[@class='confirmation-msg']")
	private WebElement cancelledReservationConfirm;

	@FindBy(xpath = "//label[@class='step4-checkbox-custom-label']")
	private WebElement userYourCreditCardCheckbox;

	@FindBy(xpath = "//span[@class='coupon-value']")
	private WebElement couponVerify;

	@FindBy(xpath = "//strong[text()='Modify Your Reservation']")
	private WebElement ModifyReservationTextMsg;

	@FindBy(xpath = "(//input[@id='PicLoc_value'])[1]")
	private WebElement pickUpLocation;

	@FindBy(xpath = "((//div[@class='angucomplete-results'])[1]//div[@class='angucomplete-description'])[1] | ((//div[@id='PicLoc_dropdown'])[1]//div[@class='angucomplete-row'])[1]")
	private WebElement suggestionLocation;

	@FindBy(xpath = "//div[@class='col-xs-3 res-inputFld dateImg']//input[@ng-model='vm.reservationModel.pickUpDateDisplay']")
	private WebElement pickUpDate;

	//div[@class='col-xs-3 res-inputFld dateImg']//input[@ng-model='vm.reservationModel.pickUpDateDisplay']

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

	@FindBy(xpath = "(//*[contains(@ng-click,'getVehicles.submit')])[1]")
	private WebElement selectMyCarButton;

	@FindBy(xpath = "//span[@class='step-title']")
	private WebElement modifyReserverental;

	@FindBy(xpath = "//*[contains(@name,'flightnumberMob')]")
	private WebElement flightNumber;

	@FindBy(xpath = "//*[contains(@name,'airlineobj')]")
	private WebElement Airline;

	@FindBy(xpath = "//button[@aria-label='Close']")
	private WebElement close;

	@FindBy(xpath = "//span[text()='Base Rate']")
	private WebElement BaseRate;

	@FindBy(xpath = "//span[@class='four-seats-feat']")
	private WebElement NumberOfSeats;

	@FindBy(xpath = "//a[@id='rate-terms']")
	private WebElement SeeRateTerms;

	@FindBy(xpath = "//span[@ng-if=\"vm.currencySymbol!='$ CA'\"]//span[@ng-bind-html='vm.prod.reservationSummary.rateSummary.currencySymbol']")
	private WebElement VerifySymbol;

	@FindBy(xpath = "//div[@class='taxes-fees']")
	private WebElement feesTaxes;

	@FindBy(xpath = "//div[@class='estimate mobile-hide']")
	private WebElement estimatedTotal;

	@FindBy(xpath = "//div[@ng-show='showTerms']")
	private WebElement verifyRateTerms;

	@FindBy(id = "res-conf-makeNewRes")
	private WebElement makeaNewReservation;

	@FindBy(id = "res-view-lastName")
	private WebElement viewmodifylastName;

	@FindBy(id = "res-view-confirmationNumber")
	private WebElement viewmodifyConfirmationNumber;

	@FindBy(xpath = " //button[@ng-click='vm.CNValidation.submit(VMCForm);']")
	private WebElement findReservation;

	@FindBy(xpath = "//span[contains(text(),'Modify: Reserve a Rental Car')]")
	private WebElement modifyReserveaCar;

	@FindBy(xpath = "//span[contains(text(),'Modify: Select a Car')]")
	private WebElement modifySelectACar;

	@FindBy(xpath = "//div[@class='col-sm-6 col-md-4 source']//div[@class='day-time-info']")
	private WebElement verifyPickupDateandTime;

	@FindBy(xpath = "//div[@class='col-sm-6 col-md-4 destination hidden-xs']//div[@class='day-time-info']")
	private WebElement verifyDropoffDateandTime;

	@FindBy(xpath = "//span[contains(text(),'Modify: Rental Options')]")
	private WebElement modifyRentalOptions;

	@FindBy(xpath = "//div[@class='col-sm-6 source']//div[@class='day-time-info']")
	private WebElement modifyRentalPickDate;

	@FindBy(xpath = "//div[@class='col-sm-6 destination']//div[@class='day-time-info']")
	private WebElement modifyRentalDropDate;

	@FindBy(xpath = "//div[@class='estimate mobile-hide text-size-1_5x']")
	private WebElement EstimatedTotal;

	@FindBy(xpath = "//p[@ng-if=\"vm.confirmation.reservationSummary.awdNumber != '' && vm.confirmation.reservationSummary.awdNumber != undefined\"]")
	private WebElement verifyBCD;

	@FindBy(xpath = "//span[@class='pull-right base-rate']//span[text()='₹']")
	private WebElement INRCurrency;

	@FindBy(xpath = "//span[@class='total-amount pull-right']//span[text()='A$']")
	private WebElement SYDCurrency;

	@FindBy(xpath = "//div[@ng-repeat='error in messageList']//span[@class='mainErrorText info-error-msg-text']")
	private WebElement FlightErrorMsg;

	@FindBy(xpath = "//span[@ng-if='deviceType === carRentalConstant.deviceTypeDesktop']")
	private WebElement FrequentTraveller;

	@FindBy(xpath = "//div[@class='col-xs-4 col-sm-2 header noPad']//a[@ng-click='vm.modifyExtras()']")
	private WebElement RentalOptionModify;

	@FindBy(xpath = "//button[text()='Continue']")
	private WebElement RentalPageContinueBtn;

	@FindBy(id = "up-label")
	private WebElement upliftCheckbox;

	@FindBy(xpath = "//div[@class='App']//div[@class='row mobile']//div[@class='content-wrapper']//div[@class='col-xs-12']//div[@class='form-group ']//div[@data-testid='text-input-root']//div[@class='sc-jrQzUz jvgGCI']//input[@name='mobile']")
	private WebElement upliftMobile;

	@FindBy(xpath = "//input[@name='birthdate']")
	private WebElement upliftBirthdate;

	@FindBy(xpath = "//a[contains(@id,'s-get-started')]/..")
	private WebElement letGetStarted;

	@FindBy(xpath = "//input[@name='mobile-verification-code']")
	private WebElement mobileVerificationCode;

	@FindBy(xpath = "//div[@class='verify-container main-container']//a[@role='button']")
	private WebElement verifyButton;

	@FindBy(xpath = "//input[@name='first-name']")
	private WebElement firstnameUplift;

	@FindBy(xpath = "//input[@name='last-name']")
	private WebElement lastNameUplift;

	@FindBy(xpath = "//input[@name='home-address']")
	private WebElement homeaddressuplift;

	@FindBy(xpath = "//input[@name='home-city']")
	private WebElement homecityUplift;

	@FindBy(xpath = "//select[@name='home-region']")
	private WebElement regionUplift;

	@FindBy(xpath = "//input[@name='home-postal-code']")
	private WebElement postalcodeUplift;

	@FindBy(xpath = "//input[@name='email']")
	private WebElement emailUplift;

	@FindBy(xpath = "//input[@name='yearly-income']")
	private WebElement yearlyincomeUplift;

	@FindBy(xpath = "//input[@name='ssn']")
	private WebElement passwordUplift;

	@FindBy(xpath = "//div[@class='App']//div[@class='col-xs-12 col-sm-8 col-sm-offset-2 footer']//div[@class='main-container']//a[@type='button']")
	private WebElement continueUplift;

	@FindBy(xpath = "//input[@name='cc-number']")
	private WebElement ccnumberUplift;

	@FindBy(xpath = "//input[@name='cc-expiration']")
	private WebElement ccexpirationUplift;

	@FindBy(xpath = "//input[@name='cc-ccv']")
	private WebElement ccccvUplift;

	@FindBy(xpath = "//input[@name='cc-zip']")
	private WebElement cczipUplift;

	@FindBy(xpath = "//input[@name='full-ssn']")
	private WebElement securitynumberUplift;

	@FindBy(xpath = "//div[@class='col-xs-12 accept-page-btn']//a[@role='button']")
	private WebElement continueCreditUplift;

	@FindBy(id = "i-agree-and-continue")
	private WebElement agreetermsUplift;


	public void reviewReserve(Map<?, ?> testDataMap) throws InterruptedException {

		if(testDataMap.get("UserType").toString().equalsIgnoreCase("Guest")) {
			WebDriverWait wait = new WebDriverWait(driver, 90);
			try {
				wait.until(ExpectedConditions.visibilityOf(BaseRate));
				BaseRate.isDisplayed();
				VerifySymbol.isDisplayed();
				feesTaxes.isDisplayed();
			} catch (Exception e) {
				// TODO: handle exception
			}
			if(Configuration.DOMAIN.equalsIgnoreCase("US") && Configuration.DOMAIN.equalsIgnoreCase("CA")) {
				estimatedTotal.isDisplayed();
			}
			if(Configuration.DOMAIN.equalsIgnoreCase("NZ")) {
				EstimatedTotal.isDisplayed();
			}
			try {
				NumberOfSeats.isDisplayed();
				SeeRateTerms.isDisplayed();
			} catch (Exception e) {
				// TODO: handle exception
			}

			if (!testDataMap.get("PickUpLocation").toString().equalsIgnoreCase("NA")) {
				try {
					wait.until(ExpectedConditions.visibilityOf(pickUpLocationVerify));
					assertTrue(pickUpLocationVerify.getText().toString().contains(testDataMap.get("PickUpLocation").toString()));

				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			if(!testDataMap.get("DropOffLocation").toString().equalsIgnoreCase("NA")) {
				wait.until(ExpectedConditions.visibilityOf(ReturnLocValue));
				assertTrue(ReturnLocValue.getText().toString().contains(testDataMap.get("DropOffLocation").toString()));
			}

			//			Validate INR Currency
			if(!testDataMap.get("Currency").toString().equalsIgnoreCase("NA")) {
				wait.until(ExpectedConditions.visibilityOf(INRCurrency));
				INRCurrency.isDisplayed();
			}

			if(!testDataMap.get("FirstName").toString().equalsIgnoreCase("NA")) {
				wait.until(ExpectedConditions.visibilityOf(firstName));
				fillText(firstName,testDataMap.get("FirstName").toString());
			}
			if(!testDataMap.get("LastName").toString().equalsIgnoreCase("NA")) {
				wait.until(ExpectedConditions.visibilityOf(lastName));
				fillText(lastName,testDataMap.get("LastName").toString() );
			}
			if(!testDataMap.get("Email").toString().equalsIgnoreCase("NA")) {
				wait.until(ExpectedConditions.visibilityOf(emailField));
				fillText(emailField,testDataMap.get("Email").toString() );
			}
			if(!testDataMap.get("PhoneNumber").toString().equalsIgnoreCase("NA")) {
				wait.until(ExpectedConditions.visibilityOf(phoneField));
				fillText(phoneField, testDataMap.get("PhoneNumber").toString());
			}
			if(testDataMap.get("Paylater&Paynow").toString().equalsIgnoreCase("PayNow")) {
				if(!Configuration.DOMAIN.equalsIgnoreCase("CA") || !Configuration.DOMAIN.equalsIgnoreCase("NZ") ) {
					if(Configuration.DOMAIN.equalsIgnoreCase("US") ) {
						wait.until(ExpectedConditions.visibilityOf(creditCardCheckBox));
						clickOn(creditCardCheckBox);
					}
					fillText(cardNumber,testDataMap.get("CCNumber").toString());
				}
			}
			if(!testDataMap.get("ExpirationDate").toString().equalsIgnoreCase("NA")) {
				fillText(creditCardExpiryDateField,testDataMap.get("ExpirationDate").toString());
				fillText(step4_CVV,testDataMap.get("CVV").toString());
				fillText(address1,testDataMap.get("Address").toString());
			}
			if(!testDataMap.get("Address3").toString().equalsIgnoreCase("NA")) {
				fillText(Address3,testDataMap.get("Address3").toString() );
			}
			if(!testDataMap.get("City").toString().equalsIgnoreCase("NA")) {
				fillText(city,testDataMap.get("City").toString());
			}
			if(!testDataMap.get("State").toString().equalsIgnoreCase("NA")) {
				clickOn(state);
				fillText(state,testDataMap.get("State").toString());
			}

			if(!testDataMap.get("ZipCode").toString().equalsIgnoreCase("NA")) {
				fillText(zip,testDataMap.get("ZipCode").toString());
			}
			if(!testDataMap.get("PayPal").toString().equalsIgnoreCase("NA")) {
				wait.until(ExpectedConditions.visibilityOf(PaypalButton));
				clickOn(PaypalButton);
				clickOn(Budget_PaypalRadioButton);
				String mainWindowHandle = driver.getWindowHandle();
				System.out.println("windowhandle :"+mainWindowHandle);
				Set<String> allWindowHandles = driver.getWindowHandles();
				System.out.println("windowhandle :"+allWindowHandles);
				Iterator<String> iterator = allWindowHandles.iterator();
				// Here we will check if child window is present and then switch to child window
				while (iterator.hasNext()) {
					String ChildWindow = iterator.next();
					if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
						driver.switchTo().window(ChildWindow);

						emailTextField.click();
						emailTextField.sendKeys(testDataMap.get("PaypalEmail").toString());
						clickOn(NextButton);
						clickOn(passwordTextField);
						passwordTextField.sendKeys(testDataMap.get("PaypalPassword").toString());
						clickOn(LoginButton);
						clickOn(AgreeAndContinueButton);
					}
				}

			}

			if(testDataMap.get("PayLaterCreditcard").toString().equalsIgnoreCase("YES")) {
				wait.until(ExpectedConditions.visibilityOf(userYourCreditCardCheckbox));
				clickOn(userYourCreditCardCheckbox);
				clickOn(creditCardCheckBox);
				fillText(cardNumber, "347651479687420");
				//				fillText(cardNumber,testDataMap.get("CCNumber").toString());
				fillText(creditCardExpiryDateField,testDataMap.get("ExpirationDate1").toString());
				fillText(step4_CVV,testDataMap.get("CVV1").toString());
				fillText(address1,testDataMap.get("Address1").toString());
				if(!testDataMap.get("City1").toString().equalsIgnoreCase("NA")) {
					fillText(city,testDataMap.get("City1").toString());
				}
				if(!testDataMap.get("State1").toString().equalsIgnoreCase("NA")) {
					clickOn(state);
					fillText(state,testDataMap.get("State1").toString());
				}
				if(!testDataMap.get("ZipCode1").toString().equalsIgnoreCase("NA")) {
					fillText(zip,testDataMap.get("ZipCode1").toString());
				}

			}

			if(testDataMap.get("UpliftInfo").toString().equalsIgnoreCase("YES")) {
				System.out.println("uplift ");
				upliftCheckbox.click();
				threadSleep(TEN_SECONDS);
				try {
					Thread.sleep(5000);
					driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='Pay Monthly']")));
					System.out.println("uplift iframe");
					Boolean b = driver.findElement(By.id("mobile")).isDisplayed();
					System.out.println("uplift iframe mobile id");

					System.out.println(b);
				} catch (NoSuchElementException e) {
					e.printStackTrace();

				}
				upliftMobile.click();
				new Actions(driver).sendKeys(testDataMap.get("UpliftMobile").toString()).perform();
				upliftBirthdate.click();
				new Actions(driver).sendKeys(testDataMap.get("UpliftBirthDate").toString()).perform();
				letGetStarted.click();					
				try {
					mobileVerificationCode.click();
				} catch (Exception e){
					letGetStarted.click();

				}
				Thread.sleep(TEN_SECONDS);
				mobileVerificationCode.click();
				new Actions(driver).sendKeys("111111").perform();
				verifyButton.click();
				wait.until(ExpectedConditions.visibilityOf(firstnameUplift));
				firstnameUplift.click();
				firstnameUplift.clear();
				new Actions(driver).sendKeys("Davis").perform();
				lastNameUplift.click();
				lastNameUplift.clear();
				new Actions(driver).sendKeys("Davis").perform();
				homeaddressuplift.click();
				new Actions(driver).sendKeys("1013 Weda Cir").perform();
				homecityUplift.click();
				new Actions(driver).sendKeys("Mayfield").perform();
				regionUplift.click();
				new Actions(driver).sendKeys("Kentucky").perform();
				postalcodeUplift.click();
				new Actions(driver).sendKeys("999999").perform();
				emailUplift.click();
				new Actions(driver).sendKeys("arthur@uplift.com").perform();
				yearlyincomeUplift.click();
				new Actions(driver).sendKeys("8,000,000").perform();
				passwordUplift.click();
				new Actions(driver).sendKeys("3507").perform();
				continueUplift.click();
				wait.until(ExpectedConditions.visibilityOf(agreetermsUplift));
				ccnumberUplift.click();
				ccnumberUplift.sendKeys("4111 1111 1111 1111");
				ccexpirationUplift.click();
				ccexpirationUplift.sendKeys("12/23");
				ccccvUplift.click();
				ccccvUplift.sendKeys("123");
				cczipUplift.click();
				cczipUplift.sendKeys("55555");
				securitynumberUplift.click();
				securitynumberUplift.sendKeys("666-58-3507");
				continueCreditUplift.click();
				wait.until(ExpectedConditions.visibilityOf(agreetermsUplift));
				agreetermsUplift.click();
				clickOn(termsCheck);
				clickOn(SubmitButton);
			}

		}
		if(!testDataMap.get("Airline").toString().equalsIgnoreCase("NA")) {
			clickOn(Airline);
			fillText(Airline,testDataMap.get("Airline").toString());
			fillText(flightNumber,testDataMap.get("FlightNumber").toString());

		}
		if(!testDataMap.get("IATA").toString().equalsIgnoreCase("NA")) {
			fillText(IataTextFiled,testDataMap.get("IATA").toString());
		}

		//			Frequent Travel partner Verify
		if(testDataMap.get("FrequentTravellerProgram").toString().equalsIgnoreCase("Yes")) {
			FrequentTraveller.isDisplayed();
		}

		if(!testDataMap.get("ReserveButton").toString().equalsIgnoreCase("NA")) {
			clickOn(termsCheck);
			clickOn(SubmitButton);
		}
		//			Flight error msg verify
		if(Configuration.DOMAIN.equalsIgnoreCase("AU")) {
			if (testDataMap.get("ErrorMessageVerify").toString().equalsIgnoreCase("Yes")) {
				wait.until(ExpectedConditions.visibilityOf(FlightErrorMsg));
				FlightErrorMsg.isDisplayed();
			}
		}
		if(testDataMap.get("ErrorMessageVerify").toString().equalsIgnoreCase("NA")) {
			try {
				wait.until(ExpectedConditions.visibilityOf(reservationConfirmation));
				System.out.println(reservationConfirmation.getText());
				assertTrue(reservationConfirmation.getText().contains("Your car is reserved."));
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Reservation Confirmation");
			}
			try {
				System.out.println("try");
				List<WebElement> s1 = driver.findElements(By.tagName("iframe"));
				for(int i=0; i < s1.size(); i++){
					if(s1.get(i).getAttribute("id").contains("rokt-placement")) {
						driver.switchTo().frame(s1.get(i).getAttribute("id"));
						List<WebElement> t = driver.findElements(By.tagName("iframe"));
						driver.switchTo().frame(t.get(0).getAttribute("id"));
						System.out.println("5");
						wait.until(ExpectedConditions.visibilityOf(close));
						System.out.println("6");
						clickOn(close);
						System.out.println("7");
						break;
					}				
				}
			}catch (Exception e) {
				System.out.println("Popup not visible");
			}
			threadSleep(TEN_SECONDS);
			assertTrue(pickUpLocationVerify.getText().toString().contains(testDataMap.get("PickUpLocation").toString()));
		}
		if(!testDataMap.get("Coupon").toString().equalsIgnoreCase("NA")){
			wait.until(ExpectedConditions.visibilityOf(couponVerify));
			assertTrue(couponVerify.getText().toString().contains(testDataMap.get("Coupon").toString()));
		}

		//			Validate NZCurrency
		if(!testDataMap.get("Currency").toString().equalsIgnoreCase("NA")) {
			wait.until(ExpectedConditions.visibilityOf(SYDCurrency));
			SYDCurrency.isDisplayed();
		}

		if(Configuration.DOMAIN.equalsIgnoreCase("AU")) {

			if(!testDataMap.get("BCD").toString().equalsIgnoreCase("NA")) {

				String BCDNo = verifyBCD.getText();
				System.out.println(BCDNo);
				String [] BCDValue= verifyBCD.getText().replace(" ", "").split(":");
				String BCDNumber = BCDValue[1];
				System.out.println(BCDNumber);
				assertEquals(BCDNumber,testDataMap.get("BCD").toString() );
			}
		}

		if(testDataMap.get("ErrorMessageVerify").toString().equalsIgnoreCase("NA")) {
			threadSleep(FIVE_SECONDS);
			String confirmationNo = confirmationNumber.getText();
			System.out.println(confirmationNo);
			String [] confirmationNoValue= confirmationNumber.getText().split(": ");
			String reservationNumber = confirmationNoValue[1].replaceAll(":", "");
			System.out.println(reservationNumber);


		
			if(testDataMap.get("ViewModifyCancel").toString().equalsIgnoreCase("Yes")) {
				viewmodifylastName.click();
				fillText(viewmodifylastName,testDataMap.get("LastName").toString());
				fillText(viewmodifyConfirmationNumber,confirmationNo );
				findReservation.click();

			}
		}
			if(testDataMap.get("ModifyReservation").toString().equalsIgnoreCase("YES")) {
				wait.until(ExpectedConditions.visibilityOf(modifyLocation));
				helper.scrollToElement(modifyLocation);
				clickOn(modifyLocation);
				modifyReserveaCar.isDisplayed();
				pickUpLocation.isDisplayed();
				dropOffLocation.isDisplayed();
				pickUpTime.isDisplayed();
				returnDatePath.isDisplayed();
				dropOffTime.isDisplayed();
				enterReturnLocation.isDisplayed();
				selectMyCarButton.isDisplayed();
				ModifyReservationTextMsg.isDisplayed();
				if (!testDataMap.get("ModifyPickUpLoc").toString().equalsIgnoreCase("NA")) {
					clickOn(pickUpLocation);
					pickUpLocation.clear();
					fillText(pickUpLocation, testDataMap.get("ModifyPickUpLoc").toString());
					clickOn(suggestionLocation);
				}
				if (!testDataMap.get("ModifyDropOffLocation").toString().equalsIgnoreCase("NA")) {
					fillText(dropOffLocation, testDataMap.get("ModifyDropOffLocation").toString());
				}
				if (!testDataMap.get("ModifyPickUpDate").toString().equalsIgnoreCase("NA")) {
					clickOn(pickUpDate);
					pickUpDate.clear();
					fillText(pickUpDate,testDataMap.get("ModifyPickUpDate").toString());
				}
				fillText(pickUpTime,testDataMap.get("ModifyPickUpTime").toString());
				if (!testDataMap.get("ModifyDropOffDate").toString().equalsIgnoreCase("NA")) {
					clickOn(returnDatePath);
					returnDatePath.clear();
					fillText(returnDatePath,testDataMap.get("ModifyDropOffDate").toString());
				}
				fillText(dropOffTime,testDataMap.get("ModifyDropOffTime").toString());
				if (!testDataMap.get("ModifyDropOffLocation").toString().equalsIgnoreCase("NA")) {
					fillText(enterReturnLocation,testDataMap.get("ModifyDropOffLocation").toString() );
					clickOn(dropOffSuggestion);
				}
				clickOn(selectMyCarButton);
				assertTrue(pickUpLocationVerify.getText().toString().contains(testDataMap.get("ModifyPickUpLoc").toString()));
				ReturnLocValue.isDisplayed();
				verifyPickupDateandTime.isDisplayed();
				verifyDropoffDateandTime.isDisplayed();
				clickOn(PayLater);
				modifyRentalOptions.isDisplayed();
				modifyRentalPickDate.isDisplayed();
				modifyRentalDropDate.isDisplayed();
				BaseRate.isDisplayed();
				VerifySymbol.isDisplayed();
				feesTaxes.isDisplayed();
				estimatedTotal.isDisplayed();
				NumberOfSeats.isDisplayed();
				SeeRateTerms.isDisplayed();
				SeeRateTerms.click();
				verifyRateTerms.isDisplayed();
				clickOn(CONTINUEBUTTON);
				clickOn(reviewModificationsButton);
				clickOn(keepModificationButton);
				if(!testDataMap.get("TrackReservation").toString().equalsIgnoreCase("NA")) {
					BudgetHomePage budgetHomePage= new BudgetHomePage(getDriver());
					budgetHomePage.viewModify(testDataMap);

				}
			}

			//			Modify Frequent travel partner
			if(testDataMap.get("FrequentTravellerProgram").toString().equalsIgnoreCase("Yes")) {
				clickOn(RentalOptionModify);
				wait.until(ExpectedConditions.visibilityOf(RentalPageContinueBtn));
				clickOn(RentalPageContinueBtn);
				wait.until(ExpectedConditions.visibilityOf(FrequentTraveller));
				FrequentTraveller.isDisplayed();
			}

			if(!testDataMap.get("CancelReservation").toString().equalsIgnoreCase("NA")) {
				if(testDataMap.get("Paylater&Paynow").toString().equalsIgnoreCase("PayNow")) {
					if(Configuration.DOMAIN.equalsIgnoreCase("US")) {
						wait.until(ExpectedConditions.visibilityOf(cancelReservationButton));
						clickOn(cancelReservationButton);
						wait.until(ExpectedConditions.visibilityOf(cancelTermscheckbox));
						clickOn(cancelTermscheckbox);
						wait.until(ExpectedConditions.visibilityOf(confirmCancelReservationButton));
						clickOn(confirmCancelReservationButton);
					}
				
								if(!Configuration.DOMAIN.equalsIgnoreCase("AU")) {
									wait.until(ExpectedConditions.visibilityOf(cancelTermscheckbox));
									clickOn(cancelTermscheckbox);
									wait.until(ExpectedConditions.visibilityOf(cancelReservation));
									clickOn(cancelReservation);
								}
								if(!Configuration.DOMAIN.equalsIgnoreCase("NZ")) {
									wait.until(ExpectedConditions.visibilityOf(cancelTermscheckbox));
									clickOn(cancelTermscheckbox);
									wait.until(ExpectedConditions.visibilityOf(cancelReservation));
									clickOn(cancelReservation);
								}
				}
			}
		}


	private Object getBrowserInstance() {
		// TODO Auto-generated method stub
		return null;
	}

	private WebDriver getDriver() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void isOnPage() {
		// TODO Auto-generated method stub

	}

}