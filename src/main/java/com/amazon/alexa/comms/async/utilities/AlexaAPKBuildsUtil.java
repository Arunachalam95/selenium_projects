package com.amazon.alexa.comms.async.utilities;


import com.amazon.alexa.comms.async.constants.ElectricCompanyWebsiteURLs;
import com.amazon.alexa.comms.async.constants.StringConstants;
import lombok.extern.java.Log;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.amazon.alexa.comms.async.constants.StringConstants.*;

@Log
public class AlexaAPKBuildsUtil {

    public ArrayList<String> urlsList = new ArrayList<>();
    public ArrayList<String> apkFilePath = new ArrayList<>();
    public ArrayList<String> apkFileNames = new ArrayList<>();
    public int addFileCount;
    public File apkFile;
    public File[] apkFilesInDirectory;
    public String folderPath;
    public String apkFileName;
    public String apkReplacedFileName;

    public String createFolder(String folderName) {
        folderPath = String.format(StringConstants.ALEXA_BUILD_DOWNLOAD_PATH, folderName);
        log.info(String.format("Creating a folder to download the builds - %s", folderPath));
        apkFile = new File(folderPath);
        boolean folderStatus = apkFile.mkdir();
        if (folderStatus) {
            System.out.println(String.format("Folder created with the name - %s", folderName));
        } else {
            System.out.println(String.format("Folder not created on the path - %s", folderPath));
        }
        return apkFile.getAbsolutePath().toString();
    }

    public boolean isFilesExist(int filesCount) {
        log.info(String.format("List of files (%1$s) in the folder path - %2$s", filesCount, folderPath));
        while (addFileCount < filesCount) {
            apkFilesInDirectory = apkFile.listFiles();
            addFileCount = 0;
            for (File apkFileInDirectory : apkFilesInDirectory) {
                if (apkFileInDirectory.getName().endsWith((".apk"))) {
                    addFileCount++;
                }
            }
        }
        return true;
    }

    public void deleteFolder() throws IOException {
        log.info("Deleting the folder after signing the apk's");
        FileUtils.deleteDirectory(new File(folderPath));
    }

    public List<String> replaceAndGetApkNames(String branchName, String buildNumber) {
        log.info(String.format("Replacing the apk Name with branch name (%1$s) and build number - %2$s", branchName, buildNumber));
        for (File apkFileInDirectory : apkFilesInDirectory) {
            apkFileName = apkFileInDirectory.getName();
            switch (apkFileName) {
                case ALEXA_APK_32_ARTIFACT:
                    apkReplacedFileName = apkFile.getPath() + File.separator + apkFileName.replace("AlexaMobileAndroid-prod-armeabi-v7a-release", "Prod_" + branchName + "_32_" + buildNumber);
                    break;
                case ALEXA_APK_64_ARTIFACT:
                    apkReplacedFileName = apkFile.getPath() + File.separator + apkFileName.replace("AlexaMobileAndroid-prod-arm64-v8a-release", "Prod_" + branchName + "_64_" + buildNumber);
                    break;
                case ALEXA_APK_FIRE_OS_ARTIFACT:
                    apkReplacedFileName = apkFile.getPath() + File.separator + apkFileName.replace("AlexaMobileAndroid-prodPhoenix-armeabi-v7a-release", "Prod_" + branchName + "_FireOS_release_" + buildNumber);
                    break;
                case ALEXA_GAMMA_APK_32_ARTIFACT:
                    apkReplacedFileName = apkFile.getPath() + File.separator + apkFileName.replace("AlexaMobileAndroid-gamma-armeabi-v7a-release", "Gamma_" + branchName + "_32_" + buildNumber);
                    break;
                case ALEXA_GAMMA_APK_64_ARTIFACT:
                    apkReplacedFileName = apkFile.getPath() + File.separator + apkFileName.replace("AlexaMobileAndroid-gamma-arm64-v8a-release", "Gamma_" + branchName + "_64_" + buildNumber);
                    break;
                case ALEXA_GAMMA_APK_FIRE_OS_ARTIFACT:
                    apkReplacedFileName = apkFile.getPath() + File.separator + apkFileName.replace("AlexaMobileAndroid-gammaPhoenix-armeabi-v7a-release", "Gamma_" + branchName + "_FOS_" + buildNumber);
                    break;
                case ALEXA_APK_FIRE_OS_DEV_ARTIFACT:
                    apkReplacedFileName = apkFile.getPath() + File.separator + apkFileName.replace("AlexaMobileAndroid-prodPhoenix-armeabi-v7a-development", "Prod_" + branchName + "_FOS_" + buildNumber);
                    break;
                case ALEXA_GAMMA_APK_FIRE_OS_DEV_ARTIFACT:
                    apkReplacedFileName = apkFile.getPath() + File.separator + apkFileName.replace("AlexaMobileAndroid-gammaPhoenix-armeabi-v7a-development", "Gamma_" + branchName + "_FOS_" + buildNumber);
                    break;
            }
            apkFileInDirectory.renameTo(new File(apkReplacedFileName));
            apkFileNames.add(apkReplacedFileName.substring(apkReplacedFileName.lastIndexOf(File.separator)).replace(File.separator, ""));
            apkFilePath.add(apkReplacedFileName.replace(File.separator, File.separator + File.separator));
        }
        return apkFilePath;
    }


