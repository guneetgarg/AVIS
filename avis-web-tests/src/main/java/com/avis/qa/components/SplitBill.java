package com.avis.qa.components;

import com.avis.qa.core.AbstractBasePage;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


/**
 * This class contains all the elements and actions performed on SplitBill
 *
 * @author ikumar
 */
@Log4j2
public class SplitBill extends AbstractBasePage {

    @FindBy(xpath = "//a[contains(@ng-click,'vm.splitSelected()')]")
    private WebElement SplitOption;


    public SplitBill(WebDriver driver) {
        super(driver);
    }

    public SplitBill optForSplit() {
        clickUsingJS(waitForVisibilityOfElement(SplitOption));
        return this;
    }

    @Override
    public void isOnPage() {
        waitForVisibilityOfElement(SplitOption);
    }
}
