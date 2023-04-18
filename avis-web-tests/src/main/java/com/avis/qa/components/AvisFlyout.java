package com.avis.qa.components;

import com.avis.qa.core.AbstractBasePage;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Random;


/**
 * This class contains all the elements and actions performed on Avis Flyout
 *
 * @author ikumar
 */
@Log4j2
public class AvisFlyout extends AbstractBasePage {

    @FindBy(xpath = "//button[contains(text(),'Next')]")
    private WebElement FlyoutNextButton;

    @FindBy(xpath = "  //input[contains(@name,'businessname')]|//input[contains(@name,'companyname')]|(//input[contains(@name,'customerCompanyBusinessName')])|(//input[contains(@name,'companyName')])")
    private WebElement smallBusinessName;

    @FindBy(xpath = "//input[contains(@name,'strAddress1')]|//input[contains(@name,'customerCompanyAddress1')]|(//input[contains(@name,'address1')])")
    private WebElement smallBusinessAddress1;

    @FindBy(id = "city")
    private WebElement smallBusinessCity;

    @FindBy(id = "state")
    private WebElement smallBusinessState;

    @FindBy(id = "zip")
    private WebElement smallBusinessZipCode;

    @FindBy(xpath = "//button[contains(text(),'Enroll & Save ')]|//button[contains(text(),'Get My Savings')]")
    private WebElement smallBusinessSubmit;

    @FindBy(xpath = "//p[@class='border-box font-abg-demi']")
    private WebElement smallBizAWD;


    public AvisFlyout(WebDriver driver) {
        super(driver);
    }

    public AvisFlyout clickOnNextButton() {
        helper.scrollToElement(FlyoutNextButton);
        FlyoutNextButton.click();
        return this;
    }

    public boolean enterCompanyInformationAndVerifyAWDLength(String companyName, String address1, String city, String province,
                                                             String zipcode, String country) {
        companyName = companyName + new Random().nextInt();
        smallBusinessName.sendKeys(companyName);
        smallBusinessAddress1.sendKeys(address1);
        smallBusinessCity.sendKeys(city);
        helper.implicitWait();
        helper.selectValueFromDropDown(smallBusinessState, province);
        smallBusinessZipCode.sendKeys(zipcode);
        smallBusinessSubmit.click();

        waitForVisibilityOfElement(smallBizAWD).click();
        String awd = smallBizAWD.getText();

        return awd.length() == 7;
    }

    @Override
    public void isOnPage() {
        log.info("Verify Flyout Component");
        waitForVisibilityOfElement(FlyoutNextButton);
    }
}
