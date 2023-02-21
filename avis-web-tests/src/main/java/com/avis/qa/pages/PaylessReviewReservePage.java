package com.avis.qa.pages;

import static com.avis.qa.utilities.CommonUtils.FIVE_SECONDS;
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

	@FindBy(xpath = "//button[@aria-label='Close']")
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

	@FindBy(xpath = "//span[text()='Edit']")
	private WebElement editLink;

	@FindBy(xpath = "(//a[contains(text(),'Pay Now')])[4]|(//a[contains(text(),'Payer maintenant')])[1]|(//a[contains(text(),'Pagar ahora')])[1]|(//a[contains(text(),'Select')])[1]")
	private WebElement PaynowButton;

	@FindBy(xpath = "//label[@class=\"step4-checkbox-custom-label\"]")
	private WebElement CheckboxPaynow;

	@FindBy(id = "expiryyear")
	private WebElement expiryYearDropDown;

	@FindBy(id = "expirydate")
	private WebElement expiryMonthDropDown;
	
	@FindBy(xpath = "//h1[@ng-if=\"(vm.pageName == carRentalConstant.confirmationPage || (vm.pageName == '/display-confirmationPage')) && !vm.disableModifyCancel\"]")
	private WebElement reservationConfirmation;
	
	@FindBy(xpath = "(//div[@class='location-info'])[1] | (//div[@class='summary-location'])[1]")
	private WebElement pickUpLocationVerify;
	
	@FindBy(xpath = "(//div[@class='day-time-info'])[1] | (//div[@class='summary-time'])[1]")
	private WebElement pickUpDate;
	
	@FindBy(xpath = "(//div[contains(div/text(),'Your Information')]//child::p)")
	private List<WebElement> userInfoList;
	
	@FindBy(xpath = "//span[@class='step-title']")
	private WebElement mofifyChooseCarVerify;
	
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
	
	@FindBy(xpath = "//span[@class='confirmation-num']")
    private WebElement confirmationNumber;

	@FindBy(xpath = "//label[@class='step4-checkbox-custom-label']")
    private WebElement cancelTerms;
	
	@FindBy(xpath = "//button[@ng-click='vm.cancelReservation.submit(cancelform)']")
    private WebElement cancelReservation;
	
	@FindBy(xpath = "//h1[@class='confirmation-msg']")
    private WebElement cancelledReservation;
	
	
	public PaylessReviewReservePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public void reviewPage(Map testDataMap) {

		assertTrue(MakeMyReservation.getText().contains("Make My Reservation"));
		if(testDataMap.get("UserType").toString().equalsIgnoreCase("Guest")) {			
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

				System.out.println("guest paynow");
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

			threadSleep(TEN_SECONDS);
			List<WebElement> s = driver.findElements(By.tagName("iframe"));
			for(int i=0; i < s.size(); i++){
				if(s.get(i).getAttribute("id").contains("rokt-placement")) {
					driver.switchTo().frame(s.get(i).getAttribute("id"));	
					List<WebElement> t = driver.findElements(By.tagName("iframe"));
					driver.switchTo().frame(t.get(0).getAttribute("id"));
					driver.findElement(By.xpath("//button[@aria-label='Close']")).click();
					break;
				}				
			}
			
			assertTrue(reservationConfirmation.getText().contains("Your car is reserved."));
			assertTrue(pickUpLocationVerify.getText().toString().contains(testDataMap.get("PickUpLocation").toString()));
			assertTrue(userInfoList.get(0).getText().contains(testDataMap.get("Email").toString()));
			threadSleep(FIVE_SECONDS);
			String confirmationNo = confirmationNumber.getText();
			String [] confirmationNoValue= confirmationNumber.getText().split(": ");
			String reservationNumber = confirmationNoValue[1].replaceAll(":", "");
			
			if(testDataMap.get("ModifyReservation").toString().equalsIgnoreCase("YES")) {
				clickOn(editLink);
//				assertTrue(mofifyChooseCarVerify.getText().contains("Modify: Choose a Car"));
				clickOn(PayLater);
				clickOn(CONTINUEBUTTON);
				clickOn(reviewModificationsButton);
				clickOn(keepModificationButton);
				clickOn(makeNewReservationButton);
				if(!testDataMap.get("CancelReservation").toString().equalsIgnoreCase("NA")) {
					clickOn(viewOrModifyReservationButton);
					fillText(lastNameTextField, testDataMap.get("LastName").toString());
					confirmationNumberTextField.sendKeys(reservationNumber);
					clickOn(submitButton);
					threadSleep(TEN_SECONDS);
					cancelReservationButton.click();
					confirmCancelReservationButton.click();
				}
		}
		}
		else {
			if(!testDataMap.get("PhoneNumber").toString().equalsIgnoreCase("NA")) {
				fillText(phoneField, testDataMap.get("PhoneNumber").toString());
			}
			if(!testDataMap.get("IATA").toString().equalsIgnoreCase("NA")) {
				fillText(IataTextFiled,testDataMap.get("IATA").toString() );
			}
//			if(!testDataMap.get("CCNumber").toString().equalsIgnoreCase("NA")) {
//				fillText(cardNumber,testDataMap.get("CCNumber").toString());
			fillText(cardNumber,"343248952280825");
			if(!testDataMap.get("ExpiryMonth").toString().equalsIgnoreCase("NA")) {
				clickOn(expiryMonthDropDown);
				fillText(expiryMonthDropDown,testDataMap.get("ExpiryMonth").toString());
				clickOn(expiryYearDropDown);
				fillText(expiryYearDropDown, testDataMap.get("ExpiryYear").toString());
				fillText(step4_CVV,testDataMap.get("CVV").toString());

			}
//			}
		clickOn(termsCheck);
		clickOn(SubmitButton);

		threadSleep(TEN_SECONDS);
		List<WebElement> s1 = driver.findElements(By.tagName("iframe"));
		for(int i=0; i < s1.size(); i++){
			if(s1.get(i).getAttribute("id").contains("rokt-placement")) {
				driver.switchTo().frame(s1.get(i).getAttribute("id"));	
				List<WebElement> t = driver.findElements(By.tagName("iframe"));
				driver.switchTo().frame(t.get(0).getAttribute("id"));
				driver.findElement(By.xpath("//button[@aria-label='Close']")).click();
				break;
			}				
		}
		
		assertTrue(reservationConfirmation.getText().contains("Your car is reserved."));
		assertTrue(pickUpLocationVerify.getText().toString().contains(testDataMap.get("PickUpLocation").toString()));
		String confirmationNo1 = confirmationNumber.getText();
		String [] confirmationNoValue1= confirmationNumber.getText().split(": ");
		String reservationNumber1 = confirmationNoValue1[1].replaceAll(":", "");
		
		if(testDataMap.get("ModifyReservation").toString().equalsIgnoreCase("YES")) {
			clickOn(editLink);
//			assertTrue(mofifyChooseCarVerify.getText().contains("Modify: Choose a Car"));
			clickOn(PayLater);
			clickOn(CONTINUEBUTTON);
			clickOn(reviewModificationsButton);
			clickOn(keepModificationButton);
			clickOn(makeNewReservationButton);
			if(!testDataMap.get("CancelReservation").toString().equalsIgnoreCase("NA")) {
				clickOn(viewOrModifyReservationButton);
				fillText(lastNameTextField, testDataMap.get("LastName").toString());
				confirmationNumberTextField.sendKeys(reservationNumber1);
				clickOn(submitButton);
				threadSleep(TEN_SECONDS);
				clickOn(cancelReservationButton);
				clickOn(confirmCancelReservationButton);
				clickOn(cancelTerms);
				clickOn(cancelReservation);

			}
			
		}
		
		}
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
