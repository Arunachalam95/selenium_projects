package com.amazon.alexa.comms.async.constants;

import java.io.File;

public class StringConstants {

    public static String CURRENT_USER_HOME_DIR = System.getProperty("user.home");
    public static String CURRENT_DATE_TIME = String.valueOf(java.time.LocalDateTime.now());
    public static String ALEXA_BUILD_DOWNLOAD_PATH = CURRENT_USER_HOME_DIR + File.separator + "%s" + "_" + CURRENT_DATE_TIME.replace(':', '-');
    public static String ALEXA_APP_BUILDS_FILE_PATH = CURRENT_USER_HOME_DIR + File.separator + "Downloads" + File.separator + "Build Signer" + File.separator;

    //Artifact Name with extension
    public static final String ALEXA_APK_32_ARTIFACT = "AlexaMobileAndroid-prod-armeabi-v7a-release.apk";
    public static final String ALEXA_APK_64_ARTIFACT = "AlexaMobileAndroid-prod-arm64-v8a-release.apk";
    public static final String ALEXA_APK_FIRE_OS_ARTIFACT = "AlexaMobileAndroid-prodPhoenix-armeabi-v7a-release.apk";
    public static final String ALEXA_GAMMA_APK_32_ARTIFACT = "AlexaMobileAndroid-gamma-armeabi-v7a-release.apk";
    public static final String ALEXA_GAMMA_APK_64_ARTIFACT = "AlexaMobileAndroid-gamma-arm64-v8a-release.apk";
    public static final String ALEXA_GAMMA_APK_FIRE_OS_ARTIFACT = "AlexaMobileAndroid-gammaPhoenix-armeabi-v7a-release.apk";
    public static final String ALEXA_APK_FIRE_OS_DEV_ARTIFACT = "AlexaMobileAndroid-prodPhoenix-armeabi-v7a-development.apk";
    public static final String ALEXA_GAMMA_APK_FIRE_OS_DEV_ARTIFACT = "AlexaMobileAndroid-gammaPhoenix-armeabi-v7a-development.apk";

    //Login warning text
    public static final String EMAIL_INCORRECT = "We cannot find an account with that email address";
    public static final String PASSWORD_INCORRECT = "Your password is incorrect";
    public static final String INTERNAL_FAILURE = "<InternalFailure/>";
    
    //Alexa Amazon Website Strings
    public static String PAGE_SOURCE = null;
    public static String RESULT = null;
    public static final String EFFECTIVE_MARKETPLACE_ID = "effectiveMarketPlaceId";
    public static final String COUNTRY_OF_RESIDENCE = "countryOfResidence";
    public static final String FULL_NAME = "fullName";

    public static final String EXISTING_ACCOUNT = "Email address already in use";
}
