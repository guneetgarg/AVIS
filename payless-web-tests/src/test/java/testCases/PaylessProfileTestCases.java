package testCases;

import java.util.Map;
import org.testng.annotations.Test;
import com.avis.qa.core.TestBase;
import com.avis.qa.utilities.ExcelDataProvider;

import pageObjects.PaylessPofileDashboardPage;
import pageObjects.PaylessSignUpPage;


	public class PaylessProfileTestCases extends TestBase {

		@Test(groups = {REGRESSION, SANITY, PAYLESSCAR}, dataProvider = "dataAsMap", dataProviderClass = ExcelDataProvider.class)
		public void ProfileTestCases(Map<?,  ?> testDataMap) {		
			launchUrl();
			
			PaylessSignUpPage paylessSignUpPage = new PaylessSignUpPage(getDriver());
			paylessSignUpPage.signup(testDataMap);
			
			PaylessPofileDashboardPage paylessPofileDashboardPage = new PaylessPofileDashboardPage(getDriver());
			paylessPofileDashboardPage.modify(testDataMap);
			

		}
	}

