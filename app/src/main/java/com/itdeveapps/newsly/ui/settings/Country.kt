package com.itdeveapps.newsly.ui.settings

import androidx.annotation.DrawableRes
import com.itdeveapps.newsly.R

/**
 * Created by omaraltamimi on 01,November,2019
 */
enum class Country(val code: String, @DrawableRes val flagICong: Int) {
    USA("us", R.drawable.us),
    SWEDEN("se", R.drawable.se);

    companion object {
        fun getAllCountries(): Array<String> {
            return listOf(USA.name, SWEDEN.name).toTypedArray()
        }

    }

}