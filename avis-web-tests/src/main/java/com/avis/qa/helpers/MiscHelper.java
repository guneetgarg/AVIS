package com.avis.qa.helpers;

import com.avis.qa.components.AvisFlyout;
import com.avis.qa.components.Footer;
import com.avis.qa.components.Header;
import com.avis.qa.components.ReservationWidget;
import com.avis.qa.core.AbstractBasePage;
import com.avis.qa.pages.*;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;

import java.sql.SQLOutput;

import static org.testng.Assert.assertTrue;

@Log4j2
public class MiscHelper extends AbstractBasePage {

    private final WebDriver driver;
    private final ReservationWidget reservationWidget;
    private final Homepage homepage;

    public MiscHelper(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.reservationWidget = new ReservationWidget(driver);
        this.homepage = new Homepage(driver);
    }

    @Override
    public void isOnPage() {
        log.info("On MiscHelper");
    }

    private Confirmation getConfirmation(String pickupLocation, String fName, String lName, String email, String phone) {
        reservationWidget
                .pickUpLocation(pickupLocation)
                .calendarSelection(1)
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        Extras extras = vehicles.step2Submit();
        ReviewAndBook reviewAndBook = extras.Step3Submit();

        reviewAndBook
                .clickContinueReservationButton()
                .firstname(fName)
                .lastname(lName)
                .email(email)
                .phone(phone)
                .checkTermsAndConditions()
                .step4Submit();

        return new Confirmation(driver);
    }

    public Confirmation Misc_OffersPage_Reservation(String pickUpLocation, String firstName, String lastName, String email,
                                                    String phoneNo) {

        Header header = new Header(driver);
        header.offersHeader().clickOnOffersCTA();

        return getConfirmation(pickUpLocation, firstName, lastName, email, phoneNo);
    }


    public Confirmation Misc_CarGuide_Res(String pickUpLocation, String firstName, String lastName, String email,
                                          String phoneNo) {
        Header header = new Header(driver);
        header.carAndservicesHeader();
        return getConfirmation(pickUpLocation, firstName, lastName, email, phoneNo);
    }

    public void Misc_BusinessPrograms_BaseRateGuarnatee(String wizardNo, String password, String pickup, String fName,
                                                        String lName, String email, String phone, String bestRateQuote, String lowerRateCar_bestRateQuote,
                                                        String pickupLocation, String dropOffLocation, String vehicle_Type, String webSite, String comments) {

        Confirmation confirmation = getConfirmation(pickupLocation, fName, lName, email, phone);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        confirmation.cancelReservation();

        Footer footer = new Footer(driver);
        BaseRate baseRate = footer.clickBRG();
        baseRate.landOnBaseRateGuaranteeForm();
        baseRate.submitForm(fName, lName, email, phone, pickupLocation);
    }

    public void Misc_SmallAndMidSizedBusiness_FlyOutSignUp(String pickUpLoc, String firstName, String lastName,
                                                           String email, String phoneNo, String companyName, String address1, String city, String province,
                                                           String zipcode, String country) {

        getConfirmation(pickUpLoc, firstName, lastName, email, phoneNo);
        AvisFlyout avisFlyout = new AvisFlyout(driver);
        avisFlyout.clickOnNextButton();
        assertTrue(avisFlyout.enterCompanyInformationAndVerifyAWDLength(companyName, address1, city, province, zipcode, country));
    }

