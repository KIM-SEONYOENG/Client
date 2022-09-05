package com.ograper.runwith.view;


import android.content.Context;
import android.content.SharedPreferences;

import com.ograper.runwith.R;

public class PreferenceAccess {
    private static final String pref_key = "preference";
    private static final String pref_user="user.name";
    private static final String pref_friend = "user.friend";
    private static final String pref_step = "user.step";
    private static final String pref_team = "user.team";

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public PreferenceAccess(Context context) {
        sharedPreferences = context.getSharedPreferences(pref_key, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public boolean setUserName(String name) {
        editor.putString(pref_user,name);
        return editor.commit();
    }

    public String getUserName() {
        return sharedPreferences.getString(pref_user, "");
    }

    public boolean setUserFriend(String friend) {
        editor.putString(pref_friend, friend);
        return editor.commit();
    }

    public String getUserFriend() {
        return sharedPreferences.getString(pref_friend, "");
    }

    public boolean setTeam(boolean is) {
        editor.putBoolean(pref_team, is);
        return editor.commit();
    }
    public boolean isTeam() {return sharedPreferences.getBoolean(pref_team, false); }

    public boolean setStep(int step) {
        editor.putInt(pref_step, step);
        return editor.commit();
    }

    public int getStep() {
        return sharedPreferences.getInt(pref_step, -1);
    }
}
