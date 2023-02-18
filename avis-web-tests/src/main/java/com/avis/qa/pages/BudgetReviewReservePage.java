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
	
	
	public void reviewReserve(Map testDataMap) {
		
//		assertTrue(YourInformation.getText().contains("Your Information"));
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
			
			clickOn(termsCheck);
			clickOn(SubmitButton);	
//			assertTrue(reservationConfirmation.getText().contains("Your car is reserved."));

	}

	@Override
	public void isOnPage() {
		// TODO Auto-generated method stub
		
	}
	
}
