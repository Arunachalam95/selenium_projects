package com.amazon.alexa.comms.async.pages;

import com.amazon.alexa.comms.async.pageobjects.PageObject;
import lombok.extern.java.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log
public class AmazonWebsiteCreateAccountPage extends PageObject {
    public AmazonWebsiteCreateAccountPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(id = "ap_customer_name")
    WebElement yourName;

    @FindBy(id = "ap_customer_name_pronunciation")
    WebElement namePronunciation;

    @FindBy(id = "ap_email")
    WebElement email;

    @FindBy(id = "ap_password")
    WebElement password;

    @FindBy(id = "ap_password_check")
    WebElement retypePassword;

    @FindBy(id = "continue")
    WebElement continueButton;

    @FindAll({
            @FindBy(xpath = "//*[text()='Email address already in use']"),
            @FindBy(xpath = "//*[text()='E-mail address already in use']")
    })
    WebElement promptText;

    @FindBy(xpath = "//*[@id=\"verification-code-form\"]/div[4]/div[1]/h1")
    WebElement vcfTitle;

    @FindBy(id = "cvf-input-code")
    WebElement cvfTextArea;

    @FindBy(id = "cvf-submit-otp-button")
    WebElement submitOTPButton;

    @FindBy(id = "cvf-resend-link")
    WebElement resendOTP;

    public void fillAccountDetails(String userName, String userEmail, String userPassword, String country){
        webDriverWait.until(ExpectedConditions.visibilityOf(yourName));
        yourName.sendKeys(userName);
        if(country.equalsIgnoreCase("JP")){
            namePronunciation.sendKeys(userName);
        }
        email.sendKeys(userEmail);
        password.sendKeys(userPassword);
        retypePassword.sendKeys(userPassword);
        continueButton.click();
    }

    public boolean isExistingAccount(){
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(promptText));
            return promptText.isDisplayed();
        }catch (Exception exception){
            log.warning(exception.toString());
            return false;
        }
    }

    public boolean isNewAccount(){
        webDriverWait.until(ExpectedConditions.visibilityOf(cvfTextArea));
        log.info(String.format("CVF Header Title - %s", vcfTitle.getText()));
        return cvfTextArea.isDisplayed();
    }

    public void enterOTPAndClickSubmitButton(String OTP){
        webDriverWait.until(ExpectedConditions.visibilityOf(cvfTextArea));
        cvfTextArea.sendKeys(OTP);
        submitOTPButton.click();
    }

    public void clickResendOTP(){
        webDriverWait.until(ExpectedConditions.visibilityOf(resendOTP));
        resendOTP.click();
    }
}
