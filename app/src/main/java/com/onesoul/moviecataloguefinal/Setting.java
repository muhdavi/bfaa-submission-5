package com.onesoul.moviecataloguefinal;

import android.content.Context;
import android.content.SharedPreferences;

public class Setting {
    private static final String DAILY_REMINDER = "isReminder";
    private static final String TODAY_RELEASE = "isRelease";
    private static final String PREFS_NAME = "setting_pref";
    private final SharedPreferences sharedPreferences;
    
    public Setting(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void setDailyReminder(boolean isActive) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(DAILY_REMINDER, isActive);
        editor.apply();
    }


    public boolean getDailyReminder() {
        return sharedPreferences.getBoolean(DAILY_REMINDER, false);
    }

    public void setTodayRelease(boolean isActive) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(DAILY_REMINDER, isActive);
        editor.apply();
    }

    public boolean getTodayRelease() {
        return sharedPreferences.getBoolean(TODAY_RELEASE, false);
    }
}
