package com.abg.budget;

import static com.avis.qa.constants.AvisConstants.PAYLESSCAR;
import static com.avis.qa.constants.AvisConstants.REGRESSION;
import static com.avis.qa.constants.AvisConstants.SANITY;

import java.util.Map;

import org.testng.annotations.Test;

import com.avis.qa.core.TestBase;
import com.avis.qa.pages.BudgetHomePage;
import com.avis.qa.pages.BudgetRentalPage;
import com.avis.qa.pages.BudgetReviewReservePage;
import com.avis.qa.pages.BudgetVehiclesPage;
import com.avis.qa.utilities.CSVFileReader;

public class BudgetAuthenticatedTestCases extends TestBase{
	
//	@Test(groups = {REGRESSION, SANITY, PAYLESSCAR}, dataProvider = "dataAsMap", dataProviderClass = CSVFileReader.class)
	public void AuthenticatedUserTestCases(Map<?, ?> testDataMap) {	
		
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
