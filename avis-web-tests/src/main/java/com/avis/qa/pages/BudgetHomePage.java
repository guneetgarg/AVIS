package com.avis.qa.pages;

import java.util.Map;

import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.avis.qa.core.AbstractBasePage;

public class BudgetHomePage extends AbstractBasePage{
	
	
	public BudgetHomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	private static final String COUPON = "Coupon";
	
	@FindBy(id = "avis-logo")
	private WebElement payLessBrandText;

	@FindBy(xpath = "//div[@class='bx-wrap']")
    private WebElement AdOverLayDiv;

    @FindBy(xpath = "//div[@class='bx-wrap']//button[@data-click='close']")
    private WebElement AdOverLayCloseButton;
    
    @FindBy(xpath = "//li[@class='dropdown']")
    private WebElement reservation;
    
    @FindBy(xpath = "//a[@href='/en/reservation/make-reservation']")
    private WebElement makeaReservation;
    
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

	@FindBy(id = "diffLoc")
	private WebElement returnLocationCheckbox;

	@FindBy(id = "DropLoc_value")
	private WebElement enterReturnLocation;

	@FindBy(xpath = "(//div[@class='angucomplete-description'])[1]")
	private WebElement dropOffSuggestion;
	
	@FindBy(xpath = "(//*[contains(@ng-click,'getVehicles.submit')])[1]")
    private WebElement selectMyCarButton;

	@FindBy(id = "age")
	private WebElement ageDropDown;

	@FindBy(xpath = "(//*[@id='reservationModel.personalInfoRQ.residency'])[1]")
    private WebElement selectCountry;

	@FindBy(id = "couponpdn")
	private WebElement CouponCheckBox;

	@FindBy(id = "coupon")
	private WebElement CouponTextField;

	@FindBy(id = "awd")
	private WebElement pdnTextField;
    
    @FindBy(xpath = "//div[@class='res-discFld form-controlD']")
    private WebElement offerCodes;
    
    @FindBy(xpath = "//div[@class='res-wizardFld form-controlD']")
    private WebElement addCustomerID;
    
    @FindBy(xpath = "//input[@aria-label='Res-wizard Number']")
    private WebElement enterCustomerID ;
    
    @FindBy(xpath = "//input[@id='res-home-lastName']")
    private WebElement enterLastName;
    
    @FindBy(xpath = "//*[@id='awd']")
    private WebElement AWDOrBCDOrPDN_TextField;
    
    @FindBy(xpath = "//input[@id='partnerMembershipId']")
    private WebElement membershipTextField;
    
//    
//    @FindBy(xpath = "")
//    private WebElement ;
//    
//    @FindBy(xpath = "")
//    private WebElement ;
//    
//    @FindBy(xpath = "")
//    private WebElement ;
//    
    
    
    public void selectYourCar(Map testDataMap) {
    	
    	try{
    		clickOn(AdOverLayDiv);
        }catch (TimeoutException e){
            return;
        }
        clickOn(AdOverLayCloseButton);
        clickOn(reservation);
    	clickOn(makeaReservation);
    	clickOn(pickUpLocation);
    	fillText(pickUpLocation, testDataMap.get("PickUpLocation").toString());
    	clickOn(suggestionLocation);
		fillText(pickUpDate,testDataMap.get("PickUpDate").toString());
		fillText(pickUpTime,testDataMap.get("PickUpTime").toString());
		fillText(returnDatePath,testDataMap.get("DropOffDate").toString());
		fillText(dropOffTime,testDataMap.get("DropOffTime").toString());
		if (!testDataMap.get("DropOffLocation").toString().equalsIgnoreCase("NA")) {
			clickOn(returnLocationCheckbox);
			fillText(enterReturnLocation,testDataMap.get("DropOffLocation").toString() );
			clickOn(dropOffSuggestion);
		}
		if (!testDataMap.get("Age").toString().equalsIgnoreCase("NA")) {
			clickOn(ageDropDown);
			fillText(ageDropDown,testDataMap.get("Age").toString());
		}
		if (!testDataMap.get("Country").toString().equalsIgnoreCase("NA")) {
			clickOn(selectCountry);
			fillText(selectCountry,testDataMap.get("Country").toString() );
			clickOn(selectCountry);
		}
		if (!testDataMap.get(COUPON).toString().equalsIgnoreCase("NA")) {
			System.out.println("coupon");
			clickOn(offerCodes);
			clickOn(CouponCheckBox);
			fillText(CouponTextField,testDataMap.get(COUPON).toString() );
		}
		
		if (!testDataMap.get("CustomerID").toString().equalsIgnoreCase("NA")) {
			clickOn(offerCodes);
			clickOn(addCustomerID);
			clickOn(enterCustomerID);
			fillText(enterCustomerID, testDataMap.get("CustomerID").toString());
			fillText(enterLastName, testDataMap.get("LastName1").toString());
		}
		if (!testDataMap.get("BCD").toString().equalsIgnoreCase("NA")) {
			clickOn(offerCodes);
			System.out.println("bcd code");
			clickOn(AWDOrBCDOrPDN_TextField);
			AWDOrBCDOrPDN_TextField.clear();
		    AWDOrBCDOrPDN_TextField.sendKeys(testDataMap.get("BCD").toString(), Keys.TAB);
		    fillText(membershipTextField,testDataMap.get("MemberNumber").toString() );
		}
		
    	clickOn(selectMyCarButton);
    	
    	
    }





	@Override
	public void isOnPage() {
		// TODO Auto-generated method stub
		
	}
	

}
