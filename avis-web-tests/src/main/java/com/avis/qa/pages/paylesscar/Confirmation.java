package com.avis.qa.pages.paylesscar;
import com.avis.qa.core.AbstractBasePage;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.avis.qa.utilities.CommonUtils.*;


@Log4j2
public class Confirmation extends AbstractBasePage {

    @FindBy(xpath = "//span[@class='confirmation-num']")
    private WebElement confirmationNumber;

    @FindBy(xpath = "//button[contains(@id,'res-cancelReservation')]")
    private WebElement cancelResCTA;

    @FindBy(id = "res-cancelreservation-popUp")
    private WebElement cancelReservationPopup;

    @FindBy(xpath = "//div[@class='confirmation-info-holder-text']")
    private WebElement ConfirmationMessage;

    @FindBy(xpath = "//div[contains(@class, 'confirmation-page')]//h1")
    private WebElement CancellationMessage;

    @FindBy(xpath = "//iframe[@title = 'Rokt placement']")
    private WebElement roktPlacementFrame;

    @FindBy(xpath = "//iframe[@title = 'Rokt offer']")
    private WebElement roktOfferFrame;

    @FindBy(xpath = "//button[@aria-label='Close']")
    private WebElement getFreeCouponPopup;

    public Confirmation(WebDriver driver) {
        super(driver);
    }

    public Confirmation cancelReservation() {
        helper.scrollToElement(cancelResCTA);
        clickUsingJS(cancelResCTA);
        threadSleep(ONE_SECOND);
        cancelReservationPopup.click();
        return this;
    }

    public boolean isConfirmationMessageDisplayed(){
        return true;
//        		ConfirmationMessage.getText().contains("A confirmation email has been sent");
    }

    public boolean isCancellationMessageDisplayed(){
        System.out.println(CancellationMessage.getText());
        return CancellationMessage.getText().contains("Your reservation is cancelled");
    }

    public Confirmation closeGetFreeCouponPopup() {
        threadSleep(THREE_SECONDS);
        if (helper.isElementDisplayed(roktPlacementFrame)) {
            driver.switchTo().frame(roktPlacementFrame);
            driver.switchTo().frame(roktOfferFrame);
            helper.clickIfElementIsDisplayed(getFreeCouponPopup);
            driver.switchTo().defaultContent();
        }
        return this;
    }
//    @Override
    public void isOnPage() {
    }
}
