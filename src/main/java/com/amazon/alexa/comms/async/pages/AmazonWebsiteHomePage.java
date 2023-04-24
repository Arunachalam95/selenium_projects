package com.amazon.alexa.comms.async.pages;

import com.amazon.alexa.comms.async.pageobjects.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AmazonWebsiteHomePage extends PageObject {

    Actions mouseHover = new Actions(webDriver);
    @FindBy(id = "nav-hamburger-menu")
    WebElement hamburgerMenu;
    @FindBy(id = "nav-link-accountList")
    WebElement accountAndList;
    @FindBy(id = "hmenu-customer-name")
    WebElement hamburgerMenuCustomerName;
    @FindBy(linkText = "Sign Out")
    WebElement signOut;

    @FindBy(id = "icp-nav-flyout")
    WebElement chooseLanguageOption;

    @FindBy(linkText = "English-EN")
    WebElement changeLanguage;

    public AmazonWebsiteHomePage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public void clickHamburgerMenu() {
        webDriverWait.until(ExpectedConditions.visibilityOf(hamburgerMenu));
        hamburgerMenu.click();
    }

    public void clickCustomerNameInHamburgerMenu() {
        webDriverWait.until(ExpectedConditions.visibilityOf(hamburgerMenuCustomerName));
        hamburgerMenuCustomerName.click();
    }

    public void mouseHoverAccountAndLists() {
        webDriverWait.until(ExpectedConditions.visibilityOf(accountAndList));
        mouseHover.moveToElement(accountAndList).build().perform();
    }

    public void clickSignOut() {
        webDriverWait.until(ExpectedConditions.visibilityOf(signOut));
        signOut.click();
    }

    public void mouseHoverLanguageOption() {
        webDriverWait.until(ExpectedConditions.visibilityOf(chooseLanguageOption));
        mouseHover.moveToElement(chooseLanguageOption).build().perform();
    }

    public void setLanguageToEnglish() {
        webDriverWait.until(ExpectedConditions.visibilityOf(changeLanguage));
        changeLanguage.click();
    }
}
