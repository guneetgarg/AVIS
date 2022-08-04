package com.avis.qa;


import com.avis.qa.core.TestBase;
import com.avis.qa.helpers.ReservationHelper;
import com.avis.qa.pages.Confirmation;
import com.avis.qa.pages.Homepage;
import com.avis.qa.utilities.CSVUtils;
import org.testng.annotations.Test;

import static com.avis.qa.constants.AvisConstants.*;
import static org.testng.Assert.assertTrue;

/**
 * Class to test the framework
 *
 * @author ikumar
 */
public class FrameworkTests extends TestBase {

    /**
     * ALM Testcase: NA
     */
//    @Test(groups = {SANITY, REGRESSION, SMOKE})
//    public void AVIS_verifyHomepageLogo_US() {
//        launchUrl();
//        Homepage homePage = new Homepage(getDriver());
//        assertTrue(homePage.isAvisLogoDisplayed(), "Avis logo is not displayed");
//    }

    @Test(groups = {REGRESSION, SMOKE},priority=1, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Reservation_Outbound_PayNow_US(String pickUpLocation, String firstName, String lastName, String email, String phoneNumber, String ccNumber, String cvv) {
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

}
