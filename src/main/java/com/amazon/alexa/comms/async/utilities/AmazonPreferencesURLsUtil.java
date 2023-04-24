package com.amazon.alexa.comms.async.utilities;


import static com.amazon.alexa.comms.async.constants.AmazonWebsitePreferenceURLs.*;

public class AmazonPreferencesURLsUtil {

    public static String getURL;
    public static String getRequestedURL(String Country)
    {
        switch(Country)
        {
            case "US":
                getURL = Amazon_Preferences_URL;
                break;
            case "GB":
                getURL = Amazon_Preferences_URL_UK;
                break;
            case "DE":
                getURL = Amazon_Preferences_URL_DE;
                break;
            case "FR":
                getURL = Amazon_Preferences_URL_FR;
                break;
            case "ES":
                getURL = Amazon_Preferences_URL_ES;
                break;
            case "IN":
                getURL = Amazon_Preferences_URL_IN;
                break;
            case "IT":
                getURL = Amazon_Preferences_URL_IT;
                break;
            case "JP":
                getURL = Amazon_Preferences_URL_JP;
                break;
            case "CA":
                getURL = Amazon_Preferences_URL_CA;
                break;
            case "BR":
                getURL = Amazon_Preferences_URL_BR;
                break;
            case "MX":
                getURL = Amazon_Preferences_URL_MX;
                break;
            case "AU":
                getURL = Amazon_Preferences_URL_AU;
                break;
            case "PL":
                getURL = Amazon_Preferences_URL_PL;
                break;
            case "NL":
                getURL = Amazon_Preferences_URL_NL;
                break;
            case "AE":
                getURL = Amazon_Preferences_URL_AE;
                break;
        }
        return getURL;
    }
}
