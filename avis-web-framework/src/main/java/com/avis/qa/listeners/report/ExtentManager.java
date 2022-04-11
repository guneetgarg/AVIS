package com.avis.qa.listeners.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.avis.qa.core.Configuration;

import java.util.Date;


public class ExtentManager {

    public static String screenshotPath;
    public static String screenshotName;
    private static ExtentReports extent;

    public static ExtentReports createInstance() {

        Date d = new Date();
        String fileName = "./extent-reports/" + "Extent_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";

        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);

        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle(fileName);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(fileName);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Environment", Configuration.ENVIRONMENT);
        extent.setSystemInfo("URL", Configuration.URL);
        extent.setSystemInfo("Brand", Configuration.BRAND);
        extent.setSystemInfo("Country", Configuration.DOMAIN);
        extent.setSystemInfo("Browser", Configuration.BROWSER);

        return extent;
    }

//		public static void captureScreenshot() {
//			File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
//
//			Date d = new Date();
//			screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";
//
//			try {
//				FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "\\reports\\" + screenshotName));
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}


}

