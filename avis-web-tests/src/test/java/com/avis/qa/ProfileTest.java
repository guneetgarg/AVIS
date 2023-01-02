package com.avis.qa;

import com.avis.qa.components.LoginWidget;
import com.avis.qa.components.ReservationWidget;
import com.avis.qa.core.TestBase;
import com.avis.qa.helpers.MiscHelper;
import com.avis.qa.helpers.ReservationHelper;
import com.avis.qa.pages.*;
import com.avis.qa.utilities.CSVUtils;
import com.avis.qa.utilities.ElementHelper;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static com.avis.qa.constants.AvisConstants.*;
import static com.avis.qa.constants.TextComparison.*;
import static com.avis.qa.utilities.CommonUtils.*;
import static org.testng.Assert.assertTrue;

public class ProfileTest  extends TestBase {

    /**
     * ALM Testcase: NA
     */
    @Test(groups = {REGRESSION , SMOKE},priority=1, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
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
        vehicles.verifyUnderAgeSurchargeTextDisplayed();
        vehicles.clickFilterOptionAndVerifyData();

    }



    @Test(groups = {REGRESSION , SMOKE},priority=2, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Budget_RES_Profile_Misc_Verify_Underage_onStep1_FleetFliter_VehicleType_Seats_Mileage_Price_onStep2_US(String username, String password, String pickUpLocation)  {
        launchUrl();

        LoginWidget loginwidget = new LoginWidget(getDriver());
        loginwidget.loginHeaderclick();
        loginwidget.login(username, password);
        threadSleep(SIXTY_SECONDS);
        threadSleep(TWO_SECONDS);
        ReservationWidget reservationWidget = new ReservationWidget(getDriver());

        reservationWidget
                .clickAcceptTermsButton()
                .pickUpLocation(pickUpLocation)
                .calendarSelection(2)
                .selectMyCar();
        Vehicles vehicles = new Vehicles(getDriver());
        vehicles.verifyUnderAgeSurchargeTextDisplayed();
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

    @Test(groups = {REGRESSION , SMOKE},priority=3, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Budget_RES_Profile_Misc_Step1AndStep4_ErrorMsg_Validation_US(String username, String password, String pickUpLocation, String pickUpDate, String pickUpTime, String dropOffLocation, String dropOffDate, String dropOffTime, String WizardNumber, String lastName, String awdCode, String corporateEmail, String rateCode, String couponCode, String creditcardNumber) {
        launchUrl();

        LoginWidget loginwidget = new LoginWidget(getDriver());
        loginwidget.loginHeaderclick();
        loginwidget.login(username, password);
        threadSleep(TWO_SECONDS);
        MiscHelper miscHelper = new MiscHelper(getDriver());
        miscHelper.Reservation_Profile_Misc_Step1AndStep4_ErrorMsg_Validation(pickUpLocation,pickUpDate, pickUpTime, dropOffLocation, dropOffDate,dropOffTime,WizardNumber,lastName,awdCode,corporateEmail,rateCode,couponCode,creditcardNumber);

    }

    @Test(groups = {REGRESSION, SMOKE}, priority = 4, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_Profile_Inbound_MultiCurrency_DCCPrePay_IATA_Cancellation_PayNow_US(String username, String password
            ,String pickUpLocation, String PickupTime, String DropTime,String awd, String corporateEmailId, String IATA,String cvv, String residentCurrencySymbol, String USCurrencyValue) {
        launchUrl();
        LoginWidget loginwidget = new LoginWidget(getDriver());
        loginwidget.loginHeaderclick();
        loginwidget.login(username, password);
        threadSleep(TWO_SECONDS);
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_Profile_InboundAndMultiCurrency_IATA_PayNow(pickUpLocation, PickupTime,DropTime, awd, corporateEmailId,IATA,cvv, residentCurrencySymbol, USCurrencyValue);
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
    @Test(groups = {REGRESSION, SMOKE}, priority = 4, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Budget_RES_Profile_Inbound_MultiCurrency_IATA_Cancellation_PayNow_US(String username, String password
            ,String pickUpLocation, String PickupTime, String DropTime,String awd, String corporateEmailId, String IATA,String cvv, String residentCurrencySymbol, String USCurrencyValue) throws InterruptedException {
        launchUrl();
        LoginWidget loginwidget = new LoginWidget(getDriver());
        loginwidget.loginHeaderclick();
        loginwidget.login(username, password);
        String otp =LoginWidget.getOtp("https://www.receivesms.co/","us-phone-number/3411/");
        loginwidget.EnterOTP(otp);
        threadSleep(TWO_SECONDS);
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_Profile_InboundAndMultiCurrency_IATA_PayNow(pickUpLocation, PickupTime,DropTime, awd, corporateEmailId,IATA,cvv, residentCurrencySymbol, USCurrencyValue);
        assertTrue(confirmation.isConfirmationNumberDisplayed());
        assertTrue(confirmation.isCarReservedTextDisplayed());
        assertTrue(confirmation.validatePickupAndReturnLocValue(pickUpLocation,pickUpLocation));
        assertTrue(confirmation.isPickUpDateTimeDisplayed(PickupTime));
        assertTrue(confirmation.isDropDateTimeDisplayed(DropTime));
        assertTrue(confirmation.verifyCurrencyOnConfirmationPage(USCurrencyValue));
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

    @Test(groups = {REGRESSION, SMOKE}, priority = 5, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Budget_RES_Profile_FlightInfo_DigitalWallet_PayPal_PayNow_US(String username, String password,String pickUpLocation,String PickupTime,String DropTime, String paypalEmail, String paypalPassword, String flightName, String flightNumber) {
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

    @Test(groups = {REGRESSION, SMOKE}, priority = 6, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_Profile_Modify_flow_Step1_to_step4_US(String username, String password,String pickUpLocation, String modifiedPickupLocation,String Country) {
        launchUrl();
        LoginWidget loginwidget = new LoginWidget(getDriver());
        loginwidget.loginHeaderclick();
        loginwidget.login(username, password);
        threadSleep(TWO_SECONDS);
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation =  reservationHelper.Reservation_Profile_ModifyFlow_PayNow(pickUpLocation,modifiedPickupLocation, Country);
        confirmation.GetConfirmationNumber();
        confirmation.isEmailSentTextDisplayed();
        confirmation.isModifiedReservationTextDisplayed();

    }

    @Test(groups = {REGRESSION, SMOKE}, priority = 7, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_Profile_Outbound_CorpCust_insuranceCover_Validate_CorpBooking_Paynow_US(String username, String password, String pickUpLocation, String pickupTime, String dropTime,String awd, String corporateEmailId,String cvv, String PickUpLocCurrencySymbol, String PickupLocCurrencyCode, String USCurrencyCode) {
        launchUrl();
        LoginWidget loginwidget = new LoginWidget(getDriver());
        loginwidget.loginHeaderclick();
        loginwidget.login(username, password);
        threadSleep(TWO_SECONDS);
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Avis_Reservation_Profile_Outbound_CorpCust_InsuranceCover_PayNow(pickUpLocation, pickupTime,dropTime, awd, corporateEmailId, cvv, PickUpLocCurrencySymbol, PickupLocCurrencyCode, USCurrencyCode);
        assertTrue(confirmation.isConfirmationNumberDisplayed());
        assertTrue(confirmation.validatePickupAndReturnLocValue(pickUpLocation,pickUpLocation));
        assertTrue(confirmation.isPickUpDateTimeDisplayed(pickupTime));
        assertTrue(confirmation.isDropDateTimeDisplayed(dropTime));
        assertTrue(confirmation.verifyCurrencyOnConfirmationPage(PickupLocCurrencyCode));
        assertTrue(confirmation.isAWDMessageTextDisplayed());
        assertTrue(confirmation.isCarReservedTextDisplayed());
        assertTrue(confirmation.isAwdConfirmationPageTextDisplayed(awd));
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SMOKE}, priority = 8, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_Profile_KeyDropLocation_M_typeCoupon_PayLater_US(String username, String password,String pickUpLocation, String dropOffTime, String couponNo) {
        launchUrl();
        LoginWidget loginwidget = new LoginWidget(getDriver());
        loginwidget.loginHeaderclick();
        loginwidget.login(username, password);
        threadSleep(TWO_SECONDS);
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_Profile_MTypeKeyDropLocation_PayLater(pickUpLocation, dropOffTime, couponNo);
        assertTrue(confirmation.isConfirmationNumberDisplayed(),CONFIRMATION_NUMBER_NOT_DISPLAYED_MESSAGE);
        assertTrue(confirmation.closeGetFreeCouponPopup().isCouponAppliedMessageDisplayed(couponNo),COUPON_NOT_APPLIED_MESSAGE);
        confirmation.closeGetFreeCouponPopup().keyDropMessageValidation();
        confirmation.closeGetFreeCouponPopup().cancelReservation();
    }


  //  @Test(groups = {REGRESSION, SMOKE}, priority = 9, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Budget_RES_Profile_KeyDropLocation_M_typeCoupon_PayLater_US(String username, String password,String pickUpLocation, String dropOffTime, String couponNo) {
        launchUrl();
        LoginWidget loginwidget = new LoginWidget(getDriver());
        loginwidget.loginHeaderclick();
        loginwidget.login(username, password);
        threadSleep(SIXTY_SECONDS);
        loginwidget.PopupBudget.click();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_Profile_MTypeKeyDropLocation_PayLater(pickUpLocation, dropOffTime, couponNo);
        assertTrue(confirmation.isConfirmationNumberDisplayed(),CONFIRMATION_NUMBER_NOT_DISPLAYED_MESSAGE);
        assertTrue(confirmation.closeGetFreeCouponPopup().isCouponAppliedMessageDisplayed(couponNo),COUPON_NOT_APPLIED_MESSAGE);
        confirmation.closeGetFreeCouponPopup().keyDropMessageValidation();
        confirmation.closeGetFreeCouponPopup().cancelReservation();
    }
    @Test(groups = {REGRESSION, SMOKE}, priority = 9, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_Profile_Costco_DigitalWallet_PayPal_PayLater_US(String username, String password,String pickUpLocation, String awd, String membershipNo, String paypalEmail, String paypalPassword) {
        launchUrl();
        LoginWidget loginwidget = new LoginWidget(getDriver());
        loginwidget.loginHeaderclick();
        loginwidget.login(username, password);
        threadSleep(TWO_SECONDS);
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_Profile_Costco_DigitalWallet_Paypal_PayLater(pickUpLocation, awd, membershipNo, paypalEmail, paypalPassword);
        assertTrue(confirmation.isConfirmationNumberDisplayed(),CONFIRMATION_NUMBER_NOT_DISPLAYED_MESSAGE);
        assertTrue(confirmation.isAWDCouponMessageDisplayed(), COUPON_NOT_APPLIED_MESSAGE);
        assertTrue(confirmation.isAwdConfirmationPageTextDisplayed(awd), AWD_CONFIRMATION_TEXT_NOT_DISPLAYED_MESSAGE);
        assertTrue(confirmation.isCardTypePaypalDisplayed(), CARD_TYPE_PAYPAL_NOT_DISPLAYED_MESSAGE);
        //confirmation.cancelReservation();
        confirmation.cancelReservationWithConfirmationBox();
    }

  //  @Test(groups = {REGRESSION, SMOKE}, priority = 9, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Budget_RES_Profile_Costco_DigitalWallet_PayPal_PayLater_US(String username, String password,String pickUpLocation, String awd, String membershipNo, String paypalEmail, String paypalPassword) {
        launchUrl();
        LoginWidget loginwidget = new LoginWidget(getDriver());
        loginwidget.loginHeaderclick();
        loginwidget.login(username, password);
        threadSleep(SIXTY_SECONDS);
        loginwidget.PopupBudget.click();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_Profile_Costco_DigitalWallet_Paypal_PayLater(pickUpLocation, awd, membershipNo, paypalEmail, paypalPassword);
        assertTrue(confirmation.isConfirmationNumberDisplayed(),CONFIRMATION_NUMBER_NOT_DISPLAYED_MESSAGE);
        assertTrue(confirmation.isAWDCouponMessageDisplayed(), COUPON_NOT_APPLIED_MESSAGE);
        assertTrue(confirmation.isAwdConfirmationPageTextDisplayed(awd), AWD_CONFIRMATION_TEXT_NOT_DISPLAYED_MESSAGE);
        assertTrue(confirmation.isCardTypePaypalDisplayed(), CARD_TYPE_PAYPAL_NOT_DISPLAYED_MESSAGE);
//        confirmation.cancelReservation();
        confirmation.cancelReservationWithConfirmationBox();
    }
    @Test(groups = {REGRESSION, SMOKE}, priority = 10, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_Profile_OneWay_USAA_PayLater_US(String username, String password,String pickUpLocation, String dropOffLocation, String awd, String membershipNo) {
        launchUrl();
        LoginWidget loginwidget = new LoginWidget(getDriver());
        loginwidget.loginHeaderclick();
        loginwidget.login(username, password);
        threadSleep(TWO_SECONDS);
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_Profile_OneWay_USAA_PayLater(pickUpLocation, dropOffLocation, awd, membershipNo);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), CONFIRMATION_NUMBER_NOT_DISPLAYED_MESSAGE);
        confirmation.cancelReservation();
    }

   // @Test(groups = {REGRESSION, SMOKE}, priority = 10, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Budget_RES_Profile_OneWay_USAA_PayLater_US(String username, String password,String pickUpLocation, String dropOffLocation, String awd, String membershipNo) {
        launchUrl();
        LoginWidget loginwidget = new LoginWidget(getDriver());
        loginwidget.loginHeaderclick();
        loginwidget.login(username, password);
        threadSleep(SIXTY_SECONDS);
        loginwidget.PopupBudget.click();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_Profile_OneWay_USAA_PayLater(pickUpLocation, dropOffLocation, awd, membershipNo);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), CONFIRMATION_NUMBER_NOT_DISPLAYED_MESSAGE);
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SMOKE}, priority = 11, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_Profile_Inbound_MultiCurrency_PayLater_US(String username, String password,String pickUpLocation, String flightNumber,String residentCurrencySymbol, String currencyValue) {
        launchUrl();
        LoginWidget loginwidget = new LoginWidget(getDriver());
        loginwidget.loginHeaderclick();
        loginwidget.login(username, password);// need to create profile user for canada region
        threadSleep(TWO_SECONDS);
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_Profile_InboundAndMultiCurrency_Paylater(pickUpLocation,flightNumber,residentCurrencySymbol, currencyValue);

        assertTrue(confirmation.isConfirmationNumberDisplayed(), CONFIRMATION_NUMBER_NOT_DISPLAYED_MESSAGE);
        assertTrue(confirmation.verifyCurrencyOnConfirmationPage(currencyValue), CURRENCY_VALUE_INCORRECT_MESSAGE);
        confirmation.cancelReservation();
    }

   // @Test(groups = {REGRESSION, SMOKE}, priority = 12, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Budget_RES_Profile_Inbound_MultiCurrency_PayLater_US(String username, String password,String pickUpLocation, String flightNumber,String residentCurrencySymbol, String currencyValue) {
        launchUrl();
        LoginWidget loginwidget = new LoginWidget(getDriver());
        loginwidget.loginHeaderclick();
        loginwidget.login(username, password);// need to create profile user for canada region
        threadSleep(SIXTY_SECONDS);
        loginwidget.PopupBudget.click();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_Profile_InboundAndMultiCurrency_Paylater(pickUpLocation,flightNumber,residentCurrencySymbol, currencyValue);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), CONFIRMATION_NUMBER_NOT_DISPLAYED_MESSAGE);
        assertTrue(confirmation.verifyCurrencyOnConfirmationPage(currencyValue), CURRENCY_VALUE_INCORRECT_MESSAGE);
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SMOKE}, priority = 12, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_Profile_Domestic_PayLater_US(String username, String password,String pickUpLocation) {
        launchUrl();
        LoginWidget loginwidget = new LoginWidget(getDriver());
        loginwidget.loginHeaderclick();
        loginwidget.login(username, password);// need to create profile user for canada region
        threadSleep(TWO_SECONDS);
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_Profile_Domestic_PayLater(pickUpLocation);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), CONFIRMATION_NUMBER_NOT_DISPLAYED_MESSAGE);
        confirmation.cancelReservation();
    }

   // @Test(groups = {REGRESSION, SMOKE}, priority = 12, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Budget_RES_Profile_Domestic_PayLater_US(String username, String password,String pickUpLocation) {
        launchUrl();
        LoginWidget loginwidget = new LoginWidget(getDriver());
        loginwidget.loginHeaderclick();
        loginwidget.login(username, password);// need to create profile user for canada region
        threadSleep(SIXTY_SECONDS);
        loginwidget.PopupBudget.click();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_Profile_Domestic_PayLater(pickUpLocation);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), CONFIRMATION_NUMBER_NOT_DISPLAYED_MESSAGE);
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SANITY, SMOKE}, priority = 13, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_Profile_G_typeCoupon_SMSCheckbox_IATA_PayLater_US(String username, String password,String pickUpLocation, String couponNo, String flightName, String flightNumber, String IATA, String couponMsg) {

        launchUrl();
        LoginWidget loginwidget = new LoginWidget(getDriver());
        loginwidget.loginHeaderclick();
        loginwidget.login(username, password);// need to create profile user for canada region
        threadSleep(TWO_SECONDS);
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_Profile_G_typeCoupon_SMSCheckbox_IATA_PayLater(pickUpLocation, couponNo, flightName, flightNumber, IATA, couponMsg);

        assertTrue(confirmation.isConfirmationNumberDisplayed(), CONFIRMATION_NUMBER_NOT_DISPLAYED_MESSAGE);
        assertTrue(confirmation.isCouponCodeMessageDisplayed(couponMsg), COUPON_CODE_NOT_DISPLAYED_MESSAGE);
        assertTrue(confirmation.isIATAValueDisplayed(IATA), IATA_VALUE_NOT_DISPLAYED_MESSAGE);
        assertTrue(confirmation.isFlightInfoDisplayed(flightName), FLIGHTINFO_NOT_DISPLAYED_MESSAGE);
        confirmation.closeGetFreeCouponPopup().cancelReservation();
        // confirmation.cancelReservation();
    }

   // @Test(groups = {REGRESSION, SANITY, SMOKE}, priority = 13, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Budget_RES_Profile_G_typeCoupon_SMSCheckbox_IATA_PayLater_US(String username, String password,String pickUpLocation, String couponNo, String flightName, String flightNumber, String IATA, String couponMsg) {
        launchUrl();
        LoginWidget loginwidget = new LoginWidget(getDriver());
        loginwidget.loginHeaderclick();
        loginwidget.login(username, password);// need to create profile user for canada region
        threadSleep(SIXTY_SECONDS);
        loginwidget.PopupBudget.click();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_Profile_G_typeCoupon_SMSCheckbox_IATA_PayLater(pickUpLocation, couponNo, flightName, flightNumber, IATA, couponMsg);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), CONFIRMATION_NUMBER_NOT_DISPLAYED_MESSAGE);
        assertTrue(confirmation.isCouponCodeMessageDisplayed(couponMsg), COUPON_CODE_NOT_DISPLAYED_MESSAGE);
        assertTrue(confirmation.isIATAValueDisplayed(IATA), IATA_VALUE_NOT_DISPLAYED_MESSAGE);
        assertTrue(confirmation.isFlightInfoDisplayed(flightName), FLIGHTINFO_NOT_DISPLAYED_MESSAGE);
        confirmation.closeGetFreeCouponPopup().cancelReservation();
        // confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SANITY, SMOKE}, priority = 14, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_Profile_U_typeCoupon_Tierbundle_PayLater_US(String username, String password,String pickUpLocation, String pickupTime, String dropOffTime, String couponNo, String couponText) {

        launchUrl();
        LoginWidget loginwidget = new LoginWidget(getDriver());
        loginwidget.loginHeaderclick();
        loginwidget.login(username, password);
        threadSleep(TWO_SECONDS);
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_Profile_UTypeCouponProcessing_tierbundle_PayLater(pickUpLocation, pickupTime, dropOffTime, couponNo, couponText);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), CONFIRMATION_NUMBER_NOT_DISPLAYED_MESSAGE);
        assertTrue(confirmation.isCouponAppliedMessageDisplayed(couponNo));
        confirmation.closeGetFreeCouponPopup();
        confirmation.ClickRentalOption();
        assertTrue(confirmation.verifyGPSCoverageOnConfirmationPage());
        //assertTrue(confirmation.verifyRSNCoverageOnConfirmationPage(), "RSN Coverage is not displayed");
        assertTrue(confirmation.isCouponCodeMessageDisplayed(couponText), COUPON_CODE_NOT_DISPLAYED_MESSAGE);
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SANITY,SMOKE},priority=15, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Res_Profile_TtypeCoupon_PayLater_US(String username, String password, String pickUpLocation, String couponNo, String couponMsg) {

        launchUrl();
        LoginWidget loginwidget = new LoginWidget(getDriver());
        loginwidget.loginHeaderclick();
        loginwidget.login(username, password);// need to create profile user for canada region
        threadSleep(TWO_SECONDS);
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_Profile_TtypeCouponProcessing_PayLater(pickUpLocation, couponNo,couponMsg);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), CONFIRMATION_NUMBER_NOT_DISPLAYED_MESSAGE);
        assertTrue(confirmation.isCouponAppliedMessageDisplayed(couponNo));
        assertTrue(confirmation.isCouponCodeMessageDisplayed(couponMsg),COUPON_CODE_NOT_DISPLAYED_MESSAGE);
        confirmation.closeGetFreeCouponPopup().cancelReservation();
    }

    @Test(groups = {REGRESSION, SMOKE}, priority = 16, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_Profile_Outbound_StrikeThroughCoupon_Cancelation_PayLater_US(String pickUpLocation, String residencyLocation, String awd, String flightNumber) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_Profile_OutboundAndStrikeThroughCoupon_Paylater(pickUpLocation, residencyLocation, awd, flightNumber);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), CONFIRMATION_NUMBER_NOT_DISPLAYED_MESSAGE);
        assertTrue(confirmation.isAWDCouponMessageDisplayed(), COUPON_NOT_APPLIED_MESSAGE);
        assertTrue(confirmation.isAwdConfirmationPageTextDisplayed(awd), AWD_CONFIRMATION_TEXT_NOT_DISPLAYED_MESSAGE);
        confirmation.cancelReservation();

    }

   // @Test(groups = {REGRESSION, SMOKE}, priority = 16, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Budget_RES_Profile_Outbound_StrikeThroughCoupon_Cancelation_PayLater_US(String pickUpLocation, String residencyLocation, String awd, String flightNumber) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_Profile_OutboundAndStrikeThroughCoupon_Paylater(pickUpLocation, residencyLocation, awd, flightNumber);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), CONFIRMATION_NUMBER_NOT_DISPLAYED_MESSAGE);
        assertTrue(confirmation.isAWDCouponMessageDisplayed(), COUPON_NOT_APPLIED_MESSAGE);
        assertTrue(confirmation.isAwdConfirmationPageTextDisplayed(awd), AWD_CONFIRMATION_TEXT_NOT_DISPLAYED_MESSAGE);
        confirmation.cancelReservation();

    }
  //  @Test(groups = {REGRESSION, SMOKE}, priority = 9, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Budget_RES_Profile_KeyDropLocation_U_typeCoupon_PayLater_US(String username, String password,String pickUpLocation, String dropOffTime, String couponNo) {
        launchUrl();
        LoginWidget loginwidget = new LoginWidget(getDriver());
        loginwidget.loginHeaderclick();
        loginwidget.login(username, password);
        threadSleep(SIXTY_SECONDS);
        loginwidget.PopupBudget.click();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_Profile_MTypeKeyDropLocation_PayLater(pickUpLocation, dropOffTime, couponNo);
        assertTrue(confirmation.isConfirmationNumberDisplayed(),CONFIRMATION_NUMBER_NOT_DISPLAYED_MESSAGE);
        assertTrue(confirmation.closeGetFreeCouponPopup().isCouponAppliedMessageDisplayed(couponNo),COUPON_NOT_APPLIED_MESSAGE);
        confirmation.closeGetFreeCouponPopup().keyDropMessageValidation();
        confirmation.closeGetFreeCouponPopup().cancelReservation();
    }

   // @Test(groups = {REGRESSION, SANITY,SMOKE},priority=28, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Budget_RES_Profile_T_typeCoupon_CCOLocation_PayLater_US(String username,String password,String pickUpLocation, String couponNo,
                                                                String couponMsg) {
        launchUrl();
        LoginWidget loginwidget = new LoginWidget(getDriver());
        loginwidget.loginHeaderclick();
        loginwidget.login(username, password);
        threadSleep(SIXTY_SECONDS);
        loginwidget.PopupBudget.click();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Budget_RES_Profile_T_typeCoupon_CCOLocation_PayLater_US(pickUpLocation, couponNo,couponMsg);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        assertTrue(confirmation.isCouponAppliedMessageDisplayed(couponNo), "Coupon Applied Message is not displayed");
        assertTrue(confirmation.isCouponCodeMessageDisplayed(couponMsg),"Coupon Applied Message is not displayed");
        confirmation.closeGetFreeCouponPopup().cancelReservation();
    }

   // @Test(groups = {REGRESSION, SMOKE}, priority = 37, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Budget_RES_Profile_Outbound_StrikeThroughCoupon_LocMandate_FlightInfo_Cancellation_PayLater_US(String username, String password,String pickUpLocation, String residencyLocation, String awd,String MembershipNum,
                                                                                                         String flightNumber) {
        launchUrl();
        LoginWidget loginwidget = new LoginWidget(getDriver());
        loginwidget.loginHeaderclick();
        loginwidget.login(username, password);
        threadSleep(SIXTY_SECONDS);
        loginwidget.PopupBudget.click();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Budget_RES_Profile_Outbound_StrikeThroughCoupon_LocMandate_FlightInfo_Cancellation_PayLater_US(pickUpLocation, residencyLocation, awd, MembershipNum,flightNumber);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        assertTrue(confirmation.isAWDCouponMessageDisplayed(), "AWD message is not displayed");
        assertTrue(confirmation.isAwdConfirmationPageTextDisplayed(awd), "AWD Confirmation text is not displayed");
        confirmation.cancelReservation();

    }

    //@Test(groups = {REGRESSION, SMOKE}, priority = 45, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Budget_RES_Profile_BCD_CorpCust_insuranceCover_Validate_Paylater_US(String username, String password, String pickUpLocation, String pickupTime, String dropTime,String awd, String corporateEmailId) {
        launchUrl();
        LoginWidget loginwidget = new LoginWidget(getDriver());
        loginwidget.loginHeaderclick();
        loginwidget.login(username, password);
        threadSleep(SIXTY_SECONDS);
        loginwidget.PopupBudget.click();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_Profile_Outbound_CorpCust_InsuranceCover_PayNow(pickUpLocation, pickupTime,dropTime, awd, corporateEmailId);
        assertTrue(confirmation.isConfirmationNumberDisplayed());
        assertTrue(confirmation.validatePickupAndReturnLocValue(pickUpLocation,pickUpLocation));
        assertTrue(confirmation.isPickUpDateTimeDisplayed(pickupTime));
        assertTrue(confirmation.isDropDateTimeDisplayed(dropTime));
