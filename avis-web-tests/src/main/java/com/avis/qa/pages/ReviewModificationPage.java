package com.avis.qa.pages;

import com.avis.qa.core.AbstractBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.avis.qa.utilities.CommonUtils.*;

/**
 * This class contains all the elements and actions performed on Review Modification Page
 *
 * @author AJain
 */

public class ReviewModificationPage extends AbstractBasePage {
    @FindBy(xpath = "//button[text()='Cancel Modifications']")
    private WebElement CancelModificationButton;

    @FindBy(xpath = "//button[text()='Keep Modifications']")
    private WebElement KeepModificationButton;

    @FindBy(xpath = "//h1[text()='Modify Reservation - Review & Confirm']")
    private WebElement HeaderText;

    @FindBy(xpath = "//strong[text()='Changes in Red Below' or text()='Changes in Orange Below']")
    private WebElement ChangesInRedBelowText;

    @FindBy(xpath = "//strong[text()='ORIGINAL']")
    private WebElement OriginalText;

    @FindBy(xpath = "//strong[text()='MODIFIED']")
    private WebElement ModifiedText;


    public ReviewModificationPage(WebDriver driver) {
        super(driver);
    }
    public Confirmation clickKeepModificationButton() {
        helper.scrollToElement(KeepModificationButton);
        clickUsingJS(waitForVisibilityOfElement(KeepModificationButton));
        //waitForVisibilityOfElement(KeepModificationButton);
      // KeepModificationButton.click();
        return new Confirmation(driver);
    }


    public boolean isCancelModificationButtonEnabled() {
        helper.scrollToElement(CancelModificationButton);
        return CancelModificationButton.isEnabled();
    }

    public boolean isHeaderTextDisplayed() {
        return HeaderText.isDisplayed();
    }

    public boolean isChangesInRedTextDisplayed() {
        return ChangesInRedBelowText.isDisplayed();
    }

    public boolean isOriginalTextDisplayed() {
        return OriginalText.isDisplayed();
    }

    public boolean isModifiedTextDisplayed() {
        return ModifiedText.isDisplayed();
    }

    @Override
    public void isOnPage() {
        waitForVisibilityOfElement(HeaderText);
    }
}
