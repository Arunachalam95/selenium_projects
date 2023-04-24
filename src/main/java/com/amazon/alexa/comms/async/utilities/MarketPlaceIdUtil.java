package com.amazon.alexa.comms.async.utilities;

public class MarketPlaceIdUtil {

    String preferredMarketplace;
    public String getEffectiveMarketPlaceId(String effectiveMarketPlaceId)
    {
        switch(effectiveMarketPlaceId)
        {
            case "ATVPDKIKX0DER":
                preferredMarketplace = "US";
                break;
            case "A1F83G8C2ARO7P":
                preferredMarketplace = "GB";
                break;
            case "A1PA6795UKMFR9":
                preferredMarketplace = "DE";
                break;
            case "A13V1IB3VIYZZH":
                preferredMarketplace = "FR";
                break;
            case "A1RKKUPIHCS9HS":
                preferredMarketplace = "ES";
                break;
            case "A21TJRUUN4KGV":
                preferredMarketplace = "IN";
                break;
            case "APJ6JRA9NG5V4":
                preferredMarketplace = "IT";
                break;
            case "A1VC38T7YXB528":
                preferredMarketplace = "JP";
                break;
            case "A2EUQ1WTGCTBG2":
                preferredMarketplace = "CA";
                break;
            case "AAHKV2X7AFYLW":
                preferredMarketplace = "CN";
                break;
            case "A2Q3Y263D00KWC":
                preferredMarketplace = "BR";
                break;
            case "A1AM78C64UM0Y8":
                preferredMarketplace = "MX";
                break;
            case "A39IBJ37TRP1C6":
                preferredMarketplace = "AU";
                break;
            case "AD2EMQ3L3PG8S":
                preferredMarketplace = "RU";
                break;
            case "A1805IZSGTT6HS":
                preferredMarketplace = "NL";
                break;
            case "A2VIGQ35RCS4UG":
                preferredMarketplace = "AE";
                break;
        }
        return preferredMarketplace;
    }
}
