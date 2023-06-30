package com.avis.qa.helpers;

import com.avis.qa.constants.TextComparison;
import com.avis.qa.pages.paylesscar.*;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

public class PayLessCarHelper {

	private final WebDriver driver;
	private final PayLessCar payLessCar;

	public PayLessCarHelper(WebDriver driver) {
		this.driver = driver;
		this.payLessCar = new PayLessCar(driver);
	}

	public Confirmation Reservation_Inbound_IATA_M_type_PayLater_US(Map<?, ?> testDataMap) {

		Validations validate = new Validations(driver);
		// verify the Payless Brand Text
		validate.verifyPaylessBrandText();
		payLessCar.pickUpLocation(testDataMap.get("PickUpLocation").toString())
				.residenceCountry(testDataMap.get("Country").toString()).getRates();

		validate.verifyPickUpLocation(testDataMap.get("PickUpLocation").toString());
		validate.verifyPickUpDate();

		Vehicles vehicles = new Vehicles(driver);

		Extras extras = vehicles.step2Submit();
		validate.verifyPickUpLocation(testDataMap.get("PickUpLocation").toString());
		validate.verifyPickUpDate();
		validate.verifyVehicleName();
		// validate.verifyCurrencyLogo();
		ReviewAndBook reviewAndBook = extras.Step3Submit();
		validate.verifyPickUpLocation(testDataMap.get("PickUpLocation").toString()).verifyPickUpDate()
				.verifyVehicleName();
		reviewAndBook.clickContinueReservationButton().firstname(testDataMap.get("FirstName").toString())
				.lastname(testDataMap.get("LastName").toString()).email(testDataMap.get("Email").toString())
				.phone(testDataMap.get("PhoneNumber").toString()).checkTermsAndConditions().step4Submit();
		validate.verifyPickUpLocation(testDataMap.get("PickUpLocation").toString()).verifyPickUpDate()
				.verifyPayLaterUserInfo(testDataMap.get("Email").toString(), testDataMap.get("Country").toString());

		return new Confirmation(driver);
	}

	public Confirmation Reservation_G_typeCoupon_Extras_paylater_US(Map<?, ?> testDataMap) {
		Validations validate = new Validations(driver);
		// verify the Payless Brand Text
		validate.verifyPaylessBrandText();
		System.out.println("coupon code enter");
		payLessCar.pickUpLocation(testDataMap.get("PickUpLocation").toString())
				.residenceCountry(testDataMap.get("Country").toString()).clickCouponCheckBox()
				.enterCouponCode(testDataMap.get("Coupon").toString()).getRates();
		validate.verifyPickUpLocation(testDataMap.get("PickUpLocation").toString()).verifyPickUpDate()
				.verifyCouponCode(testDataMap.get("Coupon").toString());

		Vehicles vehicles = new Vehicles(driver);
		Extras extras = vehicles.step2Submit();
		validate.verifyPickUpLocation(testDataMap.get("PickUpLocation").toString());
//		.verifyPickUpDate();
		ReviewAndBook reviewAndBook = extras.Step3Submit();
		validate.verifyPickUpLocation(testDataMap.get("PickUpLocation").toString());
//		.verifyPickUpDate();
		reviewAndBook.clickContinueReservationButton().firstname(testDataMap.get("FirstName").toString())
				.lastname(testDataMap.get("LastName").toString()).email(testDataMap.get("Email").toString())
				.phone(testDataMap.get("PhoneNumber").toString()).checkTermsAndConditions().step4Submit();
		validate.verifyPickUpLocation(testDataMap.get("PickUpLocation").toString())
				// .verifyPickUpDate().verifyCouponCode(testDataMap.get("Coupon").toString())
				.verifyPayLaterUserInfo(testDataMap.get("Email").toString(), testDataMap.get("Country").toString());

		return new Confirmation(driver);
	}

