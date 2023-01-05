package com.avis.qa.pages;

import com.avis.qa.components.ReservationWidget;
import com.avis.qa.core.AbstractBasePage;
import com.avis.qa.core.Configuration;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.avis.qa.constants.TextComparison.KEY_DROP_LOCATION_MESSAGE;
import static com.avis.qa.utilities.CommonUtils.TWO_SECONDS;
import static com.avis.qa.utilities.CommonUtils.threadSleep;
import static org.testng.Assert.assertEquals;


/**
 * This class contains all the elements and actions performed on Vehicles Page
 *
 * @author ikumar
 */
@Log4j2
public class Vehicles extends AbstractBasePage {

    @FindBy(xpath = "(//a[@id='res-vehicles-pay-now'])[1] | (//a[contains(text(),'Select')])[4]|(//a[contains(text(),'Select')])[3]")
    private WebElement step2PayNowSubmitButton;

   // @FindBy(xpath = "(//a[contains(text(),'Select')])[4]|(//a[contains(text(),'Select')])[3]|(//a[@id='res-vehicles-pay-later'])[1] | (//a[contains(text(),'Pay Later')])[2]|(//a[contains(text(),'Pay at Counter')])[1]|(//a[contains(text(),'Paiement au comptoir')])[1]|(//a[contains(text(),'Pagar en el mostrador')])[1]")
//   private WebElement step2SubmitButton;
    //((//p[contains(@class,'totalPay')]/../a)[1]) removed from above xpath

    @FindBy(xpath = "(//a[contains(text(),'Select')])[4]|(//a[contains(text(),'Select')])[3]|(//a[@id='res-vehicles-pay-later'])[4] | (//a[contains(text(),'Pay Later')])[4]|(//a[contains(text(),'Pay at Counter')])[1]|(//a[contains(text(),'Paiement au comptoir')])[1]|(//a[contains(text(),'Pagar en el mostrador')])[1]")
    private WebElement step2SubmitButton;

    @FindBy(xpath = "(//a[contains(text(),'Select')])[4]|(//a[contains(text(),'Select')])[1]|(//a[@id='res-vehicles-pay-later'])[2] | (//a[contains(text(),'Pay Later')])[1]|(//a[contains(text(),'Pay at Counter')])[1]|(//a[contains(text(),'Paiement au comptoir')])[1]|(//a[contains(text(),'Pagar en el mostrador')])[1]")
    private WebElement step2SubmitButton2;

    @FindBy(css = "li[title='Select a Car']")
    private WebElement selectACarText;

    @FindBy(xpath = "(//a[contains(text(),'Pay Later')])[1]|(//a[contains(text(),'Pay at Counter')])[4]|(//a[contains(text(),'Select')])[4]|(//a[contains(text(),'Paiement au comptoir')])[1]|(//a[contains(text(),'Pagar en el mostrador')])[1]|((//p[contains(@class,'totalPay')]/../a)[4]|(//a[contains(text(),'Select')])[4])")
    private WebElement payAtCounter2;

    @FindBy(xpath = "(//li[@ng-click='vm.selectCurrency(option)']/a)[1]")
    private WebElement Currencyvalue1;

    @FindBy(xpath = "(//li[@ng-click='vm.selectCurrency(option)']/a)[2]")
    private WebElement Currencyvalue2;

    @FindBy(xpath = "//a[contains(@class,'dropdown-toggle vehAllVehDrpDwn s-dropdown currencyF&S')]")
    private WebElement currencyField;

    @FindBy(xpath = "//div[contains(@class,'info-error-msg-text')]/span")
    private WebElement underAgeMsg_Step2;

    @FindBy(xpath = "(//del[@ng-if='car.displayRate.displayStkPayLater'])[1]|(//p[@ng-if='car.displayRate.displayStkSelect'])[1]")
    private WebElement StrikreThroughPriceIndicator;

