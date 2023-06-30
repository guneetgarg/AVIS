package com.avis.qa.pages.paylesscar;

import com.avis.qa.core.AbstractBasePage;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.avis.qa.utilities.CommonUtils.TWO_SECONDS;
import static com.avis.qa.utilities.CommonUtils.threadSleep;

@Log4j2
public class Vehicles extends AbstractBasePage {

    @FindBy(xpath = "(//a[contains(text(),'Pay Now')])[4]|(//a[contains(text(),'Payer maintenant')])[1]|(//a[contains(text(),'Pagar ahora')])[1]|(//a[contains(text(),'Select')])[1]")
    private WebElement step2PayNowSubmitButton;

    @FindBy(xpath = "(//a[contains(text(),'Select')])[4]|(//a[contains(text(),'Select')])[3]|(//a[@id='res-vehicles-pay-later'])[1] | (//a[contains(text(),'Pay Later')])[2]|(//a[contains(text(),'Pay at Counter')])[1]|(//a[contains(text(),'Paiement au comptoir')])[1]|(//a[contains(text(),'Pagar en el mostrador')])[1]|((//p[contains(@class,'totalPay')]/../a)[1])")
    private WebElement step2SubmitButton;

    @FindBy(css = "li[title='Choose a Car']")
    private WebElement selectACarText;

    public Vehicles(WebDriver driver) {
        super(driver);
    }

    public Extras step2Submit() {
        threadSleep(TWO_SECONDS);
        helper.scrollToElement(step2SubmitButton);
        clickUsingJS(step2SubmitButton);
        return new Extras(driver);
    }

    /**
     * Method to click on Pay Now
     */
    public Extras step2SubmitPayNow() {
        threadSleep(TWO_SECONDS);
        helper.scrollToElement(step2PayNowSubmitButton);
        clickUsingJS(step2PayNowSubmitButton);
        return new Extras(driver);
    }

    @Override
    public void isOnPage() {
        waitForVisibilityOfElement(selectACarText);
    }
}
