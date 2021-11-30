package com.example.master12021hunaultfil_rouge;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener{
    SharedPreferences shared;
    PreferenceScreen pref_screen;
    int count;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.pref_ex);
        Context context=this.getContext();
        shared=getPreferenceScreen().getSharedPreferences();
        pref_screen=getPreferenceScreen();
        count=pref_screen.getPreferenceCount();
        for (int i=0;i<count;i++){
            Preference p=pref_screen.getPreference(i);
            if(p instanceof EditTextPreference){
                String value =shared.getString(p.getKey(),"");
                p.setSummary(value);
            }
        }

        Preference pref = findPreference(getString(R.string.pref_setting_2_key));
        pref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                String pref2=newValue.toString();
                try {
                    int pref2_int=Integer.parseInt(pref2);
                    if(pref2_int<0){
                        Toast toast=Toast.makeText(context,"Le nombre est inférieur à 0",Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else return true;

                }catch (Exception e){
                    Toast toast=Toast.makeText(context,"la valeur rentrer n'est pas un nombre positif",Toast.LENGTH_SHORT);
                    toast.show();
                    System.out.println("");
                }
                return false;
            }

        });
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equals(getString(R.string.pref_setting_2_key))) {
            String pref2=sharedPreferences.getString(key,"");
            for (int i=0;i<count;i++) {
                Preference p = pref_screen.getPreference(i);
                if (p instanceof EditTextPreference) {
                    p.setSummary(pref2);
                }
            }

        }
    }
    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);

    }
}