    public void Reservation_Profile_Misc_Step1AndStep4_ErrorMsg_Validation(String pickUpLocation, String pickUpDate, String pickUpTime, String dropOffLocation, String dropOffDate, String dropOffTime, String WizardNumber, String lastName, String awdCode, String corporateEmail, String rateCode, String couponCode, String creditcardNumber) {
        reservationWidget
                .clickAcceptTermsButton()
                .pickUpTime(pickUpTime)
                .dropOffLocation(dropOffLocation)
                .calendarSelection(1)
                .dropOffTime(dropOffTime)
                .selectMyCar()
                .verifyPickUpLocationErrorMessage();

        homepage.clickAvisLogo();

        reservationWidget
                .pickUpLocation(pickUpLocation)
                .dropOffLocation(dropOffLocation)
                .calendarSelection(1)
                .clearPickUpDateValue()
                //empty pickUp date
                .selectMyCar()
                .verifyPickUpDateErrorMessage(); //validate pickUp empty date msg

        homepage.clickAvisLogo();

        reservationWidget
                .pickUpLocation(pickUpLocation)
                .dropOffLocation(dropOffLocation)
                .calendarSelection(1)
                .clearDropOffDateValue() //empty dropoff date
                .selectMyCar()
                .verifyReturnDateErrorMessage();  //validate dropoff empty date msg

        homepage.clickAvisLogo();


        reservationWidget
                .pickUpLocation(pickUpLocation)
                .dropOffLocation(dropOffLocation)
                .enterCurrentDatePickUpDropOffDate()
                .pickUpTime("11:00 AM")
                .dropOffTime("10:00 AM") //dropoff time before pickuptime for same date
                .selectMyCar()
                .verifyReturnTimeErrorMessage();


        homepage.clickAvisLogo();

        reservationWidget
                .pickUpLocation(pickUpLocation)
                .dropOffLocation(dropOffLocation)
                .enterpickUpDropOffDate("10/01/22","12/01/22") //wrong date format
                .selectMyCar()
                .verifyPickUpReturnDateInvalidErrorMessage();
        //validate date error


        homepage.clickAvisLogo();

        reservationWidget
                .pickUpLocation(pickUpLocation)
                .dropOffLocation(dropOffLocation)
                .calendarSelection(1)
                .expandDiscountCode()
                .enterAwd("abc123") //Invalid AWD code
                //validate for invalid awd code
                .selectMyCar()
                .verifyAWDCouponcodeInvalidErrorMessage();
        reservationWidget
                .enterAwd(awdCode)  //correct corporate AWD code
                //Validate for corporate email ID
                .selectMyCar()
                .verifyCorpEmailIDBlankErrorMessage();
        reservationWidget.expandDiscountCode();

        homepage.clickAvisLogo();

        reservationWidget
                .pickUpLocation(pickUpLocation)
                .dropOffLocation(dropOffLocation)
                .calendarSelection(1)
                .expandDiscountCode()
                .enterCouponCode("test")//invalid discount code
                //Validation for invalid discount code
                .selectMyCar()
                .verifyAWDCouponcodeInvalidErrorMessage();
        reservationWidget.expandDiscountCode();

        homepage.clickAvisLogo();

        reservationWidget
                .pickUpLocation(pickUpLocation)
                .dropOffLocation(dropOffLocation)
                .calendarSelection(1)
                .pickUpTime(pickUpTime)
                .dropOffTime(dropOffTime)
                .expandDiscountCode()
                .enterAwd(awdCode)  // valid discount code
                .enterCorporateEmailId("test@hh.com") //invalid corporate email id
                //Validation for invalid corporate email id
                .selectMyCar()
                .verifyCorpEmailIDInvalidErrorMessage();
        reservationWidget.expandDiscountCode();

        homepage.clickAvisLogo();

        reservationWidget
                .pickUpLocation(pickUpLocation)
                .dropOffLocation(dropOffLocation)
                .calendarSelection(1)
                .expandDiscountCode()
                .enterAwd(awdCode)
                .enterCorporateEmailId(corporateEmail)
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        vehicles.DiscountDropDownClick("test");
        vehicles.verifyAWDCouponcodeInvalidErrorMessage();
        vehicles.DiscountDropDownAWDClear();
        Extras extras = vehicles.step2Submit();
        ReviewAndBook reviewAndBook = extras.Step3Submit();


        reviewAndBook
                .enterEmail("abc")
                .enterPhnNo("abc")
                .enterSecurityCode("12")
                .step4Submit()
                .isCVVInvalidErrorMsgDisplayed();
        reviewAndBook.isEmailInvalidErrorMsgDisplayed();
        reviewAndBook.isPhonenumInvalidErrorMsgDisplayed();
        reviewAndBook.isTncErrorMsgtextDisplayed();



    }

