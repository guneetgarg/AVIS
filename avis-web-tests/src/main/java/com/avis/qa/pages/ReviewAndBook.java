package com.avis.qa.pages;

import com.avis.qa.core.AbstractBasePage;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.math.BigDecimal;
import java.util.Random;

import static com.avis.qa.utilities.CommonUtils.*;


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

    @FindBy(xpath = "//*[contains(@name,'selectedExpYear')]")
    private WebElement year;

    @FindBy(xpath = "(//input[@id='cardnumber'])[2]")
    private WebElement cardNumber;

    @FindBy(xpath = "//input[contains(@id,'securitycode')]")
    private WebElement step4_CVV;

    @FindBy(xpath = "//*[contains(@for,'creditcard')]")
    private WebElement creditCardCheckBox;

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


    @FindBy(xpath = "//span[text()='Estimated Total']/following-sibling::span/child::span/child::span[2]")
    private WebElement EstimatedTotalText;

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
    double  CreditCardAmount2 = 1;
    double  CreditCardAmount1;

    String EstimatedTotal = EstimatedTotalText.getText();
    double EstimatedTotalAmount;

    //Float EstimatedTotalAmountValue = Float.parseFloat(EstimatedTotalAmount.replaceAll(",", ""));
    //double CreditCardAmount1 = (EstimatedTotalAmountValue - CreditCardAmount2);

    public ReviewAndBook enterPrimaryAndSecondaryAmount() {

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
            e.printStackTrace();
            BigDecimal b1 = new BigDecimal(Double.toString(EstimatedTotalAmount));
            System.out.println("EstimatedTotalAmount catch block :"+b1);
            BigDecimal b2 = new BigDecimal(Double.toString(CreditCardAmount2));
            System.out.println("EstimatedTotalAmount B2 Catch block :"+b2);
            CreditCardAmount1 = b1.subtract(b2).doubleValue();
            System.out.println("CreditCardAmount1 catch block  :"+CreditCardAmount2);
        }

        waitForVisibilityOfElement(primaryAmountField);
        primaryAmountField.click();
        primaryAmountField.clear();
        //primaryAmountField.sendKeys(String.valueOf(CreditCardAmount1));
        primaryAmountField.sendKeys(Double.toString(CreditCardAmount1));
        System.out.println("CreditCardAmount1 sendKeys  :"+CreditCardAmount1);
        secondaryAmountField.click();
        //secondaryAmountField.sendKeys(String.valueOf(CreditCardAmount2));
        secondaryAmountField.sendKeys(Double.toString(CreditCardAmount2));
        System.out.println("CreditCardAmount2 sendkeys  :"+CreditCardAmount2);
        return this;
    }

    /**
     * To check T&C check box
     */
    public ReviewAndBook checkTermsAndConditions() {
        //helper.scrollToElement(termsCheckInput);
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

    /**
     * @param secCode to enter CVV code
     */
    public ReviewAndBook enterSecurityCode(String secCode) {
        step4_CVV.sendKeys(secCode);
        return this;
    }

    public ReviewAndBook step4_CreditCardCheckBox() {
        if (helper.isElementDisplayed(creditCardCheckBox))
            creditCardCheckBox.click();
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
    public ReviewAndBook flightInfo() {
        waitForVisibilityOfElement(step4_flightInfo);
        helper.selectValueFromDropDown(step4_flightInfo, 3);
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

    public ReviewAndBook reviewModifications() {
        threadSleep(TWO_SECONDS);
        clickUsingJS(reviewModification);
        return this;
    }

    public ReviewAndBook setSelectedCountryText() {
        selectedCountryText = selectedCountry.getText();
        return this;
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

    public boolean isPrimaryCardtextDisplayed() { return PrimaryCardText.isDisplayed(); }

    public boolean isSecondaryCardtextDisplayed() {
        return SecondaryCardText.isDisplayed();
    }

    @Override
    public void isOnPage() {
        log.info("Verify Review And Book Page");
        waitForVisibilityOfElement(firstName);
    }
}
