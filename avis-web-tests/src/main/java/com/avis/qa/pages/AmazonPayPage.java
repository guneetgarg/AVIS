package com.avis.qa.pages;

import com.avis.qa.core.AbstractBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static com.avis.qa.utilities.CommonUtils.*;

/**
 * This class contains all the elements and actions performed on AmazonPay Page
 *
 * @author AJain
 */

public class AmazonPayPage extends AbstractBasePage {

    @FindBy(xpath = "//input[@id='ap_email']")
    private WebElement emailTextField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement SignInButton;

    @FindBy(xpath = "//input[@id='ap_password']")
    private WebElement passwordTextField;



    public AmazonPayPage(WebDriver driver) {
        super(driver);
    }

    public AmazonPayPage enterEmail(String email) {
        threadSleep(THREE_SECONDS);
        waitForVisibilityOfElement(emailTextField);
        emailTextField.click();
        emailTextField.sendKeys(email);
        return this;
    }

    public AmazonPayPage enterPassword(String pwd) {
        waitForVisibilityOfElement(passwordTextField);
        passwordTextField.click();

        passwordTextField.sendKeys(pwd);
        return this;
    }

    public AmazonPayPage ClickLogin() {
        SignInButton.click();
        return this;
    }

    @Override
    public void isOnPage() {
        //waitForVisibilityOfElement(passwordTextField);
    }

}
