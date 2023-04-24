package com.amazon.alexa.comms.async.utilities;

import com.amazon.alexa.comms.async.pages.AmazonWebsiteHouseholdPage;
import lombok.extern.java.Log;
import org.openqa.selenium.WebDriver;

@Log
public class AmazonWebsiteHouseHoldUtil {

    static AmazonWebsiteHouseholdPage amazonWebsiteHouseholdPage;

    public static String inviteAdult(WebDriver webDriver, String userTwoFullName, String userTwoEmail){
        amazonWebsiteHouseholdPage = new AmazonWebsiteHouseholdPage(webDriver);

        log.info("Clicking on Add Adult button to Create your Household now");
        amazonWebsiteHouseholdPage.clickAddAdultButton();

        log.info("Inviting someone you live with to share benefits");
        amazonWebsiteHouseholdPage.inviteAdult(userTwoFullName, userTwoEmail);

        log.info("Clicking Agree and continue to Share your wallet to verify you live together");
        amazonWebsiteHouseholdPage.clickContinueButton();

        log.info("Clicking Continue to Choose what digital content you wish to share with");
        amazonWebsiteHouseholdPage.clickContinueButton();

        log.info(String.format("Clicking Continue to Send your invite to %1$s(%2$s)", userTwoFullName, userTwoEmail));
        amazonWebsiteHouseholdPage.clickContinueButton();

        log.info("Clicking Manage Invite to fetch the invite tokenId");
        amazonWebsiteHouseholdPage.clickManageInviteButton();

        String tokenId = AmazonWebsiteUtil.getTokenId(amazonWebsiteHouseholdPage.getCurrentURL());
        log.info(String.format("Token id for the invite adult request - %s", tokenId));

        return tokenId;
    }

    public static void clickContinueToJoinHouseHold(){
        log.info("Clicking Continue to join Amazon Household");
        amazonWebsiteHouseholdPage.clickContinueLinkButton();
    }

    public static String acceptAdultAndGetConfirmationText(){
        log.info("Clicking Agree and continue to Share your wallet to verify you live together");
        amazonWebsiteHouseholdPage.clickContinueButton();

        log.info("Clicking Continue to Choose what digital content you wish to share with");
        amazonWebsiteHouseholdPage.clickContinueButton();

        log.info(String.format("Status - %s", amazonWebsiteHouseholdPage.getConfirmationText()));
        return amazonWebsiteHouseholdPage.getConfirmationText();
    }
}
