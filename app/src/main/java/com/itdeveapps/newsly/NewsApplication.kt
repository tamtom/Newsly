package com.itdeveapps.newsly

import com.itdeveapps.newsly.di.DaggerAppComponent
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

/**
 * Created by omaraltamimi on 01,November,2019
 */
class NewsApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = DaggerAppComponent.builder().create(this)


    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)


    }
}