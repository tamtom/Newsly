package com.itdeveapps.newsly.di

import android.content.Context
import com.itdeveapps.newsly.NewsApplication
import dagger.Binds
import dagger.Module

/**
 * Created by omaraltamimi on 01,November,2019
 * a dependency Injection for the Application context
 */
@Module
abstract class ContextModule {
    @Binds
    abstract fun provideContext(application: NewsApplication): Context


}