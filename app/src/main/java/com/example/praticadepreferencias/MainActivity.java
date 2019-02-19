package com.example.praticadepreferencias;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private TextView nameTextView;
    private TextView ageTextView;
    private TextView emailTextView;
    private TextView heightTextView;
    private AppCompatCheckBox lazyCheckBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameTextView = findViewById(R.id.textview_name);
        ageTextView = findViewById(R.id.textview_age);
        emailTextView = findViewById(R.id.textview_email);
        heightTextView = findViewById(R.id.textview_height);
        lazyCheckBox = findViewById(R.id.checkbox_lazy);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

        String name = sharedPreferences.getString(getString(R.string.pref_key_name), "");
        String age = sharedPreferences.getString(getString(R.string.pref_key_age), "");
        String email = sharedPreferences.getString(getString(R.string.pref_key_email), "");
        String height = sharedPreferences.getString(getString(R.string.pref_key_height), "");
        boolean lazy = sharedPreferences.getBoolean(getString(R.string.pref_key_lazy), false);

        nameTextView.setText(name);
        ageTextView.setText(age);
        emailTextView.setText(email);
        heightTextView.setText(height);
        lazyCheckBox.setChecked(lazy);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_settings){
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equals(getString(R.string.pref_key_name))){
            nameTextView.setText(sharedPreferences.getString(key, ""));
        }

        if(key.equals(getString(R.string.pref_key_age))){
            ageTextView.setText(sharedPreferences.getString(key, ""));
        }

        if(key.equals(getString(R.string.pref_key_email))){
            emailTextView.setText(sharedPreferences.getString(key, ""));
        }

        if(key.equals(getString(R.string.pref_key_height))){
            heightTextView.setText(sharedPreferences.getString(key, ""));
        }

        if(key.equals(getString(R.string.pref_key_lazy))){
            lazyCheckBox.setChecked(sharedPreferences.getBoolean(key, false));
        }
    }
}
