package com.itdeveapps.newsly.ui.settings

import android.content.SharedPreferences
import androidx.core.content.edit

/**
 * Created by omaraltamimi on 01,November,2019
 */
class SettingsPrefrences(private val sharedPreferences: SharedPreferences) {


    fun setCountry(country: Country) =
        sharedPreferences.edit { putString(KEY_SELELCTED_COUNTRY, country.name) }

    fun getCountry(defaultCountry: Country): Country {
        val string = sharedPreferences.getString(KEY_SELELCTED_COUNTRY, defaultCountry.name)
        return Country.valueOf(string!!)
    }

    companion object {
        private const val KEY_SELELCTED_COUNTRY = "selectedCountry"
    }
}