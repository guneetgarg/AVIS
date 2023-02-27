package com.abg.avis;


import com.avis.qa.components.ReservationWidget;
import com.avis.qa.core.AbstractBasePage;
import com.avis.qa.core.Configuration;
import com.avis.qa.core.TestBase;
import com.avis.qa.helpers.LocationHelper;
import com.avis.qa.helpers.MiscHelper;
import com.avis.qa.helpers.ReservationHelper;
import com.avis.qa.pages.*;
import com.avis.qa.utilities.CSVUtils;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Map;

import static com.avis.qa.constants.AvisConstants.*;
import static com.avis.qa.constants.TextComparison.*;
import static org.testng.Assert.*;

/**
 * Class to test the reservation functionality
 *
 * @author ikumar
 */
@Log4j2
public class AvisAnonymousUser extends TestBase {

    @Test(groups = {REGRESSION, SMOKE,AVIS}, priority = 40, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_FlightInfo_DigitalWallet_PayPal_PayNow_US(String pickUpLocation, String fname, String lname,
                                                                   String email, String phoneNo, String paypalEmail, String paypalPassword, String flightName, String flightNumber) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_FlightInfo_DigitalWallet_Paypal_PayNow(pickUpLocation, fname, lname, email, phoneNo, paypalEmail, paypalPassword, flightName, flightNumber);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        assertTrue(confirmation.isCarReservedTextDisplayed(), "Car reserved text is not displayed");
        assertTrue(confirmation.validatePickupAndReturnLocValue(pickUpLocation,pickUpLocation), "Pickup and Drop Loc is not displayed");
        assertTrue(confirmation.isPickUpDateTimeDisplayed(PICK_UP_TIME), "Pickup Time is not Displayed");
        assertTrue(confirmation.isDropDateTimeDisplayed(DROP_UP_TIME),"Drop Time is not Displayed");
        assertTrue(confirmation.isCardTypePaypalDisplayed(), "Card Type Paypal is not displayed");
        assertTrue(confirmation.isFlightInfoDisplayed(flightName), "Flight Info is not displayed");
        confirmation.cancelReservationWithConfirmationBox();
    }

    @Test(groups = {REGRESSION, SANITY, SMOKE,AVIS}, priority = 35, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_G_typeCoupon_LocMandate_FlightInfo_SMSCheckbox_IATA_PayLater_US(String pickUpLocation, String couponNo, String fname,
                                                                                         String lname, String email, String phoneNo,String flightName, String flightNumber, String IATA, String couponMsg) {

        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_G_typeCoupon_LocMandate_FlightInfo_SMSCheckbox_IATA_PayLater(pickUpLocation, couponNo, fname, lname,
                email, phoneNo,flightName, flightNumber, IATA, couponMsg);

        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        assertTrue(confirmation.isCouponCodeMessageDisplayed(couponMsg), "Coupon Code is not displayed");
        assertTrue(confirmation.isIATAValueDisplayed(IATA), "IATA value is not displayed");
        assertTrue(confirmation.isFlightInfoDisplayed(flightName), "Flight Info is not displayed");
        confirmation.closeGetFreeCouponPopup().cancelReservation();
    }

    @Test(groups = {REGRESSION, SMOKE,AVIS}, priority = 41, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_Outbound_CorpCust_insuranceCover_Validate_CorpBooking_Paynow_US(String pickUpLocation, String pickupTime,String awd, String corporateEmailId, String firstName, String lastName,
                                                                                         String email, String phoneNumber,String ccNo, String cvv, String PickUpLocCurrencySymbol, String PickupLocCurrencyCode, String USCurrencyCode) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_Outbound_CorpCust_InsuranceCover_PayNow(pickUpLocation, pickupTime, awd, corporateEmailId, firstName, lastName, email,
                phoneNumber, ccNo, cvv, PickUpLocCurrencySymbol, PickupLocCurrencyCode, USCurrencyCode);

        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        assertTrue(confirmation.validatePickupAndReturnLocValue(pickUpLocation,pickUpLocation), "Pickup and Drop Loc is not displayed");
        assertTrue(confirmation.isPickUpDateTimeDisplayed(pickupTime), "Pickup Time is not Displayed");
        assertTrue(confirmation.isDropDateTimeDisplayed("12:00 PM"),"Drop Time is not Displayed");
        assertTrue(confirmation.verifyCurrencyOnConfirmationPage(PickupLocCurrencyCode), "Currency value is incorrect");
        assertTrue(confirmation.isAWDMessageTextDisplayed(), "AWD message is not displayed");
        assertTrue(confirmation.isCarReservedTextDisplayed(), "Car reserved text is not displayed");
        assertTrue(confirmation.isAwdConfirmationPageTextDisplayed(awd), "AWD Confirmation text is not displayed");
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SMOKE,AVIS}, priority = 38, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_Outbound_StrikeThroughCoupon_Cancelation_PayLater_US(String pickUpLocation, String residencyLocation, String awd, String fname, String lname,
                                                                              String email, String phoneNo, String flightNumber) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_OutboundAndStrikeThroughCoupon_Paylater(pickUpLocation, residencyLocation, awd, fname, lname, email, phoneNo, flightNumber);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        assertTrue(confirmation.isAWDCouponMessageDisplayed(), "AWD message is not displayed");
        assertTrue(confirmation.isAwdConfirmationPageTextDisplayed(awd), "AWD Confirmation text is not displayed");
        confirmation.cancelReservation();

    }

