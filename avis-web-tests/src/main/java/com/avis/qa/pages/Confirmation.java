package com.avis.qa.pages;

import com.avis.qa.components.ReservationWidget;
import com.avis.qa.core.AbstractBasePage;
import com.avis.qa.core.Configuration;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.avis.qa.constants.AvisConstants.additionalCharges;
import static com.avis.qa.constants.AvisConstants.fareCharges;
import static com.avis.qa.constants.TextComparison.KEY_DROP_LOCATION_MESSAGE;
import static com.avis.qa.utilities.CommonUtils.*;
import static org.testng.Assert.assertEquals;
import static com.avis.qa.constants.AvisConstants.*;
import static org.testng.Assert.assertTrue;


/**
 * This class contains all the elements and actions performed on Confirmation Page
 *
 * @author ikumar
 */
@Log4j2
public class Confirmation extends AbstractBasePage {

    @FindBy(xpath = "//span[@class='confirmation-num']")
    private WebElement confirmationNumber;

    @FindBy(xpath = "//a[@class='navbar-brand']")
    private WebElement avisLogo;

    @FindBy(xpath = "//button[contains(@id,'res-cancelReservation')]")
    private WebElement cancelResCTA;

    @FindBy(xpath = "//label[@class='step4-checkbox-custom-label']")
    private WebElement CancelConfirmationCheckBox;

    @FindBy(xpath = "//button[contains(@id,'res-cancelreservation')]")
    private WebElement confirmCancelResCTA;

    @FindBy(xpath = "(//div[@class='col-lg-4 col-md-4 col-sm-4 col-xs-12 pad-zero']/p)[1]")
    private WebElement AWDConfirmationPage;

    @FindBy(id = "res-cancelreservation-popUp")
    private WebElement cancelReservationPopup;

    @FindBy(xpath = "//div[@class='col-lg-12 message-success']")
    private WebElement OneClickGPSAdded;

    @FindBy(xpath = "(//p[contains(text(),'Coupon savings applied')])[2]|//span[contains(@class,'coupon-value')]")
    private WebElement ConfirmationCouponValidation;

    @FindBy(xpath = "(//p[contains(text(),'AWD savings applied')])[2] | (//p[contains(text(),'BCD savings applied')])[2]")
    private WebElement ConfirmationAWDCouponValidation;

    @FindBy(xpath = "//button[@aria-label='Close']")
    private WebElement getFreeCouponPopup;

    @FindBy(xpath = "//iframe[@title = 'Rokt placement']")
    private WebElement roktPlacementFrame;

    @FindBy(xpath = "//iframe[@title = 'Rokt offer']")
    private WebElement roktOfferFrame;

    @FindBy(xpath = "(//span[@data-dismiss='modal'])[13]")
    private WebElement OfferPopup;

    @FindBy(xpath = "//strong[contains(text(),'Equipment & Services')]")
    private WebElement EquipmentAndServiceProductsInfo;

    @FindBy(xpath = "(//span[contains(text(),'Snow Chains')])[2]")
    private WebElement SnowChainProductInfo;

    @FindBy(xpath = "//span[contains(text(),'Selected')]")
    private WebElement SnowChainProductSelectedStatus;

    @FindBy(xpath = "(//*[contains(text(),'Modify')])[4]")
    private WebElement modifyTimeAndPlace;

    @FindBy(xpath = "//div[@class='flyout-image-wrapper']//span[@class='close-icon-black']")
    private WebElement flyoutClosebtn;

    @FindBy(xpath = "(//div[@class='col-xs-6']//span)[1]")
    private WebElement CurrencyConfirmationPage;

    @FindBy(xpath = "//span[contains(@ng-if,'product.code != carRentalConstant.equipmentCodeCSSG')]")
    private WebElement rentalOptionsRSN;

    @FindBy(xpath = "//div[@class='rate-benefit yrinfosec confirmation-key clearfix']/h3")
    private WebElement NameDetails;

    @FindBy(xpath = " //strong[contains(text(),'Email')]/parent::p")
    private WebElement EmailConfirmationPage;

