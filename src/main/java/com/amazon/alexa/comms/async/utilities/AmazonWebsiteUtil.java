package com.amazon.alexa.comms.async.utilities;


import com.amazon.alexa.comms.async.pages.AmazonWebsiteHomePage;
import com.amazon.alexa.comms.async.pages.AmazonWebsiteSignInPage;
import lombok.extern.java.Log;
import org.openqa.selenium.WebDriver;

import static com.amazon.alexa.comms.async.constants.AmazonWebsiteURLs.*;

@Log
public class AmazonWebsiteUtil {

    public static String getURL;
    public static String getRequestedURL(String Country)
    {
        switch(Country)
        {
            case "US":
            case "AE":
            case "NONE":
                getURL = AMAZON_URL;
                break;
            case "GB":
                getURL = AMAZON_URL_UK;
                break;
            case "DE":
                getURL = AMAZON_URL_DE;
                break;
            case "FR":
                getURL = AMAZON_URL_FR;
                break;
            case "ES":
                getURL = AMAZON_URL_ES;
                break;
            case "IN":
                getURL = AMAZON_URL_IN;
                break;
            case "IT":
                getURL = AMAZON_URL_IT;
                break;
            case "JP":
                getURL = AMAZON_URL_JP;
                break;
            case "CA":
                getURL = AMAZON_URL_CA;
                break;
            case "BR":
                getURL = AMAZON_URL_BR;
                break;
            case "MX":
                getURL = AMAZON_URL_MX;
                break;
            case "AU":
                getURL = AMAZON_URL_AU;
                break;
            case "PL":
                getURL = AMAZON_URL_PL;
                break;
            case "NL":
                getURL = AMAZON_URL_NL;
                break;
        }
        return getURL;
    }

    public static String getTokenId(String URL)
    {
        URL = URL.substring(URL.lastIndexOf("=") + 1);
        return URL;
    }

    public static void signOutAmazonWebsite(WebDriver webDriver) {
        log.info(("Sing out of Amazon Website"));
        AmazonWebsiteHomePage amazonWebsiteHomePage = new AmazonWebsiteHomePage(webDriver);
        amazonWebsiteHomePage.mouseHoverAccountAndLists();
        amazonWebsiteHomePage.clickSignOut();
    }

    public static void signInAmazonWebsite(WebDriver webDriver, String userEmail, String userPassword){
        AmazonWebsiteSignInPage amazonWebsiteSignInPage = new AmazonWebsiteSignInPage(webDriver);
        log.info(String.format("Navigating to SignIn Page, Inserting email %s and clicking continue button", userEmail));
        amazonWebsiteSignInPage.inputEmail(userEmail);
        amazonWebsiteSignInPage.clickContinueButton();
        log.info(String.format("Inserting password %s and clicking SignIn button", userPassword));
        amazonWebsiteSignInPage.inputPassword(userPassword);
        amazonWebsiteSignInPage.clickSignInButton();
    }
}
