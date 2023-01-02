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

    @FindBy(xpath = "//a[contains(.,'Welcome')]")
    private WebElement ProfileButton;

    @FindBy(xpath = "//a[contains(.,'My Profile')]")
    private WebElement MyProfileButton;

    @FindBy(xpath = "//a[contains(text(),'My Rentals')]")
    private WebElement MyRentalsTab;

    @FindBy(xpath = "//h2[text()='Upcoming Rentals']")
    private WebElement UpcomingRentalsTitle;

    @FindBy(xpath = "//h2[text()='Past Rentals']")
    private WebElement PastRentalsTitle;

    @FindBy(xpath = "//h1[text()='Rewards']")
    private WebElement RewardsTitle;

    @FindBy(xpath = "//h2[text()='Avis Card']")
    private WebElement AvisCardTitle;

    @FindBy(xpath = "//span[contains(.,'Avis Preferred Points')]")
    private WebElement AvisPreferredPointsTitle;

    @FindBy(xpath = "//a[contains(text(),'Preferences')]")
    private WebElement PreferenceTab;

    @FindBy(xpath = "//h2[contains(.,'Protections & Coverages')]")
    private WebElement ProtectionsAndCoveragesTitle;

    @FindBy(xpath = "//h2[contains(.,'Discount Codes')]")
    private WebElement DiscountCodesTitle;

    @FindBy(xpath = "//h2[contains(.,'Car Preferences')]")
    private WebElement CarPreferencesTitle;

    @FindBy(xpath = "//h2[contains(.,'Travel Preferences')]")
    private WebElement TravelPreferencesTitle;

    @FindBy(xpath = "//h2[contains(.,'Site Preferences')]")
    private WebElement SitePreferencesTitle;

    @FindBy(xpath = "//a[contains(text(),'About Avis Preferred')]")
    private WebElement AboutTab;

    @FindBy(xpath = "//h1[contains(.,'About Avis Preferred')]")
    private WebElement AboutAvisTitle;

    @FindBy(xpath = "//h2[contains(.,'Take The Preferred Route')]")
    private WebElement TakeThePreferredRouteTitle;

    @FindBy(xpath = "//h3/a[contains(.,'Privacy Policy')]")
    private WebElement PrivacyPolicyTab;

    @FindBy(xpath = "//a[text()='Terms and Conditions']")
    private WebElement TermsAndConditionsTab;

    @FindBy(xpath = "//a[contains(text(),'Earn Points')]")
    private WebElement EarnPointsButton;

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


    public ProfileDashboard clickProfile(){
        waitForVisibilityOfElement(ProfileButton).click();
        MyProfileButton.click();
        return this;

    }

    public ProfileDashboard verifyMyRentalTab(){
        MyRentalsTab.click();
        UpcomingRentalsTitle.isDisplayed();
        PastRentalsTitle.isDisplayed();
        return this;
    }

    public ProfileDashboard verifyRewardsTab(){
        RewardsTab.click();
        RewardsTitle.isDisplayed();
        AvisCardTitle.isDisplayed();
        AvisPreferredPointsTitle.isDisplayed();
        return this;
    }

    public ProfileDashboard verifyPreferenceTab(){
        PreferenceTab.click();
        ProtectionsAndCoveragesTitle.isDisplayed();
        DiscountCodesTitle.isDisplayed();
        CarPreferencesTitle.isDisplayed();
        TravelPreferencesTitle.isDisplayed();
        SitePreferencesTitle.isDisplayed();
        return this;

    }

    public ProfileDashboard verifyAboutTab(){
        AboutTab.click();
        AboutAvisTitle.isDisplayed();
        TakeThePreferredRouteTitle.isDisplayed();
        return this;

    }

    public void clickEarnPointsButton(){
        EarnPointsButton.click();
    }

    public void verifyTermsAndConditionsTab() {
        TermsAndConditionsTab.click();
    }

    public ProfileDashboard verifyPrivacyPolicyTab(){
        PrivacyPolicyTab.click();
        return this;
    }


    @Override
    public void isOnPage() {
        log.info("Verify Profile Dashboard");
        waitForVisibilityOfElement(RewardsTab);
    }
}
