package testCases;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.Test;

import com.avis.qa.core.TestBase;
import com.avis.qa.utilities.ExcelDataProvider;

public class BudgetAuthenticateTestCases extends TestBase{
	
	
	@Test(groups = {REGRESSION, SANITY, PAYLESSCAR}, dataProvider = "dataAsMap", dataProviderClass = ExcelDataProvider.class)
	public void AuthenticateUserTestCases(Map<?, ?> testDataMap) {	
		System.out.println(testDataMap);
//		System.out.println("Anonymoyus test cases");
		launchUrl();
	}
}
