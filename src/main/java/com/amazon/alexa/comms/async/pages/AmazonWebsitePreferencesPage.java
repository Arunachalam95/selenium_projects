package com.amazon.alexa.comms.async.pages;

import com.amazon.alexa.comms.async.pageobjects.PageObject;
import lombok.extern.java.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
@Log
public class AmazonWebsitePreferencesPage extends PageObject {

    Actions mouseHover = new Actions(webDriver);
    final String addressCountryCodeOption = "//*[@id=\"adr_CountryCode\"]/select/option[contains(text(),'%s')]";
    final String phoneNumber = "1234567890";

    @FindBy(id = "country")
    WebElement countryAndRegionSettings;
    @FindBy(xpath = "//*[@id=\"country\"]/div[2]/div")
    WebElement countryAndRegion;
    @FindBy(xpath = "//*[@id=\"country_change_myx \"]/span/button/span")
    WebElement changeButton;
    @FindBy(xpath = "//input[@id='adr_AddressLine1']")
    WebElement addressLineOne;

    @FindBy(id = "adr_AddressLine2")
    WebElement addressLineTwo;

    @FindBy(id = "adr_City")
    WebElement addressCity;

    @FindBy(id = "adr_StateOrRegion")
    WebElement addressStateOrRegion;

    @FindBy(id = "adr_PostalCode")
    WebElement addressPostalCode;

    @FindBy(id = "adr_PostalCode1")
    WebElement addressPostalCodeOne;

    @FindBy(id = "adr_PostalCode2")
    WebElement addressPostalCodeTwo;

    @FindBy(xpath = "//*[@id=\"adr_CountryCode\"]/select")
    WebElement addressCountryCode;

    @FindBy(id = "adr_PhoneNumber")
    WebElement addressPhoneNumber;

    @FindBy(xpath = "//*[@id=\"dialogButton_ok_myx \"]/span/button/span")
    WebElement updateButton;

    @FindBy(xpath = "//*[@id=\"country\"]/div[1]/div/div[2]/div/div[2]")
    WebElement countryOfResidence;

    public AmazonWebsitePreferencesPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public void clickCountryAndRegion() {
        webDriverWait.until(ExpectedConditions.visibilityOf(countryAndRegionSettings));
        countryAndRegion.click();
    }

    public void clickChangeButton() {
        webDriverWait.until(ExpectedConditions.visibilityOf(changeButton));
        changeButton.click();
    }

    public void fillAddress(String Country, List<String> Address) {
        if (Country.equals("JP")) {
            webDriverWait.until(ExpectedConditions.visibilityOf(addressPostalCodeOne));
            addressPostalCodeOne.sendKeys("093");
            addressPostalCodeTwo.sendKeys("0022");
            addressLineOne.sendKeys("448-1195");
            addressLineTwo.sendKeys("Minami 12-jonishi, Abashiri-shi");
        } else {
            webDriverWait.until(ExpectedConditions.visibilityOf(addressLineOne));
            addressLineOne.sendKeys(Address.get(0));
            addressLineTwo.sendKeys(Address.get(1));
            addressCity.sendKeys(Address.get(2));
            addressStateOrRegion.sendKeys(Address.get(3));
            addressPostalCode.sendKeys(Address.get(4));
            addressCountryCode.click();
            log.info(String.format("Selecting country code for the account - %s", Address.get(5)));
            webDriver.findElement(By.xpath(String.format(addressCountryCodeOption, Address.get(5)))).click();
            String countryCode = webDriver.findElement(By.xpath(String.format(addressCountryCodeOption, Address.get(5)))).getText();
            log.info(String.format("Country code selected for the account - %s", countryCode));
            if (!countryCode.equals(Address.get(5))) {
                addressCountryCode.click();
                webDriver.findElement(By.xpath(String.format(addressCountryCodeOption + "[2]", Address.get(5)))).click();
            }
        }
        addressPhoneNumber.sendKeys(phoneNumber);
        updateButton.click();
    }

    public String getCountryOfResidence() {
        webDriverWait.until(ExpectedConditions.visibilityOf(countryOfResidence));
        return countryOfResidence.getText();
    }

    public void mouseHoverUpdateButton() {
        webDriverWait.until(ExpectedConditions.visibilityOf(updateButton));
        mouseHover.moveToElement(updateButton).build().perform();
    }
}
