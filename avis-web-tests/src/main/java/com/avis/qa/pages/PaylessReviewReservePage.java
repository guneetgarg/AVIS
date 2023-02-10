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
    
	public PaylessReviewReservePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public void reviewPage(Map testDataMap) {
		
		assertTrue(MakeMyReservation.getText().contains("Make My Reservation"));
		System.out.println("make my reservation verify");
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
		clickOn(termsCheck);
		SubmitButton.click();
	}
		
//		System.out.println(driver.findElements(By.tagName("iframe")).size());
//		List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
//		for(int i = 1; i<=iframes.size(); i++) {
//		driver.switchTo().frame(i);
//		Close.click();
//		}
//		try {
//			driver.switchTo().frame(1);
//			Close.click();
//		}
//			catch (Exception e) {
//				driver.switchTo().frame(2);
//				Close.click();
//			}
//		}
	

//		
//		for(int i = 1; i<=iframes.size(); i++) {
//			try {
//				System.out.println("INSIDE "+i);
//				driver.switchTo().frame(i);
//				Close.click();
//				
//				System.out.println("close");
//				
//				if(Close.isDisplayed()) {
//					System.out.println("FINALLY CLOSED WITH "+i);
//				}
//								
//			} catch (Exception e) {
//				// TODO: handle exception
//				
//				System.out.println("EXCEPTION WITH "+i);
//			}
//		}
//				
//	}



	private Object getDriver() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void isOnPage() {
		// TODO Auto-generated method stub
		
	}
	}
