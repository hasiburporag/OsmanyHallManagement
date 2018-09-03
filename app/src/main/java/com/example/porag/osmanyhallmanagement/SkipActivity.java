package com.example.porag.osmanyhallmanagement;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SkipActivity {


    private SharedPreferences prefs;

    public SkipActivity(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);

    }

    public void setusename(String usename) {
        prefs.edit().putString("usename", usename).commit();
    }

    public String getusename() {
        String usename = prefs.getString("usename","");
        return usename;
    }
    public void clearAll()
    {
        SharedPreferences.Editor editor=prefs.edit();
        editor.clear();
        editor.commit();
        //finish();
    }
}
