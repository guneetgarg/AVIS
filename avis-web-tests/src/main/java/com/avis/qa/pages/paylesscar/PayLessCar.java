package com.avis.qa.pages.paylesscar;
import com.avis.qa.core.AbstractBasePage;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import static com.avis.qa.utilities.CommonUtils.*;
import static com.avis.qa.utilities.CommonUtils.THREE_SECONDS;

@Log4j2
public class PayLessCar extends AbstractBasePage{

    @FindBy(id = "avis-logo")
    private WebElement payLessLogo;

    @FindBy(id = "PicLoc_value")
    private WebElement pickUpLocation;

    @FindBy(xpath = "//*[contains(@id,'DropLoc_value')]")
    private WebElement dropOffLocation;

    @FindBy(xpath = "(//div[@class='angucomplete-description'])[1]")
    private WebElement dropOffSuggestion;

    @FindBy(xpath = "((//div[@class='angucomplete-results'])[1]//div[@class='angucomplete-description'])[1] | ((//div[@id='PicLoc_dropdown'])[1]//div[@class='angucomplete-row'])[1]")
    private WebElement suggestionLocation;

    @FindBy(id = "residency")
    private WebElement residenceCountryDropdown;

    @FindBy(xpath = "(//*[contains(@ng-click,'getVehicles.submit')])[1]")
    private WebElement getRatesButton;

    @FindBy(id = "coupon")
    private WebElement CouponTextField;

    @FindBy(id = "couponpdn")
    private WebElement CouponCheckBox;

    @FindBy(xpath = "//*[contains(@id,'from')] | //input[@id='from']")
    private WebElement pickupDate;

    @FindBy(xpath = "//div[contains(@class,'ui-datepicker-group ui-datepicker-group-last')]/div/a")
    private WebElement nextMonthSelection;

    @FindBy(xpath = "//table[contains(@class,'ui-datepicker-calendar uitable ui-datepicker-table-first ')]//a[contains(text(),16)]")
    private WebElement pickupDateSelection;

    @FindBy(xpath = "//table[contains(@class,'datepicker-calendar')]//a[contains(text(),10)]")
    private WebElement returnDateSelection;

    @FindBy(xpath = "//*[contains(@name,'reservationModel.dropTime')]")
    private WebElement dropOffTime;

    @FindBy(xpath = "//*[contains(@name,'reservationModel.pickUpTime')]")
    private WebElement pickUpTime;

    @FindBy(id = "awd")
    private WebElement pdnTextField;

    @FindBy(id = "age")
    private WebElement ageDropDown;

    @FindBy(xpath = "(//span[contains(@class, 'info-error-msg-text')])[2]")
    private WebElement warningMessage;

    @FindBy(xpath = "//span[@class='mainErrorText']")
    private WebElement underAgeSubChargeMessage;

    @FindBy(xpath = "//div[@class='info-key-drop-text']/p")
    private WebElement locationClosedMessage;

    @FindBy(xpath = "(//*[contains(@id,'from')])[1]")
    private WebElement pickupDateField;

    @FindBy(xpath = "(//*[contains(@id,'from')])[1]")
    private WebElement returnDateField;

    @FindBy(xpath = "//span[text()='Edit']")
    private WebElement editLink;

    @FindBy(id = "modify-link")
    private WebElement modifyReservation;

    @FindBy(id = "selectCar")
    private WebElement updateButton;

    @FindBy(id = "VMlastName")
    private WebElement lastNameTextField;

    @FindBy(id = "VMCConfirmationNumber")
    private WebElement confirmationNumberTextField;

    @FindBy(id = "VMCForm")
    private WebElement submitButton;

    @FindBy(id = "res-cancelReservation")
    private WebElement cancelReservationButton;

    @FindBy(id = "res-cancelreservation-popUp")
    private WebElement confirmCancelReservationButton;

    @FindBy(id = "res-conf-makeNewRes")
    private WebElement makeNewReservationButton;

    @FindBy(xpath = "//a[contains(.,'Modify your reservation')]")
    private WebElement viewOrModifyReservationButton;

    public PayLessCar(WebDriver driver) {
        super(driver);
    }

    public PayLessCar pickUpLocation(String pickUpLocationValue) {
        threadSleep(TWO_SECONDS);
        waitForVisibilityOfElement(pickUpLocation);
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
    public PayLessCar dropOffLocation(String dropOffLocationValue) {
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

    public PayLessCar residenceCountry(String country){
        Select dropDown = new Select(residenceCountryDropdown);
        dropDown.selectByVisibleText(country);
        return this;
    }

    private void enterLocation(String location, WebElement element) {
        element.click();
        element.clear();
        element.sendKeys(location);
        threadSleep(TWO_SECONDS);
    }

    public PayLessCar getRates() {
        waitForVisibilityOfElement(getRatesButton);
        getRatesButton.click();
        return this;
    }

    public PayLessCar enterCouponCode(String couponNo) {
        CouponTextField.clear();
        CouponTextField.sendKeys(couponNo);
        return this;
    }

    public PayLessCar clickCouponCheckBox(){
        CouponCheckBox.click();
        return this;
    }

    public PayLessCar calendarSelection() {
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

    public PayLessCar dropOffTime(String dot) {
        helper.selectValueFromDropDown(dropOffTime, dot);
        return this;
    }

    public PayLessCar pickUpTime(String put) {
        helper.selectValueFromDropDown(pickUpTime, put);
        return this;
    }

    public PayLessCar enterPdn(String pdn){
        pdnTextField.clear();
        pdnTextField.sendKeys(pdn);
        return this;
    }

    public PayLessCar selectAge(String age){
        Select dropDown = new Select(ageDropDown);
        dropDown.selectByVisibleText(age);
        return this;
    }

    public boolean isNoCarsAvailableMessageDisplayed(){
        waitForVisibilityOfElement(warningMessage);
        return warningMessage.getText().contains("Based on your age selection, there are no cars available at this location");
    }

    public boolean isUnderAgeSubChargeMessageDisplayed(){
        waitForVisibilityOfElement(underAgeSubChargeMessage);
        return underAgeSubChargeMessage.getText().contains("An underage surcharge is applicable except at participating locations");
    }

    public boolean isLocationClosedMessageDisplayed(){
        waitForVisibilityOfElement(locationClosedMessage);
        return locationClosedMessage.getText().contains("The location you selected is closed at the time of drop off, but a key-drop box is available for your convenience");
    }

    public PayLessCar clickEditButton(){
        editLink.click();
        return this;
    }

    public PayLessCar clickModifyReservationButton(){
        modifyReservation.click();
        return this;
    }

    public PayLessCar clickUpdateButton(){
        updateButton.click();
        return this;
    }

    public PayLessCar clickMakeNewReservationButton(){
        makeNewReservationButton.click();
        return this;
    }
    public PayLessCar cancelReservation(String reservationNumber, String lastName){
        viewOrModifyReservationButton.click();
        lastNameTextField.sendKeys(lastName);
        confirmationNumberTextField.sendKeys(reservationNumber);
        submitButton.click();
        cancelReservationButton.click();
        confirmCancelReservationButton.click();

        return this;
    }


    @Override
    public void isOnPage() {

    }
}
