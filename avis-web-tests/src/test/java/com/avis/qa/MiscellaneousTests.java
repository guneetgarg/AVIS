package com.avis.qa;

import com.avis.qa.components.Header;
import com.avis.qa.core.TestBase;
import com.avis.qa.helpers.MiscHelper;
import com.avis.qa.pages.Confirmation;
import com.avis.qa.utilities.CSVUtils;
import org.testng.annotations.Test;

import static com.avis.qa.constants.AvisConstants.*;
import static org.testng.Assert.assertTrue;

/**
 * Class contains Miscellaneous Tests
 *
 * @author ikumar
 */
public class MiscellaneousTests extends TestBase {

    @Test(groups = {REGRESSION, SANITY,SMOKE}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Misc_OneClick_VanityURL_US(String url, String title) {
        System.out.println(getAvisUrl(url));
        launchUrl(getAvisUrl(url));
        assertTrue(getDriver().getTitle().contains(title), "Page title is incorrect");
    }

    @Test(groups = {REGRESSION, SANITY, SMOKE}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Misc_OffersPage_Reservation_US(String pickUplocation, String firstName, String lastName, String email,
                                            String phoneNo) {
        launchUrl();
        MiscHelper miscHelper = new MiscHelper(getDriver());
        Confirmation confirmation = miscHelper.Misc_OffersPage_Reservation(pickUplocation, firstName, lastName, email, phoneNo);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        confirmation.cancelReservation();
    }


//    @Test(groups = {REGRESSION, SANITY,SMOKE}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Misc_BusinessPrograms_BaseRateGuranatee_US(String wizardNo, String password, String pickup, String fName,
                                                        String lName, String email, String phone, String bestRateQuote, String LowerRateCar_bestRateQuote,
                                                        String pickupLocation, String DropOffLocation, String vehicle_Type, String webSite, String comments) {
        launchUrl();
        MiscHelper miscHelper = new MiscHelper(getDriver());
        miscHelper.Misc_BusinessPrograms_BaseRateGuarnatee(wizardNo, password, pickup, fName, lName, email,
                phone, bestRateQuote, LowerRateCar_bestRateQuote, pickupLocation, DropOffLocation, vehicle_Type,
                webSite, comments);
    }

    @Test(groups = {REGRESSION, SANITY,SMOKE}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Misc_CarGuide_Res_US(String pickUpLoc, String firstName, String lastName, String email, String phoneNo) {
        launchUrl();
        MiscHelper miscHelper = new MiscHelper(getDriver());
        Confirmation confirmation = miscHelper.Misc_CarGuide_Res(pickUpLoc, firstName, lastName, email, phoneNo);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SANITY,SMOKE}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Misc_OffersPage_SignUp_US(String email, String firstName, String lastName, String country) {
        launchUrl();
        Header header = new Header(getDriver());
        header.offersHeader().enterEmailForSigup(email, firstName, lastName, country);
        assertTrue(header.isSignupConfirmationTextDisplayed(), "Signup Confirmation Text is not displayed");

    }

    @Test(groups = {REGRESSION, SANITY,SMOKE}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Misc_SmallAndMidSizedBusiness_FlyOutSignUp_US(String pickUpLoc, String firstName, String lastName,
                                                           String email, String phoneNo, String companyName, String address1, String city, String province,
                                                           String zipcode, String country) {
        launchUrl();
        MiscHelper miscHelper = new MiscHelper(getDriver());
        miscHelper.Misc_SmallAndMidSizedBusiness_FlyOutSignUp(pickUpLoc, firstName, lastName, email, phoneNo,
                companyName, address1, city, province, zipcode, country);

    }


}