    public List<String> getURLs(String apkVersionAndEnvironment, String buildNumber) {
        urlsList.clear();
        switch (apkVersionAndEnvironment) {
            case "All":
                urlsList.add(String.format(ElectricCompanyWebsiteURLs.PROD_32_APK, buildNumber));
                urlsList.add(String.format(ElectricCompanyWebsiteURLs.PROD_64_APK, buildNumber));
                urlsList.add(String.format(ElectricCompanyWebsiteURLs.PROD_FIRE_OS_APK, buildNumber));
                urlsList.add(String.format(ElectricCompanyWebsiteURLs.GAMMA_32_APK, buildNumber));
                urlsList.add(String.format(ElectricCompanyWebsiteURLs.GAMMA_64_APK, buildNumber));
                urlsList.add(String.format(ElectricCompanyWebsiteURLs.GAMMA_FIRE_OS_APK, buildNumber));
                break;
            case "Prod":
                urlsList.add(String.format(ElectricCompanyWebsiteURLs.PROD_32_APK, buildNumber));
                urlsList.add(String.format(ElectricCompanyWebsiteURLs.PROD_64_APK, buildNumber));
                urlsList.add(String.format(ElectricCompanyWebsiteURLs.PROD_FIRE_OS_APK, buildNumber));
                break;
            case "Gamma":
                urlsList.add(String.format(ElectricCompanyWebsiteURLs.GAMMA_32_APK, buildNumber));
                urlsList.add(String.format(ElectricCompanyWebsiteURLs.GAMMA_64_APK, buildNumber));
                urlsList.add(String.format(ElectricCompanyWebsiteURLs.GAMMA_FIRE_OS_APK, buildNumber));
                break;
            case "Prod_Android":
                urlsList.add(String.format(ElectricCompanyWebsiteURLs.PROD_32_APK, buildNumber));
                urlsList.add(String.format(ElectricCompanyWebsiteURLs.PROD_64_APK, buildNumber));
                break;
            case "Gamma_Android":
                urlsList.add(String.format(ElectricCompanyWebsiteURLs.GAMMA_32_APK, buildNumber));
                urlsList.add(String.format(ElectricCompanyWebsiteURLs.GAMMA_64_APK, buildNumber));
                break;
            case "Prod_Gamma_32":
                urlsList.add(String.format(ElectricCompanyWebsiteURLs.PROD_32_APK, buildNumber));
                urlsList.add(String.format(ElectricCompanyWebsiteURLs.GAMMA_32_APK, buildNumber));
                break;
            case "Prod_Gamma_64":
                urlsList.add(String.format(ElectricCompanyWebsiteURLs.PROD_64_APK, buildNumber));
                urlsList.add(String.format(ElectricCompanyWebsiteURLs.GAMMA_64_APK, buildNumber));
                break;
            case "Prod_Gamma_FireOS":
                urlsList.add(String.format(ElectricCompanyWebsiteURLs.PROD_FIRE_OS_APK, buildNumber));
                urlsList.add(String.format(ElectricCompanyWebsiteURLs.GAMMA_FIRE_OS_APK, buildNumber));
                break;
            case "Prod_32":
                urlsList.add(String.format(ElectricCompanyWebsiteURLs.PROD_32_APK, buildNumber));
                break;
            case "Gamma_32":
                urlsList.add(String.format(ElectricCompanyWebsiteURLs.GAMMA_32_APK, buildNumber));
                break;
            case "Prod_64":
                urlsList.add(String.format(ElectricCompanyWebsiteURLs.PROD_64_APK, buildNumber));
                break;
            case "Gamma_64":
                urlsList.add(String.format(ElectricCompanyWebsiteURLs.GAMMA_64_APK, buildNumber));
                break;
            case "Prod_FireOS":
                urlsList.add(String.format(ElectricCompanyWebsiteURLs.PROD_FIRE_OS_APK, buildNumber));
                break;
            case "Gamma_FireOS":
                urlsList.add(String.format(ElectricCompanyWebsiteURLs.GAMMA_FIRE_OS_APK, buildNumber));
                break;
        }
        return urlsList;
    }

    public List<String> getFileNames() {
        return apkFileNames;
    }
}
