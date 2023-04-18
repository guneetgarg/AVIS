package com.avis.qa.pages;

import com.avis.qa.components.ReservationWidget;
import com.avis.qa.core.AbstractBasePage;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.avis.qa.core.Configuration.URL;
import static com.avis.qa.utilities.CommonUtils.*;


/**
 * This class contains all the elements and actions performed on SignUp Page
 *
 * @author AJain
 */
@Log4j2
public class Enrollment extends AbstractBasePage {

    @FindBy(xpath = "//span[text()='Join Avis Preferred']")
    private WebElement JoinAvisText;

    @FindBy(xpath = "(//select[@name='country'])[1]")
    private WebElement CountryDropdown;

    @FindBy(xpath = "//input[@name='firstname']")
    private WebElement FirstNameTextBox;

    @FindBy(xpath = "//input[@name='lastname']")
    private WebElement LastNameTextBox;

    @FindBy(xpath = "//input[@name='phone']")
    private WebElement MobileNumberTextBox;

    @FindBy(xpath = "(//input[@name='earn-points'])[1]")
    private WebElement ReceiveTextsAboutReservationsToggleButton;

    @FindBy(xpath = "//input[@name='email']")
    private WebElement EmailAddressTextBox;

    @FindBy(xpath = "//span[text()='Use Email address as Username']")
    private WebElement UseEmailAsUsernameCheckbox;

    @FindBy(xpath = "(//input[@name='username'])[2]")
    private WebElement UsernameTextBox;

    @FindBy(xpath = "(//input[@name='password'])[2]")
    private WebElement PasswordTextBox;

    @FindBy(xpath = "(//input[@name='earn-points'])[2]")
    private WebElement RecieveEmailOffersToggleButton;

    @FindBy(xpath = "//input[@name='addressline1']")
    private WebElement AddLine1TextBox;

    @FindBy(xpath = "(//div[@ng-if='showSuggestions'])[1]")
    private WebElement suggestionAddressLine1;

    @FindBy(xpath = "//input[@name='addressline2']")
    private WebElement AddLine2TextBox;

    @FindBy(xpath = "//input[@name='zip']")
    private WebElement ZipcodeTextBox;

    @FindBy(xpath = "//input[@name='AWDNumber']")
    private WebElement AWDNumberTextBox;

    @FindBy(xpath = "//button[text()='Save']")
    private WebElement SaveButton;

    public Enrollment(WebDriver driver) {
        super(driver);
    }


    public boolean isJoinAvisPreferredTextDisplayed() {
        log.info("Verify Join Avis Preffered displayed on page");
        return JoinAvisText.isDisplayed();
    }

    public Enrollment enterFirstName(String fname) {
        waitForVisibilityOfElement(FirstNameTextBox);
        FirstNameTextBox.sendKeys(fname);
        threadSleep(ONE_SECOND);
        return this;
    }

    public Enrollment enterLastName(String lname) {
        LastNameTextBox.sendKeys(lname);
        threadSleep(ONE_SECOND);
        return this;
    }

    public Enrollment enterMobileNumber(String phone) {
        MobileNumberTextBox.sendKeys(phone);
        threadSleep(ONE_SECOND);
        return this;
    }

    public Enrollment enterEmailAddress(String email) {
        EmailAddressTextBox.sendKeys(email);
        threadSleep(ONE_SECOND);
        return this;
    }

    public Enrollment enterUsername(String username) {
        UsernameTextBox.sendKeys(username);
        threadSleep(ONE_SECOND);
        return this;
    }
    public Enrollment enterPassword(String pwd) {
        PasswordTextBox.sendKeys(pwd);
        threadSleep(ONE_SECOND);
        return this;
    }

    public Enrollment selectAddressLine1Suggestion(String addLine1) {
        enterAddLine1(addLine1, AddLine1TextBox);
        if (helper.isElementDisplayed(suggestionAddressLine1))
            clickUsingJS(suggestionAddressLine1);
        else {
            clearTextUsingJS(AddLine1TextBox);
            enterAddLine1(addLine1, AddLine1TextBox);
            if (helper.isElementDisplayed(suggestionAddressLine1))
                clickUsingJS(suggestionAddressLine1);
        }
        return this;
    }

    private void enterAddLine1(String location, WebElement element) {
        element.click();
        element.clear();
        element.sendKeys(location);
        threadSleep(TWO_SECONDS);
    }

    public Enrollment clickUseEmailAsUsernameCheckbox() {
        waitForVisibilityOfElement(UseEmailAsUsernameCheckbox);
        UseEmailAsUsernameCheckbox.click();
        threadSleep(ONE_SECOND);
        return this;
    }

    public EnrollmentCreditCardPage clickSaveButton() {
        waitForVisibilityOfElement(SaveButton);
        SaveButton.click();
        return new EnrollmentCreditCardPage(driver);
    }

    @Override
    public void isOnPage() {
        log.info("Verify Enrollment Page");
        waitForVisibilityOfElement(JoinAvisText);
    }
}
