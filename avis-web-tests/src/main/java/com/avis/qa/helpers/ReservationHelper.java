package com.avis.qa.helpers;

import com.avis.qa.components.LoginWidget;
import com.avis.qa.components.ReservationWidget;
import com.avis.qa.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static com.avis.qa.utilities.CommonUtils.ONE_SECOND;
import static com.avis.qa.utilities.CommonUtils.threadSleep;
import static org.testng.Assert.assertTrue;

public class ReservationHelper {

    private final WebDriver driver;
    private final ReservationWidget reservationWidget;

    public ReservationHelper(WebDriver driver) {
        this.driver = driver;
        this.reservationWidget = new ReservationWidget(driver);
    }


    public void Reservation_DomesticOrOutbound_PayNow(String pickUpLocation, String firstName, String lastName,

                                                      String email, String phoneNumber, String ccNumber, String cvv) {
        reservationWidget
                .pickUpLocation(pickUpLocation)
                .calendarSelection()
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        Extras extras = vehicles.step2SubmitPayNow();
        ReviewAndBook reviewAndBook = extras.Step3Submit();

        reviewAndBook
                .clickContinueReservationButton()
                .firstname(firstName)
                .lastname(lastName)
                .email(email)
                .phone(phoneNumber)
                .enterCardNumber(ccNumber)
                .selectExpiryDate()
                .enterSecurityCode(cvv)
                .enterAddress()
                .checkTermsAndConditions()
                .setSelectedCountryText()
                .step4Submit();

        Assert.assertTrue(reviewAndBook.verifySelectedCountryText("U S A") || reviewAndBook.verifySelectedCountryText("U.S.A."), "Country Text is incorrect");
    }

    public ReviewAndBook Reservation_CCOLocation_PayLater(String pickUpLocation, String firstName, String lastName, String email,
                                                          String phoneNo, String cCNumber) {

        reservationWidget
                .pickUpLocation(pickUpLocation)
                .calendarSelection()
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        Extras extras = vehicles.step2Submit();
        ReviewAndBook reviewAndBook = extras.Step3Submit();

        reviewAndBook
                .clickContinueReservationButton()
                .firstname(firstName)
                .lastname(lastName)
                .email(email)
                .phone(phoneNo);

        return reviewAndBook;
    }

    public Confirmation Reservation_Costco_USAA_PayLater(String pickUpLocation, String awd, String membershipNo, String fname,
                                                         String lname, String email, String phoneNo) {

        reservationWidget
                .pickUpLocation(pickUpLocation)
                .calendarSelection()
                .expandDiscountCode()
                .enterAwd(awd)
                .enterMembershipNo(membershipNo)
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        Extras extras = vehicles.step2Submit();
        ReviewAndBook reviewAndBook = extras.Step3Submit();

        reviewAndBook
                .clickContinueReservationButton()
                .firstname(fname)
                .lastname(lname)
                .email(email)
                .phone(phoneNo)
                .checkTermsAndConditions()
                .step4Submit();

        return new Confirmation(driver);
    }

    public Confirmation Reservation_GEBUser_PayLater(String pickUpLocation, String wizardNo, String lastName, String firstName,
                                                     String email, String phoneNo) {

        reservationWidget
                .pickUpLocation(pickUpLocation)
                .calendarSelection()
                .expandAvisWizardNumber()
                .enterAwdAndLastname(wizardNo, lastName)
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        Extras extras = vehicles.step2Submit();
        ReviewAndBook reviewAndBook = extras.Step3Submit();

        reviewAndBook
                .clickContinueReservationButton()
                .firstname(firstName)
                .email(email)
                .phone(phoneNo)
                .checkTermsAndConditions()
                .step4Submit();

        return new Confirmation(driver);
    }

    public Confirmation Reservation_CouponProcessing_PayLater(String pickUpLocation, String couponNo, String fname,

                                                              String lname, String email, String phoneNo) {

        reservationWidget
                .pickUpLocation(pickUpLocation)
                .calendarSelection()
                .expandDiscountCode()
                .enterCouponCode(couponNo)
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        Extras extras = vehicles.step2Submit();
        ReviewAndBook reviewAndBook = extras.Step3Submit();

        reviewAndBook
                .clickContinueReservationButton()
                .firstname(fname)
                .lastname(lname)
                .email(email)
                .phone(phoneNo)
                .checkTermsAndConditions()
                .step4Submit();

        return new Confirmation(driver);
    }

