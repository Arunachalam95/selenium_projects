package com.amazon.alexa.comms.async.pages;

import com.amazon.alexa.comms.async.pageobjects.PageObject;
import lombok.extern.java.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log
public class AlexaAmazonWebsiteSignInPage extends PageObject {

    @FindBy(id = "ap_email")
    WebElement emailTextArea;
    @FindBy(id = "ap_password")
    WebElement passwordTextArea;
    @FindBy(id = "signInSubmit")
    WebElement signInButton;
    @FindBy(xpath = "/html/body/pre")
    WebElement internalFailureText;
    @FindBy(xpath = "//*[@id=\"auth-error-message-box\"]/div/div/ul/li/span")
    WebElement loginWarningText;
    @FindBy(xpath = "//*[@id=\"auth-error-message-box\"]/div/h4")
    WebElement loginWarningHeaderText;

    @FindBy(partialLinkText = "alexa.amazon.com")
    WebElement websiteLinkText;

    @FindBy(id = "ar-continue-button")
    WebElement goToAlexaApp;

    public AlexaAmazonWebsiteSignInPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public void inputEmail(String amazonAccountEmailID) {
        webDriverWait.until(ExpectedConditions.visibilityOf(emailTextArea));
        emailTextArea.sendKeys(amazonAccountEmailID);
    }

    public void inputPassword(String amazonAccountPassword) {
        webDriverWait.until(ExpectedConditions.visibilityOf(emailTextArea));
        passwordTextArea.sendKeys(amazonAccountPassword);
    }

    public void clickSignInButton() {
        webDriverWait.until(ExpectedConditions.visibilityOf(signInButton));
        signInButton.click();
    }

    public boolean checkForInternalFailure(String failureText) {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(internalFailureText));
            return internalFailureText.getText().equals(failureText);
        } catch (Exception exception) {
            return false;
        }
    }

    public String checkForWarningText() {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(loginWarningHeaderText));
            return loginWarningText.getText();
        } catch (Exception exception) {
            return exception.toString();
        }
    }

    public void goToSignInPage(){
        webDriverWait.until(ExpectedConditions.visibilityOf(websiteLinkText));
        websiteLinkText.click();
    }
}