	public Confirmation Reservation_Outbound_PDN_Paylater_US(Map<?, ?> testDataMap) {
		payLessCar.pickUpLocation(testDataMap.get("PickUpLocation").toString())
				.residenceCountry(testDataMap.get("Country").toString()).calendarSelection()
				.pickUpTime(testDataMap.get("PickUpTime").toString()).clickCouponCheckBox()
				.enterPdn(testDataMap.get("PDN").toString()).getRates();

		Validations validate = new Validations(driver);
		validate.verifyPickUpLocation(testDataMap.get("PickUpLocation").toString())
				.verifyPdnNumber(testDataMap.get("PDN").toString());

		Vehicles vehicles = new Vehicles(driver);
		Extras extras = vehicles.step2Submit();
		validate.verifyPickUpLocation(testDataMap.get("PickUpLocation").toString());
		ReviewAndBook reviewAndBook = extras.Step3Submit();
		validate.verifyPickUpLocation(testDataMap.get("PickUpLocation").toString());
		reviewAndBook.clickContinueReservationButton().firstname(testDataMap.get("FirstName").toString())
				.lastname(testDataMap.get("LastName").toString()).email(testDataMap.get("Email").toString())
				.phone(testDataMap.get("PhoneNumber").toString()).checkTermsAndConditions().step4Submit();
		validate.verifyPickUpLocation(testDataMap.get("PickUpLocation").toString())
				.verifyPdnNumber(testDataMap.get("PDN").toString())
				.verifyPayLaterUserInfo(testDataMap.get("Email").toString(), testDataMap.get("Country").toString());

		return new Confirmation(driver);
	}

	public Confirmation Reservation_Verify_Underage_onStep1_keydrop_US(Map<?, ?> testDataMap) {
		payLessCar.pickUpLocation(testDataMap.get("PickUpLocation").toString());
		payLessCar.pickUpdate("03/08/2023");
		payLessCar.pickUpTime(testDataMap.get("PickUpTime").toString());
		payLessCar.returnDate("04/10/2023");
		payLessCar.dropOffTime(testDataMap.get("DropOffTime").toString());
		payLessCar.dropOffLocation(testDataMap.get("DropOffLocation").toString());
		payLessCar.selectAge(testDataMap.get("Age").toString());
		payLessCar.getRates();		
		assertTrue(payLessCar.isNoCarsAvailableMessageDisplayed(), TextComparison.NO_CARS_AVAILABLE_MESSAGE);
		payLessCar.selectAge("25+").getRates();
		Validations validate = new Validations(driver);
		validate.verifyPickUpLocation(testDataMap.get("PickUpLocation").toString());
		validate.verifyPickUpDate();
//		.verifyPickUpDate();
//		assertTrue(payLessCar.isUnderAgeSubChargeMessageDisplayed(), TextComparison.UNDER_SUB_AGE_MESSAGE);
//		assertTrue(payLessCar.isLocationClosedMessageDisplayed(), TextComparison.LOCATION_CLOSED_MESSAGE);

		Vehicles vehicles = new Vehicles(driver);
		Extras extras = vehicles.step2SubmitPayNow();
		validate.verifyPickUpLocation(testDataMap.get("PickUpLocation").toString()).verifyPickUpDate();
		ReviewAndBook reviewAndBook = extras.Step3Submit();
		validate.verifyPickUpLocation(testDataMap.get("PickUpLocation").toString()).verifyPickUpDate();
		reviewAndBook.clickContinueReservationButton().firstname(testDataMap.get("FirstName").toString())
				.lastname(testDataMap.get("LastName").toString()).email(testDataMap.get("Email").toString())
				.phone(testDataMap.get("PhoneNumber").toString())
				
				.enterCardNumber(testDataMap.get("CCNumber").toString()).selectExpiryDate()
				.enterSecurityCode(testDataMap.get("CVV").toString())
				.enterAddressInboundSpecific(testDataMap.get("Country").toString()).checkTermsAndConditions()
				.step4Submit();

//		validate.verifyPickUpLocation(testDataMap.get("PickUpLocation").toString());
//		.verifyPickUpDate()
//				.verifyPayNowUserInfo(testDataMap.get("Email").toString());

		return new Confirmation(driver);
	}

