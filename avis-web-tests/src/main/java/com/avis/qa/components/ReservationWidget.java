package com.avis.qa.components;

import com.avis.qa.core.AbstractBasePage;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.avis.qa.constants.TextComparison.ABOVE_NINETY_ERROR_MESSAGE;
import static com.avis.qa.constants.TextComparison.ABOVE_THREE_THIRTY_ERROR_MESSAGE;
import static com.avis.qa.utilities.CommonUtils.*;


/**
 * This class contains all the elements and actions performed on ReservationWidget
 *
 * @author ikumar
 */
@Log4j2
public class ReservationWidget extends AbstractBasePage {

    public static WebElement PopupBudget;
    
    @FindBy(xpath = "//*[contains(@id,'DropLoc_value')]")
    private WebElement dropOffLocation;

    @FindBy(xpath = "(//div[@class='angucomplete-description'])[1]")
    private WebElement dropOffSuggestion;

    @FindBy(xpath = "(//input[@id='PicLoc_value'])[1]")
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

    @FindBy(xpath="//a[@class='ui-state-default']")
    private List<WebElement> pickupDateSel;


    @FindBy(xpath = "//table[contains(@class,'ui-datepicker-calendar uitable ui-datepicker-table-first ')]//a[contains(text(),20)]")
    private WebElement returnDateSelection5daysGap;

    @FindBy(xpath = "//table[contains(@class,'datepicker-calendar')]//a[contains(text(),10)]")
    private WebElement returnDateSelection;

    @FindBy(xpath = "//table[contains(@class,'ui-datepicker-calendar uitable ui-datepicker-table-first ')]//a[text()='24']")
    private WebElement snowChainPickupDate;

    @FindBy(xpath = "//table[contains(@class,'ui-datepicker-calendar uitable ui-datepicker-table-last ')]//a[text()='4']")
    private WebElement snowChainReturnDate;

    @FindBy(xpath = "(//*[contains(@ng-click,'getVehicles.submit')])[1]")
    private WebElement selectMyCarButton;

    @FindBy(id = "avis-logo")
    private WebElement avisLogo;

    @FindBy(xpath = "//span[@class='mainErrorText info-error-msg-text' and @ng-bind-html='error.message | htmlFilter']")
    private WebElement ErrorMessagePath;

    @FindBy(xpath = "//input[@id ='to']")
    private WebElement returnDate;

    @FindBy(xpath = "(//table[contains(@class,'datepicker-calendar')]//a[contains(text(),25)])")
    private WebElement returnDateSelection_new4;

    @FindBy(xpath = "//div[@class='res-discFld form-controlD']")
    private WebElement Discount_Codes;

    @FindBy(xpath = "(//input[@id='coupon'])[1]/following-sibling::span[@class='error']")
    private WebElement CouponErrorText;

    @FindBy(xpath = "//*[@id='awd']")
    private WebElement AWDOrBCDOrPDN_TextField;

    @FindBy(xpath = "//input[@id='partnerMembershipId']")
    private WebElement membershipTextField;

    @FindBy(xpath = "//button[text()='Accept Terms']")
    private WebElement AcceptTermsButton;

    @FindBy(xpath = "//span[text()='Please enter a ']/b[text()='Pick-up Location']")
    private WebElement PickUpLocMissingErrorMsg;

    @FindBy(xpath = "//span[text()='Please enter a valid  ']/b[text()=' Pick-up Date']")
    private WebElement PickUpDateErrorMsg;

    @FindBy(xpath = "(//span[text()='This should be date format'])[1]")
    private WebElement PickUpInvalidDateMsg;

    @FindBy(xpath = "//span[text()='Please enter a valid  ']/b[text()='Return Date']")
    private WebElement ReturnDateErrorMsg;

    @FindBy(xpath = "(//span[text()='This should be date format'])[2]")
    private WebElement ReturnDateInvalidMsg;

    @FindBy(xpath = "(//span[text()='Whoops! Your return time has already passed. Please select a new time.'])[1] | (//span[contains(text(),'Return Date cannot be prior to the Pick-Up Date')])[1]")
    private WebElement ReturnTimeErrorMsg;