    @FindBy(xpath = "//div[contains(@class,'info-key-drop-text')]/p")
    private WebElement keyDropInfo;

    @FindBy(xpath = "//a[@class='discount-dropdown s-dropdown']")
    private WebElement discountDropdown;

    @FindBy(xpath = "(//input[@id='awd'])[2]")
    private WebElement AWDdiscountCodeTextField;

    @FindBy(xpath = "//a[text()='Update']")
    private WebElement UpdateButton;

    @FindBy(xpath = "(//div[@class='day-time-info'])[1]")
    private WebElement PickupDateTime;

    @FindBy(xpath = "(//div[@class='day-time-info'])[2]")
    private WebElement ReturnDateTime;

    @FindBy(xpath = "(//a[@id='res-vehicles-details'])[1]")
    private WebElement ViewVehicleInformation;

    @FindBy(xpath = "(//a[@id='res-vehicles-details'])[1]")
    private WebElement CloseVehicleInformation;

    @FindBy(xpath = "(//span[@class='four-door-feat'])[1]")
    private WebElement NumberOfDoors;

    @FindBy(xpath = "(//a[@id='res-vehicles-pay-later'])[1]")
    private WebElement DiscountAppliedVehiclePaylaterButton;

    @FindAll(@FindBy(xpath = "//p[@class='striked-text']/following-sibling::a"))
    List<WebElement> allDiscountedVehiclePaylaterButton;

    @FindBy(xpath = "//span[text()='Your USAA savings are reflected below.']")
    private WebElement USAASavingText;

    @FindBy(xpath = "//span[@class='addinlinfo']")
    private WebElement Step2SavingText;

    @FindBy(xpath = "//span[contains(text(),'Tip for International Travelers - Select ')]/child::b[contains(text(),'Pay Now')]/following-sibling::b[text()='Visa' ]/following-sibling::b[text()='Mastercard' ]")
    private WebElement TipForInternationalTravellerText;



    @FindBy(xpath = "//span[text()='Your savings are reflected below.']")
    private WebElement savingText;

    @FindBy(xpath = "(//p[@class='payamntp']/span)[1] | (//sup[@class='currencySymbol'])[1]")
    private WebElement currencySymbol;

    @FindBy(xpath = "//input[@name='res.coupon.couponNumber']")
    private WebElement CouponValue;

    @FindBy(xpath = "//section[@class='infoText']")
    private WebElement CouponSavingText;

    @FindBy(xpath = "(//div[@class='location-info'])[1]")
    private WebElement PickUpLocValue;

    @FindBy(xpath = "(//div[@class='location-info'])[2]")
    private WebElement ReturnLocValue;

    @FindBy(xpath = "//a[@id='res-vehicles-filter-by-vehicle-type']")
    private WebElement AllVehiclesText;

    @FindBy(xpath = "//a[@id='res-vehicles-sort']")
    private WebElement SortByDropDown;

    @FindBy(xpath = "//ul[@class='dropdown-menu']/li[1]/a")
    private WebElement PriceLowToHighValue;
    @FindBy(xpath = "//ul[@class='dropdown-menu']/li[2]/a")
    private WebElement MileageHighToLowValue;

    @FindBy(xpath = "//ul[@class='dropdown-menu']/li[3]/a | //ul[@class='dropdown-menu']/li[2]/a")
    private WebElement SeatsHighToLowValue;

    @FindBy(xpath = "(//span[contains(text(),'Small to Full Size')])[1] | (//span[contains(text(),'Small & Mid Size ')])[1]")
    private WebElement SmallToFullSizeText;

    @FindBy(xpath = "//span[text()='An underage surcharge is applicable except at participating locations.']")
    private WebElement UnderAgeSurchargeTextMsg;

    //Milegae sort High to Low
    @FindAll(@FindBy(xpath= "//p[@class='coupnelg mpg-seats-feat']"))
    List<WebElement> AllMileage;

