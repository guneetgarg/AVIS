package com.avis.qa.pages;

import com.avis.qa.core.AbstractBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.avis.qa.utilities.CommonUtils.*;

/**
 * This class contains all the elements and actions performed on Paypal Page
 *
 * @author AJain
 */

public class PayPalPage extends AbstractBasePage {

    @FindBy(xpath = "//input[@id='email']")
    private WebElement emailTextField;

    @FindBy(xpath = "//button[@id='btnNext']")
    private WebElement NextButton;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordTextField;

    @FindBy(xpath = "//button[@id='btnLogin']")
    private WebElement LoginButton;

    @FindBy(xpath = "//button[@id='consentButton']")
    private WebElement AgreeAndContinueButton;

    @FindBy(xpath = "//a[contains(@id, 'bx-close-inside')]")
    private WebElement cancelPopup;

    public PayPalPage(WebDriver driver) {
        super(driver);
    }

    public PayPalPage enterEmail(String email) {
        threadSleep(THREE_SECONDS);
        waitForVisibilityOfElement(emailTextField);
        emailTextField.click();
        emailTextField.sendKeys(email);
        return this;
    }
    public PayPalPage ClickNext() {
        NextButton.click();
        return this;
    }

    public PayPalPage enterPassword(String pwd) {
        waitForVisibilityOfElement(passwordTextField);
        passwordTextField.click();
        passwordTextField.sendKeys(pwd);
        return this;
    }

    public PayPalPage ClickLogin() {
        LoginButton.click();
        return this;
    }

    public PayPalPage ClickAgreeAndContinueButton() {
        waitForVisibilityOfElement(AgreeAndContinueButton);
        helper.scrollToElement(AgreeAndContinueButton);
        clickUsingJS(AgreeAndContinueButton);
        return this;
    }


    @Override
    public void isOnPage() {
        try{
            clickOn(cancelPopup);
        } catch(Exception e) {
            e.printStackTrace();
        }
        waitForVisibilityOfElement(emailTextField);
    }
}
