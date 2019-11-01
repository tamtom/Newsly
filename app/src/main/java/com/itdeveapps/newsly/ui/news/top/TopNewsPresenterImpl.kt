package com.itdeveapps.newsly.ui.news.top

import android.util.Log
import com.itdeveapps.newsly.api.NewsResult
import com.itdeveapps.newsly.api.NewsService
import com.itdeveapps.newsly.base.schedulers.BaseSchedulerProvider
import com.itdeveapps.newsly.mapper.NewsMapper
import com.itdeveapps.newsly.model.Article
import com.itdeveapps.newsly.ui.news.DEFAULT_SIZE
import com.itdeveapps.newsly.ui.news.FIRST_PAGE
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

/**
 * Created by omaraltamimi on 01,November,2019
 */
class TopNewsPresenterImpl @Inject constructor(
    private val apiInterface: NewsService,
    private val schedulerProvider: BaseSchedulerProvider
) : NewsContract.TopPresenter {
    private var pageSize = DEFAULT_SIZE
    private var currnetPage = FIRST_PAGE
    private var numberOfResults = 0
    private lateinit var view: NewsContract.View
    override fun takeView(view: NewsContract.View) {
        this.view = view
    }


    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun loadNews(country: String, category: String) {
        pageSize = DEFAULT_SIZE
        currnetPage = FIRST_PAGE
        view.setLoadingIndicator(true)
        var loadMoreEnd = false
        compositeDisposable.add(
            apiInterface.getTopHeadLines(country, category)
                .filter {
                    if (it.isError()) {
                        view.onError(it.message)
                        return@filter false
                    }
                    return@filter true
                }
                .map {
                    loadMoreEnd = checkLoadMore(it)
                    NewsMapper().map(it.articles)
                    it.articles
                }.subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeWith(object : DisposableObserver<List<Article>>() {
                    override fun onComplete() {

                        view.setLoadingIndicator(false)
                    }

                    override fun onNext(items: List<Article>) {
                        view.showNews(items)

                        if (loadMoreEnd)
                            view.loadMoreEnd()
                        else
                            view.loadMoreComplete()
                    }

                    override fun onError(e: Throwable) {
                        Log.e("Error", "", e)
                    }

                })
        )
    }

    private fun checkLoadMore(it: NewsResult): Boolean {
        numberOfResults += it.articles.size
        if (it.totalResults == numberOfResults) {
            return false
        } else if (it.totalResults < numberOfResults) {
            val diff = numberOfResults - it.totalResults
            return if (diff > 0) {
                pageSize = diff
                false
            } else {
                true
            }
        }
        return false
    }

    override fun getMoreNews(country: String, category: String) {
        currnetPage++
        var loadMoreEnd = false

        compositeDisposable.add(
            apiInterface.getTopHeadLines(country, category, pageSize, currnetPage)
                .filter {
                    if (it.isError()) {
                        view.onError(it.message)
                        return@filter false
                    }
                    return@filter true
                }
                .map {
                    loadMoreEnd = checkLoadMore(it)
                    NewsMapper().map(it.articles)
                    it.articles
                }.subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeWith(object : DisposableObserver<List<Article>>() {
                    override fun onComplete() {

                    }

                    override fun onNext(items: List<Article>) {
                        view.loadMoreData(items)
                        if (loadMoreEnd)
                            view.loadMoreEnd()
                        else
                            view.loadMoreComplete()
                    }

                    override fun onError(e: Throwable) {
                        Log.e("Error", "", e)
                        view.loadMoreEnd()

                    }

                })
        )
    }


    override fun dropView() {
        compositeDisposable.clear()
    }
}