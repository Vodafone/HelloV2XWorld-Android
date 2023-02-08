package com.vodafone.v2x.android.hellov2xworld.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.vodafone.v2x.sdk.android.facade.enums.StationType;
/**
 * Parameters class provides an interface to access the Vodafone V2X related preferences, including the acceptance of terms and conditions, the type of the station, and the application ID and token.
 * The class uses Android's SharedPreferences to store and retrieve the values.
 * The class is a singleton and should be obtained through the static method `getInstance`.
 */
public class Parameters {
    private static final StationType DEFAULT_STATION_TYPE = StationType.PASSENGER_CAR;
    private static final String DEFAULT_APPLICATION_ID = "a74432c1-bca5-4f54-a4c3-5786699ce5ff";
    private static final String DEFAULT_APPLICATION_TOKEN = "6504d612-67a9-43fd-b362-caaca3b25acf";
    private static final String CAM_SUBSCRIBE_GROUP = "510298_1";
    private static final String CAM_PUBLISH_GROUP = "510298_1";
    private static SharedPreferences sharedPref;
    private static SharedPreferences.Editor editor;
    final private String termsAndConditionsKey = "com.vodafone.v2x.android.hellov2xworld.TERMS_CONDITIONS_ACCEPTED";
    final private String stationTypeKey = "com.vodafone.v2x.android.hellov2xworld.STATION_TYPE";
    final private String applicationIdKey = "com.vodafone.v2x.android.hellov2xworld.APPLICATION_ID";
    final private String applicationTokenKey = "com.vodafone.v2x.android.hellov2xworld.APPLICATION_TOKEN";

    private Parameters(Context context) {
        sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
    }
    /**
     * Returns a singleton instance of the Parameters class.
     *
     * @param context the Android context
     * @return a singleton instance of the Parameters class
     */
    public static Parameters getInstance(Context context) {
        return new Parameters(context);
    }
    /**
     * Returns the acceptance status of the terms and conditions.
     *
     * @return the acceptance status of the terms and conditions
     */
    public boolean getTermsAndConditionsAccepted() {
        return sharedPref.getBoolean(termsAndConditionsKey, false);
    }
    /**
     * Sets the acceptance status of the terms and conditions.
     *
     * @param areTermsAndConditionsAccepted the acceptance status of the terms and conditions
     * @return true if the value is successfully written to SharedPreferences
     */
    public boolean setTermsAndConditionsAccepted(boolean areTermsAndConditionsAccepted) {
        editor = sharedPref.edit();
        editor.putBoolean(termsAndConditionsKey, areTermsAndConditionsAccepted);
        return editor.commit();
    }
    /**
     * Returns the type of the station.
     *
     * @return the type of the station
     */
    public StationType getStationType() {
        return StationType.valueOf(sharedPref.getString(stationTypeKey, DEFAULT_STATION_TYPE.toString()));
    }
    /**

     Set the type of the station.
     @param stationType the type of the station
     @return true if the value is successfully written to SharedPreferences
     */
    public boolean setStationType(StationType stationType) {
        editor = sharedPref.edit();
        editor.putString(stationTypeKey, stationType.toString());
        return editor.commit();
    }
    /**

     Get the application ID.
     @return the application ID
     */
    public String getApplicationID() {
        return sharedPref.getString(applicationIdKey, DEFAULT_APPLICATION_ID);
    }
    /**

     Set the application ID.
     @param applicationId the application ID
     @return true if the value is successfully written to SharedPreferences
     */
    public boolean setApplicationId(String applicationId) {
        editor = sharedPref.edit();
        editor.putString(applicationIdKey, applicationId);
        return editor.commit();
    }
    /**

     Get the application token.
     @return the application token
     */
    public String getApplicationToken() {
        return sharedPref.getString(applicationTokenKey, DEFAULT_APPLICATION_TOKEN);
    }
    /**

     Set the application token.
     @param applicationToken the application token
     @return true if the value is successfully written to SharedPreferences
     */
    public boolean setApplicationToken(String applicationToken) {
        editor = sharedPref.edit();
        editor.putString(applicationTokenKey, applicationToken);
        return editor.commit();
    }
    /**

     Get the CAM subscribe group.
     @return the CAM subscribe group
     */
    public String getCamSubscribeGroup() {
        return CAM_SUBSCRIBE_GROUP;
    }
    /**

     Get the CAM publish group.
     @return the CAM publish group
     */
    public String getCamPublishGroup() {
        return CAM_PUBLISH_GROUP;
    }
}
