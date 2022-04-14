package com.avis.qa.listeners.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.avis.qa.core.TestBase;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;


public class ExtentListener extends TestBase implements ITestListener {

	public static ExtentReports extent;
	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();
	public static ExtentTest test;

	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName() + "     @TestCase : " + result.getMethod().getMethodName());
		testReport.set(test);
	}

	public void onTestSuccess(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " PASSED" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		testReport.get().pass(m);
	}

	public void onTestFailure(ITestResult result) {

		String excepionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		testReport.get().fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
				+ "</font>" + "</b >" + "</summary>" + excepionMessage.replaceAll(",", "<br>") + "</details>" + " \n");

//		ExtentManager.captureScreenshot();
//		testReport.get().fail("<b>" + "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>",
//				MediaEntityBuilder.createScreenCaptureFromPath(ExtentManager.screenshotName)
//						.build());

		String failureLogg = "TEST CASE FAILED";
		Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
		testReport.get().log(Status.FAIL, m);

	}

	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "Test Case:- " + methodName + " Skipped" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		//testReport.get().skip(m);
		//testReport.get().log(Status.SKIP, m);
		extent.removeTest(test);

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {

	}

	public void onFinish(ITestContext context) {
		if (extent != null) {
			extent.flush();
		}
	}

}
