package com.avis.qa.pages;

import com.avis.qa.components.ReservationWidget;
import com.avis.qa.core.AbstractBasePage;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static com.avis.qa.constants.TextComparison.ABOVE_NINETY_ERROR_MESSAGE;
import static com.avis.qa.constants.TextComparison.ABOVE_THREE_THIRTY_ERROR_MESSAGE;
import static com.avis.qa.utilities.CommonUtils.*;

/**
 * This class contains all the elements and actions performed on ManageReservation Page
 *
 * @author AJain
 */
@Log4j2
public class ModifyReservationTimeAndPlacePage extends AbstractBasePage {

    @FindBy(xpath = "//*[contains(@id,'DropLoc_value')]")
    private WebElement dropOffLocation;

    @FindBy(xpath = "//strong[text()='Modify Your Reservation']")
    private WebElement ModifyReservationTextMsg;

    @FindBy(xpath = "(//div[@class='angucomplete-description'])[1]")
    private WebElement dropOffSuggestion;

    @FindBy(id = "PicLoc_value")
    private WebElement pickUpLocation;

    @FindBy(xpath = "((//div[@class='angucomplete-results'])[1]//div[@class='angucomplete-description'])[1] | ((//div[@id='PicLoc_dropdown'])[1]//div[@class='angucomplete-row'])[1]")
    private WebElement suggestionLocation;

    @FindBy(xpath = "//*[contains(@id,'from')] | //input[@id='from']")
    private WebElement pickupDate;

    @FindBy(xpath = "//div[contains(@class,'ui-datepicker-group ui-datepicker-group-last')]/div/a")
    private WebElement nextMonthSelection;

    @FindBy(xpath = "//select[@data-handler='jumpMonth']")
    private WebElement jumpMonthDropdown;

    @FindBy(xpath = "//table[contains(@class,'ui-datepicker-calendar uitable ui-datepicker-table-first ')]//a[contains(text(),16)]")
    private WebElement pickupDateSelection;

    @FindBy(xpath = "//table[contains(@class,'ui-datepicker-calendar uitable ui-datepicker-table-first ')]//a[contains(text(),20)]")
    private WebElement returnDateSelection5daysGap;

    @FindBy(xpath = "//table[contains(@class,'datepicker-calendar')]//a[contains(text(),10)]")
    private WebElement returnDateSelection;

    @FindBy(xpath = "(//*[contains(@ng-click,'getVehicles.submit')])[1]")
    private WebElement selectMyCarButton;

    @FindBy(id = "avis-logo")
    private WebElement avisLogo;

    @FindBy(xpath = "//input[@id ='to']")
    private WebElement returnDate;

    @FindBy(xpath = "(//table[contains(@class,'datepicker-calendar')]//a[contains(text(),25)])")
    private WebElement returnDateSelection_new4;

    @FindBy(xpath = "(//*[@id='reservationModel.personalInfoRQ.residency'])[1]")
    private WebElement selectCountry;

    @FindBy(xpath = "(//*[contains(@id,'from')])[1]")
    private WebElement pickupDateField;

    @FindBy(xpath = "//input[@id='to']")
    private WebElement returnDateField;

    @FindBy(xpath = "//span[@class='mainErrorText info-error-msg-text']")
    private WebElement nullPickUpLocationMessageElement;

    @FindBy(xpath = "//*[contains(@name,'reservationModel.personalInfoRQ.age')]")
    private WebElement selectAge;

    @FindBy(xpath = "//*[contains(@name,'reservationModel.dropTime')]")
    private WebElement dropOffTime;

    @FindBy(xpath = "//*[contains(@name,'reservationModel.pickUpTime')]")
    private WebElement pickUpTime;

    @FindBy(xpath = "(//span[contains(text(),'The location you have selected is Sold Out during the dates requested.')])")
    private WebElement LocationSoldOutErrorText;

