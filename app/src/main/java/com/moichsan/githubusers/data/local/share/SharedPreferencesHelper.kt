package com.moichsan.githubusers.data.local.share

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesHelper(private val context: Context) {
    private lateinit var sharedPreferences: SharedPreferences

    fun enabledAlarm(){
        sharedPreferences = context.getSharedPreferences(TAG_SHARED_PREF, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(TAG_IS_ALARM_ENABLED, true)
        editor.apply()
    }

    fun disabledAlarm(){
        sharedPreferences = context.getSharedPreferences(TAG_SHARED_PREF, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(TAG_IS_ALARM_ENABLED, false)
        editor.apply()
    }

    fun getAlarmState(): Boolean{
        sharedPreferences = context.getSharedPreferences(TAG_SHARED_PREF, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(TAG_IS_ALARM_ENABLED, false)
    }

    companion object {
        const val TAG_SHARED_PREF = "SharedPrefApps"
        const val TAG_IS_ALARM_ENABLED = "isAlarmEnabled"
    }
}