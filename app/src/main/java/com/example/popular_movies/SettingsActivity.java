package com.example.popular_movies;

        import android.os.Bundle;
        import android.preference.ListPreference;
        import android.preference.Preference;
        import android.preference.PreferenceActivity;
        import android.preference.PreferenceManager;

        import com.example.popular_movies.R;

/**
 * Created by Alex on 29/03/2018.
 */
public class SettingsActivity extends PreferenceActivity implements Preference.OnPreferenceChangeListener{

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
        bindPreferenceSummaryToValue(findPreference(getString(R.string.pref_language_key)));
    }


    private void bindPreferenceSummaryToValue(Preference preference){
        preference.setOnPreferenceChangeListener(this);

        onPreferenceChange(preference,
                PreferenceManager
                        .getDefaultSharedPreferences(preference.getContext())
                        .getString(preference.getKey(), ""));
    }


    @Override
    public boolean onPreferenceChange(Preference preference, Object value){
        String stringValue = value.toString();

        if(preference instanceof ListPreference){
            ListPreference listPreference = (ListPreference) preference;
            int prefIndex = listPreference.findIndexOfValue(stringValue);
            if(prefIndex >= 0){
                preference.setSummary(listPreference.getEntries()[prefIndex]);
            }
        }else{
            preference.setSummary(stringValue);
        }
        return true;
    }
}

