//package com.avis.qa.utilities;
//
//import java.util.Map;
//
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Test;
//
//public class TestRunDemo {
//	
//	@BeforeTest
//	public void beforeTestRun() {
//		String filePath = "C:\\Users\\Lenovo\\Downloads\\econ-web-us feature-develop branch\\ecom-us-web\\avis-web-tests\\testData\\Paylesscar_DataParameter_US.csv";
//		CSVFileReader csvFileReader = new CSVFileReader();
//		
//	}
//	
//	
//	@Test(dataProvider="dataAsMap",dataProviderClass = CSVFileReader.class)
//	public void Paylesscar_RES_Verify_Underage_onStep1_keydrop_US(Map<?,?> testDataMap) {
//		
////		if(testDataMap != null) {
//			
//			System.out.println(testDataMap.keySet());
////		}
//		
//	}
//
//}
