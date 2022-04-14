package com.avis.qa;


import com.avis.qa.core.TestBase;
import com.avis.qa.pages.Homepage;
import org.testng.annotations.Test;

import static com.avis.qa.constants.AvisConstants.*;
import static org.testng.Assert.assertFalse;

/**
 * Class to test the framework
 *
 * @author ikumar
 */
public class FrameworkTests extends TestBase {

    /**
     * ALM Testcase: NA
     */
    @Test(groups = {SANITY, REGRESSION, SMOKE})
    public void verifyHomepageLogo() {
        launchUrl();
        Homepage homePage = new Homepage(getDriver());
        assertFalse(homePage.isAvisLogoDisplayed(), "Avis logo is not displayed");
    }

}
