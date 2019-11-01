package com.itdeveapps.newsly.ui

import android.os.Bundle
import com.itdeveapps.newsly.R
import dagger.android.support.DaggerAppCompatActivity

/**
 * the host activity of the fragments using the Navigation component architecture
 * the logic is in navigation.xml file
 */
class NewsActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)


    }


}
