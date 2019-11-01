package com.itdeveapps.newsly.di

import com.itdeveapps.newsly.ui.NewsActivity
import com.itdeveapps.newsly.ui.news.all.AllModule
import com.itdeveapps.newsly.ui.news.all.AllNewsFragment
import com.itdeveapps.newsly.ui.news.top.TopModule
import com.itdeveapps.newsly.ui.news.top.TopNewsFragment
import com.itdeveapps.newsly.ui.news.top.TopNewsPresenterImpl
import com.itdeveapps.newsly.ui.settings.SettingsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
/**
 * Created by omaraltamimi on 01,November,2019
 */
@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector
    abstract fun bindActivity(): NewsActivity
    @ContributesAndroidInjector
    abstract fun bindSettings(): SettingsFragment

    @ContributesAndroidInjector(modules = [TopModule::class])
    abstract fun bindTopHeadlinesFragment(): TopNewsFragment

    @ContributesAndroidInjector(modules = [AllModule::class])
    abstract fun bindAllArticlesFragment(): AllNewsFragment
}