    @FindAll(@FindBy(xpath= "//div[@class='paybtndtl col-lg-12 col-xs-12']/div/p[@class='payamntp']"))
    List<WebElement> AllPayLaterPrice;

    @FindAll(@FindBy(xpath= "//p[@class='coupnelg four-seats-feat']"))
    List<WebElement> AllNumberOfSeats;


    @FindAll(@FindBy(xpath= "//a[text()='Pay Later']"))
    List<WebElement> NumberOfPaylaterButton;


    @FindBy(xpath = "//span[text()='Your original vehicle selection is available, please confirm  by re-selecting your vehicle.']")
    private WebElement VehicleReselectionTextMessage;

    @FindBy(xpath = "//span[text()='Your discount code is invalid ']")
    private WebElement AWDCouponCodeInvalidErrorMsg;

    @FindBy(xpath = "(//button[@type='reset'])[1]")
    private WebElement AdPopup;


    public Vehicles(WebDriver driver) {
        super(driver);
    }

    int sizeAllVehicle;

    public Extras step2Submit() {
        threadSleep(TWO_SECONDS);
        helper.scrollToElement(step2SubmitButton);
        threadSleep(TWO_SECONDS);
        clickUsingJS(step2SubmitButton);
        return new Extras(driver);
    }

    public Extras step2Submit2() {
        threadSleep(TWO_SECONDS);
        helper.scrollToElement(step2SubmitButton2);
        threadSleep(TWO_SECONDS);
        clickUsingJS(step2SubmitButton2);
        return new Extras(driver);
    }

    public Extras Step2_DiscountAppliedSubmit() {
        threadSleep(TWO_SECONDS);
        helper.scrollToElement(DiscountAppliedVehiclePaylaterButton);
        clickUsingJS(DiscountAppliedVehiclePaylaterButton);
        return new Extras(driver);
    }
    public Extras Step2_ClickDiscountAppliedSubmit() {
    int totalDiscountedvehicles= allDiscountedVehiclePaylaterButton.size();
    if(totalDiscountedvehicles == 0)
    {
        log.info("No discounted vehicle is available");
    }
    else if(totalDiscountedvehicles >= 1) {
        threadSleep(TWO_SECONDS);
        WebElement payLater = allDiscountedVehiclePaylaterButton.get(1);
        helper.scrollToElement(payLater);
        clickUsingJS(payLater);
    }
    return new Extras(driver);
    }


    /**
     * Method to click on Pay Now
     */
    public Extras step2SubmitPayNow() {
        threadSleep(TWO_SECONDS);
        helper.scrollToElement(step2PayNowSubmitButton);
        clickUsingJS(step2PayNowSubmitButton);
        return new Extras(driver);
    }

    public Extras payLater() {
        payAtCounter2.click();
        return new Extras(driver);
    }

    /**
     * Method on click on Currency Label
     *
     * @return
     */
    public boolean isCurrencyValueDisplayed() {
        clickUsingJS(currencyField);
        return Currencyvalue2.isDisplayed() && Currencyvalue1.isDisplayed();
    }

    public boolean
    verifyCurrencySymbolDisplayed(String currencyvalue) {
        return currencySymbol.getText().contains(currencyvalue);
    }

    public boolean
    verifyAllVehiclesTextDisplayed() {
        return AllVehiclesText.isDisplayed();
    }

    public boolean
    verifySmallToFullSizeTextDisplayed() {
        return SmallToFullSizeText.isDisplayed();
    }

    public boolean
    verifyUnderAgeSurchargeTextDisplayed() {
        return UnderAgeSurchargeTextMsg.isDisplayed();
    }


    public boolean verifyCouponSavingtextDisplayed(String CouponMsg)
    {
        System.out.println("Coupon text"+CouponSavingText.getText());
        return CouponSavingText.getText().contains(CouponMsg);
    }

    public boolean isStrikreThroughPriceIndicatorDisplayed() {
        return StrikreThroughPriceIndicator.isDisplayed();
    }

