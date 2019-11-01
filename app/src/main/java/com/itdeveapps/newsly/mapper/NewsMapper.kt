package com.itdeveapps.newsly.mapper

import com.itdeveapps.newsly.model.Article

/**
 * Created by omaraltamimi on 01,November,2019
 * a mapper that filters the list of missing data we want to display
 * also it fix the issue of the text limit from the API
 */
class NewsMapper {
    fun map(list: List<Article>?):List<Article>? {
        return list?.filter { it.hasEssentialAttributes() }
        ?.onEach { article -> article.content = article.content?.substringBefore("[+") }
    }
}