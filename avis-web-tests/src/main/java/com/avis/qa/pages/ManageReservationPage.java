package com.avis.qa.pages;

import com.avis.qa.core.AbstractBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.avis.qa.utilities.CommonUtils.THREE_SECONDS;
import static com.avis.qa.utilities.CommonUtils.threadSleep;

/**
 * This class contains all the elements and actions performed on ManageReservation Page
 *
 * @author AJain
 */

public class ManageReservationPage extends AbstractBasePage {

    @FindBy(xpath = "//h1[text()='Your car is reserved.']")
    private WebElement CarReservedTextMessage;

    @FindBy(xpath = "(//span[text()='Modify'])[3]")
    private WebElement RateAndBenefitInfoModifyButton;

    @FindBy(xpath = "//input[@id='res-view-confirmationNumber']")
    private WebElement ConfirmationNumberTextField;

    @FindBy(xpath = "//span[@class='confirmation-num']")
    private WebElement confirmationNumber;

    public ManageReservationPage(WebDriver driver) {
        super(driver);
    }

    public ManageReservationPage ClickRateAndBenefitInfoModifyButton() {
        helper.scrollToElement(RateAndBenefitInfoModifyButton);
        clickUsingJS(waitForVisibilityOfElement(RateAndBenefitInfoModifyButton));
        return this;
    }

    public boolean isCarReservedTextMessageDisplayed() {
        return CarReservedTextMessage.isDisplayed();
    }

    public boolean isConfirmationNumberSame(String ConfirmationNum) {
        return confirmationNumber.getText().contains(ConfirmationNum);
    }

    @Override
    public void isOnPage() {
        waitForVisibilityOfElement(CarReservedTextMessage);
    }













}
