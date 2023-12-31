package com.avis.qa.pages;

import com.avis.qa.core.AbstractBasePage;
import com.avis.qa.core.Configuration;
import com.avis.qa.utilities.CommonUtils;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

import static com.avis.qa.utilities.CommonUtils.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static com.avis.qa.constants.AvisConstants.*;


/**
 * This class contains all the elements and actions performed on Extras Page
 *
 * @author ikumar
 */
@Log4j2
public class Extras extends AbstractBasePage {

    @FindBy(xpath = "//button[contains(@ng-click,'vm.review()')]")
    private WebElement submitStep3;

    @FindBy(xpath = "(//label[@for='RSNchk'])[1]")
    private WebElement TieredBundle;

    @FindBy(xpath = "//input[@id='Protection & Safety PackagepaiChk']")
    private WebElement ProtectionSafetyTireBundle_old;

    @FindBy(xpath = "//label[@for='Protection & Safety PackagepaiChk']")
    private WebElement ProtectionSafetyTireBundle;

    @FindBy(id = "Step3-Services")
    private WebElement Step3ServicesTab;

    @FindBy(xpath = "//a[text()='Discount Packages']")
    private WebElement Step3DiscountPackages;

    @FindBy(xpath = "//span[text()='Discount Code Savings'] | //span[text()='Coupon savings applied ']")
    private WebElement DiscountCodeSaving;

    @FindBy(xpath = "(//span[@class='pull-left'])[2]")
    private WebElement currencySymbol;

    @FindBy(xpath = "//p[contains(text(),'Curbside Drop Off')] | //p[contains(text(),'Curbside Drop')]")
    private WebElement CurbsideDropoffText;

    @FindBy(xpath = "//p[text()='Certain extras are included or discounted due to your provided AWD number.']")
    private WebElement ExtrasIncludedText;

    @FindBy(xpath = "//span[contains(text(),'Your provided AWD number includes discounts or certain extras, and may include')] ")
    private WebElement AWDIncludedCoveragesText;

    @FindBy(xpath = "//a[@id='Step3-Protections & Coverages']")
    private WebElement ProtectionAndCoveragesTab;

    @FindBy(xpath = "//*[contains(text(),'Protections & Coverages')]")
    private WebElement Budget_ProtectionAndCoverages;

    @FindBy(xpath = "//a[contains(text(),'Equipment')]")
    private WebElement EquipmentTab;

    @FindBy(xpath = "//*[contains(text(),'Equipment')]")
    private WebElement Budget_EquipmentAndServices;

    @FindBy(xpath = "(//span[@class='c-icon uplift-logo'])[2]")
    private WebElement Budget_UpliftLogo;

    @FindBy(xpath = "//input[@id='CDWchk']")
    private WebElement LDWCheckbox;

    @FindBy(xpath = "//input[@id='PAIchk']")
    private WebElement PAICheckbox;

    @FindBy(xpath = "(//div[@class='location-info'])[1]")
    private WebElement PickUpLocValue;

    @FindBy(xpath = "(//div[@class='location-info'])[2]")
    private WebElement ReturnLocValue;

    @FindBy(xpath = "(//div[@class='day-time-info'])[1]")
    private WebElement PickupDateTime;

    @FindBy(xpath = "(//div[@class='day-time-info'])[2]")
    private WebElement ReturnDateTime;

    @FindBy(xpath = "//span[@class='four-seats-feat']")
    private WebElement NumberOfSeats;

    @FindBy(xpath = "//a[@id='rate-terms']")
    private WebElement SeeRateTerms;

    @FindBy(xpath = "//span[text()='Base Rate']")
    private WebElement BaseRate;

    @FindBy(xpath = "(//p[contains(text(),'Third Party Liability')]/ancestor::div[contains(@ng-class,'selected')]//Span[contains(text(),'INCLUDED')])[1]")
     private WebElement thirdPartyLiabilityIncluded;

    @FindBy(xpath="(//p[contains(text(),'Cover The Car (LDW)')]/ancestor::div[contains(@ng-class,'selected')]//Span[contains(text(),'INCLUDED')])[1]")
    private WebElement ldWIncluded;

    @FindBy(xpath="(//span[@class='tick-icon']//parent::div//span[contains(text(),'Unlimited')])[1]")
     private WebElement unlimitedKilometer;

    @FindBy(xpath = "(//span[@class='tick-icon']//parent::div//span[contains(text(),'0 Due Today')])[1]")
    private WebElement zeroDueToday;

    @FindBy(xpath="(//span[@class='tick-icon']//parent::span//span[text()='Free Cancellation'])[1]")
     private  WebElement freeCancellation;

    @FindBy(xpath = "//*[@id='google-close-button']/a")
    private WebElement googleCloseBtn;

    HashMap<String,String> awdMessage=new HashMap<String,String >() ;
    HashMap<String, String> awdIncluded=new HashMap<String, String>();


    public Extras(WebDriver driver) {
        super(driver);
    }
    {
        if (Configuration.DOMAIN.equals("CA"))
        LDWCheckbox=PAICheckbox;
    }
    {
        awdMessage.put("B771000","Your provided AWD number includes or discounts certain extras, and may include Loss Damage Waiver (LDW) and Liability Coverage. Subject to terms and conditions");
        additionalCharges.put("EquipmentServiceCharges",0.0);
        additionalCharges.put("ProtectionCoverageCharges",0.0);

    }


    public ReviewAndBook Step3Submit() {
        threadSleep(TWO_SECONDS);
        helper.scrollToElement(submitStep3);
        clickUsingJS(submitStep3);
        return new ReviewAndBook(driver);
    }

    public boolean isSubmitStepThreeDisplayed() {
        return submitStep3.isDisplayed();
    }

