package com.avis.qa.helpers;
import com.avis.qa.constants.TextComparison;
import com.avis.qa.pages.paylesscar.*;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertTrue;

public class PayLessCarHelper {

    private final WebDriver driver;
    private final PayLessCar payLessCar;

    public PayLessCarHelper(WebDriver driver) {
        this.driver = driver;
        this.payLessCar = new PayLessCar(driver);
    }

    public Confirmation Reservation_Inbound_IATA_M_type_PayLater_US(String pickUpLocation, String country, String fname,
                                                                    String lname, String email, String phoneNo){
        payLessCar
                .pickUpLocation(pickUpLocation)
                .residenceCountry(country)
                .getRates();

        Validations validate = new Validations(driver);
        validate
                .verifyPickUpLocation(pickUpLocation)
                .verifyPickUpDate();
        Vehicles vehicles = new Vehicles(driver);
        Extras extras = vehicles.step2Submit();
        validate
                .verifyPickUpLocation(pickUpLocation)
                .verifyPickUpDate()
                .verifyVehicleName();
        ReviewAndBook reviewAndBook = extras.Step3Submit();
        validate
                .verifyPickUpLocation(pickUpLocation)
                .verifyPickUpDate()
                .verifyVehicleName();
        reviewAndBook
                .clickContinueReservationButton()
                .firstname(fname)
                .lastname(lname)
                .email(email)
                .phone(phoneNo)
                .checkTermsAndConditions()
                .step4Submit();
        validate
                .verifyPickUpLocation(pickUpLocation)
                .verifyPickUpDate()
                .verifyPayLaterUserInfo(email, country);

        return new Confirmation(driver);
    }

    public Confirmation Reservation_G_typeCoupon_Extras_paylater_US(String pickUpLocation, String country, String couponNo, String fname,
                                                                    String lname, String email, String phoneNo){
        payLessCar
                .pickUpLocation(pickUpLocation)
                .residenceCountry(country)
                .clickCouponCheckBox()
                .enterCouponCode(couponNo)
                .getRates();
        Validations validate = new Validations(driver);
        validate
                .verifyPickUpLocation(pickUpLocation)
                .verifyPickUpDate()
                .verifyCouponCode(couponNo);

        Vehicles vehicles = new Vehicles(driver);
        Extras extras = vehicles.step2Submit();
        validate
                .verifyPickUpLocation(pickUpLocation)
                .verifyPickUpDate();
        ReviewAndBook reviewAndBook = extras.Step3Submit();
        validate
                .verifyPickUpLocation(pickUpLocation)
                .verifyPickUpDate();
        reviewAndBook
                .clickContinueReservationButton()
                .firstname(fname)
                .lastname(lname)
                .email(email)
                .phone(phoneNo)
                .checkTermsAndConditions()
                .step4Submit();
        validate
                .verifyPickUpLocation(pickUpLocation)
                .verifyPickUpDate()
                .verifyCouponCode(couponNo)
                .verifyPayLaterUserInfo(email, country);

        return new Confirmation(driver);
    }

    public Confirmation Reservation_Outbound_PDN_Paylater_US(String pickUpLocation, String country, String pickUpTime,
                                                             String pdn, String fname, String lname, String email, String phoneNo){
        payLessCar
                .pickUpLocation(pickUpLocation)
                .residenceCountry(country)
                .calendarSelection()
                .pickUpTime(pickUpTime)
                .clickCouponCheckBox()
                .enterPdn(pdn)
                .getRates();

        Validations validate = new Validations(driver);
        validate
                .verifyPickUpLocation(pickUpLocation)
                .verifyPdnNumber(pdn);

        Vehicles vehicles = new Vehicles(driver);
        Extras extras = vehicles.step2Submit();
        validate
                .verifyPickUpLocation(pickUpLocation);
        ReviewAndBook reviewAndBook = extras.Step3Submit();
        validate
                .verifyPickUpLocation(pickUpLocation);
        reviewAndBook
                .clickContinueReservationButton()
                .firstname(fname)
                .lastname(lname)
                .email(email)
                .phone(phoneNo)
                .checkTermsAndConditions()
                .step4Submit();
        validate
                .verifyPickUpLocation(pickUpLocation)
                .verifyPdnNumber(pdn)
                .verifyPayLaterUserInfo(email, country);

        return new Confirmation(driver);
    }