    @FindBy(xpath = "//div[contains(@class,'info-key-drop-text')]/p")
    private WebElement keyDropInfo;

    @FindBy(xpath = "//div[text()='Loss Damage Waiver (LDW)']/following-sibling::div/span[contains(text(),'Accepted')]")
    private WebElement LDWAcceptedText;

    @FindBy(xpath = "//div[text()='Cover The Car (LDW)']/following-sibling::div/span[contains(text(),'Accepted')]")
    private WebElement ProtectionAndCoverageLDWAcceptedText;


    @FindBy(xpath = "//*[@class='sp-payment-method']/descendant::span[text()='Primary Payment Method']/../../child::div//span[contains(@class,'cc-dropdown-selected')]/span[contains(text(),'************')]")
    private WebElement PrimaryCardDetails;

    @FindBy(xpath = "//*[@class='sp-payment-method']/descendant::span[text()='Secondary Payment Method']/../../child::div//span[contains(@class,'cc-dropdown-selected')]/span[contains(text(),'************')]")
    private WebElement SecondaryCardDetails;

    @FindBy(xpath = "//span[@class='coupon-value']")
    private WebElement CouponValue;

    @FindBy(xpath = "//p[@ng-if='vm.checkIATA()']")
    private WebElement IATAvalue;

    @FindBy(xpath = "//*[@id='vehicleTeaser']/div/div[1]/div/div[4]/div[3]/div[1]/div[4]/p")
    private WebElement FlightInfo;

    @FindBy(xpath = "(//div[@class='pull-left s-icon arrow-right'])[2]")
    private WebElement RentalOption;

    @FindBy(xpath = "(//div[@class='pull-left s-icon arrow-right'])[3]")
    private WebElement DiscountCodesArrow;

    @FindBy(xpath = "(//span[contains(text(),'Cover Roadside Issues (RSN)')])[1]")
    private WebElement RSNCoverageText;

    @FindBy(xpath = "//div[@ng-repeat='product in vm.confirmation.reservationSummary.rateSummary.selectedProducts']//span[contains(text(),'Extended Roadside Assistance (RSN)')]")
    private WebElement CARSNCoverageText;

    @FindBy(xpath = "(//span[contains(text(),'Hands-Free Navigation (GPS)')])[1]")
    private WebElement GPSCoverageText;

    @FindBy(xpath = "//div[@ng-repeat='product in vm.confirmation.reservationSummary.rateSummary.selectedProducts']//span[contains(text(),'GPS Navigation (GPS)')]")
    private WebElement CAGPSCoverageText;

    @FindBy(xpath = "(//span[@class='additional-text discount-summary-section'])[2]")
    private WebElement AWDCouponValue;

    @FindBy(xpath = "(//img[@title='Paypal'])[2]")
    private WebElement CardTypePaypal;

    @FindBy(xpath = "//p[@class='cancel-restxt-pad']")
    private WebElement ModifiedTextMsg;

    @FindBy(xpath = "//div[@class='confirmation-info-holder-text']")
    private WebElement EmailSentMsg;

    @FindBy(xpath = "//div[@class='info-msg-text']")
    private WebElement AWDMessageText;

    @FindBy(xpath = "(//div[@class='summary-location'])[1]")
    private WebElement PickUpLocValue;

    @FindBy(xpath = "(//div[@class='summary-location'])[2]")
    private WebElement ReturnLocValue;

    @FindBy(xpath = "(//div[@class='summary-time'])[1]")
    private WebElement PickupDateTime;

    @FindBy(xpath = "(//div[@class='summary-time'])[2] || (//div[@class='day-time-info'])[2] || (//div[contains(text(),'Return')]/following-sibling::div[@class='summary-time'])")
    private WebElement ReturnDateTime;

    @FindBy(xpath = "//h1[text()='Your car is reserved.']")
    private WebElement CarReservedTextMsg;

    @FindBy(xpath = "//div[@class='confirmation-info-holder-text']")
    private WebElement ConfirmationMessage;


    @FindBy(xpath = "//div[text()='Cover The Car (LDW)']/following-sibling::div//span[contains(text(),'Included')]")
    private WebElement ldwIncludedText;

