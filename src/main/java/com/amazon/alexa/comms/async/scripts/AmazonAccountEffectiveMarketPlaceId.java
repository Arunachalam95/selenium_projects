package com.amazon.alexa.comms.async.scripts;

import com.amazon.alexa.comms.async.constants.AlexaAmazonWebsiteURLs;
import com.amazon.alexa.comms.async.driver.DriverConfiguration;
import com.amazon.alexa.comms.async.utilities.AlexaAmazonWebsiteUtil;
import com.amazon.alexa.comms.async.utilities.MarketPlaceIdUtil;
import lombok.extern.java.Log;

import static com.amazon.alexa.comms.async.constants.StringConstants.*;


@Log
public class AmazonAccountEffectiveMarketPlaceId extends DriverConfiguration {

    public static DriverConfiguration driverConfiguration = new DriverConfiguration();
    String pageSource;
    String effectiveMarketPlaceId;
    String countryOfResidence;
    String preferredMarketPlace;
    MarketPlaceIdUtil marketPlaceUtil = new MarketPlaceIdUtil();

    public String getPreferredMarketplace(String userEmail, String userPassword) throws InterruptedException {
        log.info("Initiating driver setup");
        driverConfiguration.setDriverHeadless();

        pageSource = AlexaAmazonWebsiteUtil.getAlexaAmazonWebsitePageSource(AlexaAmazonWebsiteURLs.ALEXA_AMAZON_API_URL, userEmail, userPassword, webDriver);

        if (pageSource.equalsIgnoreCase(EMAIL_INCORRECT)) {
            pageSource = AlexaAmazonWebsiteUtil.getAlexaAmazonWebsitePageSource(AlexaAmazonWebsiteURLs.ALEXA_AMAZON_API_URL_JP, userEmail, userPassword, webDriver);
            if (pageSource.equalsIgnoreCase(EMAIL_INCORRECT) || pageSource.equalsIgnoreCase(PASSWORD_INCORRECT)) {
                return pageSource;
            }
        } else if (pageSource.equalsIgnoreCase(PASSWORD_INCORRECT)) {
            return pageSource;
        }

        effectiveMarketPlaceId = AlexaAmazonWebsiteUtil.getStringFromJSON(pageSource, EFFECTIVE_MARKETPLACE_ID);
        log.info(String.format("effectiveMarketPlaceId - %1$s for the account - %2$s", effectiveMarketPlaceId, userEmail));

        countryOfResidence = AlexaAmazonWebsiteUtil.getStringFromJSON(pageSource, COUNTRY_OF_RESIDENCE);
        log.info(String.format("effectiveMarketPlaceId - %1$s for the account - %2$s", countryOfResidence, userEmail));

        preferredMarketPlace = marketPlaceUtil.getEffectiveMarketPlaceId(effectiveMarketPlaceId);
        log.info(String.format("effectiveMarketPlaceId - %1$s for the account - %2$s", preferredMarketPlace, userEmail));

        driverConfiguration.quitDriver();
        return preferredMarketPlace;
    }
}
