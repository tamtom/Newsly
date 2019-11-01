package com.itdeveapps.newsly.ui.news.top

import com.itdeveapps.newsly.base.mvp.BasePresenter
import com.itdeveapps.newsly.base.mvp.BaseView
import com.itdeveapps.newsly.model.Article

/**
 * Created by omaraltamimi on 01,November,2019
 */
interface NewsContract {
    interface View : BaseView<Presenter> {
        fun setLoadingIndicator(show: Boolean)
        fun showNews(items: List<Article>)
        fun loadMoreComplete()
        fun loadMoreEnd()
        fun loadMoreData(items: List<Article>)
        fun onError(message: String)

    }

    interface ViewAll : BaseView<Presenter> {
        fun setLoadingIndicator(show: Boolean)
        fun showNews(items: List<Article>)
        fun loadMoreComplete()
        fun loadMoreEnd()
        fun loadMoreData(items: List<Article>)
        fun onError(message: String)

    }

    interface Presenter : BasePresenter<View>

    interface TopPresenter : Presenter {
        fun loadNews(country: String, category: String)
        fun getMoreNews(country: String, category: String)
    }

    interface AllNewsPresenter : BasePresenter<ViewAll> {
        fun loadAllNews()
        fun loadMoreNews()
    }

}