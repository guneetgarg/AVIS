package com.abg.avis;


import com.avis.qa.components.Header;
import com.avis.qa.components.ReservationWidget;
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
public class AvisAUAnonymousUser extends TestBase {

    @Test(groups = {REGRESSION, SMOKE,AVIS}, priority = 18, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_OutboundAndMultiCurrency_Paylater_AU(String pickUpLocation, String residencyLocation, String firstName, String lastName,
                                                                      String email, String phoneNumber, String flightNumber, String currencyValue) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_OutboundAndMultiCurrency_Paylater(pickUpLocation, residencyLocation, firstName, lastName, email,
                phoneNumber, flightNumber,currencyValue);

        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        assertTrue(confirmation.verifyCurrencyOnConfirmationPage("CAD"), "Currency value is incorrect");
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SMOKE,AVIS}, priority = 33, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_Inbound_Domestic_MultiCurrency_PayLater_AU(String pickUpLocation, String residencyLocation, String firstName, String lastName,
                                                                    String email, String phoneNumber, String flightNumber,String residentCurrencySymbol, String currencyValue) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_InboundAndMultiCurrency_Paylater(pickUpLocation, residencyLocation, firstName, lastName, email,
                phoneNumber, flightNumber,residentCurrencySymbol, currencyValue);

