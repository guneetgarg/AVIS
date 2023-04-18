package com.avis.qa.pages.paylesscar;

import com.avis.qa.core.AbstractBasePage;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.avis.qa.utilities.CommonUtils.TWO_SECONDS;
import static com.avis.qa.utilities.CommonUtils.threadSleep;

@Log4j2
public class HomePage extends AbstractBasePage {

    @FindBy(id = "avis-logo")
    private WebElement payLessLog;

    @FindBy(xpath = "(//a[text()='Sign In'])[2]")
    private WebElement LoginLink;

    @FindBy(xpath = "//a[text()='Reservations']")
    private WebElement reservationTab;

    @FindBy(xpath = "//a[text()='Make a Reservation']")
    private WebElement makeReservation;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickLoginLink(){
        waitForVisibilityOfElement(LoginLink);
        clickUsingJS(LoginLink);
    }

    public void clickMakeReservation(){
        waitForVisibilityOfElement(reservationTab);
        threadSleep(TWO_SECONDS);
        reservationTab.click();
        makeReservation.click();
    }

    @Override
    public void isOnPage() {
        waitForVisibilityOfElement(payLessLog);
    }
}