    public Confirmation Reservation_Inbound_PayLater(String pickUpLocation, String Country, String firstName, String lastName,
                                                     String email, String phoneNumber) {
        reservationWidget
                .pickUpLocation(pickUpLocation)
                .calendarSelection()
                .selectCountry(Country)
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        Extras extras = vehicles.step2Submit();
        ReviewAndBook reviewAndBook = extras.Step3Submit();

        reviewAndBook
                .clickContinueReservationButton()
                .firstname(firstName)
                .lastname(lastName)
                .email(email)
                .phone(phoneNumber)
                .checkTermsAndConditions()
                .step4Submit();

        return new Confirmation(driver);
    }

    public Confirmation Reservation_Inbound_PayNow(String pickUpLocation, String country, String firstName, String lastName,
                                                   String email, String phoneNumber, String ccNumber, String cvv) {
        reservationWidget
                .pickUpLocation(pickUpLocation)
                .calendarSelection()
                .selectCountry(country)
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        Extras extras = vehicles.step2SubmitPayNow();
        ReviewAndBook reviewAndBook = extras.Step3Submit();

        reviewAndBook
                .clickContinueReservationButton()
                .firstname(firstName)
                .lastname(lastName)
                .email(email)
                .phone(phoneNumber)
                .enterCardNumber(ccNumber)
                .selectExpiryDate()
                .enterSecurityCode(cvv)
                .enterAddressInboundSpecific(country)
                .checkTermsAndConditions()
                .step4Submit();

        return new Confirmation(driver);
    }

    public Confirmation Reservation_SnowChain_PayLater(String pickUpLocation, String firstName, String lastName, String email,
                                                       String phoneNo) {
        reservationWidget
                .pickUpLocation(pickUpLocation)
                .calendarSelectionSnowChain()
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        Extras extras = vehicles.step2Submit();
        ReviewAndBook reviewAndBook = extras.Step3Submit();


        reviewAndBook
                .clickContinueReservationButton()
                .firstname(firstName)
                .lastname(lastName)
                .email(email)
                .phone(phoneNo)
                .checkTermsAndConditions()
                .step4Submit();

        return new Confirmation(driver);
    }

    public Confirmation Reservation_Recognised_Paylater(String pickUpLocation, String wizardNo, String lastName,
                                                        String pickUpLocation1) {

        reservationWidget
                .pickUpLocation(pickUpLocation)
                .calendarSelection()
                .expandAvisWizardNumber()
                .enterAwdAndLastname(wizardNo, lastName)
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        Extras extras = vehicles.step2Submit();
        ReviewAndBook reviewAndBook = extras.Step3Submit();

        reviewAndBook
                .clickContinueReservationButton()
                .checkTermsAndConditions()
                .step4Submit();

        Confirmation confirmation = new Confirmation(driver);
        ReservationWidget reservationWidget = confirmation
                .closeGetFreeCouponPopup()
                .flyOutClose()
                .modifyTimeAndPlaceClick();

        reservationWidget.pickUpLocation(pickUpLocation1)
                .selectMyCar();

        vehicles.payLater();
        extras.Step3Submit();
        reviewAndBook
                .reviewModifications()
                .keepModification();
        confirmation.flyOutClose();

        return confirmation;
    }


    public Confirmation Reservation_OutboundAndMultiCurrency_Paylater(String pickUpLocation, String firstName, String lastName,
                                                                      String email, String phoneNumber, String flightNumber) {
        reservationWidget
                .pickUpLocation(pickUpLocation)
                .calendarSelection()
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        assertTrue(vehicles.isCurrencyValueDisplayed(), "Currency value is not displayed");

        Extras extras = vehicles.step2Submit();
        ReviewAndBook reviewAndBook = extras.Step3Submit();

        reviewAndBook
                .firstname(firstName)
                .lastname(lastName)
                .email(email)
                .phone(phoneNumber)
                .flightInfo()
                .enterflightNumber(flightNumber)
                .checkTermsAndConditions()
                .step4Submit();

        return new Confirmation(driver);

    }

    public Confirmation Reservation_OneWay_PayLater(String pickUpLoction, String dropOffLocation, String fname, String lname,
                                                    String email, String phoneNo) {

        reservationWidget
                .pickUpLocation(pickUpLoction)
                .dropOffLocation(dropOffLocation)
                .calendarSelection()
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        Extras extras = vehicles.step2Submit();
        ReviewAndBook reviewAndBook = extras.Step3Submit();


        reviewAndBook
                .clickContinueReservationButton()
                .firstname(fname)
                .lastname(lname)
                .email(email)
                .phone(phoneNo)
                .checkTermsAndConditions()
                .step4Submit();

        return new Confirmation(driver);
    }

