package com.amazon.alexa.comms.async.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;

public class DriverConfiguration {

    public static WebDriver webDriver;
    public static WebDriverWait webDriverWait;
    public static ChromeOptions chromeOptions;
    public static DesiredCapabilities desiredCapabilities;
    public int waitTimeInSeconds = 10;

    HashMap<String, Object> chromePreferences = new HashMap<String, Object>();

    public void setDriver() {
        // set the system property for Chrome driver
        WebDriverManager.chromedriver().clearResolutionCache().setup();
        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        // Create driver object for CHROME browser
        webDriver = new ChromeDriver(chromeOptions);
        webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriver.manage().window().maximize();
    }

    public void setDriverHeadless(String downloadPath) {
        chromePreferences.put("profile.default_content_settings.popups", 0);
        chromePreferences.put("download.default_directory", downloadPath);
        // Set the system property for Chrome driver
        WebDriverManager.chromedriver().clearResolutionCache().setup();
        // Setting up chrome options for CHROME browser
        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.setHeadless(true);
        chromeOptions.setExperimentalOption("prefs", chromePreferences);
        chromeOptions.addArguments("start-maximized");
        // Setting up capabilities for CHROME browser
        desiredCapabilities = new DesiredCapabilities();
//        desiredCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        // Create driver object for CHROME browser
        webDriver = new ChromeDriver(chromeOptions);
        webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }

    public void setDriverHeadless() {
        // set the system property for Chrome driver
        WebDriverManager.chromedriver().clearResolutionCache().setup();
        // Create driver object for CHROME browser
        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.setHeadless(true);
        chromeOptions.addArguments("start-maximized");
        webDriver = new ChromeDriver(chromeOptions);
        webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }

    public void closeDriver() {
        webDriver.close();
    }

    public void quitDriver() {
        webDriver.quit();
    }

    public void getURL(String Url) {
        webDriver.get(Url);
    }

    public String getPageSource() {
        return webDriver.getPageSource();
    }

    public String getCurrentURL() {
        return webDriver.getCurrentUrl();
    }
}