    public Extras selectTierBundle() {
        clickOn(Step3DiscountPackages);
        if (helper.isElementDisplayed(ProtectionSafetyTireBundle))
            clickUsingJS(ProtectionSafetyTireBundle);
        CommonUtils.threadSleep(TWO_SECONDS);
        return this;
    }

    public boolean isDiscountCodeSavingtextDisplayed() {
        return DiscountCodeSaving.isDisplayed();
    }

    public boolean isAWDIncludedInsuranceCoveragetextDisplayed() {
        return AWDIncludedCoveragesText.isDisplayed();
    }

    public boolean verifyCurrencySymbolDisplayed(String symbol) {
        return currencySymbol.getText().contains(symbol);
    }

    public boolean isExtrasIncludedTextDisplayed() {
        return ExtrasIncludedText.isDisplayed();
    }

    public boolean isUpliftTextDisplayed() {
        return Budget_UpliftLogo.isDisplayed();
    }

    public boolean isExtrasTabDisplayed() {
        if(Configuration.BRAND.equalsIgnoreCase("Avis")) {
            if (ProtectionAndCoveragesTab.isDisplayed() && Step3ServicesTab.isDisplayed() && EquipmentTab.isDisplayed() && Step3DiscountPackages.isDisplayed()) ;
            return true;
        }
        else if(Configuration.BRAND.equalsIgnoreCase("Budget"))
        {
            System.out.println("test");
            if (Budget_ProtectionAndCoverages.isDisplayed() && Budget_EquipmentAndServices.isDisplayed()) ;
            return true;
        }
        return false;
    }

    public void verifyCurbsideNotDisplayed(){
        if(Configuration.BRAND.equalsIgnoreCase("Avis"))
        {
            waitForVisibilityOfElement(Step3ServicesTab).click();
        }

        else if(Configuration.BRAND.equalsIgnoreCase("Budget") && Configuration.DOMAIN.equalsIgnoreCase("US"))
        {
            waitForVisibilityOfElement(Budget_EquipmentAndServices);
        }
        //waitForVisibilityOfElement(CurbsideDropoffText);
        //Assert.assertTrue(!CurbsideDropoffText.isDisplayed());
        try {
             CurbsideDropoffText.isDisplayed();
             Assert.assertFalse(true,"Curbside Drop off text not present");
        }
        catch(Exception e) {
            System.out.println("Curbside Drop off text not present");
            Assert.assertFalse(false,"Curbside Drop off text present");
        }
    }

    public boolean verifyLossDamageWaiverIsSelected() {
        waitForVisibilityOfElement(ProtectionAndCoveragesTab).click();
        return LDWCheckbox.isSelected();
    }

    public Extras ClickLDWCoverage() {
        if(Configuration.BRAND.equalsIgnoreCase("Budget")) {
            waitForVisibilityOfElement(Budget_ProtectionAndCoverages).click();
        }
        else {
            waitForVisibilityOfElement(ProtectionAndCoveragesTab).click();
        }
        CommonUtils.threadSleep(ONE_SECOND);
        clickOn(LDWCheckbox);
        CommonUtils.threadSleep(TWO_SECONDS);
        return this;
    }

    public boolean validatePickupAndReturnLocValue(String pickupLoc, String DropLoc) {
        if(PickUpLocValue.getText().contains(pickupLoc) && ReturnLocValue.getText().contains(DropLoc));
        return true;
    }

    public boolean isPickUpDateTimeDisplayed(String PickupTime) {
        return PickupDateTime.getText().contains(PickupTime);
    }

    public boolean isDropDateTimeDisplayed(String DropTime) {
        return ReturnDateTime.getText().contains(DropTime);
    }

    public Boolean isRateTermAndBaseRateAndNumberOfSeatsDisplayed()
    {
        if(BaseRate.isDisplayed() && SeeRateTerms.isDisplayed() && NumberOfSeats.isDisplayed());
        return true;
    }

    @Override
    public void isOnPage() {
        log.info("Verify Extras Page");
        try{
            clickOn(googleCloseBtn);
        }catch(Exception e){
            e.printStackTrace();
        }
        waitForVisibilityOfElement(submitStep3);
    }

    public boolean isAwdMessageMatch(String awdNumber)
    {
        if(isAWDIncludedInsuranceCoveragetextDisplayed())
        {
            String message=AWDIncludedCoveragesText.getText();
            System.out.println(message);
            if(message.contains(awdMessage.get(awdNumber)))
                return true;
            else
                return  false;
        }
        else
            return false;

    }

    public boolean isAWDIncluded(String awd)
    {
          boolean isAWDIncluded =false;
        try {
            if (awd.equals("B771000")) {
                waitForVisibilityOfElement(ProtectionAndCoveragesTab).click();
                ProtectionAndCoveragesTab.click();
                ldWIncluded.isDisplayed();
                thirdPartyLiabilityIncluded.isDisplayed();
                isAWDIncluded=true;

            }
        }
        catch (Exception e)
        {
            isAWDIncluded=false;
        }

        return isAWDIncluded;
    }
    public boolean isUnlimitedKilometerDisplay()
    {
        try{
            return unlimitedKilometer.isDisplayed();
        }
        catch(Exception e)
        {
            log.info("UnlimitedKilometer is not Displayed");
            return false;
        }
    }
    public boolean isDueTodayDisplay()
    {

        try{
            return zeroDueToday.isDisplayed();
        }
        catch(Exception e)
        {
            log.info("Free Cancellation is not Displayed");
            return false;
        }
    }
    public boolean isFreeCancellationDisplay()
    {
        try{
            return freeCancellation.isDisplayed();
        }
        catch(Exception e)
        {
            log.info("Zero Due is not Displayed");
            return false;
        }
    }
}
