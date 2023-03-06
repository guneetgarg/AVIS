package testCases;

import java.util.Map;

import org.testng.annotations.Test;

import com.avis.qa.core.TestBase;
import com.avis.qa.utilities.CSVFileReader;
import com.avis.qa.utilities.ExcelDataProvider;

import pageObjects.PaylessHomePage;
import pageObjects.PaylessRentalPage;
import pageObjects.PaylessReviewReservePage;
import pageObjects.PaylessVehiclesPage;


public class PaylessAnonymousTestCases extends TestBase {

	@Test(groups = {REGRESSION, SANITY, PAYLESSCAR}, dataProvider = "dataAsMap", dataProviderClass = ExcelDataProvider.class)
	public void AnonymousUserTestCases(Map<?, ?> testDataMap) {		
		launchUrl();
		
//		PaylessHomePage paylessHomepage = new PaylessHomePage(getDriver());
//		paylessHomepage.getRates(testDataMap);
//
//		PaylessVehiclesPage paylessVehiclesPage = new PaylessVehiclesPage(getDriver());
//		paylessVehiclesPage.chooseVehicles(testDataMap);
//
//		PaylessRentalPage paylessRentalPage = new PaylessRentalPage(getDriver());
//		paylessRentalPage.rentalPage(testDataMap);
//
//		PaylessReviewReservePage paylessReviewReservePage = new PaylessReviewReservePage(getDriver());
//		paylessReviewReservePage.reviewPage(testDataMap);

	}

}
