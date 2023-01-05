package com.avis.qa.helpers;

import com.avis.qa.components.ReservationWidget;
import com.avis.qa.core.AbstractBasePage;
import com.avis.qa.pages.*;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static com.avis.qa.constants.AvisConstants.FLIGHT_NAME;
import static com.avis.qa.constants.AvisConstants.FLIGHT_NUMBER;

@Log4j2
public class LocationHelper extends AbstractBasePage {

    private final WebDriver driver;
    private final Homepage homepage;

    public LocationHelper(WebDriver webDriver) {
        super(webDriver);
        this.driver = webDriver;
        this.homepage = new Homepage(webDriver);
    }

    @Override
    public void isOnPage() {
        log.info("Is on LocationHelper");
    }

    public Confirmation Location_Search_MnemonicCodeRes(String pickUpLocation, String firstName, String lastName, String email,
                                                        String phoneNo) {

        Locations locations = homepage.goToFindALocationPage();
        ReservationWidget reservationWidget = locations
                .searchLocation(pickUpLocation)
                .clickOnMakeAReservation();

        reservationWidget
                .calendarSelection(1)
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        Extras extras = vehicles.step2Submit();
        ReviewAndBook reviewAndBook = extras.Step3Submit();

        reviewAndBook
                .clickContinueReservationButton()
                .firstname(firstName)
                .lastname(lastName)
                .email(email)
                .phone(phoneNo)
                .SelectflightInfo(FLIGHT_NAME)
                .enterflightNumber(FLIGHT_NUMBER)
                .checkTermsAndConditions()
                .step4Submit();

        return new Confirmation(driver);
    }

}

