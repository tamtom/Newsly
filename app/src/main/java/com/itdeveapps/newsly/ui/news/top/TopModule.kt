package com.itdeveapps.newsly.ui.news.top

import com.itdeveapps.newsly.api.NewsService
import com.itdeveapps.newsly.base.schedulers.BaseSchedulerProvider
import dagger.Module
import dagger.Provides

/**
 * Created by omaraltamimi on 01,November,2019
 */
@Module
class TopModule {
    @Provides
    fun provideTopNews(
        newsService: NewsService,
        schedulerProvider: BaseSchedulerProvider
    ): TopNewsPresenterImpl {
        return TopNewsPresenterImpl(newsService, schedulerProvider)
    }
}