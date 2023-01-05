package com.avis.qa;


import com.avis.qa.components.ReservationWidget;
import com.avis.qa.core.AbstractBasePage;
import com.avis.qa.core.TestBase;
import com.avis.qa.helpers.ReservationHelper;
import com.avis.qa.pages.*;
import com.avis.qa.utilities.CSVUtils;
import lombok.extern.log4j.Log4j2;
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
public class ReservationTests extends TestBase {

    /**
     * ALM Testcase: Res_Avis_US_007_Reservation_Outbound_PayNowAnonymous
     * ALM Testcase: Res_Avis_US_007_Reservation_Outbound_PayNow
     */
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

    //    @Test(groups = {REGRESSION, SMOKE}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Budget_Reservation_Outbound_PayNow_US_EN(String pickUpLocation, String firstName, String lastName, String email, String phoneNumber, String ccNumber, String cvv) {
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

    /**
     * ALM Testcase: Res_Avis_US_005_Reservation_Domestic_PayNow
     */
//    @Test(groups = {REGRESSION,SMOKE},priority=2, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    // public void Avis_Reservation_ErrorMessage_PayNow_US(String pickUpLocation, String firstName, String lastName, String email,
    //                                         String phoneNumber, String ccNumber, String cvv) {
    //     launchUrl();
    //     ReservationHelper reservationHelper = new ReservationHelper(getDriver());
    //     reservationHelper.Reservation_DomesticOrOutbound_PayNow(pickUpLocation, firstName, lastName, email, phoneNumber,
    //             ccNumber, cvv);
    //     Confirmation confirmation = new Confirmation(getDriver());
    //     assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
    //     confirmation.cancelReservationWithConfirmationBox();
    // }
    @Test(groups = {REGRESSION, SMOKE, AVIS}, priority = 3, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_CCOLocation_PayLater_US(String pickUpLocation, String firstName, String lastName, String email,
                                                         String phoneNo, String cCNumber) {
        log.info("Test case execution started :Avis_Reservation_CCOLocation_PayLater_US");
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        ReviewAndBook reviewAndBook = reservationHelper.Reservation_CCOLocation_PayLater(pickUpLocation, firstName, lastName, email, phoneNo,
                cCNumber);
        assertFalse(reviewAndBook.cvvCCOValidation(), "CVV is displayed");
        log.info("Test case execution ended :Avis_Reservation_CCOLocation_PayLater_US");
    }

    @Test(groups = {REGRESSION, SMOKE,BUDGET}, priority = 3, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Budget_Reservation_CCOLocation_PayLater_US(String pickUpLocation, String firstName, String lastName, String email,
                                                         String phoneNo, String cCNumber) {
        log.info("Test case execution started :Avis_Reservation_CCOLocation_PayLater_US");
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        ReviewAndBook reviewAndBook = reservationHelper.Reservation_CCOLocation_PayLater(pickUpLocation, firstName, lastName, email, phoneNo,
                cCNumber);
        assertFalse(reviewAndBook.cvvCCOValidation(), "CVV is displayed");
        log.info("Test case execution ended :Avis_Reservation_CCOLocation_PayLater_US");
    }


    @Test(groups = {REGRESSION, SMOKE,AVIS}, priority = 4, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_Costco_PayLater_US(String pickUpLocation, String awd, String membershipNo, String fname,
                                                    String lname, String email, String phoneNo) {
        log.info("Test case execution started :Avis_Reservation_Costco_PayLater_US");
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_Costco_USAA_PayLater(pickUpLocation, awd, membershipNo, fname, lname, email, phoneNo);
        assertTrue(confirmation.isAwdConfirmationPageTextDisplayed(awd), "AWD Confirmation text is not displayed");
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        confirmation.cancelReservation();
        log.info("Test case execution ended :Avis_Reservation_Costco_PayLater_US");
    }

    @Test(groups = {REGRESSION, SANITY, SMOKE,AVIS}, priority = 5, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_EMEA90Days_ErrorMessage_US(String pickUpLocation, String months) {
        launchUrl();

        ReservationWidget reservationWidget = new ReservationWidget(getDriver());

        reservationWidget
                .pickUpLocation(pickUpLocation)
                .aboveThirtyDaysCalendarSelection(months)
                .selectMyCar();
        assertTrue(reservationWidget.isErrorMessageDisplayed(months));

    }

    @Test(groups = {REGRESSION, SANITY, SMOKE,BUDGET}, priority = 5, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Budget_RES_EMEA90Days_ErrorMessage_Step1_Res_widget_US(String pickUpLocation, String months) {
        launchUrl();
        ReservationWidget reservationWidget = new ReservationWidget(getDriver());

        reservationWidget
                .closeAdPopup()
                .pickUpLocation(pickUpLocation)
                .aboveThirtyDaysCalendarSelection(months)
                .selectMyCar();
        assertTrue(reservationWidget.isErrorMessageDisplayed(months));
    }

    @Test(groups = {REGRESSION, SANITY, SMOKE,AVIS}, priority = 6, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_EMEA330Days_ErrorMessage_US(String pickUpLocation, String months) {
        launchUrl();
        ReservationWidget reservationWidget = new ReservationWidget(getDriver());

        reservationWidget
                .pickUpLocation(pickUpLocation)
                .aboveThirtyDaysCalendarSelection(months)
                .selectMyCar();
        assertTrue(reservationWidget.isErrorMessageDisplayed(months));
    }





    @Test(groups = {REGRESSION, SANITY, SMOKE,AVIS}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_ErrorMessage_PayNow_US(String pickUpLocation, String firstName, String lastName, String email,
                                                        String phoneNo, String ccNo, String cvv) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        reservationHelper.Reservation_DomesticOrOutbound_PayNow(pickUpLocation, firstName, lastName, email, phoneNo,
                ccNo, cvv);
        ReviewAndBook reviewAndBook = new ReviewAndBook(getDriver());
        assertEquals(reviewAndBook.getDummyCreditCardErrorMessage(), DUMMY_CC_ERROR_MESSAGE, "Credit card error message is incorrect");
    }

    @Test(groups = {REGRESSION, SANITY, SMOKE,AVIS}, priority = 7, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_OneClick_Step3_US(String url) {
        launchUrl();
        Homepage homepage = new Homepage(getDriver());
        homepage.oneClickLandingPage(url);

        Extras extras = new Extras(getDriver());
        assertTrue(extras.isSubmitStepThreeDisplayed(), "Submit step three button is not displayed");
    }

    // @Test(groups = {REGRESSION, SANITY,SMOKE},priority=8, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_OneClick_GPSSelection_US(String url) {
        launchUrl();
        Homepage homepage = new Homepage(getDriver());
        homepage.oneClickLandingPage(url);

        ReviewAndBook reviewAndBook = new ReviewAndBook(getDriver());
        Confirmation confirmation = reviewAndBook.keepModification();
        assertTrue(confirmation.isExtrasAddedMessageDisplayed(), "Extra Added Message is not displayed");
    }

    @Test(groups = {REGRESSION, SMOKE,AVIS}, priority = 9, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_GEBUser_PayLater_US(String pickUpLocation, String wizardNo, String lastName, String firstName,
                                                     String email, String phoneNo) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_GEBUser_PayLater(pickUpLocation, wizardNo, lastName, firstName, email, phoneNo);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SANITY, SMOKE,AVIS}, priority = 10, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_GtypeCouponProcessing_PayLater_US(String pickUpLocation, String couponNo, String fname,
                                                                   String lname, String email, String phoneNo) {

        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation;
        ReservationWidget reservationWidget;
        Map<String, String> map;
        try {
            confirmation = reservationHelper.Reservation_CouponProcessing_PayLater(pickUpLocation, couponNo, fname, lname, email,
                    phoneNo);
        } catch (Exception ex) {
            reservationWidget = reservationHelper.getReservationWidget();
            map = reservationWidget.getCouponErrorMessageAttributes();
            assertEquals(map.get("errorText"), "Your Coupon can't be used for this reservation. Learn Why?", "Coupon error message is different than expected");
            assertEquals(map.get("errorColor"), "rgba(212, 0, 42, 1)", "CouponError Color is different than expected");
        }
    }

    //TODO: Check vehicle selection - coupon is applied on specific vehicles
    @Test(groups = {REGRESSION, SANITY, SMOKE,AVIS}, priority = 11, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_UtypeCouponProcessing_PayLater_US(String pickUpLocation, String couponNo, String fname,
                                                                   String lname, String email, String phoneNo) {

        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_CouponProcessing_PayLater(pickUpLocation, couponNo, fname, lname, email,
                phoneNo);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        //assertTrue(confirmation.isCouponAppliedMessageDisplayed(couponNo), "Coupon Applied Message is not displayed");
        confirmation.closeGetFreeCouponPopup().cancelReservation();
    }

    @Test(groups = {REGRESSION, SANITY, SMOKE,BUDGET}, priority = 11, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Budget_RES_U_type_Coupon_PayLater_US(String pickUpLocation, String couponNo, String fname,
                                                                   String lname, String email, String phoneNo) {

        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_CouponProcessing_PayLater(pickUpLocation, couponNo, fname, lname, email,
                phoneNo);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        assertTrue(confirmation.isCouponAppliedMessageDisplayed(couponNo), "Coupon Applied Message is not displayed");
        confirmation.closeGetFreeCouponPopup().cancelReservation();
    }


    @Test(groups = {REGRESSION, SANITY, SMOKE,AVIS}, priority = 12, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_MtypeCouponProcessing_PayLater_US(String pickUpLocation, String couponNo, String fname,
                                                                   String lname, String email, String phoneNo) {

        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_CouponProcessing_PayLater(pickUpLocation, couponNo, fname, lname, email,
                phoneNo);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        assertTrue(confirmation.isCouponAppliedMessageDisplayed(couponNo), "Coupon Applied Message is not displayed");
        confirmation.closeGetFreeCouponPopup().cancelReservation();
    }


    @Test(groups = {REGRESSION, SMOKE,AVIS}, priority = 13, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_Inbound_PayLater_US(String pickUpLocation, String Country, String firstName, String lastName,
                                                     String email, String phoneNumber) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_Inbound_PayLater(pickUpLocation, Country, firstName, lastName, email,
                phoneNumber);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SMOKE,AVIS}, priority = 14, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_Inbound_PayNow_US(String pickUpLocation, String Country, String firstName, String lastName,
                                                   String email, String phoneNumber, String ccNumber, String cvv) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_Inbound_PayNow(pickUpLocation, Country, firstName, lastName, email, phoneNumber,
                ccNumber, cvv);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        confirmation.cancelReservationWithConfirmationBox();
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

    @Test(groups = {REGRESSION, SMOKE,AVIS}, priority = 16, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_SnowChain_PayLater_US(String pickUpLocation, String firstName, String lastName, String email,
                                                       String phoneNo) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_SnowChain_PayLater(pickUpLocation, firstName, lastName, email, phoneNo);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        assertTrue(confirmation.isSnowChainProductPresent(), "Snowchain product is not displayed");
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SMOKE,AVIS}, priority = 17, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_Recognised_Paylater_US(String pickUpLocation, String wizardNo, String lastName,
                                                        String pickUpLocation1) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_Recognised_Paylater(pickUpLocation, wizardNo, lastName, pickUpLocation1);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SMOKE,AVIS}, priority = 18, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_OutboundAndMultiCurrency_Paylater_US(String pickUpLocation, String residencyLocation, String firstName, String lastName,
                                                                      String email, String phoneNumber, String flightNumber, String currencyValue) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_OutboundAndMultiCurrency_Paylater(pickUpLocation, residencyLocation, firstName, lastName, email,
                phoneNumber, flightNumber,currencyValue);

        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        assertTrue(confirmation.verifyCurrencyOnConfirmationPage(currencyValue), "Currency value is incorrect");
        confirmation.cancelReservation();
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


    @Test(groups = {REGRESSION, SANITY, SMOKE,AVIS}, priority = 21, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_UnderAgeAndHomePageErrorMessageAndPersonalInfoValidation_Paylater_US(String pickUpLocation,
                                                                                                      String age, String firstName, String lastName, String email, String phoneNo) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_UnderAgeAndHomePageErrorMessageAndPersonalInfoValidation_Paylater(pickUpLocation,
                age, firstName, lastName, email, phoneNo);
        confirmation.cancelReservation();
    }

    //
    // @Test(groups = {REGRESSION, SANITY,SMOKE},priority=22, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_FTPPayLater_Authenticated_US(String wizardNo, String password, String pickUpLocation,
                                                              String partnerName, String membershipNo) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        reservationHelper.Reservation_FTPPayLater_Authenticated(wizardNo, password, pickUpLocation, partnerName,
                membershipNo);

    }

    //    @Test(groups = {REGRESSION, SANITY,SMOKE},priority=23, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_AuthenticatedAndDomestic_Paylater_US(String pickUpLocation, String wizardNo, String password,
                                                                      String pickUpLocation1) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_AuthenticatedAndDomestic_Paylater(pickUpLocation, wizardNo, password,
                pickUpLocation1);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        confirmation.cancelReservation();
    }

    //    @Test(groups = {REGRESSION, SANITY, SMOKE},priority=24, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_SMSOptInProfile_Verification_US(String wizardNo, String password, String pickUpLocation,
                                                                 String phoneNo) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_SMSOptInProfile_Verification(wizardNo, password, pickUpLocation, phoneNo);
        confirmation.closeGetFreeCouponPopup().cancelReservation();
    }

    //    @Test(groups = {REGRESSION, SANITY, SMOKE},priority=25, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_LastMinuteBundle_PayLater_US(String pickUpLocation, String firstName, String LastName,
                                                              String email, String phoneNo) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_LastMinuteBundle_PayLater(pickUpLocation, firstName, LastName, email, phoneNo);
        confirmation.closeGetFreeCouponPopup().cancelReservation();
    }

