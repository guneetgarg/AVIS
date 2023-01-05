package com.avis.qa;

import com.avis.qa.components.Header;
import com.avis.qa.components.LoginWidget;
import com.avis.qa.components.ReservationWidget;
import com.avis.qa.core.Configuration;
import com.avis.qa.core.TestBase;
import com.avis.qa.helpers.MiscHelper;
import com.avis.qa.pages.Confirmation;
import com.avis.qa.pages.Vehicles;
import com.avis.qa.utilities.CSVUtils;
import org.testng.annotations.Test;
import static com.avis.qa.constants.TextComparison.*;
import static com.avis.qa.constants.AvisConstants.*;
import static com.avis.qa.utilities.CommonUtils.TWO_SECONDS;
import static com.avis.qa.utilities.CommonUtils.threadSleep;
import static org.testng.Assert.assertTrue;

/**
 * Class contains Miscellaneous Tests
 *
 * @author ikumar
 */
public class
MiscellaneousTests extends TestBase {

    @Test(groups = {REGRESSION, SANITY,SMOKE,AVIS}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Misc_OneClick_VanityURL_US(String url, String title) {
        System.out.println(getAvisUrl(url));
        launchUrl(getAvisUrl(url));
        assertTrue(getDriver().getTitle().contains(title), "Page title is incorrect");
    }

    @Test(groups = {REGRESSION, SANITY, SMOKE, AVIS}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Misc_OffersPage_Reservation_US(String pickUplocation, String firstName, String lastName, String email,
                                            String phoneNo) {
        launchUrl();
        MiscHelper miscHelper = new MiscHelper(getDriver());
        Confirmation confirmation = miscHelper.Misc_OffersPage_Reservation(pickUplocation, firstName, lastName, email, phoneNo);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        confirmation.cancelReservation();
    }


//    @Test(groups = {REGRESSION, SANITY,SMOKE}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Misc_BusinessPrograms_BaseRateGuranatee_US(String wizardNo, String password, String pickup, String fName,
                                                        String lName, String email, String phone, String bestRateQuote, String LowerRateCar_bestRateQuote,
                                                        String pickupLocation, String DropOffLocation, String vehicle_Type, String webSite, String comments) {
        launchUrl();
        MiscHelper miscHelper = new MiscHelper(getDriver());
        miscHelper.Misc_BusinessPrograms_BaseRateGuarnatee(wizardNo, password, pickup, fName, lName, email,
                phone, bestRateQuote, LowerRateCar_bestRateQuote, pickupLocation, DropOffLocation, vehicle_Type,
                webSite, comments);
    }

    @Test(groups = {REGRESSION, SANITY,SMOKE, AVIS}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Misc_CarGuide_Res_US(String pickUpLoc, String firstName, String lastName, String email, String phoneNo) {
        launchUrl();
        MiscHelper miscHelper = new MiscHelper(getDriver());
        Confirmation confirmation = miscHelper.Misc_CarGuide_Res(pickUpLoc, firstName, lastName, email, phoneNo);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SANITY,SMOKE, AVIS}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Misc_OffersPage_SignUp_US(String email, String firstName, String lastName, String country) {
        launchUrl();
        Header header = new Header(getDriver());
        header.offersHeader().enterEmailForSigup(email, firstName, lastName, country);
        assertTrue(header.isSignupConfirmationTextDisplayed(), "Signup Confirmation Text is not displayed");

    }

//    @Test(groups = {REGRESSION, SANITY,SMOKE}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Misc_SmallAndMidSizedBusiness_FlyOutSignUp_US(String pickUpLoc, String firstName, String lastName,
                                                           String email, String phoneNo, String companyName, String address1, String city, String province,
                                                           String zipcode, String country) {
        launchUrl();
        MiscHelper miscHelper = new MiscHelper(getDriver());
        miscHelper.Misc_SmallAndMidSizedBusiness_FlyOutSignUp(pickUpLoc, firstName, lastName, email, phoneNo,
                companyName, address1, city, province, zipcode, country);

    }

    @Test(groups = {REGRESSION , SMOKE, AVIS},priority=1, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_EMEA90Days_ErrorMessage_Step1_Res_widget_US(String pickUpLocation, String months) {
        launchUrl();
        ReservationWidget reservationWidget = new ReservationWidget(getDriver());

        reservationWidget

                .clickAcceptTermsButton()
                .pickUpLocation(pickUpLocation)
                .aboveThirtyDaysCalendarSelection(months)
                .selectMyCar();
        assertTrue(reservationWidget.isErrorMessageDisplayed(months));
    }

    @Test(groups = {REGRESSION , SMOKE,AVIS},priority=2, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_Misc_Verify_Underage_onStep1_FleetFliter_VehicleType_Seats_Mileage_Price_onStep2_US(String pickUpLocation, String age)  {
        launchUrl();
        ReservationWidget reservationWidget = new ReservationWidget(getDriver());

        reservationWidget
                .clickAcceptTermsButton()
                .pickUpLocation(pickUpLocation)
                .calendarSelection(2)
                .selectAge(age)
                .selectMyCar();
        Vehicles vehicles = new Vehicles(getDriver());
        if(Configuration.DOMAIN.equalsIgnoreCase("US")) {
            vehicles.verifyUnderAgeSurchargeTextDisplayed();
        }
        vehicles.clickFilterOptionAndVerifyData();

    }

    @Test(groups = {REGRESSION , SMOKE,BUDGET},priority=2, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Budget_RES_Misc_Verify_Underage_onStep1_FleetFliter_VehicleType_Seats_Mileage_Price_onStep2_US(String pickUpLocation, String age1, String age2)  {
        launchUrl();
        ReservationWidget reservationWidget = new ReservationWidget(getDriver());

        reservationWidget
                .clickAcceptTermsButton()
                .pickUpLocation(pickUpLocation)
                .calendarSelection(2)
                .selectAge(age1)
                .selectMyCar()
                .VerifyErrorMessageDisplayed(MINIMUM_AGE_ERROR_MESSAGE);
        reservationWidget
                .selectAge(age2)
                .selectMyCar();
        Vehicles vehicles = new Vehicles(getDriver());
        vehicles.clickFilterOptionAndVerifyData();

    }

    @Test(groups = {REGRESSION , SMOKE, AVIS},priority=3, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_Misc_Step1AndStep4_ErrorMsg_Validation_US(String pickUpLocation, String pickUpDate, String pickUpTime, String dropOffLocation, String dropOffDate, String dropOffTime, String WizardNumber, String lastName, String awdCode, String corporateEmail, String rateCode, String couponCode, String creditcardNumber) {
        launchUrl();
        MiscHelper miscHelper = new MiscHelper(getDriver());
        miscHelper.Reservation_Misc_Step1AndStep4_ErrorMsg_Validation(pickUpLocation,pickUpDate, pickUpTime, dropOffLocation, dropOffDate,dropOffTime,WizardNumber,lastName,awdCode,corporateEmail,rateCode,couponCode,creditcardNumber);

    }


    @Test(groups = {REGRESSION , SMOKE, BUDGET},priority=3, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public
    void Budget_RES_Misc_Step1_2_Step4_ErrorMsg_Validation_US(String pickUpLocation, String pickUpDate, String pickUpTime, String dropOffLocation, String dropOffDate, String dropOffTime, String WizardNumber, String lastName, String awdCode, String corporateEmail, String rateCode, String couponCode, String creditcardNumber) {
        launchUrl();
        MiscHelper miscHelper = new MiscHelper(getDriver());
        miscHelper.Reservation_Misc_Step1AndStep4_ErrorMsg_Validation(pickUpLocation,pickUpDate, pickUpTime, dropOffLocation, dropOffDate,dropOffTime,WizardNumber,lastName,awdCode,corporateEmail,rateCode,couponCode,creditcardNumber);

    }



}
