package com.avis.qa.pages;

import static org.testng.Assert.assertTrue;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.avis.qa.core.AbstractBasePage;

public class VehiclesPage extends AbstractBasePage {

	public VehiclesPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "(//a[contains(text(),'Select')])[4]|(//a[contains(text(),'Select')])[3]|(//a[@id='res-vehicles-pay-later'])[1] | (//a[contains(text(),'Pay Later')])[2]|(//a[contains(text(),'Pay at Counter')])[1]|(//a[contains(text(),'Paiement au comptoir')])[1]|(//a[contains(text(),'Pagar en el mostrador')])[1]|((//p[contains(@class,'totalPay')]/../a)[1])")
	private WebElement PayLater;

	@FindBy(css = "li[title='Choose a Car']")
	private WebElement selectACarText;

	@FindBy(xpath = "(//a[contains(text(),'Pay Now')])[4]|(//a[contains(text(),'Payer maintenant')])[1]|(//a[contains(text(),'Pagar ahora')])[1]|(//a[contains(text(),'Select')])[1]")
	private WebElement PaynowButton;

	@FindBy(xpath = "(//div[@class='location-info'])[1] | (//div[@class='summary-location'])[1]")
	private WebElement pickUpLocationVerify;

	@FindBy(xpath = "(//div[@class='vehicle-name'])[1] | //p[@class='car-name']")
	private WebElement vehicleName;

	@FindBy(xpath = "//li[@ng-if='vm.response.reservationSummary.coupon.number']/span[2] | //span[@class='coupon-value']")
	private WebElement couponCode;

	@FindBy(xpath = "//li[@ng-if='vm.response.reservationSummary.awdNumber']/span[2] | //p[contains(@ng-if,'vm.confirmation.reservationSummary.awdNumber')]")
	private WebElement pdn;


	public void chooseVehicles(Map testDataMap) {
		assertTrue(pickUpLocationVerify.getText().toString().contains(testDataMap.get("PickUpLocation").toString()));
		if (!testDataMap.get("Coupon").toString().equalsIgnoreCase("NA")) {
			assertTrue(couponCode.getText().toString().contains(testDataMap.get("Coupon").toString())); 
		}
		if (!testDataMap.get("PDN").toString().equalsIgnoreCase("NA")) {
			assertTrue(pdn.getText().toString().contains(testDataMap.get("PDN").toString())); 
		}
		assertTrue(selectACarText.getText().toString().contains("Choose a Car"));
		if(testDataMap.get("Paylater&Paynow").toString().equalsIgnoreCase("Paylater")) {
			helper.scrollToElement(PayLater);
			clickOn(PayLater);
		}
		if(testDataMap.get("Paylater&Paynow").toString().equalsIgnoreCase("PayNow")) {
			clickOn(PaynowButton);
		}

	}

	@Override
	public void isOnPage() {
		// TODO Auto-generated method stub

	}


}
