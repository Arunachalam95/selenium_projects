package com.amazon.alexa.comms.async.pages;

import com.amazon.alexa.comms.async.pageobjects.PageObject;
import lombok.extern.java.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

@Log
public class OutlookWebsitePage extends PageObject {
    public OutlookWebsitePage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(xpath = "//*[text()='Verify your new Amazon account' or text()='Verifica tu nueva cuenta de Amazon']")
    WebElement mailSubject;

    @FindBy(xpath = "//*[text()='Amazon Account OTP']")
    WebElement otpFolder;

    @FindBy(xpath = "//*[@id=\"ItemHeader.ToContainer\"]/div/div/div/span/span/div/span[2]")
    WebElement emailId;

    @FindBy(xpath = "//*[@id=\"x_verificationMsg\"]/p[2]")
    WebElement otpText;

    @FindBy(xpath = "//*[@id=\"primaryContainer\"]/div[5]/div/div[1]/div/div[5]/div[1]/div/div[1]/div/div/div[3]/div/button")
    WebElement deleteEmail;

    public void clickOTPFolder(){
        webDriverWait.until(ExpectedConditions.visibilityOf(otpFolder));
        otpFolder.click();
    }
    public boolean verifyEmailsInOTPFolder(){
        webDriverWait.until(ExpectedConditions.visibilityOf(mailSubject));
        mailSubject.click();
        return mailSubject.isDisplayed();
    }

    public boolean isOTPReceived(String email){
        boolean otpReceivedStatus = false;
        webDriverWait.until(ExpectedConditions.visibilityOf(mailSubject));
        List<WebElement> mailSubjectElements = webDriver.findElements(By
                .xpath("//*[text()='Verify your new Amazon account' or text()='Verifica tu nueva cuenta de Amazon']"));
        for (WebElement mailSubjectElement : mailSubjectElements) {
            try {
                mailSubjectElement.click();
            } catch (Exception exception) {
                log.warning(exception.toString());
            }
            webDriverWait.until(ExpectedConditions.visibilityOf(emailId));
            log.info(String.format("Fetched email Id from the outlook email - %s", emailId.getText()));
            if(email.equals(emailId.getText().replace(";","")))
                otpReceivedStatus = true;
                break;
        }
        return otpReceivedStatus;
    }

    public String getOTP(){
        webDriverWait.until(ExpectedConditions.visibilityOf(otpText));
        return otpText.getText();
    }

    public void deleteOTPEmail(){
        webDriverWait.until(ExpectedConditions.visibilityOf(deleteEmail));
        deleteEmail.click();
    }
}
