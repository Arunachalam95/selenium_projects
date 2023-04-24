package com.amazon.alexa.comms.async.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageObject {

    protected int waitTimeInSeconds = 10;
    protected WebDriver webDriver;
    protected WebDriverWait webDriverWait;

    public PageObject(WebDriver webDriver) {
        this.webDriver = webDriver;
        webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(waitTimeInSeconds));
    }

    public PageObject(WebDriver webDriver, int waitTimeInSeconds) {
        this.webDriver = webDriver;
        this.waitTimeInSeconds = waitTimeInSeconds;
        webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(waitTimeInSeconds));
    }
}