    public boolean isTipForInternationaltravellerTextDisplayed() {
        return TipForInternationalTravellerText.isDisplayed();
    }

    public boolean isVehicleReselectionTextMessageDisplayed() {
        return VehicleReselectionTextMessage.isDisplayed();
    }

    public Vehicles verifyUnderAgeMsg() {
        underAgeMsg_Step2.getText().contains("An underage surcharge is applicable except at participating locations.");
        return this;
    }

    public Vehicles keyDropMessageValidation() {
        String actualMessage = keyDropInfo.getText();
        assertEquals(actualMessage, KEY_DROP_LOCATION_MESSAGE);
        return this;
    }

    public Vehicles DiscountDropDownClick(String awd) {
        discountDropdown.click();
        waitForVisibilityOfElement(AWDdiscountCodeTextField);
        AWDdiscountCodeTextField.click();
        AWDdiscountCodeTextField.clear();
        AWDdiscountCodeTextField.sendKeys(awd);
        UpdateButton.click();
        return this;
    }
    public Vehicles DiscountDropDownAWDClear() {
        AWDdiscountCodeTextField.clear();
        return this;
    }


    public boolean verifyAWDCouponcodeInvalidErrorMessage() {
        return AWDCouponCodeInvalidErrorMsg.isDisplayed();
    }

    public Vehicles isCouponvalueDisplayed(String Couponcode) {
        String actualCoupon = CouponValue.getText();
        assertEquals(actualCoupon, Couponcode);
        return this;
    }

    public boolean isPickUpDateTimeDisplayed(String PickupTime) {
        return PickupDateTime.getText().contains(PickupTime);
    }

    public boolean isDropDateTimeDisplayed(String DropTime) {
        return ReturnDateTime.getText().contains(DropTime);
    }

    public Vehicles clickViewCloseVehicleInformation()
    {
        waitForVisibilityOfElement(ViewVehicleInformation);
        ViewVehicleInformation.click();
       // waitForVisibilityOfElement(NumberOfDoors);
        waitForVisibilityOfElement(CloseVehicleInformation);
        CloseVehicleInformation.click();
        return this;
    }

    public boolean validatePickupAndReturnLocValue(String pickupLoc, String DropLoc) {
        if(PickUpLocValue.getText().contains(pickupLoc) && ReturnLocValue.getText().contains(DropLoc));
        return true;
    }


    public boolean isDiscountDropdownDisplayed() {
        return discountDropdown.getText().contains("Lower Rates/This AWD");
    }

    public boolean isUSAACoupontextDisplayed() {
        return USAASavingText.isDisplayed();
    }

    public boolean isPayLaterButtonEnabled() {
        return step2SubmitButton.isEnabled();
    }

    public boolean savingMessageValidation(String message) {
      //  String actualMessage = Step2SavingText.getText();
     //   assertEquals(actualMessage, message);
        System.out.println("Coupon text"+Step2SavingText.getText());
        return Step2SavingText.getText().contains(message);
        //return this;
    }