        assertTrue(confirmation.closeGetFreeCouponPopup().isConfirmationNumberDisplayed(), CONFIRMATION_NUMBER_NOT_DISPLAYED_MESSAGE);
        assertTrue(confirmation.verifyCurrencyOnConfirmationPage(currencyValue), CURRENCY_VALUE_INCORRECT_MESSAGE);
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SMOKE,AVIS}, priority = 41, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_Outbound_CorpCust_insuranceCover_Validate_CorpBooking_Paynow_AU(String pickUpLocation, String pickupTime, String awd, String corporateEmailId, String firstName, String lastName,
                                                                                         String email, String phoneNumber,String ccNo, String cvv, String PickUpLocCurrencySymbol, String PickupLocCurrencyCode, String USCurrencyCode) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_Outbound_CorpCust_InsuranceCover_PayNow(pickUpLocation, pickupTime, awd, corporateEmailId, firstName, lastName, email,
                phoneNumber, ccNo, cvv, PickUpLocCurrencySymbol, PickupLocCurrencyCode, USCurrencyCode);

        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        assertTrue(confirmation.validatePickupAndReturnLocValue(pickUpLocation,pickUpLocation), "Pickup and Drop Loc is not displayed");
        assertTrue(confirmation.isPickUpDateTimeDisplayed(pickupTime), "Pickup Time is not Displayed");
        //assertTrue(confirmation.isDropDateTimeDisplayed("9:00 AM"),"Drop Time is not Displayed");
        assertTrue(confirmation.verifyCurrencyOnConfirmationPage(PickupLocCurrencyCode), "Currency value is incorrect");
        assertTrue(confirmation.isAWDMessageTextDisplayed(), "AWD message is not displayed");
        assertTrue(confirmation.isCarReservedTextDisplayed(), "Car reserved text is not displayed");
        assertTrue(confirmation.isAwdConfirmationPageTextDisplayed(awd), "AWD Confirmation text is not displayed");
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SMOKE, AVIS}, priority = 43, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_Modify_flow_Step1_to_step4_AU(String pickUpLocation, String firstName, String lastName,
                                                       String email, String phoneNumber,String ccNo, String cvv, String Country, String modifiedPickupLocation) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation =  reservationHelper.Reservation_ModifyFlow_PayNow(pickUpLocation, firstName, lastName, email,
                phoneNumber, ccNo, cvv, Country, modifiedPickupLocation);
        confirmation.GetConfirmationNumber();
        confirmation.isEmailSentTextDisplayed(email);
        confirmation.isModifiedReservationTextDisplayed(firstName);
    }

    @Test(groups = {REGRESSION, SMOKE,AVIS}, priority = 34, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_AWD_SplitBillItemized_CorpCust_insuranceCover_Validate_CorpBooking_Paylater_AU(String pickUpLoction, String AWD, String corporateEmailId, String fname, String lname,
                                                                                                        String mail, String pNo, String primaryCardNo, String secCardNo) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_SplitBillItemized_CorpCust_PayLater(pickUpLoction, AWD, corporateEmailId, fname, lname,
                mail, pNo, primaryCardNo, secCardNo);

        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        assertTrue(confirmation.isPrimaryCardDetailsDisplayed(), "Primary card details is not displayed");
        assertTrue(confirmation.isSecondaryCardDetailsDisplayed(), "Secondary card details is not displayed");
        confirmation.closeGetFreeCouponPopup().cancelReservation();
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SANITY, SMOKE,AVIS}, priority = 35, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_G_typeCoupon_LocMandate_FlightInfo_SMSCheckbox_IATA_PayLater_AU(String pickUpLocation, String couponNo, String fname,
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

    @Test(groups = {REGRESSION, SMOKE,AVIS}, priority = 38, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_Outbound_StrikeThroughCoupon_Cancelation_PayLater_AU(String pickUpLocation, String residencyLocation, String awd, String fname, String lname,
                                                                              String email, String phoneNo, String flightNumber) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_OutboundAndStrikeThroughCoupon_Paylater(pickUpLocation, residencyLocation, awd, fname, lname, email, phoneNo, flightNumber);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        assertTrue(confirmation.isAWDCouponMessageDisplayed(), "AWD message is not displayed");
        assertTrue(confirmation.isAwdConfirmationPageTextDisplayed(awd), "AWD Confirmation text is not displayed");
        confirmation.cancelReservation();

    }

    @Test(groups = {REGRESSION,SMOKE, AVIS, TEST},priority=2, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_ErrorMessage_PayNow_AU(String pickUpLocation, String firstName, String lastName, String email,
                                                        String phoneNumber, String ccNumber, String cvv) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        reservationHelper.Reservation_DomesticOrOutbound_PayNow(pickUpLocation, firstName, lastName, email, phoneNumber,
                ccNumber, cvv);
        Confirmation confirmation = new Confirmation(getDriver());
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        confirmation.cancelReservationWithConfirmationBox();
    }

    @Test(groups = {REGRESSION, SANITY,SMOKE,AVIS}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Location_Search_MnemonicCodeRes_AU(String pickUpLocation, String firstName, String lastName, String email,
                                                        String phoneNo) {
        launchUrl();
        LocationHelper locationHelper = new LocationHelper(getDriver());
        Confirmation confirmation = locationHelper.Location_Search_MnemonicCodeRes(pickUpLocation, firstName, lastName, email, phoneNo);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION , SMOKE, AVIS},priority=1, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_EMEA90Days_ErrorMessage_Step1_Res_widget_AU(String pickUpLocation, String months) {
        launchUrl();
        ReservationWidget reservationWidget = new ReservationWidget(getDriver());

        reservationWidget

                .clickAcceptTermsButton()
                .pickUpLocation(pickUpLocation)
                .aboveThirtyDaysCalendarSelection(months)
                .selectMyCar();
        assertTrue(reservationWidget.isErrorMessageDisplayed(months));
    }

    @Test(groups = {REGRESSION, SMOKE, AVIS},priority=2, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_Misc_Verify_Underage_onStep1_FleetFliter_VehicleType_Seats_Mileage_Price_onStep2_AU(String pickUpLocation, String age)  {
        launchUrl();
        ReservationWidget reservationWidget = new ReservationWidget(getDriver());

        reservationWidget
                .clickAcceptTermsButton()
                .pickUpLocation(pickUpLocation)
                .calendarSelection(2)
                .selectAge(age)
                .selectMyCar();
        Vehicles vehicles = new Vehicles(getDriver());
        if(Configuration.DOMAIN.equalsIgnoreCase("AU")) {
            vehicles.verifyUnderAgeSurchargeTextDisplayed();
        }

    }

    @Test(groups = {REGRESSION, SMOKE,AVIS, TEST}, priority = 15, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_USAA_PayLater_AU(String pickUpLocation, String awd, String membershipNo, String fname,
                                                  String lname, String email, String phoneNo) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_Costco_USAA_PayLater(pickUpLocation, awd, membershipNo, fname, lname, email, phoneNo);
        assertTrue(confirmation.isAwdConfirmationPageTextDisplayed(awd), "AWD Confirmation text is not displayed");
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SANITY, SMOKE, AVIS}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Misc1_OffersPage_Reservation_AU(String pickUpLoction, String dropOffLocation, String fname, String lname,
                                                     String email, String phoneNo) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_OneWay_PayLater(pickUpLoction, dropOffLocation, fname, lname, email, phoneNo);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SANITY, SMOKE,AVIS}, priority = 19, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Location_Browse_Category_AU(String test) {
        launchUrl();
        Homepage homepage = new Homepage(getDriver());
        Locations locations = homepage.goToFindALocationPage();
        locations.browserLocation();
        Assert.assertTrue(locations.isMakeAReservationButtonDisplayed(), "Make a Reservation button is not displayed");
        locations.clickOnMakeAReservationWidget();
    }

    @Test(groups = {REGRESSION, SMOKE,AVIS}, priority = 19, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_OneWay_PayLater_AU(String pickUpLoction, String dropOffLocation, String fname, String lname,
                                                    String email, String phoneNo) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_OneWay_PayLater(pickUpLoction, dropOffLocation, fname, lname, email, phoneNo);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SANITY, SMOKE,AVIS}, priority = 20, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_TierBundle_PayLater_AU(String pickUpLocation, String firstName, String lastName, String email,
                                                        String phoneNo, String ccNo, String cvv) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_TierBundle_PayLater(pickUpLocation, firstName, lastName, email, phoneNo, ccNo,
                cvv);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        assertTrue(confirmation.verifyRentalOptionsText("Cover Roadside Issues (RSN)"), "Rental Options Text is incorrect");
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SMOKE, AVIS}, priority = 1, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_Outbound_PayNow_AU(String pickUpLocation, String firstName, String lastName, String email, String phoneNumber, String ccNumber, String cvv) {
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

    @Test(groups = {REGRESSION, SMOKE, AVIS, TEST}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Misc_CarGuide_Res_AU(String pickUpLoc, String firstName, String lastName, String email, String phoneNo) {
        launchUrl();
        MiscHelper miscHelper = new MiscHelper(getDriver());
        Confirmation confirmation = miscHelper.Misc_CarGuide_Res(pickUpLoc, firstName, lastName, email, phoneNo);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SANITY, SMOKE, AVIS, TEST}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Misc_OffersPage_Reservation_AU(String pickUplocation, String firstName, String lastName, String email,
                                                    String phoneNo) {
        launchUrl();
        MiscHelper miscHelper = new MiscHelper(getDriver());
        Confirmation confirmation = miscHelper.Misc_OffersPage_Reservation(pickUplocation, firstName, lastName, email, phoneNo);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SANITY,SMOKE,AVIS}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Misc_OneClick_VanityURL_AU(String url, String title) {
        launchUrl(getAvisUrl(url));
        assertTrue(getDriver().getTitle().contains(title), "Page title is incorrect");
    }

    @Test(groups = {REGRESSION, SMOKE, AVIS}, priority = 3, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_CCOLocation_PayLater_AU(String pickUpLocation, String firstName, String lastName, String email,
                                                         String phoneNo, String cCNumber) {
        log.info("Test case execution started :Avis_Reservation_CCOLocation_PayLater_AU");
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        ReviewAndBook reviewAndBook = reservationHelper.Reservation_CCOLocation_PayLater(pickUpLocation, firstName, lastName, email, phoneNo,
                cCNumber);
        assertFalse(reviewAndBook.cvvCCOValidation(), "CVV is displayed");
        log.info("Test case execution ended :Avis_Reservation_CCOLocation_PayLater_AU");
    }

    @Test(groups = {REGRESSION, SMOKE,AVIS, TEST}, priority = 4, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_Costco_PayLater_AU(String pickUpLocation, String awd, String membershipNo, String fname,
                                                    String lname, String email, String phoneNo) {
        log.info("Test case execution started :Avis_Reservation_Costco_PayLater_AU");
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_Costco_USAA_PayLater(pickUpLocation, awd, membershipNo, fname, lname, email, phoneNo);
        assertTrue(confirmation.isAwdConfirmationPageTextDisplayed(awd), "AWD Confirmation text is not displayed");
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        confirmation.cancelReservation();
        log.info("Test case execution ended :Avis_Reservation_Costco_PayLater_AU");
    }

    @Test(groups = {REGRESSION, SANITY, SMOKE,AVIS}, priority = 5, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_EMEA90Days_ErrorMessage_AU(String pickUpLocation, String months) {
        launchUrl();
        ReservationWidget reservationWidget = new ReservationWidget(getDriver());
        reservationWidget
                .pickUpLocation(pickUpLocation)
                .aboveThirtyDaysCalendarSelection(months)
                .selectMyCar();
        assertTrue(reservationWidget.isErrorMessageDisplayed(months));
    }

    @Test(groups = {REGRESSION, SANITY, SMOKE,AVIS}, priority = 6, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_EMEA330Days_ErrorMessage_AU(String pickUpLocation, String months) {
        launchUrl();
        ReservationWidget reservationWidget = new ReservationWidget(getDriver());

        reservationWidget
                .pickUpLocation(pickUpLocation)
                .aboveThirtyDaysCalendarSelection(months)
                .selectMyCar();
        assertTrue(reservationWidget.isErrorMessageDisplayed(months));
    }

}
