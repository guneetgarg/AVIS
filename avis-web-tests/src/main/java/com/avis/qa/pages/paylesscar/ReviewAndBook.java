package com.avis.qa.pages.paylesscar;

import com.avis.qa.core.AbstractBasePage;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.Random;

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

    @FindBy(xpath = "//input[contains(@id,'cardnumber')]")
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

    @FindBy(id = "expirydate")
    private WebElement creditCardExpiryDateField;

    @FindBy(id = "expirydate")
    private WebElement expiryMonthDropDown;

    @FindBy(id = "expiryyear")
    private WebElement expiryYearDropDown;

    @FindBy(xpath = "//button[text()='Review Modifications']")
    private WebElement reviewModificationsButton;

    @FindBy(xpath = "//button[text()='Keep Modifications']")
    private WebElement keepModificationButton;

    @FindBy(xpath = "//div[contains(.,'A confirmation email has been sent')]")
    private WebElement confirmationMessage;

    @FindBy(xpath = "//span[@class='confirmation-num']")
    private WebElement confirmationNumber;

    @FindBy(xpath = "//button[@aria-label='Close']/*[name()='svg']")
    private WebElement closeIcon;

    @FindBy(xpath = "//iframe[@title='Rokt offer']")
    private WebElement offerIframe;

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

    /**
     * To check T&C check box
     */
    public ReviewAndBook checkTermsAndConditions() {
        //helper.scrollToElement(termsCheckInput);
        clickUsingJS(termsCheck);
        return this;
    }

    /**
     * @param cardNo To enter credit card no
     */
    public ReviewAndBook enterCardNumber(String cardNo) {
        helper.scrollToElement(cardNumber);
        cardNumber.sendKeys(cardNo, Keys.TAB);
        return this;
        //CF.tabOut("cardNumber");
    }

    /**
     * To select expiry date
     */
    public ReviewAndBook selectExpiryDate() {
        creditCardExpiryDateField.sendKeys("1129");
        //helper.selectValueFromDropDown(month,5);
        //helper.selectValueFromDropDown(year,5);
        return this;
    }

    public ReviewAndBook selectExpiryDateUsingDropDown() {
        Select expiryYear = new Select(expiryYearDropDown);
        Select expiryMonth = new Select(expiryMonthDropDown);

        expiryMonth.selectByVisibleText("November");
        expiryYear.selectByVisibleText("2027");
        return this;
    }

    /**
     * @param secCode to enter CVV code
     */
    public ReviewAndBook enterSecurityCode(String secCode) {
        step4_CVV.sendKeys(secCode);
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

    public ReviewAndBook clickReviewModificationsButton(){
        reviewModificationsButton.click();
        return this;
    }

    public ReviewAndBook clickKeepModificationButton(){
        keepModificationButton.click();
        return this;
    }

    public ReviewAndBook waitForConfirmationMessage(){
        waitForVisibilityOfElement(confirmationMessage);
        return this;
    }


    public String  getConfirmationNumber(){
        String confirmationNo = confirmationNumber.getText();
        return confirmationNo.split(": ")[1];
    }

    @Override
    public void isOnPage() {
        log.info("Verify Review And Book Page");
        waitForVisibilityOfElement(firstName);
    }
}
