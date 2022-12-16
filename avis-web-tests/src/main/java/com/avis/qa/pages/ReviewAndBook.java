package com.avis.qa.pages;

import com.avis.qa.core.AbstractBasePage;
import com.avis.qa.core.Configuration;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import static com.avis.qa.constants.AvisConstants.CARDEXPIRATIONDATE;
import static com.avis.qa.utilities.CommonUtils.*;
import static org.testng.Assert.assertEquals;


/**
 * This class contains all the elements and actions performed on HomePage
 *
 * @author ikumar
 */
@Log4j2
public class ReviewAndBook extends AbstractBasePage {

    @FindBy(xpath = "//*[contains(@id,'firstname')]")
    private WebElement firstName;

    @FindBy(id = "lastname")
    private WebElement lastName;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "phone")
    private WebElement phoneField;

    @FindBy(id = "comboPhone")
    private WebElement comboPhone;

    @FindBy(xpath = "//label[contains(@for,'termsCheck')]/preceding-sibling::input")
    private WebElement termsCheckInput;

    @FindBy(xpath = "//label[contains(@for,'termsCheck')]")
    private WebElement termsCheck;

    @FindBy(xpath = "//*[contains(@id,'submit_button')]")
    private WebElement step4Submit;

    @FindBy(css = "button[type='reset']")
    private WebElement continueReservationButton;

    @FindBy(xpath = "//*[contains(@name,'expmonthselectedItem')]")
    private WebElement month;

    @FindBy(xpath = "//input[@name='expirydate']")
    private WebElement expiryDate;


    @FindBy(xpath = "//*[contains(@name,'selectedExpYear')]")
    private WebElement year;

    @FindBy(xpath = "//input[@id='cardnumber']")
    private WebElement cardNumber;

    @FindBy(xpath = "(//input[@id='cardnumber'])[2]")
    private WebElement splitPageCardNumber;

    @FindBy(xpath = "//input[contains(@id,'securitycode')]")
    private WebElement step4_CVV;

    @FindBy(xpath = "(//input[contains(@id,'securitycode')])[2]")
    private WebElement step4_CVV2;

    @FindBy(xpath = "//*[contains(@for,'creditcard')]")
    private WebElement creditCardCheckBox;

    @FindBy(xpath = "//label[@for='ccCard']")
    private WebElement Budget_CreditCardRadioButton;


    @FindBy(xpath = "//*[contains(@id,'address1')]")
    private WebElement address1;

    @FindBy(xpath = "//*[@id='States']")
    private WebElement state;

    @FindBy(xpath = "(//*[contains(@id,'city')])|(//*[contains(@name,'city')])")
    private WebElement city;

    @FindBy(xpath = "//*[contains(@id,'zip')]")
    private WebElement zip;

    @FindBy(xpath = "//div[contains(@ng-repeat,'error in messageList')]")
    private WebElement DummyCreditCardErrorMessage_Paylesscar;

    @FindBy(xpath = "//button[@id='res-review-keepMod']")
    private WebElement keepModifications;

    @FindBy(id = "expirydate")
    private WebElement creditCardExpiryDateField;

    @FindBy(xpath = "//button[@id='res-review-keepMod'] | //button[@type='submit' and contains(.,'Review Modification')]|//button[@type='submit' and contains(.,'Revoir les modifications')]|//button[@type='submit' and contains(.,'Revisar modificaciones')]")
    private WebElement reviewModification;

    @FindBy(xpath = "//*[contains(@name,'flightnumberMob')]")
    private WebElement step4_flightNumber;

    @FindBy(xpath = "//*[contains(@name,'airlineobj')]")
    private WebElement step4_flightInfo;

    @FindBy(xpath = "//*[contains(@for,'profile-msg')]")
    private WebElement phoneCheckbox;

    @FindBy(id = "iatanumber")
    private WebElement IataTextFiled;

    @FindBy(id = "selectedCountry")
    private WebElement selectedCountry;

    @FindBy(xpath = "//span[text()='Discount Code Savings']")
    private WebElement DiscountCodeSaving;

    @FindBy(xpath = "//a[contains(text(),'Save time and add your card now.')]")
    private WebElement SavetimeAddCreditCardLink;

    @FindBy(xpath = "//h4[text()='Split Payment Information']")
    private WebElement SplitPaymentInfoText;

    @FindBy(xpath = "//input[@id='SBSplitAmount']")
    private WebElement splitToggleButton;

    @FindBy(xpath = "//span[contains(text(),'Add Credit Card')]")
    private WebElement AddCreditcardButton1;

    @FindBy(xpath = "(//span[contains(text(),'Add Credit Card')])[2]")
    private WebElement AddCreditcardButton2;

    @FindBy(xpath = "//span[contains(text(),'Use same address as primary payment')]")
    private WebElement sameAddressAsPrimaryCheckbox;

    @FindBy(xpath = "(//button[text()='Save'])[1]")
    private WebElement SaveButton;

    @FindBy(xpath = "//input[@id='primaryAmount']")
    private WebElement primaryAmountField;

    @FindBy(xpath = "//input[@id='secondaryAmount']")
    private WebElement secondaryAmountField;

    @FindBy(xpath = "//strong[text()='Primary']")
    private WebElement PrimaryCardText;

    @FindBy(xpath = "//strong[text()='Secondary']")
    private WebElement SecondaryCardText;

    @FindBy(xpath = "//*[contains(@for,'creditcard')] ")
    private WebElement AddCreditCardCheckbox;

    @FindBy(xpath = "//div[@data-funding-source='paypal'] | //span[@class='c-icon paypal-logo']")
    private WebElement PaypalButton;

    @FindBy(xpath = "//label[@for='payPal']")
    private WebElement Budget_PaypalRadioButton;

    @FindBy(xpath = "//img[@id='OffAmazonPaymentsWidgets0'] | //span[@class='c-icon amazonpay-logo']")
    private WebElement AmazonpayButton;

    @FindBy(xpath = "//span[text()='Paying With ']")
    private WebElement PayingWithText;

    @FindBy(xpath = "//img[@title='Paypal']")
    private WebElement PayPalImage;

    @FindBy(xpath = "//iFrame[@title='PayPal']")
    private WebElement iFramePayPal;


    @FindBy(xpath = "//span[text()='Estimated Total']/following-sibling::span/child::span/child::span[2]")
    private WebElement EstimatedTotalText;

    @FindBy(xpath = "//span[contains(text(),'You have the option to pay with your Visa or MasterCard in your native currency. If you choose to pay with different card type your charges will be posted in USD ($')] | //span[contains(text(),'native currency'] | //span[@class='mainErrorText']")
    private WebElement NativeCurrencyPayInfoMsg;

    @FindBy(xpath = "//button[text()='Review Modifications']")
    private WebElement ReviewModificationsButton;

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

    @FindBy(xpath = "//span[@id='errTermsCheck']")
    private WebElement TermsAndConditionCheckBoxErrorMsg;

    @FindBy(xpath = "//span[text()='Please enter valid CVV code.']")
    private WebElement CvvInvalidErrorMsg;

    @FindBy(xpath = "//span[text()=' should contain atleast six characters.']")
    private WebElement EmailInvalidErrorMsg;

    @FindBy(xpath = "//span[text()='Please provide a valid ']")
    private WebElement PhoneNumInvalidErrorMsg;

    @FindBy(xpath = "//span[text()='The credit card number you have entered is not valid. Please check to ensure that you have entered the correct credit card type and number.']")
    private WebElement CreditCardInvalidErrorMsg;

    @FindBy(xpath = "//span[text()=' cannot contain numeric or special characters. Please remove and try again.']")
    private WebElement FirstNameInvalidErrorMsg;

    @FindBy(xpath = "//span[text()=' cannot contain special characters. Please remove and try again.']")
    private WebElement LastNamenvalidErrorMsg;







    private String selectedCountryText;


    public ReviewAndBook(WebDriver driver) {
        super(driver);
    }


    /**
     * To click continue Reservation pop-up on Review and Book Page
     */
    public ReviewAndBook clickContinueReservationButton() {
        helper.clickIfElementIsDisplayed(continueReservationButton);
        return this;
    }

    /**
     * @param fname To enter first name on review an book page
     */
    public ReviewAndBook firstname(String fname) {
        firstName.sendKeys(fname);
        return this;
    }

    /**
     * To enter first name on review an book page
     */
    public ReviewAndBook lastname(String lname) {
        lastName.sendKeys(lname);
        return this;
    }

    /**
     * To enter email on review an book page
     */
    public ReviewAndBook email(String email) {
        String emailID = email + new Random().nextInt() + "@gmail.com";
        if (emailID.contains("-")) {
            emailID = emailID.replace("-", "");
        }
        emailField.sendKeys(emailID);
        return this;
    }

    public ReviewAndBook enterEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
        return this;
    }

    public ReviewAndBook enterPhnNo(String PhnNo) {
        phoneField.clear();
        phoneField.sendKeys(PhnNo);
        return this;
    }

    /**
     * To enter email on review an book page
     *
     * @return
     */
    public String emailReturnType(String email) {
        String emailID = email + new Random().nextInt() + "@gmail.com";
        if (emailID.contains("-")) {
            emailID = emailID.replace("-", "");
        }
        emailField.sendKeys(emailID);
        return emailID;
    }

    /**
     * To enter Phone number
     */
    public ReviewAndBook phone(String pno) {
        if (helper.isElementDisplayed(phoneField)) {
            phoneField.sendKeys(pno);
        } else {
            comboPhone.sendKeys(pno);
        }
        return this;
    }

    public ReviewAndBook smsOptInCheckbox() {
        waitForVisibilityOfElement(phoneCheckbox);
        if (!phoneCheckbox.isSelected())
            clickUsingJS(phoneCheckbox);
        return this;
    }

    public ReviewAndBook iataNumber(String iataNo) {
        IataTextFiled.sendKeys(iataNo);
        return this;

    }

    /* Spliting Estimated amount value*/
    String value;
    public ReviewAndBook getEstimatedTotalvalue() {
        value = EstimatedTotalText.getText();
        return this;
}

    public ReviewAndBook enterPrimaryAndSecondaryAmount() {
        double  CreditCardAmount2 = 5.00;
        double  CreditCardAmount1;
        double  EstimatedTotalAmount;
        String EstimatedTotal = String.valueOf(value);
        //String EstimatedTotal =EstimatedTotalText.getText();

        try{
            EstimatedTotalAmount= Double.parseDouble(EstimatedTotal.replaceAll(",", ""));
            BigDecimal b1 = new BigDecimal(Double.toString(EstimatedTotalAmount));
            System.out.println("EstimatedTotalAmount2 :"+b1);
            BigDecimal b2 = new BigDecimal(Double.toString(CreditCardAmount2));
            System.out.println("EstimatedTotalAmount3 :"+b2);
            CreditCardAmount1= b1.subtract(b2).doubleValue();
            System.out.println("EstimatedTotalAmount4 :"+CreditCardAmount1);
        }
        catch(Exception e)
        {
            EstimatedTotalAmount = Double.parseDouble(EstimatedTotal);
            //e.printStackTrace();
            BigDecimal b1 = new BigDecimal(Double.toString(EstimatedTotalAmount));
            System.out.println("EstimatedTotalAmount catch block :"+b1);
            BigDecimal b2 = new BigDecimal(Double.toString(CreditCardAmount2));
            System.out.println("EstimatedTotalAmount B2 Catch block :"+b2);
            CreditCardAmount1 = b1.subtract(b2).doubleValue();
            System.out.println("CreditCardAmount1 catch block  :"+CreditCardAmount1);
        }

        waitForVisibilityOfElement(primaryAmountField);
        primaryAmountField.click();
        primaryAmountField.clear();
        //primaryAmountField.sendKeys(String.valueOf(CreditCardAmount1));
        primaryAmountField.sendKeys(Double.toString(CreditCardAmount1));
        System.out.println("CreditCardAmount1 sendKeys  :"+CreditCardAmount1);
        return this;
    }

    /**
     * To check T&C check box
     */
    public ReviewAndBook checkTermsAndConditions() {
        //helper.scrollToElement(termsCheckInput);
        waitForVisibilityOfElement(termsCheck);
        clickUsingJS(termsCheck);
        return this;
    }

    /*
     * @param cardNo To enter credit card no
     */

    public ReviewAndBook ClickAddCreditCardLink() {
        SavetimeAddCreditCardLink.click();
        return this;
    }

    public ReviewAndBook clickToggleSplitCreditCardButton() {
        waitForVisibilityOfElement(SplitPaymentInfoText);
        splitToggleButton.click();
        return this;
    }

    public ReviewAndBook clickAddCreditCardButton1() {
        waitForVisibilityOfElement(AddCreditcardButton1);
        threadSleep(TWO_SECONDS);
        AddCreditcardButton1.click();
        threadSleep(TWO_SECONDS);
        return this;
    }
