package com.abg.payless;

import static com.avis.qa.constants.AvisConstants.PAYLESSCAR;
import static com.avis.qa.constants.AvisConstants.REGRESSION;
import static com.avis.qa.constants.AvisConstants.SANITY;

import java.util.Map;

import org.testng.annotations.Test;

import com.avis.qa.core.TestBase;
import com.avis.qa.pages.PaylessHomePage;
import com.avis.qa.pages.PaylessRentalPage;
import com.avis.qa.pages.PaylessReviewReservePage;
import com.avis.qa.pages.VehiclesPage;
import com.avis.qa.utilities.CSVFileReader;

public class PaylessAnonymousUser extends TestBase {

	@Test(groups = {REGRESSION, SANITY, PAYLESSCAR}, dataProvider = "dataAsMap", dataProviderClass = CSVFileReader.class)
	public void AnonymousUserTestCases(Map<?, ?> testDataMap) {		
		launchUrl();
		
		PaylessHomePage paylessHomepage = new PaylessHomePage(getDriver());
		paylessHomepage.getRates(testDataMap);

		VehiclesPage paylessVehiclesPage = new VehiclesPage(getDriver());
		paylessVehiclesPage.chooseVehicles(testDataMap);

		PaylessRentalPage paylessRentalPage = new PaylessRentalPage(getDriver());
		paylessRentalPage.rentalPage(testDataMap);

		PaylessReviewReservePage paylessReviewReservePage = new PaylessReviewReservePage(getDriver());
		paylessReviewReservePage.reviewPage(testDataMap);

	}

}