    @FindBy(xpath = "//div[text()='Third Party Liability']/following-sibling::div//span[contains(text(),'Included')]")
    private WebElement thirdPartyIncludedText;

    @FindBy(xpath="//button[normalize-space()=PreCheck]")
    private WebElement preCheckButton;

    @FindBy(xpath="//div[contains(text(),'Estimated Total')]//ancestor::div[@class='header']//span[@class='total-amount pull-right']")
    private WebElement estimatedTotalAmount;

    @FindBy(xpath="//span[contains(.,'Base Rate')]//following-sibling::div[@class='pull-right ']")
    private WebElement baseRateAmount;

    @FindBy(xpath="//div[contains(text(),'Your Time & Place')]//span[contains(text(),'Modify')]")
    private WebElement modifyTime;

    @FindBy(xpath="//div[contains(text(),'Rate & Benefit Information')]//span[contains(text(),'Modify')]")
    private WebElement modifyRate;

    @FindBy(xpath="//div[contains(text(),'Rental Options')]//following-sibling::div//span[contains(text(),'Modify')]")
    private WebElement modifyRentalOption;


    @FindBy(xpath=" //strong[contains(text(),'AWD')]/parent::p")
    private WebElement awdCode;

    @FindBy(xpath="//div[contains(text(),'Your Car')]//span[contains(text(),'Modify')]")
    private  WebElement modifyCar;

    @FindBy(xpath="//div[@class='confirmation-info-holder-text']")
    private WebElement emailSentMessage;

    @FindBy(xpath="//span[contains(text(),'Equipment & Services')]/parent::div/following-sibling::div/span[@class='sub-total make-bold pull-right']")
    private WebElement equipmentServiceCharges;

    @FindBy(xpath="//span[contains(text(),'Protections & Coverages')]/parent::div/following-sibling::div/span[@class='sub-total make-bold pull-right']")
    private WebElement protectionCoverageCharges;

   @FindBy(xpath = "(//a[@class='accordion-toggle']//span[contains(.,'Rental Options')])[1]")
   private WebElement rentalOptionLink;

   @FindBy(xpath="//form[@id='smallBizYourInfoform']//button[@id='smallbiz_step1']")
   private WebElement smallBizFlyNextButton;

   @FindBy(xpath="//input[@name='companyName']")
   private WebElement smallBizFlyCompanyName;

   @FindBy(xpath = "//input[@name='address1']")
   private WebElement smallBizFlyAddress;

   @FindBy(xpath="//input[@id='city']")
   private  WebElement smallBizFlyCity;

   @FindBy(xpath="//Select[@id='state']")
   private WebElement smallBizFlyState;

   @FindBy(xpath = "//input[@id='zip']")
   private  WebElement smallBizFlyZip;

   @FindBy(xpath="//button[@id='smallbiz_step2']")
   private WebElement smallBizFlyEnrollButton;

   @FindBy(xpath="(//p[contains(text(),'Your Avis Worldwide Discount (AWD) number is:')]//following-sibling::p)[1]")
   private  WebElement smallBizFlyAwdNumber;

   @FindBy(xpath="//a[@href='/en/home.html']")
   private WebElement homePageLink;

    @FindBy(xpath = "//div[contains(@class,'avisred')]//span[contains(@class,'close-icon-black')]")
    private WebElement skipCounterCloseBtn;

    public Confirmation(WebDriver driver) {
        super(driver);
    }
   /* {
        setBaseRateAndEstimatedTotal();
    }*/

    {
        if (Configuration.DOMAIN.equals("CA")) {
            GPSCoverageText = CAGPSCoverageText;
            RSNCoverageText= CARSNCoverageText;
        }
    }

    public boolean isConfirmationNumberDisplayed() {
        confirmationNo=confirmationNumber.getText();
        confirmationNo=(confirmationNo.split(":"))[1].trim();
        log.info("Confirmation num :"+confirmationNo);
        return confirmationNumber.isDisplayed();
    }
    public boolean isCarReservedTextDisplayed() {
        return CarReservedTextMsg.isDisplayed();
    }

