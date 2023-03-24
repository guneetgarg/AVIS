//package pageObjects;
//
//import static org.testng.Assert.assertTrue;
//
//import java.util.Map;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import com.avis.qa.core.AbstractBasePage;
//
//public class BudgetVehiclesPage extends AbstractBasePage {
//
//	public BudgetVehiclesPage(WebDriver driver) {
//		super(driver);
//		// TODO Auto-generated constructor stub
//	}
//
//	@FindBy(css = "li[title='Select a Car']")
//	private WebElement selectACarText;
//
////	@FindBy(xpath = "(//a[contains(text(),'Select')])[4]|(//a[contains(text(),'Select')])[3]|(//a[@id='res-vehicles-pay-later'])[4] | (//a[contains(text(),'Pay Later')])[4]|(//a[contains(text(),'Pay at Counter')])[1]|(//a[contains(text(),'Paiement au comptoir')])[1]|(//a[contains(text(),'Pagar en el mostrador')])[1]")
////	private WebElement PayLater;
//	@FindBy(xpath = "(//a[contains(text(),'Select')])[4]|(//a[contains(text(),'Select')])[3]|(//a[@id='res-vehicles-pay-later'])[2] | (//a[contains(text(),'Pay Later')])[4]|(//a[contains(text(),'Pay at Counter')])[1]|(//a[contains(text(),'Paiement au comptoir')])[1]|(//a[contains(text(),'Pagar en el mostrador')])[1]")
//	private WebElement PayLater;
//
//	@FindBy(xpath = "(//a[@id='res-vehicles-pay-now'])[1] | (//a[contains(text(),'Select')])[4]|(//a[contains(text(),'Select')])[3]")
//	private WebElement PayNow;
//
//	@FindBy(xpath = "(//div[@class='location-info'])[1] | (//div[@class='summary-location'])[1]")
//	private WebElement pickUpLocationVerify;
//
//	@FindBy(xpath = "(//p[@class='payamntp']/span)[1] | (//sup[@class='currencySymbol'])[1]")
//	private WebElement currencySymbol;
//
//	@FindBy(xpath = "(//div[@class='location-info'])[1]")
//	private WebElement PickUpLocValue;
//
//	@FindBy(xpath = "(//div[@class='location-info'])[2]")
//	private WebElement ReturnLocValue;
//
//	@FindBy(xpath = "(//div[@class='day-time-info'])[1]")
//	private WebElement PickupDateTime;
//
//	@FindBy(xpath = "(//div[@class='day-time-info'])[2]")
//	private WebElement ReturnDateTime;
//
//	public void chooseVehicles(Map testDataMap) {
//		WebDriverWait wait= new WebDriverWait(driver, 90);
//		wait.until(ExpectedConditions.visibilityOf(selectACarText));
//		assertTrue(selectACarText.getText().toString().contains("Select a Car"));
////		assertTrue(pickUpLocationVerify.getText().toString().contains(testDataMap.get("PickUpLocation").toString()));
//		if(!testDataMap.get("DropOffLocation").toString().equalsIgnoreCase("NA")) {
//			assertTrue(ReturnLocValue.getText().toString().contains(testDataMap.get("DropOffLocation").toString()));
//		}
//		if(!testDataMap.get("PickUpTime").toString().equalsIgnoreCase("NA")) {
//			assertTrue(PickupDateTime.getText().toString().contains(testDataMap.get("PickUpTime").toString()));
//		}
//		if(!testDataMap.get("DropOffTime").toString().equalsIgnoreCase("NA")) {
//			assertTrue(ReturnDateTime.getText().toString().contains(testDataMap.get("DropOffTime").toString()));
//		}
//
//		if(testDataMap.get("Paylater&Paynow").toString().equalsIgnoreCase("Paylater")) {
//			helper.scrollToElement(PayLater);
//			clickOn(PayLater);
//		}
//		if(testDataMap.get("Paylater&Paynow").toString().equalsIgnoreCase("PayNow")) {
//			clickOn(PayNow);
//		}
//	}
//	@Override
//	public void isOnPage() {
//		// TODO Auto-generated method stub
//	}
//}

package pageObjects;

import static org.testng.Assert.assertTrue;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.avis.qa.core.AbstractBasePage;

public class BudgetVehiclesPage extends AbstractBasePage {

	public BudgetVehiclesPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(css = "li[title='Select a Car']")
	private WebElement selectACarText;

//	@FindBy(xpath = "(//a[contains(text(),'Select')])[4]|(//a[contains(text(),'Select')])[3]|(//a[@id='res-vehicles-pay-later'])[4] | (//a[contains(text(),'Pay Later')])[4]|(//a[contains(text(),'Pay at Counter')])[1]|(//a[contains(text(),'Paiement au comptoir')])[1]|(//a[contains(text(),'Pagar en el mostrador')])[1]")
//	private WebElement PayLater;
	@FindBy(xpath = "(//a[contains(text(),'Select')])[4]|(//a[contains(text(),'Select')])[3]|(//a[@id='res-vehicles-pay-later'])[2] | (//a[contains(text(),'Pay Later')])[4]|(//a[contains(text(),'Pay at Counter')])[1]|(//a[contains(text(),'Paiement au comptoir')])[1]|(//a[contains(text(),'Pagar en el mostrador')])[1]")
	private WebElement PayLater;

	@FindBy(xpath = "(//a[@id='res-vehicles-pay-now'])[1] | (//a[contains(text(),'Select')])[4]|(//a[contains(text(),'Select')])[3]")
	private WebElement PayNow;

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

	public void chooseVehicles(Map testDataMap) {
		WebDriverWait wait= new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(selectACarText));
		assertTrue(selectACarText.getText().toString().contains("Select a Car"));
		
		String location = pickUpLocationVerify.getText();
		String [] locationValue = pickUpLocationVerify.getText().split("");
		String locations = locationValue[1].replaceAll("", "");
		
		assertTrue(pickUpLocationVerify.getText().toString().contains(locations));
//		assertTrue(pickUpLocationVerify.getText().toString().contains(testDataMap.get("PickUpLocation").toString()));
		if(!testDataMap.get("DropOffLocation").toString().equalsIgnoreCase("NA")) {
			assertTrue(ReturnLocValue.getText().toString().contains(testDataMap.get("DropOffLocation").toString()));
		}
		if(!testDataMap.get("PickUpTime").toString().equalsIgnoreCase("NA")) {
			assertTrue(PickupDateTime.getText().toString().contains(testDataMap.get("PickUpTime").toString()));
		}
		if(!testDataMap.get("DropOffTime").toString().equalsIgnoreCase("NA")) {
			assertTrue(ReturnDateTime.getText().toString().contains(testDataMap.get("DropOffTime").toString()));
		}

		if(testDataMap.get("Paylater&Paynow").toString().equalsIgnoreCase("Paylater")) {
			wait.until(ExpectedConditions.visibilityOf(PayLater));
			helper.scrollToElement(PayLater);
			clickOn(PayLater);
		}
		if(testDataMap.get("Paylater&Paynow").toString().equalsIgnoreCase("PayNow")) {
			wait.until(ExpectedConditions.visibilityOf(PayNow));
			clickOn(PayNow);
		}
	}
	@Override
	public void isOnPage() {
		// TODO Auto-generated method stub
	}
}