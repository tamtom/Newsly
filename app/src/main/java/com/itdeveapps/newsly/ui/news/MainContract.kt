package com.itdeveapps.newsly.ui.news

import com.itdeveapps.newsly.base.mvp.BasePresenter
import com.itdeveapps.newsly.model.Article
import com.itdeveapps.newsly.base.mvp.BaseView

/**
 * Created by omaraltamimi on 06,June,2019
 */
interface MainContract {
    interface View : BaseView<Presenter> {

        fun changeToolbarTitle(pagePosition: Int)
    }

    interface Presenter : BasePresenter<View> {
        fun handleTabSelected(position: Int)
    }
}