    public Confirmation Reservation_Verify_Underage_onStep1_keydrop_US(String pickUpLocation, String dropOffLocation, String country, String pickUpTime,
                                                                       String dropOffTime, String age, String fname, String lname, String email, String phoneNo,
                                                                       String ccNumber, String cvv){
        payLessCar
                .pickUpLocation(pickUpLocation)
                .residenceCountry(country)
                .dropOffTime(dropOffTime)
                .selectAge(age)
                .getRates();
        assertTrue(payLessCar.isNoCarsAvailableMessageDisplayed(), TextComparison.NO_CARS_AVAILABLE_MESSAGE);

        payLessCar.selectAge("21")
                .getRates();
        Validations validate = new Validations(driver);
        validate
                .verifyPickUpLocation(pickUpLocation)
                .verifyPickUpDate();

        assertTrue(payLessCar.isUnderAgeSubChargeMessageDisplayed(), TextComparison.UNDER_SUB_AGE_MESSAGE);
        assertTrue(payLessCar.isLocationClosedMessageDisplayed(), TextComparison.LOCATION_CLOSED_MESSAGE);


        Vehicles vehicles = new Vehicles(driver);
        Extras extras = vehicles.step2SubmitPayNow();
        validate
                .verifyPickUpLocation(pickUpLocation)
                .verifyPickUpDate();
        ReviewAndBook reviewAndBook = extras.Step3Submit();
        validate
                .verifyPickUpLocation(pickUpLocation)
                .verifyPickUpDate();
        reviewAndBook
                .clickContinueReservationButton()
                .firstname(fname)
                .lastname(lname)
                .email(email)
                .phone(phoneNo)
                .enterCardNumber(ccNumber)
                .selectExpiryDate()
                .enterSecurityCode(cvv)
                .enterAddressInboundSpecific(country)
                .checkTermsAndConditions()
                .step4Submit();

        validate
                .verifyPickUpLocation(pickUpLocation)
                .verifyPickUpDate()
                .verifyPayNowUserInfo(email);


        return new Confirmation(driver);
    }

    public Confirmation Reservation_Profile_G_typeCoupon_Extras_Paylater_US(String pickUpLocation, String couponNo){
        payLessCar
                .pickUpLocation(pickUpLocation)
                .clickCouponCheckBox()
                .enterCouponCode(couponNo)
                .getRates();
        Validations validate = new Validations(driver);
        validate
                .verifyPickUpLocation(pickUpLocation)
                .verifyPickUpDate()
                .verifyCouponCode(couponNo);

        Vehicles vehicles = new Vehicles(driver);
        Extras extras = vehicles.step2Submit();
        validate
                .verifyPickUpLocation(pickUpLocation)
                .verifyPickUpDate();
        ReviewAndBook reviewAndBook = extras.Step3Submit();
        validate
                .verifyPickUpLocation(pickUpLocation)
                .verifyPickUpDate();
        reviewAndBook
                .clickContinueReservationButton()
                .checkTermsAndConditions()
                .step4Submit();
        validate
                .verifyPickUpLocation(pickUpLocation)
                .verifyPickUpDate()
                .verifyCouponCode(couponNo)
                .verifyUserEmail();
        return new Confirmation(driver);
    }

    public Confirmation Reservation_Profile_Outbound_PDN_Paylater_US(String pickUpLocation, String pdn){
        payLessCar
                .pickUpLocation(pickUpLocation)
                .clickCouponCheckBox()
                .enterPdn(pdn)
                .getRates();
        Validations validate = new Validations(driver);
        validate
                .verifyPickUpLocation(pickUpLocation)
                .verifyPickUpDate()
                .verifyPdnNumber(pdn);
        Vehicles vehicles = new Vehicles(driver);
        Extras extras = vehicles.step2Submit();
        validate
                .verifyPickUpLocation(pickUpLocation)
                .verifyPickUpDate();
        ReviewAndBook reviewAndBook = extras.Step3Submit();
        validate
                .verifyPickUpLocation(pickUpLocation)
                .verifyPickUpDate();

        reviewAndBook
                .clickContinueReservationButton()
                .checkTermsAndConditions()
                .step4Submit();
        validate
                .verifyPickUpLocation(pickUpLocation)
                .verifyPickUpDate()
                .verifyPdnNumber(pdn)
                .verifyUserEmail();

        return new Confirmation(driver);
    }

