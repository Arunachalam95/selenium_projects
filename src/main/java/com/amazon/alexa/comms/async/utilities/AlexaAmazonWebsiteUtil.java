package com.amazon.alexa.comms.async.utilities;

import com.amazon.alexa.comms.async.constants.AlexaAmazonWebsiteURLs;
import com.amazon.alexa.comms.async.constants.StringConstants;
import com.amazon.alexa.comms.async.pages.AlertPopUpPage;
import com.amazon.alexa.comms.async.pages.AlexaAmazonWebsiteSignInPage;
import lombok.extern.java.Log;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;

import static com.amazon.alexa.comms.async.scripts.AmazonAccountEffectiveMarketPlaceId.driverConfiguration;
import static com.amazon.alexa.comms.async.constants.StringConstants.*;

@Log
public class AlexaAmazonWebsiteUtil {

    static AlexaAmazonWebsiteSignInPage alexaAmazonWebsiteSignInPage;
    static AlertPopUpPage alertPopUpPage;

    public static String getAlexaAmazonWebsitePageSource(String URL, String userEmail, String userPassword, WebDriver webDriver) {

        log.info(String.format("Opening the URL on the chrome browser - %s", URL));
        driverConfiguration.getURL(URL);

        signInAlexaAmazonWebsite(userEmail, userPassword, webDriver);

        if(isLoginSuccessful()){
            return PAGE_SOURCE;
        }

        if (alexaAmazonWebsiteSignInPage.checkForInternalFailure(StringConstants.INTERNAL_FAILURE)) {
            log.info(String.format("Opening the URL on the chrome browser due to internal failure - %s", AlexaAmazonWebsiteURLs.ALEXA_AMAZON_API_URL));
            driverConfiguration.getURL(AlexaAmazonWebsiteURLs.ALEXA_AMAZON_API_URL);
        }

        PAGE_SOURCE = driverConfiguration.getPageSource();
        PAGE_SOURCE = PAGE_SOURCE.substring(PAGE_SOURCE.indexOf("{"));
        PAGE_SOURCE = PAGE_SOURCE.substring(0, PAGE_SOURCE.indexOf("}")) + "}";
        log.info(String.format("Page Source - %s", PAGE_SOURCE));
        return PAGE_SOURCE;
    }

    public static String getStringFromJSON(String pageSource, String key) {
        JSONObject jsonObject = new JSONObject(pageSource);
        pageSource = jsonObject.getString(key);
        log.info(String.format("Full name for the user - %s", pageSource));
        return pageSource;
    }

    public static void signInAlexaAmazonWebsite(String userEmail, String userPassword, WebDriver webDriver) {
        alertPopUpPage = new AlertPopUpPage(webDriver);
        alertPopUpPage.clickContinueButton();
        log.info("Signing in Alexa Amazon Website");
        alexaAmazonWebsiteSignInPage = new AlexaAmazonWebsiteSignInPage(webDriver);
        log.info(String.format("Inserting email %1$s, password %2$s, and clicking SignIn button", userEmail, userPassword));
        alexaAmazonWebsiteSignInPage.inputEmail(userEmail);
        alexaAmazonWebsiteSignInPage.inputPassword(userPassword);
        alexaAmazonWebsiteSignInPage.clickSignInButton();
    }

    public static boolean isLoginSuccessful() {
        log.info("Validating successful login in Alexa Amazon Website");
        String warningText = alexaAmazonWebsiteSignInPage.checkForWarningText();
        if (warningText.equalsIgnoreCase(EMAIL_INCORRECT)) {
            RESULT = EMAIL_INCORRECT;
            return true;
        } else if (warningText.equalsIgnoreCase(PASSWORD_INCORRECT)) {
            RESULT = PASSWORD_INCORRECT;
            return true;
        }
        return false;
    }
}
