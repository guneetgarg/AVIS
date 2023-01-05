package com.avis.qa;


import com.avis.qa.core.TestBase;
import com.avis.qa.helpers.LocationHelper;
import com.avis.qa.pages.Confirmation;
import com.avis.qa.pages.Homepage;
import com.avis.qa.pages.Locations;
import com.avis.qa.utilities.CSVUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.avis.qa.constants.AvisConstants.*;
import static org.testng.Assert.assertTrue;

/**
 * Class to test the location functionality
 *
 * @author ikumar
 */
public class LocationTests extends TestBase {


    /**
     * To verify the Browse functionality on the Location Page
     */
    @Test(groups = {REGRESSION, SANITY, SMOKE,AVIS})
    public void Avis_Location_Browse_Category_US() {
        launchUrl();
        Homepage homepage = new Homepage(getDriver());
        Locations locations = homepage.goToFindALocationPage();
        locations.browserLocation();
        Assert.assertTrue(locations.isMakeAReservationButtonDisplayed(), "Make a Reservation button is not displayed");
        locations.clickOnMakeAReservationWidget();
    }

    /**
     * To verify the Browse functionality on the Location Page for Budget
     */
    @Test(groups = {REGRESSION, SANITY, SMOKE,BUDGET})
    public void Budget_Location_Browse_Category_US() {
        launchUrl();
        Homepage homepage = new Homepage(getDriver());

        Locations locations = homepage.goToFindALocationPage();
        locations.browserLocation();
        Assert.assertTrue(locations.isMakeAReservationButtonDisplayed(), "Make a Reservation button is not displayed");
        locations.clickOnMakeAReservationWidget();
    }

    /**
     * To search for the location from location field.
     **/
    @Test(groups = {REGRESSION, SANITY,SMOKE,AVIS}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Location_Search_MnemonicCodeRes_US(String pickUpLocation, String firstName, String lastName, String email,
                                                String phoneNo,String airline, String flightNumber) {
        launchUrl();
        LocationHelper locationHelper = new LocationHelper(getDriver());
        Confirmation confirmation = locationHelper.Location_Search_MnemonicCodeRes(pickUpLocation, firstName, lastName, email, phoneNo, airline, flightNumber);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        confirmation.cancelReservation();
    }

    @Test(groups = {REGRESSION, SANITY,SMOKE,BUDGET}, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Budget_Location_Search_MnemonicCodeRes_US(String pickUpLocation, String firstName, String lastName, String email,
                                                        String phoneNo, String airline, String flightNumber) {
        launchUrl();
        LocationHelper locationHelper = new LocationHelper(getDriver());
        Confirmation confirmation = locationHelper.Location_Search_MnemonicCodeRes(pickUpLocation, firstName, lastName, email, phoneNo, airline, flightNumber);
        assertTrue(confirmation.isConfirmationNumberDisplayed(), "Confirmation Number is not displayed");
        confirmation.cancelReservation();
    }
}