	public Confirmation Reservation_Profile_G_typeCoupon_Extras_Paylater_US(Map<?, ?> testDataMap) {
		payLessCar.pickUpLocation(testDataMap.get("PickUpLocation").toString());
		payLessCar.clickCouponCheckBox();
		payLessCar.pickUpdate("03/08/2023");
		payLessCar.returnDate("04/10/2023");
		payLessCar.enterCouponCode(testDataMap.get("Coupon").toString());
		payLessCar.getRates();
		Validations validate = new Validations(driver);
		
		validate.verifyPickUpLocation(testDataMap.get("PickUpLocation").toString()).verifyPickUpDate()
				.verifyCouponCode(testDataMap.get("Coupon").toString());

		Vehicles vehicles = new Vehicles(driver);
		Extras extras = vehicles.step2Submit();
		validate.verifyPickUpLocation(testDataMap.get("PickUpLocation").toString()).verifyPickUpDate();
		ReviewAndBook reviewAndBook = extras.Step3Submit();
		validate.verifyPickUpLocation(testDataMap.get("PickUpLocation").toString()).verifyPickUpDate();
		reviewAndBook.clickContinueReservationButton().checkTermsAndConditions().step4Submit();
		validate.verifyPickUpLocation(testDataMap.get("PickUpLocation").toString()).verifyPickUpDate()
				.verifyCouponCode(testDataMap.get("Coupon").toString()).verifyUserEmail();
		return new Confirmation(driver);
	}

	public Confirmation Reservation_Profile_Outbound_PDN_Paylater_US(Map<?, ?> testDataMap) {
		String pdn = testDataMap.get("PDN").toString();

		payLessCar.pickUpLocation(testDataMap.get("PickUpLocation").toString());
		payLessCar.pickUpdate("03/16/2023");
		payLessCar.pickUpTime(testDataMap.get("PickUpTime").toString());
		payLessCar.returnDate("04/10/2023");
		payLessCar.clickCouponCheckBox();
		payLessCar.enterPdn(pdn);
		payLessCar.getRates();
		Validations validate = new Validations(driver);
		validate.verifyPickUpLocation(testDataMap.get("PickUpLocation").toString()).verifyPickUpDate()
				.verifyPdnNumber(pdn);
		Vehicles vehicles = new Vehicles(driver);
		Extras extras = vehicles.step2Submit();
		validate.verifyPickUpLocation(testDataMap.get("PickUpLocation").toString()).verifyPickUpDate();
		ReviewAndBook reviewAndBook = extras.Step3Submit();
		validate.verifyPickUpLocation(testDataMap.get("PickUpLocation").toString()).verifyPickUpDate();

		reviewAndBook.clickContinueReservationButton().checkTermsAndConditions().step4Submit();
		validate.verifyPickUpLocation(testDataMap.get("PickUpLocation").toString()).verifyPickUpDate()
				.verifyPdnNumber(pdn).verifyUserEmail();

		return new Confirmation(driver);
	}

	public Confirmation Reservation_Profile_Inbound_IATA_M_type_PayLater_US(Map<?, ?> testDataMap) {
		String pickUpLocation = testDataMap.get("PickUpLocation").toString();
		String coupon = testDataMap.get("Coupon").toString();

		payLessCar.pickUpLocation(pickUpLocation).clickCouponCheckBox().enterCouponCode(coupon).getRates();
		Validations validate = new Validations(driver);
		validate.verifyPickUpLocation(pickUpLocation).verifyPickUpDate().verifyCouponCode(coupon);
		Vehicles vehicles = new Vehicles(driver);
		Extras extras = vehicles.step2Submit();
		validate.verifyPickUpLocation(pickUpLocation).verifyPickUpDate();
		ReviewAndBook reviewAndBook = extras.Step3Submit();
		validate.verifyPickUpLocation(pickUpLocation).verifyPickUpDate();
		reviewAndBook.clickContinueReservationButton().checkTermsAndConditions().step4Submit();

		validate.verifyPickUpLocation(pickUpLocation).verifyPickUpDate().verifyCouponCode(coupon).verifyUserEmail();

		return new Confirmation(driver);
	}

