package com.itdeveapps.newsly

import com.itdeveapps.newsly.mapper.NewsMapper
import com.itdeveapps.newsly.model.Article
import com.itdeveapps.newsly.model.Source
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

/**
 * Created by omaraltamimi on 01,November,2019
 * this class test the @see com.itdeveapps.newsly.mapper.NewsMapper class
 * where it filters the missing required data
 */
class MapperTest {
    private val article: List<Article> by lazy {
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
    }
    private val expectedMappedArticle by lazy {
        listOf<Article>(
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
                title = "title",
                urlToImage = "urlToImage",
                publishedAt = "2019-03-27T05:52:21Z",
                description = "description",
                content = "content",
                author = "author",
                url = "url"
            )
        )
    }

    @Test
    fun `all essential attributes are specified`() {
        assertThat(article[0].hasEssentialAttributes(), `is`(true))
    }

    @Test
    fun mapFromResponse() {
        val map = NewsMapper().map(article)
        assert(map?.size == expectedMappedArticle.size)
    }
}