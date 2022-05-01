package com.avis.qa;


import com.avis.qa.components.ReservationWidget;
import com.avis.qa.core.TestBase;
import com.avis.qa.helpers.ReservationHelper;
import com.avis.qa.pages.Confirmation;
import com.avis.qa.pages.Extras;
import com.avis.qa.pages.Homepage;
import com.avis.qa.pages.ReviewAndBook;
import com.avis.qa.utilities.CSVUtils;
import org.testng.annotations.Test;

import static com.avis.qa.constants.AvisConstants.*;
import static com.avis.qa.constants.TextComparison.DUMMY_CC_ERROR_MESSAGE;
import static org.testng.Assert.*;

/**
 * Class to test the reservation functionality
 *
 * @author ikumar
 */
public class ReservationTests extends TestBase {


    /**
     * ALM Testcase: Res_Avis_US_007_Reservation_Outbound_PayNowAnonymous
     * ALM Testcase: Res_Avis_US_007_Reservation_Outbound_PayNow
     */
    @Test(groups = {REGRESSION, SMOKE}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Reservation_Outbound_PayNow(String pickUpLocation, String firstName, String lastName, String email, String phoneNumber, String ccNumber, String cvv) {
        launchUrl();
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
    @Test(groups = {REGRESSION}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Reservation_Domestic_PayNow(String pickUpLocation, String firstName, String lastName, String email,
                                            String phoneNumber, String ccNumber, String cvv) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        reservationHelper.Reservation_DomesticOrOutbound_PayNow(pickUpLocation, firstName, lastName, email, phoneNumber,
                ccNumber, cvv);
        Confirmation confirmation = new Confirmation(getDriver());
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        confirmation.cancelReservationWithConfirmationBox();
    }

    @Test(groups = {REGRESSION}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Reservation_CCOLocation_PayLater(String pickUpLocation, String firstName, String lastName, String email,
                                                 String phoneNo, String cCNumber) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        ReviewAndBook reviewAndBook = reservationHelper.Reservation_CCOLocation_PayLater(pickUpLocation, firstName, lastName, email, phoneNo,
                cCNumber);
        assertFalse(reviewAndBook.cvvCCOValidation(), "CVV is displayed");
    }


    @Test(groups = {REGRESSION}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Reservation_Costco_PayLater(String pickUpLocation, String awd, String membershipNo, String fname,
                                            String lname, String email, String phoneNo) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_Costco_USAA_PayLater(pickUpLocation, awd, membershipNo, fname, lname, email, phoneNo);
        assertTrue(confirmation.isAwdConfirmationPageTextDisplayed(), "AWD Confirmation text is not displayed");
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SANITY}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Reservation_EMEA90Days_ErrorMessage(String pickUpLocation, String months) {
        launchUrl();
        ReservationWidget reservationWidget = new ReservationWidget(getDriver());

        reservationWidget
                .pickUpLocation(pickUpLocation)
                .aboveThirtyDaysCalendarSelection(months)
                .selectMyCar();
        assertTrue(reservationWidget.isErrorMessageDisplayed(months));
    }

    @Test(groups = {REGRESSION, SANITY}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Reservation_EMEA330Days_ErrorMessage(String pickUpLocation, String months) {
        launchUrl();
        ReservationWidget reservationWidget = new ReservationWidget(getDriver());

        reservationWidget
                .pickUpLocation(pickUpLocation)
                .aboveThirtyDaysCalendarSelection(months)
                .selectMyCar();
        assertTrue(reservationWidget.isErrorMessageDisplayed(months));
    }

    @Test(groups = {REGRESSION, SANITY, SMOKE}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Reservation_ErrorMessage_PayNow(String pickUpLocation, String firstName, String lastName, String email,
                                                String phoneNo, String ccNo, String cvv) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        reservationHelper.Reservation_DomesticOrOutbound_PayNow(pickUpLocation, firstName, lastName, email, phoneNo,
                ccNo, cvv);
        ReviewAndBook reviewAndBook = new ReviewAndBook(getDriver());
        assertEquals(reviewAndBook.getDummyCreditCardErrorMessage(), DUMMY_CC_ERROR_MESSAGE, "Credit card error message is incorrect");
    }

    @Test(groups = {REGRESSION, SANITY}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Reservation_OneClick_Step3(String url) {
        launchUrl();
        Homepage homepage = new Homepage(getDriver());
        homepage.oneClickLandingPage(url);

        Extras extras = new Extras(getDriver());
        assertTrue(extras.isSubmitStepThreeDisplayed(), "Submit step three button is not displayed");
    }

    //@Test(groups = {REGRESSION, SANITY}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Reservation_OneClick_GPSSelection(String url) {
        launchUrl();
        Homepage homepage = new Homepage(getDriver());
        homepage.oneClickLandingPage(url);

        ReviewAndBook reviewAndBook = new ReviewAndBook(getDriver());
        Confirmation confirmation = reviewAndBook.keepModification();
        assertTrue(confirmation.isExtrasAddedMessageDisplayed(), "Extra Added Message is not displayed");
    }

    @Test(groups = {REGRESSION}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Reservation_GEBUser_PayLater(String pickUpLocation, String wizardNo, String lastName, String firstName,
                                             String email, String phoneNo) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_GEBUser_PayLater(pickUpLocation, wizardNo, lastName, firstName, email, phoneNo);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SANITY}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Reservation_GtypeCouponProcessing_PayLater(String pickUpLocation, String couponNo, String fname,
                                                           String lname, String email, String phoneNo) {

        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_CouponProcessing_PayLater(pickUpLocation, couponNo, fname, lname, email,
                phoneNo);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        assertTrue(confirmation.isCouponAppliedMessageDisplayed(couponNo), "Coupon Applied Message is not displayed");
        confirmation.closeGetFreeCouponPopup().cancelReservation();
    }

    //TODO: Check vehicle selection - coupon is applied on specific vehicles
    @Test(groups = {REGRESSION, SANITY}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Reservation_UtypeCouponProcessing_PayLater(String pickUpLocation, String couponNo, String fname,
                                                           String lname, String email, String phoneNo) {

        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_CouponProcessing_PayLater(pickUpLocation, couponNo, fname, lname, email,
                phoneNo);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        //assertTrue(confirmation.isCouponAppliedMessageDisplayed(couponNo), "Coupon Applied Message is not displayed");
        confirmation.closeGetFreeCouponPopup().cancelReservation();
    }

    @Test(groups = {REGRESSION, SANITY}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Reservation_MtypeCouponProcessing_PayLater(String pickUpLocation, String couponNo, String fname,
                                                           String lname, String email, String phoneNo) {

        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_CouponProcessing_PayLater(pickUpLocation, couponNo, fname, lname, email,
                phoneNo);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        assertTrue(confirmation.isCouponAppliedMessageDisplayed(couponNo), "Coupon Applied Message is not displayed");
        confirmation.closeGetFreeCouponPopup().cancelReservation();
    }


    @Test(groups = {REGRESSION}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Reservation_Inbound_PayLater(String pickUpLocation, String Country, String firstName, String lastName,
                                             String email, String phoneNumber) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_Inbound_PayLater(pickUpLocation, Country, firstName, lastName, email,
                phoneNumber);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Reservation_Inbound_PayNow(String pickUpLocation, String Country, String firstName, String lastName,
                                           String email, String phoneNumber, String ccNumber, String cvv) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_Inbound_PayNow(pickUpLocation, Country, firstName, lastName, email, phoneNumber,
                ccNumber, cvv);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        confirmation.cancelReservationWithConfirmationBox();
    }

    @Test(groups = {REGRESSION}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Reservation_USAA_PayLater(String pickUpLocation, String awd, String membershipNo, String fname,
                                          String lname, String email, String phoneNo) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_Costco_USAA_PayLater(pickUpLocation, awd, membershipNo, fname, lname, email, phoneNo);
        assertTrue(confirmation.isAwdConfirmationPageTextDisplayed(), "AWD Confirmation text is not displayed");
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        confirmation.cancelReservation();
    }

    //@Test(groups = {REGRESSION}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Reservation_SnowChain_PayLater(String pickUpLocation, String firstName, String lastName, String email,
                                               String phoneNo) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_SnowChain_PayLater(pickUpLocation, firstName, lastName, email, phoneNo);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        assertTrue(confirmation.isSnowChainProductPresent(), "Snowchain product is not displayed");
        confirmation.cancelReservation();
    }

    //@Test(groups = {REGRESSION}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Reservation_Recognised_Paylater(String pickUpLocation, String wizardNo, String lastName,
                                                String pickUpLocation1) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_Recognised_Paylater(pickUpLocation, wizardNo, lastName, pickUpLocation1);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Reservation_OutboundAndMultiCurrency_Paylater(String pickUpLocation, String firstName, String lastName,
                                                              String email, String phoneNumber, String flightNumber) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_OutboundAndMultiCurrency_Paylater(pickUpLocation, firstName, lastName, email,
                phoneNumber, flightNumber);

        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        assertTrue(confirmation.verifyCurrencyOnConfirmationPage(), "Currency value is incorrect");
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Reservation_OneWay_PayLater(String pickUpLoction, String dropOffLocation, String fname, String lname,
                                            String email, String phoneNo) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_OneWay_PayLater(pickUpLoction, dropOffLocation, fname, lname, email, phoneNo);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SANITY}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Reservation_TierBundle_PayLater(String pickUpLocation, String firstName, String lastName, String email,
                                                String phoneNo, String ccNo, String cvv) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_TierBundle_PayLater(pickUpLocation, firstName, lastName, email, phoneNo, ccNo,
                cvv);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        assertTrue(confirmation.verifyRentalOptionsText(), "Rental Options Text is incorrect");
        confirmation.closeGetFreeCouponPopup().cancelReservation();
    }


