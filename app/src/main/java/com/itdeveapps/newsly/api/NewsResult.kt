package com.itdeveapps.newsly.api

import com.itdeveapps.newsly.model.Article

/**
 * Created by omaraltamimi on 01,November,2019
 */
class NewsResult(val articles: List<Article>, val totalResults: Int=0, val status: String="ok",val message:String="") {
    fun isError(): Boolean = "error" == status
}