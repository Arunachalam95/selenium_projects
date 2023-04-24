package com.amazon.alexa.comms.async.scripts;

import com.amazon.alexa.comms.async.constants.AlexaAmazonWebsiteURLs;
import com.amazon.alexa.comms.async.constants.AmazonWebsiteURLs;
import com.amazon.alexa.comms.async.driver.DriverConfiguration;
import com.amazon.alexa.comms.async.pages.*;
import com.amazon.alexa.comms.async.utilities.AlexaAmazonWebsiteUtil;
import com.amazon.alexa.comms.async.utilities.AmazonWebsiteHouseHoldUtil;
import com.amazon.alexa.comms.async.utilities.AmazonWebsiteUtil;
import lombok.extern.java.Log;

import static com.amazon.alexa.comms.async.constants.StringConstants.*;
import static com.amazon.alexa.comms.async.scripts.AmazonAccountEffectiveMarketPlaceId.driverConfiguration;

@Log
public class AmazonAccountHouseHold extends DriverConfiguration {

    String className = this.getClass().getSimpleName();
    AlertPopUpPage alertPopUpPage;

    public String addAmazonAccountsAsHouseHold(String userOneEmail, String userOnePassword, String userTwoEmail, String userTwoPassword) throws InterruptedException {
        log.info(String.format("Initiating driver setup for the class - %s", className));
        driverConfiguration.setDriverHeadless();

        alertPopUpPage = new AlertPopUpPage(webDriver);

        String pageSource = AlexaAmazonWebsiteUtil.getAlexaAmazonWebsitePageSource(AlexaAmazonWebsiteURLs.ALEXA_AMAZON_API_URL, userTwoEmail, userTwoPassword, webDriver);
        log.info(String.format("Page Source from getAlexaAmazonWebsitePageSource Method - %s", pageSource));

        if (pageSource.equalsIgnoreCase(EMAIL_INCORRECT) || pageSource.equalsIgnoreCase(PASSWORD_INCORRECT)) {
            return pageSource;
        }

        String userTwoFullName = AlexaAmazonWebsiteUtil.getStringFromJSON(pageSource, FULL_NAME);
        log.info(String.format("User Two Full Name from getStringFromJSON Method - %s", userTwoFullName));

        log.info(String.format("Opening the URL on the chrome browser - %s", AmazonWebsiteURLs.AMAZON_URL));
        driverConfiguration.getURL(AmazonWebsiteURLs.AMAZON_URL);

        AmazonWebsiteUtil.signOutAmazonWebsite(webDriver);

        Thread.sleep(2000);
        log.info(String.format("Opening the Amazon My Household URL on the chrome browser - %s", AmazonWebsiteURLs.AMAZON_MY_HOUSEHOLD_URL));
        driverConfiguration.getURL(AmazonWebsiteURLs.AMAZON_MY_HOUSEHOLD_URL);

        AmazonWebsiteUtil.signInAmazonWebsite(webDriver, userOneEmail, userOnePassword);

        log.info("Handling alert pages after AmazonSignInPage");
        alertPopUpPage.clickSkipButton();

        String inviteTokenId = AmazonWebsiteHouseHoldUtil.inviteAdult(webDriver, userTwoFullName, userTwoEmail);

        AmazonWebsiteUtil.signOutAmazonWebsite(webDriver);

        Thread.sleep(2000);
        log.info(String.format("Opening the Amazon Accept Adult URL on the chrome browser - %s", AmazonWebsiteURLs.AMAZON_ACCEPT_ADULT_URL));
        driverConfiguration.getURL(AmazonWebsiteURLs.AMAZON_ACCEPT_ADULT_URL + inviteTokenId);

        AmazonWebsiteHouseHoldUtil.clickContinueToJoinHouseHold();

        AmazonWebsiteUtil.signInAmazonWebsite(webDriver, userTwoEmail, userTwoPassword);

        log.info("Handling alert pages after AmazonSignInPage");
        alertPopUpPage.clickSkipButton();

        String confirmationText = AmazonWebsiteHouseHoldUtil.acceptAdultAndGetConfirmationText();
        driverConfiguration.quitDriver();
        return confirmationText;
    }
}
