package com.amazon.alexa.comms.async.pages;

import com.amazon.alexa.comms.async.pageobjects.PageObject;
import lombok.extern.java.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log
public class AmazonWebsiteHouseholdPage extends PageObject {

    public AmazonWebsiteHouseholdPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(linkText = "Add Adult")
    WebElement addAdultButton;

    @FindBy(xpath = "//*[@id=\"c2cr-invite-form\"]/div[1]/div[1]/input")
    WebElement theirNameTextArea;

    @FindBy(xpath = "//*[@id=\"c2cr-invite-form\"]/div[1]/div[2]/input")
    WebElement theirEmailTextArea;

    @FindBy(xpath = "//*[@id=\"a-autoid-0\"]/span/input")
    WebElement inviteContinueButton;

    @FindBy(linkText = "Manage Invite")
    WebElement manageInviteButton;

    @FindBy(linkText = "Continue")
    WebElement continueButton;

    @FindBy(xpath = "//*[@id=\"singleColumnLayout\"]/div/div/div/div[2]/div/div[2]/div/div/h4")
    WebElement confirmationText;

    public void clickAddAdultButton(){
        webDriverWait.until(ExpectedConditions.visibilityOf(addAdultButton));
        addAdultButton.click();
    }

    public void inviteAdult(String theirName, String theirEmail){
        webDriverWait.until(ExpectedConditions.visibilityOf(theirNameTextArea));
        theirNameTextArea.sendKeys(theirName);
        theirEmailTextArea.sendKeys(theirEmail);
        inviteContinueButton.click();
    }

    /* Helper function to click continue/Agree And Continue/Send Invite button

     */
    public void clickContinueButton(){
        webDriverWait.until(ExpectedConditions.visibilityOf(inviteContinueButton));
        inviteContinueButton.click();
    }

    public void clickManageInviteButton(){
        webDriverWait.until(ExpectedConditions.visibilityOf(manageInviteButton));
        manageInviteButton.click();
    }

    public String getCurrentURL() {
        webDriverWait.until(ExpectedConditions.visibilityOf(inviteContinueButton));
        return webDriver.getCurrentUrl();
    }

    public void clickContinueLinkButton(){
        webDriverWait.until(ExpectedConditions.visibilityOf(continueButton));
        continueButton.click();
    }

    public String getConfirmationText(){
        webDriverWait.until(ExpectedConditions.visibilityOf(confirmationText));
        return confirmationText.getText();
    }
}