/*
    public ReviewAndBook clickAddCreditCardButton2() {
        waitForVisibilityOfElement(AddCreditcardButton2);
        threadSleep(ONE_SECOND);
        AddCreditcardButton2.click();
        return this;
    }*/

    public ReviewAndBook enterCardNumber(String cardNo) {
        waitForVisibilityOfElement(cardNumber);
        cardNumber.click();
        cardNumber.sendKeys(cardNo);
        return this;
        //CF.tabOut("cardNumber");
    }

    public ReviewAndBook enterCardNumberSplitPage(String cardNo) {
        waitForVisibilityOfElement(splitPageCardNumber);
        splitPageCardNumber.click();
        splitPageCardNumber.sendKeys(cardNo);
        return this;
    }

    /**
     * To select expiry date
     */
    public ReviewAndBook selectExpiryDateAndYear() {
        month.click();
        helper.selectValueFromDropDown(month,5);
        year.click();
        helper.selectValueFromDropDown(year,5);
        //creditCardExpiryDateField.sendKeys("1129");
        //helper.selectValueFromDropDown(month,5);
        //helper.selectValueFromDropDown(year,5);
        return this;
    }

    public ReviewAndBook EnterExpiryDateAndYear() {
        expiryDate.click();
        expiryDate.sendKeys(CARDEXPIRATIONDATE);
        return this;
    }

    /**
     * @param secCode to enter CVV code
     */
    public ReviewAndBook enterSecurityCode(String secCode) {
        waitForVisibilityOfElement(step4_CVV);
        step4_CVV.click();
        step4_CVV.sendKeys(secCode);
        return this;
    }

    public ReviewAndBook enterSecurityCodeProfileUser(String secCode) {
        waitForVisibilityOfElement(step4_CVV2);
        step4_CVV2.click();
        step4_CVV2.sendKeys(secCode);
        return this;
    }

    public ReviewAndBook step4_CreditCardCheckBox() {
        if(Configuration.BRAND.equalsIgnoreCase("Avis")) {
            if (helper.isElementDisplayed(creditCardCheckBox))
                creditCardCheckBox.click();
        }
        if(Configuration.BRAND.equalsIgnoreCase("Budget"))
        {
            helper.waitUntilClickabilityOfElement(Budget_CreditCardRadioButton);
            helper.clickIfElementIsDisplayed(Budget_CreditCardRadioButton);
        }
        return this;
    }

    public ReviewAndBook step4_AddCreditCardCheckBox() {
         //   helper.scrollToElement(AddCreditCardCheckbox);
        AddCreditCardCheckbox.click();
        threadSleep(THREE_SECONDS);
        threadSleep(THREE_SECONDS);
        return this;

    }

    public ReviewAndBook enterAddress() {
        address1.sendKeys("701 West St");
        city.sendKeys("San Antonio");
        helper.selectValueFromDropDown(state, 47);
        zip.sendKeys("99022");
        return this;
    }

    public ReviewAndBook enterAddressInboundSpecific(String country) {
        if (country.equalsIgnoreCase("Canada")) {
            address1.sendKeys("701 West St");
            city.sendKeys("Toronto");
            helper.selectValueFromDropDown(state, "Ontario");
            zip.sendKeys("M5A4R5");
        }
        if (country.equals("U S A")) {
            address1.sendKeys("701 West St");
            city.sendKeys("San Antonio");
            helper.selectValueFromDropDown(state, 47);
            zip.sendKeys("99022");
        }
        return this;
    }

    public ReviewAndBook clickSaveButton() {
        waitForVisibilityOfElement(SaveButton);
        SaveButton.click();
        return this;
    }

    public ReviewAndBook clickSameAsPrimaryCheckboxButton() {
        waitForVisibilityOfElement(sameAddressAsPrimaryCheckbox);
        sameAddressAsPrimaryCheckbox.click();
        return this;
    }

    public Confirmation keepModification() {
        waitForVisibilityOfElement(keepModifications);
        clickUsingJS(keepModifications);
        return new Confirmation(driver);
    }

    /**
     * To enter flight Info
     */
    public ReviewAndBook flightInfo(String flightname) {
        waitForVisibilityOfElement(step4_flightInfo);
        helper.selectValueFromDropDown(step4_flightInfo, flightname);
        return this;
    }

    public ReviewAndBook SelectflightInfo(int i) {
        waitForVisibilityOfElement(step4_flightInfo);
        helper.selectValueFromDropDown(step4_flightInfo, i);
        return this;
    }

    /**
     * To enter flight number
     */
    public ReviewAndBook enterflightNumber(String flightNumber) {
        waitForVisibilityOfElement(step4_flightNumber).sendKeys(flightNumber);
        return this;
    }

    /**
     * step 4 submit
     *
     * @return
     */
    public ReviewAndBook step4Submit() {
        helper.scrollToElement(step4Submit);
        clickUsingJS(step4Submit);
        return this;
    }
    public ReviewAndBook ReserveButton() {
        waitForVisibilityOfElement(PayingWithText);
        helper.scrollToElement(step4Submit);
        clickUsingJS(step4Submit);
        return this;
    }


    public ReviewAndBook reviewModifications() {
        threadSleep(TWO_SECONDS);
        clickUsingJS(reviewModification);
        return this;
    }

    public ReviewAndBook setSelectedCountryText() {
        selectedCountryText = selectedCountry.getText();
        return this;
    }

    public PayPalPage handlePaypal()
    {
        waitForVisibilityOfElement(iFramePayPal);
        driver.switchTo().frame(0);
        System.out.println("IFramePaypal switched");
        threadSleep(TWO_SECONDS);
        helper.waitUntilClickabilityOfElement(PaypalButton);
        clickUsingJS(PaypalButton);
        driver.switchTo().defaultContent();
        return new PayPalPage(driver);
    }

    public PayPalPage clickPaypalButton() {

        if(Configuration.BRAND.equalsIgnoreCase("Avis")) {
            handlePaypal();
        }
        else if(Configuration.BRAND.equalsIgnoreCase("Budget"))
        {
            helper.waitUntilClickabilityOfElement(Budget_PaypalRadioButton);
            clickUsingJS(Budget_PaypalRadioButton);
            handlePaypal();
        }
            return new PayPalPage(driver);
        }