    public boolean isAWDMessageTextDisplayed() {
        return AWDMessageText.getText().contains("Your provided AWD number includes or discounts certain extras, and may include ");
    }


    public boolean isConfirmationNumberSame(String ConfirmationNum) {
        return confirmationNumber.getText().contains(ConfirmationNum);
    }

    public boolean isModifiedReservationTextDisplayed(String firstname) {
        return ModifiedTextMsg.getText().contains("Thank you"+" "+firstname+","+" "+"your reservation has been modified and your car is reserved.");
    }

    public boolean isModifiedReservationTextDisplayed() {
        return ModifiedTextMsg.getText().contains("your reservation has been modified and your car is reserved.");
    }

    public boolean isEmailSentTextDisplayed(String email) {
        return EmailSentMsg.getText().contains("A confirmation email has been sent to"+" "+email);
    }

    public boolean isEmailSentTextDisplayed() {
        return EmailSentMsg.getText().contains("A confirmation email has been sent to");
    }

    public boolean isAWDCouponMessageDisplayed() {
        helper.scrollToElement(ConfirmationAWDCouponValidation);
        threadSleep(TWO_SECONDS);
        return ConfirmationAWDCouponValidation.isDisplayed();
    }

    public boolean isPrimaryCardDetailsDisplayed() {
        helper.scrollToElement(PrimaryCardDetails);
        threadSleep(TWO_SECONDS);
        return PrimaryCardDetails.isDisplayed();
    }

    public boolean isSecondaryCardDetailsDisplayed() {
        return SecondaryCardDetails.isDisplayed();
    }

    public Confirmation cancelReservationWithConfirmationBox() {
        threadSleep(TWO_SECONDS);
        clickUsingJS(waitForVisibilityOfElement(cancelResCTA));
        threadSleep(TWO_SECONDS);
        clickUsingJS(waitForVisibilityOfElement(CancelConfirmationCheckBox));
        clickUsingJS(waitForVisibilityOfElement(confirmCancelResCTA));
        return this;
    }

    public Confirmation cancelReservation() {
        helper.scrollToElement(cancelResCTA);
        clickUsingJS(cancelResCTA);
        threadSleep(ONE_SECOND);
        clickOn(cancelReservationPopup);
        return this;
    }

    public boolean isExtrasAddedMessageDisplayed() {
        helper.scrollToElement(OneClickGPSAdded);
        threadSleep(TWO_SECONDS);
        return OneClickGPSAdded.getText().contains("Hands-Free Navigation (GPS) is successfully added to your reservation.");
    }

    public boolean isCouponCodeMessageDisplayed(String coupon) {
        helper.scrollToElement(CouponValue);
        threadSleep(TWO_SECONDS);
        System.out.println("Couponvalue :"+CouponValue.getText());
        return CouponValue.getText().contains(coupon);

    }

    public boolean isFlightInfoDisplayed(String flightName) {
        helper.scrollToElement(FlightInfo);
        threadSleep(TWO_SECONDS);
        System.out.println("FlightInfo :"+FlightInfo.getText());
        return FlightInfo.getText().contains(flightName);

    }

    public Confirmation ClickRentalOption() {
        RentalOption.click();
        return this;
    }

    public Homepage ClickAvisLogo() {
        avisLogo.click();
        driver.navigate().refresh();
        return new Homepage(driver);
    }

    public Confirmation ClickDiscountCodesArrow() {
        DiscountCodesArrow.click();
        return this;
    }

    public boolean verifyAWDCouponValueConfirmationPage() {
        System.out.println("AWD Coupon : "+AWDCouponValue.getText());
        helper.scrollToElement(AWDCouponValue);
        threadSleep(TWO_SECONDS);
        return AWDCouponValue.isDisplayed();
    }

    public boolean isIATAValueDisplayed(String IATA) {
        helper.scrollToElement(IATAvalue);
        threadSleep(TWO_SECONDS);
        System.out.println(IATAvalue.getText());
        return IATAvalue.getText().contains(IATA);
    }

