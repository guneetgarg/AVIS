package com.avis.qa.core;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Class Contains local driver setup
 *
 * @author ikumar
 */
@Log4j2
public class DockerInstance extends BrowserInstance {

    private static final String REMOTE_URL = "http://localhost:4444";

    public DockerInstance(String browser) {
        super(browser);
    }

    protected void initializeChrome() {
        try {
//            ChromeOptions options = new ChromeOptions();
//            options.setAcceptInsecureCerts(true);
//            webDriver = new RemoteWebDriver(new URL(REMOTE_URL), options);
            ChromeOptions browserOptions = new ChromeOptions();
            browserOptions.setCapability("platformName", "Windows 10");
            browserOptions.setCapability("browserVersion", "latest");
            Map<String, Object> sauceOptions = new HashMap<>();
            sauceOptions.put("build", "dev");
            sauceOptions.put("name", "poc popup");
            browserOptions.setCapability("sauce:options", sauceOptions);

            URL url = new URL("https://oauth-devarshi.work-45e09:33b7782f-79c6-4896-ac4c-43359ce98a74@ondemand.us-west-1.saucelabs.com:443/wd/hub");
            webDriver = new RemoteWebDriver(url, browserOptions);
        } catch (MalformedURLException malformedURLException) {
            throw new RuntimeException("MalformedURLException");
        }
    }

    protected void initializeFirefox() {
        try {
            FirefoxOptions options = new FirefoxOptions();
            options.setAcceptInsecureCerts(true);
            webDriver = new RemoteWebDriver(new URL(REMOTE_URL), options);
        } catch (MalformedURLException malformedURLException) {
            throw new RuntimeException("MalformedURLException");
        }
    }

    protected void initializeEdge() {
        EdgeOptions options = new EdgeOptions();
        try {
            webDriver = new RemoteWebDriver(new URL(REMOTE_URL), options);
        } catch (MalformedURLException malformedURLException) {
            throw new RuntimeException("MalformedURLException");
        }
    }

    protected void initializeMobileEmulation(String deviceName) {
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", deviceName);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

        try {
            webDriver = new RemoteWebDriver(new URL(REMOTE_URL), chromeOptions);
        } catch (MalformedURLException malformedURLException) {
            throw new RuntimeException("MalformedURLException");
        }
    }

}
