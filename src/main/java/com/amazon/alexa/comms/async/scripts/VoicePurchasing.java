package com.amazon.alexa.comms.async.scripts;

import com.amazon.alexa.comms.async.driver.DriverConfiguration;
import com.amazon.alexa.comms.async.pages.VoicePurchasingPage;
import lombok.extern.java.Log;

import static com.amazon.alexa.comms.async.constants.AlexaAmazonWebsiteURLs.*;
import static com.amazon.alexa.comms.async.constants.StringConstants.RESULT;
import static com.amazon.alexa.comms.async.scripts.AmazonAccountEffectiveMarketPlaceId.driverConfiguration;
import static com.amazon.alexa.comms.async.utilities.AlexaAmazonWebsiteUtil.isLoginSuccessful;
import static com.amazon.alexa.comms.async.utilities.AlexaAmazonWebsiteUtil.signInAlexaAmazonWebsite;

@Log
public class VoicePurchasing extends DriverConfiguration {

    public String voicePurchasingToggle(String userEmail, String userPassword, String toggleType) {
        log.info(String.format("Initiating driver setup for the class - %s", "className"));
        driverConfiguration.setDriverHeadless();

        VoicePurchasingPage voicePurchasingPage = new VoicePurchasingPage(webDriver);

        log.info(String.format("Opening the URL on the chrome browser - %s", ALEXA_AMAZON_URL));
        driverConfiguration.getURL(ALEXA_AMAZON_URL);

        signInAlexaAmazonWebsite(userEmail, userPassword, webDriver);

        if(isLoginSuccessful()){
            return RESULT;
        }

        log.info(String.format("Opening the URL on the chrome browser - %s", ALEXA_AMAZON_VOICE_PURCHASING));
        driverConfiguration.getURL(ALEXA_AMAZON_VOICE_PURCHASING);

        voicePurchasingPage.dismissMessageToast();
        boolean toggleStatus = voicePurchasingPage.isToggleEnabled();
        if (toggleType.equalsIgnoreCase("Check")) {
            if(toggleStatus) {
                log.info(String.format("Voice Purchasing toggle is already enabled - %s", toggleStatus));
                RESULT = "Voice Purchasing toggle is already enabled";
            }else{
                log.info(String.format("Clicking the toggle to enable voice purchasing - %s", toggleStatus));
                voicePurchasingPage.clickToggle();
                RESULT = "Voice Purchasing toggle is enabled";
            }
        } else if(toggleType.equalsIgnoreCase("Disable")){
            if(!toggleStatus) {
                log.info(String.format("Voice Purchasing toggle is already disabled - %s", toggleStatus));
                RESULT = "Voice Purchasing toggle is already disabled";
            }else{
                log.info(String.format("Clicking the toggle to disable voice purchasing - %s", toggleStatus));
                voicePurchasingPage.clickToggle();
                RESULT = "Voice Purchasing toggle is disabled";
            }
        }
        driverConfiguration.quitDriver();
        return RESULT;
    }
}