	public Confirmation Reservation_Profile_Verify_Underage_onStep1_keydrop_US(Map<?, ?> testDataMap) {
		String pickUpLocation = testDataMap.get("PickUpLocation").toString();
		String country = testDataMap.get("Country").toString();
		String dropOffTime = testDataMap.get("DropOffTime").toString();

		payLessCar.pickUpLocation(pickUpLocation).residenceCountry(country).dropOffTime(dropOffTime).getRates();

		assertTrue(payLessCar.isNoCarsAvailableMessageDisplayed(), TextComparison.NO_CARS_AVAILABLE_MESSAGE);

		return new Confirmation(driver);
	}

	public Confirmation Reservation_Outbound_DCC_Paynow_US(Map<?, ?> testDataMap) {
		String pickUpLocation = testDataMap.get("PickUpLocation").toString();
		String pickUpTime = testDataMap.get("PickUpTime").toString();
		String firstName = testDataMap.get("FirstName").toString();
		String lastName = testDataMap.get("LastName").toString();
		String email = testDataMap.get("Email").toString();
		String phoneNumber = testDataMap.get("PhoneNumber").toString();
		String cvv = testDataMap.get("CVV").toString();
		String country = testDataMap.get("Country").toString();

		payLessCar.pickUpLocation(pickUpLocation).pickUpTime(pickUpTime).getRates();
		Validations validate = new Validations(driver);
		validate.verifyPickUpLocation(pickUpLocation).verifyPickUpDate();
		Vehicles vehicles = new Vehicles(driver);
		Extras extras = vehicles.step2SubmitPayNow();
		validate.verifyPickUpLocation(pickUpLocation).verifyPickUpDate();

		ReviewAndBook reviewAndBook = extras.Step3Submit();
		validate.verifyPickUpLocation(pickUpLocation).verifyPickUpDate();
		reviewAndBook.clickContinueReservationButton().firstname(firstName).lastname(lastName).email(email)
				.phone(phoneNumber).enterCardNumber("376494122533509").selectExpiryDate().enterSecurityCode(cvv)
				.enterAddressInboundSpecific(country).checkTermsAndConditions().step4Submit();
		validate.verifyPickUpLocation(pickUpLocation).verifyPickUpDate().verifyPayNowUserInfo(email);

		return new Confirmation(driver);
	}

	public Confirmation Reservation_Profile_Outbound_DCC_Paynow_US(Map<?, ?> testDataMap) {
		String pickUpLocation = testDataMap.get("PickUpLocation").toString();
		String pickUpTime = testDataMap.get("PickUpTime").toString();
		String cvv = testDataMap.get("CVV").toString();
		payLessCar.pickUpLocation(pickUpLocation).pickUpTime(pickUpTime).getRates();
		Validations validate = new Validations(driver);
		validate.verifyPickUpLocation(pickUpLocation).verifyPickUpDate();

		Vehicles vehicles = new Vehicles(driver);
		Extras extras = vehicles.step2SubmitPayNow();
		validate.verifyPickUpLocation(pickUpLocation).verifyPickUpDate();
		ReviewAndBook reviewAndBook = extras.Step3Submit();
		validate.verifyPickUpLocation(pickUpLocation).verifyPickUpDate();
		reviewAndBook.clickContinueReservationButton().enterCardNumber("376494122533509").selectExpiryDateUsingDropDown()
				.enterSecurityCode(cvv).checkTermsAndConditions().step4Submit();

		validate.verifyPickUpLocation(pickUpLocation).verifyPickUpDate().verifyUserEmail();
		
		return new Confirmation(driver);
	}

