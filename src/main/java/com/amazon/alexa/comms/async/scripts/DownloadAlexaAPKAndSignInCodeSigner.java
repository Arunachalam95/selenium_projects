package com.amazon.alexa.comms.async.scripts;


import com.amazon.alexa.comms.async.constants.CodeSignerWebsiteURLs;
import com.amazon.alexa.comms.async.driver.DriverConfiguration;
import com.amazon.alexa.comms.async.pages.AlexaAPKBuildDownloadPage;
import com.amazon.alexa.comms.async.pages.CodeSignerWebsitePage;

import com.amazon.alexa.comms.async.utilities.AlexaAPKBuildsUtil;
import lombok.extern.java.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.amazon.alexa.comms.async.scripts.AmazonAccountEffectiveMarketPlaceId.driverConfiguration;

@Log
public class DownloadAlexaAPKAndSignInCodeSigner extends DriverConfiguration {

    List<String> signedLinks = new ArrayList<>();

    List<String> urlList;
    List<String> apkFilePathList;
    List<String> apkFileNamesList;
    List<String> codeSignerFileNamesList;
    String pageSource;
    String replaceURL;
    String errorText;
    String getFileName;
    String getDownloadLink;

    int getRowIndex;

    AlexaAPKBuildsUtil alexaAPKBuildsUtil = new AlexaAPKBuildsUtil();

    public List<String> downloadAndSignApk(String buildNumber, String branchName, String environmentAndVersion, String fireOSApkType) throws IOException, InterruptedException {

        log.info(String.format("Initiating driver setup for the class - %s", "DownloadAlexaAPKAndSignInCodeSigner"));
        driverConfiguration.setDriverHeadless(alexaAPKBuildsUtil.createFolder(branchName + "_" + buildNumber));

        AlexaAPKBuildDownloadPage alexaAPKBuildDownloadPage = new AlexaAPKBuildDownloadPage(webDriver);
        CodeSignerWebsitePage codeSignerWebsitePage = new CodeSignerWebsitePage(webDriver);

        urlList = alexaAPKBuildsUtil.getURLs(environmentAndVersion, buildNumber);
        log.info(String.format("URLs to download the Alexa App build - %s", urlList));

        for (int urlCount = 0; urlCount < urlList.size(); urlCount++) {
            log.info(String.format("Getting URL from the list - %s", urlList.get(urlCount)));
            if (urlList.get(urlCount).contains("Phoenix")) {
                replaceURL = urlList.get(urlCount).replace("release", fireOSApkType);
                log.info(String.format("Opening the URL on the chrome browser - %s", replaceURL));
                driverConfiguration.getURL(replaceURL);
            } else {
                log.info(String.format("Opening the URL on the chrome browser - %s", urlList.get(urlCount)));
                driverConfiguration.getURL(urlList.get(urlCount));
            }

            pageSource = webDriver.getPageSource();
            log.info(String.format("PageSource of the opened URL on the chrome browser - %s", urlList.get(urlCount)));

            if (pageSource.contains("Build Artifacts")) {
                log.info(String.format("Clicking download debug signed option in the URL(%s) by checking the pageSource contains Build Artifacts ", urlList.get(urlCount)));
                alexaAPKBuildDownloadPage.clickDownloadDebugSigned();
            } else if (pageSource.contains("Page Not Found")) {
                errorText = "Package build artifact doesn't exist";
                log.info(String.format("PageSource returns \"Page Not Found\" with error - %s", errorText));
                urlList.remove(urlCount);
            }
        }
        if(urlList.size() > 0) {
            log.info(String.format("Checking the folder to get the status of build downloads - %s", alexaAPKBuildsUtil.isFilesExist(urlList.size())));

            apkFilePathList = alexaAPKBuildsUtil.replaceAndGetApkNames(branchName, buildNumber);
            log.info(String.format("Size of the apkNamesList - %s", apkFilePathList.size()));

            apkFileNamesList = alexaAPKBuildsUtil.getFileNames();
            log.info(String.format("Size of the apkFileNamesList - %s", apkFileNamesList.size()));

            for (int apkNamesCount = 0; apkNamesCount < apkFilePathList.size(); apkNamesCount++) {

                webDriver.get(CodeSignerWebsiteURLs.URL);

                log.info(String.format("Selecting AlexaMobileAndroid option for the Apk - %s", apkFilePathList.get(apkNamesCount)));
                codeSignerWebsitePage.selectAlexaMobileAndroid();

                log.info(String.format("Sending file path to the browse button - %s", apkFilePathList.get(apkNamesCount)));
                codeSignerWebsitePage.sendFilePathToBrowseButton(apkFilePathList.get(apkNamesCount));

                log.info("Clicking signItButton to sign the builds");
                codeSignerWebsitePage.clickSignItButton();
            }

            log.info("Waiting for 60 secs to sign the uploaded builds");
            Thread.sleep(1 * 60 * 1000);

            webDriver.get(CodeSignerWebsiteURLs.SEARCH_URL);

            codeSignerFileNamesList = codeSignerWebsitePage.getFileNameText();
            log.info(String.format("Size of the apkFileNamesList - %s", codeSignerFileNamesList.size()));

            for (int listIndex = 0; listIndex < apkFileNamesList.size(); listIndex++) {
                getFileName = apkFileNamesList.get(listIndex);
                getRowIndex = codeSignerFileNamesList.indexOf(getFileName);

                log.info(String.format("Row Index for the file name (%1$s) - %2$s", getFileName, getRowIndex));
                getDownloadLink = codeSignerWebsitePage.getDownloadLink(getRowIndex);

                log.info(String.format("Adding the filename (%1$s) and the download link (%2$s) in the list", getFileName, getDownloadLink));
                signedLinks.add("\"" + getFileName + " - " + getDownloadLink + "\"");
            }
        }else {
            signedLinks.add("\"" + buildNumber + "_" + environmentAndVersion + " - " + "No builds found for the given artifact" + "\"");
        }

        log.info("Deleting the created folder");
        alexaAPKBuildsUtil.deleteFolder();
        driverConfiguration.quitDriver();
        return signedLinks;
    }
}

