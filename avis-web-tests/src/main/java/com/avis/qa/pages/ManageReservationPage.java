package com.avis.qa.pages;

import com.avis.qa.core.AbstractBasePage;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.avis.qa.constants.AvisConstants.fareCharges;
import static com.avis.qa.utilities.CommonUtils.THREE_SECONDS;
import static com.avis.qa.utilities.CommonUtils.threadSleep;

/**
 * This class contains all the elements and actions performed on ManageReservation Page
 *
 * @author AJain
 */
@Log4j2
public class ManageReservationPage extends AbstractBasePage {

    @FindBy(xpath = "//h1[text()='Your car is reserved.']")
    private WebElement CarReservedTextMessage;

    @FindBy(xpath = "(//span[text()='Modify'])[3]")
    private WebElement RateAndBenefitInfoModifyButton;

    @FindBy(xpath = "//input[@id='res-view-confirmationNumber']")
    private WebElement ConfirmationNumberTextField;

    @FindBy(xpath = "//span[@class='confirmation-num']")
    private WebElement confirmationNumber;

    @FindBy(xpath="//button[@id='res-conf-print']")
    private WebElement printConfirmation;

    @FindBy(xpath="//div[contains(text(),'Estimated Total')]//ancestor::div[@class='header']//span[@class='total-amount pull-right']")
    private WebElement estimatedTotalAmount;

    @FindBy(xpath="//span[contains(.,'Base Rate')]//following-sibling::div[@class='pull-right ']")
    private WebElement baseRateAmount;

    @FindBy(xpath="//div[contains(text(),'Your Car')]//span[contains(text(),'Modify')]")
    private  WebElement mvcModifyIcon;

    @FindBy(xpath="//div[contains(text(),'Your Time & Place')]//span[contains(text(),'Modify')]")
    private WebElement timeAndPlaceModifyIcon;
    @FindBy(xpath="//div[contains(text(),'Rate & Benefit Information')]//span[contains(text(),'Modify')]")
    private WebElement rateModifyIcon;

    @FindBy(xpath="//div[contains(text(),'Rental Options')]//span[contains(text(),'Modify')]")
    private WebElement rentaleModifyIcon;

    @FindBy(xpath = "//div[@class='info-msg-text']")
    private WebElement AWDMessageText;


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

    public boolean isPrintConfirmationButtonDisplay()
    {
        boolean isButtonDisplay;
        try {
            waitForVisibilityOfElement(printConfirmation);
            isButtonDisplay = printConfirmation.isDisplayed();
        }
        catch (Exception e)
        {
            log.error("Exception in PrintDispaly Button "+e);
            isButtonDisplay=false;
        }
        return  isButtonDisplay;

    }
    public void setBaseRateAndEstimatedTotal()
    {
        waitForVisibilityOfElement(baseRateAmount);
        fareCharges.put("ReviewPreModification-BaseRate",Double.parseDouble(baseRateAmount.getText().replace("$","").replace("US","")));
        waitForVisibilityOfElement(estimatedTotalAmount);
        fareCharges.put("ReviewPreModification-TotalEstimatedValue",Double.parseDouble(estimatedTotalAmount.getText().replace("$","")));
    }
    public boolean ismvcModifyIconVisible()
    {
        boolean isVisible=false;
        try
        {
            helper.scrollToElement(mvcModifyIcon);
            isVisible=mvcModifyIcon.isDisplayed();
            log.info("Modify option visible for MVC ");
        }
        catch (Exception e)
        {
            isVisible=false;
            log.error("Modify option not visible for  MVC");
        }
        return  isVisible;
    }
    public boolean isModifyTimeAndPlaceIconVisible()
    {
        boolean isVisible=false;
        try
        {
            helper.scrollToElement(timeAndPlaceModifyIcon);
            isVisible=timeAndPlaceModifyIcon.isDisplayed();
            log.info("Modify option visible for Time And Place Section");
        }
        catch (Exception e)
        {
            isVisible=false;
            log.error("Modify option not visible for Time And Place Section");
        }
        return  isVisible;
    }

    public boolean isModifyRateIconVisible()
    {
        boolean isVisible=false;
        try
        {
            helper.scrollToElement(rateModifyIcon);
            isVisible=rateModifyIcon.isDisplayed();
            log.info("Modify Icon visible for Rate Section ");
        }
        catch (Exception e)
        {
            isVisible=false;
            log.error("Modify Icon not visible for Rental Section ");
        }
        return  isVisible;
    }

    public boolean isModifyRentalIconVisible()
    {
        boolean isVisible=false;
        try
        {
            helper.scrollToElement(rentaleModifyIcon);
            isVisible=rentaleModifyIcon.isDisplayed();
            log.info("Modify option visible for Rental Section ");
        }
        catch (Exception e)
        {
            isVisible=false;
            log.error("Modify option not visible for  Rental");
        }
        return  isVisible;
    }

    public boolean isAWDMessageTextDisplayed() {
        return AWDMessageText.getText().contains("Your provided AWD number includes or discounts certain extras, and may include ");
    }












}
