package com.avis.qa.pages;

import com.avis.qa.core.AbstractBasePage;
import com.avis.qa.utilities.CommonUtils;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import static com.avis.qa.utilities.CommonUtils.*;
import static org.testng.Assert.assertFalse;


/**
 * This class contains all the elements and actions performed on Extras Page
 *
 * @author ikumar
 */
@Log4j2
public class Extras extends AbstractBasePage {

    @FindBy(xpath = "//button[contains(@ng-click,'vm.review()')]")
    private WebElement submitStep3;

    @FindBy(xpath = "(//label[@for='RSNchk'])[1]")
    private WebElement TieredBundle;

    @FindBy(xpath = "//input[@id='Protection & Safety PackagepaiChk']")
    private WebElement ProtectionSafetyTireBundle;


    @FindBy(id = "Step3-Services")
    private WebElement Step3ServicesTab;

    @FindBy(xpath = "//a[text()='Discount Packages']")
    private WebElement Step3DiscountPackages;


    @FindBy(xpath = "//span[text()='Discount Code Savings']")
    private WebElement DiscountCodeSaving;

    @FindBy(xpath = "(//span[@class='pull-left'])[2]")
    private WebElement currencySymbol;

    @FindBy(xpath = "//p[contains(text(),'Curbside Drop Off')]")
    private WebElement CurbsideDropoffText;

    @FindBy(xpath = "//p[text()='Certain extras are included or discounted due to your provided AWD number.']")
    private WebElement ExtrasIncludedText;

    @FindBy(xpath = "//a[@id='Step3-Protections & Coverages']")
    private WebElement ProtectionAndCoveragesTab;

    @FindBy(xpath = "//input[@id='CDWchk']")
    private WebElement LDWCheckbox;

    public Extras(WebDriver driver) {
        super(driver);
    }

    public ReviewAndBook Step3Submit() {
        threadSleep(TWO_SECONDS);
        helper.scrollToElement(submitStep3);
        clickUsingJS(submitStep3);
        return new ReviewAndBook(driver);
    }

    public boolean isSubmitStepThreeDisplayed() {
        return submitStep3.isDisplayed();
    }

    public Extras selectTierBundle() {
        waitForVisibilityOfElement(Step3DiscountPackages).click();
        if (helper.isElementDisplayed(ProtectionSafetyTireBundle))
            clickUsingJS(ProtectionSafetyTireBundle);
        CommonUtils.threadSleep(TWO_SECONDS);
        return this;
    }

    public boolean isDiscountCodeSavingtextDisplayed() {
        return DiscountCodeSaving.isDisplayed();
    }

    public boolean verifyCurrencySymbolDisplayed() {
        return currencySymbol.getText().contains("C$");
    }

    public boolean isExtrasIncludedTextDisplayed() {
        return ExtrasIncludedText.isDisplayed();
    }

    public void verifyCurbsideNotDisplayed(){
        waitForVisibilityOfElement(Step3ServicesTab).click();
        //waitForVisibilityOfElement(CurbsideDropoffText);
        //Assert.assertTrue(!CurbsideDropoffText.isDisplayed());
        try {
             CurbsideDropoffText.isDisplayed();
             Assert.assertFalse(true,"Curbside Drop off text not present");
        }
        catch(Exception e) {
            System.out.println("Curbside Drop off text not present");
            Assert.assertFalse(false,"Curbside Drop off text present");
        }
    }

    public boolean verifyLossDamageWaiverIsSelected() {
        waitForVisibilityOfElement(ProtectionAndCoveragesTab).click();
        return LDWCheckbox.isSelected();
    }

    public Extras ClickLDWCoverage() {
        waitForVisibilityOfElement(ProtectionAndCoveragesTab).click();
        CommonUtils.threadSleep(ONE_SECOND);
        LDWCheckbox.click();
        CommonUtils.threadSleep(TWO_SECONDS);
        return this;
    }

    @Override
    public void isOnPage() {
        log.info("Verify Extras Page");
        waitForVisibilityOfElement(submitStep3);
    }
}