    public Confirmation Reservation_TierBundle_PayLater(String pickUpLocation, String firstName, String lastName, String email,
                                                        String phoneNo, String ccNo, String cvv) {

        reservationWidget
                .pickUpLocation(pickUpLocation)
                .calendarSelection()
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        Extras extras = vehicles.step2Submit();
        ReviewAndBook reviewAndBook = extras
                .selectTierBundle()
                .Step3Submit();

        reviewAndBook
                .firstname(firstName)
                .lastname(lastName)
                .email(email)
                .phone(phoneNo)
                .checkTermsAndConditions()
                .step4Submit();

        Confirmation confirmation = new Confirmation(driver);
        return confirmation.closeGetFreeCouponPopup();
    }

    public Confirmation Reservation_UnderAgeAndHomePageErrorMessageAndPersonalInfoValidation_Paylater(String pickUpLocation,
                                                                                                      String age, String firstName, String lastName, String email, String phoneNo) {
        reservationWidget.selectMyCar();
        Assert.assertTrue(reservationWidget.verifyNullPickUpLocationMessage(), "Null Pick UpLocation Message is incorrect");

        reservationWidget
                .pickUpLocation(pickUpLocation)
                .calendarSelection()
                .selectAge(age)
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        Extras extras = vehicles
                .verifyUnderAgeMsg()
                .step2Submit();
        ReviewAndBook reviewAndBook = extras.Step3Submit();

        String emailEntered = reviewAndBook
                .clickContinueReservationButton()
                .firstname(firstName)
                .lastname(lastName)
                .emailReturnType(email);

        reviewAndBook
                .phone(phoneNo)
                .checkTermsAndConditions()
                .step4Submit();

        Confirmation confirmation = new Confirmation(driver);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        assertTrue(confirmation.verifypersonalInfo(firstName, emailEntered));

        return confirmation;
    }

    public void Reservation_FTPPayLater_Authenticated(String wizardNo, String password, String pickUpLocation,
                                                      String partnerName, String membershipNo) {

        LoginWidget loginWidget = new LoginWidget(driver);
        loginWidget
                .loginHeaderclick()
                .login(wizardNo, password);
        threadSleep(ONE_SECOND);

        Homepage homepage = new Homepage(driver);
        ProfileDashboard profileDashboard = homepage.profileDashboardNavigation();

        profileDashboard
                .navigateToRewardsTab()
                .clickOnEditRewards()
                .enterPartnerNameAndNo(partnerName, membershipNo)
                .clickOnSaveButton();
    }

    public Confirmation Reservation_AuthenticatedAndDomestic_Paylater(String pickUpLocation, String wizardNo, String password,
                                                                      String pickUpLocation1) {
        // Test steps on Global Header
        LoginWidget loginWidget = new LoginWidget(driver);
        ReservationWidget rw = loginWidget
                .loginHeaderclick()
                .login(wizardNo, password)
                .clickPickupLocation();

        rw
                .pickUpLocation(pickUpLocation)
                .calendarSelection()
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        Extras extras = vehicles.step2Submit();
        ReviewAndBook reviewAndBook = extras.Step3Submit();

        reviewAndBook
                .smsOptInCheckbox()
                .checkTermsAndConditions()
                .step4Submit();

        Confirmation confirmation = new Confirmation(driver);
        ReservationWidget reservationWidget = confirmation
                .closeGetFreeCouponPopup()
                .flyOutClose()
                .modifyTimeAndPlaceClick();

        reservationWidget.pickUpLocation(pickUpLocation1)
                .selectMyCar();

        vehicles.step2Submit();
        extras.Step3Submit();
        reviewAndBook
                .reviewModifications()
                .keepModification();
        confirmation.flyOutClose();

        return new Confirmation(driver);
    }

    public Confirmation Reservation_SplitBillItemized_PayLater(String pickUpLoction, String AWD, String fname, String lname,
                                                               String mail, String pNo, String primaryCardNo, String secCardNo) {

        reservationWidget
                .pickUpLocation(pickUpLoction)
                .calendarSelection()
                .expandDiscountCode()
                .enterAwd(AWD)
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        Extras extras = vehicles.step2Submit();
        ReviewAndBook reviewAndBook = extras.selectTierBundle().Step3Submit();

        reviewAndBook
                .clickContinueReservationButton()
                .firstname(fname)
                .lastname(lname)
                .email(mail)
                .phone(pNo);


//        splitBill.optForSplit();
//        splitBill.addPrimmaryAndSecondaryCardsForSplit(primaryCardNo, secCardNo);
//        splitBill.itmizedSplit();
//        splitBill.itemizedSpitConfirm();
//        splitBill.splitBillSave();

        reviewAndBook
                .checkTermsAndConditions()
                .step4Submit();


        //  splitBill.validateSplitBillOnConfirmationPage();

        return new Confirmation(driver);
    }

