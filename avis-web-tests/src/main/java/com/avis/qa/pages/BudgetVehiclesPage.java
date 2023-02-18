package com.avis.qa.pages;

import static org.testng.Assert.assertTrue;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.avis.qa.core.AbstractBasePage;

public class BudgetVehiclesPage extends AbstractBasePage {

	public BudgetVehiclesPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(css = "li[title='Select a Car']")
	private WebElement selectACarText;
	
	@FindBy(xpath = "(//a[contains(text(),'Select')])[4]|(//a[contains(text(),'Select')])[3]|(//a[@id='res-vehicles-pay-later'])[4] | (//a[contains(text(),'Pay Later')])[4]|(//a[contains(text(),'Pay at Counter')])[1]|(//a[contains(text(),'Paiement au comptoir')])[1]|(//a[contains(text(),'Pagar en el mostrador')])[1]")
    private WebElement PayLater;
	
	@FindBy(xpath = "(//a[@id='res-vehicles-pay-now'])[1] | (//a[contains(text(),'Select')])[4]|(//a[contains(text(),'Select')])[3]")
    private WebElement PayNow;
	
	@FindBy(xpath = "(//div[@class='location-info'])[1] | (//div[@class='summary-location'])[1]")
	private WebElement pickUpLocationVerify;
	
	public void chooseVehicles(Map testDataMap) {
		assertTrue(selectACarText.getText().toString().contains("Select a Car"));
		assertTrue(pickUpLocationVerify.getText().toString().contains(testDataMap.get("PickUpLocation").toString()));
		
		if(testDataMap.get("Paylater&Paynow").toString().equalsIgnoreCase("Paylater")) {
			helper.scrollToElement(PayLater);
			clickOn(PayLater);
		}
		if(testDataMap.get("Paylater&Paynow").toString().equalsIgnoreCase("PayNow")) {
			clickOn(PayNow);
		}

		
		

		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@Override
	public void isOnPage() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	
	
	

}
