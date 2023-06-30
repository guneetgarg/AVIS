package com.avis.qa.pages;

import com.avis.qa.core.AbstractBasePage;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.avis.qa.constants.AvisConstants.fareCharges;

/**
 * This class contains all the elements and actions performed on Review Modification Page
 *
 * @author AJain
 */
@Log4j2
public class ReviewPreModificationPage extends AbstractBasePage {


    @FindBy(xpath = "//h1[text()='Modify Reservation - Review & Confirm']")
    private WebElement HeaderText;

    @FindBy(xpath="//button[@id='res-conf-print']")
    private WebElement printConfirmation;

    @FindBy(xpath="//div[contains(text(),'Estimated Total')]//ancestor::div[@class='header']//span[@class='total-amount pull-right']")
    private WebElement estimatedTotalAmount;

    @FindBy(xpath="//span[contains(.,'Base Rate')]//following-sibling::div[@class='pull-right ']")
    private WebElement baseRateAmount;

    public ReviewPreModificationPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPrintConfirmationButtonDisplay()
    {
        boolean isButtonDisplay;
        try {
            waitForVisibilityOfElement(printConfirmation);
            isButtonDisplay = printConfirmation.isDisplayed();
        }
        catch (Exception e)
        {
            log.error("Exception in PrintDispaly Button "+e);
            isButtonDisplay=false;
        }
        return  isButtonDisplay;

    }
    public void setBaseRateAndEstimatedTotal()
    {
        waitForVisibilityOfElement(baseRateAmount);
        fareCharges.put("ReviewPreModification-BaseRate",Double.parseDouble(baseRateAmount.getText().replace("$","").replace("US","")));
        waitForVisibilityOfElement(estimatedTotalAmount);
        fareCharges.put("ReviewPreModification-TotalEstimatedValue",Double.parseDouble(estimatedTotalAmount.getText().replace("$","")));
    }
    @Override
    public void isOnPage() {
        waitForVisibilityOfElement(HeaderText);
    }
}