    public Confirmation Reservation_SMSOptInProfile_Verification(String wizardNo, String password, String pickUpLocation,
                                                                 String phoneNo) {

        LoginWidget loginWidget = new LoginWidget(driver);
        ReservationWidget rw = loginWidget
                .loginHeaderclick()
                .login(wizardNo, password)
                .clickPickupLocation();

        rw
                .pickUpLocation(pickUpLocation)
                .calendarSelection()
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        Extras extras = vehicles.step2Submit();
        ReviewAndBook reviewAndBook = extras.Step3Submit();


        reviewAndBook
                .smsOptInCheckbox()
                .checkTermsAndConditions()
                .step4Submit();

        return new Confirmation(driver);
    }

    public Confirmation Reservation_LastMinuteBundle_PayLater(String pickUpLocation, String firstName, String lastName,
                                                              String email, String phoneNo) {
        reservationWidget
                .pickUpLocation(pickUpLocation)
                //.lastMinuteCalender();
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        // vehicles.lastMinuteBanner();

        ReviewAndBook reviewAndBook = new ReviewAndBook(driver);

        reviewAndBook
                .clickContinueReservationButton()
                .firstname(firstName)
                .lastname(lastName)
                .email(email)
                .phone(phoneNo)
                .checkTermsAndConditions()
                .step4Submit();

        return new Confirmation(driver);
    }

    public Confirmation Reservation_AnonymousDomesticAWDorBDCorPDNAndIATAAndKeyDrop_Paylater(String pickUpLocation,
                                                                                             String dropOffTime, String awd, String firstName, String lastName, String email, String phoneNo,
                                                                                             String iataNo, String pickUpLocation1) {

        reservationWidget
                .pickUpLocation(pickUpLocation)
                .calendarSelection()
                .expandDiscountCode()
                .enterAwd(awd)
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        Extras extras = vehicles
                .step2Submit();
        ReviewAndBook reviewAndBook = extras.Step3Submit();

        reviewAndBook
                .clickContinueReservationButton()
                .firstname(firstName)
                .lastname(lastName)
                .email(email)
                .phone(phoneNo)
                .smsOptInCheckbox()
                .iataNumber(iataNo)
                .checkTermsAndConditions()
                .step4Submit();


        Confirmation confirmation = new Confirmation(driver);
        assertTrue(confirmation.isAwdConfirmationPageTextDisplayed(), "AWD Confirmation text is not displayed");
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");

        ReservationWidget reservationWidget = confirmation
                .closeGetFreeCouponPopup()
                .flyOutClose()
                .modifyTimeAndPlaceClick();

        reservationWidget
                .pickUpLocation(pickUpLocation1)
                .dropOffTime(dropOffTime)
                .selectMyCar();

        vehicles
                .keyDropMessageValidation()
                .payLater();

        extras.Step3Submit();
        reviewAndBook
                .reviewModifications()
                .keepModification();
        confirmation.flyOutClose();

        return confirmation;
    }

    public Confirmation Reservation_MandateflightInfoAndTtypeCouponProcessingStrikeThrough_Paylater(String pickUpLocation,
                                                                                                    String firstName, String lastName, String emailId, String phoneNo, String flightNumber,
                                                                                                    String pickUpLocation1, String couponNo) {

        reservationWidget
                .pickUpLocation(pickUpLocation)
                .calendarSelection()
                .expandDiscountCode()
                .enterCouponCode(couponNo)
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        Assert.assertTrue(vehicles.isStrikreThroughPriceIndicatorDisplayed(), "Strikre Through Price Indicator not Displayed");

        Extras extras = vehicles.payLater();
        ReviewAndBook reviewAndBook = extras.Step3Submit();

        reviewAndBook
                .clickContinueReservationButton()
                .firstname(firstName)
                .lastname(lastName)
                .email(emailId)
                .phone(phoneNo)
                .checkTermsAndConditions()
                .step4Submit();

        return new Confirmation(driver);
    }

    public ReservationWidget getReservationWidget(){
        return this.reservationWidget;
    }

}
