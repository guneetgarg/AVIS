package com.avis.qa.components;

import com.avis.qa.core.AbstractBasePage;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.avis.qa.utilities.CommonUtils.*;


/**
 * This class contains all the elements and actions performed on Login Widget
 *
 * @author ikumar
 */
@Log4j2
public class LoginWidget extends AbstractBasePage {

    @FindBy(xpath = "((//a[@id='res-login-profile'])[2]) | ((//a[contains(text(),'Sign In')])[1])")
    private WebElement HeaderLoginButton2;

    @FindBy(xpath = "(//a[contains(text(),'Sign In')])[2]")
    private WebElement HeaderLoginButton;

    @FindBy(xpath = "(//ul[@class='header-secondary']//li//a[@id='res-login-profile'])[1]")
    private WebElement HeaderLoginButtonBudget;

    @FindBy(id = "username")
    private WebElement UserName;

    @FindBy(id = "password")
    private WebElement Password;

    @FindBy(id = "res-login-profile")
    private WebElement LoginButton;

    @FindBy(id = "PicLoc_value")
    private WebElement pickUpLocation;

    @FindBy(xpath = "//span[@class='close-FC g-icon']")
    public WebElement PopupBudget;

    @FindBy(xpath = "//input[@name='otp']")
    private WebElement OTPtextBox;

    @FindBy(xpath = "//button[@id='otp_submit']")
    private WebElement OTPSubmitButton;

    public LoginWidget(WebDriver driver) {
        super(driver);
    }
    public LoginWidget loginHeaderclick() {
        if (driver.getCurrentUrl().contains("avis")){
            clickUsingJS(waitForVisibilityOfElement(HeaderLoginButton));}
        else {
            clickUsingJS(waitForVisibilityOfElement(HeaderLoginButtonBudget));
        }
        return this;
    }

    public LoginWidget login(String emailNew, String passwordNew) {
        waitForVisibilityOfElement(UserName);
        UserName.sendKeys(emailNew);
        waitForVisibilityOfElement(Password);
        Password.sendKeys(passwordNew);
        threadSleep(ONE_SECOND);
       // waitForVisibilityOfElement(LoginButton);
        threadSleep(TWO_SECONDS);
        clickUsingJS(LoginButton);
        threadSleep(FIVE_SECONDS);
        threadSleep(TWO_SECONDS);
        threadSleep(TWO_SECONDS);
        if(helper.isElementDisplayed(PopupBudget)){
            PopupBudget.click();
        }
        return this;
    }

    public LoginWidget EnterOTP(String OTP) {
        waitForVisibilityOfElement(OTPtextBox);
        OTPtextBox.sendKeys(OTP);
        waitForVisibilityOfElement(OTPSubmitButton);
        OTPSubmitButton.click();
        return this;
    }

    public ReservationWidget clickPickupLocation() {
        threadSleep(TWO_SECONDS);
        waitForVisibilityOfElement(pickUpLocation).click();
        return new ReservationWidget(driver);
    }

    public static String getOtp(String baseUrl, String endPoint) throws InterruptedException {



        baseUrl = "https://www.receivesms.co/";

        endPoint = "us-phone-number/3411/";

        Response response = RestAssured.get(baseUrl + endPoint);
        threadSleep(3000);


        String otp =response.asString().split("Your AVIS verification code is")[1].split("data-clipboard-text=\"")[1].split("\"><b>")[0];

        System.out.println(otp);

        return otp;

    }


    @Override
    public void isOnPage() {
        log.info("Verify Login Widget");
        if (driver.getCurrentUrl().contains("avis")){
        waitForVisibilityOfElement(HeaderLoginButton);}
        else {
            waitForVisibilityOfElement(HeaderLoginButtonBudget);
        }
    }
}
