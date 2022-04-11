package com.avis.qa.pages;

import com.avis.qa.core.AbstractBasePage;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


/**
 * This class contains all the elements and actions performed on Profile Dashboard
 *
 * @author ikumar
 */
@Log4j2
public class ProfileDashboard extends AbstractBasePage {

    @FindBy(id = "airlines")
    private WebElement PartnerField;

    @FindBy(xpath = "//input[@name='Partner']")
    private WebElement MemebershipField;

    @FindBy(xpath = "//a[contains(text(),'Rewards')]")
    private WebElement RewardsTab;

    @FindBy(xpath = "//a[contains(text(),'Edit')]")
    private WebElement RewardsProgramEditButton;

    @FindBy(xpath = "//button[contains(@ng-disabled,'vm.disableSaveButton')]")
    private WebElement SaveButton;


    public ProfileDashboard(WebDriver driver) {
        super(driver);
    }

    public ProfileDashboard navigateToRewardsTab() {
        RewardsTab.click();
        return this;
    }

    public ProfileDashboard clickOnEditRewards() {
        RewardsProgramEditButton.click();
        return this;

    }

    public ProfileDashboard enterPartnerNameAndNo(String partnerName, String membershipNo) {
        //helper.selectValueFromDropDown(PartnerField,14);
        PartnerField.sendKeys(partnerName);
        MemebershipField.sendKeys(membershipNo);
        return this;
    }

    public ProfileDashboard clickOnSaveButton() {
        SaveButton.click();
        return this;
    }

    @Override
    public void isOnPage() {
        log.info("Verify Profile Dashboard");
        waitForVisibilityOfElement(RewardsTab);
    }
}
