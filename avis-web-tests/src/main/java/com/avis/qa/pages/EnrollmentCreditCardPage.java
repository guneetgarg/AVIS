package com.avis.qa.pages;

import com.avis.qa.core.AbstractBasePage;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.avis.qa.utilities.CommonUtils.THREE_SECONDS;
import static com.avis.qa.utilities.CommonUtils.threadSleep;


/**
 * This class contains all the elements and actions performed on CreditCard Page
 *
 * @author AJain
 */
@Log4j2
public class EnrollmentCreditCardPage extends AbstractBasePage {

    @FindBy(xpath = "//div[@class='wrap-credit-card-dl']/p")
    private WebElement CreditCardDetailsText;

    @FindBy(xpath = "(//select[@class='form-control ng-pristine ng-pending ng-empty ng-touched']")
    private WebElement CreditCardTypeDropdown;

    @FindBy(xpath = "//input[@name='creditcard']")
    private WebElement CreditCardNumberTextBox;

    @FindBy(xpath = "//*[contains(@name,'expmonthselectedItem')]")
    private WebElement MonthDropDown;

    @FindBy(xpath = "//*[contains(@name,'selectedExpYear')]")
    private WebElement YearDropDown;

    @FindBy(xpath = "//input[@name='cvv']")
    private WebElement cvvTextBox;

    @FindBy(xpath = "//button[text()='Save']")
    private WebElement SaveButton;

    //card details
    //MASTERCARD, 5430289291007399, 11/2025, 408
    //VISA, 4556382670259383, 8/2027, 694
    //AMEX, 371777335245621, 7/2023, 868


    public EnrollmentCreditCardPage selectCardType() {
        CreditCardTypeDropdown.click();
        helper.selectValueFromDropDown(CreditCardTypeDropdown,1);
        return this;
    }

    public EnrollmentCreditCardPage enterCardNumber(String cardNo) {
        waitForVisibilityOfElement(CreditCardNumberTextBox);
        CreditCardNumberTextBox.click();
        CreditCardNumberTextBox.sendKeys(cardNo);
        return this;
        //CF.tabOut("cardNumber");
    }

    public EnrollmentCreditCardPage(WebDriver driver) {
        super(driver);
    }

    public EnrollmentCreditCardPage selectExpiryDateAndYear() {
        MonthDropDown.click();
        helper.selectValueFromDropDown(MonthDropDown,5);
        YearDropDown.click();
        helper.selectValueFromDropDown(YearDropDown,5);
        return this;
    }

    public EnrollmentCreditCardPage enterSecurityCode(String secCode) {
        cvvTextBox.sendKeys(secCode);
        return this;
    }

    public EnrollmentDrivingLicencePage clickSaveButton() {
        waitForVisibilityOfElement(SaveButton);
        SaveButton.click();
        return new EnrollmentDrivingLicencePage(driver);
    }

    @Override
    public void isOnPage() {
        log.info("Verify Credit Card Page");
        waitForVisibilityOfElement(CreditCardDetailsText);
    }
}
