package pageObjects;

import static org.testng.Assert.assertTrue;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.avis.qa.core.AbstractBasePage;
import com.avis.qa.core.Configuration;

public class BudgetVehiclesPage extends AbstractBasePage {

	public BudgetVehiclesPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(css = "li[title='Select a Car']")
	private WebElement selectACarText;

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
	
	@FindBy(xpath = "//div[@class='info-key-drop-text']")
	private WebElement verifyKeydropmessage;
	
//	@FindBy(xpath = "//div[@class='payatcntr col-xs-6']//span[@ng-bind-html='car.payLaterRate.currencySymbol']")
	@FindBy(xpath = "//span[@ng-bind-html='car.payLaterRate.currencySymbol']")
	private WebElement verifySymbol;
	
	@FindBy(xpath = "//a[@ng-bind='vm.userCurrency']")
    private WebElement Currency;
    
    @FindBy(xpath = "//li[@ng-repeat='option in vm.uniqueCurrencylist'][2]")
    private WebElement CurrencyList;
    
    
	
	public void chooseVehicles(Map testDataMap) {
		WebDriverWait wait= new WebDriverWait(driver, 60);
		if(!testDataMap.get("PickUpLocation").toString().equalsIgnoreCase("NA")) {
		wait.until(ExpectedConditions.visibilityOf(selectACarText));
		assertTrue(selectACarText.getText().toString().contains("Select a Car"));
		if(Configuration.DOMAIN.equalsIgnoreCase("US") ) {
		verifySymbol.isDisplayed();
		}
		String location = pickUpLocationVerify.getText();
		String [] locationValue = pickUpLocationVerify.getText().split("");
		String locations = locationValue[1].replaceAll("", "");
//		if(!testDataMap.get("PickUpLocation").toString().equalsIgnoreCase("NA")) {
		assertTrue(pickUpLocationVerify.getText().toString().contains(testDataMap.get("PickUpLocation").toString()));
		}
		if(!testDataMap.get("DropOffLocation").toString().equalsIgnoreCase("NA")) {
			assertTrue(ReturnLocValue.getText().toString().contains(testDataMap.get("DropOffLocation").toString()));
		}
		 if(!testDataMap.get("Currency").toString().equalsIgnoreCase("NA")) {
             wait.until(ExpectedConditions.visibilityOf(Currency));
             clickOn(Currency);
             clickOn(CurrencyList);
             wait.until(ExpectedConditions.visibilityOf(Currency));
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
		if(testDataMap.get("VerifyKeyDropMessage").toString().equalsIgnoreCase("Yes")) {
			verifyKeydropmessage.isDisplayed();
		}
	}
	
	@Override
	public void isOnPage() {
		// TODO Auto-generated method stub
	}
}