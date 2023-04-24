package com.amazon.alexa.comms.async.scripts;

import com.amazon.alexa.comms.async.driver.DriverConfiguration;
import com.amazon.alexa.comms.async.pages.*;
import com.amazon.alexa.comms.async.utilities.AmazonWebsiteUtil;
import lombok.extern.java.Log;
import org.openqa.selenium.JavascriptExecutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.amazon.alexa.comms.async.constants.AmazonWebsiteURLs.*;
import static com.amazon.alexa.comms.async.constants.StringConstants.*;
import static com.amazon.alexa.comms.async.scripts.AmazonAccountEffectiveMarketPlaceId.driverConfiguration;
@Log
public class AccountCreation extends DriverConfiguration {

    double waitTimeInSecs = 0;
    String OTP;

    AmazonWebsiteSignInPage amazonWebsiteSignInPage;
    AmazonWebsiteHomePage amazonWebsiteHomePage;
    AmazonWebsitePreferencesPage amazonWebsitePreferencesPage;
    AmazonWebsiteCreateAccountPage amazonWebsiteCreateAccountPage;
    OutlookWebsitePage outlookWebsitePage;
    AlertPopUpPage alertPopUpPage;
    ArrayList<String> browserTabs;
    public String createTestAccounts(String email, String password, int startSeq, int endSeq, String preferredMarketPlace) throws InterruptedException {
        log.info(String.format("Initiating driver setup for the class - %s", "className"));
        driverConfiguration.setDriver();

        amazonWebsiteSignInPage = new AmazonWebsiteSignInPage(webDriver);
        amazonWebsiteHomePage = new AmazonWebsiteHomePage(webDriver);
        amazonWebsitePreferencesPage = new AmazonWebsitePreferencesPage(webDriver);
        amazonWebsiteCreateAccountPage = new AmazonWebsiteCreateAccountPage(webDriver);
        outlookWebsitePage = new OutlookWebsitePage(webDriver);
        alertPopUpPage = new AlertPopUpPage(webDriver);

        openURLAndNavigateToSignInPage(preferredMarketPlace);

        for(int start = startSeq; start <= endSeq; start++) {
            String userEmail = email + start + "@amazon.com";
            log.info(String.format("Clicking create your account button for the account - %s", email));
            amazonWebsiteSignInPage.clickCreateYourAccountButton();

            log.info(String.format("Filling the account details {name - %1$s, email - %2$s, password - %3$s and preferredMarketPlace - %4$s}",
                    getUserName(), email, password, preferredMarketPlace));
            amazonWebsiteCreateAccountPage.fillAccountDetails(getUserName(), userEmail, password, preferredMarketPlace);

            log.info(("Validating the account details"));
            if(amazonWebsiteCreateAccountPage.isExistingAccount()){
                openURLAndNavigateToSignInPage(preferredMarketPlace);
                return EXISTING_ACCOUNT;
            } else if (!amazonWebsiteCreateAccountPage.isNewAccount()) {
                return "Verify email address page is not visible";
            }

            switchBetweenBrowserTabs(1); //Switching to outlook browser tab

            openOutlookAndNavigateToOTPFolder();

            if(outlookWebsitePage.verifyEmailsInOTPFolder()){
                getOTPAndCloseBrowser(userEmail);
            }

            amazonWebsiteCreateAccountPage.enterOTPAndClickSubmitButton(OTP);

        }

        return "";
    }

    public void openURLAndNavigateToSignInPage(String preferredMarketPlace){
        log.info(String.format("Opening the URL on the chrome browser - %s", AmazonWebsiteUtil.getRequestedURL(preferredMarketPlace)));
        driverConfiguration.getURL(AmazonWebsiteUtil.getRequestedURL(preferredMarketPlace));

        log.info("Handling alert pages before AmazonSignInPage");
        alertPopUpPage.clickSkipButton();

        log.info("Navigating to SignIn Page to create new account");
        amazonWebsiteHomePage.clickHamburgerMenu();
        amazonWebsiteHomePage.clickCustomerNameInHamburgerMenu();
    }

    public String getUserName(){
        log.info("Getting random user name from the list");
        List<String> firstName = Arrays.asList("John", "William", "James", "Thomas", "George", "Joseph", "Samuel", "Henry", "David", "Daniel",
                "Mary", "Elizabeth", "Sarah", "Nancy", "Catherine", "Margaret", "Jane", "Susan", "Hannah", "Martha");
        Collections.shuffle(firstName);
        List<String> lastName = Arrays.asList("Smith", "Brown", "Miller", "Johnson", "Jones", "Davis", "Wilson", "Clark", "Taylor");
        Collections.shuffle(lastName);
        return firstName.get(0) + " " + lastName.get(1);
    }

    public void switchBetweenBrowserTabs(int browserTabIndex){
        log.info(String.format("Switching to browser tab index - %s", browserTabIndex));
        ((JavascriptExecutor) webDriver).executeScript("window.open()");
        browserTabs = new ArrayList<String>(webDriver.getWindowHandles());
        webDriver.switchTo().window(browserTabs.get(browserTabIndex));
    }

    public void getOTPAndCloseBrowser(String userEmail) throws InterruptedException {
        log.info(String.format("Verifying OTP is received for the account - %s", userEmail));
        while(!outlookWebsitePage.isOTPReceived(userEmail) || waitTimeInSecs / 60 < 6.0) {
            log.info("Waiting for 10 seconds to receive OTP in mail");
            Thread.sleep(10000);
            waitTimeInSecs = waitTimeInSecs + 10;

            if (waitTimeInSecs / 60 == 2.0 || waitTimeInSecs / 60 == 4.0 || waitTimeInSecs / 60 == 6.0) {
                switchTabAndClickResendOTP();
            }
            log.info(String.format("Verifying OTP is received for the account after - %s Secs", waitTimeInSecs));
            outlookWebsitePage.isOTPReceived(userEmail);
        }
        waitTimeInSecs = 0;

        OTP = outlookWebsitePage.getOTP();

        log.info(String.format("Deleting the OTP email for the account - %s", userEmail));
        outlookWebsitePage.deleteOTPEmail();
    }

    public void openOutlookAndNavigateToOTPFolder(){
        webDriver.get(OUTLOOK_URL);
        webDriver.get(OUTLOOK_URL);

        log.info("Navigating to OTP Folder in the outlook");
        outlookWebsitePage.clickOTPFolder();
    }

    public void switchTabAndClickResendOTP(){
        log.info("Switching back to amazon website to click on resend OTP");
        webDriver.switchTo().window(browserTabs.get(0));
        amazonWebsiteCreateAccountPage.clickResendOTP();

        log.info("Switching back to outlook to check OTP is received");
        webDriver.switchTo().window(browserTabs.get(1));
    }
}
