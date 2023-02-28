package com.avis.qa.pages;

import static com.avis.qa.utilities.CommonUtils.FIVE_SECONDS;
import static com.avis.qa.utilities.CommonUtils.TEN_SECONDS;
import static com.avis.qa.utilities.CommonUtils.threadSleep;
import static org.testng.Assert.assertTrue;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.avis.qa.core.AbstractBasePage;

public class BudgetReviewReservePage extends AbstractBasePage{

	public BudgetReviewReservePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
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

	@FindBy(xpath = "//div[@data-funding-source='paypal'] | //span[@class='c-icon paypal-logo']")
	private WebElement PaypalButton;

	@FindBy(xpath = "//div[@class='paypal-button-label-container']")
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

	@FindBy(xpath = "//label[@class='step4-checkbox-custom-label']")
	private WebElement cancelTerms;

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
	
	@FindBy(xpath = "(//*[contains(@ng-click,'getVehicles.submit')])[1]")
	private WebElement selectMyCarButton;
	
	@FindBy(xpath = "//span[@class='step-title']")
	private WebElement modifyReserverental;

	public void reviewReserve(Map testDataMap) {

		//		assertTrue(YourInformation.getText().contains("Your Information"));
		assertTrue(pickUpLocationVerify.getText().toString().contains(testDataMap.get("PickUpLocation").toString()));
		if(!testDataMap.get("DropOffLocation").toString().equalsIgnoreCase("NA")) {
			assertTrue(ReturnLocValue.getText().toString().contains(testDataMap.get("DropOffLocation").toString()));
		}
		if(!testDataMap.get("FirstName").toString().equalsIgnoreCase("NA")) {
			fillText(firstName,testDataMap.get("FirstName").toString());
		}
		if(!testDataMap.get("LastName").toString().equalsIgnoreCase("NA")) {
			fillText(lastName,testDataMap.get("LastName").toString() );
		}
		if(!testDataMap.get("Email").toString().equalsIgnoreCase("NA")) {
			fillText(emailField,testDataMap.get("Email").toString() );
		}
		if(!testDataMap.get("PhoneNumber").toString().equalsIgnoreCase("NA")) {
			fillText(phoneField, testDataMap.get("PhoneNumber").toString());
		}

		//			if(!testDataMap.get("CCNumber").toString().equalsIgnoreCase("NA")) {
		if(testDataMap.get("Paylater&Paynow").toString().equalsIgnoreCase("PayNow")) {
			clickOn(creditCardCheckBox);
			fillText(cardNumber,"379381331688207");
		}
		//				/clickOn(creditCardCheckBox);
		//				fillText(cardNumber,testDataMap.get("CCNumber").toString());
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
		//			}
		if(!testDataMap.get("PayPal").toString().equalsIgnoreCase("NA")) {
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
		if(!testDataMap.get("IATA").toString().equalsIgnoreCase("NA")) {
			fillText(IataTextFiled,testDataMap.get("IATA").toString() );
		}

		if(testDataMap.get("PayLaterCreditcard").toString().equalsIgnoreCase("YES")) {
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


		clickOn(termsCheck);
		clickOn(SubmitButton);
		threadSleep(TEN_SECONDS);
		assertTrue(reservationConfirmation.getText().contains("Your car is reserved."));

		assertTrue(pickUpLocationVerify.getText().toString().contains(testDataMap.get("PickUpLocation").toString()));
		if(!testDataMap.get("Coupon").toString().equalsIgnoreCase("NA")){
			assertTrue(couponVerify.getText().toString().contains(testDataMap.get("Coupon").toString()));
		}

		threadSleep(FIVE_SECONDS);
		String confirmationNo = confirmationNumber.getText();
		String [] confirmationNoValue= confirmationNumber.getText().split(": ");
		String reservationNumber = confirmationNoValue[1].replaceAll(":", "");
		System.out.println(reservationNumber);

		if(testDataMap.get("ModifyReservation").toString().equalsIgnoreCase("YES")) {
			helper.scrollToElement(modifyLocation);
			clickOn(modifyLocation);
//			assertTrue(modifyReserverental.getText().contains("Modify: Reserve a Rental Car"));
//			System.out.println("MODIFY RESERVE a car");
			ModifyReservationTextMsg.isDisplayed();
			clickOn(pickUpLocation);
			pickUpLocation.clear();
			fillText(pickUpLocation, testDataMap.get("ModifyPickUpLoc").toString());
			clickOn(suggestionLocation);
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
//			assertTrue(modifyReserverental.getText().contains("Modify: Select a Car"));
			clickOn(PayLater);
//			assertTrue(modifyReserverental.getText().contains("Modify: Rental Options"));
			clickOn(CONTINUEBUTTON);
//			assertTrue(modifyReserverental.getText().contains("Modify: Your Information"));
			clickOn(reviewModificationsButton);
			clickOn(keepModificationButton);
			if(!testDataMap.get("TrackReservation").toString().equalsIgnoreCase("NA")) {
				BudgetHomePage budgetHomePage= new BudgetHomePage(getDriver());
				budgetHomePage.viewModify(testDataMap);

			}
			if(!testDataMap.get("CancelReservation").toString().equalsIgnoreCase("NA")) {
				threadSleep(TEN_SECONDS);
				clickOn(cancelReservationButton);
				clickOn(confirmCancelReservationButton);
				clickOn(cancelTerms);
				clickOn(cancelReservation);
				threadSleep(FIVE_SECONDS);
				assertTrue(cancelledReservationConfirm.getText().toString().contains("Your prepaid reservation is cancelled."));

			}

		}
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
