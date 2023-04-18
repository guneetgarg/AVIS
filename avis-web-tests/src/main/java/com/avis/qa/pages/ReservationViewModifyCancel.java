package com.avis.qa.pages;

import com.avis.qa.core.AbstractBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.avis.qa.utilities.CommonUtils.THREE_SECONDS;
import static com.avis.qa.utilities.CommonUtils.threadSleep;

/**
 * This class contains all the elements and actions performed on ReservationViewModifyCancel Page
 *
 * @author AJain
 */

public class ReservationViewModifyCancel extends AbstractBasePage {

    @FindBy(xpath = "//select[@id='countrylookup']")
    private WebElement countryDropdown;

    @FindBy(xpath = "//input[@id='res-view-lastName']")
    private WebElement lastnameTextField;

    @FindBy(xpath = "//input[@id='res-view-confirmationNumber']")
    private WebElement ConfirmationNumberTextField;

    @FindBy(xpath = "//button[text()='Find Reservation']")
    private WebElement findReservationButton;

    public ReservationViewModifyCancel(WebDriver driver) {
        super(driver);
    }

    public ReservationViewModifyCancel enterLastname(String lastname) {
        threadSleep(THREE_SECONDS);
        waitForVisibilityOfElement(lastnameTextField);
        lastnameTextField.click();
        lastnameTextField.sendKeys(lastname);
        return this;
    }
    public ReservationViewModifyCancel selectCountry(String country) {
        helper.selectValueFromDropDown(countryDropdown,country);
        return this;
    }

    public ReservationViewModifyCancel enterConfirmationNumber(String confirmationNumber) {
        waitForVisibilityOfElement(ConfirmationNumberTextField);
        ConfirmationNumberTextField.click();

        ConfirmationNumberTextField.sendKeys(confirmationNumber);
        return this;
    }

    public ManageReservationPage ClickFindReservationButton() {
        threadSleep(2000);
        findReservationButton.click();
        return new ManageReservationPage(driver);
    }

    @Override
    public void isOnPage() {
        waitForVisibilityOfElement(findReservationButton);
    }
}
