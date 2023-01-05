package com.avis.qa.pages.paylesscar;

import com.avis.qa.core.AbstractBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class Validations extends AbstractBasePage {

    public Validations(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "(//div[@class='location-info'])[1] | (//div[@class='summary-location'])[1]")
    private WebElement pickUpLocation;

    @FindBy(xpath = "(//div[@class='location-info'])[2]")
    private WebElement dropOffLocation;

    @FindBy(xpath = "//li[@ng-if='vm.response.reservationSummary.awdNumber']/span[2] | //p[contains(@ng-if,'vm.confirmation.reservationSummary.awdNumber')]")
    private WebElement pdn;

    @FindBy(xpath = "//li[@ng-if='vm.response.reservationSummary.coupon.number']/span[2] | //span[@class='coupon-value']")
    private WebElement couponCode;

    @FindBy(xpath = "(//div[@class='vehicle-name'])[1] | //p[@class='car-name']")
    private WebElement vehicleName;

    @FindBy(xpath = "(//div[@class='day-time-info'])[1] | (//div[@class='summary-time'])[1]")
    private WebElement pickUpDate;

    @FindBy(xpath = "(//div[contains(div/text(),'Your Information')]//child::p)")
    private List<WebElement> userInfoList;

    public Validations verifyPickUpLocation(String pickUpLoc){
        assertTrue(pickUpLocation.getText().contains(pickUpLoc));
        return this;
    }

    public Validations verifyDropOffLocation(String dropOffLoc){
        assertTrue(dropOffLocation.getText().contains(dropOffLoc));
        return this;
    }

    public Validations verifyCouponCode(String coupon){
        assertTrue(couponCode.getText().contains(coupon));
        return this;
    }

    public Validations verifyPdnNumber(String pdnNo){
        assertTrue(pdn.getText().contains(pdnNo));
        return this;
    }

    public Validations verifyVehicleName(){
        assertTrue(vehicleName.getText().contains("Economy"));
        return this;
    }

    public Validations verifyPickUpDate(){
        String expectedDate = getDate(1);
        String actualDate = dateFormatter(pickUpDate.getText());

        assertTrue(expectedDate.contains(actualDate));
        return this;

    }

    public void verifyCustomerInfo(String email, String age, String residence){

    }

    public String getDate(int noOfDays){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, noOfDays);

        Date date = calendar.getTime();
        return String.valueOf(date);
    }

    public Validations verifyPayNowUserInfo(String email){
        assertTrue(userInfoList.get(0).getText().contains(email));
        assertTrue(userInfoList.get(1).getText().contains("US"));
        assertTrue(userInfoList.get(2).getText().contains("AmericanExpress"));
        return this;
    }

    public Validations verifyUserEmail(){
        assertTrue(userInfoList.get(0).getText().contains("paylessautomation"));
        return this;
    }

    public Validations verifyPayLaterUserInfo(String email, String country){
        assertTrue(userInfoList.get(0).getText().contains(email));
        switch (country){
            case "Thailand":
                assertTrue(userInfoList.get(1).getText().contains("TH"));
                break;
            default:
                assertTrue(userInfoList.get(1).getText().contains("US"));
        }

        return this;
    }

    public String dateFormatter(String date){
        String[] dateArr = date.split(",");

        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < dateArr.length-1; i++) {
            sb.append(dateArr[i]);
        }

        return sb.toString();

    }

    @Override
    public void isOnPage() {

    }
}
