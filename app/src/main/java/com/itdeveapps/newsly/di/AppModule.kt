package com.itdeveapps.newsly.di

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.itdeveapps.newsly.BuildConfig
import com.itdeveapps.newsly.NewsApplication
import com.itdeveapps.newsly.api.NewsService
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import com.itdeveapps.newsly.base.schedulers.BaseSchedulerProvider
import com.itdeveapps.newsly.base.schedulers.SchedulerProvider
import com.itdeveapps.newsly.ui.news.all.AllNewsPresnenterImpl
import com.itdeveapps.newsly.ui.news.top.NewsContract
import com.itdeveapps.newsly.ui.news.top.TopNewsFragment
import com.itdeveapps.newsly.ui.news.top.TopNewsPresenterImpl
import com.itdeveapps.newsly.ui.settings.SettingsPrefrences
import dagger.Binds
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by omaraltamimi on 01,November,2019
 */
@Module
class AppModule {

    companion object {
        /**
         * the cache size of the response from the API if no changes accords
         */
        const val CACHE_SIZE: Long = 10 * 1024 * 1024
        /**
         * time in of timeout waiting the response in seconds
         */
        const val CONNECT_TIMEOUT: Long = 30
        const val READ_TIMEOUT: Long = 60
        const val BASE_URL = "https://newsapi.org/v2/"
        private const val SHARED_PREFERENCES_NAME = "newsly_shared_preferences"

    }

    @Provides
    @Singleton
    fun provideApiService(okHttpClient: OkHttpClient): NewsService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build().create(NewsService::class.java)
    }

    /**
     * if you want to customize your request and adding an Interceptor to your request add it here
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(application: NewsApplication): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        /**
         * adding a logger to see the response
         * to make it readable
         * only log when testing
         */
        interceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
        else HttpLoggingInterceptor.Level.NONE

        val cacheDir = File(application.cacheDir, UUID.randomUUID().toString())
        val cache = Cache(cacheDir, CACHE_SIZE)

        return OkHttpClient.Builder()
            .cache(cache)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRxSchedulers(): BaseSchedulerProvider {
        return SchedulerProvider()
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(application: NewsApplication): SharedPreferences {
        return application.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideSettingsSharedPrefrence(sharedPreferences: SharedPreferences): SettingsPrefrences {
        return SettingsPrefrences(sharedPreferences)
    }



}