    @Test(groups = {REGRESSION,SMOKE},priority=2, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_ErrorMessage_PayNow_US(String pickUpLocation, String firstName, String lastName, String email,
                                                        String phoneNumber, String ccNumber, String cvv) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        reservationHelper.Reservation_DomesticOrOutbound_PayNow(pickUpLocation, firstName, lastName, email, phoneNumber,
                ccNumber, cvv);
        Confirmation confirmation = new Confirmation(getDriver());
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        confirmation.cancelReservationWithConfirmationBox();
    }

    @Test(groups = {REGRESSION, SMOKE,AVIS}, priority = 18, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_OutboundAndMultiCurrency_Paylater_US(String pickUpLocation, String residencyLocation, String firstName, String lastName,
                                                                      String email, String phoneNumber, String flightNumber, String currencyValue) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_OutboundAndMultiCurrency_Paylater(pickUpLocation, residencyLocation, firstName, lastName, email,
                phoneNumber, flightNumber,currencyValue);

        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        assertTrue(confirmation.verifyCurrencyOnConfirmationPage("CAD"), "Currency value is incorrect");
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SANITY,SMOKE,AVIS}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Location_Search_MnemonicCodeRes_US(String pickUpLocation, String firstName, String lastName, String email,
                                                        String phoneNo) {
        launchUrl();
        LocationHelper locationHelper = new LocationHelper(getDriver());
        Confirmation confirmation = locationHelper.Location_Search_MnemonicCodeRes(pickUpLocation, firstName, lastName, email, phoneNo);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        confirmation.cancelReservation();
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

    @Test(groups = {REGRESSION, SMOKE,AVIS}, priority = 15, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_USAA_PayLater_US(String pickUpLocation, String awd, String membershipNo, String fname,
                                                  String lname, String email, String phoneNo) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_Costco_USAA_PayLater(pickUpLocation, awd, membershipNo, fname, lname, email, phoneNo);
        assertTrue(confirmation.isAwdConfirmationPageTextDisplayed(awd), "AWD Confirmation text is not displayed");
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        confirmation.cancelReservation();
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
    @Test(groups = {REGRESSION, SANITY, SMOKE,AVIS}, priority = 19, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Location_Browse_Category_US(String test) {
        launchUrl();
        Homepage homepage = new Homepage(getDriver());
        Locations locations = homepage.goToFindALocationPage();
        locations.browserLocation();
        Assert.assertTrue(locations.isMakeAReservationButtonDisplayed(), "Make a Reservation button is not displayed");
        locations.clickOnMakeAReservationWidget();
    }

    @Test(groups = {REGRESSION, SMOKE,AVIS}, priority = 19, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_OneWay_PayLater_US(String pickUpLoction, String dropOffLocation, String fname, String lname,
                                                    String email, String phoneNo) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_OneWay_PayLater(pickUpLoction, dropOffLocation, fname, lname, email, phoneNo);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SANITY, SMOKE,AVIS}, priority = 20, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_TierBundle_PayLater_US(String pickUpLocation, String firstName, String lastName, String email,
                                                        String phoneNo, String ccNo, String cvv) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_TierBundle_PayLater(pickUpLocation, firstName, lastName, email, phoneNo, ccNo,
                cvv);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        assertTrue(confirmation.verifyRentalOptionsText(), "Rental Options Text is incorrect");
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SMOKE, AVIS}, priority = 1, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_Outbound_PayNow_US(String pickUpLocation, String firstName, String lastName, String email, String phoneNumber, String ccNumber, String cvv) {
        launchUrl();
        Homepage homePage = new Homepage(getDriver());
        homePage.findAndCloseAdOverLay();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        reservationHelper.Reservation_DomesticOrOutbound_PayNow(pickUpLocation, firstName, lastName, email, phoneNumber,
                ccNumber, cvv);
        Confirmation confirmation = new Confirmation(getDriver());
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        confirmation.cancelReservationWithConfirmationBox();
    }

}
