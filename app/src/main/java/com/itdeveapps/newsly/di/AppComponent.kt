package com.itdeveapps.newsly.di

import com.itdeveapps.newsly.NewsApplication
import com.itdeveapps.newsly.api.NewsService
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 * Created by omaraltamimi on 01,November,2019
 */
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class, ContextModule::class, AppModule::class, ActivityBuilder::class

    ]
)
interface AppComponent : AndroidInjector<NewsApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<NewsApplication>()
}