//package com.avis.qa;
//import com.avis.qa.constants.TextComparison;
//import com.avis.qa.pages.paylesscar.HomePage;
//import com.avis.qa.pages.paylesscar.LoginWidget;
//import com.avis.qa.core.TestBase;
//import com.avis.qa.helpers.PayLessCarHelper;
//import com.avis.qa.pages.paylesscar.Confirmation;
//import com.avis.qa.utilities.CSVUtils;
//import org.testng.annotations.Test;
//
//import static com.avis.qa.constants.AvisConstants.*;
//import static org.testng.Assert.assertTrue;
//
//public class PayLessCarTest extends TestBase {
//
//
//    @Test(groups = {REGRESSION, SANITY, PAYLESSCAR}, priority=1, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
//    public void Paylesscar_RES_Inbound_IATA_M_type_PayLater_US(String pickUpLocation, String country, String fname,String lname, String email, String phoneNo, String a, String b, String c, String d, String e, String f, String g, String h){
//        
//    	System.out.println("pickUpLocation= "+pickUpLocation);
//    	System.out.println("Paylesscar_RES_Inbound_IATA_M_type_PayLater_US@@@@@@@@@@@");
//    	launchUrl();
//        PayLessCarHelper payLessCarHelper = new PayLessCarHelper(getDriver());
//        Confirmation confirmation = payLessCarHelper.Reservation_Inbound_IATA_M_type_PayLater_US(pickUpLocation, country, fname, lname, email, phoneNo);
//        assertTrue(confirmation.isConfirmationMessageDisplayed(), TextComparison.RESERVATION_CONFIRMATION_MESSAGE_DISPLAYED_STATUS);
//    }
//
//    //Check
////    @Test(groups = {REGRESSION, SANITY,PAYLESSCAR}, priority=1, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
//    public void Paylesscar_RES_G_typeCoupon_Extras_paylater_US(String pickUpLocation, String country, String fname,String lname, String email, String phoneNo, String a, String b, String c, String d, String e, String f, String g, String h){
//        launchUrl();
////        PayLessCarHelper payLessCarHelper = new PayLessCarHelper(getDriver());
////        Confirmation confirmation = payLessCarHelper.Reservation_G_typeCoupon_Extras_paylater_US(pickUpLocation, country, coupon, fname, lname, email, phoneNo);
////        assertTrue(confirmation.isConfirmationMessageDisplayed(), TextComparison.RESERVATION_CONFIRMATION_MESSAGE_DISPLAYED_STATUS);
//    }
//
////    @Test(groups = {REGRESSION, SANITY,PAYLESSCAR}, priority=1, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
//    public void Paylesscar_RES_Outbound_PDN_Paylater_US(String pickUpLocation, String country, String fname,String lname, String email, String phoneNo, String a, String b, String c, String d, String e, String f, String g, String h){
//        launchUrl();
////        PayLessCarHelper payLessCarHelper = new PayLessCarHelper(getDriver());
////        Confirmation confirmation = payLessCarHelper.Reservation_Outbound_PDN_Paylater_US(pickUpLocation, country, pickUpTime, pdn, fname, lname, email, phoneNo);
////        assertTrue(confirmation.isConfirmationMessageDisplayed(), TextComparison.RESERVATION_CONFIRMATION_MESSAGE_DISPLAYED_STATUS);
//    }
//
////    @Test(groups = {REGRESSION, SANITY,PAYLESSCAR}, priority=1, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
//    public void Paylesscar_RES_Verify_Underage_onStep1_keydrop_US(String pickUpLocation, String dropOffLocation, String country, String pickUpTime,
//                                                                  String dropOffTime, String age, String fname, String lname, String email, String phoneNo,
//                                                                  String ccNumber, String cvv){
//        launchUrl();
//        PayLessCarHelper payLessCarHelper = new PayLessCarHelper(getDriver());
//        Confirmation confirmation = payLessCarHelper.Reservation_Verify_Underage_onStep1_keydrop_US(pickUpLocation, dropOffLocation, country, pickUpTime, dropOffTime, age, fname, lname, email, phoneNo, ccNumber, cvv);
//        assertTrue(confirmation.isConfirmationMessageDisplayed(), TextComparison.RESERVATION_CONFIRMATION_MESSAGE_DISPLAYED_STATUS);
//    }
//
//    //Check
////    @Test(groups = {REGRESSION, SANITY,PAYLESSCAR}, priority=1, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
//    public void Paylesscar_RES_Profile_G_typeCoupon_Extras_Paylater_US(String username, String password, String pickUpLocation, String coupon){
//        launchUrl();
//        HomePage homepage = new HomePage(getDriver());
//        LoginWidget loginWidget = new LoginWidget(getDriver());
//
//        homepage.clickLoginLink();
//        loginWidget.login(username,password);
//        homepage.clickMakeReservation();
//
//        PayLessCarHelper payLessCarHelper = new PayLessCarHelper(getDriver());
//        Confirmation confirmation = payLessCarHelper.Reservation_Profile_G_typeCoupon_Extras_Paylater_US(pickUpLocation, coupon);
//        assertTrue(confirmation.isConfirmationMessageDisplayed(), TextComparison.RESERVATION_CONFIRMATION_MESSAGE_DISPLAYED_STATUS);
//    }
//
//    //bug
////    @Test(groups = {REGRESSION, SANITY,PAYLESSCAR}, priority=1, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
//    public void Paylesscar_RES_Profile_Outbound_PDN_Paylater_US(String username, String password, String pickUpLocation, String pdn){
//        launchUrl();
//        HomePage homepage = new HomePage(getDriver());
//        LoginWidget loginWidget = new LoginWidget(getDriver());
//
//        homepage.clickLoginLink();
//        loginWidget.login(username,password);
//        homepage.clickMakeReservation();
//
//        PayLessCarHelper payLessCarHelper = new PayLessCarHelper(getDriver());
//        Confirmation confirmation = payLessCarHelper.Reservation_Profile_Outbound_PDN_Paylater_US(pickUpLocation, pdn);
//        assertTrue(confirmation.isConfirmationMessageDisplayed(), TextComparison.RESERVATION_CONFIRMATION_MESSAGE_DISPLAYED_STATUS);
//    }
//
////    @Test(groups = {REGRESSION, SANITY,PAYLESSCAR}, priority=1, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
//    public void Paylesscar_RES_Profile_Inbound_IATA_M_type_PayLater_US(String username, String password, String pickUpLocation, String coupon){
//        launchUrl();
//
//        HomePage homepage = new HomePage(getDriver());
//        LoginWidget loginWidget = new LoginWidget(getDriver());
//
//        homepage.clickLoginLink();
//        loginWidget.login(username,password);
//        homepage.clickMakeReservation();
//
//        PayLessCarHelper payLessCarHelper = new PayLessCarHelper(getDriver());
//        Confirmation confirmation = payLessCarHelper.Reservation_Profile_Inbound_IATA_M_type_PayLater_US(pickUpLocation, coupon);
//        assertTrue(confirmation.isConfirmationMessageDisplayed(), TextComparison.RESERVATION_CONFIRMATION_MESSAGE_DISPLAYED_STATUS);
//    }
//
//    //Under age will not work
////    @Test(groups = {REGRESSION, SANITY,PAYLESSCAR}, priority=1, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
//    public void Paylesscar_RES_Profile_Verify_Underage_onStep1_keydrop_US(String username, String password, String pickUpLocation, String dropOffLocation, String country, String pickUpTime,
//                                                                  String dropOffTime, String age, String fname, String lname, String email, String phoneNo,
//                                                                  String ccNumber, String cvv){
//        launchUrl();
//        HomePage homepage = new HomePage(getDriver());
//        LoginWidget loginWidget = new LoginWidget(getDriver());
//
//        homepage.clickLoginLink();
//        loginWidget.login(username,password);
//        homepage.clickMakeReservation();
//
//        PayLessCarHelper payLessCarHelper = new PayLessCarHelper(getDriver());
//        Confirmation confirmation = payLessCarHelper.Reservation_Profile_Verify_Underage_onStep1_keydrop_US(pickUpLocation, dropOffLocation, country, pickUpTime, dropOffTime, age, fname, lname, email, phoneNo, ccNumber, cvv);
//    }
//
////    @Test(groups = {REGRESSION, SANITY,PAYLESSCAR}, priority=1, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
//    public void Paylesscar_RES_Outbound_DCC_Paynow_US(String pickUpLocation, String country, String pickUpTime, String fname, String lname, String email, String phoneNo,
//                                                                          String ccNumber, String cvv){
//        launchUrl();
//
//        PayLessCarHelper payLessCarHelper = new PayLessCarHelper(getDriver());
//        Confirmation confirmation = payLessCarHelper.Reservation_Outbound_DCC_Paynow_US(pickUpLocation, country, pickUpTime, fname, lname, email, phoneNo, ccNumber, cvv);
//        assertTrue(confirmation.isConfirmationMessageDisplayed(), TextComparison.RESERVATION_CONFIRMATION_MESSAGE_DISPLAYED_STATUS);
//    }
//
////    @Test(groups = {REGRESSION, SANITY,PAYLESSCAR}, priority=1, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
//    public void Paylesscar_RES_Profile_Outbound_DCC_Paynow_US(String username, String password, String pickUpLocation, String country, String pickUpTime, String fname, String lname, String email, String phoneNo,
//                                                      String ccNumber, String cvv){
//        launchUrl();
//        HomePage homepage = new HomePage(getDriver());
//        LoginWidget loginWidget = new LoginWidget(getDriver());
//
//        homepage.clickLoginLink();
//        loginWidget.login(username,password);
//        homepage.clickMakeReservation();
//
//        PayLessCarHelper payLessCarHelper = new PayLessCarHelper(getDriver());
//        Confirmation confirmation = payLessCarHelper.Reservation_Profile_Outbound_DCC_Paynow_US(pickUpLocation, country, pickUpTime, fname, lname, email, phoneNo, ccNumber, cvv);
//        assertTrue(confirmation.isConfirmationMessageDisplayed(), TextComparison.RESERVATION_CONFIRMATION_MESSAGE_DISPLAYED_STATUS);
//    }
//
////    @Test(groups = {REGRESSION, SANITY,PAYLESSCAR}, priority=1, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
//    public void Paylesscar_RES_Modify_cancel_flow_Step1_to_Step4_US(String pickUpLocation, String country, String pickUpTime, String pdn,
//                                                                    String fname, String lname, String email, String phoneNo) throws InterruptedException {
//        launchUrl();
//
//        PayLessCarHelper payLessCarHelper = new PayLessCarHelper(getDriver());
//        Confirmation confirmation = payLessCarHelper.Reservation_Modify_cancel_flow_Step1_to_Step4_US(pickUpLocation, country, pickUpTime, pdn, fname, lname, email, phoneNo);
//        assertTrue(confirmation.isCancellationMessageDisplayed(), TextComparison.RESERVATION_CANCELLATION_MESSAGE);
//    }
//
////    @Test(groups = {REGRESSION, SANITY,PAYLESSCAR}, priority=1, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
//    public void Paylesscar_RES_Profile_Modify_cancel_flow_Step1_to_Step4_US(String username, String password, String pickUpLocation, String country, String pickUpTime, String pdn,
//                                                                    String fname, String lname, String email, String phoneNo) throws InterruptedException {
//        launchUrl();
//        HomePage homepage = new HomePage(getDriver());
//        LoginWidget loginWidget = new LoginWidget(getDriver());
//
//        homepage.clickLoginLink();
//        loginWidget.login(username,password);
//        homepage.clickMakeReservation();
//
//        PayLessCarHelper payLessCarHelper = new PayLessCarHelper(getDriver());
//        Confirmation confirmation = payLessCarHelper.Reservation_Profile_Modify_cancel_flow_Step1_to_Step4_US(pickUpLocation, country, pickUpTime, pdn, fname, lname, email, phoneNo);
//        assertTrue(confirmation.isCancellationMessageDisplayed(), TextComparison.RESERVATION_CANCELLATION_MESSAGE);
//    }
//
//}
//
//
//
//
//
