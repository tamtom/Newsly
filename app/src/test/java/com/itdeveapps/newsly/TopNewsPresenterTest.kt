package com.itdeveapps.newsly

import com.itdeveapps.newsly.api.NewsResult
import com.itdeveapps.newsly.api.NewsService
import com.itdeveapps.newsly.base.schedulers.BaseSchedulerProvider
import com.itdeveapps.newsly.base.schedulers.ImmediateSchedulerProvider
import com.itdeveapps.newsly.model.Article
import com.itdeveapps.newsly.model.Source
import com.itdeveapps.newsly.ui.news.top.NewsContract
import com.itdeveapps.newsly.ui.news.top.TopNewsPresenterImpl
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

/**
 * Created by omaraltamimi on 01,November,2019
 */
class TopNewsPresenterTest {
    private lateinit var schedulerProvider: BaseSchedulerProvider
    @Mock
    lateinit var view: NewsContract.View
    @Mock
    lateinit var apiInterface: NewsService
    private lateinit var presenter: NewsContract.TopPresenter
    private lateinit var newsResult: NewsResult

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        schedulerProvider = ImmediateSchedulerProvider()
        newsResult = NewsResult(
            listOf(
            Article(
                source = Source(
                    name = "name"
                ),
                title = "title",
                urlToImage = "urlToImage",
                publishedAt = "2019-03-27T05:52:21Z",
                description = "description",
                content = "content",
                author = "author",
                url = "url"
            )
            ,
            Article(
                source = Source(
                    name = "name"
                ),
                title = "title",
                urlToImage = "urlToImage",
                publishedAt = "2019-03-27T05:52:21Z",
                description = "description",
                content = "content",
                author = "author",
                url = "url"
            ),
            Article(
                source = Source(
                    name = "name"
                ),
                title = "asda",
                urlToImage = null,
                publishedAt = "2019-03-27T05:52:21Z",
                description = "description",
                content = "content",
                author = "author",
                url = "url"
            )
        )
        )
        presenter = TopNewsPresenterImpl(apiInterface, schedulerProvider)
        presenter.takeView(view)
    }

    @Test
    fun testLoadingNews() {
        whenever(apiInterface.getTopHeadLines()).thenReturn(Observable.just(newsResult))
        presenter.loadNews("us","")
        verify(view).setLoadingIndicator(true)
        verify(view).showNews(newsResult.articles)
        verify(view).setLoadingIndicator(false)
    }

}