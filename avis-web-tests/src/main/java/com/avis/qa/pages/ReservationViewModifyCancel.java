package com.avis.qa.pages;

import com.avis.qa.core.AbstractBasePage;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Set;

import static com.avis.qa.utilities.CommonUtils.THREE_SECONDS;
import static com.avis.qa.utilities.CommonUtils.threadSleep;

/**
 * This class contains all the elements and actions performed on ReservationViewModifyCancel Page
 *
 * @author AJain
 */
@Log4j2
public class ReservationViewModifyCancel extends AbstractBasePage {

    @FindBy(xpath = "//select[@id='countrylookup']")
    private WebElement countryDropdown;

    @FindBy(xpath = "//input[@id='res-view-lastName']")
    private WebElement lastnameTextField;

    @FindBy(xpath = "//input[@id='res-view-confirmationNumber']")
    private WebElement ConfirmationNumberTextField;

    @FindBy(xpath = "//button[text()='Find Reservation']")
    private WebElement findReservationButton;


    @FindBy(xpath = "//a[contains(text(),'Chat with Us')]")
    private WebElement chatWithUs;

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
        helper.selectValueFromDropDown(countryDropdown, country);
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

    public boolean isChatWindowOpen() {
        waitForVisibilityOfElement(chatWithUs);
        chatWithUs.click();
        String parentWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        log.info("Set==========>" + allWindows);
        for (String window : allWindows) {
            if (!parentWindow.equals(window)) {
                driver.switchTo().window(window);
                log.info("URL==================>"+driver.getCurrentUrl());
                if (driver.getCurrentUrl().contains("ABGroupChatClient")) {
                    log.info("Chat Window is opened");
                    driver.close();
                    driver.switchTo().window(parentWindow);
                    return true;
                } else {
                    log.error("Chat Window is not opened");
                    return false;
                }
            }
            else
            {
                log.info("Chat Window is not opened ");
                return  false;
            }

        }
        log.info("Chat Window is not opened ");
        return  false;
    }

    public boolean countrySelected(String countrySelected)
    {
        waitForVisibilityOfElement(countryDropdown);
        String country=helper.defaultSelectedvalue(countryDropdown);
        if(countrySelected.equals(country))
            return true;
        else
            return  false;

    }

}