    public boolean isConfirmationMessageDisplayed(){
        return ConfirmationMessage.getText().contains("A confirmation email has been sent");
    }

    public boolean isAwdConfirmationPageTextDisplayed(String coupon) {
        helper.scrollToElement(AWDConfirmationPage);
        threadSleep(TWO_SECONDS);
        return AWDConfirmationPage.getText().contains(coupon);
    }

    public boolean isCouponAppliedMessageDisplayed(String couponApplied) {
        waitForVisibilityOfElement(ConfirmationCouponValidation);
        String actualText = ConfirmationCouponValidation.getText();
        return actualText.contains("Coupon savings applied") || actualText.contains(couponApplied);
    }

    public boolean isSnowChainProductPresent() {
        return helper.isElementDisplayed(EquipmentAndServiceProductsInfo) && helper.isElementDisplayed(SnowChainProductInfo) && helper.isElementDisplayed(SnowChainProductSelectedStatus);
    }

    public ReservationWidget modifyTimeAndPlaceClick() {
        threadSleep(THREE_SECONDS);
        helper.scrollToElement(modifyTimeAndPlace);
        clickUsingJS(waitForVisibilityOfElement(modifyTimeAndPlace));
        return new ReservationWidget(driver);
    }

    public Confirmation closeGetFreeCouponPopup() {
        threadSleep(THREE_SECONDS);
        if (helper.isElementDisplayed(roktPlacementFrame)) {
            driver.switchTo().frame(roktPlacementFrame);
            driver.switchTo().frame(roktOfferFrame);
            helper.clickIfElementIsDisplayed(getFreeCouponPopup);
            driver.switchTo().defaultContent();
        }
        return this;
    }

    public Confirmation flyOutClose() {
        if(!Configuration.BRAND.equalsIgnoreCase("AVIS") && !Configuration.DOMAIN.equalsIgnoreCase("US")) {
            helper.clickIfElementIsDisplayed(flyoutClosebtn);
        }
        return this;
    }

    public Confirmation PopupClose() {
        helper.clickIfElementIsDisplayed(OfferPopup);
        return this;
    }

    public boolean verifyCurrencyOnConfirmationPage(String currency) {
        return CurrencyConfirmationPage.getText().contains(currency);
    }

    public boolean verifyLWDOnConfirmationPage() {
        helper.scrollToElement(LDWAcceptedText);
        threadSleep(TWO_SECONDS);
        System.out.println("LWD verification on Confirmation page");
        return LDWAcceptedText.isDisplayed();
    }

    public boolean verifyRSNCoverageOnConfirmationPage() {
        System.out.println("RSN : "+RSNCoverageText.getText());
        return RSNCoverageText.isDisplayed();
    }

    public boolean verifyGPSCoverageOnConfirmationPage() {
        System.out.println("GPS : "+GPSCoverageText.getText());
        return GPSCoverageText.isDisplayed();
    }

    public boolean verifyRentalOptionsText(String msg) {
        waitForVisibilityOfElement(rentalOptionsRSN);
        return rentalOptionsRSN.getText().contains(msg);
    }

    public boolean verifypersonalInfo(String firstName, String email) {
        helper.scrollToElement(NameDetails);
        threadSleep(TWO_SECONDS);
        System.out.println("Confirm EMAIL---->"+EmailConfirmationPage.getText());
        boolean verifyFirstName = NameDetails.getText().contains(firstName);
        boolean verifyEmail = EmailConfirmationPage.getText().contains(email);
        return verifyEmail && verifyFirstName;
    }

    public Confirmation keyDropMessageValidation() {
        waitForVisibilityOfElement(keyDropInfo);
        String actualMessage = keyDropInfo.getText();
        assertEquals(actualMessage, KEY_DROP_LOCATION_MESSAGE);
        return this;
    }

    public boolean isCardTypePaypalDisplayed() {
        helper.scrollToElement(CardTypePaypal);
        threadSleep(TWO_SECONDS);
        return CardTypePaypal.isDisplayed();
    }