    public Confirmation Reservation_Profile_Inbound_IATA_M_type_PayLater_US(String pickUpLocation, String coupon){
        payLessCar
                .pickUpLocation(pickUpLocation)
                .clickCouponCheckBox()
                .enterCouponCode(coupon)
                .getRates();
        Validations validate = new Validations(driver);
        validate
                .verifyPickUpLocation(pickUpLocation)
                .verifyPickUpDate()
                .verifyCouponCode(coupon);
        Vehicles vehicles = new Vehicles(driver);
        Extras extras = vehicles.step2Submit();
        validate
                .verifyPickUpLocation(pickUpLocation)
                .verifyPickUpDate();
        ReviewAndBook reviewAndBook = extras.Step3Submit();
        validate
                .verifyPickUpLocation(pickUpLocation)
                .verifyPickUpDate();
        reviewAndBook
                .clickContinueReservationButton()
                .checkTermsAndConditions()
                .step4Submit();

        validate
                .verifyPickUpLocation(pickUpLocation)
                .verifyPickUpDate()
                .verifyCouponCode(coupon)
                .verifyUserEmail();

        return new Confirmation(driver);
    }

    public Confirmation Reservation_Profile_Verify_Underage_onStep1_keydrop_US(String pickUpLocation, String dropOffLocation, String country, String pickUpTime,
                                                                               String dropOffTime, String age, String fname, String lname, String email, String phoneNo,
                                                                               String ccNumber, String cvv){
        payLessCar
                .pickUpLocation(pickUpLocation)
                .residenceCountry(country)
                .dropOffTime(dropOffTime)
                .getRates();

        assertTrue(payLessCar.isNoCarsAvailableMessageDisplayed(), TextComparison.NO_CARS_AVAILABLE_MESSAGE);

        return new Confirmation(driver);
    }

    public Confirmation Reservation_Outbound_DCC_Paynow_US(String pickUpLocation, String country, String pickUpTime, String fname, String lname,
                                                           String email, String phoneNo, String ccNumber, String cvv){
        payLessCar
                .pickUpLocation(pickUpLocation)
                .pickUpTime(pickUpTime)
                .getRates();
        Validations validate = new Validations(driver);
        validate
                .verifyPickUpLocation(pickUpLocation)
                .verifyPickUpDate();
        Vehicles vehicles = new Vehicles(driver);
        Extras extras = vehicles.step2SubmitPayNow();
        validate
                .verifyPickUpLocation(pickUpLocation)
                .verifyPickUpDate();

        ReviewAndBook reviewAndBook = extras.Step3Submit();
        validate
                .verifyPickUpLocation(pickUpLocation)
                .verifyPickUpDate();
        reviewAndBook
                .clickContinueReservationButton()
                .firstname(fname)
                .lastname(lname)
                .email(email)
                .phone(phoneNo)
                .enterCardNumber(ccNumber)
                .selectExpiryDate()
                .enterSecurityCode(cvv)
                .enterAddressInboundSpecific(country)
                .checkTermsAndConditions()
                .step4Submit();
        validate
                .verifyPickUpLocation(pickUpLocation)
                .verifyPickUpDate()
                .verifyPayNowUserInfo(email);

        return new Confirmation(driver);
    }

    public Confirmation Reservation_Profile_Outbound_DCC_Paynow_US(String pickUpLocation, String country, String pickUpTime, String fname, String lname,
                                                                   String email, String phoneNo, String ccNumber, String cvv){
        payLessCar
                .pickUpLocation(pickUpLocation)
                .pickUpTime(pickUpTime)
                .getRates();
        Validations validate = new Validations(driver);
        validate
                .verifyPickUpLocation(pickUpLocation)
                .verifyPickUpDate();

        Vehicles vehicles = new Vehicles(driver);
        Extras extras = vehicles.step2SubmitPayNow();
        validate
                .verifyPickUpLocation(pickUpLocation)
                .verifyPickUpDate();
        ReviewAndBook reviewAndBook = extras.Step3Submit();
        validate
                .verifyPickUpLocation(pickUpLocation)
                .verifyPickUpDate();
        reviewAndBook
                .clickContinueReservationButton()
                .enterCardNumber(ccNumber)
                .selectExpiryDateUsingDropDown()
                .enterSecurityCode(cvv)
                .checkTermsAndConditions()
                .step4Submit();

        validate
                .verifyPickUpLocation(pickUpLocation)
                .verifyPickUpDate()
                .verifyUserEmail();

        return new Confirmation(driver);
    }

