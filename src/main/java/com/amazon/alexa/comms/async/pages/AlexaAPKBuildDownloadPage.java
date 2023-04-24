package com.amazon.alexa.comms.async.pages;

import com.amazon.alexa.comms.async.pageobjects.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AlexaAPKBuildDownloadPage extends PageObject {

    public AlexaAPKBuildDownloadPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(linkText = "Download (Debug Signed)")
    WebElement downloadDebugSigned;


    public void clickDownloadDebugSigned() {
        webDriverWait.until(ExpectedConditions.visibilityOf(downloadDebugSigned));
        downloadDebugSigned.click();
    }
}
