package com.avis.qa.pages;


import com.avis.qa.components.ReservationWidget;
import com.avis.qa.core.AbstractBasePage;
import com.avis.qa.core.Configuration;
import com.avis.qa.utilities.CommonUtils;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.avis.qa.utilities.CommonUtils.threadSleep;

/**
 * This class contains all the elements and actions performed on Locations Page
 *
 * @author ikumar
 */
@Log4j2
public class Locations extends AbstractBasePage {

    @FindBy(id = "PicLoc_value")
    WebElement SearchField;

    @FindBy(xpath = "(//*[contains(@class,'btn browse-CTA-btn ')])[1]")
    WebElement BrowseByCountry;

    @FindBy(xpath = "//*[contains(text(),'Canada & USA')]")
    WebElement CanadaUsaCategory;

    @FindBy(xpath = "(//button[contains(text(),'USA')])[2] | //button[contains(text(),'United States')]")
    WebElement USACategory;

    @FindBy(xpath = "//button[normalize-space()='Canada']")
    WebElement CanadaCareogary;

    @FindBy(xpath = "//button[contains(text(),'United States')]")
    WebElement UnitedStatesCategory;

    @FindBy(xpath = "//button[contains(text(),'Newark')]")
    WebElement NewarkCategory;

    @FindBy(xpath="//button[normalize-space()='Burnaby']")
    WebElement CanadaState;

    @FindBy(xpath = "//button[contains(text(),'New Jersey')]")
    WebElement NewJersyCategory;

    @FindBy(xpath = "//button[normalize-space()='British Columbia']")
    WebElement CanadaLocation;

    @FindBy(xpath = "(//*[contains(text(),'Newark')])[4]")
    WebElement NewarkAirportCategory;

    @FindBy(xpath = "//button[contains(text(),'Abg/parsippany')]")
    WebElement ABGparsiPpanyCategory;

    @FindBy(xpath = "(//a[@title='Newark Liberty Intl Airport 1 car rental'])[1] | (//a[text()=' Make a Reservation'])[1]")
    WebElement makeAReservationButton;

    @FindBy(xpath = "(//a[@title=\"Newark Liberty Intl Airport car rental\"])[1] | (//a[@title=\"Auckland Airport car rental\"])[1]" )
    WebElement makeAReservationButtonBudget;

    @FindBy(xpath = "(//a[@title='Newark Liberty Intl Airport 1 car rental'])[2]")
    WebElement makeAReservationButtonWidget;

    @FindBy(xpath="//li[@id='BrowsepushPin_1']//a[@title='Burnaby MetroPointe car rental'][normalize-space()='Make a Reservation']")
    WebElement makeAReservationButtonWidget_CA;
    
    @FindBy(xpath = "(//a[@title=\"Newark Liberty Intl Airport car rental\"])[2]")
    WebElement makeAReservationButtonBudgetWidget;

    public Locations(WebDriver driver) {
        super(driver);
    }

    // This block for Setting up Webelement object for Canada Site
    {
        if (Configuration.DOMAIN.equals("CA")) {
            USACategory = CanadaCareogary;
            NewJersyCategory = CanadaLocation;
            NewarkCategory = CanadaState;
            makeAReservationButtonWidget = makeAReservationButtonWidget_CA;
        }
    }

    public Locations searchLocation(String pickUpLocation) {
        ReservationWidget reservationWidget = new ReservationWidget(driver);
        reservationWidget.pickUpLocation(pickUpLocation);
        return this;
    }

    public ReservationWidget clickOnMakeAReservationWidget() {
        if(driver.getCurrentUrl().contains("avis")) {
            waitForVisibilityOfElement(makeAReservationButtonWidget);
            makeAReservationButtonWidget.click();
        }
        else
        {
            waitForVisibilityOfElement(makeAReservationButtonBudgetWidget);
            makeAReservationButtonBudgetWidget.click();
        }
        return new ReservationWidget(driver);
    }

    public ReservationWidget clickOnMakeAReservation() {
        if(driver.getCurrentUrl().contains("avis")) {
            waitForVisibilityOfElement(makeAReservationButton);
            makeAReservationButton.click();
        }
        else
        {
            waitForVisibilityOfElement(makeAReservationButtonBudget);
            makeAReservationButtonBudget.click();
        }
        return new ReservationWidget(driver);
    }

    public void browserLocation() {

        threadSleep(CommonUtils.TWO_SECONDS);
        waitForVisibilityOfElement(BrowseByCountry).click();
        threadSleep(CommonUtils.TWO_SECONDS);
        waitForVisibilityOfElement(CanadaUsaCategory).click();
        threadSleep(CommonUtils.TWO_SECONDS);
        waitForVisibilityOfElement(USACategory).click();
        threadSleep(CommonUtils.TWO_SECONDS);
        waitForVisibilityOfElement(NewJersyCategory).click();
        threadSleep(CommonUtils.TWO_SECONDS);
        waitForVisibilityOfElement(NewarkCategory).click();
        threadSleep(CommonUtils.TWO_SECONDS);

    }

    public boolean isMakeAReservationButtonDisplayed() {
        if (driver.getCurrentUrl().contains("avis"))
            return  makeAReservationButtonWidget.isDisplayed();
        return makeAReservationButtonBudgetWidget.isDisplayed();
    }

    @Override
    public void isOnPage() {
        waitForVisibilityOfElement(SearchField);
    }
}
