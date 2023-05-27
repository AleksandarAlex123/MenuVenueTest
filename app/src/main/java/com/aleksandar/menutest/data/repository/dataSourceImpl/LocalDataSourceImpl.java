package com.aleksandar.menutest.data.repository.dataSourceImpl;

import static com.aleksandar.menutest.data.util.AppConstant.EMPTY;
import static com.aleksandar.menutest.data.util.AppConstant.PREF_NAME;

import android.content.Context;
import android.content.SharedPreferences;

import com.aleksandar.menutest.data.repository.dataSource.LocalDataSource;
import com.aleksandar.menutest.data.util.AppConstant;

public class LocalDataSourceImpl implements LocalDataSource {
    private final SharedPreferences sharedPreferences;

    public LocalDataSourceImpl(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public void saveData(String key, String value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString(key, value);
        prefsEditor.apply();
    }

    @Override
    public String getData(String key) {
        if (sharedPreferences != null) {
            return sharedPreferences.getString(key, EMPTY);
        }
        return EMPTY;
    }

    @Override
    public void clearData(String key) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString(key, EMPTY);
        prefsEditor.apply();
    }

    @Override
    public boolean isUserLogedIn() {
        return !getData(AppConstant.ACCESS_TOKEN_KEY).isEmpty();
    }
}