//        assertTrue(confirmation.verifyCurrencyOnConfirmationPage(PickupLocCurrencyCode));
        assertTrue(confirmation.isAWDMessageTextDisplayed());
        assertTrue(confirmation.isCarReservedTextDisplayed());
        assertTrue(confirmation.isAwdConfirmationPageTextDisplayed(awd));
        confirmation.cancelReservation();
    }

    //@Test(groups = {REGRESSION, SANITY,SMOKE}, priority=1, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Profile_View_Updation_dashboard_US(String username, String pwd) {

        launchUrl();
        LoginWidget loginwidget = new LoginWidget(getDriver());
        loginwidget.loginHeaderclick();
        loginwidget.login(username, pwd);
        ProfileDashboard profileDashboard = new ProfileDashboard(getDriver());

        profileDashboard
                .clickProfile()
                .verifyMyRentalTab()
                .verifyRewardsTab()
                .verifyPreferenceTab()
                .verifyAboutTab()
                .verifyPrivacyPolicyTab()
                .verifyTermsAndConditionsTab();
    }

   // @Test(groups = {REGRESSION, SANITY,SMOKE}, priority=1, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Profile_WizardLookUp_UserName_ForgotPassword_US(String firstname, String lastname, String email, String zip) throws InterruptedException {
        launchUrl();
        Homepage homepage = new Homepage(getDriver());
        Login login = new Login(getDriver());
        homepage.clickLoginLink();

        login
                .clickForgotPassword()
                .enterResetPasswordDetails(firstname, lastname, email, zip)
                .checkMailInbox(email);
    }

   // @Test(groups = {REGRESSION, SANITY,SMOKE}, priority=1, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Profile_Loyalty_OptOut_US(String username, String pwd){
        launchUrl();
        Homepage homepage = new Homepage(getDriver());
        LoginWidget loginWidget = new LoginWidget(getDriver());
        ProfileDashboard profileDashboard = new ProfileDashboard(getDriver());
        homepage.clickLoginLink();
        loginWidget.login(username,pwd);
        profileDashboard
                .clickProfile()
                .verifyRewardsTab()
                .clickEarnPointsButton();
    }

   // @Test(groups = {REGRESSION}, priority=1, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Profile_ExpressProfile_Login_US(String username, String pwd){
        launchUrl();
        Homepage homepage = new Homepage(getDriver());
        LoginWidget loginWidget = new LoginWidget(getDriver());
        homepage.clickLoginLink();
        loginWidget.login(username,pwd);
    }

    @Test(groups = {REGRESSION}, priority=1, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Profile_AccountLock_Authenticated_US(String username, String pwd) throws InterruptedException {
        launchUrl();
        Homepage homepage = new Homepage(getDriver());
        LoginWidget loginWidget = new LoginWidget(getDriver());
        homepage.clickLoginLink();
        for (int i = 0; i < 4; i++) {
            loginWidget.login(username, pwd);
        }
        TimeUnit.SECONDS.sleep(5);
    }

    // @Test(groups = {REGRESSION}, priority=1, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Profile_Loyalty_OptIn_US(String username, String pwd){
        launchUrl();
        Homepage homepage = new Homepage(getDriver());
        LoginWidget loginWidget = new LoginWidget(getDriver());
        ProfileDashboard profileDashboard = new ProfileDashboard(getDriver());
        homepage.clickLoginLink();
        loginWidget.login(username,pwd);
        profileDashboard
                .clickProfile()
                .verifyRewardsTab()
                .clickEarnPointsButton();
    }

    @Test(groups = {REGRESSION}, priority=1, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Profile_OneClick_OAUTHLogin_US(String emailUrl) {
        launchUrl(emailUrl);
    }

    //@Test(groups = {REGRESSION}, priority=1, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Profile_OneClick_EmailLink_US(String emailUrl, String username, String pwd){
        launchUrl(emailUrl);
        LoginWidget loginWidget = new LoginWidget(getDriver());
        loginWidget.login(username,pwd);
    }

   // @Test(groups = {REGRESSION}, priority=1, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_Profile_Misc_Step1_and_Step4_ErrorMsg_Validation_US(String username, String pwd, String pickUpLocation, String pickUpDate, String pickUpTime, String dropOffLocation, String dropOffDate, String dropOffTime, String WizardNumber, String lastName, String awdCode, String corporateEmail, String rateCode, String couponCode, String creditcardNumber){
        launchUrl();
        Homepage homepage = new Homepage(getDriver());
        com.avis.qa.components.LoginWidget loginWidget = new com.avis.qa.components.LoginWidget(getDriver());
        homepage.clickLoginLink();
        loginWidget.login(username,pwd);
        MiscHelper miscHelper = new MiscHelper(getDriver());
        miscHelper.Reservation_Misc_Step1AndStep4_ErrorMsg_Validation(pickUpLocation,pickUpDate, pickUpTime, dropOffLocation, dropOffDate,dropOffTime,WizardNumber,lastName,awdCode,corporateEmail,rateCode,couponCode,creditcardNumber);

    }

    @Test(groups = {REGRESSION, SMOKE}, priority = 32, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_RES_OneWay_USAA_PayLater_US(String pickUpLocation, String dropOffLocation, String awd, String membershipNo, String fname, String lname,
                                                 String email, String phoneNo) {
        launchUrl();
        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_OneWay_USAA_PayLater(pickUpLocation, dropOffLocation, awd, membershipNo, fname, lname, email, phoneNo);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        confirmation.cancelReservation();
    }

    //@Test(groups = {REGRESSION, SMOKE}, priority = 6, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Budget_RES_Profile_Modify_flow_Step1_to_step4_US(String username, String password,String pickUpLocation, String modifiedPickupLocation,String Country) {
        launchUrl();
        LoginWidget loginwidget = new LoginWidget(getDriver());
        loginwidget.loginHeaderclick();
        loginwidget.login(username, password);
        threadSleep(TWO_SECONDS);
        loginwidget.PopupBudget.click();

        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation =  reservationHelper.Reservation_Profile_ModifyFlow_PayNow(pickUpLocation,modifiedPickupLocation, Country);
        confirmation.GetConfirmationNumber();
        confirmation.isEmailSentTextDisplayed();
        confirmation.isModifiedReservationTextDisplayed();

    }

    //@Test(groups = {REGRESSION, SMOKE}, priority = 39, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Budget_RES_Profile_Outbound_USAA_Validate_CorpBooking_URS(String username, String password, String pickUpLocation, String BCD, String membershipNo) {
        launchUrl();
        LoginWidget loginwidget = new LoginWidget(getDriver());
        loginwidget.loginHeaderclick();
        loginwidget.login(username, password);
        threadSleep(TWO_SECONDS);
        loginwidget.PopupBudget.click();

        ReservationHelper reservationHelper = new ReservationHelper(getDriver());
        Confirmation confirmation = reservationHelper.Reservation_Profile_Outbound_USAA_Validate_CorpBooking_URS(pickUpLocation, BCD, membershipNo);
        assertTrue(confirmation.isConfirmationNumberDisplayed());
    }
}
