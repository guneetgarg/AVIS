package com.avis.qa;

import com.avis.qa.components.LoginWidget;
import com.avis.qa.components.ReservationWidget;
import com.avis.qa.core.TestBase;
import com.avis.qa.helpers.MiscHelper;
import com.avis.qa.helpers.ReservationHelper;
import com.avis.qa.pages.*;
import com.avis.qa.utilities.CSVUtils;
import com.avis.qa.utilities.ElementHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.avis.qa.constants.AvisConstants.*;
import static com.avis.qa.utilities.CommonUtils.*;
import static org.testng.Assert.assertTrue;

public class ProfileTest  extends TestBase {

    /**
     * ALM Testcase: NA
     */
    //@Test(groups = {REGRESSION , SMOKE},priority=1, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Profile_Enrollment_Activation_US(String fname, String lname, String email, String phoneNo, String username, String pwd, String AddLine1) {
        launchUrl();
        Homepage homepage = new Homepage(getDriver());
        Enrollment enrollment = homepage.clickSignupLink();
        enrollment.enterFirstName(fname);
        enrollment.enterLastName(lname);
        enrollment.enterMobileNumber(phoneNo);
        enrollment.enterEmailAddress(email);
        enrollment.clickUseEmailAsUsernameCheckbox();
        enrollment.enterUsername(username);
        enrollment.enterPassword(pwd);
       // enrollment.selectAddressLine1Suggestion(AddLine1);
        enrollment.clickSaveButton();
        threadSleep(5000);

    }

    @Test(groups = {REGRESSION , SMOKE},priority=1, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_Profile_EMEA90Days_ErrorMessage_Step1_Res_widget_US(String username, String password, String pickUpLocation, String months) {
        launchUrl();
        LoginWidget loginwidget = new LoginWidget(getDriver());
        loginwidget.loginHeaderclick();
        loginwidget.login(username,password);
        threadSleep(TWO_SECONDS);
        ReservationWidget reservationWidget = new ReservationWidget(getDriver());

        reservationWidget

                .clickAcceptTermsButton()
                .pickUpLocation(pickUpLocation)
                .aboveThirtyDaysCalendarSelection(months)
                .selectMyCar();
        assertTrue(reservationWidget.isErrorMessageDisplayed(months));
    }

    @Test(groups = {REGRESSION , SMOKE},priority=2, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_Profile_Misc_Verify_Underage_onStep1_FleetFliter_VehicleType_Seats_Mileage_Price_onStep2_US(String username, String password, String pickUpLocation)  {
        launchUrl();

            LoginWidget loginwidget = new LoginWidget(getDriver());
            loginwidget.loginHeaderclick();
            loginwidget.login(username, password);
        threadSleep(TWO_SECONDS);
        ReservationWidget reservationWidget = new ReservationWidget(getDriver());

        reservationWidget
                .clickAcceptTermsButton()
                .pickUpLocation(pickUpLocation)
                .calendarSelection(2)
                .selectMyCar();
        Vehicles vehicles = new Vehicles(getDriver());
        //vehicles.verifyUnderAgeSurchargeTextDisplayed();
        vehicles.clickFilterOptionAndVerifyData();

    }

    //@Test(groups = {REGRESSION , SMOKE},priority=3, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_Profile_Misc_Step1AndStep4_ErrorMsg_Validation_US(String username, String password, String pickUpLocation, String pickUpDate, String pickUpTime, String dropOffLocation, String dropOffDate, String dropOffTime, String WizardNumber, String lastName, String awdCode, String corporateEmail, String rateCode, String couponCode, String creditcardNumber) {
        launchUrl();

        LoginWidget loginwidget = new LoginWidget(getDriver());
        loginwidget.loginHeaderclick();
        loginwidget.login(username, password);
        threadSleep(TWO_SECONDS);
        MiscHelper miscHelper = new MiscHelper(getDriver());
        miscHelper.Reservation_Profile_Misc_Step1AndStep4_ErrorMsg_Validation(pickUpLocation,pickUpDate, pickUpTime, dropOffLocation, dropOffDate,dropOffTime,WizardNumber,lastName,awdCode,corporateEmail,rateCode,couponCode,creditcardNumber);

    }

   // @Test(groups = {REGRESSION, SMOKE}, priority = 4, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_Profile_Inbound_MultiCurrency_DCCPrePay_IATA_Cancellation_PayNow_US(String username, String password
            ,String pickUpLocation, String residencyLocation, String PickupTime, String DropTime,String awd, String corporateEmailId, String IATA,String residentCurrencySymbol, String USCurrencyValue) {
        launchUrl();
        LoginWidget loginwidget = new LoginWidget(getDriver());
        loginwidget.loginHeaderclick();
        loginwidget.login(username, password);
        threadSleep(TWO_SECONDS);
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_InboundAndMultiCurrency_IATA_PayNow(pickUpLocation, residencyLocation,PickupTime,DropTime, awd, corporateEmailId,IATA, residentCurrencySymbol, USCurrencyValue);

        assertTrue(confirmation.isConfirmationNumberDisplayed());
        assertTrue(confirmation.isCarReservedTextDisplayed());
        assertTrue(confirmation.validatePickupAndReturnLocValue(pickUpLocation,pickUpLocation));
        assertTrue(confirmation.isPickUpDateTimeDisplayed(PickupTime));
        assertTrue(confirmation.isDropDateTimeDisplayed(DropTime));
        assertTrue(confirmation.verifyCurrencyOnConfirmationPage(USCurrencyValue));
        // assertTrue(confirmation.isAWDCouponMessageDisplayed(), "AWD message is not displayed");
        assertTrue(confirmation.isAwdConfirmationPageTextDisplayed(awd));
        assertTrue(confirmation.isIATAValueDisplayed(IATA));
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SMOKE}, priority = 5, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_Profile_FlightInfo_DigitalWallet_PayPal_PayNow_US(String username, String password,String pickUpLocation,String PickupTime,String DropTime, String paypalEmail, String paypalPassword, String flightName, String flightNumber) {
        launchUrl();
        LoginWidget loginwidget = new LoginWidget(getDriver());
        loginwidget.loginHeaderclick();
        loginwidget.login(username, password);
        threadSleep(TWO_SECONDS);
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_Profile_FlightInfo_DigitalWallet_Paypal_PayNow(pickUpLocation, PickupTime, DropTime, paypalEmail, paypalPassword, flightName, flightNumber);
        assertTrue(confirmation.isConfirmationNumberDisplayed());
        assertTrue(confirmation.isCarReservedTextDisplayed());
        assertTrue(confirmation.validatePickupAndReturnLocValue(pickUpLocation,pickUpLocation));
        assertTrue(confirmation.isPickUpDateTimeDisplayed(PickupTime));
        assertTrue(confirmation.isDropDateTimeDisplayed(DropTime));
        assertTrue(confirmation.isCardTypePaypalDisplayed());
        //confirmation.cancelReservation();
        assertTrue(confirmation.isFlightInfoDisplayed(flightName));
        confirmation.cancelReservationWithConfirmationBox();
    }

}
