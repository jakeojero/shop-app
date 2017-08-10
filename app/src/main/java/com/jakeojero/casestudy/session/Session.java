package com.jakeojero.casestudy.session;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.jakeojero.casestudy.R;

public class Session {

    private SharedPreferences prefs;
    private Context context;
    public Session(Context ctx) {
        this.context = ctx;
        this.prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setUser(String user) {
        prefs.edit().putString(context.getString(R.string.user_session), user).apply();
    }

    public String getUser() {
        return prefs.getString(context.getString(R.string.user_session),"");

    }

    public void clearUser() {
        prefs.edit().remove(context.getString(R.string.user_session)).apply();
    }
}
