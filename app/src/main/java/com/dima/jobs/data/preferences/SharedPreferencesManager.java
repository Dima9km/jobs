package com.dima.jobs.data.preferences;

import android.content.SharedPreferences;

import com.dima.jobs.data.model.UserProfile;

public class SharedPreferencesManager {

    private final String FIRST_NAME = "first_name";
    private final String PATRONYMIC = "patronymic";
    private final String LAST_NAME = "last_name";
    private final String SEX = "sex";
    private final String USER_LOCATION = "user_location";
    private final String BIRTHDAY = "birthday";

    private SharedPreferences preferences;

    public SharedPreferencesManager(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public UserProfile getProfile() {
        return new UserProfile(preferences.getString(FIRST_NAME, ""),
                preferences.getString(PATRONYMIC, ""),
                preferences.getString(LAST_NAME, ""),
                preferences.getInt(SEX, 0),
                preferences.getInt(USER_LOCATION, 1),
                preferences.getString(BIRTHDAY, ""));
    }

    public void updateProfile(UserProfile userProfile) {
        preferences.edit()
                .putString(FIRST_NAME, userProfile.getFirstName())
                .putString(PATRONYMIC, userProfile.getPatronymic())
                .putString(LAST_NAME, userProfile.getLastName())
                .putInt(SEX, userProfile.getSex())
                .putInt(USER_LOCATION, userProfile.getUserLocation())
                .putString(BIRTHDAY, userProfile.getBirthday())
                .apply();
    }
}
