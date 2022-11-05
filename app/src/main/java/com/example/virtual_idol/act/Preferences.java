package com.example.virtual_idol.act;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

    private static Preferences instance = new Preferences();
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private Preferences() {}

    public static Preferences getInstance() {
        if (instance == null) {
            instance = new Preferences();
        }
        return instance;
    }

    public void setTable(Context context, String key) {
        preferences = context.getSharedPreferences(key,Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void saveString(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }
    public void saveBoolean(String key, Boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void delete(String key) {
        editor.remove(key);
        editor.commit();
    }

    public void clear() {
        editor.clear().commit();
    }

    public String getStringValue(String key) {
        return preferences.getString(key, "");
    }

    public Boolean getBooleanValue(String key) {
        return preferences.getBoolean(key, false);
    }
}
