package com.amazon.alexa.comms.async.pages;

import com.amazon.alexa.comms.async.pageobjects.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AmazonWebsiteSignInPage extends PageObject {

    public AmazonWebsiteSignInPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(id = "ap_email")
    WebElement emailTextArea;

    @FindBy(id = "continue")
    WebElement continueButton;

    @FindBy(id ="createAccountSubmit")
    WebElement createYourAmazonAccountButton;

    @FindBy(id = "ap_password")
    WebElement passwordTextArea;

    @FindBy(id = "signInSubmit")
    WebElement signInButton;

    public void inputEmail(String amazonAccountEmailID) {
        webDriverWait.until(ExpectedConditions.visibilityOf(emailTextArea));
        emailTextArea.sendKeys(amazonAccountEmailID);
    }

    public void inputPassword(String amazonAccountPassword) {
        webDriverWait.until(ExpectedConditions.visibilityOf(passwordTextArea));
        passwordTextArea.sendKeys(amazonAccountPassword);
    }

    public void clickContinueButton() {
        webDriverWait.until(ExpectedConditions.visibilityOf(continueButton));
        continueButton.click();
    }

    public void clickSignInButton() {
        webDriverWait.until(ExpectedConditions.visibilityOf(signInButton));
        signInButton.click();
    }

    public void clickCreateYourAccountButton(){
        webDriverWait.until(ExpectedConditions.visibilityOf(createYourAmazonAccountButton));
        createYourAmazonAccountButton.click();
    }
}
