package com.amazon.alexa.comms.async.pages;

import com.amazon.alexa.comms.async.pageobjects.PageObject;
import lombok.extern.java.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log
public class VoicePurchasingPage extends PageObject {

    @FindBy(xpath = "//*[@id=\"d-content\"]/div/div/div/div")
    WebElement pageID;

    @FindBy(xpath = "//*[@id=\"dee-can-purchase\"]")
    WebElement onOffSwitch;

    @FindBy(id = "d-message-dismiss")
    WebElement dismissMessageToast;

    public VoicePurchasingPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageVisible(){
        webDriverWait.until(ExpectedConditions.visibilityOf(pageID));
        return pageID.isDisplayed();
    }

    public boolean isToggleEnabled(){
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"dee-can-purchase\"]")));
        return onOffSwitch.isSelected();
    }

    public void clickToggle(){
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"dee-can-purchase\"]")));
        onOffSwitch.click();
    }

    public void dismissMessageToast(){
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(dismissMessageToast));
            dismissMessageToast.click();
        } catch (Exception exception) {
            log.warning(exception.toString());
        }
    }
}
