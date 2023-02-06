package com.avis.qa;
import com.avis.qa.constants.TextComparison;
import com.avis.qa.pages.paylesscar.HomePage;
import com.avis.qa.pages.paylesscar.LoginWidget;
import com.avis.qa.core.TestBase;
import com.avis.qa.helpers.PayLessCarHelper;
import com.avis.qa.pages.paylesscar.Confirmation;
import com.avis.qa.utilities.CSVFileReader;
import com.avis.qa.utilities.CSVUtils;
import org.testng.annotations.Test;

import static com.avis.qa.constants.AvisConstants.*;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.Map;

public class PayLessCarTest2 extends TestBase {


    @Test(groups = {REGRESSION, SANITY, PAYLESSCAR}, priority=1, dataProvider = "dataAsMap", dataProviderClass = CSVFileReader.class)
    public void Paylesscar_RES_Inbound_IATA_M_type_PayLater_US(Map<?,?> testDataMap){
        
    	launchUrl();
        PayLessCarHelper payLessCarHelper = new PayLessCarHelper(getDriver());
        Confirmation confirmation = payLessCarHelper.Reservation_Inbound_IATA_M_type_PayLater_US(testDataMap);
        assertTrue(confirmation.isConfirmationMessageDisplayed(), TextComparison.RESERVATION_CONFIRMATION_MESSAGE_DISPLAYED_STATUS);
    }
 
    //Check
    @Test(groups = {REGRESSION, SANITY,PAYLESSCAR}, priority=1, dataProvider = "dataAsMap", dataProviderClass = CSVFileReader.class)
    public void Paylesscar_RES_G_typeCoupon_Extras_paylater_US(Map<?,?> testDataMap){
        launchUrl();
        PayLessCarHelper payLessCarHelper = new PayLessCarHelper(getDriver());
        System.out.println("2 test case");
        Confirmation confirmation = payLessCarHelper.Reservation_G_typeCoupon_Extras_paylater_US(testDataMap);
        assertTrue(confirmation.isConfirmationMessageDisplayed(), TextComparison.RESERVATION_CONFIRMATION_MESSAGE_DISPLAYED_STATUS);
    }

    @Test(groups = {REGRESSION, SANITY,PAYLESSCAR}, priority=1, dataProvider = "dataAsMap", dataProviderClass = CSVFileReader.class)
    public void Paylesscar_RES_Outbound_PDN_Paylater_US(Map<?,?> testDataMap){
        launchUrl();
        PayLessCarHelper payLessCarHelper = new PayLessCarHelper(getDriver());
        Confirmation confirmation = payLessCarHelper.Reservation_Outbound_PDN_Paylater_US(testDataMap);
        assertTrue(confirmation.isConfirmationMessageDisplayed(), TextComparison.RESERVATION_CONFIRMATION_MESSAGE_DISPLAYED_STATUS);
    }
    
//Need to be solved stale element error
    @Test(groups = {REGRESSION, SANITY,PAYLESSCAR}, priority=1, dataProvider = "dataAsMap", dataProviderClass = CSVFileReader.class)
    public void Paylesscar_RES_Verify_Underage_onStep1_keydrop_US(Map<?,?> testDataMap){
        launchUrl();
        PayLessCarHelper payLessCarHelper = new PayLessCarHelper(getDriver());
        Confirmation confirmation = payLessCarHelper.Reservation_Verify_Underage_onStep1_keydrop_US(testDataMap);
        assertTrue(confirmation.isConfirmationMessageDisplayed(), TextComparison.RESERVATION_CONFIRMATION_MESSAGE_DISPLAYED_STATUS);
    }

    //Check
    @Test(groups = {REGRESSION, SANITY,PAYLESSCAR}, priority=1, dataProvider = "dataAsMap", dataProviderClass = CSVFileReader.class)
    public void Paylesscar_RES_Profile_G_typeCoupon_Extras_Paylater_US(Map<?,?> testDataMap) throws IOException{
        launchUrl();
        HomePage homepage = new HomePage(getDriver());
        LoginWidget loginWidget = new LoginWidget(getDriver());

        homepage.clickLoginLink();
        loginWidget.login(testDataMap);
        homepage.clickMakeReservation();

        PayLessCarHelper payLessCarHelper = new PayLessCarHelper(getDriver());
        Confirmation confirmation = payLessCarHelper.Reservation_Profile_G_typeCoupon_Extras_Paylater_US(testDataMap);
        assertTrue(confirmation.isConfirmationMessageDisplayed(), TextComparison.RESERVATION_CONFIRMATION_MESSAGE_DISPLAYED_STATUS);
    }

    //bug
    @Test(groups = {REGRESSION, SANITY,PAYLESSCAR}, priority=1, dataProvider = "dataAsMap", dataProviderClass = CSVFileReader.class)
    public void Paylesscar_RES_Profile_Outbound_PDN_Paylater_US(Map<?,?> testDataMap){
        launchUrl();
        HomePage homepage = new HomePage(getDriver());
        LoginWidget loginWidget = new LoginWidget(getDriver());

        homepage.clickLoginLink();
        loginWidget.login(testDataMap);
        homepage.clickMakeReservation();

        PayLessCarHelper payLessCarHelper = new PayLessCarHelper(getDriver());
        Confirmation confirmation = payLessCarHelper.Reservation_Profile_Outbound_PDN_Paylater_US(testDataMap);
        assertTrue(confirmation.isConfirmationMessageDisplayed(), TextComparison.RESERVATION_CONFIRMATION_MESSAGE_DISPLAYED_STATUS);
    }