    public String GetConfirmationNumber() {
        System.out.println("Confirmation num get text :" + confirmationNumber.getText());
        String ConfNum = confirmationNumber.getText();
        System.out.println("Confirmation num :"+ConfNum.substring(14));
        return ConfNum.substring(14);
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

    @Override
    public void isOnPage() {
        log.info("Verify Confirmation Page");
        waitForVisibilityOfElement(confirmationNumber);
    }

    public boolean isLdwIncluded()
    {
        boolean isIncluded=false;
        try
        {
            helper.scrollToElement(ldwIncludedText);
            isIncluded=ldwIncludedText.isDisplayed();
            log.info("LDW is Included");
        }
        catch (Exception e)
        {
            isIncluded=false;
            log.error("Included Message not displayed ");
        }
        return  isIncluded;
    }
    public boolean isthirdPartyIncluded()
    {
        boolean isIncluded=false;
        try
        {
            helper.scrollToElement(thirdPartyIncludedText);
            isIncluded=thirdPartyIncludedText.isDisplayed();
            log.info("Third Party is Included");
        }
        catch (Exception e)
        {
            isIncluded=false;
            log.error("Included Message not displayed ");
        }
        return  isIncluded;
    }
    public boolean isPreCheckButtonVisible()
    {
        boolean isVisible=false;
        try
        {
            helper.scrollToElement(preCheckButton);
            isVisible=preCheckButton.isDisplayed();
            log.info("Pre check button is visible");
        }
        catch (Exception e)
        {
            isVisible=false;
            log.error("Pre check button is not visible ");
        }
        return  isVisible;
    }
    public void setBaseRateAndEstimatedTotal()
    {
        waitForVisibilityOfElement(baseRateAmount);
        fareCharges.put("confirmation-BaseRate",Double.parseDouble(baseRateAmount.getText().replace("$","").replace("US","")));
        waitForVisibilityOfElement(estimatedTotalAmount);
        fareCharges.put("confirmation-TotalEstimatedValue",Double.parseDouble(estimatedTotalAmount.getText().replace("$","")));
    }
    public boolean isModifyTimeOptionVisible()
    {
        boolean isVisible=false;
        try
        {
            helper.scrollToElement(modifyTime);
            isVisible=modifyTime.isDisplayed();
            log.info("Modify option visible for Time And Place  Section ");
        }
        catch (Exception e)
        {
            isVisible=false;
            log.error("Modify option not visible for Time And Place  Section ");
        }
        return  isVisible;
    }
    public boolean isModifyRateOptionVisible()
    {
        boolean isVisible=false;
        try
        {
            helper.scrollToElement(modifyRate);
            isVisible=modifyRate.isDisplayed();
            log.info("Modify option visible for Rate Section ");
        }
        catch (Exception e)
        {
            isVisible=false;
            log.error("Modify option not visible for Rental Section ");
        }
        return  isVisible;
    }
    public boolean isModifyRentalOptionVisible()
    {
        boolean isVisible=false;
        try
        {
            helper.scrollToElement(modifyRentalOption);
            isVisible=modifyRentalOption.isDisplayed();
            log.info("Modify option visible for Rental Section ");
        }
        catch (Exception e)
        {
            isVisible=false;
            log.error("Modify option not visible for  Rental");
        }
        return  isVisible;
    }
    public boolean isAWDCodVisibleUnderRateSection(String awdCodevVlue)
    {
        helper.scrollToElement(awdCode);
        log.info("-------------->"+awdCode.getText());
        if(awdCode.getText().contains(awdCodevVlue))
        {
            log.info("AWD Code Visible in Rate Section ");
            return  true;
        }
        else
        {
            log.error("AWD Code not Visible in Rate Section ");
            return  false;
        }
    }
    public boolean isModifyMVCOptionVisible()
    {
        boolean isVisible=false;
        try
        {
            helper.scrollToElement(modifyCar);
            isVisible=modifyCar.isDisplayed();
            log.info("Modify option visible for MVC ");
        }
        catch (Exception e)
        {
            isVisible=false;
            log.error("Modify option not visible for  MVC");
        }
        return  isVisible;
    }
    public boolean isEmailSentMessageVisible()
    {
        boolean isVisible=false;
        try
        {
            helper.scrollToElement(emailSentMessage);
            isVisible=emailSentMessage.isDisplayed();
            log.info("Email Sent Text is visible");
        }
        catch (Exception e)
        {
            isVisible=false;
            log.error("Email Sent Text is not visible ");
        }
        return  isVisible;
    }

    public boolean isProtectionCoverageChargeMatch()
    {
        helper.scrollToElement(rentalOptionLink);
        clickUsingJS(rentalOptionLink);
       // rentalOptionLink.click();
       // helper.scrollToElement(protectionCoverageCharges);
        log.info("Discount Charges=================>"+protectionCoverageCharges.getText());
        Double charges=Double.parseDouble(protectionCoverageCharges.getText().replace("$",""));
        if(charges.equals(additionalCharges.get("ProtectionCoverageCharges")))
        {
            log.info("Protection charges match with protection Charges on Extra Page");
            return  true;
        }
        else
        {
            log.error("Protection charges not match with protection Charges on Extra Page");
            return  false;

        }
    }
    public boolean isEquipmentServiceChargeMatch()
    {
       // helper.scrollToElement(equipmentServiceCharges);
       // helper.scrollToElement(rentalOptionLink);
       // clickUsingJS(rentalOptionLink);
      //  rentalOptionLink.click();
        log.info("Equipemnet Charges============>"+equipmentServiceCharges.getText().replace("$",""));
        Double charges=Double.parseDouble(equipmentServiceCharges.getText().replace("$",""));
        if(charges.equals(additionalCharges.get("ProtectionCoverageCharges")))
        {
            log.info("Equipment Service charges match with Charges on Extra Page");
            return  true;
        }
        else
        {
            log.error("Equipment Service charges not match with Charges on Extra Page");
            return  false;

        }
    }
    public boolean smallBizFlyEnroll()
    {
        String companyName="";
        try {
            helper.scrollToElement(smallBizFlyNextButton);
            threadSleep(THREE_SECONDS);
            smallBizFlyNextButton.click();
            companyName = "Test" + getUniqueName();
            smallBizFlyCompanyName.sendKeys(companyName);
            setBizFlyCompanyAddress();
            threadSleep(THREE_SECONDS);
            smallBizFlyEnrollButton.click();
            log.info("SmallBiz Fly option form Submitted ");
            return true;
        }
        catch(Exception e)
        {
            log.error("SmallBiz Fly option form not  Submitted");
            return false;

        }
    }
    public void  setBizFlyCompanyAddress()
    {
            smallBizFlyAddress.sendKeys("3156 Virgil Street");
            smallBizFlyCity.sendKeys("Dallas");
            helper.selectValueFromDropDown(smallBizFlyState, "Texas");
            threadSleep(THREE_SECONDS);
            smallBizFlyZip.sendKeys("75201");


    }

    public String getUniqueName()
    {
        String uniqueName="";
        Date date= new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        uniqueName=dateFormat.format(date).replaceAll("-","").replaceAll("\\W","");
        return uniqueName;

    }
    public boolean isSmallBizFlyAWDNumberVisible()
    {
        if(smallBizFlyEnroll())
        {
            threadSleep(FIVE_SECONDS);
            waitForVisibilityOfElement(smallBizFlyAwdNumber);
            if(smallBizFlyAwdNumber.isDisplayed())
            {
                log.info("Awd Number is generated=====>"+smallBizFlyAwdNumber.getText());
                return  true;
            }
            else {
                log.error("Awd Number is not generated=====>");
                return  false;
            }
        }
        else {
            log.error("SmallBiz Form is not submitted");
            return  false;
        }
    }

    public Homepage clickOnAvisLogo()
    {
        helper.scrollToElement(homePageLink);
        homePageLink.click();
        return new Homepage(driver);
    }

    public Confirmation skipCounterClose() {
        if(Configuration.BRAND.equalsIgnoreCase("AVIS") && Configuration.DOMAIN.equalsIgnoreCase("US")) {
            helper.clickIfElementIsDisplayed(skipCounterCloseBtn);
        }
        return this;
    }

}
