package com.amazon.alexa.comms.async.scripts;

import com.amazon.alexa.comms.async.driver.DriverConfiguration;
import com.amazon.alexa.comms.async.pages.AlertPopUpPage;


import com.amazon.alexa.comms.async.pages.AmazonWebsiteHomePage;
import com.amazon.alexa.comms.async.pages.AmazonWebsitePreferencesPage;
import com.amazon.alexa.comms.async.pages.AmazonWebsiteSignInPage;
import com.amazon.alexa.comms.async.utilities.AddressUtil;
import com.amazon.alexa.comms.async.utilities.AmazonPreferencesURLsUtil;
import com.amazon.alexa.comms.async.utilities.AmazonWebsiteUtil;
import lombok.extern.java.Log;

import static com.amazon.alexa.comms.async.scripts.AmazonAccountEffectiveMarketPlaceId.driverConfiguration;

@Log
public class AmazonAccountCountryOfResidence extends DriverConfiguration {

    public static String result = null;
    String className = this.getClass().getSimpleName();
    AmazonWebsiteSignInPage amazonWebsiteSignInPage;
    AmazonWebsiteHomePage amazonWebsiteHomePage;
    AmazonWebsitePreferencesPage amazonWebsitePreferencesPage;
    AlertPopUpPage alertPopUpPage;

    public String setCountryOfResidence(String email, String password, String preferredMarketPlace, String countryOfResidence) throws InterruptedException {

        log.info(String.format("Initiating driver setup for the class - %s", className));
        driverConfiguration.setDriver();

        amazonWebsiteSignInPage = new AmazonWebsiteSignInPage(webDriver);
        amazonWebsiteHomePage = new AmazonWebsiteHomePage(webDriver);
        amazonWebsitePreferencesPage = new AmazonWebsitePreferencesPage(webDriver);
        alertPopUpPage = new AlertPopUpPage(webDriver);

        log.info(String.format("Opening the URL on the chrome browser - %s", AmazonWebsiteUtil.getRequestedURL(preferredMarketPlace)));
        driverConfiguration.getURL(AmazonWebsiteUtil.getRequestedURL(preferredMarketPlace));

        log.info("Handling alert pages before AmazonSignInPage");
        alertPopUpPage.clickSkipButton();

        log.info(String.format("Navigating to SignIn Page, Inserting email %s and clicking continue button", email));
        amazonWebsiteHomePage.clickHamburgerMenu();
        amazonWebsiteHomePage.clickCustomerNameInHamburgerMenu();

        AmazonWebsiteUtil.signInAmazonWebsite(webDriver, email, password);

        log.info("Handling alert pages before AmazonWebsiteHomePage");
        alertPopUpPage.clickSkipButton();

        log.info(String.format("Opening the Preferences URL on the chrome browser - %s", AmazonPreferencesURLsUtil.getRequestedURL(preferredMarketPlace)));
        driverConfiguration.getURL(AmazonPreferencesURLsUtil.getRequestedURL(preferredMarketPlace));

        log.info("Clicking on country and region to fill address");
        amazonWebsitePreferencesPage.clickCountryAndRegion();
        amazonWebsitePreferencesPage.clickChangeButton();
        if(preferredMarketPlace.equals("US")) {
            amazonWebsitePreferencesPage.fillAddress(preferredMarketPlace, AddressUtil.getAddress(countryOfResidence));
        }else {
            amazonWebsitePreferencesPage.fillAddress(preferredMarketPlace, AddressUtil.getAddress(preferredMarketPlace));
        }
        Thread.sleep(3000);
        log.info(String.format("Fetching CountryOfResidence for the account - %s", email));
        result = amazonWebsitePreferencesPage.getCountryOfResidence();

        log.info(String.format("CountryOfResidence (%1$s) for the account - %2$s", result, email));
//        driverConfiguration.quitDriver();
        return result;
    }
}
