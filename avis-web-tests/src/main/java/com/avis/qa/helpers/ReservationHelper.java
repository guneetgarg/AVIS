package com.avis.qa.helpers;

import com.avis.qa.components.LoginWidget;
import com.avis.qa.components.ReservationWidget;
import com.avis.qa.core.AbstractBasePage;
import com.avis.qa.core.Configuration;
import com.avis.qa.pages.*;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import lombok.extern.log4j.Log4j2;
import java.util.Iterator;
import java.util.Set;

import static com.avis.qa.constants.AvisConstants.*;
import static com.avis.qa.constants.TextComparison.*;
import static com.avis.qa.utilities.CommonUtils.ONE_SECOND;
import static com.avis.qa.utilities.CommonUtils.threadSleep;
import static org.testng.Assert.assertTrue;

@Log4j2
public class ReservationHelper extends AbstractBasePage {

    private final WebDriver driver;
    private final ReservationWidget reservationWidget;

    public ReservationHelper(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.reservationWidget = new ReservationWidget(driver);
    }

    @Override
    public void isOnPage() {
        log.info("Is on Reservationhelper");
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
                .EnterExpiryDateAndYear()
                .enterSecurityCode(cvv)
                .enterAddress()
                .SelectflightInfo(FLIGHT_NAME)
                .enterflightNumber(FLIGHT_NUMBER)
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

    public Confirmation Reservation_Domestic_PayLater(String pickUpLocation, String fname,
                                                         String lname, String email, String phoneNo) {

        reservationWidget
                .pickUpLocation(pickUpLocation)
                .calendarSelection(2)
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        Extras extras = vehicles.step2Submit();
        if(!(Configuration.BRAND.equalsIgnoreCase("Budget") && Configuration.DOMAIN.equalsIgnoreCase("NZ"))){
            assertTrue(extras.isUpliftTextDisplayed());
        }
        ReviewAndBook reviewAndBook = extras.Step3Submit();
        reviewAndBook
                .clickContinueReservationButton()
                .firstname(fname)
                .lastname(lname)
                .email(email)
                .phone(phoneNo)
                .SelectflightInfo(FLIGHT_NAME)
                .enterflightNumber(FLIGHT_NUMBER)
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
                .SelectflightInfo(FLIGHT_NAME)
                .enterflightNumber(FLIGHT_NUMBER)
                .checkTermsAndConditions()
                .step4Submit();

        return new Confirmation(driver);
    }

    public Confirmation Reservation_TtypeCouponProcessing_PayLater(String pickUpLocation, String couponNo, String fname,

                                                              String lname, String email, String phoneNo, String couponMsg) {

        reservationWidget
                .closeAdPopup()
                .pickUpLocation(pickUpLocation)
                .calendarSelection(2)
                .expandDiscountCode()
                .enterCouponCode(couponNo)
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
       // vehicles.savingMessageValidation(couponNo);
            Extras  extras = vehicles.step2Submit2();

        assertTrue(extras.isDiscountCodeSavingtextDisplayed(),"Discount Code Saving text is not displayed");
        if(Configuration.BRAND.equalsIgnoreCase("Budget") && Configuration.DOMAIN.equalsIgnoreCase("US")) {
            extras.isUpliftTextDisplayed();
        }

        ReviewAndBook reviewAndBook = extras.Step3Submit();
        assertTrue(reviewAndBook.isDiscountCodeSavingtextDisplayed(),"Discount Code Saving text is not displayed");

        reviewAndBook
                .clickContinueReservationButton()
                .firstname(fname)
                .lastname(lname)
                .email(email)
                .phone(phoneNo);
        if(Configuration.BRAND.equalsIgnoreCase("Budget") && Configuration.DOMAIN.equalsIgnoreCase("US"))
        {
            reviewAndBook
                    .step4_CreditCardCheckBox()
                    .enterCardNumber(CreditCardNumber)
                    .EnterExpiryDateAndYear()
                    .enterSecurityCode(CVV)
                    .enterAddressInboundSpecific(ResidentLoc);
        }
        reviewAndBook
                .SelectflightInfo(FLIGHT_NAME)
                .enterflightNumber(FLIGHT_NUMBER)
                .checkTermsAndConditions()
                .step4Submit();

        return new Confirmation(driver);
    }


    public Confirmation Budget_RES_Profile_T_typeCoupon_CCOLocation_PayLater_US(String pickUpLocation, String couponNo, String couponMsg) {

        reservationWidget
                .closeAdPopup()
                .pickUpLocation(pickUpLocation)
                .calendarSelection(2)
                .expandDiscountCode()
                .enterCouponCode(couponNo)
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        // vehicles.savingMessageValidation(couponNo);
        Extras  extras = vehicles.step2Submit2();

//        assertTrue(extras.isDiscountCodeSavingtextDisplayed(),"Discount Code Saving text is not displayed");
//        if(Configuration.BRAND.equalsIgnoreCase("Budget")) {
//            extras.isUpliftTextDisplayed();
//        }

        ReviewAndBook reviewAndBook = extras.Step3Submit();
        assertTrue(reviewAndBook.isDiscountCodeSavingtextDisplayed(),"Discount Code Saving text is not displayed");

        reviewAndBook
                .clickContinueReservationButton()
                .step4_CreditCardCheckBox()
                .enterCardNumber(CreditCardNumber)
                .EnterExpiryDateAndYear()
                .enterSecurityCode(CVV)
                .enterAddressInboundSpecific(ResidentLoc);

        reviewAndBook
                .checkTermsAndConditions()
                .step4Submit();
        return new Confirmation(driver);
    }

    public Confirmation Reservation_Profile_TtypeCouponProcessing_PayLater(String pickUpLocation, String couponNo, String couponMsg) {

        reservationWidget
                .pickUpLocation(pickUpLocation)
                .calendarSelection()
                .expandDiscountCode()
                .enterCouponCode(couponNo)
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        vehicles.savingMessageValidation(couponNo);
        Extras extras = vehicles.step2Submit();
        assertTrue(extras.isDiscountCodeSavingtextDisplayed(),"Discount Code Saving text is not displayed");
        ReviewAndBook reviewAndBook = extras.Step3Submit();
        assertTrue(reviewAndBook.isDiscountCodeSavingtextDisplayed(),"Discount Code Saving text is not displayed");

        reviewAndBook
                .clickContinueReservationButton()
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

    public Confirmation Reservation_Profile_Domestic_PayLater(String pickUpLocation) {
        reservationWidget
                .pickUpLocation(pickUpLocation)
                .calendarSelection()
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        Extras extras = vehicles.step2Submit();
        ReviewAndBook reviewAndBook = extras.Step3Submit();

        reviewAndBook
                .clickContinueReservationButton()
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
               // .selectExpiryDateAndYear()
                .EnterExpiryDateAndYear()
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


    public Confirmation Reservation_OutboundAndMultiCurrency_Paylater(String pickUpLocation,String residencyLocation, String firstName, String lastName,
                                                                      String email, String phoneNumber, String flightNumber, String currencyValue) {
        reservationWidget
                .pickUpLocation(pickUpLocation)
                .calendarSelection()
        		.selectCountry(residencyLocation)
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

                .SelectflightInfo(3)
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
                .SelectflightInfo(FLIGHT_NAME)
                .enterflightNumber(FLIGHT_NUMBER)
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
        assertTrue(confirmation.isAwdConfirmationPageTextDisplayed(awd), "AWD Confirmation text is not displayed");
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

    public Confirmation Reservation_MTypeKeyDropLocation_PayLater(String pickUpLocation, String dropOffTime, String couponNo, String fname, String lname, String email, String phoneNo){
        reservationWidget
                .pickUpLocation(pickUpLocation)
                .calendarSelection()
                .dropOffTime(dropOffTime)
                .expandDiscountCode()
                .enterCouponCode(couponNo)
                //.enterAwd(awd)
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        vehicles.keyDropMessageValidation();
        Extras extras = vehicles.Step2_DiscountAppliedSubmit();
        assertTrue(extras.isDiscountCodeSavingtextDisplayed(),"Discount Code Saving text is not displayed");
        ReviewAndBook reviewAndBook = extras.Step3Submit();
        assertTrue(reviewAndBook.isDiscountCodeSavingtextDisplayed(),"Discount Code Saving text is not displayed");

        reviewAndBook
                .clickContinueReservationButton()
                .firstname(fname)
                .lastname(lname)
                .email(email)
                .phone(phoneNo)
                .smsOptInCheckbox()
                .checkTermsAndConditions()
                .step4Submit();


        Confirmation confirmation = new Confirmation(driver);
      //  assertTrue(confirmation.isAwdConfirmationPageTextDisplayed(awd), "AWD Confirmation text is not displayed");
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");


        return confirmation;
    }

    public Confirmation Reservation_Profile_MTypeKeyDropLocation_PayLater(String pickUpLocation, String dropOffTime, String couponNo){
        reservationWidget
                .pickUpLocation(pickUpLocation)
                .calendarSelection()
                .dropOffTime(dropOffTime)
                .expandDiscountCode()
                .enterCouponCode(couponNo)
                //.enterAwd(awd)
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        vehicles.keyDropMessageValidation();
        Extras extras = vehicles.Step2_DiscountAppliedSubmit();
        assertTrue(extras.isDiscountCodeSavingtextDisplayed());
        ReviewAndBook reviewAndBook = extras.Step3Submit();
        assertTrue(reviewAndBook.isDiscountCodeSavingtextDisplayed());

        reviewAndBook
                .clickContinueReservationButton()
                .checkTermsAndConditions()
                .step4Submit();


        Confirmation confirmation = new Confirmation(driver);
        //  assertTrue(confirmation.isAwdConfirmationPageTextDisplayed(awd), "AWD Confirmation text is not displayed");
       // assertTrue(confirmation.isConfirmationNumberDisplayed());


        return confirmation;
    }

    public Confirmation Reservation_AnonymousDomesticAWD_Paylater(String pickUpLocation, String awd, String firstName, String lastName, String email, String phoneNo) {

        reservationWidget
                .pickUpLocation(pickUpLocation)
                .calendarSelection()
                .expandDiscountCode()
                .enterAwd(awd)
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        assertTrue(vehicles.isDiscountDropdownDisplayed(), "coupon link text is not displayed");
        Extras extras = vehicles
                .Step2_DiscountAppliedSubmit();
        assertTrue(extras.isDiscountCodeSavingtextDisplayed(),"Discount Code Saving text is not displayed");
        ReviewAndBook reviewAndBook = extras.Step3Submit();
        assertTrue(reviewAndBook.isDiscountCodeSavingtextDisplayed(),"Discount Code Saving text is not displayed");

        reviewAndBook
                .clickContinueReservationButton()
                .firstname(firstName)
                .lastname(lastName)
                .email(email)
                .phone(phoneNo)
                .smsOptInCheckbox()
                .checkTermsAndConditions()
                .step4Submit();


        Confirmation confirmation = new Confirmation(driver);
        assertTrue(confirmation.isAwdConfirmationPageTextDisplayed(awd), "AWD Confirmation text is not displayed");
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");


        return confirmation;
    }

    public Confirmation Reservation_OneWay_USAA_PayLater(String pickUpLocation, String dropOffLocation, String awd, String membershipNo, String fname, String lname,
                                                    String email, String phoneNo) {

        reservationWidget
                .pickUpLocation(pickUpLocation)
                .dropOffLocation(dropOffLocation)
                .calendarSelection()
                .expandDiscountCode()
                .enterAwd(awd)
                .enterMembershipNo(membershipNo)
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
      //  assertTrue(vehicles.isUSAACoupontextDisplayed(), "USAA coupon text is not displayed");
        //Extras extras = vehicles.Step2_ClickDiscountAppliedSubmit();
        Extras extras = vehicles.step2Submit();
        extras.verifyCurbsideNotDisplayed();

        ReviewAndBook reviewAndBook = extras.Step3Submit();

        reviewAndBook
                .clickContinueReservationButton()
                .firstname(fname)
                .lastname(lname)
                .email(email)
                .phone(phoneNo)
                .SelectflightInfo(FLIGHT_NAME)
                .enterflightNumber(FLIGHT_NUMBER)
                .checkTermsAndConditions()
                .step4Submit();

        return new Confirmation(driver);
    }

    public Confirmation Reservation_Profile_OneWay_USAA_PayLater(String pickUpLocation, String dropOffLocation, String awd, String membershipNo) {

        reservationWidget
                .pickUpLocation(pickUpLocation)
                .dropOffLocation(dropOffLocation)
                .calendarSelection()
                .expandDiscountCode()
                .enterAwd(awd)
                .enterMembershipNo(membershipNo)
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        //  assertTrue(vehicles.isUSAACoupontextDisplayed(), "USAA coupon text is not displayed");
        //Extras extras = vehicles.Step2_ClickDiscountAppliedSubmit();
        Extras extras = vehicles.step2Submit();
        extras.verifyCurbsideNotDisplayed();

        ReviewAndBook reviewAndBook = extras.Step3Submit();

        reviewAndBook
                .clickContinueReservationButton()
                .checkTermsAndConditions()
                .step4Submit();

        return new Confirmation(driver);
    }

    public Confirmation Reservation_OutboundAndStrikeThroughCoupon_Paylater(String pickUpLocation,String residencyLocation, String awd, String fname, String lname,
                                                                            String email, String phoneNo, String flightNumber) {

        reservationWidget
                .pickUpLocation(pickUpLocation)
                .selectCountry(residencyLocation)
                .calendarSelection(2)
                .expandDiscountCode()
                .enterAwd(awd)
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        Assert.assertTrue(vehicles.isStrikreThroughPriceIndicatorDisplayed(), "Strikre Through Price Indicator not Displayed");
        Assert.assertTrue(vehicles.isSavingtextDisplayed(), "Extras and protection text not Displayed");
        Extras extras = vehicles.step2Submit();
      //  assertTrue(extras.verifyLossDamageWaiverIsSelected(),"LDW is not selected");
        ReviewAndBook reviewAndBook = extras.Step3Submit();

        reviewAndBook
                .clickContinueReservationButton()
                .firstname(fname)
                .lastname(lname)
                .email(email)
                .phone(phoneNo)
                .SelectflightInfo(3)
                .enterflightNumber(flightNumber)
                .checkTermsAndConditions()
                .step4Submit();

        return new Confirmation(driver);
    }

    public Confirmation Reservation_OutboundAndStrikeThroughCoupon_LocMandate_FlightInfo_Paylater(String pickUpLocation,String residencyLocation, String awd, String MembershipNum,  String fname, String lname,
                                                                            String email, String phoneNo, String flightNumber) {

        reservationWidget
                .closeAdPopup()
                .pickUpLocation(pickUpLocation)
                .selectCountry(residencyLocation)
                .calendarSelection(2)
                .expandDiscountCode()
                .enterAwd(awd)
                .enterMembershipNo(MembershipNum)
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        if(!(Configuration.BRAND.equalsIgnoreCase("Budget") && Configuration.DOMAIN.equalsIgnoreCase("NZ"))) {
            Assert.assertTrue(vehicles.isStrikreThroughPriceIndicatorDisplayed(), "Strikre Through Price Indicator not Displayed");
        }
       // Assert.assertTrue(vehicles.isSavingtextDisplayed(), "Extras and protection text not Displayed");
        Extras extras = vehicles.step2Submit();
        //  assertTrue(extras.verifyLossDamageWaiverIsSelected(),"LDW is not selected");
        ReviewAndBook reviewAndBook = extras.Step3Submit();

        reviewAndBook
                .clickContinueReservationButton()
                .firstname(fname)
                .lastname(lname)
                .email(email)
                .phone(phoneNo)
                .SelectflightInfo(3)
                .enterflightNumber(flightNumber)
                .checkTermsAndConditions()
                .step4Submit();

        return new Confirmation(driver);
    }

    public Confirmation Budget_RES_Profile_Outbound_StrikeThroughCoupon_LocMandate_FlightInfo_Cancellation_PayLater_US(String pickUpLocation,String residencyLocation, String awd, String MembershipNum, String flightNumber) {

        reservationWidget
                .closeAdPopup()
                .pickUpLocation(pickUpLocation)
                .selectCountry(residencyLocation)
                .calendarSelection(2)
                .expandDiscountCode()
                .enterAwd(awd)
                .enterMembershipNo(MembershipNum)
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        Assert.assertTrue(vehicles.isStrikreThroughPriceIndicatorDisplayed(), "Strikre Through Price Indicator not Displayed");
        // Assert.assertTrue(vehicles.isSavingtextDisplayed(), "Extras and protection text not Displayed");
        Extras extras = vehicles.step2Submit();
        //  assertTrue(extras.verifyLossDamageWaiverIsSelected(),"LDW is not selected");
        ReviewAndBook reviewAndBook = extras.Step3Submit();

        reviewAndBook
                .clickContinueReservationButton()
                .SelectflightInfo(3)
                .enterflightNumber(flightNumber)
                .checkTermsAndConditions()
                .step4Submit();

        return new Confirmation(driver);
    }

    public Confirmation Reservation_Profile_OutboundAndStrikeThroughCoupon_Paylater(String pickUpLocation,String residencyLocation, String awd, String flightNumber) {

        reservationWidget
                .pickUpLocation(pickUpLocation)
                .selectCountry(residencyLocation)
                .calendarSelection(2)
                .expandDiscountCode()
                .enterAwd(awd)
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        Assert.assertTrue(vehicles.isStrikreThroughPriceIndicatorDisplayed(), "Strikre Through Price Indicator not Displayed");
        Assert.assertTrue(vehicles.isSavingtextDisplayed(), "Extras and protection text not Displayed");
        Extras extras = vehicles.step2Submit();
        //  assertTrue(extras.verifyLossDamageWaiverIsSelected(),"LDW is not selected");
        ReviewAndBook reviewAndBook = extras.Step3Submit();

        reviewAndBook
                .clickContinueReservationButton()
                .SelectflightInfo(3)
                .enterflightNumber(flightNumber)
                .checkTermsAndConditions()
                .step4Submit();

        return new Confirmation(driver);
    }

    public Confirmation Reservation_InboundAndMultiCurrency_Paylater(String pickUpLocation,String residencyLocation, String firstName, String lastName,
                                                                      String email, String phoneNumber, String flightNumber,String residentCurrencySymbol, String currencyValue) {
        reservationWidget
                .closeAdPopup()
                .pickUpLocation(pickUpLocation)
                .calendarSelection()
                .selectCountry(residencyLocation)
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        vehicles.closeAdPopup();
        assertTrue(vehicles.isCurrencyValueDisplayed(), CURRENCY_VALUE_NOT_DISPLAYED_MESSAGE);
        assertTrue(vehicles.verifyCurrencySymbolDisplayed(residentCurrencySymbol), CURRENCY_VALUE_NOT_SAME_RESIDENCE_COUNTRY_MESSAGE);
        Extras extras = vehicles.step2Submit();
        assertTrue(extras.verifyCurrencySymbolDisplayed(residentCurrencySymbol), CURRENCY_VALUE_NOT_SAME_RESIDENCE_COUNTRY_MESSAGE);
        ReviewAndBook reviewAndBook = extras.Step3Submit();

        reviewAndBook
                .firstname(firstName)
                .lastname(lastName)
                .email(email)
                .phone(phoneNumber);
        reviewAndBook
                .SelectflightInfo(FLIGHT_NAME)
                .enterflightNumber(flightNumber)
                .checkTermsAndConditions()
                .step4Submit();

        return new Confirmation(driver);

    }

    public Confirmation Reservation_Profile_InboundAndMultiCurrency_Paylater(String pickUpLocation, String flightNumber,String residentCurrencySymbol, String currencyValue) {
        reservationWidget
                .pickUpLocation(pickUpLocation)
                .calendarSelection()
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        assertTrue(vehicles.isCurrencyValueDisplayed(), "Currency value is not displayed");
        assertTrue(vehicles.verifyCurrencySymbolDisplayed(residentCurrencySymbol), "Currency  value is not same as residence country");
        Extras extras = vehicles.step2Submit();
        assertTrue(extras.verifyCurrencySymbolDisplayed(residentCurrencySymbol), "Currency  value is not same as residence country");
        ReviewAndBook reviewAndBook = extras.Step3Submit();

        reviewAndBook
                .SelectflightInfo(3)
                .enterflightNumber(flightNumber)
                .checkTermsAndConditions()
                .step4Submit();

        return new Confirmation(driver);

    }


    public Confirmation Reservation_SplitBillItemized_CorpCust_PayLater(String pickUpLoction, String AWD, String corporateEmailId, String fname, String lname,
                                                               String mail, String pNo, String primaryCardNo, String secCardNo) {

        reservationWidget
                .pickUpLocation(pickUpLoction)
                .calendarSelection()
                .expandDiscountCode()
                .enterAwd(AWD)
                .enterCorporateEmailId(corporateEmailId)
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        Extras extras = vehicles.step2Submit();
        //ReviewAndBook reviewAndBook = extras.selectTierBundle().Step3Submit();
        extras.ClickLDWCoverage();
        ReviewAndBook reviewAndBook = extras.Step3Submit();

        reviewAndBook
                .getEstimatedTotalvalue()
                .firstname(fname)
                .lastname(lname)
                .email(mail)
                .phone(pNo)
                .ClickAddCreditCardLink()
                .clickToggleSplitCreditCardButton()
                .clickAddCreditCardButton1()
                .enterCardNumberSplitPage(primaryCardNo)
                .selectExpiryDateAndYear()
                .enterAddress()
                .clickSaveButton()
                .clickAddCreditCardButton1()
                .enterCardNumberSplitPage(secCardNo)
                .selectExpiryDateAndYear()
                .clickSameAsPrimaryCheckboxButton()
                .clickSaveButton()
                .enterPrimaryAndSecondaryAmount()
                .clickSaveButton();

        Assert.assertTrue(reviewAndBook.isPrimaryCardtextDisplayed(), "Primary card text not Displayed");
        Assert.assertTrue(reviewAndBook.isSecondaryCardtextDisplayed(), "Secondary card text not Displayed");
        reviewAndBook
                .checkTermsAndConditions()
                .step4Submit();

        return new Confirmation(driver);
    }

    public Confirmation Reservation_CorpCust_PayLater(String pickUpLoction, String BCD, String corporateEmailId, String fname, String lname,
                                                                        String mail, String pNo) {

        reservationWidget
                .pickUpLocation(pickUpLoction)
                .calendarSelection()
                .expandDiscountCode()
                .enterAwd(BCD)
                .enterCorporateEmailId(corporateEmailId)
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        Extras extras = vehicles.step2Submit();
        //ReviewAndBook reviewAndBook = extras.selectTierBundle().Step3Submit();
        if(!(Configuration.BRAND.equalsIgnoreCase("Budget") && Configuration.DOMAIN.equalsIgnoreCase("NZ"))) {
            extras.isUpliftTextDisplayed();
        }
        extras.ClickLDWCoverage();
        ReviewAndBook reviewAndBook = extras.Step3Submit();

        reviewAndBook

                .firstname(fname)
                .lastname(lname)
                .email(mail)
                .phone(pNo);

        reviewAndBook
                .checkTermsAndConditions()
                .step4Submit();

        return new Confirmation(driver);
    }

    public Confirmation Reservation_G_typeCoupon_LocMandate_FlightInfo_SMSCheckbox_IATA_PayLater(String pickUpLocation, String couponNo, String fname,
                                                                                                 String lname, String email, String phoneNo,String flightName, String flightNumber, String IATANumber, String couponMsg) {

        reservationWidget
                .pickUpLocation(pickUpLocation)
                .calendarSelection()
                .expandDiscountCode()
                .enterCouponCode(couponNo)
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
       // vehicles.DiscountDropDownClick();
               // .isCouponvalueDisplayed(couponNo);
        Extras extras = vehicles.step2Submit();
        ReviewAndBook reviewAndBook = extras.Step3Submit();

        reviewAndBook
                .clickContinueReservationButton()
                .firstname(fname)
                .lastname(lname)
                .email(email)
                .phone(phoneNo)
                .smsOptInCheckbox()
                .flightInfo(flightName)
                .enterflightNumber(flightNumber)
                .iataNumber(IATANumber)

                .checkTermsAndConditions()
                .step4Submit();


        return new Confirmation(driver);
    }

    public Confirmation Reservation_Profile_G_typeCoupon_SMSCheckbox_IATA_PayLater(String pickUpLocation, String couponNo,String flightName, String flightNumber, String IATANumber, String couponMsg) {

        reservationWidget
                .pickUpLocation(pickUpLocation)
                .calendarSelection()
                .expandDiscountCode()
                .enterCouponCode(couponNo)
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        // vehicles.DiscountDropDownClick();
        // .isCouponvalueDisplayed(couponNo);
        Extras extras = vehicles.step2Submit();
        ReviewAndBook reviewAndBook = extras.Step3Submit();

        reviewAndBook
                .clickContinueReservationButton()
                .smsOptInCheckbox()
                .flightInfo(flightName)
                .enterflightNumber(flightNumber)
                .iataNumber(IATANumber)

                .checkTermsAndConditions()
                .step4Submit();


        return new Confirmation(driver);
    }


    public Confirmation Reservation_UTypeCouponProcessing_tierbundle_PayLater(String pickUpLocation,String pickupTime, String dropOffTime, String couponNo, String fname,

                                                              String lname, String email, String phoneNo, String couponText) {

        reservationWidget
                .pickUpLocation(pickUpLocation)
                .calendarSelection(1)
                .pickUpTime(pickupTime)
                .dropOffTime(dropOffTime)
                .expandDiscountCode()
                .enterCouponCode(couponNo)
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
       // vehicles.verifyCouponSavingtextDisplayed(couponNo);
        Extras extras = vehicles.Step2_DiscountAppliedSubmit();
        ReviewAndBook reviewAndBook = extras
                .selectTierBundle()
                .Step3Submit();

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

    public Confirmation Reservation_Profile_UTypeCouponProcessing_tierbundle_PayLater(String pickUpLocation,String pickupTime, String dropOffTime, String couponNo, String couponText) {

        reservationWidget
                .pickUpLocation(pickUpLocation)
                .calendarSelection(1)
                .pickUpTime(pickupTime)
                .dropOffTime(dropOffTime)
                .expandDiscountCode()
                .enterCouponCode(couponNo)
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        // vehicles.verifyCouponSavingtextDisplayed(couponNo);
        Extras extras = vehicles.Step2_DiscountAppliedSubmit();
        ReviewAndBook reviewAndBook = extras
                .selectTierBundle()
                .Step3Submit();

        reviewAndBook
                .clickContinueReservationButton()
                .checkTermsAndConditions()
                .step4Submit();

        return new Confirmation(driver);
    }

    public Confirmation Reservation_Costco_DigitalWallet_Paypal_PayLater(String pickUpLocation, String awd, String membershipNo, String fname, String lname,
                                                         String email, String phoneNo, String paypalEmail, String paypalPassword) {

        reservationWidget
                .closeAdPopup()
                .pickUpLocation(pickUpLocation)
                .calendarSelection()
                .expandDiscountCode()
                .enterAwd(awd)
                .enterMembershipNo(membershipNo)
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        vehicles.savingMessageValidation("Your Costco savings are reflected below.");
        //Extras extras = vehicles.Step2_ClickDiscountAppliedSubmit();
        Extras extras = vehicles.step2Submit();

        ReviewAndBook reviewAndBook = extras.Step3Submit();
        PayPalPage paypalpage = new PayPalPage(driver);

        reviewAndBook
                .clickContinueReservationButton()
                .firstname(fname)
                .lastname(lname)
                .email(email)
                .phone(phoneNo)
                .step4_AddCreditCardCheckBox()
                .clickPaypalButton();
        //Get handles of the windows
        String mainWindowHandle = driver.getWindowHandle();
        System.out.println("windowhandle :"+mainWindowHandle);
        Set<String> allWindowHandles = driver.getWindowHandles();
        System.out.println("windowhandle :"+allWindowHandles);
        Iterator<String> iterator = allWindowHandles.iterator();

        // Here we will check if child window is present and then switch to child window
        while (iterator.hasNext()) {
            String ChildWindow = iterator.next();
               if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
                driver.switchTo().window(ChildWindow);

                paypalpage
                        .enterEmail(paypalEmail)
                        .ClickNext()
                        .enterPassword(paypalPassword)
                        .ClickLogin()
                        .ClickAgreeAndContinueButton();
            }
        }

        driver.switchTo().window(mainWindowHandle);

        assertTrue(reviewAndBook.isPaypalImageDisplayed(), "Paypal Image is not displayed");
        reviewAndBook
                .checkTermsAndConditions()
                .ReserveButton();

        return new Confirmation(driver);
    }

    public Confirmation Reservation_Profile_Costco_DigitalWallet_Paypal_PayLater(String pickUpLocation, String awd, String membershipNo, String paypalEmail, String paypalPassword) {

        reservationWidget
                .pickUpLocation(pickUpLocation)
                .calendarSelection()
                .expandDiscountCode()
                .enterAwd(awd)
                .enterMembershipNo(membershipNo)
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        vehicles.savingMessageValidation("Your Costco savings are reflected below.");
        //Extras extras = vehicles.Step2_ClickDiscountAppliedSubmit();
        Extras extras = vehicles.step2Submit();

        ReviewAndBook reviewAndBook = extras.Step3Submit();
        PayPalPage paypalpage = new PayPalPage(driver);

        reviewAndBook
                .clickContinueReservationButton()
                .step4_AddCreditCardCheckBox()
                .clickPaypalButton();
        //Get handles of the windows
        String mainWindowHandle = driver.getWindowHandle();
        System.out.println("windowhandle :"+mainWindowHandle);
        Set<String> allWindowHandles = driver.getWindowHandles();
        System.out.println("windowhandle :"+allWindowHandles);
        Iterator<String> iterator = allWindowHandles.iterator();

        // Here we will check if child window is present and then switch to child window
        while (iterator.hasNext()) {
            String ChildWindow = iterator.next();
            if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
                driver.switchTo().window(ChildWindow);

                paypalpage
                        .enterEmail(paypalEmail)
                        .ClickNext()
                        .enterPassword(paypalPassword)
                        .ClickLogin()
                        .ClickAgreeAndContinueButton();
            }
        }

        driver.switchTo().window(mainWindowHandle);

        assertTrue(reviewAndBook.isPaypalImageDisplayed(), "Paypal Image is not displayed");
        reviewAndBook
                .checkTermsAndConditions()
                .ReserveButton();

        return new Confirmation(driver);
    }


    public Confirmation Reservation_FlightInfo_DigitalWallet_Paypal_PayNow(String pickUpLocation, String fname, String lname,
                                                                         String email, String phoneNo, String paypalEmail, String paypalPassword, String flightName, String flightNumber) {

        reservationWidget
                .pickUpLocation(pickUpLocation)
                .calendarSelection(3)
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        assertTrue(vehicles.validatePickupAndReturnLocValue(pickUpLocation,pickUpLocation), "Pickup and Drop Loc is not displayed");
        assertTrue(vehicles.isPickUpDateTimeDisplayed(PICK_UP_TIME), "Pickup Time is not Displayed");
        assertTrue(vehicles.isDropDateTimeDisplayed(DROP_UP_TIME),"Drop Time is not Displayed");
        vehicles.clickViewCloseVehicleInformation();
        assertTrue(vehicles.isPayLaterButtonEnabled(),"PayLater button is not displayed");
        Extras extras = vehicles.step2Submit2();
        assertTrue(extras.validatePickupAndReturnLocValue(pickUpLocation,pickUpLocation), "Pickup and Drop Loc is not displayed");
        assertTrue(extras.isPickUpDateTimeDisplayed(PICK_UP_TIME), "Pickup Time is not Displayed");
        assertTrue(extras.isDropDateTimeDisplayed(DROP_UP_TIME),"Drop Time is not Displayed");
        assertTrue(extras.isRateTermAndBaseRateAndNumberOfSeatsDisplayed(), "RateTerm/Base rate/NumberOfSeats not Displayed");
        extras.isExtrasTabDisplayed();
        ReviewAndBook reviewAndBook = extras.Step3Submit();
        assertTrue(reviewAndBook.validatePickupAndReturnLocValue(pickUpLocation,pickUpLocation), "Pickup and Drop Loc is not displayed");
        assertTrue(reviewAndBook.isPickUpDateTimeDisplayed(PICK_UP_TIME), "Pickup Time is not Displayed");
        assertTrue(reviewAndBook.isDropDateTimeDisplayed(DROP_UP_TIME),"Drop Time is not Displayed");
        assertTrue(reviewAndBook.isRateTermAndBaseRateAndNumberOfSeatsDisplayed(), "RateTerm/Base rate/NumberOfSeats not Displayed");

        PayPalPage paypalpage = new PayPalPage(driver);

        reviewAndBook
                .clickContinueReservationButton()
                .firstname(fname)
                .lastname(lname)
                .email(email)
                .phone(phoneNo);
        if(!(Configuration.BRAND.equalsIgnoreCase("Budget") && Configuration.DOMAIN.equalsIgnoreCase("NZ"))) {
            reviewAndBook.clickPaypalButton();
            //Get handles of the windows
            String mainWindowHandle = driver.getWindowHandle();
            System.out.println("Parentwindowhandle :" + mainWindowHandle);
            Set<String> allWindowHandles = driver.getWindowHandles();
            System.out.println("Allwindowhandle :" + allWindowHandles);
            Iterator<String> iterator = allWindowHandles.iterator();

            // Here we will check if child window is present and then switch to child window
            while (iterator.hasNext()) {
                String ChildWindow = iterator.next();
                if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
                    driver.switchTo().window(ChildWindow);

                    paypalpage
                            .enterEmail(paypalEmail)
                            .ClickNext()
                            .enterPassword(paypalPassword)
                            .ClickLogin()
                            .ClickAgreeAndContinueButton();
                }
            }

            driver.switchTo().window(mainWindowHandle);

            assertTrue(reviewAndBook.isPaypalImageDisplayed(), "Paypal Image is not displayed");
        }
        reviewAndBook.isFlightInfoDisplayed();
        reviewAndBook
                .checkTermsAndConditions()
                .flightInfo(flightName)
                .enterflightNumber(flightNumber)
                .ReserveButton();

        return new Confirmation(driver);
    }

    public Confirmation Reservation_InboundAndMultiCurrency_IATA_PayNow(String pickUpLocation,String residencyLocation,String awd, String corporateEmailId, String firstName, String lastName,
                                                                     String email, String phoneNumber, String IATA,String ccNo, String cvv, String residentCurrencySymbol, String USCurrencyValue) {
        reservationWidget
                .closeAdPopup()
                .pickUpLocation(pickUpLocation)
                .calendarSelection(3)
                .selectCountry(residencyLocation)
                .expandDiscountCode()
                .enterAwd(awd)
                .enterCorporateEmailId(corporateEmailId)
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        assertTrue(vehicles.isCurrencyValueDisplayed(), "Currency value is not displayed");
        assertTrue(vehicles.isTipForInternationaltravellerTextDisplayed(), "Tip for international Traveller is not displayed");
        assertTrue(vehicles.verifyCurrencySymbolDisplayed(residentCurrencySymbol), "Currency  value is not same as residence country");
        assertTrue(vehicles.validatePickupAndReturnLocValue(pickUpLocation,pickUpLocation), "Pickup and Drop Loc is not displayed");
        assertTrue(vehicles.isPickUpDateTimeDisplayed(PICK_UP_TIME), "Pickup Time is not Displayed");
        assertTrue(vehicles.isDropDateTimeDisplayed(DROP_UP_TIME),"Drop Time is not Displayed");
        vehicles.clickViewCloseVehicleInformation();
        Extras   extras = vehicles.step2SubmitPayNow();

        assertTrue(extras.verifyCurrencySymbolDisplayed(residentCurrencySymbol), "Currency  value is not same as residence country");
        assertTrue(extras.validatePickupAndReturnLocValue(pickUpLocation,pickUpLocation), "Pickup and Drop Loc is not displayed");
        assertTrue(extras.isPickUpDateTimeDisplayed(PICK_UP_TIME), "Pickup Time is not Displayed");
        assertTrue(extras.isDropDateTimeDisplayed(DROP_UP_TIME),"Drop Time is not Displayed");
        assertTrue(extras.isRateTermAndBaseRateAndNumberOfSeatsDisplayed(), "RateTerm/Base rate/NumberOfSeats not Displayed");
        ReviewAndBook reviewAndBook = extras.Step3Submit();

        assertTrue(reviewAndBook.validatePickupAndReturnLocValue(pickUpLocation,pickUpLocation), "Pickup and Drop Loc is not displayed");
        assertTrue(reviewAndBook.isPickUpDateTimeDisplayed(PICK_UP_TIME), "Pickup Time is not Displayed");
        assertTrue(reviewAndBook.isDropDateTimeDisplayed(DROP_UP_TIME),"Drop Time is not Displayed");
        assertTrue(reviewAndBook.isRateTermAndBaseRateAndNumberOfSeatsDisplayed(), "RateTerm/Base rate/NumberOfSeats not Displayed");
        reviewAndBook.isFlightInfoDisplayed();
        if(!(Configuration.BRAND.equalsIgnoreCase("Budget") && Configuration.DOMAIN.equalsIgnoreCase("NZ"))) {
            reviewAndBook.isPayPalAndAmazonPayDisplayed();
        }


        reviewAndBook
                .firstname(firstName)
                .lastname(lastName)
                .email(email)
                .phone(phoneNumber)
                .step4_CreditCardCheckBox()
                .enterCardNumber(ccNo)
                // .selectExpiryDateAndYear()
                .EnterExpiryDateAndYear()
                .enterSecurityCode(cvv)
                .enterAddressInboundSpecific(residencyLocation);
                assertTrue(reviewAndBook.isNativeCurrencyMsgtextDisplayed(), "Native currency pay message not displayed");
        reviewAndBook
                .iataNumber(IATA)
                .checkTermsAndConditions()
                .step4Submit();

        return new Confirmation(driver);

    }

    public Confirmation Reservation_Profile_InboundAndMultiCurrency_IATA_PayNow(String pickUpLocation, String PickupTime, String DropTime,String awd, String corporateEmailId, String IATA,String cvv, String residentCurrencySymbol, String USCurrencyValue) {
        reservationWidget
                .pickUpLocation(pickUpLocation)
                .calendarSelection(3);
        if(Configuration.BRAND.equalsIgnoreCase("Avis")) {
            reservationWidget
                    .expandDiscountCode()
                    .enterAwd(awd)
                    .enterCorporateEmailId(corporateEmailId);
        }
        reservationWidget.selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        assertTrue(vehicles.isCurrencyValueDisplayed(), "Currency value is not displayed");
        assertTrue(vehicles.isTipForInternationaltravellerTextDisplayed(), "Tip for international Traveller is not displayed");
        assertTrue(vehicles.verifyCurrencySymbolDisplayed(residentCurrencySymbol), "Currency  value is not same as residence country");
        assertTrue(vehicles.validatePickupAndReturnLocValue(pickUpLocation,pickUpLocation), "Pickup and Drop Loc is not displayed");
        assertTrue(vehicles.isPickUpDateTimeDisplayed("12:00 PM"), "Pickup Time is not Displayed");
        assertTrue(vehicles.isDropDateTimeDisplayed("12:00 PM"),"Drop Time is not Displayed");
        vehicles.clickViewCloseVehicleInformation();
        Extras extras = vehicles.step2Submit();
        assertTrue(extras.verifyCurrencySymbolDisplayed(residentCurrencySymbol), "Currency  value is not same as residence country");
        assertTrue(extras.validatePickupAndReturnLocValue(pickUpLocation,pickUpLocation), "Pickup and Drop Loc is not displayed");
        assertTrue(extras.isPickUpDateTimeDisplayed("12:00 PM"), "Pickup Time is not Displayed");
        assertTrue(extras.isDropDateTimeDisplayed("12:00 PM"),"Drop Time is not Displayed");
        assertTrue(extras.isRateTermAndBaseRateAndNumberOfSeatsDisplayed(), "RateTerm/Base rate/NumberOfSeats not Displayed");
        ReviewAndBook reviewAndBook = extras.Step3Submit();

        assertTrue(reviewAndBook.validatePickupAndReturnLocValue(pickUpLocation,pickUpLocation), "Pickup and Drop Loc is not displayed");
        assertTrue(reviewAndBook.isPickUpDateTimeDisplayed("12:00 PM"), "Pickup Time is not Displayed");
        assertTrue(reviewAndBook.isDropDateTimeDisplayed("12:00 PM"),"Drop Time is not Displayed");
        assertTrue(reviewAndBook.isRateTermAndBaseRateAndNumberOfSeatsDisplayed(), "RateTerm/Base rate/NumberOfSeats not Displayed");
        reviewAndBook.isFlightInfoDisplayed();
        reviewAndBook.isPayPalAndAmazonPayDisplayed();

        reviewAndBook
                .step4_CreditCardCheckBox()
                .enterSecurityCode(cvv);
        assertTrue(reviewAndBook.isNativeCurrencyMsgtextDisplayed(), "Native currency pay message not displayed");
        reviewAndBook
                .iataNumber(IATA)
                .checkTermsAndConditions()
                .step4Submit();

        return new Confirmation(driver);

    }


    public Confirmation Reservation_Outbound_CorpCust_InsuranceCover_PayNow(String pickUpLocation, String pickupTime,String awd, String corporateEmailId, String firstName, String lastName,
                                                                            String email, String phoneNumber,String ccNo, String cvv, String PickUpLocCurrencySymbol,String PickupLocCurrencyCode, String USCurrencyCode) {
        reservationWidget
                .pickUpLocation(pickUpLocation)
                .calendarSelection(3)
                .pickUpTime(pickupTime)
                .expandDiscountCode()
                .enterAwd(awd)
                .enterCorporateEmailId(corporateEmailId)
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        assertTrue(vehicles.isCurrencyValueDisplayed(), "Currency value is not displayed");
        //vehicles.DiscountDropDownClick(awd);
        assertTrue(vehicles.validatePickupAndReturnLocValue(pickUpLocation,pickUpLocation), "Pickup and Drop Loc is not displayed");
        assertTrue(vehicles.isPickUpDateTimeDisplayed(pickupTime), "Pickup Time is not Displayed");
        assertTrue(vehicles.isDropDateTimeDisplayed("12:00 PM"),"Drop Time is not Displayed");
        vehicles.clickViewCloseVehicleInformation();
        assertTrue(vehicles.verifyCurrencySymbolDisplayed(PickUpLocCurrencySymbol), "Currency  value is not same as residence country");
        Extras extras = vehicles.step2Submit();
        assertTrue(extras.validatePickupAndReturnLocValue(pickUpLocation,pickUpLocation), "Pickup and Drop Loc is not displayed");
        assertTrue(extras.isPickUpDateTimeDisplayed(pickupTime), "Pickup Time is not Displayed");
        assertTrue(extras.isDropDateTimeDisplayed("12:00 PM"),"Drop Time is not Displayed");
        assertTrue(extras.isRateTermAndBaseRateAndNumberOfSeatsDisplayed(), "RateTerm/Base rate/NumberOfSeats not Displayed");
        assertTrue(extras.isAWDIncludedInsuranceCoveragetextDisplayed(), "AWD Included Insurance Coverage text is not displayed");
        assertTrue(extras.verifyCurrencySymbolDisplayed(PickUpLocCurrencySymbol), "Currency  value is not same as residence country");
        ReviewAndBook reviewAndBook = extras.Step3Submit();

        assertTrue(reviewAndBook.validatePickupAndReturnLocValue(pickUpLocation,pickUpLocation), "Pickup and Drop Loc is not displayed");
        assertTrue(reviewAndBook.isPickUpDateTimeDisplayed(pickupTime), "Pickup Time is not Displayed");
        assertTrue(reviewAndBook.isDropDateTimeDisplayed("12:00 PM"),"Drop Time is not Displayed");
        assertTrue(reviewAndBook.isRateTermAndBaseRateAndNumberOfSeatsDisplayed(), "RateTerm/Base rate/NumberOfSeats not Displayed");
        reviewAndBook.isFlightInfoDisplayed();
        reviewAndBook.isPayPalAndAmazonPayDisplayed();

        reviewAndBook
                .firstname(firstName)
                .lastname(lastName)
                .email(email)
                .phone(phoneNumber)
                .step4_AddCreditCardCheckBox()
                .enterCardNumber(ccNo)
                // .selectExpiryDateAndYear()
                .EnterExpiryDateAndYear()
                .enterSecurityCode(cvv)
                .enterAddressInboundSpecific("U S A");
        reviewAndBook
                .checkTermsAndConditions()
                .step4Submit();

        return new Confirmation(driver);

    }

    public Confirmation Reservation_Profile_Outbound_CorpCust_InsuranceCover_PayNow(String pickUpLocation, String pickupTime,String dropTime,String awd, String corporateEmailId) {
        reservationWidget
                .pickUpLocation(pickUpLocation)
                .calendarSelection(3)
                .pickUpTime(pickupTime)
                .expandDiscountCode()
                .enterAwd(awd)
                .enterCorporateEmailId(corporateEmailId)
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        //assertTrue(vehicles.isCurrencyValueDisplayed());
        //vehicles.DiscountDropDownClick(awd);
        assertTrue(vehicles.isPayLaterButtonEnabled(),"PayLater button is not displayed");
//        assertTrue(vehicles.validatePickupAndReturnLocValue(pickUpLocation,pickUpLocation));
//        assertTrue(vehicles.isPickUpDateTimeDisplayed(pickupTime));
//        assertTrue(vehicles.isDropDateTimeDisplayed(dropTime));
//        vehicles.clickViewCloseVehicleInformation();
       // assertTrue(vehicles.verifyCurrencySymbolDisplayed(PickUpLocCurrencySymbol));
        Extras extras = vehicles.step2Submit();
       // assertTrue(extras.validatePickupAndReturnLocValue(pickUpLocation,pickUpLocation));
        //assertTrue(extras.isPickUpDateTimeDisplayed(pickupTime));
        //assertTrue(extras.isDropDateTimeDisplayed(dropTime));
        assertTrue(extras.isRateTermAndBaseRateAndNumberOfSeatsDisplayed());
        assertTrue(extras.isAWDIncludedInsuranceCoveragetextDisplayed());
       // assertTrue(extras.verifyCurrencySymbolDisplayed(PickUpLocCurrencySymbol));
        ReviewAndBook reviewAndBook = extras.Step3Submit();
        assertTrue(reviewAndBook.validatePickupAndReturnLocValue(pickUpLocation,pickUpLocation));
        assertTrue(reviewAndBook.isPickUpDateTimeDisplayed(pickupTime));
        assertTrue(reviewAndBook.isDropDateTimeDisplayed(dropTime));
        assertTrue(reviewAndBook.isRateTermAndBaseRateAndNumberOfSeatsDisplayed());
        reviewAndBook.isFlightInfoDisplayed();
        reviewAndBook.isPayPalAndAmazonPayDisplayed();

        reviewAndBook
                .step4_CreditCardCheckBox()
               // .enterSecurityCodeProfileUser(cvv)
                .checkTermsAndConditions()
                .step4Submit();

        return new Confirmation(driver);

    }
    public Confirmation Avis_Reservation_Profile_Outbound_CorpCust_InsuranceCover_PayNow(String pickUpLocation, String pickupTime,String dropTime,String awd, String corporateEmailId,String cvv,String PickUpLocCurrencySymbol,String USCurrencyCode,String PickupLocCurrencyCode) {
        reservationWidget
                .pickUpLocation(pickUpLocation)
                .calendarSelection(3)
                .pickUpTime(pickupTime)
                .expandDiscountCode()
                .enterAwd(awd)
                .enterCorporateEmailId(corporateEmailId)
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        assertTrue(vehicles.isCurrencyValueDisplayed());
        vehicles.DiscountDropDownClick(awd);
        assertTrue(vehicles.validatePickupAndReturnLocValue(pickUpLocation,pickUpLocation));
        assertTrue(vehicles.isPickUpDateTimeDisplayed(pickupTime));
        assertTrue(vehicles.isDropDateTimeDisplayed(dropTime));
        vehicles.clickViewCloseVehicleInformation();
         assertTrue(vehicles.verifyCurrencySymbolDisplayed(PickUpLocCurrencySymbol));
        Extras extras = vehicles.step2Submit();
        assertTrue(extras.validatePickupAndReturnLocValue(pickUpLocation,pickUpLocation));
        assertTrue(extras.isPickUpDateTimeDisplayed(pickupTime));
        assertTrue(extras.isDropDateTimeDisplayed(dropTime));
        assertTrue(extras.isRateTermAndBaseRateAndNumberOfSeatsDisplayed());
        assertTrue(extras.isAWDIncludedInsuranceCoveragetextDisplayed());
        assertTrue(extras.verifyCurrencySymbolDisplayed(PickUpLocCurrencySymbol));
        ReviewAndBook reviewAndBook = extras.Step3Submit();
        assertTrue(reviewAndBook.validatePickupAndReturnLocValue(pickUpLocation,pickUpLocation));
        assertTrue(reviewAndBook.isPickUpDateTimeDisplayed(pickupTime));
        assertTrue(reviewAndBook.isDropDateTimeDisplayed(dropTime));
        assertTrue(reviewAndBook.isRateTermAndBaseRateAndNumberOfSeatsDisplayed());
        reviewAndBook.isFlightInfoDisplayed();
        reviewAndBook.isPayPalAndAmazonPayDisplayed();

        reviewAndBook
                .step4_CreditCardCheckBox()
                 .enterSecurityCodeProfileUser(cvv)
                .checkTermsAndConditions()
                .step4Submit();

        return new Confirmation(driver);

    }

    public Confirmation Reservation_DigitalWallet_AmazonPay_PayNow(String pickUpLocation, String fname, String lname,
                                                                   String email, String phoneNo, String AmazonEmail, String AmazonPassword) {

        reservationWidget
                .pickUpLocation(pickUpLocation)
                .calendarSelection(3)
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        vehicles.validatePickupAndReturnLocValue(pickUpLocation,pickUpLocation);
        vehicles.isPayLaterButtonEnabled();
        Extras extras = vehicles.step2SubmitPayNow();
        extras.validatePickupAndReturnLocValue(pickUpLocation,pickUpLocation);
        extras.isExtrasTabDisplayed();
        ReviewAndBook reviewAndBook = extras.Step3Submit();
        AmazonPayPage amazonpage = new AmazonPayPage(driver);

        reviewAndBook
                .clickContinueReservationButton()
                .firstname(fname)
                .lastname(lname)
                .email(email)
                .phone(phoneNo)
                .clickAmazonPayButton();

        //Get handles of the windows
        String mainWindowHandle = driver.getWindowHandle();
        System.out.println("Parentwindowhandle :"+mainWindowHandle);
        Set<String> allWindowHandles = driver.getWindowHandles();
        System.out.println("Allwindowhandle :"+allWindowHandles);
        Iterator<String> iterator = allWindowHandles.iterator();

        // Here we will check if child window is present and then switch to child window
        while (iterator.hasNext()) {
            String ChildWindow = iterator.next();
            if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
                driver.switchTo().window(ChildWindow);
                System.out.println("Allwindowhandle :"+allWindowHandles);

                amazonpage
                        .enterEmail(AmazonEmail)
                        .enterPassword(AmazonPassword)
                        .ClickLogin();
            }
        }

        driver.switchTo().window(mainWindowHandle);

        reviewAndBook
                .checkTermsAndConditions()
                .ReserveButton();

        return new Confirmation(driver);
    }

    public Confirmation Reservation_ModifyFlow_PayNow(String pickUpLocation, String firstName, String lastName,
                                                                            String email, String phoneNumber,String ccNo, String cvv, String Country, String modifiedPickupLocation) {
        reservationWidget
                .pickUpLocation(pickUpLocation)
                .calendarSelection(3)
                .expandDiscountCode()
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        Extras extras = vehicles.step2Submit();
        ReviewAndBook reviewAndBook = extras.Step3Submit();

        reviewAndBook
                .firstname(firstName)
                .lastname(lastName)
                .email(email)
                .phone(phoneNumber)
                .step4_AddCreditCardCheckBox()
                .enterCardNumber(ccNo)
                .EnterExpiryDateAndYear()
                .enterSecurityCode(cvv)
                .enterAddressInboundSpecific("U S A");
        reviewAndBook
                .checkTermsAndConditions()
                .step4Submit();

        Confirmation confirmation = new Confirmation(driver);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        String cNum= confirmation.GetConfirmationNumber();
        confirmation.ClickAvisLogo();

        Homepage homepage = new Homepage(driver);

        homepage.goToViewModifyCancelPage();
        ReservationViewModifyCancel reservationViewModifyCancel = new ReservationViewModifyCancel(driver);
        reservationViewModifyCancel.selectCountry(Country);
        reservationViewModifyCancel.enterLastname(lastName);
        reservationViewModifyCancel.enterConfirmationNumber(cNum);
        reservationViewModifyCancel.ClickFindReservationButton();

        ManageReservationPage managereservationpage = new ManageReservationPage(driver);
        managereservationpage.isCarReservedTextMessageDisplayed();
        managereservationpage.isConfirmationNumberSame(cNum);
        managereservationpage.ClickRateAndBenefitInfoModifyButton();

        ModifyReservationTimeAndPlacePage TimeAndPlacePage =new ModifyReservationTimeAndPlacePage(driver);
        TimeAndPlacePage.isModifyReservationTextMsgDisplayed();
        TimeAndPlacePage
                .pickUpLocation(modifiedPickupLocation)
                .calendarSelection(1)
                .selectMyCar();
       // vehicles.isVehicleReselectionTextMessageDisplayed();
        extras = vehicles.step2Submit();
        reviewAndBook = extras.Step3Submit();

        reviewAndBook.reviewModifications();
        ReviewModificationPage reviewmodificationpage = new ReviewModificationPage(driver);

        reviewmodificationpage.isHeaderTextDisplayed();
        reviewmodificationpage.isChangesInRedTextDisplayed();
        reviewmodificationpage.isOriginalTextDisplayed();
        reviewmodificationpage.isModifiedTextDisplayed();
        reviewmodificationpage.isCancelModificationButtonEnabled();
        reviewmodificationpage.clickKeepModificationButton();

        confirmation.isConfirmationNumberSame(cNum);
        return new Confirmation(driver);



    }

    public Confirmation Reservation_Profile_ModifyFlow_PayNow(String pickUpLocation, String modifiedPickupLocation, String Country) {
        reservationWidget
                .pickUpLocation(pickUpLocation)
                .calendarSelection(3)
                .expandDiscountCode()
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        Extras extras = vehicles.step2Submit();
        ReviewAndBook reviewAndBook = extras.Step3Submit();

        reviewAndBook
                .checkTermsAndConditions()
                .step4Submit();

        Confirmation confirmation = new Confirmation(driver);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        String cNum= confirmation.GetConfirmationNumber();
        confirmation.ClickAvisLogo();

        Homepage homepage = new Homepage(driver);

        homepage.goToViewModifyCancelPage();
        ReservationViewModifyCancel reservationViewModifyCancel = new ReservationViewModifyCancel(driver);
        reservationViewModifyCancel.selectCountry(Country);
        reservationViewModifyCancel.enterConfirmationNumber(cNum);
        reservationViewModifyCancel.ClickFindReservationButton();

        ManageReservationPage managereservationpage = new ManageReservationPage(driver);
        managereservationpage.isCarReservedTextMessageDisplayed();
        managereservationpage.isConfirmationNumberSame(cNum);
        managereservationpage.ClickRateAndBenefitInfoModifyButton();

        ModifyReservationTimeAndPlacePage TimeAndPlacePage =new ModifyReservationTimeAndPlacePage(driver);
        TimeAndPlacePage.isModifyReservationTextMsgDisplayed();
        TimeAndPlacePage
                .pickUpLocation(modifiedPickupLocation)
                .calendarSelection(2)
                .selectMyCar();
        // vehicles.isVehicleReselectionTextMessageDisplayed();
        extras = vehicles.step2Submit();
        reviewAndBook = extras.Step3Submit();

        reviewAndBook.reviewModifications();
        ReviewModificationPage reviewmodificationpage = new ReviewModificationPage(driver);

        reviewmodificationpage.isHeaderTextDisplayed();
        reviewmodificationpage.isChangesInRedTextDisplayed();
        reviewmodificationpage.isOriginalTextDisplayed();
        reviewmodificationpage.isModifiedTextDisplayed();
        reviewmodificationpage.isCancelModificationButtonEnabled();
        reviewmodificationpage.clickKeepModificationButton();

        confirmation.isConfirmationNumberSame(cNum);
        return new Confirmation(driver);



    }

    public Confirmation Reservation_Profile_FlightInfo_DigitalWallet_Paypal_PayNow(String pickUpLocation,String PickupTime,String DropTime, String paypalEmail, String paypalPassword, String flightName, String flightNumber) {

        reservationWidget
                .pickUpLocation(pickUpLocation)
                .calendarSelection(3)
                .pickUpTime(PickupTime)
                .dropOffTime(DropTime)
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        assertTrue(vehicles.validatePickupAndReturnLocValue(pickUpLocation,pickUpLocation));
        assertTrue(vehicles.isPickUpDateTimeDisplayed(PickupTime));
        assertTrue(vehicles.isDropDateTimeDisplayed(DropTime));
        vehicles.clickViewCloseVehicleInformation();
        assertTrue(vehicles.isPayLaterButtonEnabled());
        Extras extras = vehicles.step2SubmitPayNow();
        assertTrue(extras.validatePickupAndReturnLocValue(pickUpLocation,pickUpLocation));
        assertTrue(extras.isPickUpDateTimeDisplayed(PickupTime));
        assertTrue(extras.isDropDateTimeDisplayed(DropTime));
        assertTrue(extras.isRateTermAndBaseRateAndNumberOfSeatsDisplayed());
        extras.isExtrasTabDisplayed();
        ReviewAndBook reviewAndBook = extras.Step3Submit();
        assertTrue(reviewAndBook.validatePickupAndReturnLocValue(pickUpLocation,pickUpLocation));
        assertTrue(reviewAndBook.isPickUpDateTimeDisplayed(PickupTime));
        assertTrue(reviewAndBook.isDropDateTimeDisplayed(DropTime));
        assertTrue(reviewAndBook.isRateTermAndBaseRateAndNumberOfSeatsDisplayed());

        PayPalPage paypalpage = new PayPalPage(driver);

        reviewAndBook
                .clickContinueReservationButton()
                .clickPaypalButton();
        //Get handles of the windows
        String mainWindowHandle = driver.getWindowHandle();
        System.out.println("Parentwindowhandle :"+mainWindowHandle);
        Set<String> allWindowHandles = driver.getWindowHandles();
        System.out.println("Allwindowhandle :"+allWindowHandles);
        Iterator<String> iterator = allWindowHandles.iterator();

        // Here we will check if child window is present and then switch to child window
        while (iterator.hasNext()) {
            String ChildWindow = iterator.next();
            if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
                driver.switchTo().window(ChildWindow);

                paypalpage
                        .enterEmail(paypalEmail)
                        .ClickNext()
                        .enterPassword(paypalPassword)
                        .ClickLogin()
                        .ClickAgreeAndContinueButton();
            }
        }

        driver.switchTo().window(mainWindowHandle);

        assertTrue(reviewAndBook.isPaypalImageDisplayed(), "Paypal Image is not displayed");
        reviewAndBook.isFlightInfoDisplayed();
        reviewAndBook
                .checkTermsAndConditions()
                .flightInfo(flightName)
                .enterflightNumber(flightNumber)
                .ReserveButton();

        return new Confirmation(driver);
    }

