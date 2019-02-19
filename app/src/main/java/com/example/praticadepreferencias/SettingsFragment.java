package com.example.praticadepreferencias;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;
import android.widget.Toast;

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener,
        Preference.OnPreferenceChangeListener{
    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.preferences_user_data);

        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

        updatePreferenceScreen();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        updatePreferenceScreen();
    }

    private void updatePreferenceScreen(){
        final SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        int preferenceCount = preferenceScreen.getPreferenceCount();

        for(int i=0; i<preferenceCount; i++){
            Preference preference = preferenceScreen.getPreference(i);
            String key = preference.getKey();

            preference.setOnPreferenceChangeListener(this);

            if(preference instanceof EditTextPreference){
                EditTextPreference editTextPreference = (EditTextPreference)preference;
                String value = sharedPreferences.getString(key, "");
                editTextPreference.setSummary(value);
            }
        }
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object value) {
        String ageKey = getString(R.string.pref_key_age);
        String heightKey = getString(R.string.pref_key_height);

        if(preference.getKey().equals(ageKey)){
            try{
                Integer.parseInt((String) value);
            }catch(NumberFormatException e){
                Toast.makeText(SettingsFragment.this.getContext(), "Valor inválido", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        if(preference.getKey().equals(heightKey)){
            try{
                Float.parseFloat((String)value);
            }catch(NumberFormatException e){
                Toast.makeText(SettingsFragment.this.getContext(), "Valor inválido", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        return true;
    }
}
