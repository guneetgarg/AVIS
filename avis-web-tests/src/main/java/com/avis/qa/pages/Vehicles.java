package com.avis.qa.pages;

import com.avis.qa.core.AbstractBasePage;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.avis.qa.constants.TextComparison.KEY_DROP_LOCATION_MESSAGE;
import static com.avis.qa.utilities.CommonUtils.TWO_SECONDS;
import static com.avis.qa.utilities.CommonUtils.threadSleep;
import static org.testng.Assert.assertEquals;


/**
 * This class contains all the elements and actions performed on Vehicles Page
 *
 * @author ikumar
 */
@Log4j2
public class Vehicles extends AbstractBasePage {

    @FindBy(xpath = "(//a[@id='res-vehicles-pay-now'])[1]")
    private WebElement step2PayNowSubmitButton;

    @FindBy(xpath = "(//a[contains(text(),'Select')])[4]|(//a[contains(text(),'Select')])[3]|(//a[@id='res-vehicles-pay-later'])[1] | (//a[contains(text(),'Pay Later')])[2]|(//a[contains(text(),'Pay at Counter')])[1]|(//a[contains(text(),'Paiement au comptoir')])[1]|(//a[contains(text(),'Pagar en el mostrador')])[1]|((//p[contains(@class,'totalPay')]/../a)[1])")
    private WebElement step2SubmitButton;

    @FindBy(css = "li[title='Select a Car']")
    private WebElement selectACarText;

    @FindBy(xpath = "(//a[contains(text(),'Pay Later')])[1]|(//a[contains(text(),'Pay at Counter')])[4]|(//a[contains(text(),'Select')])[4]|(//a[contains(text(),'Paiement au comptoir')])[1]|(//a[contains(text(),'Pagar en el mostrador')])[1]|((//p[contains(@class,'totalPay')]/../a)[4]|(//a[contains(text(),'Select')])[4])")
    private WebElement payAtCounter2;

    @FindBy(xpath = "(//li[@ng-click='vm.selectCurrency(option)']/a)[1]")
    private WebElement Currencyvalue1;

    @FindBy(xpath = "(//li[@ng-click='vm.selectCurrency(option)']/a)[2]")
    private WebElement Currencyvalue2;

    @FindBy(xpath = "//a[contains(@class,'dropdown-toggle vehAllVehDrpDwn s-dropdown currencyF&S')]")
    private WebElement currencyField;

    @FindBy(xpath = "//div[contains(@class,'info-error-msg-text')]/span")
    private WebElement underAgeMsg_Step2;

    @FindBy(xpath = "(//del[@ng-if='car.displayRate.displayStkPayLater'])[1]|(//p[@ng-if='car.displayRate.displayStkSelect'])[1]")
    private WebElement StrikreThroughPriceIndicator;

    @FindBy(xpath = "//div[contains(@class,'info-key-drop-text')]/p")
    private WebElement keyDropInfo;

    @FindBy(xpath = "//a[@class='discount-dropdown s-dropdown']")
    private WebElement discountDropdown;

    @FindBy(xpath = "(//a[@id='res-vehicles-pay-later'])[1]")
    private WebElement DiscountAppliedVehiclePaylaterButton;

    @FindAll(@FindBy(xpath = "//p[@class='striked-text']/following-sibling::a"))
    List<WebElement> allDiscountedVehiclePaylaterButton;

    @FindBy(xpath = "//span[text()='Your USAA savings are reflected below.']")
    private WebElement USAASavingText;

    @FindBy(xpath = "//span[@class='addinlinfo']")
    private WebElement Step2SavingText;


    @FindBy(xpath = "//span[text()='Your savings are reflected below.']")
    private WebElement savingText;

    @FindBy(xpath = "(//p[@class='payamntp']/span)[1]")
    private WebElement currencySymbol;

    @FindBy(xpath = "//input[@name='res.coupon.couponNumber']")
    private WebElement CouponValue;

    @FindBy(xpath = "//section[@class='infoText']")
    private WebElement CouponSavingText;

    public Vehicles(WebDriver driver) {
        super(driver);
    }


    public Extras step2Submit() {
        threadSleep(TWO_SECONDS);
        helper.scrollToElement(step2SubmitButton);
        threadSleep(TWO_SECONDS);
        clickUsingJS(step2SubmitButton);
        return new Extras(driver);
    }

    public Extras Step2_DiscountAppliedSubmit() {
        threadSleep(TWO_SECONDS);
        helper.scrollToElement(DiscountAppliedVehiclePaylaterButton);
        clickUsingJS(DiscountAppliedVehiclePaylaterButton);
        return new Extras(driver);
    }
    public Extras Step2_ClickDiscountAppliedSubmit() {
    int totalDiscountedvehicles= allDiscountedVehiclePaylaterButton.size();
    if(totalDiscountedvehicles == 0)
    {
        log.info("No discounted vehicle is available");
    }
    else if(totalDiscountedvehicles >= 1) {
        threadSleep(TWO_SECONDS);
        WebElement payLater = allDiscountedVehiclePaylaterButton.get(1);
        helper.scrollToElement(payLater);
        clickUsingJS(payLater);
    }
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

    public Extras payLater() {
        payAtCounter2.click();
        return new Extras(driver);
    }

    /**
     * Method on click on Currency Label
     *
     * @return
     */
    public boolean isCurrencyValueDisplayed() {
        clickUsingJS(currencyField);
        return Currencyvalue2.isDisplayed() && Currencyvalue1.isDisplayed();
    }

    public boolean
    verifyCurrencySymbolDisplayed() {
        return currencySymbol.getText().contains("C$");
    }

    public boolean verifyCouponSavingtextDisplayed(String CouponMsg)
    {
        System.out.println("Coupon text"+CouponSavingText.getText());
        return CouponSavingText.getText().contains(CouponMsg);
    }

    public boolean isStrikreThroughPriceIndicatorDisplayed() {
        return StrikreThroughPriceIndicator.isDisplayed();
    }

    public Vehicles verifyUnderAgeMsg() {
        underAgeMsg_Step2.getText().contains("An underage surcharge is applicable except at participating locations.");
        return this;
    }

    public Vehicles keyDropMessageValidation() {
        String actualMessage = keyDropInfo.getText();
        assertEquals(actualMessage, KEY_DROP_LOCATION_MESSAGE);
        return this;
    }

    public Vehicles DiscountDropDownClick() {
        discountDropdown.click();
        return this;
    }

    public Vehicles isCouponvalueDisplayed(String Couponcode) {
        String actualCoupon = CouponValue.getText();
        assertEquals(actualCoupon, Couponcode);
        return this;
    }


    public boolean isDiscountDropdownDisplayed() {
        return discountDropdown.getText().contains("Lower Rates/This AWD");
    }

    public boolean isUSAACoupontextDisplayed() {
        return USAASavingText.isDisplayed();
    }

    public boolean savingMessageValidation(String message) {
      //  String actualMessage = Step2SavingText.getText();
     //   assertEquals(actualMessage, message);
        System.out.println("Coupon text"+Step2SavingText.getText());
        return Step2SavingText.getText().contains(message);
        //return this;
    }


    public boolean isSavingtextDisplayed() {
        return savingText.isDisplayed();
    }

    @Override
    public void isOnPage() {
        waitForVisibilityOfElement(selectACarText);
    }
}
