package com.avis.qa.pages;

import com.avis.qa.components.ReservationWidget;
import com.avis.qa.core.AbstractBasePage;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.avis.qa.constants.TextComparison.KEY_DROP_LOCATION_MESSAGE;
import static com.avis.qa.utilities.CommonUtils.*;
import static org.testng.Assert.assertEquals;
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

    @FindBy(xpath = "(//p[contains(text(),'AWD savings applied')])[2]")
    private WebElement ConfirmationAWDCouponValidation;

    @FindBy(xpath = "//button[@aria-label='Close']")
    private WebElement getFreeCouponPopup;

    @FindBy(xpath = "//iframe[@title = 'Rokt placement']")
    private WebElement roktPlacementFrame;

    @FindBy(xpath = "//iframe[@title = 'Rokt offer']")
    private WebElement roktOfferFrame;

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

    @FindBy(xpath = "(//h3)[1]")
    private WebElement NameDetails;

    @FindBy(xpath = "((//div[@class='col-lg-4 col-sm-6 col-xs-12 pad-zero'])[1]//p)[1]")
    private WebElement EmailConfirmationPage;

    @FindBy(xpath = "//div[contains(@class,'info-key-drop-text')]/p")
    private WebElement keyDropInfo;

    @FindBy(xpath = "//div[text()='Loss Damage Waiver (LDW)']/following-sibling::div/span[contains(text(),'Accepted')]")
    private WebElement LDWAcceptedText;

    @FindBy(xpath = "//div[text()='Cover The Car (LDW)']/following-sibling::div/span[contains(text(),'Accepted')]")
    private WebElement ProtectionAndCoverageLDWAcceptedText;


    @FindBy(xpath = "(//strong[contains(text(),'Card Number:')])[1] | span[contains(text(),'************')]")
    private WebElement PrimaryCardDetails;

    @FindBy(xpath = "(//strong[contains(text(),'Card Number:')])[2] | span[contains(text(),'************')]")
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

    @FindBy(xpath = "(//span[contains(text(),'Hands-Free Navigation (GPS)')])[1]")
    private WebElement GPSCoverageText;

    @FindBy(xpath = "(//span[@class='additional-text discount-summary-section'])[2]")
    private WebElement AWDCouponValue;

    @FindBy(xpath = "(//img[@title='Paypal'])[2]")
    private WebElement CardTypePaypal;

    @FindBy(xpath = "//p[@class='cancel-restxt-pad']")
    private WebElement ModifiedTextMsg;

    //Thank you Abhishek, your reservation has been modified and your car is reserved.

    @FindBy(xpath = "//div[@class='confirmation-info-holder-text']")
    private WebElement EmailSentMsg;

    //A confirmation email has been sent to abhi@gmail.com

    @FindBy(xpath = "//div[@class='info-msg-text']")
    private WebElement AWDMessageText;

    //Your provided AWD number includes or discounts certain extras, and may include

    @FindBy(xpath = "(//div[@class='summary-location'])[1]")
    private WebElement PickUpLocValue;

    @FindBy(xpath = "(//div[@class='summary-location'])[2]")
    private WebElement ReturnLocValue;

    @FindBy(xpath = "(//div[@class='summary-time'])[1]")
    private WebElement PickupDateTime;

    @FindBy(xpath = "(//div[@class='summary-time'])[2]")
    private WebElement ReturnDateTime;

    @FindBy(xpath = "//h1[text()='Your car is reserved.']")
    private WebElement CarReservedTextMsg;



    public Confirmation(WebDriver driver) {
        super(driver);
    }

    public boolean isConfirmationNumberDisplayed() {
        System.out.println("Confirmation num :"+confirmationNumber.getText());
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
        System.out.println("AWD coupon applied verification on Confirmation page");
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
        cancelReservationPopup.click();
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
        helper.clickIfElementIsDisplayed(flyoutClosebtn);
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

    public boolean verifyRentalOptionsText() {
        return rentalOptionsRSN.getText().contains("Cover Roadside Issues (RSN)");
    }

    public boolean verifypersonalInfo(String firstName, String email) {
        helper.scrollToElement(NameDetails);
        threadSleep(TWO_SECONDS);
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
}
