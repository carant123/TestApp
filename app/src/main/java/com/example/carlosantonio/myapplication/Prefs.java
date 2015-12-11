package com.example.carlosantonio.myapplication;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by Carlos Antonio on 25/10/2015.
 */
public class Prefs extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs);
    }
}