    @FindBy(xpath = "(//span[text()='Enter your Wizard Number'])[2]")
    private WebElement AWDBlankErrorMsg;

    @FindBy(xpath = "(//span[text()='Enter your Last Name'])[2]")
    private WebElement LastnameBlankErrorMsg;

    @FindBy(xpath = "(//button[@type='reset'])[1]")
    private WebElement AdPopup;

    @FindBy(xpath = "(//div[contains(@class,'row res-inputFldPrt res-inputFldBack')]/div[4]/div[4]/div[3]/div)|(//div[contains(@class,'res-wizardFld form-controlD')])")
    private WebElement AvisWizardNumberLink;

    @FindBy(id = "res-home-wizNum")
    private WebElement WizardNoTextField;

    @FindBy(id = "res-home-lastName")
    private WebElement LastNameTextField;

    @FindBy(id = "coupon")
    private WebElement CouponTextField;

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

    @FindBy(xpath = "(//input[@name='email'])[1]")
    private WebElement corporatEmailTextField;

    @FindBy(xpath = "//span[text()='Your discount code is invalid '] | //span[text()='Your offer code is invalid ']")
    private WebElement AWDCouponCodeInvalidErrorMsg;

    @FindBy(xpath = "//span[@class='platform-error-message error']")
    private WebElement CorporateEmailIDBlankErrorMsg;

    @FindBy(xpath = "(//span[contains(text(),'Please enter a valid corporate email address.')])[1]")
    private WebElement CorporateEmailIDInvalidErrorMsg;

    @FindBy(xpath = "//span[text()='Based on your age selection, there are no cars available at this location-'] | //span[@class='mainErrorText info-error-msg-text' and @ng-bind-html='error.message | htmlFilter']")
    private WebElement AgeSelectionNocarsErrorMsg;

    @FindBy(xpath = "//button[text()='Confirm My Choices']")
    private WebElement ChoicesPopup;

    @FindBy(xpath = "//button[text()='Accept All Cookies']")
    private WebElement acceptCookies;

    public ReservationWidget(WebDriver driver) {
        super(driver);
        //clickOn(ChoicesPopup);
    }

