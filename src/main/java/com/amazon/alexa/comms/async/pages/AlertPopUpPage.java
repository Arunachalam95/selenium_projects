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
public class AlertPopUpPage extends PageObject {

    @FindAll({
            @FindBy(linkText = "Skip"),
            @FindBy(linkText = "Omitir"),
            @FindBy(id = "sp-cc-accept"),
            @FindBy(linkText = "Not now")
    })
    WebElement skipButton;
    @FindAll({
            @FindBy(xpath = "//*[@id=\"auth-account-fixup-phone-form\"]/div/h1"),
            @FindBy(xpath = "//*[@id=\"sp-cc\"]/div[2]/h4"),
            @FindBy(xpath = "/html/body/div[1]/div[2]/div/div[2]/div/div/div[4]/div/div/div/div[1]/h1"),
            @FindBy(xpath = "//*[@id=\"d-header-title\"]"),
            @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[1]/div/div[1]/div/div[1]")
    })
    WebElement alertHeaderText;


    //Terms of Use page/Begin Setup/ (Alexa Amazon Website)
    @FindAll({
            @FindBy(xpath = "/html/body/div[1]/div[2]/div/div[2]/div/div/div[4]/div/div/div/div[3]/div[1]/button/span[1]"),
            @FindBy(xpath = "/html/body/div[1]/div[2]/div/div[2]/div/div/div[4]/div/div/div/div[2]/div[1]/button/span[1]"),
            @FindBy(partialLinkText = "alexa.amazon.com")
    })
    WebElement continueButton;

    public AlertPopUpPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public void clickSkipButton() {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(skipButton));
            log.info(String.format("Handling Alert Page - %s", alertHeaderText.getText()));
            skipButton.click();
        } catch (Exception exception) {
            log.warning(exception.toString());
        }
    }

    //Handling Terms of Use page (Alexa Amazon Website)
    public void clickContinueButton() {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(continueButton));
            log.info(String.format("Handling Alert Page - %s", alertHeaderText.getText()));
            continueButton.click();
        } catch (Exception exception) {
            log.warning(exception.toString());
        }
    }

}
