package com.amazon.alexa.comms.async.pages;

import com.amazon.alexa.comms.async.pageobjects.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AmazonWebsiteYourAccountPage extends PageObject {

    public AmazonWebsiteYourAccountPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(xpath = "//*[@id=\"a-page\"]/div[2]/div/div[2]/div[2]/a/div/div/div/div[2]/h2")
    WebElement loginAndSecurityOption;

    public void clickLoginAndSecurity(){
        webDriverWait.until(ExpectedConditions.visibilityOf(loginAndSecurityOption));
        loginAndSecurityOption.click();
    }
}
