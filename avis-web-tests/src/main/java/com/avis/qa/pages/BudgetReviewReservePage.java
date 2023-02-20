package com.avis.qa.pages;

import static org.testng.Assert.assertTrue;

import java.util.Map;

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
			
			if(!testDataMap.get("CCNumber").toString().equalsIgnoreCase("NA")) {
				clickOn(creditCardCheckBox);
				System.out.println("guest paynow");
				fillText(cardNumber,testDataMap.get("CCNumber").toString());
				fillText(creditCardExpiryDateField,testDataMap.get("ExpirationDate").toString());
				fillText(step4_CVV,testDataMap.get("CVV").toString());
				fillText(address1,testDataMap.get("Address").toString());
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
			}
			if(!testDataMap.get("IATA").toString().equalsIgnoreCase("NA")) {
				fillText(IataTextFiled,testDataMap.get("IATA").toString() );
			}
			
			clickOn(termsCheck);
			clickOn(SubmitButton);	
//			assertTrue(reservationConfirmation.getText().contains("Your car is reserved."));

	}

	@Override
	public void isOnPage() {
		// TODO Auto-generated method stub
		
	}
	
}
