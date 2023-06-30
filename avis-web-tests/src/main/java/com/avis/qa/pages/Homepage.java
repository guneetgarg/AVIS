package com.avis.qa.pages;

import com.avis.qa.core.AbstractBasePage;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.avis.qa.core.Configuration.URL;
import static com.avis.qa.utilities.CommonUtils.ONE_SECOND;
import static com.avis.qa.utilities.CommonUtils.threadSleep;


/**
 * This class contains all the elements and actions performed on HomePage
 *
 * @author ikumar
 */
@Log4j2
public class Homepage extends AbstractBasePage {

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

    @FindBy(xpath = "(//a[text()='Sign Up'])[2]")
    private WebElement signUpLink;

    @FindBy(xpath = "(//a[@id='res-login-profile'])[2]")
    private WebElement LogInLink;



    @FindBy(xpath = "//span[@class='hidden-xs']/child::p[1]")
    private WebElement FindYourBestCarRentalWithAvisText;

    @FindBy(xpath = "(//a[text()='Reservations'])[1]")
    private WebElement ReservationsTab;


    @FindBy(xpath = "//a[text()='View / Modify / Cancel']")
    private WebElement Reservation_ViewModifyCancelLink;

    @FindBy(xpath = "(//a[text()='Sign In'])[2] | (//a[text()='Log In'])[2]")
    private WebElement LoginLink;

    public Homepage(WebDriver driver) {
        super(driver);
    }


    public boolean isAvisLogoDisplayed() {
        log.info("Verify Avis Logo displayed on page header");
        return avisLogo.isDisplayed();
    }

    public void findAndCloseAdOverLay() {
        log.info("Close the offer overlay div if displayed");
        try{
            AdOverLayDiv = waitForVisibilityOfElement(AdOverLayDiv, 10);
        }catch (TimeoutException e){
            return;
        }
        AdOverLayCloseButton.click();
    }

    public Locations goToFindALocationPage() {
        clickUsingJS(LocationsMenu);
        clickUsingJS(FindALocationSubMenu);
        return new Locations(driver);
    }

    public Homepage clickAvisLogo() {
        clickUsingJS(avisLogo);
        return this;
    }

    public Enrollment clickSignupLink() {
        waitForVisibilityOfElement(signUpLink);
        clickUsingJS(signUpLink);
        return new Enrollment(driver);
    }

    public Enrollment clickLogInLink() {
        waitForVisibilityOfElement(LogInLink);
        clickUsingJS(LogInLink);
        return new Enrollment(driver);
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

    public ReservationViewModifyCancel goToViewModifyCancelPage() {
        clickUsingJS(ReservationsTab);
        clickUsingJS(Reservation_ViewModifyCancelLink);
        return new ReservationViewModifyCancel(driver);
    }

    public void clickLoginLink(){
        waitForVisibilityOfElement(LoginLink);
        clickUsingJS(LoginLink);
    }


    @Override
    public void isOnPage() {
        log.info("Verify Home Page");
        waitForVisibilityOfElement(avisLogo);
        // waitForVisibilityOfElement(FindYourBestCarRentalWithAvisText);
    }
}