    public Confirmation Reservation_InboundAndMultiCurrency_IATA_PayNow(String pickUpLocation,String residencyLocation,String PickupTime, String DropTime, String awd, String corporateEmailId,
                                                                        String IATA, String residentCurrencySymbol, String USCurrencyValue) {
        reservationWidget
                .pickUpLocation(pickUpLocation)
                .calendarSelection(3)
                .selectCountry(residencyLocation)
                .pickUpTime(PickupTime)
                .dropOffTime(DropTime)
                .expandDiscountCode()
                .enterAwd(awd)
                .enterCorporateEmailId(corporateEmailId)
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        assertTrue(vehicles.isCurrencyValueDisplayed());
        assertTrue(vehicles.isTipForInternationaltravellerTextDisplayed());
        assertTrue(vehicles.verifyCurrencySymbolDisplayed(residentCurrencySymbol));
        assertTrue(vehicles.validatePickupAndReturnLocValue(pickUpLocation,pickUpLocation));
        assertTrue(vehicles.isPickUpDateTimeDisplayed(PickupTime));
        assertTrue(vehicles.isDropDateTimeDisplayed(DropTime));
        vehicles.clickViewCloseVehicleInformation();
        Extras extras = vehicles.step2Submit();
        assertTrue(extras.verifyCurrencySymbolDisplayed(residentCurrencySymbol));
        assertTrue(extras.validatePickupAndReturnLocValue(pickUpLocation,pickUpLocation));
        assertTrue(extras.isPickUpDateTimeDisplayed(PickupTime));
        assertTrue(extras.isDropDateTimeDisplayed(DropTime));
        assertTrue(extras.isRateTermAndBaseRateAndNumberOfSeatsDisplayed());
        ReviewAndBook reviewAndBook = extras.Step3Submit();

        assertTrue(reviewAndBook.validatePickupAndReturnLocValue(pickUpLocation,pickUpLocation));
        assertTrue(reviewAndBook.isPickUpDateTimeDisplayed(PickupTime));
        assertTrue(reviewAndBook.isDropDateTimeDisplayed(DropTime));
        assertTrue(reviewAndBook.isRateTermAndBaseRateAndNumberOfSeatsDisplayed());
        reviewAndBook.isFlightInfoDisplayed();
        reviewAndBook.isPayPalAndAmazonPayDisplayed();

        assertTrue(reviewAndBook.isNativeCurrencyMsgtextDisplayed());
        reviewAndBook
                .iataNumber(IATA)
                .checkTermsAndConditions()
                .step4Submit();

        return new Confirmation(driver);

    }

