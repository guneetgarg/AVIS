package com.avis.qa.pages;

import static com.avis.qa.utilities.CommonUtils.TEN_SECONDS;
import static com.avis.qa.utilities.CommonUtils.threadSleep;
import static org.testng.Assert.assertTrue;

import java.awt.RenderingHints.Key;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.avis.qa.core.AbstractBasePage;

public class PaylessReviewReservePage extends AbstractBasePage {
	
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
    
    @FindBy(xpath = "//h2[@class='grayFont']")
    private WebElement MakeMyReservation;//verify
    
    @FindBy(id = "iatanumber")
    private WebElement IataTextFiled;
    
    @FindBy(xpath = "//button[@aria-label='Close']/parent::div")
    private WebElement Close;
    
    @FindBy(xpath = "//button[@class='sc-clsFYl sc-zzebL eqOCIy dfGSsa']")
    private WebElement Nothanks;
    
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
    
    @FindBy(xpath = "//span[@id='cancel']")
    private WebElement close;
    
    @FindBy(xpath = "//iframe[@title = 'Rokt placement']")
    private WebElement roktPlacementFrame;

    @FindBy(xpath = "//iframe[@title = 'Rokt offer']")
    private WebElement roktOfferFrame;

    @FindBy(xpath = "//button[@aria-label='Close']")
    private WebElement getFreeCouponPopup;

    
	public PaylessReviewReservePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public void reviewPage(Map testDataMap) {
		
		assertTrue(MakeMyReservation.getText().contains("Make My Reservation"));
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
		if(!testDataMap.get("IATA").toString().equalsIgnoreCase("NA")) {
			fillText(IataTextFiled,testDataMap.get("IATA").toString() );
		}
		if(!testDataMap.get("CCNumber").toString().equalsIgnoreCase("NA")) {
			fillText(cardNumber,testDataMap.get("CCNumber").toString());
			fillText(creditCardExpiryDateField,testDataMap.get("ExpirationDate").toString());
			fillText(step4_CVV,testDataMap.get("CVV").toString());
			fillText(address1,testDataMap.get("Address").toString());
			fillText(city,testDataMap.get("City").toString());
			clickOn(state);
			fillText(state,testDataMap.get("State").toString());
			fillText(zip,testDataMap.get("ZipCode").toString());
	    }
		clickOn(termsCheck);
		clickOn(SubmitButton);
		clickOn(getFreeCouponPopup);
		clickOn(close);
		
	}
		
	private Object getDriver() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void isOnPage() {
		// TODO Auto-generated method stub
		
	}
	}
