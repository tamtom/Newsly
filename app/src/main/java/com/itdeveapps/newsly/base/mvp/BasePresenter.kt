package com.itdeveapps.newsly.base.mvp


/**
 * Created by omaraltamimi on 06,June,2019
 */
interface BasePresenter<T> {

    /**
     * Drops the reference to the view when destroyed
     */
    fun dropView()
    fun takeView(view: T)

}
