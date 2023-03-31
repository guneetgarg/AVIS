package com.avis.qa.core;

import static com.avis.qa.core.Configuration.BROWSER;
import static com.avis.qa.core.Configuration.DOCKER;
import static com.avis.qa.core.Configuration.URL;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.xml.XmlTest;

import com.avis.qa.listeners.report.ExtentListener;
import com.avis.qa.listeners.report.ExtentManager;
import com.avis.qa.utilities.ElementHelper;

import lombok.extern.log4j.Log4j2;

/**
 * Class contains the Pre-requisite setup before running a Test Case
 *
 * @author ikumar
 */
@Listeners({ ExtentListener.class })
@Log4j2
public class TestBase {
	public static final String REGRESSION = "regression";
	public static final String SANITY = "sanity";
	public static final String SMOKE = "smoke";
    public static final String PAYLESSCAR = "Paylesscar";
    public static final String AVIS = "Avis";
    public static final String BUDGET = "Budget";
    public static final String TEST_DATA = "testData";

    private static final String TEST_RESULT_XML = "/"+Configuration.getValue("domain")+"_testResult.xml";
	protected ElementHelper helper;
	private ThreadLocal<String> testName = new ThreadLocal<>();
	private String testCaseName = "";
	private final ThreadLocal<BrowserInstance> appInstance = new ThreadLocal<>();
	
	private void readWriteIntoFile(String reportContent, boolean isFileWritable) throws IOException {
		File myObj = new File(System.getProperty("user.dir") + TEST_RESULT_XML);
		if (myObj.createNewFile()) {
			System.out.println("File created: " + myObj.getName());
		} else {
		}
		FileWriter fr = new FileWriter(myObj, isFileWritable);
		BufferedWriter f_writer = new BufferedWriter(fr);
		f_writer.write(reportContent);
		f_writer.newLine();
		f_writer.close();
	}

	@BeforeSuite(alwaysRun = true)
	public void beforeGroupsTest(XmlTest xmlTest) {
		deleteFile();
	}

	@AfterSuite(alwaysRun = true)
	public void afterGroupsTest(XmlTest xmlTest) throws IOException {
		String reportContent = readFile();
		String modifyContent = "<testsuite hostname=\"TestResult\" ignored=\"2\" name=\"Test\" tests=\"1\" failures=\"0\" timestamp=\"2023-01-17T11:51:36 IST\" time=\"28.492\" errors=\"0\">\n"
				+ reportContent + "\n</testsuite>";
		reportContent = reportContent.replace(reportContent, modifyContent);
		readWriteIntoFile(reportContent, false);
	}

	@BeforeTest(alwaysRun = true)
	public void startTest(XmlTest xmlTest) {
		Configuration.setTestNGParameters(xmlTest);
		Configuration.setURL();
		ExtentListener.extent = ExtentManager.createInstance();

	}

	@BeforeMethod(alwaysRun = true)
	public void beforeMethodTestBase(Method method, Object[] testData) {
		ArrayList<String> testSheetData = new ArrayList<>();
		String testCaseValue = "";
		if(method.getName().contains("Avis")) {
			testCaseValue = method.getName();
		} else {
			testCaseValue= testData[0].toString();
		}


		if (testCaseValue.contains(",") && testCaseValue.contains("=")) {
			testCaseName = testCaseValue.split("TestCaseName")[1].split(",")[0].split("=")[1];
		} else {
			testCaseName = testCaseValue;
		}
		testName.set(testCaseName);
		testSheetData.add(testCaseName);
		log.info("beforeMethodTestBase() called");
		if (DOCKER.equalsIgnoreCase("true"))
			appInstance.set(new DockerInstance(BROWSER));
		else
			appInstance.set(new BrowserInstance(BROWSER));

	}

	@AfterMethod(alwaysRun = true)
	public void afterMethodTestBase(ITestResult result) throws IOException {
		try {

			if (result.getStatus() == 1) {
				System.out.println("passed");
				String reportContent = "<testcase name=\"" + testCaseName + "\"  classname=\"" + result.getTestClass()
				+ "\"/>";
				reportContent = reportContent.replace("[TestClass name=class", "").replace("]", "");
				readWriteIntoFile(reportContent, true);

			} else if (result.getStatus() == 2) {
				System.out.println("failed");
				TakesScreenshot ss = ((TakesScreenshot) getDriver());
				File file = ss.getScreenshotAs(OutputType.FILE);
				File desti = new File("./testdata/" + result.getMethod().getMethodName().toString() + ".png");
				FileUtils.copyFile(file, desti);
				
				String reportContent = "<testcase name=\"" + testCaseName + "\"  classname=\""
						+ result.getTestClass() + "\">";
				
				if (reportContent.contains("[TestClass name=class")) {
					reportContent = reportContent.replace("[TestClass name=class", "").replace("]", "");
				}

				if (result.getThrowable().toString().contains("Exception:")) {
					reportContent = reportContent + "\n<failure type=\""
							+ result.getThrowable().toString().split("Exception:")[0] + "Exception\" message=\""
							+ result.getThrowable().toString().split("Exception:")[1].split(":")[0] + "\">\n<![CDATA["
							+ result.getThrowable().toString().split("Exception:")[1].split(":")[1]
									+ result.getThrowable().getMessage() + "]]>\n</failure>\n</testcase>";
					
				} else if (result.getThrowable().toString().contains("Error:")) {
					reportContent = reportContent + "\n<failure type=\""
							+ result.getThrowable().toString().split("Error:")[0] + "Exception\" message=\""
							+ result.getThrowable().toString().split("Error:")[1].split(":")[0] + "\">\n<![CDATA["
							+ result.getThrowable().toString().split("Error:")[1] + result.getThrowable().getMessage()
							+ "]]>\n</failure>\n</testcase>";

				} else {
					reportContent = reportContent + "\n<failure type=\"" + result.getThrowable().toString()
							+ "Exception\" message=\"" + result.getThrowable().toString() + "\">\n<![CDATA["
							+ result.getThrowable().toString() + result.getThrowable().getMessage()
							+ "]]>\n</failure>\n</testcase>";
				}

				readWriteIntoFile(reportContent, true);
			}

		} catch (Exception e) {
			System.out.println(e);
		}

//		getDriver().quit();
	}

	public void deleteFile() {
		File file = new File(System.getProperty("user.dir") + TEST_RESULT_XML);
		if (file.exists()) {
			if (file.delete()) {
				System.out.println("File deleted successfully");
			} else {
				System.out.println("Failed to delete the file");
			}
		}
	}

	public String readFile() throws IOException {
		StringBuilder builder = new StringBuilder();
		File file = new File(System.getProperty("user.dir") + TEST_RESULT_XML);
		if (file.exists()) {
			try (BufferedReader buffer = new BufferedReader(new FileReader(file))) {
				String str;
				while ((str = buffer.readLine()) != null) {
					builder.append(str).append("\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return builder.toString();
	}

	private BrowserInstance getBrowserInstance() {
		return appInstance.get();
	}

	public void launchUrl(String url) {
		getBrowserInstance().start(url);
	}

	public void launchUrl() {
		getBrowserInstance().start(URL);
	}

	public WebDriver getDriver() {
		return getBrowserInstance().getDriver();
	}
}
