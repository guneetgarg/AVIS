package com.avis.qa.pages;

import com.avis.qa.core.AbstractBasePage;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.avis.qa.utilities.CommonUtils.THREE_SECONDS;
import static com.avis.qa.utilities.CommonUtils.threadSleep;


/**
 * This class contains all the elements and actions performed on Driving Licence Page
 *
 * @author AJain
 */
@Log4j2
public class EnrollmentDrivingLicencePage extends AbstractBasePage {

    @FindBy(xpath = "//p[text()='Credit Card Info Saved.']")
    private WebElement CreditcardSavedText;

    @FindBy(xpath = "//select[@ng-model='vm.accountModel.dlSummary.state']")
    private WebElement StateDropdown;

    @FindBy(xpath = "//select[@name='Month']")
    private WebElement MonthDropDown;

    @FindBy(xpath = "//select[@name='Date']")
    private WebElement DateDropDown;

    @FindBy(xpath = "//select[@name='Year']")
    private WebElement YearDropDown;

    @FindBy(xpath = "//input[@name='dlNumber']")
    private WebElement DriverLicenceNumberTextBox;

    @FindBy(xpath = "(//input[@name='earn-points'])[1]")
    private WebElement ReceiveTextsAboutReservationsToggleButton;

    @FindBy(xpath = "//input[@name='phone']")
    private WebElement MobileNumberTextBox;



    @FindBy(xpath = "//button[text()='Save']")
    private WebElement SaveButton;

    public EnrollmentDrivingLicencePage(WebDriver driver) {
        super(driver);
    }

    public EnrollmentDrivingLicencePage selectState() {
        StateDropdown.click();
        helper.selectValueFromDropDown(StateDropdown,1);
        return this;
    }
    public EnrollmentDrivingLicencePage selectDOB() {
        MonthDropDown.click();
        helper.selectValueFromDropDown(MonthDropDown,1);
        DateDropDown.click();
        helper.selectValueFromDropDown(DateDropDown,3);
        YearDropDown.click();
        helper.selectValueFromDropDown(YearDropDown,5);
        return this;
    }

    public EnrollmentDrivingLicencePage enterDrivingLicence(String DrivingLicence) {
        DriverLicenceNumberTextBox.sendKeys(DrivingLicence);
        return this;
    }

    public EnrollmentDrivingLicencePage clickSaveButton() {
        waitForVisibilityOfElement(SaveButton);
        SaveButton.click();
        return this;
    }


    @Override
    public void isOnPage() {
        log.info("Verify Credit Card Saved Message");
        waitForVisibilityOfElement(CreditcardSavedText);
    }
}