//    public PayPalPage clickPaypalButton() {
//        if(Configuration.BRAND=="Avis")
//        {
//            handlePaypal();
//        }
//
//        else if(Configuration.BRAND=="Budget")
//        {
//            waitForVisibilityOfElement(Budget_PaypalRadioButton);
//            Budget_PaypalRadioButton.click();
//            handlePaypal();
//        }
//
//        return new PayPalPage(driver);
//    }


    public AmazonPayPage clickAmazonPayButton() {
        zip.click();
        waitForVisibilityOfElement(iFramePayPal);
       // driver.switchTo().frame(0);
      //  System.out.println("IFramePaypal switched");
        threadSleep(TWO_SECONDS);
        helper.waitUntilClickabilityOfElement(AmazonpayButton);
        clickUsingJS(AmazonpayButton);
        threadSleep(TWO_SECONDS);
        //driver.switchTo().defaultContent();
        return new AmazonPayPage(driver);
    }


    public Boolean verifySelectedCountryText(String country) {
        return selectedCountryText.contains(country);
    }

    public boolean cvvCCOValidation() {
        return helper.isElementDisplayed(step4_CVV);
    }

    public String getDummyCreditCardErrorMessage() {
        return DummyCreditCardErrorMessage_Paylesscar.getText();
    }

    public boolean isDiscountCodeSavingtextDisplayed() {
        return DiscountCodeSaving.isDisplayed();
    }

    public boolean isNativeCurrencyMsgtextDisplayed() {
        return NativeCurrencyPayInfoMsg.isDisplayed();
    }

    public boolean isPrimaryCardtextDisplayed() { return PrimaryCardText.isDisplayed(); }

    public boolean isTncErrorMsgtextDisplayed() { return TermsAndConditionCheckBoxErrorMsg.isDisplayed(); }

    public boolean isSecondaryCardtextDisplayed() {
        return SecondaryCardText.isDisplayed();
    }

    public boolean isFlightInfoDisplayed() {
        return step4_flightInfo.isDisplayed();
    }

    public boolean isPayPalAndAmazonPayDisplayed() {
        if(AmazonpayButton.isDisplayed() && PaypalButton.isDisplayed());
        return true;
    }

    public boolean isPaypalImageDisplayed() {
        waitForVisibilityOfElement(PayPalImage);
        return PayPalImage.isDisplayed();
    }

    public boolean isEmailInvalidErrorMsgDisplayed() {
        return EmailInvalidErrorMsg.isDisplayed();
    }

    public boolean isPhonenumInvalidErrorMsgDisplayed() {
        return PhoneNumInvalidErrorMsg.isDisplayed();
    }

    public boolean isCVVInvalidErrorMsgDisplayed() {
        return CvvInvalidErrorMsg.isDisplayed();
    }

    public boolean isCreditCardInvalidErrorMsgDisplayed() {
        return CreditCardInvalidErrorMsg.isDisplayed();
    }

    public boolean isFnameInvalidErrorMsgDisplayed() {
        return FirstNameInvalidErrorMsg.isDisplayed();
    }

    public boolean isLnameInvalidErrorMsgDisplayed() {
        return LastNamenvalidErrorMsg.isDisplayed();
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
        log.info("Verify Review And Book Page");
        waitForVisibilityOfElement(firstName);
    }
}