    public void Reservation_Misc_Step1AndStep4_ErrorMsg_Validation(String pickUpLocation, String pickUpDate, String pickUpTime, String dropOffLocation, String dropOffDate, String dropOffTime, String WizardNumber, String lastName, String awdCode, String corporateEmail, String rateCode, String couponCode, String creditcardNumber) {
        reservationWidget
                .clickAcceptTermsButton()
                .pickUpTime(pickUpTime)
                .dropOffLocation(dropOffLocation)
                .calendarSelection(1)
                .dropOffTime(dropOffTime)
                .selectMyCar()
                .verifyPickUpLocationErrorMessage();

        homepage.clickAvisLogo();

        reservationWidget
                .pickUpLocation(pickUpLocation)
                .dropOffLocation(dropOffLocation)
                .calendarSelection(1)
                .selectAge("18")
                .selectMyCar()
                .isAgeSelectionNocarErrorMsgDisplayed();


        homepage.clickAvisLogo();


        reservationWidget
                .pickUpLocation(pickUpLocation)
                .dropOffLocation(dropOffLocation)
                .calendarSelection(1)
                .selectAge("25+")
                .clearPickUpDateValue()
                //empty pickUp date
                .selectMyCar()
                .verifyPickUpDateErrorMessage(); //validate pickUp empty date msg

        homepage.clickAvisLogo();

        reservationWidget
                .pickUpLocation(pickUpLocation)
                .dropOffLocation(dropOffLocation)
                .calendarSelection(1)
                .clearDropOffDateValue() //empty dropoff date
                .selectMyCar()
                .verifyReturnDateErrorMessage();  //validate dropoff empty date msg

        homepage.clickAvisLogo();


        reservationWidget
                .pickUpLocation(pickUpLocation)
                .dropOffLocation(dropOffLocation)
                .enterCurrentDatePickUpDropOffDate()
                .pickUpTime("11:00 AM")
                .dropOffTime("10:00 AM") //dropoff time before pickuptime for same date
                .selectMyCar()
                .verifyReturnTimeErrorMessage();


        homepage.clickAvisLogo();

        reservationWidget
                .pickUpLocation(pickUpLocation)
                .dropOffLocation(dropOffLocation)
                .enterpickUpDropOffDate("10/01/22","12/01/22") //wrong date format
                .selectMyCar()
                .verifyPickUpReturnDateInvalidErrorMessage();
        //validate date error


        homepage.clickAvisLogo();

        reservationWidget
                .pickUpLocation(pickUpLocation)
                .dropOffLocation(dropOffLocation)
                .calendarSelection(1)
                .expandAvisWizardNumber()
                .enterAwdAndLastname(WizardNumber,"") // blank last name
                //validate for blank last name error msg
                .selectMyCar()
                .verifyLastNameBlankErrorMessage();
        /*
        reservationWidget
                .enterAwdAndLastname("",lastName) //blank wizard number
                //validate for blank wizard number error
                .selectMyCar()
                .verifyAWDBlankErrorMessage()
                .expandAvisWizardNumber();

*/

        homepage.clickAvisLogo();

        reservationWidget
                .pickUpLocation(pickUpLocation)
                .dropOffLocation(dropOffLocation)
                .calendarSelection(1)
                .expandDiscountCode()
                .enterAwd("abc123") //Invalid AWD code
                //validate for invalid awd code
                .selectMyCar()
                .verifyAWDCouponcodeInvalidErrorMessage();
        reservationWidget.expandDiscountCode();
        reservationWidget
                .expandDiscountCode()
                .enterAwd(awdCode)  //correct corporate AWD code
                //Validate for corporate email ID
                .selectMyCar()
                .verifyCorpEmailIDBlankErrorMessage();
        reservationWidget.expandDiscountCode();

        homepage.clickAvisLogo();

        reservationWidget
                .pickUpLocation(pickUpLocation)
                .dropOffLocation(dropOffLocation)
                .calendarSelection(1)
               // .expandDiscountCode()
                .enterCouponCode("test")//invalid discount code
                //Validation for invalid discount code
                .selectMyCar()
                .verifyAWDCouponcodeInvalidErrorMessage();
        reservationWidget.expandDiscountCode();

        homepage.clickAvisLogo();

        reservationWidget
                .pickUpLocation(pickUpLocation)
                .dropOffLocation(dropOffLocation)
                .calendarSelection(1)
//                .expandDiscountCode()
                .enterAwd(awdCode)  // valid discount code
                .enterCorporateEmailId("test@hh.com") //invalid corporate email id
                //Validation for invalid corporate email id
                .selectMyCar()
                .verifyCorpEmailIDInvalidErrorMessage();
        reservationWidget.expandDiscountCode();

        homepage.clickAvisLogo();

        reservationWidget
                .pickUpLocation(pickUpLocation)
                .dropOffLocation(dropOffLocation)
                .calendarSelection(1)
//                .expandDiscountCode()
                .enterAwd(awdCode)
                .enterCorporateEmailId(corporateEmail)
                .selectMyCar();

        Vehicles vehicles = new Vehicles(driver);
        vehicles.DiscountDropDownClick("test");
        vehicles.verifyAWDCouponcodeInvalidErrorMessage();
        vehicles.DiscountDropDownAWDClear();
        Extras extras = vehicles.step2Submit();
        ReviewAndBook reviewAndBook = extras.Step3Submit();


        reviewAndBook
                .firstname("123@$")
                .lastname("12@")
                .enterEmail("abc")
                .enterPhnNo("abc")
                .step4_AddCreditCardCheckBox()
                .enterCardNumber(creditcardNumber)
                .EnterExpiryDateAndYear()
                .enterSecurityCode("12")
                .enterAddressInboundSpecific("U S A")
                .step4Submit()
                .isCVVInvalidErrorMsgDisplayed();

        reviewAndBook.isLnameInvalidErrorMsgDisplayed();
        reviewAndBook.isFnameInvalidErrorMsgDisplayed();
        reviewAndBook.isCreditCardInvalidErrorMsgDisplayed();

        reviewAndBook.isEmailInvalidErrorMsgDisplayed();
        reviewAndBook.isPhonenumInvalidErrorMsgDisplayed();
        reviewAndBook.isTncErrorMsgtextDisplayed();



    }


}