    @Test(groups = {REGRESSION, SANITY, SMOKE,AVIS}, priority = 26, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_AnonymousDomesticAWDorBDCorPDNAndIATAAndKeyDrop_Paylater_US(String pickUpLocation,
                                                                                             String dropOffTime, String awd, String firstName, String lastName, String email, String phoneNo,
                                                                                             String iataNo, String pickUpLocation_new) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_AnonymousDomesticAWDorBDCorPDNAndIATAAndKeyDrop_Paylater(pickUpLocation,
                dropOffTime, awd, firstName, lastName, email, phoneNo, iataNo, pickUpLocation_new);
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SANITY, SMOKE,AVIS}, priority = 27, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_MandateflightInfoAndTtypeCouponProcessingStrikeThrough_Paylater_US(String pickUpLocation,
                                                                                                    String firstName, String lastName, String emailId, String phoneNo, String flightNumber,
                                                                                                    String pickUpLocation1, String couponNo) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_MandateflightInfoAndTtypeCouponProcessingStrikeThrough_Paylater(pickUpLocation,
                firstName, lastName, emailId, phoneNo, flightNumber, pickUpLocation1, couponNo);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        assertTrue(confirmation.isCouponAppliedMessageDisplayed(couponNo), "Coupon Applied Message is not displayed");
        confirmation.closeGetFreeCouponPopup().cancelReservation();
    }

     @Test(groups = {REGRESSION, SANITY,SMOKE,AVIS},priority=28, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_TtypeCoupon_PayLater_US(String pickUpLocation, String couponNo, String fname,
                                                                   String lname, String email, String phoneNo, String couponMsg) {

        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_TtypeCouponProcessing_PayLater(pickUpLocation, couponNo, fname, lname, email,
                phoneNo,couponMsg);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        assertTrue(confirmation.isCouponAppliedMessageDisplayed(couponNo), "Coupon Applied Message is not displayed");
        assertTrue(confirmation.isCouponCodeMessageDisplayed(couponMsg),"Coupon Applied Message is not displayed");
        confirmation.closeGetFreeCouponPopup().cancelReservation();
    }

    @Test(groups = {REGRESSION, SANITY,SMOKE,BUDGET},priority=28, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Budget_RES_T_typeCoupon_CCOLocation_PayLater_US(String pickUpLocation, String couponNo, String fname,
                                                         String lname, String email, String phoneNo, String couponMsg) {

        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_TtypeCouponProcessing_PayLater(pickUpLocation, couponNo, fname, lname, email,
                phoneNo,couponMsg);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        assertTrue(confirmation.isCouponAppliedMessageDisplayed(couponNo), "Coupon Applied Message is not displayed");
        assertTrue(confirmation.isCouponCodeMessageDisplayed(couponMsg),"Coupon Applied Message is not displayed");
        confirmation.closeGetFreeCouponPopup().cancelReservation();
    }

    // @Test(groups = {REGRESSION, SMOKE},priority=29, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_UTypeKeyDropLocation_PayLater_US(String pickUpLocation, String dropOffTime, String couponNo, String fname, String lname,
                                                                  String email, String phoneNo) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_MTypeKeyDropLocation_PayLater(pickUpLocation, dropOffTime, couponNo, fname, lname, email, phoneNo);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        confirmation.cancelReservation();
    }

    // @Test(groups = {REGRESSION, SANITY, SMOKE},priority=30, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_AnonymousDomesticAWD_Paylater_US(String pickUpLocation, String awd, String firstName, String lastName, String email, String phoneNo) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_AnonymousDomesticAWD_Paylater(pickUpLocation, awd, firstName, lastName, email, phoneNo);
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SANITY, SMOKE,BUDGET},priority=30, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Budget_RES_Domestic_PayLater_US(String pickUpLocation, String firstName, String lastName, String email,
                                                String phoneNo) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_Domestic_PayLater( pickUpLocation,  firstName,  lastName,  email,
                 phoneNo);
        assertTrue(confirmation.isConfirmationNumberDisplayed(),CONFIRMATION_NUMBER_NOT_DISPLAYED_MESSAGE);
        confirmation.closeGetFreeCouponPopup().cancelReservation();
    }

    @Test(groups = {REGRESSION, SMOKE,AVIS}, priority = 31, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_KeyDropLocation_M_typeCoupon_PayLater_US(String pickUpLocation, String dropOffTime, String couponNo, String fname, String lname,
                                                                  String email, String phoneNo) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_MTypeKeyDropLocation_PayLater(pickUpLocation, dropOffTime, couponNo, fname, lname, email, phoneNo);
        assertTrue(confirmation.isConfirmationNumberDisplayed(),CONFIRMATION_NUMBER_NOT_DISPLAYED_MESSAGE);
        assertTrue(confirmation.closeGetFreeCouponPopup().isCouponAppliedMessageDisplayed(couponNo),COUPON_NOT_APPLIED_MESSAGE);
        confirmation.closeGetFreeCouponPopup().keyDropMessageValidation();
        confirmation.closeGetFreeCouponPopup().cancelReservation();
        // confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SMOKE,AVIS}, priority = 32, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_OneWay_USAA_PayLater_US(String pickUpLocation, String dropOffLocation, String awd, String membershipNo, String fname, String lname,
                                                 String email, String phoneNo) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_OneWay_USAA_PayLater(pickUpLocation, dropOffLocation, awd, membershipNo, fname, lname, email, phoneNo);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), CONFIRMATION_NUMBER_NOT_DISPLAYED_MESSAGE);
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SMOKE,BUDGET}, priority = 32, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Budget_RES_OneWay_USAA_PayLater_US(String pickUpLocation, String dropOffLocation, String awd, String membershipNo, String fname, String lname,
                                                 String email, String phoneNo) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_OneWay_USAA_PayLater(pickUpLocation, dropOffLocation, awd, membershipNo, fname, lname, email, phoneNo);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), CONFIRMATION_NUMBER_NOT_DISPLAYED_MESSAGE);
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SMOKE,AVIS}, priority = 33, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_Inbound_Domestic_MultiCurrency_PayLater_US(String pickUpLocation, String residencyLocation, String firstName, String lastName,
                                                                    String email, String phoneNumber, String flightNumber,String residentCurrencySymbol, String currencyValue) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_InboundAndMultiCurrency_Paylater(pickUpLocation, residencyLocation, firstName, lastName, email,
                phoneNumber, flightNumber,residentCurrencySymbol, currencyValue);

        assertTrue(confirmation.closeGetFreeCouponPopup().isConfirmationNumberDisplayed(), CONFIRMATION_NUMBER_NOT_DISPLAYED_MESSAGE);
        assertTrue(confirmation.verifyCurrencyOnConfirmationPage(currencyValue), CURRENCY_VALUE_INCORRECT_MESSAGE);
        confirmation.cancelReservation();

    }

    @Test(groups = {REGRESSION, SMOKE,BUDGET}, priority = 33, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Budget_RES_Inbound_MultiCurrency_PayLater_US(String pickUpLocation, String residencyLocation, String firstName, String lastName,
                                                                    String email, String phoneNumber, String flightNumber,String residentCurrencySymbol, String currencyValue) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_InboundAndMultiCurrency_Paylater(pickUpLocation, residencyLocation, firstName, lastName, email,
                phoneNumber, flightNumber,residentCurrencySymbol, currencyValue);

        assertTrue(confirmation.closeGetFreeCouponPopup().isConfirmationNumberDisplayed(), CONFIRMATION_NUMBER_NOT_DISPLAYED_MESSAGE);
        assertTrue(confirmation.verifyCurrencyOnConfirmationPage(currencyValue), CURRENCY_VALUE_INCORRECT_MESSAGE);
        confirmation.cancelReservation();
    }


    @Test(groups = {REGRESSION, SMOKE,AVIS}, priority = 34, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_AWD_SplitBillItemized_CorpCust_insuranceCover_Validate_CorpBooking_Paylater_US(String pickUpLoction, String AWD, String corporateEmailId, String fname, String lname,
                                                                                                        String mail, String pNo, String primaryCardNo, String secCardNo) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_SplitBillItemized_CorpCust_PayLater(pickUpLoction, AWD, corporateEmailId, fname, lname,
                mail, pNo, primaryCardNo, secCardNo);

        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        assertTrue(confirmation.isPrimaryCardDetailsDisplayed(), "Primary card details is not displayed");
        assertTrue(confirmation.isSecondaryCardDetailsDisplayed(), "Secondary card details is not displayed");
        confirmation.closeGetFreeCouponPopup().cancelReservation();
        //confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SMOKE,BUDGET}, priority = 34, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Budget_RES_BCD_CorpCust_insuranceCover_Validate_Paylater_US(String pickUpLoction, String BCD, String corporateEmailId, String fname, String lname,
                                                                                                        String mail, String pNo) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_CorpCust_PayLater(pickUpLoction, BCD, corporateEmailId, fname, lname,
                mail, pNo);

        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        confirmation.closeGetFreeCouponPopup().cancelReservation();

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
        // confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SANITY, SMOKE,BUDGET}, priority = 35, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Budget_RES_G_typeCoupon__SMSCheckbox_IATA_PayLater_US(String pickUpLocation, String couponNo, String fname,
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


    @Test(groups = {REGRESSION, SANITY, SMOKE,AVIS}, priority = 36, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_UtypeCoupon_Tierbundle_PayLater_US(String pickUpLocation, String pickupTime, String dropOffTime, String couponNo, String fname,
                                                            String lname, String email, String phoneNo, String couponText) {

        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_UTypeCouponProcessing_tierbundle_PayLater(pickUpLocation, pickupTime, dropOffTime, couponNo, fname, lname, email, phoneNo, couponText);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        assertTrue(confirmation.isCouponAppliedMessageDisplayed(couponNo), "Coupon Applied Message is not displayed");
        confirmation.closeGetFreeCouponPopup();
        confirmation.ClickRentalOption();
        assertTrue(confirmation.verifyGPSCoverageOnConfirmationPage(), "GPS Coverage is not displayed");
        //assertTrue(confirmation.verifyRSNCoverageOnConfirmationPage(), "RSN Coverage is not displayed");
        assertTrue(confirmation.isCouponCodeMessageDisplayed(couponText), "Coupon Code is not displayed");
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SMOKE,AVIS}, priority = 37, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_Costco_DigitalWallet_PayPal_PayLater_US(String pickUpLocation, String awd, String membershipNo, String fname, String lname,
                                                                 String email, String phoneNo, String paypalEmail, String paypalPassword) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_Costco_DigitalWallet_Paypal_PayLater(pickUpLocation, awd, membershipNo, fname, lname, email, phoneNo, paypalEmail, paypalPassword);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), CONFIRMATION_NUMBER_NOT_DISPLAYED_MESSAGE);
        assertTrue(confirmation.isAWDCouponMessageDisplayed(), "AWD message is not displayed");
        assertTrue(confirmation.isAwdConfirmationPageTextDisplayed(awd), "AWD Confirmation text is not displayed");
        assertTrue(confirmation.isCardTypePaypalDisplayed(), "Card Type Paypal is not displayed");
        //confirmation.cancelReservation();
        confirmation.cancelReservationWithConfirmationBox();
    }

    @Test(groups = {REGRESSION, SMOKE,BUDGET}, priority = 37, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Budget_RES_Costco_DigitalWallet_PayPal_PayLater_US(String pickUpLocation, String awd, String membershipNo, String fname, String lname,
                                                                 String email, String phoneNo, String paypalEmail, String paypalPassword) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_Costco_DigitalWallet_Paypal_PayLater(pickUpLocation, awd, membershipNo, fname, lname, email, phoneNo, paypalEmail, paypalPassword);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), CONFIRMATION_NUMBER_NOT_DISPLAYED_MESSAGE);
        assertTrue(confirmation.isAWDCouponMessageDisplayed(), "AWD message is not displayed");
        assertTrue(confirmation.isAwdConfirmationPageTextDisplayed(awd), "AWD Confirmation text is not displayed");
        assertTrue(confirmation.isCardTypePaypalDisplayed(), "Card Type Paypal is not displayed");
        //confirmation.cancelReservation();
        confirmation.cancelReservationWithConfirmationBox();
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


    @Test(groups = {REGRESSION, SMOKE,BUDGET}, priority = 38, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Budget_RES_Outbound_StrikeThroughCoupon_LocMandate_FlightInfo_Cancellation_PayLater_US(String pickUpLocation, String residencyLocation, String awd,String MembershipNum, String fname, String lname,
                                                                              String email, String phoneNo, String flightNumber) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_OutboundAndStrikeThroughCoupon_LocMandate_FlightInfo_Paylater(pickUpLocation, residencyLocation, awd, MembershipNum,  fname, lname, email, phoneNo, flightNumber);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        assertTrue(confirmation.isAWDCouponMessageDisplayed(), "AWD message is not displayed");
        assertTrue(confirmation.isAwdConfirmationPageTextDisplayed(awd), "AWD Confirmation text is not displayed");
        confirmation.cancelReservation();

    }

    @Test(groups = {REGRESSION, SMOKE,AVIS}, priority = 40, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_FlightInfo_DigitalWallet_PayPal_PayNow_US(String pickUpLocation, String fname, String lname,
                                                                 String email, String phoneNo, String paypalEmail, String paypalPassword, String flightName, String flightNumber) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_FlightInfo_DigitalWallet_Paypal_PayNow(pickUpLocation, fname, lname, email, phoneNo, paypalEmail, paypalPassword, flightName, flightNumber);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        assertTrue(confirmation.isCarReservedTextDisplayed(), "Car reserved text is not displayed");
        assertTrue(confirmation.validatePickupAndReturnLocValue(pickUpLocation,pickUpLocation), "Pickup and Drop Loc is not displayed");
        assertTrue(confirmation.isPickUpDateTimeDisplayed("12:00 PM"), "Pickup Time is not Displayed");
        assertTrue(confirmation.isDropDateTimeDisplayed("12:00 PM"),"Drop Time is not Displayed");
        assertTrue(confirmation.isCardTypePaypalDisplayed(), "Card Type Paypal is not displayed");
        //confirmation.cancelReservation();
        assertTrue(confirmation.isFlightInfoDisplayed(flightName), "Flight Info is not displayed");
        confirmation.cancelReservationWithConfirmationBox();
    }

    @Test(groups = {REGRESSION, SMOKE,BUDGET}, priority = 39, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Budget_RES_FlightInfo_DigitalWallet_PayPal_PayNow_US(String pickUpLocation, String fname, String lname,
                                                                   String email, String phoneNo, String paypalEmail, String paypalPassword, String flightName, String flightNumber) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_FlightInfo_DigitalWallet_Paypal_PayNow(pickUpLocation, fname, lname, email, phoneNo, paypalEmail, paypalPassword, flightName, flightNumber);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        assertTrue(confirmation.isCarReservedTextDisplayed(), "Car reserved text is not displayed");
        assertTrue(confirmation.validatePickupAndReturnLocValue(pickUpLocation,pickUpLocation), "Pickup and Drop Loc is not displayed");
        assertTrue(confirmation.isPickUpDateTimeDisplayed("12:00 PM"), "Pickup Time is not Displayed");
        assertTrue(confirmation.isDropDateTimeDisplayed("12:00 PM"),"Drop Time is not Displayed");
        assertTrue(confirmation.isCardTypePaypalDisplayed(), "Card Type Paypal is not displayed");
        //confirmation.cancelReservation();
        assertTrue(confirmation.isFlightInfoDisplayed(flightName), "Flight Info is not displayed");
        confirmation.cancelReservationWithConfirmationBox();
    }


    @Test(groups = {REGRESSION, SMOKE,AVIS}, priority = 40, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_Inbound_MultiCurrency_DCCPrePay_IATA_Cancellation_PayNow_US(String pickUpLocation, String residencyLocation,String awd, String corporateEmailId, String firstName, String lastName,
                                                                    String email, String phoneNumber, String IATA,String ccNo, String cvv, String residentCurrencySymbol, String USCurrencyValue) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_InboundAndMultiCurrency_IATA_PayNow(pickUpLocation, residencyLocation, awd, corporateEmailId, firstName, lastName, email,
                phoneNumber, IATA, ccNo, cvv, residentCurrencySymbol, USCurrencyValue);

        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        assertTrue(confirmation.isCarReservedTextDisplayed(), "Car reserved text is not displayed");
        assertTrue(confirmation.validatePickupAndReturnLocValue(pickUpLocation,pickUpLocation), "Pickup and Drop Loc is not displayed");
        assertTrue(confirmation.isPickUpDateTimeDisplayed("12:00 PM"), "Pickup Time is not Displayed");
        assertTrue(confirmation.isDropDateTimeDisplayed("12:00 PM"),"Drop Time is not Displayed");
        assertTrue(confirmation.verifyCurrencyOnConfirmationPage(USCurrencyValue), "Currency value is incorrect");
       // assertTrue(confirmation.isAWDCouponMessageDisplayed(), "AWD message is not displayed");
        assertTrue(confirmation.isAwdConfirmationPageTextDisplayed(awd), "AWD Confirmation text is not displayed");
        assertTrue(confirmation.isIATAValueDisplayed(IATA), "IATA value is not displayed");
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SMOKE,BUDGET}, priority = 40, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Budget_RES_Inbound_MultiCurrency_IATA_Cancellation_PayNow_US(String pickUpLocation, String residencyLocation,String awd, String corporateEmailId, String firstName, String lastName,
                                                                                     String email, String phoneNumber, String IATA,String ccNo, String cvv, String residentCurrencySymbol, String USCurrencyValue) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_InboundAndMultiCurrency_IATA_PayNow(pickUpLocation, residencyLocation, awd, corporateEmailId, firstName, lastName, email,
                phoneNumber, IATA, ccNo, cvv, residentCurrencySymbol, USCurrencyValue);

        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        assertTrue(confirmation.isCarReservedTextDisplayed(), "Car reserved text is not displayed");
        assertTrue(confirmation.validatePickupAndReturnLocValue(pickUpLocation,pickUpLocation), "Pickup and Drop Loc is not displayed");
        assertTrue(confirmation.isPickUpDateTimeDisplayed("12:00 PM"), "Pickup Time is not Displayed");
        assertTrue(confirmation.isDropDateTimeDisplayed("12:00 PM"),"Drop Time is not Displayed");
        assertTrue(confirmation.verifyCurrencyOnConfirmationPage(USCurrencyValue), "Currency value is incorrect");
        // assertTrue(confirmation.isAWDCouponMessageDisplayed(), "AWD message is not displayed");
       // assertTrue(confirmation.isAwdConfirmationPageTextDisplayed(awd), "AWD Confirmation text is not displayed");
        assertTrue(confirmation.isIATAValueDisplayed(IATA), "IATA value is not displayed");
        confirmation.cancelReservation();
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

  //  @Test(groups = {REGRESSION, SMOKE}, priority = 42, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_DigitalWallet_AmazonPay_Paynow_US(String pickUpLocation, String fname, String lname,
                                                                   String email, String phoneNo, String AmazonEmail, String AmazonPassword) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_DigitalWallet_AmazonPay_PayNow(pickUpLocation, fname, lname, email, phoneNo, AmazonEmail, AmazonPassword);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        confirmation.cancelReservationWithConfirmationBox();
    }

    @Test(groups = {REGRESSION, SMOKE,AVIS}, priority = 43, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_Modify_flow_Step1_to_step4_US(String pickUpLocation, String firstName, String lastName,
                                                                                     String email, String phoneNumber,String ccNo, String cvv, String Country, String modifiedPickupLocation) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation =  reservationHelper.Reservation_ModifyFlow_PayNow(pickUpLocation, firstName, lastName, email,
                phoneNumber, ccNo, cvv, Country, modifiedPickupLocation);
        confirmation.GetConfirmationNumber();
        confirmation.isEmailSentTextDisplayed(email);
        confirmation.isModifiedReservationTextDisplayed(firstName);

    }
    @Test(groups = {REGRESSION, SMOKE,BUDGET}, priority = 44, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Budget_RES_Modify_flow_Step1_to_step4_US(String pickUpLocation, String firstName, String lastName,
                                                       String email, String phoneNumber,String ccNo, String cvv, String Country, String modifiedPickupLocation) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation =  reservationHelper.Reservation_ModifyFlow_PayNow(pickUpLocation, firstName, lastName, email,
                phoneNumber, ccNo, cvv, Country, modifiedPickupLocation);
        confirmation.GetConfirmationNumber();
        confirmation.isEmailSentTextDisplayed(email);
        confirmation.isModifiedReservationTextDisplayed(firstName);

    }


}
