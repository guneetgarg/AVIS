package testCases;

import java.util.Map;
import org.testng.annotations.Test;
import com.avis.qa.core.TestBase;
import com.avis.qa.utilities.ExcelDataProvider;
import pageObjects.PaylessSignUp;


	public class PaylessProfileTestCases extends TestBase {

		@Test(groups = {REGRESSION, SANITY, PAYLESSCAR}, dataProvider = "dataAsMap", dataProviderClass = ExcelDataProvider.class)
		public void ProfileTestCases(Map<?,  ?> testDataMap) {		
			launchUrl();
			
			PaylessSignUp paylessSignUp = new PaylessSignUp(getDriver());
			paylessSignUp.signup(testDataMap);

		}
	}

