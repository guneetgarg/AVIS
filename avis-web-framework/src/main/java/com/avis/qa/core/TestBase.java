package com.avis.qa.core;

import com.avis.qa.listeners.report.ExtentListener;
import com.avis.qa.listeners.report.ExtentManager;
import lombok.extern.log4j.Log4j2;

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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static com.avis.qa.core.Configuration.*;

/**
 * Class contains the Pre-requisite setup before running a Test Case
 *
 * @author ikumar
 */
@Listeners({ExtentListener.class})
@Log4j2
public class TestBase {

    private final ThreadLocal<BrowserInstance> appInstance = new ThreadLocal<>();
    
    @BeforeSuite(alwaysRun = true)
	public void beforeGroupsTest(XmlTest xmlTest) {
    	System.out.println("BEFORE SUITE");
		deleteFile();
	}

	@AfterSuite(alwaysRun = true)
	public void afterGroupsTest(XmlTest xmlTest) throws IOException {
		System.out.println("AFTER SUITE");
		String reportContent = readFile();
		String modifyContent = "<testsuite hostname=\"AnkurChaudhary\" ignored=\"2\" name=\"Test\" tests=\"1\" failures=\"0\" timestamp=\"2023-01-17T11:51:36 IST\" time=\"28.492\" errors=\"0\">\n"
				+ reportContent + "\n</testsuite>";
		reportContent = reportContent.replace(reportContent, modifyContent);
		readWriteIntoFile(reportContent,false);
	}

	private void readWriteIntoFile(String reportContent, boolean isFileWritable) throws IOException {
		File myObj = new File(System.getProperty("user.dir") + "/testResult.xml");
		if (myObj.createNewFile()) {
			System.out.println("File created: " + myObj.getName());
		} else {
		}
		FileWriter fr = new FileWriter(myObj, isFileWritable);
		BufferedWriter f_writer = new BufferedWriter(fr);
		f_writer.write(reportContent);
		f_writer.newLine();
		f_writer.close();
		System.out.println("afterMethodTestBase5");
	}

    @BeforeTest(alwaysRun = true)
    public void startTest(XmlTest xmlTest) {
        Configuration.setTestNGParameters(xmlTest);
        Configuration.setURL();
        ExtentListener.extent = ExtentManager.createInstance();
        System.out.println("BEFORE TEST");
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethodTestBase() {
    	System.out.println("BEFORE METHOD");
        log.info("beforeMethodTestBase() called");
        if (DOCKER.equalsIgnoreCase("true"))
            appInstance.set(new DockerInstance(BROWSER));
        else
            appInstance.set(new BrowserInstance(BROWSER));
        
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethodTestBase(ITestResult result) throws IOException {
        log.info("afterMethodTestBase() called");
        try {
            if (result.getStatus() == 1) {
            	System.out.println("PASSED");
                String reportContent = "<testcase name=\""+result.getMethod().getMethodName()+"\"  classname=\""+result.getTestClass()+"\"/>";
                reportContent = reportContent.replace("[TestClass name=class", "").replace("]", "");
                readWriteIntoFile(reportContent,true);
            }
            else if (result.getStatus() == 2) {
//            	System.out.println(result.getThrowable().getStackTrace());
        		TakesScreenshot ss = ((TakesScreenshot) getDriver());
        		File file = ss.getScreenshotAs(OutputType.FILE);
        		File desti = new File("./testdata/"+result.getMethod().getMethodName().toString()+".png");
        		FileUtils.copyFile(file, desti);
            	System.out.println("FAILED");
                String reportContent = "<testcase name=\""+result.getMethod().getMethodName()+"\"  classname=\""+result.getTestClass()+"\">";
               System.out.println("afterMethodTestBase1");
                if(reportContent.contains("[TestClass name=class")){
                	System.out.println("afterMethodTestBase2");
                	reportContent = reportContent.replace("[TestClass name=class", "").replace("]", "");
                }
                System.out.println("result.getThrowable(): "+result.getThrowable());
                if(result.getThrowable().toString().contains("Exception:")) {
                	System.out.println("afterMethodTestBase2");
                reportContent = reportContent+"\n<failure type=\""+result.getThrowable().toString().split("Exception:")[0]+"Exception\" message=\""+result.getThrowable().toString().split("Exception:")[1].split(":")[0]+"\">\n<![CDATA["+result.getThrowable().toString().split("Exception:")[1].split(":")[1]+result.getThrowable().getMessage()+"]]>\n</failure>\n</testcase>";
                }else if(result.getThrowable().toString().contains("Error:")) {
                	System.out.println("afterMethodTestBase3");
                    reportContent = reportContent+"\n<failure type=\""+result.getThrowable().toString().split("Error:")[0]+"Exception\" message=\""+result.getThrowable().toString().split("Error:")[1].split(":")[0]+"\">\n<![CDATA["+result.getThrowable().toString().split("Error:")[1]+result.getThrowable().getMessage()+"]]>\n</failure>\n</testcase>";
                	
                }else {
                	System.out.println("afterMethodTestBase4");
                    reportContent = reportContent+"\n<failure type=\""+result.getThrowable().toString()+"Exception\" message=\""+result.getThrowable().toString()+"\">\n<![CDATA["+result.getThrowable().toString()+result.getThrowable().getMessage()+"]]>\n</failure>\n</testcase>";          	
                }
                
                readWriteIntoFile(reportContent,true);

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        getDriver().quit();
    }
    
    public void deleteFile() {
		File file = new File(System.getProperty("user.dir") + "/testResult.xml");
		if(file.exists()) {
		if (file.delete()) {
			System.out.println("File deleted successfully");
		} else {
			System.out.println("Failed to delete the file");
		}
		}
	}
	public String readFile() throws IOException {
		StringBuilder builder = new StringBuilder();
		File file =new File(System.getProperty("user.dir") + "/testResult.xml");
		if(file.exists()) {
		try (BufferedReader buffer = new BufferedReader(new FileReader(file))) {
			String str;
			while ((str = buffer.readLine()) != null) {
				builder.append(str).append("\n");
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		}
		return builder.toString();
	}
    private BrowserInstance getBrowserInstance() {
    	System.out.println("getBrowserInstance");
        return appInstance.get();
    }

    public void launchUrl(String url) {
        getBrowserInstance().start(url);
    }

    public void launchUrl() {
    	System.out.println("launchUrl");
        getBrowserInstance().start(URL);
    }

    public WebDriver getDriver() {
        return getBrowserInstance().getDriver();
    }
}