    /**
     * Method to verify pickup location
     **/
    public ReservationWidget pickUpLocation(String pickUpLocationValue) {
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
    public ReservationWidget dropOffLocation(String dropOffLocationValue) {
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
        waitForVisibilityOfElement(element);
       // element.click();
        clickUsingJS(element);
        element.clear();
        element.sendKeys(location);
        threadSleep(TWO_SECONDS);
    }

    /**
     * Method to select calendar
     **/
    public ReservationWidget calendarSelection() {
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

    public ReservationWidget calendarSelection(int num) {
        helper.scrollBy("-600");
        threadSleep(TWO_SECONDS);
        pickupDate.click();
        pickupDate.clear();
        dropOffLocation.click();
        clickOn(returnDate);
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
    public ReservationWidget calendarSelection(int month,String pickDate,String dropDate) {
        helper.scrollBy("-600");
        threadSleep(TWO_SECONDS);
        pickupDate.click();
        pickupDate.clear();
        dropOffLocation.click();
        clickOn(returnDate);
        returnDate.clear();
        dropOffLocation.click();
        pickupDate.click();

        for (int i = 0; i < month; i++) {
            threadSleep(ONE_SECOND);
            nextMonthSelection.click();
        }
        dateSelect(pickDate);
        threadSleep(THREE_SECONDS);
        dateSelect(dropDate);
       // pickupDateSelection.click();



      //  returnDateSelection5daysGap.click();
        return this;
    }
    /**
     * Method to select calendar
     **/
    public ReservationWidget calendarSelectionSnowChain() {
        helper.scrollBy("-600");
        threadSleep(TWO_SECONDS);
        pickupDate.click();

        int year = Calendar.getInstance().get(Calendar.YEAR);

        Select select = new Select(jumpMonthDropdown);
        select.selectByVisibleText("December " + year);
       /* snowChainPickupDate.click();
        snowChainReturnDate.click();*/
      clickOn(snowChainPickupDate);
        clickOn(snowChainReturnDate);
        return this;
    }

    public ReservationWidget selectCountry(String country) {
        helper.selectValueFromDropDown(selectCountry, country);
        return this;
    }

    public ReservationWidget clickAcceptTermsButton() {
        helper.clickIfElementIsDisplayed(AcceptTermsButton);
        return this;
    }

    public ReservationWidget dropOffTime(String dot) {
        helper.selectValueFromDropDown(dropOffTime, dot);
        return this;
    }

    public ReservationWidget pickUpTime(String dot) {
        helper.selectValueFromDropDown(pickUpTime, dot);
        return this;
    }

    /**
     * Method to click on Home page submit i.e Select my Car Button.
     *
     * @return
     **/

    public ReservationWidget selectMyCar() {
        waitForVisibilityOfElement(selectMyCarButton);
        try {
            clickOn(selectMyCarButton);
            LocationSoldOutErrorText.isDisplayed();
            for(int i=2;i<=4;i++)
            {
                if(LocationSoldOutErrorText.isDisplayed())
                {
                    calendarSelection(i);
                    clickOn(selectMyCarButton);
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

    public ReservationWidget aboveThirtyDaysCalendarSelection(String months) {
        threadSleep(ONE_SECOND);
        dropOffLocation.click(); /*To resolve returnDate click issue first clicked some other locator to make it interactable.*/
        threadSleep(ONE_SECOND);
        returnDate.click();
        threadSleep(TWO_SECONDS);

        for (int i = 1; i <= Integer.parseInt(months); i++) {
            nextMonthSelection.click();
            threadSleep(ONE_SECOND);
            waitForVisibilityOfElement(nextMonthSelection);
        }

        returnDateSelection_new4.click();
        return this;
    }

    public boolean isErrorMessageDisplayed(String months) {
        waitForVisibilityOfElement(ErrorMessagePath);
        String errorMessage = ErrorMessagePath.getText();
        System.out.println(errorMessage);
        if (Integer.parseInt(months) > 11) {
            return errorMessage.contains(ABOVE_THREE_THIRTY_ERROR_MESSAGE);
        }
        return errorMessage.contains(ABOVE_NINETY_ERROR_MESSAGE);
    }

    public boolean VerifyErrorMessageDisplayed(String message) {
        waitForVisibilityOfElement(ErrorMessagePath);
        String errorMessage = ErrorMessagePath.getText();
        return errorMessage.contains(message);
    }

    public ReservationWidget expandDiscountCode() {
        waitForVisibilityOfElement(Discount_Codes);
        //Discount_Codes.click();
        clickOn(Discount_Codes);
        return this;
    }

    public ReservationWidget enterCouponCode(String couponNo) {
        CouponTextField.sendKeys(couponNo);
        return this;
    }

    public ReservationWidget enterAwd(String awd) {
        waitForVisibilityOfElement(AWDOrBCDOrPDN_TextField);
        AWDOrBCDOrPDN_TextField.clear();
        AWDOrBCDOrPDN_TextField.sendKeys(awd, Keys.TAB);
        //event.tabOut("AWDOrBCDOrPDN_TextField");
        return this;
    }

    public ReservationWidget enterMembershipNo(String membershipNo) {
        membershipTextField.sendKeys(membershipNo);
        return this;
    }

    public ReservationWidget enterCorporateEmailId(String corporateEmail) {
        corporatEmailTextField.sendKeys(corporateEmail);
        return this;
    }

    /**
     * Method to expand Avis Wizard number
     **/
    public ReservationWidget expandAvisWizardNumber() {
        AvisWizardNumberLink.click();
        return this;
    }

    public ReservationWidget enterAwdAndLastname(String wizardNo, String lastName) {
        WizardNoTextField.click();
        WizardNoTextField.clear();
        WizardNoTextField.sendKeys(wizardNo,Keys.TAB);
        LastNameTextField.click();
        LastNameTextField.clear();
        LastNameTextField.sendKeys(lastName);
        return this;
    }

    public boolean verifyNullPickUpLocationMessage() {
        return nullPickUpLocationMessageElement.getText().contains("Please enter a Pick-up Location");
    }

    public ReservationWidget selectAge(String age) {
        helper.selectValueFromDropDown(selectAge, age);
        return this;
    }

    public Map<String, String> getCouponErrorMessageAttributes(){
        String errorText = CouponErrorText.getText();
        String errorColor = CouponErrorText.getCssValue("color");
        return new HashMap<String, String>() {{
            put("errorText", errorText);
            put("errorColor", errorColor);
        }};
    }

    public boolean verifyPickUpLocationErrorMessage() {
        return PickUpLocMissingErrorMsg.isDisplayed();
    }

    public boolean verifyAWDCouponcodeInvalidErrorMessage() {
        return AWDCouponCodeInvalidErrorMsg.isDisplayed();
    }

    public boolean verifyPickUpReturnDateInvalidErrorMessage() {
        if(PickUpInvalidDateMsg.isDisplayed() && ReturnDateInvalidMsg.isDisplayed());
        return true;
    }

    public boolean verifyPickUpDateErrorMessage() {
        return PickUpDateErrorMsg.isDisplayed();
    }

    public boolean verifyCorpEmailIDBlankErrorMessage() {
        return CorporateEmailIDBlankErrorMsg.isDisplayed();
    }

    public boolean verifyCorpEmailIDInvalidErrorMessage() {
        return CorporateEmailIDInvalidErrorMsg.isDisplayed();
    }

    public boolean verifyAWDBlankErrorMessage() {
        return AWDBlankErrorMsg.isDisplayed();
    }

    public boolean verifyLastNameBlankErrorMessage() {
        return LastnameBlankErrorMsg.isDisplayed();
    }

    public boolean verifyReturnDateErrorMessage() {
        return ReturnDateErrorMsg.isDisplayed();
    }

    public boolean verifyReturnTimeErrorMessage() {
        return ReturnTimeErrorMsg.isDisplayed();
    }

    public boolean isAgeSelectionNocarErrorMsgDisplayed() {
        return AgeSelectionNocarsErrorMsg.isDisplayed();
    }

    public ReservationWidget clearPickUpDateValue() {
        helper.scrollBy("-600");
        threadSleep(TWO_SECONDS);
        pickupDate.click();
        pickupDate.clear();
        pickupDate.sendKeys(Keys.TAB);
        return this;
    }

    public ReservationWidget clearDropOffDateValue() {
        helper.scrollBy("-600");
        threadSleep(TWO_SECONDS);
        returnDate.click();
        returnDate.clear();
        returnDate.sendKeys(Keys.TAB);
        return this;
    }

    public ReservationWidget enterpickUpDropOffDate(String pickDate, String dropDate) {
        helper.scrollBy("-600");
        threadSleep(TWO_SECONDS);
        pickupDate.click();
        pickupDate.clear();
        pickupDate.sendKeys(pickDate);
        dropOffLocation.click();
        returnDate.click();
        returnDate.clear();
        returnDate.sendKeys(dropDate);
        return this;
    }

    public ReservationWidget enterCurrentDatePickUpDropOffDate()
    {
        Date date = new Date();
        String DATE_FORMAT = "MM/dd/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        System.out.println("Today is " + sdf.format(date));
        helper.scrollBy("-600");
        threadSleep(TWO_SECONDS);
        pickupDate.click();
        pickupDate.clear();
        pickupDate.sendKeys(sdf.format(date));
        dropOffLocation.click();
        returnDate.click();
        returnDate.clear();
        returnDate.sendKeys(sdf.format(date));
        return this;
    }

    public ReservationWidget closeAdPopup()
    {
        helper.clickIfElementIsDisplayed(AdPopup);
        return this;
    }


    @Override
    public void isOnPage() {
        log.info("Verify Reservation Widget");
        waitForVisibilityOfElement(pickUpLocation);
    }

    public void dateSelect(String day)
    {
        for(int i=0;i<pickupDateSel.size();i++)
        {
            if(pickupDateSel.get(i).getText().equals(day))
            {
                pickupDateSel.get(i).click();
                break;
            }
        }
    }

    public ReservationWidget acceptCookies()
    {
        try {
            waitForVisibilityOfElement(acceptCookies);
           acceptCookies.click();

            }
        catch(Exception e)
        {
            clickUsingJS(acceptCookies);
            log.info("Accept Cookies not clicked  ");
        }
        return this ;
    }
}
