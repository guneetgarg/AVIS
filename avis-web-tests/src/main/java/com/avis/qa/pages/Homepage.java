package com.avis.qa.pages;

import com.avis.qa.core.AbstractBasePage;
import com.avis.qa.core.AbstractBasePageProxy;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.avis.qa.core.Configuration.*;
import static com.avis.qa.utilities.CommonUtils.ONE_SECOND;
import static com.avis.qa.utilities.CommonUtils.threadSleep;


/**
 * This class contains all the elements and actions performed on HomePage
 *
 * @author ikumar
 */
@Log4j2
public class Homepage extends AbstractBasePageProxy {

    @FindBy(xpath = "//a[@class='navbar-brand']")
    private WebElement avisLogo;

    @FindBy(xpath = "//div[@class='bx-wrap']")
    private WebElement AdOverLayDiv;

    @FindBy(xpath = "//div[@class='bx-wrap']//button[@data-click='close']")
    private WebElement AdOverLayCloseButton;

    @FindBy(xpath = "(//a[contains(text(),'Locations')])[1]")
    private WebElement LocationsMenu;

    @FindBy(xpath = "//a[contains(text(),'Find a Location')] | //a[text()='US']")
    private WebElement FindALocationSubMenu;

    @FindBy(xpath = "(//a[@class='welcome-menu-trigger welcome'])[2]")
    private WebElement ProfileWelcomeOverlay;

    @FindBy(xpath = "//button[contains(text(),'My Profile')]")
    private WebElement MyProfileButton_Overlay;

    @FindBy(xpath = "(//li[contains(@class,'welcome-menu')]//a[contains(text(),'Hi')])|(//li[contains(@class,'welcome-menu')]//a[contains(text(),'Welcome')])[1]")
    private WebElement WelcomeOverLay;

    public Homepage(WebDriver driver) {
        super(driver);
    }


    public boolean isAvisLogoDisplayed() {
        log.info("Verify Avis Logo displayed on page header");
        return avisLogo.isDisplayed();
    }

    public void findAndCloseAdOverLay() {
        log.info("Close the offer overlay div if displayed");
//        try{
//            AdOverLayDiv = waitForVisibilityOfElement(AdOverLayDiv, 10);
//        }catch (TimeoutException e){
//            return;
//        }
//        AdOverLayCloseButton.click();
        if(BRAND.equals("Budget"))
            waitForVisibilityOfElement(AdOverLayCloseButton, 20).click();
    }

    public Locations goToFindALocationPage() {
        clickUsingJS(LocationsMenu);
        clickUsingJS(FindALocationSubMenu);
        return new Locations(driver);
    }

    public void oneClickLandingPage(String url) {
        driver.get(URL.replace("/en/home", url));
        System.out.println(driver.getTitle());
        threadSleep(ONE_SECOND);
    }

    public ProfileDashboard profileDashboardNavigation() {
        threadSleep(ONE_SECOND);
        waitForVisibilityOfElement(WelcomeOverLay);
        helper.mouseHover(WelcomeOverLay);
        waitForVisibilityOfElement(MyProfileButton_Overlay).click();
        return new ProfileDashboard(driver);
    }

    @Override
    public void isOnPage() {
        log.info("Verify Home Page");
        waitForVisibilityOfElement(avisLogo);
    }
}
