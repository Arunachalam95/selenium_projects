package com.amazon.alexa.comms.async.pages;

import com.amazon.alexa.comms.async.pageobjects.PageObject;
import lombok.extern.java.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

@Log
public class CodeSignerWebsitePage extends PageObject {

    public CodeSignerWebsitePage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(xpath = "//*[@id=\"request_product\"]/option[contains(text(),'AlexaMobileAndroid')]")
    WebElement alexaMobileAndroidApplication;

    @FindBy(id = "request_package")
    WebElement browseButton;

    @FindBy(id = "anyword")
    WebElement searchTextArea;

    @FindBy(xpath = "/html/body/div[4]/div/div[2]/div[1]/form/div/span[2]/input")
    WebElement signItButton;

    @FindBy(xpath = "//*[@id=\"page_len_limit\"]/option[2]")
    WebElement showEntries;

    @FindBy(xpath = "/html/body/div[4]/div/div[2]/div[3]/div[1]/form/span[2]/input[2]")
    WebElement searchButton;

    String rowFileNameElement = "//*[@id=\"requests\"]/tbody/tr[%s]/td[2]";
    String rowDownloadLinkElement = "//*[@id=\"requests\"]/tbody/tr[%s]/td[6]/a";
    String filenameText;
    String downloadLink;

    public void selectAlexaMobileAndroid() {
        webDriverWait.until(ExpectedConditions.visibilityOf(alexaMobileAndroidApplication));
        alexaMobileAndroidApplication.click();
    }

    public void sendFilePathToBrowseButton(String filePath) {
        webDriverWait.until(ExpectedConditions.visibilityOf(browseButton));
        browseButton.sendKeys(filePath);
    }

    public void clickSignItButton() {
        webDriverWait.until(ExpectedConditions.visibilityOf(signItButton));
        signItButton.click();
    }

    public void sendAliasToSearch() {
        webDriverWait.until(ExpectedConditions.visibilityOf(searchTextArea));
        searchTextArea.sendKeys("arnach");
    }

    public void selectOptionToShowEntries() {
        webDriverWait.until(ExpectedConditions.visibilityOf(showEntries));
        showEntries.click();
    }

    public void clickSearchButton() {
        webDriverWait.until(ExpectedConditions.visibilityOf(searchButton));
        searchButton.click();
    }

    public List<String> getFileNameText() {
    List<String> fileNamesTextList = new ArrayList<>();
        for (int rowIndex = 1; rowIndex <= 25; rowIndex++) {
            try {
                filenameText = webDriver.findElement(By.xpath(String.format(rowFileNameElement, rowIndex))).getText();
                System.out.println(filenameText);
            } catch (Exception exception) {
                filenameText = "No elements in the row";
                log.info("Exception - " + exception.toString());
            }
            fileNamesTextList.add(filenameText);
        }
        System.out.println(fileNamesTextList);
        return fileNamesTextList;
    }

    public String getDownloadLink(int rowIndex){
        try {
            downloadLink = webDriver.findElement(By.xpath(String.format(rowDownloadLinkElement, rowIndex + 1))).getAttribute("href");
        }catch (Exception exception){
            log.info(exception.toString());
        }
        return downloadLink;
    }
}
