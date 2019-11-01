package com.itdeveapps.newsly.ui.news.all

import com.itdeveapps.newsly.api.NewsService
import com.itdeveapps.newsly.base.schedulers.BaseSchedulerProvider
import dagger.Module
import dagger.Provides

/**
 * Created by omaraltamimi on 01,November,2019
 */
@Module
class AllModule {
    @Provides
    fun provideAllNewsFragment(
        newsService: NewsService,
        schedulerProvider: BaseSchedulerProvider
    ): AllNewsPresnenterImpl {
        return AllNewsPresnenterImpl(newsService, schedulerProvider)
    }


}