    @Test(groups = {REGRESSION, SANITY}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Reservation_UnderAgeAndHomePageErrorMessageAndPersonalInfoValidation_Paylater(String pickUpLocation,
                                                                                              String age, String firstName, String lastName, String email, String phoneNo) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_UnderAgeAndHomePageErrorMessageAndPersonalInfoValidation_Paylater(pickUpLocation,
                age, firstName, lastName, email, phoneNo);
        confirmation.cancelReservation();
    }

    //@Test(groups = {REGRESSION, SANITY}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Reservation_FTPPayLater_Authenticated(String wizardNo, String password, String pickUpLocation,
                                                      String partnerName, String membershipNo) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        reservationHelper.Reservation_FTPPayLater_Authenticated(wizardNo, password, pickUpLocation, partnerName,
                membershipNo);

    }

    //@Test(groups = {REGRESSION, SANITY}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Reservation_AuthenticatedAndDomestic_Paylater(String pickUpLocation, String wizardNo, String password,
                                                              String pickUpLocation1) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_AuthenticatedAndDomestic_Paylater(pickUpLocation, wizardNo, password,
                pickUpLocation1);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        confirmation.cancelReservation();
    }

    //@Test(groups = {REGRESSION, SANITY}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Reservation_SMSOptInProfile_Verification(String wizardNo, String password, String pickUpLocation,
                                                         String phoneNo) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_SMSOptInProfile_Verification(wizardNo, password, pickUpLocation, phoneNo);
        confirmation.closeGetFreeCouponPopup().cancelReservation();
    }

    // @Test(groups = {REGRESSION, SANITY}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Reservation_LastMinuteBundle_PayLater(String pickUpLocation, String firstName, String LastName,
                                                      String email, String phoneNo) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_LastMinuteBundle_PayLater(pickUpLocation, firstName, LastName, email, phoneNo);
        confirmation.closeGetFreeCouponPopup().cancelReservation();
    }

    @Test(groups = {REGRESSION, SANITY}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Reservation_AnonymousDomesticAWDorBDCorPDNAndIATAAndKeyDrop_Paylater(String pickUpLocation,
                                                                                     String dropOffTime, String awd, String firstName, String lastName, String email, String phoneNo,
                                                                                     String iataNo, String pickUpLocation_new) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_AnonymousDomesticAWDorBDCorPDNAndIATAAndKeyDrop_Paylater(pickUpLocation,
                dropOffTime, awd, firstName, lastName, email, phoneNo, iataNo, pickUpLocation_new);
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SANITY}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Reservation_MandateflightInfoAndTtypeCouponProcessingStrikeThrough_Paylater(String pickUpLocation,
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


}
