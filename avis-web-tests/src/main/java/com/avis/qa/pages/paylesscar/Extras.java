package com.avis.qa.pages.paylesscar;

import com.avis.qa.core.AbstractBasePage;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.avis.qa.utilities.CommonUtils.*;

@Log4j2
public class Extras extends AbstractBasePage {

    @FindBy(xpath = "//button[@class='btn btn-primary-avis pull-right'] | //button[contains(@id,'res-extras-continue-bottom')] | (//button[contains(text(),'Continue')])[2] | (//button[contains(text(),'Continue')])[1]")
    private WebElement submitStep3;

    @FindBy(id = "modify_link")
    private WebElement modifyReservation;

    @FindBy(xpath = "//div[contains(text(),'Location & Date')]")
    private WebElement locationAndDateHeader;

    @FindBy(xpath = "(//div[contains(text(),'Car Class')])[2]")
    private WebElement carClassHeader;

    @FindBy(xpath = "(//div[contains(text(),'Your Information')])")
    private WebElement yourInformationHeader;

    @FindBy(xpath = "(//div[contains(text(),'Rate Terms')])[1]")
    private WebElement rateTermsHeader;


    public Extras(WebDriver driver) {
        super(driver);
    }

    public ReviewAndBook Step3Submit() {
        threadSleep(TWO_SECONDS);
        helper.scrollToElement(submitStep3);
        System.out.println("continue");
        clickUsingJS(submitStep3);
        return new ReviewAndBook(driver);
    }

    public Extras clickEditRentalDetails(){
        threadSleep(THREE_SECONDS);
        modifyReservation.click();
        return this;
    }

    public Extras verifyReservationDetails(){
        waitForVisibilityOfElement(locationAndDateHeader);
        waitForVisibilityOfElement(carClassHeader);
        waitForVisibilityOfElement(yourInformationHeader);
        waitForVisibilityOfElement(rateTermsHeader);
        return this;
    }

    @Override
    public void isOnPage() {
        log.info("Verify Extras Page");
        waitForVisibilityOfElement(submitStep3);
    }
}
