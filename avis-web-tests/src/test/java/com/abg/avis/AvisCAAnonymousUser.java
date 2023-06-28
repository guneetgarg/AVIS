package com.abg.avis;


import com.avis.qa.components.Header;
import com.avis.qa.components.ReservationWidget;
import com.avis.qa.core.Configuration;
import com.avis.qa.core.TestBase;
import com.avis.qa.helpers.LocationHelper;
import com.avis.qa.helpers.MiscHelper;
import com.avis.qa.helpers.ReservationHelper;
import com.avis.qa.pages.*;
import com.avis.qa.utilities.CSVFileReader;
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
public class AvisCAAnonymousUser extends TestBase {



    @Test(groups = {REGRESSION, SANITY, SMOKE,AVIS}, priority = 35, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_G_typeCoupon_LocMandate_FlightInfo_SMSCheckbox_IATA_PayLater_CA(String pickUpLocation, String couponNo, String fname,
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
    public void Avis_RES_Outbound_CorpCust_insuranceCover_Validate_CorpBooking_Paynow_CA(String pickUpLocation, String pickupTime,String awd, String corporateEmailId, String firstName, String lastName,
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
    public void Avis_RES_Outbound_StrikeThroughCoupon_Cancelation_PayLater_CA(String pickUpLocation, String residencyLocation, String awd, String fname, String lname,
                                                                              String email, String phoneNo, String flightNumber) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_OutboundAndStrikeThroughCoupon_Paylater(pickUpLocation, residencyLocation, awd, fname, lname, email, phoneNo, flightNumber);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        assertTrue(confirmation.isAWDCouponMessageDisplayed(), "AWD message is not displayed");
        assertTrue(confirmation.isAwdConfirmationPageTextDisplayed(awd), "AWD Confirmation text is not displayed");
        confirmation.cancelReservation();

    }

    @Test(groups = {REGRESSION,SMOKE, AVIS},priority=2, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_ErrorMessage_PayNow_CA(String pickUpLocation, String firstName, String lastName, String email,
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
    public void Avis_Reservation_OutboundAndMultiCurrency_Paylater_CA(String pickUpLocation, String residencyLocation, String firstName, String lastName,
                                                                      String email, String phoneNumber, String flightNumber, String currencyValue) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_OutboundAndMultiCurrency_Paylater(pickUpLocation, residencyLocation, firstName, lastName, email,
                phoneNumber, flightNumber,currencyValue);

        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        assertTrue(confirmation.verifyCurrencyOnConfirmationPage("USD"), "Currency value is incorrect");
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SANITY,SMOKE,AVIS}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Location_Search_MnemonicCodeRes_CA(String pickUpLocation, String firstName, String lastName, String email,
                                                        String phoneNo) {
        launchUrl();
        LocationHelper locationHelper = new LocationHelper(getDriver());
        Confirmation confirmation = locationHelper.Location_Search_MnemonicCodeRes(pickUpLocation, firstName, lastName, email, phoneNo);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION , SMOKE, AVIS},priority=1, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_EMEA90Days_ErrorMessage_Step1_Res_widget_CA(String pickUpLocation, String months) {
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
    public void Avis_RES_Misc_Verify_Underage_onStep1_FleetFliter_VehicleType_Seats_Mileage_Price_onStep2_CA(String pickUpLocation, String age)  {
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

    }

    @Test(groups = {REGRESSION, SMOKE,AVIS}, priority = 15, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_USAA_PayLater_CA(String pickUpLocation, String awd, String membershipNo, String fname,
                                                  String lname, String email, String phoneNo) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_Costco_USAA_PayLater(pickUpLocation, awd, membershipNo, fname, lname, email, phoneNo);
        assertTrue(confirmation.isAwdConfirmationPageTextDisplayed(awd), "AWD Confirmation text is not displayed");
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SANITY, SMOKE, AVIS}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Misc1_OffersPage_Reservation_CA(String pickUpLoction, String dropOffLocation, String fname, String lname,
                                                    String email, String phoneNo) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_OneWay_PayLater(pickUpLoction, dropOffLocation, fname, lname, email, phoneNo);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        confirmation.cancelReservation();
    }
    @Test(groups = {REGRESSION, SANITY, SMOKE,AVIS}, priority = 19, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Location_Browse_Category_CA(String test) {
        launchUrl();
        Homepage homepage = new Homepage(getDriver());
        Locations locations = homepage.goToFindALocationPage();
        locations.browserLocation();
        Assert.assertTrue(locations.isMakeAReservationButtonDisplayed(), "Make a Reservation button is not displayed");
        locations.clickOnMakeAReservationWidget();
    }

    @Test(groups = {REGRESSION, SMOKE,AVIS}, priority = 19, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_OneWay_PayLater_CA(String pickUpLoction, String dropOffLocation, String fname, String lname,
                                                    String email, String phoneNo) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_OneWay_PayLater(pickUpLoction, dropOffLocation, fname, lname, email, phoneNo);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SANITY, SMOKE,AVIS}, priority = 20, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_TierBundle_PayLater_CA(String pickUpLocation, String firstName, String lastName, String email,
                                                        String phoneNo, String ccNo, String cvv) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_TierBundle_PayLater(pickUpLocation, firstName, lastName, email, phoneNo, ccNo,
                cvv);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        assertTrue(confirmation.verifyRentalOptionsText("Extended Roadside Assistance (RSN)"), "Rental Options Text is incorrect");
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SMOKE, AVIS}, priority = 1, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_Outbound_PayNow_CA(String pickUpLocation, String firstName, String lastName, String email, String phoneNumber, String ccNumber, String cvv) {
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

    @Test(groups = {REGRESSION, SMOKE, AVIS}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Misc_CarGuide_Res_CA(String pickUpLoc, String firstName, String lastName, String email, String phoneNo) {
        launchUrl();
        MiscHelper miscHelper = new MiscHelper(getDriver());
        Confirmation confirmation = miscHelper.Misc_CarGuide_Res(pickUpLoc, firstName, lastName, email, phoneNo);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SANITY, SMOKE, AVIS}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Misc_OffersPage_Reservation_CA(String pickUplocation, String firstName, String lastName, String email,
                                                    String phoneNo) {
        launchUrl();
        MiscHelper miscHelper = new MiscHelper(getDriver());
        Confirmation confirmation = miscHelper.Misc_OffersPage_Reservation(pickUplocation, firstName, lastName, email, phoneNo);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        confirmation.cancelReservation();
    }
/*@Test(groups = {REGRESSION, SANITY, PAYLESSCAR}, dataProvider = "dataAsMap", dataProviderClass = CSVFileReader.class)
    public void Avis_Misc_OffersPage_Reservation_CA(Map<?, ?> testDataMap) {
    System.out.println(testDataMap);
        launchUrl();
        MiscHelper miscHelper = new MiscHelper(getDriver());
      Confirmation confirmation = miscHelper.Misc_OffersPage_Reservation(pickUplocation, firstName, lastName, email, phoneNo);
//        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
//        confirmation.cancelReservation();
    }*/



/*    @Test(groups = {REGRESSION, SANITY,SMOKE,AVIS}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Misc_OneClick_VanityURL_CA(String url, String title) {
        launchUrl(getAvisUrl(url));
        assertTrue(getDriver().getTitle().contains(title), "Page title is incorrect");
    }*/

    @Test(groups = {REGRESSION, SANITY, SMOKE, AVIS},priority=3, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_Misc_Step1AndStep4_ErrorMsg_Validation_CA(String pickUpLocation, String pickUpDate, String pickUpTime, String dropOffLocation, String dropOffDate, String dropOffTime, String WizardNumber, String lastName, String awdCode, String corporateEmail, String rateCode, String couponCode, String creditcardNumber) {
        launchUrl();
        MiscHelper miscHelper = new MiscHelper(getDriver());
        miscHelper.Reservation_Misc_Step1AndStep4_ErrorMsg_Validation(pickUpLocation,pickUpDate, pickUpTime, dropOffLocation, dropOffDate,dropOffTime,WizardNumber,lastName,awdCode,corporateEmail,rateCode,couponCode,creditcardNumber);
    }

    @Test(groups = {REGRESSION, SMOKE, AVIS}, priority = 3, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_CCOLocation_PayLater_CA(String pickUpLocation, String firstName, String lastName, String email,
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
    public void Avis_Reservation_Costco_PayLater_CA(String pickUpLocation, String awd, String membershipNo, String fname,
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
    public void Avis_Reservation_EMEA90Days_ErrorMessage_CA(String pickUpLocation, String months) {
        launchUrl();
        ReservationWidget reservationWidget = new ReservationWidget(getDriver());
        reservationWidget
                .pickUpLocation(pickUpLocation)
                .aboveThirtyDaysCalendarSelection(months)
                .selectMyCar();
        assertTrue(reservationWidget.isErrorMessageDisplayed(months));
    }

    @Test(groups = {REGRESSION, SANITY, SMOKE,AVIS}, priority = 6, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_EMEA330Days_ErrorMessage_CA(String pickUpLocation, String months) {
        launchUrl();
        ReservationWidget reservationWidget = new ReservationWidget(getDriver());

        reservationWidget
                .pickUpLocation(pickUpLocation)
                .aboveThirtyDaysCalendarSelection(months)
                .selectMyCar();
        assertTrue(reservationWidget.isErrorMessageDisplayed(months));
    }

    @Test(groups = {REGRESSION, SANITY, SMOKE,AVIS}, priority = 7, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_OneClick_Step3_CA(String url) {
        launchUrl();
        Homepage homepage = new Homepage(getDriver());
        homepage.oneClickLandingPage(url);
        Extras extras = new Extras(getDriver());
        assertTrue(extras.isSubmitStepThreeDisplayed(), "Submit step three button is not displayed");
    }

    @Test(groups = {REGRESSION, SMOKE,AVIS}, priority = 9, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_GEBUser_PayLater_CA(String pickUpLocation, String wizardNo, String lastName, String firstName,
                                                     String email, String phoneNo) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_GEBUser_PayLater(pickUpLocation, wizardNo, lastName, firstName, email, phoneNo);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SANITY, SMOKE,AVIS}, priority = 10, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_GtypeCouponProcessing_PayLater_CA(String pickUpLocation, String couponNo, String fname,
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

    @Test(groups = {REGRESSION, SANITY, SMOKE,AVIS}, priority = 11, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_UtypeCouponProcessing_PayLater_CA(String pickUpLocation, String couponNo, String fname,
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
    public void Avis_Reservation_MtypeCouponProcessing_PayLater_CA(String pickUpLocation, String couponNo, String fname,
                                                                   String lname, String email, String phoneNo) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_CouponProcessing_PayLater(pickUpLocation, couponNo, fname, lname, email,
                phoneNo);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        confirmation.closeGetFreeCouponPopup();
        assertTrue(confirmation.isCouponAppliedMessageDisplayed(couponNo), "Coupon Applied Message is not displayed");
        confirmation.closeGetFreeCouponPopup().cancelReservation();
    }

    @Test(groups = {REGRESSION, SMOKE,AVIS}, priority = 13, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_Inbound_PayLater_CA(String pickUpLocation, String Country, String firstName, String lastName,
                                                     String email, String phoneNumber) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_Inbound_PayLater(pickUpLocation, Country, firstName, lastName, email,
                phoneNumber);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SMOKE,AVIS}, priority = 14, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_Inbound_PayNow_CA(String pickUpLocation, String Country, String firstName, String lastName,
                                                   String email, String phoneNumber, String ccNumber, String cvv) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_Inbound_PayNow(pickUpLocation, Country, firstName, lastName, email, phoneNumber,
                ccNumber, cvv);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        confirmation.cancelReservationWithConfirmationBox();
    }

    @Test(groups = {REGRESSION, SMOKE,AVIS}, priority = 16, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_SnowChain_PayLater_CA(String pickUpLocation, String firstName, String lastName, String email,
                                                       String phoneNo) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_SnowChain_PayLater(pickUpLocation, firstName, lastName, email, phoneNo);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        assertTrue(confirmation.isSnowChainProductPresent(), "Snowchain product is not displayed");
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SMOKE,AVIS}, priority = 17, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_Recognised_Paylater_CA(String pickUpLocation, String wizardNo, String lastName,
                                                        String pickUpLocation1) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_Recognised_Paylater(pickUpLocation, wizardNo, lastName, pickUpLocation1);
        //confirmation.closeGetFreeCouponPopup();
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        confirmation.closeGetFreeCouponPopup().cancelReservation();
    }

    @Test(groups = {REGRESSION, SANITY, SMOKE,AVIS}, priority = 21, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_UnderAgeAndHomePageErrorMessageAndPersonalInfoValidation_Paylater_CA(String pickUpLocation,
                                                                                                      String age, String firstName, String lastName, String email, String phoneNo) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_UnderAgeAndHomePageErrorMessageAndPersonalInfoValidation_Paylater(pickUpLocation,
                age, firstName, lastName, email, phoneNo);
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SANITY, SMOKE,AVIS}, priority = 26, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_AnonymousDomesticAWDorBDCorPDNAndIATAAndKeyDrop_Paylater_CA(String pickUpLocation,
                                                                                             String dropOffTime, String awd, String firstName, String lastName, String email, String phoneNo,
                                                                                             String iataNo, String pickUpLocation_new) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_AnonymousDomesticAWDorBDCorPDNAndIATAAndKeyDrop_Paylater(pickUpLocation,
                dropOffTime, awd, firstName, lastName, email, phoneNo, iataNo, pickUpLocation_new);
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SANITY, SMOKE,AVIS}, priority = 27, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_MandateflightInfoAndTtypeCouponProcessingStrikeThrough_Paylater_CA(String pickUpLocation,
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

   /* @Test(groups = {REGRESSION, SANITY,SMOKE,AVIS},priority=28, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_TtypeCoupon_PayLater_CA(String pickUpLocation, String couponNo, String fname,
                                                         String lname, String email, String phoneNo, String couponMsg) {

        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_TtypeCouponProcessing_PayLater(pickUpLocation, couponNo, fname, lname, email,
                phoneNo,couponMsg);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        assertTrue(confirmation.isCouponAppliedMessageDisplayed(couponNo), "Coupon Applied Message is not displayed");
        assertTrue(confirmation.isCouponCodeMessageDisplayed(couponMsg),"Coupon Applied Message is not displayed");
        confirmation.closeGetFreeCouponPopup().cancelReservation();
    }*/

    @Test(groups = {REGRESSION, SMOKE,AVIS}, priority = 31, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_KeyDropLocation_M_typeCoupon_PayLater_CA(String pickUpLocation, String dropOffTime, String couponNo, String fname, String lname,
                                                                  String email, String phoneNo) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_MTypeKeyDropLocation_PayLater(pickUpLocation, dropOffTime, couponNo, fname, lname, email, phoneNo);
        assertTrue(confirmation.isConfirmationNumberDisplayed(),CONFIRMATION_NUMBER_NOT_DISPLAYED_MESSAGE);
        assertTrue(confirmation.closeGetFreeCouponPopup().isCouponAppliedMessageDisplayed(couponNo),COUPON_NOT_APPLIED_MESSAGE);
        confirmation.closeGetFreeCouponPopup().keyDropMessageValidation();
        confirmation.closeGetFreeCouponPopup().cancelReservation();
        //confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SMOKE,AVIS}, priority = 32, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_OneWay_USAA_PayLater_CA(String pickUpLocation, String dropOffLocation, String awd, String membershipNo, String fname, String lname,
                                                 String email, String phoneNo) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_OneWay_USAA_PayLater(pickUpLocation, dropOffLocation, awd, membershipNo, fname, lname, email, phoneNo);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), CONFIRMATION_NUMBER_NOT_DISPLAYED_MESSAGE);
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SMOKE,AVIS}, priority = 33, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_Inbound_Domestic_MultiCurrency_PayLater_CA(String pickUpLocation, String residencyLocation, String firstName, String lastName,
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
    public void Avis_RES_AWD_SplitBillItemized_CorpCust_insuranceCover_Validate_CorpBooking_Paylater_CA(String pickUpLoction, String AWD, String corporateEmailId, String fname, String lname,
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

    @Test(groups = {REGRESSION, SANITY, SMOKE,AVIS}, priority = 36, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_UtypeCoupon_Tierbundle_PayLater_CA(String pickUpLocation, String pickupTime, String dropOffTime, String couponNo, String fname,
                                                            String lname, String email, String phoneNo, String couponText) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_UTypeCouponProcessing_tierbundle_PayLater(pickUpLocation, pickupTime, dropOffTime, couponNo, fname, lname, email, phoneNo, couponText);
        confirmation.closeGetFreeCouponPopup();
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        assertTrue(confirmation.isCouponAppliedMessageDisplayed(couponNo), "Coupon Applied Message is not displayed");
        confirmation.closeGetFreeCouponPopup();
        confirmation.ClickRentalOption();
        assertTrue(confirmation.verifyGPSCoverageOnConfirmationPage(), "GPS Coverage is not displayed");
        assertTrue(confirmation.verifyRSNCoverageOnConfirmationPage(), "RSN Coverage is not displayed");
        assertTrue(confirmation.isCouponCodeMessageDisplayed(couponText), "Coupon Code is not displayed");

        confirmation.cancelReservation();
    }

//   @Test(groups = {REGRESSION, SMOKE,AVIS}, priority = 37, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
//    public void Avis_RES_Costco_DigitalWallet_PayPal_PayLater_CA(String pickUpLocation, String awd, String membershipNo, String fname, String lname,
//                                                                 String email, String phoneNo, String paypalEmail, String paypalPassword) {
//        launchUrl();
//        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
//        Confirmation confirmation = reservationHelper.Reservation_Costco_DigitalWallet_Paypal_PayLater(pickUpLocation, awd, membershipNo, fname, lname, email, phoneNo, paypalEmail, paypalPassword);
//        assertTrue(confirmation.isConfirmationNumberDisplayed(), CONFIRMATION_NUMBER_NOT_DISPLAYED_MESSAGE);
//        assertTrue(confirmation.isAWDCouponMessageDisplayed(), "AWD message is not displayed");
//        assertTrue(confirmation.isAwdConfirmationPageTextDisplayed(awd), "AWD Confirmation text is not displayed");
//        assertTrue(confirmation.isCardTypePaypalDisplayed(), "Card Type Paypal is not displayed");
//        confirmation.cancelReservationWithConfirmationBox();
//    }

  /*  @Test(groups = {REGRESSION, SMOKE,AVIS}, priority = 40, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_Inbound_MultiCurrency_DCCPrePay_IATA_Cancellation_PayNow_CA(String pickUpLocation, String residencyLocation,String awd, String corporateEmailId, String firstName, String lastName,
                                                                                     String email, String phoneNumber, String IATA,String ccNo, String cvv, String residentCurrencySymbol, String USCurrencyValue) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_InboundAndMultiCurrency_IATA_PayNow(pickUpLocation, residencyLocation, awd, corporateEmailId, firstName, lastName, email,
                phoneNumber, IATA, ccNo, cvv, residentCurrencySymbol, USCurrencyValue);

        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        assertTrue(confirmation.isCarReservedTextDisplayed(), "Car reserved text is not displayed");
        assertTrue(confirmation.validatePickupAndReturnLocValue(pickUpLocation,pickUpLocation), "Pickup and Drop Loc is not displayed");
        assertTrue(confirmation.isPickUpDateTimeDisplayed(PICK_UP_TIME), "Pickup Time is not Displayed");
        assertTrue(confirmation.isDropDateTimeDisplayed(DROP_UP_TIME),"Drop Time is not Displayed");
        assertTrue(confirmation.verifyCurrencyOnConfirmationPage(USCurrencyValue), "Currency value is incorrect");
        assertTrue(confirmation.isAWDCouponMessageDisplayed(), "AWD message is not displayed");
        assertTrue(confirmation.isAwdConfirmationPageTextDisplayed(awd), "AWD Confirmation text is not displayed");
        assertTrue(confirmation.isIATAValueDisplayed(IATA), "IATA value is not displayed");
        confirmation.cancelReservation();
    }*/

    @Test(groups = {REGRESSION, SMOKE}, priority = 43, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_Modify_flow_Step1_to_step4_CA(String pickUpLocation, String firstName, String lastName,
                                                       String email, String phoneNumber,String ccNo, String cvv, String Country, String modifiedPickupLocation) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation =  reservationHelper.Reservation_ModifyFlow_PayNow(pickUpLocation, firstName, lastName, email,
                phoneNumber, ccNo, cvv, Country, modifiedPickupLocation);
        confirmation.GetConfirmationNumber();
        confirmation.isEmailSentTextDisplayed(email);
        confirmation.isModifiedReservationTextDisplayed(firstName);
    }

    @Test(groups = {REGRESSION, SMOKE,AVIS}, priority = 19, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_OneWay_PayLater_CoperateDiscount_US(String pickUpLoction,String pickDate,String pickupTime,String dropOffLocation,String dropDate,String dropTime,String month, String fname, String lname,
                                                    String email, String phoneNo,String awd, String corporateEmailId) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_OneWay_PayLater_coperatediscount(pickUpLoction,pickDate, pickupTime,dropOffLocation,dropDate, dropTime,month,fname, lname, email, phoneNo,awd,corporateEmailId);
        confirmation.setBaseRateAndEstimatedTotal();
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        assertTrue(confirmation.isEmailSentMessageVisible(),"Email Sent message is not Visible");
        assertTrue(fareCharges.get("Step-4-BaseRate").equals(fareCharges.get("confirmation-BaseRate")));
        assertTrue(fareCharges.get("Step-4-TotalEstimatedValue").equals(fareCharges.get("confirmation-TotalEstimatedValue")));

        assertTrue(confirmation.isLdwIncluded(),"Lwd Included text Not Visible");
        assertTrue(confirmation.isthirdPartyIncluded(),"Third party Included text Not Visible");
        assertTrue(confirmation.isModifyRateOptionVisible(),"Modify text is not visible under Rate Section ");
        assertTrue(confirmation.isModifyTimeOptionVisible(),"Modify text is not visible under Time And Place Section ");
        assertTrue(confirmation.isModifyRentalOptionVisible(),"Modify text is not visible under Rental Section ");
        assertTrue(confirmation.isModifyMVCOptionVisible(),"Modify text is not visible for MVC Section");
        assertTrue(confirmation.isAWDCodVisibleUnderRateSection(awd),"AWD number not visible under Rate Section");
        assertTrue(confirmation.isEmailSentMessageVisible(),"Email Sent Message not Visible");
        assertTrue(confirmation.isProtectionCoverageChargeMatch(),"Protection Charges not match with Extra Page Charges");
        assertTrue(confirmation.isEquipmentServiceChargeMatch(),"Equipment Service Charges not match with Extra Page Charges");
    //   assertTrue(confirmation.isSmallBizFlyAWDNumberVisible(),"Small Biz AWD number is not generated");
        ManageReservationPage manageReservationPage = reservationHelper.Reservation_Modify_View_Cancel(confirmation,lname,"U S A");

        Assert.assertTrue(manageReservationPage.isPrintConfirmationButtonDisplay(),"Print Confirmation Button not Visible");
        Assert.assertTrue(fareCharges.get("ReviewPreModification-BaseRate").equals(fareCharges.get("confirmation-BaseRate")));
        Assert.assertTrue(fareCharges.get("ReviewPreModification-TotalEstimatedValue").equals(fareCharges.get("confirmation-TotalEstimatedValue")));
        Assert.assertTrue(manageReservationPage.ismvcModifyIconVisible(),"Modify Icon is not Visible for MVC");
        Assert.assertTrue(manageReservationPage.isModifyTimeAndPlaceIconVisible(),"Modify Icon us not visible for Time & Place");
        Assert.assertTrue(manageReservationPage.isModifyRateIconVisible(),"Modify ICON is not Visible for Rate");
        Assert.assertTrue(manageReservationPage.isModifyRentalIconVisible(),"Modify Icon is not visible for Rental");
        Assert.assertTrue(manageReservationPage.isAWDMessageTextDisplayed(),"AWD included message not Visible");

        /*assertTrue(confirmation.isLdwIncluded(),"Lwd Included text Not Visible");
        assertTrue(confirmation.isthirdPartyIncluded(),"Third party Included text Not Visible");
        assertTrue(confirmation.isModifyRateOptionVisible(),"Modify text is not visible under Rate Section ");
        assertTrue(confirmation.isModifyTimeOptionVisible(),"Modify text is not visible under Time And Place Section ");
        assertTrue(confirmation.isModifyRentalOptionVisible(),"Modify text is not visible under Rental Section ");
        assertTrue(confirmation.isModifyMVCOptionVisible(),"Modify text is not visible for MVC Section");
        assertTrue(confirmation.isAWDCodVisibleUnderRateSection(awd),"AWD number not visible under Rate Section");
        assertTrue(confirmation.isEmailSentMessageVisible(),"Email Sent Message not Visible");
        assertTrue(confirmation.isProtectionCoverageChargeMatch(),"Protection Charges not match with Extra Page Charges");
        assertTrue(confirmation.isEquipmentServiceChargeMatch(),"Equipment Service Charges not match with Extra Page Charges");
        // confirmation.cancelReservation();*/
    }

}
