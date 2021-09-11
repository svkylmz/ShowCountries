package com.svkylmz.showcountries.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

//create shared preferences class to store small TIME data of when data was last saved in database
//for example if time is smaller than 10 minutes, when the page is refreshed, get them from db, else get them from api
class CustomSharedPreferences {
    companion object {
        private val PREFERENCES_TIME = "preferences_time"
        private var sharedPreferences: SharedPreferences? = null

        @Volatile private var instance: CustomSharedPreferences? = null
        private val lock = Any()

        operator fun invoke(context: Context): CustomSharedPreferences = instance ?: synchronized(lock) {
            instance ?: makeCustomSharedPreferences(context).also {
                instance = it
            }
        }

        private fun makeCustomSharedPreferences(context: Context): CustomSharedPreferences {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            return CustomSharedPreferences()
        }
    }

    fun saveTime(time: Long) {  //Long is the most detailed unit of time we can get -> nanoTime()
        sharedPreferences?.edit(commit = true) {
            putLong(PREFERENCES_TIME, time)
        }
    }

    fun getTime() = sharedPreferences?.getLong(PREFERENCES_TIME, 0)
}