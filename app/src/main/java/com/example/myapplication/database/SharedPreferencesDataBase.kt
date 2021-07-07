package com.example.myapplication.database

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesDataBase(context: Context) {

    private var preferences: SharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    fun saveName(value: String?) {
        val editor = preferences.edit()
        editor?.putString(KEY_NAME, value)
        editor?.apply()
    }

    fun getName(): String? {
        return preferences.getString(KEY_NAME, "Peserta")
    }

    companion object {
        const val PREFERENCES_NAME = "dataku"
        const val KEY_NAME = "name"
    }


}