    public Vehicles clickFilterOptionAndVerifyData()
    {
        int size = clickAllVehicesOption();
        if(Configuration.BRAND.equalsIgnoreCase("Budget")) {
            helper.mouseHover(SortByDropDown);
            helper.clickIfElementIsDisplayed(SortByDropDown);
            helper.clickIfElementIsDisplayed(PriceLowToHighValue);
        }
        ArrayList<Double> obtainedList1 = new ArrayList<>();
        if(Configuration.DOMAIN.equalsIgnoreCase("AU")) {
            for(WebElement s:AllPayLaterPrice){
                try {
                    obtainedList1.add(Double.parseDouble(s.getText().replaceAll(",", "").substring(2,8).trim()));
                }
                catch(Exception e)
                {
                    obtainedList1.add(Double.parseDouble(s.getText().substring(2,7).trim()));
                }
            }
        }
        else {
            for (WebElement s : AllPayLaterPrice) {
                try {
                    obtainedList1.add(Double.parseDouble(s.getText().replaceAll(",", "").substring(1).trim()));
                } catch (Exception e) {
                    obtainedList1.add(Double.parseDouble(s.getText().substring(1).trim()));
                }
            }
        }
        System.out.println("PayLater Price obtainedList "+obtainedList1);
        ArrayList<Double> sortedList1 = new ArrayList<>();
        for(Double s:obtainedList1){
            sortedList1.add(s);
        }
        Collections.sort(sortedList1);
        System.out.println("Paylater Price sorted list "+sortedList1);
        Assert.assertTrue(sortedList1.equals(obtainedList1));
    if(!Configuration.DOMAIN.equalsIgnoreCase("AU")) {
    helper.mouseHover(SortByDropDown);
    helper.clickIfElementIsDisplayed(SortByDropDown);
    helper.clickIfElementIsDisplayed(MileageHighToLowValue);
    ArrayList<String> obtainedList = new ArrayList<>();
    if (Configuration.BRAND.equalsIgnoreCase("Avis")) {
        for (WebElement s : AllMileage) {
            obtainedList.add(s.getText().substring(3));
        }
    }
    if (Configuration.BRAND.equalsIgnoreCase("Budget")) {
        for (WebElement s : AllMileage) {
            obtainedList.add(s.getText());
        }
    }
    System.out.println("Mileage obtainedList " + obtainedList);
    ArrayList<String> sortedList = new ArrayList<>();
    for (String s : obtainedList) {
        sortedList.add(s);
    }
    Collections.sort(sortedList);
    Collections.reverse(sortedList);
    System.out.println(" Mileage sorted list " + sortedList);
    Assert.assertTrue(sortedList.equals(obtainedList));
    //  Assert.assertTrue(size==AllMileage.size());
}
        helper.mouseHover(SortByDropDown);
        helper.clickIfElementIsDisplayed(SortByDropDown);
        helper.clickIfElementIsDisplayed(SeatsHighToLowValue);
        ArrayList<Integer> obtainedList3 = new ArrayList<>();
        for(WebElement s:AllNumberOfSeats){
            obtainedList3.add(Integer.parseInt(s.getText()));
        }
        System.out.println("obtainedList of seats"+obtainedList3);
        ArrayList<Integer> sortedList3 = new ArrayList<>();
        for(Integer s:obtainedList3){
            sortedList3.add(s);
        }

        Collections.sort(sortedList3);
        Collections.reverse(sortedList3);
        System.out.println("seat sorted list "+sortedList3);
        if (sortedList3.equals(obtainedList3) == true) {
            System.out.println(" Array List are equal");
        }
        else
        {
            System.out.println(" Array List are not equal");
        }
        Assert.assertTrue(size==AllNumberOfSeats.size());
        return this;
    }

    public int clickAllVehicesOption()
    {

        String allVehiclesText =AllVehiclesText.getText();
        if(NumberOfPaylaterButton.size() < 10) {
            String size= allVehiclesText.substring(14, 15);
            sizeAllVehicle= Integer.parseInt(size);
        }
        else {
            String size= allVehiclesText.substring(14, 16);
            sizeAllVehicle= Integer.parseInt(size);
        }
        System.out.println(" size of AllVehicle :"+sizeAllVehicle);
        helper.clickIfElementIsDisplayed(AllVehiclesText);
        verifySmallToFullSizeTextDisplayed();
        helper.clickIfElementIsDisplayed(AllVehiclesText);
        return sizeAllVehicle;
    }

    public Vehicles closeAdPopup()
    {
        helper.clickIfElementIsDisplayed(AdPopup);
        return this;
    }

    public boolean isSavingtextDisplayed() {
        return savingText.isDisplayed();
    }

    @Override
    public void isOnPage() {
        waitForVisibilityOfElement(selectACarText);
    }
}
