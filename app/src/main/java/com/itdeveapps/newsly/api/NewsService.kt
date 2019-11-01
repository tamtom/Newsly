package com.itdeveapps.newsly.api

import com.itdeveapps.newsly.BuildConfig
import com.itdeveapps.newsly.BuildConfig.*
import com.itdeveapps.newsly.ui.news.DEFAULT_SIZE
import com.itdeveapps.newsly.ui.news.FIRST_PAGE
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by omaraltamimi on 31,October,2019
 */
const val API_KEY = "7019973f03494525b62199f2e92fe71f"
private val DEFAULT_NEWS_SOURCES =
    listOf(
        "the-new-york-times",
        "the-wall-street-journal",
        "the-washington-post",
        "abc-news",
        "bbc-news",
        "bbc-sport",
        "wired",
        "politico",
        "usa-today",
        "cnn",
        "nbc-news",
        "fortune",
        "bloomberg",
        "daily-mail",
        "fox-news",
        "time",
        "hacker-news",
        "reddit-r-all",
        "techcrunch",
        "techradar"
    )

interface NewsService {
    /**
     * Search through millions of articles from over 30,000 large and small news sources and blogs.
     * This includes breaking news as well as lesser articles.
     * @param query Keywords or phrases to search for in the article title and body
     */
    @GET("everything?apiKey=$API_KEY")
    fun getEveryThing(
        @Query("pageSize") pageSize: Int = DEFAULT_SIZE,
        @Query("page") page: Int = FIRST_PAGE,
        @Query("sources") sources: String
        = DEFAULT_NEWS_SOURCES.joinToString(",").toString()
    ): Observable<NewsResult>

    /**
     * This endpoint provides live top and breaking headlines for a country,
     * specific category in a country, single source, or multiple sources.
     * You can also search with keywords. Articles are sorted by the earliest date published first.
     * @param country The 2-letter ISO 3166-1 code of the country you want to get headlines for.
     * Note: you can't mix this param with the sources param.
     * @param category The category you want to get headlines for. Possible options:
     * business entertainment general health science sports technology
     *  Note: you can't mix this param with the sources param.
     */
    @GET("top-headlines?apiKey=$API_KEY")
    fun getTopHeadLines(
        @Query("country") country: String = "us"
        , @Query("category") category: String = ""
        , @Query("pageSize") pageSize: Int = DEFAULT_SIZE,
        @Query("page") page: Int = FIRST_PAGE
    ): Observable<NewsResult>

}