    @Test(groups = {REGRESSION, SANITY,PAYLESSCAR}, priority=1, dataProvider = "dataAsMap", dataProviderClass = CSVFileReader.class)
    public void Paylesscar_RES_Profile_Inbound_IATA_M_type_PayLater_US(Map<?,?> testDataMap){
        launchUrl();

        HomePage homepage = new HomePage(getDriver());
        LoginWidget loginWidget = new LoginWidget(getDriver());

        homepage.clickLoginLink();
        loginWidget.login(testDataMap);
        homepage.clickMakeReservation();

        PayLessCarHelper payLessCarHelper = new PayLessCarHelper(getDriver());
        Confirmation confirmation = payLessCarHelper.Reservation_Profile_Inbound_IATA_M_type_PayLater_US(testDataMap);
        assertTrue(confirmation.isConfirmationMessageDisplayed(), TextComparison.RESERVATION_CONFIRMATION_MESSAGE_DISPLAYED_STATUS);
    }

    //Under age will not work
    @Test(groups = {REGRESSION, SANITY,PAYLESSCAR}, priority=1, dataProvider = "dataAsMap", dataProviderClass = CSVFileReader.class)
    public void Paylesscar_RES_Profile_Verify_Underage_onStep1_keydrop_US(Map<?,?> testDataMap){
        launchUrl();
        HomePage homepage = new HomePage(getDriver());
        LoginWidget loginWidget = new LoginWidget(getDriver());

        homepage.clickLoginLink();
        loginWidget.login(testDataMap);
        homepage.clickMakeReservation();

        PayLessCarHelper payLessCarHelper = new PayLessCarHelper(getDriver());
        Confirmation confirmation = payLessCarHelper.Reservation_Profile_Verify_Underage_onStep1_keydrop_US(testDataMap);
    }

    @Test(groups = {REGRESSION, SANITY,PAYLESSCAR}, priority=1, dataProvider = "dataAsMap", dataProviderClass = CSVFileReader.class)
    public void Paylesscar_RES_Outbound_DCC_Paynow_US(Map<?,?> testDataMap){
        launchUrl();

        PayLessCarHelper payLessCarHelper = new PayLessCarHelper(getDriver());
        Confirmation confirmation = payLessCarHelper.Reservation_Outbound_DCC_Paynow_US(testDataMap);
        assertTrue(confirmation.isConfirmationMessageDisplayed(), TextComparison.RESERVATION_CONFIRMATION_MESSAGE_DISPLAYED_STATUS);
    }

    @Test(groups = {REGRESSION, SANITY,PAYLESSCAR}, priority=1, dataProvider = "dataAsMap", dataProviderClass = CSVFileReader.class)
    public void Paylesscar_RES_Profile_Outbound_DCC_Paynow_US(Map<?,?> testDataMap){
        launchUrl();
        HomePage homepage = new HomePage(getDriver());
        LoginWidget loginWidget = new LoginWidget(getDriver());

        homepage.clickLoginLink();
        loginWidget.login(testDataMap);
        homepage.clickMakeReservation();

        PayLessCarHelper payLessCarHelper = new PayLessCarHelper(getDriver());
        Confirmation confirmation = payLessCarHelper.Reservation_Profile_Outbound_DCC_Paynow_US(testDataMap);
        assertTrue(confirmation.isConfirmationMessageDisplayed(), TextComparison.RESERVATION_CONFIRMATION_MESSAGE_DISPLAYED_STATUS);
    }

    @Test(groups = {REGRESSION, SANITY,PAYLESSCAR}, priority=1, dataProvider = "dataAsMap", dataProviderClass = CSVFileReader.class)
    public void Paylesscar_RES_Modify_cancel_flow_Step1_to_Step4_US(Map<?,?> testDataMap) throws InterruptedException {
        launchUrl();

        PayLessCarHelper payLessCarHelper = new PayLessCarHelper(getDriver());
        Confirmation confirmation = payLessCarHelper.Reservation_Modify_cancel_flow_Step1_to_Step4_US(testDataMap);
        assertTrue(confirmation.isCancellationMessageDisplayed(), TextComparison.RESERVATION_CANCELLATION_MESSAGE);
    }

    @Test(groups = {REGRESSION, SANITY,PAYLESSCAR}, priority=1, dataProvider = "dataAsMap", dataProviderClass = CSVFileReader.class)
    public void Paylesscar_RES_Profile_Modify_cancel_flow_Step1_to_Step4_US(Map<?,?> testDataMap) throws InterruptedException {
        launchUrl();
        HomePage homepage = new HomePage(getDriver());
        LoginWidget loginWidget = new LoginWidget(getDriver());

        homepage.clickLoginLink();
        loginWidget.login(testDataMap);
        homepage.clickMakeReservation();

        PayLessCarHelper payLessCarHelper = new PayLessCarHelper(getDriver());
        Confirmation confirmation = payLessCarHelper.Reservation_Profile_Modify_cancel_flow_Step1_to_Step4_US(testDataMap);
        assertTrue(confirmation.isCancellationMessageDisplayed(), TextComparison.RESERVATION_CANCELLATION_MESSAGE);
    }

}





