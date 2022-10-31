package com.avis.qa.helpers;

import com.avis.qa.components.AvisFlyout;
import com.avis.qa.components.Footer;
import com.avis.qa.components.Header;
import com.avis.qa.components.ReservationWidget;
import com.avis.qa.core.PopUpHandler;
import com.avis.qa.pages.*;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertTrue;

public class MiscHelper extends PopUpHandler {

    private final WebDriver driver;
    private final ReservationWidget reservationWidget;

    public MiscHelper(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.reservationWidget = new ReservationWidget(driver);
    }

    private Confirmation getConfirmation(String pickupLocation, String fName, String lName, String email, String phone) {
        reservationWidget
                .pickUpLocation(pickupLocation)
                .calendarSelection()
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        Extras extras = vehicles.step2Submit();
        ReviewAndBook reviewAndBook = extras.Step3Submit();

        reviewAndBook
                .clickContinueReservationButton()
                .firstname(fName)
                .lastname(lName)
                .email(email)
                .phone(phone)
                .checkTermsAndConditions()
                .step4Submit();

        return new Confirmation(driver);
    }

    public Confirmation Misc_OffersPage_Reservation(String pickUpLocation, String firstName, String lastName, String email,
                                                    String phoneNo) {

        Header header = new Header(driver);
        header.offersHeader().clickOnOffersCTA();

        return getConfirmation(pickUpLocation, firstName, lastName, email, phoneNo);
    }


    public Confirmation Misc_CarGuide_Res(String pickUpLocation, String firstName, String lastName, String email,
                                          String phoneNo) {
        Header header = new Header(driver);
        header.carAndservicesHeader();
        return getConfirmation(pickUpLocation, firstName, lastName, email, phoneNo);
    }

    public void Misc_BusinessPrograms_BaseRateGuarnatee(String wizardNo, String password, String pickup, String fName,
                                                        String lName, String email, String phone, String bestRateQuote, String lowerRateCar_bestRateQuote,
                                                        String pickupLocation, String dropOffLocation, String vehicle_Type, String webSite, String comments) {

        Confirmation confirmation = getConfirmation(pickupLocation, fName, lName, email, phone);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        confirmation.cancelReservation();

        Footer footer = new Footer(driver);
        BaseRate baseRate = footer.clickBRG();
        baseRate.landOnBaseRateGuaranteeForm();
        baseRate.submitForm(fName, lName, email, phone, pickupLocation);
    }

    public void Misc_SmallAndMidSizedBusiness_FlyOutSignUp(String pickUpLoc, String firstName, String lastName,
                                                           String email, String phoneNo, String companyName, String address1, String city, String province,
                                                           String zipcode, String country) {

        getConfirmation(pickUpLoc, firstName, lastName, email, phoneNo);
        AvisFlyout avisFlyout = new AvisFlyout(driver);
        avisFlyout.clickOnNextButton();
        assertTrue(avisFlyout.enterCompanyInformationAndVerifyAWDLength(companyName, address1, city, province, zipcode, country));
    }


}
