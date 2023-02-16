package com.abg.budget;

import static com.avis.qa.constants.AvisConstants.PAYLESSCAR;
import static com.avis.qa.constants.AvisConstants.REGRESSION;
import static com.avis.qa.constants.AvisConstants.SANITY;

import java.util.Map;

import org.testng.annotations.Test;

import com.avis.qa.core.TestBase;
import com.avis.qa.utilities.CSVFileReader;

public class BudgetAnonymousTestCases extends TestBase{
	
	
	@Test(groups = {REGRESSION, SANITY, PAYLESSCAR}, dataProvider = "dataAsMap", dataProviderClass = CSVFileReader.class)
	public void AnonymousUserTestCases(Map<?, ?> testDataMap) {	
		
		launchUrl();
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
