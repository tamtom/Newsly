package com.itdeveapps.newsly.ui.news

/**
 * Created by omaraltamimi on 06,June,2019
 */

const val DEFAULT_SIZE = 20
const val FIRST_PAGE = 1

class MainPresenter : MainContract.Presenter {
    lateinit var view: MainContract.View

    override fun handleTabSelected(position: Int) {
        view.changeToolbarTitle(position)

    }

    override fun takeView(view: MainContract.View) {
        this.view = view
    }


    override fun dropView() {
    }
}