    public Confirmation Reservation_Amazon_Customer_Paynow_US(String pickUpLocation, String firstName, String lastName,

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
                .EnterExpiryDateAndYear()
                .enterSecurityCode(cvv)
                .enterAddress()
                .checkTermsAndConditions()
                .setSelectedCountryText()
                .step4Submit();

        Assert.assertTrue(reviewAndBook.verifySelectedCountryText("U S A") || reviewAndBook.verifySelectedCountryText("U.S.A."), "Country Text is incorrect");
        return new Confirmation(driver);
    }

    public Confirmation ReservationProfileUTypeCouponTireBundlePayLaterUS(String pickUpLocation, String coupon, String firstName, String lastName, String email,
                                                                          String phoneNo, String cCNumber) {

        reservationWidget
                .pickUpLocation(pickUpLocation)
                .calendarSelection()
                .expandDiscountCode()
                .enterCouponCode(coupon)
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        Extras extras = vehicles.payLater();
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

    public Confirmation Reservation_Profile_Outbound_USAA_Validate_CorpBooking_URS(String pickUpLocation, String BCD, String membershipNo){
        reservationWidget
                .pickUpLocation(pickUpLocation)
                .calendarSelection()
                .expandDiscountCode()
                .enterAwd(BCD)
                .enterMembershipNo(membershipNo)
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        Extras extras = vehicles.payLater();
        ReviewAndBook reviewAndBook = extras.Step3Submit();

        reviewAndBook
                .step4_CreditCardCheckBox()
                .checkTermsAndConditions()
                .step4Submit();


        return new Confirmation(driver);
    }

    public ReservationWidget getReservationWidget(){
        return this.reservationWidget;
    }

}
