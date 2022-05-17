package com.avis.qa;


import com.avis.qa.core.TestBase;
import com.avis.qa.pages.Homepage;
import org.testng.annotations.Test;

import static com.avis.qa.constants.AvisConstants.REGRESSION;
import static com.avis.qa.constants.AvisConstants.SANITY;
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
  //  @Test(groups = {SANITY, REGRESSION})
    public void verifyHomepageLogo() {
        launchUrl();
        Homepage homePage = new Homepage(getDriver());
        assertTrue(homePage.isAvisLogoDisplayed(), "Avis logo is not displayed");
    }

}