    public ModifyReservationTimeAndPlacePage(WebDriver driver) {
        super(driver);
    }

    public ModifyReservationTimeAndPlacePage pickUpLocation(String pickUpLocationValue) {
        enterLocation(pickUpLocationValue, pickUpLocation);
        if (helper.isElementDisplayed(suggestionLocation))
            clickUsingJS(suggestionLocation);
        else {
            clearTextUsingJS(pickUpLocation);
            enterLocation(pickUpLocationValue, pickUpLocation);
            if (helper.isElementDisplayed(suggestionLocation))
                clickUsingJS(suggestionLocation);
        }
        return this;
    }


    /**
     * Method to verify dropOff location
     **/
    public ModifyReservationTimeAndPlacePage dropOffLocation(String dropOffLocationValue) {
        enterLocation(dropOffLocationValue, dropOffLocation);

        if (helper.isElementDisplayed(dropOffSuggestion))
            clickUsingJS(dropOffSuggestion);
        else {
            clearTextUsingJS(pickUpLocation);
            enterLocation(dropOffLocationValue, dropOffLocation);
            if (helper.isElementDisplayed(dropOffSuggestion))
                clickUsingJS(dropOffSuggestion);
        }
        return this;
    }

    private void enterLocation(String location, WebElement element) {
        element.click();
        element.clear();
        element.sendKeys(location);
        threadSleep(TWO_SECONDS);
    }

    /**
     * Method to select calendar
     **/
    public ModifyReservationTimeAndPlacePage calendarSelection() {
        helper.scrollBy("-600");
        threadSleep(TWO_SECONDS);
        pickupDate.click();

        for (int i = 0; i < 5; i++) {
            threadSleep(ONE_SECOND);
            nextMonthSelection.click();
        }

        pickupDateSelection.click();
        threadSleep(THREE_SECONDS);
        returnDateSelection.click();
        return this;
    }

    public ModifyReservationTimeAndPlacePage calendarSelection(int num) {
        helper.scrollBy("-600");
        threadSleep(TWO_SECONDS);
        pickupDate.click();
        pickupDate.clear();
        dropOffLocation.click();
        returnDate.click();
        returnDate.clear();
        dropOffLocation.click();
        pickupDate.click();

        for (int i = 0; i < num; i++) {
            threadSleep(ONE_SECOND);
            nextMonthSelection.click();
        }
        pickupDateSelection.click();
        threadSleep(THREE_SECONDS);
        returnDateSelection5daysGap.click();
        return this;
    }


    public ModifyReservationTimeAndPlacePage selectCountry(String country) {
        helper.selectValueFromDropDown(selectCountry, country);
        return this;
    }

    public ModifyReservationTimeAndPlacePage dropOffTime(String dot) {
        helper.selectValueFromDropDown(dropOffTime, dot);
        return this;
    }

    public ModifyReservationTimeAndPlacePage pickUpTime(String put) {
        helper.selectValueFromDropDown(pickUpTime, put);
        return this;
    }

    /**
     * Method to click on Home page submit i.e Select my Car Button.
     *
     * @return
     **/

    public ModifyReservationTimeAndPlacePage selectMyCar() {
        waitForVisibilityOfElement(selectMyCarButton);
        try {
            selectMyCarButton.click();
            LocationSoldOutErrorText.isDisplayed();
            for(int i=2;i<=4;i++)
            {
                if(LocationSoldOutErrorText.isDisplayed())
                {
                    calendarSelection(i);
                    selectMyCarButton.click();
                }
                else
                {
                    break;
                }
            }
            return this;
        }
        catch(Exception e)
        {
            //e.printStackTrace();
            log.info("No cabs available for selected date error handled");
            return this;
        }

    }

    public boolean isModifyReservationTextMsgDisplayed() {
        return ModifyReservationTextMsg.isDisplayed();
    }
    @Override
    public void isOnPage() {
        waitForVisibilityOfElement(pickUpLocation);
    }

}
