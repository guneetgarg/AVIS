package testCases;
import java.util.Map;

import org.testng.annotations.Test;

import com.avis.qa.core.TestBase;
import com.avis.qa.utilities.CSVFileReader;
import com.avis.qa.utilities.ExcelDataProvider;

import pageObjects.BudgetHomePage;
import pageObjects.BudgetRentalPage;
import pageObjects.BudgetReviewReservePage;
import pageObjects.BudgetVehiclesPage;

public class BudgetAnonymousTestCases extends TestBase{
	
	
	@Test(groups = {REGRESSION, SANITY, PAYLESSCAR}, dataProvider = "dataAsMap", dataProviderClass = ExcelDataProvider.class)
	public void AnonymousUserTestCases(Map<?, ?> testDataMap) throws InterruptedException {	
		launchUrl();
		
		BudgetHomePage budgetHomePage= new BudgetHomePage(getDriver());
		budgetHomePage.selectYourCar(testDataMap);
		
		BudgetVehiclesPage budgetVehiclesPage=new BudgetVehiclesPage(getDriver());
		budgetVehiclesPage.chooseVehicles(testDataMap);
		
		BudgetRentalPage budgetRentalPage=new BudgetRentalPage(getDriver());
		budgetRentalPage.rentalPage(testDataMap);
		
		BudgetReviewReservePage budgetReviewReservePage = new BudgetReviewReservePage(getDriver());
		budgetReviewReservePage.reviewReserve(testDataMap);
	}
}