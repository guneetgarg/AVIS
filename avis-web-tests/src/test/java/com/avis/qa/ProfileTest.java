package com.avis.qa;

import com.avis.qa.core.TestBase;
import com.avis.qa.helpers.ReservationHelper;
import com.avis.qa.pages.Confirmation;
import com.avis.qa.pages.Enrollment;
import com.avis.qa.pages.Homepage;
import com.avis.qa.pages.Locations;
import com.avis.qa.utilities.CSVUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.avis.qa.constants.AvisConstants.*;
import static com.avis.qa.utilities.CommonUtils.ONE_SECOND;
import static com.avis.qa.utilities.CommonUtils.threadSleep;
import static org.testng.Assert.assertTrue;

public class ProfileTest  extends TestBase {

    /**
     * ALM Testcase: NA
     */
    @Test(groups = {REGRESSION , SMOKE},priority=1, dataProvider = TEST_DATA, dataProviderClass = CSVUtils.class)
    public void Avis_Profile_Enrollment_Activation_US(String fname, String lname, String email, String phoneNo, String username, String pwd, String AddLine1) {
        launchUrl();
        Homepage homepage = new Homepage(getDriver());
        Enrollment enrollment = homepage.clickSignupLink();
        enrollment.enterFirstName(fname);
        enrollment.enterLastName(lname);
        enrollment.enterMobileNumber(phoneNo);
        enrollment.enterEmailAddress(email);
        enrollment.clickUseEmailAsUsernameCheckbox();
        enrollment.enterUsername(username);
        enrollment.enterPassword(pwd);
       // enrollment.selectAddressLine1Suggestion(AddLine1);
        enrollment.clickSaveButton();
        threadSleep(5000);

    }
}