	public Confirmation Reservation_Modify_cancel_flow_Step1_to_Step4_US(Map<?, ?> testDataMap)
			throws InterruptedException {
		String pickUpLocation = testDataMap.get("PickUpLocation").toString();
		String pickUpTime = testDataMap.get("PickUpTime").toString();
		String firstName = testDataMap.get("FirstName").toString();
		String lastName = testDataMap.get("LastName").toString();
		String email = testDataMap.get("Email").toString();
		String phoneNumber = testDataMap.get("PhoneNumber").toString();
		String country = testDataMap.get("Country").toString();
		String pdn = testDataMap.get("PDN").toString();

		payLessCar.pickUpLocation(pickUpLocation).residenceCountry(country).clickCouponCheckBox().enterPdn(pdn)
				.getRates();
		Validations validate = new Validations(driver);
		validate.verifyPickUpLocation(pickUpLocation).verifyPickUpDate().verifyPdnNumber(pdn);
		Vehicles vehicles = new Vehicles(driver);
		Extras extras = vehicles.step2Submit();
		validate.verifyPickUpLocation(pickUpLocation).verifyPickUpDate();
		ReviewAndBook reviewAndBook = extras.Step3Submit();
		validate.verifyPickUpLocation(pickUpLocation).verifyPickUpDate();

		reviewAndBook.clickContinueReservationButton().firstname(firstName).lastname(lastName).email(email)
				.phone(phoneNumber).checkTermsAndConditions().step4Submit().waitForConfirmationMessage();

		validate.verifyPickUpLocation(pickUpLocation).verifyPickUpDate().verifyPdnNumber(pdn)
				.verifyPayLaterUserInfo(email, country);

		Confirmation confirmation = new Confirmation(driver);
		confirmation.closeGetFreeCouponPopup();

		payLessCar.clickEditButton().clickModifyReservationButton().calendarSelection().pickUpTime(pickUpTime)
				.clickUpdateButton();

		vehicles.step2Submit();

		extras.clickEditRentalDetails().verifyReservationDetails().Step3Submit();

		reviewAndBook.clickReviewModificationsButton().clickKeepModificationButton();
		String reservationNo = reviewAndBook.getConfirmationNumber();

		payLessCar.clickMakeNewReservationButton().cancelReservation(reservationNo, lastName);

		return new Confirmation(driver);
	}

	public Confirmation Reservation_Profile_Modify_cancel_flow_Step1_to_Step4_US(Map<?, ?> testDataMap)
			throws InterruptedException {
		String pickUpLocation = testDataMap.get("PickUpLocation").toString();
		String pickUpTime = testDataMap.get("PickUpTime").toString();
		String lastName = testDataMap.get("LastName").toString();

		payLessCar.pickUpLocation(pickUpLocation).getRates();
		Validations validate = new Validations(driver);
		validate.verifyPickUpLocation(pickUpLocation).verifyPickUpDate();
		Vehicles vehicles = new Vehicles(driver);
		Extras extras = vehicles.step2Submit();
		validate.verifyPickUpLocation(pickUpLocation).verifyPickUpDate();

		ReviewAndBook reviewAndBook = extras.Step3Submit();
		validate.verifyPickUpLocation(pickUpLocation).verifyPickUpDate();

		reviewAndBook.clickContinueReservationButton().checkTermsAndConditions().step4Submit();
		validate.verifyPickUpLocation(pickUpLocation).verifyPickUpDate().verifyUserEmail();

		Confirmation confirmation = new Confirmation(driver);
		confirmation.closeGetFreeCouponPopup();

		payLessCar.clickEditButton().clickModifyReservationButton().calendarSelection().pickUpTime(pickUpTime)
				.clickUpdateButton();

		vehicles.step2Submit();

		extras.clickEditRentalDetails().verifyReservationDetails().Step3Submit();

		reviewAndBook.clickReviewModificationsButton().clickKeepModificationButton();
		String reservationNo = reviewAndBook.getConfirmationNumber();

		payLessCar.clickMakeNewReservationButton().cancelReservation(reservationNo, lastName);

		return new Confirmation(driver);
	}

}
