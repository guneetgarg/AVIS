package com.avis.qa.pages;

import com.avis.qa.core.AbstractBasePage;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

@Log4j2
public class Login extends AbstractBasePage{

    @FindBy(id = "avis-logo")
    private WebElement avisLogo;

    @FindBy(xpath = "//a[contains(.,'Forgot password?')]")
    private WebElement ForgotPasswordLink;

    @FindBy(name = "firstname")
    private WebElement FirstNameTextBox;

    @FindBy(name = "lastname")
    private WebElement LastNameTextBox;

    @FindBy(id = "email")
    private WebElement EmailTextBox;

    @FindBy(id = "zip")
    private WebElement ZipTextBox;

    @FindBy(xpath = "//button[text()='Look Up']")
    private WebElement LookUpButton;

    @FindBy(id = "login")
    private WebElement YopMailEMailTextBox;

    @FindBy(xpath = "//button[contains(@title,'Check Inbox')]")
    private WebElement YopMailNextButton;

    @FindBy(xpath = "//span[text()='Avis <avis@e.avis.com>']")
    private WebElement YopMailInboxLabel;

    @FindBy(xpath = "//button[contains(span/text(), 'Delete')]")
    private WebElement YopMailDeleteButton;

    @FindBy(xpath = "//span[contains(.,'A link to reset your password has been sent to')]")
    private WebElement PasswordResetMessage;

    @FindBy(id = "ifmail")
    private WebElement EmailIframe;

    @FindBy(id = "refresh")
    private WebElement YopMailRefreshButton;

    public Login(WebDriver driver) {
        super(driver);
    }

    public Login clickForgotPassword(){
        ForgotPasswordLink.click();
        return this;
    }

    public Login enterResetPasswordDetails(String firstName, String lastName, String email, String zip){
        FirstNameTextBox.sendKeys(firstName);
        LastNameTextBox.sendKeys(lastName);
        EmailTextBox.sendKeys(email);
        ZipTextBox.sendKeys(zip);
        LookUpButton.click();
        waitForVisibilityOfElement(PasswordResetMessage);

        return this;
    }

    public Login checkMailInbox(String email) throws InterruptedException {
        int retry = 1;
        driver.get("https://yopmail.com/en/wm");
        YopMailEMailTextBox.sendKeys(email);
        YopMailNextButton.click();
        driver.switchTo().frame("ifmail");
        while(true) {
            try {
                waitForVisibilityOfElement(YopMailInboxLabel);
                YopMailDeleteButton.click();
                break;
            } catch (Exception e) {
                if (retry != 5) {
                    retry = retry+1;
                    YopMailRefreshButton.click();
                    TimeUnit.SECONDS.sleep(3);
                }
                else {
                    break;
                }
            }
        }

        return this;
    }

    @Override
    public void isOnPage() {
        waitForVisibilityOfElement(avisLogo);
    }

}