    public Confirmation Reservation_Modify_cancel_flow_Step1_to_Step4_US(String pickUpLocation, String country, String pickUpTime, String pdn,
                                                                         String fname, String lname, String email, String phoneNo) throws InterruptedException {
        payLessCar
                .pickUpLocation(pickUpLocation)
                .residenceCountry(country)
                .clickCouponCheckBox()
                .enterPdn(pdn)
                .getRates();
        Validations validate = new Validations(driver);
        validate
                .verifyPickUpLocation(pickUpLocation)
                .verifyPickUpDate()
                .verifyPdnNumber(pdn);
        Vehicles vehicles = new Vehicles(driver);
        Extras extras = vehicles.step2Submit();
        validate
                .verifyPickUpLocation(pickUpLocation)
                .verifyPickUpDate();
        ReviewAndBook reviewAndBook = extras.Step3Submit();
        validate
                .verifyPickUpLocation(pickUpLocation)
                .verifyPickUpDate();

        reviewAndBook
                .clickContinueReservationButton()
                .firstname(fname)
                .lastname(lname)
                .email(email)
                .phone(phoneNo)
                .checkTermsAndConditions()
                .step4Submit()
                .waitForConfirmationMessage();

        validate
                .verifyPickUpLocation(pickUpLocation)
                .verifyPickUpDate()
                .verifyPdnNumber(pdn)
                .verifyPayLaterUserInfo(email, country);

        Confirmation confirmation = new Confirmation(driver);
        confirmation.closeGetFreeCouponPopup();

        payLessCar
                .clickEditButton()
                .clickModifyReservationButton()
                .calendarSelection()
                .pickUpTime(pickUpTime)
                .clickUpdateButton();

        vehicles.step2Submit();

        extras
                .clickEditRentalDetails()
                .verifyReservationDetails()
                .Step3Submit();

        reviewAndBook.clickReviewModificationsButton()
                .clickKeepModificationButton();
        String reservationNo = reviewAndBook.getConfirmationNumber();

        payLessCar
                .clickMakeNewReservationButton()
                .cancelReservation(reservationNo, lname);

        return new Confirmation(driver);
    }

    public Confirmation Reservation_Profile_Modify_cancel_flow_Step1_to_Step4_US(String pickUpLocation, String country, String pickUpTime, String pdn,
                                                                                 String fname, String lname, String email, String phoneNo) throws InterruptedException {
        payLessCar
                .pickUpLocation(pickUpLocation)
                .getRates();
        Validations validate = new Validations(driver);
        validate
                .verifyPickUpLocation(pickUpLocation)
                .verifyPickUpDate();
        Vehicles vehicles = new Vehicles(driver);
        Extras extras = vehicles.step2Submit();
        validate
                .verifyPickUpLocation(pickUpLocation)
                .verifyPickUpDate();

        ReviewAndBook reviewAndBook = extras.Step3Submit();
        validate
                .verifyPickUpLocation(pickUpLocation)
                .verifyPickUpDate();

        reviewAndBook
                .clickContinueReservationButton()
                .checkTermsAndConditions()
                .step4Submit();
        validate
                .verifyPickUpLocation(pickUpLocation)
                .verifyPickUpDate()
                .verifyUserEmail();


        Confirmation confirmation = new Confirmation(driver);
        confirmation.closeGetFreeCouponPopup();

        payLessCar
                .clickEditButton()
                .clickModifyReservationButton()
                .calendarSelection()
                .pickUpTime(pickUpTime)
                .clickUpdateButton();

        vehicles.step2Submit();

        extras
                .clickEditRentalDetails()
                .verifyReservationDetails()
                .Step3Submit();

        reviewAndBook.clickReviewModificationsButton()
                .clickKeepModificationButton();
        String reservationNo = reviewAndBook.getConfirmationNumber();

        payLessCar
                .clickMakeNewReservationButton()
                .cancelReservation(reservationNo, lname);

        return new Confirmation(driver);
    }



}