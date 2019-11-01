package com.itdeveapps.newsly.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by omaraltamimi on 01,November,2019
 */
@Parcelize
class Article(
    val title: String?, val description: String?
    , val publishedAt: String?
    , val urlToImage: String?
    , val url: String?
    , val author: String?
    , val source: Source?
    , var content: String?

) : Parcelable {
    fun hasEssentialAttributes() = !urlToImage.isNullOrEmpty()
            && !author.isNullOrEmpty()
            && !source?.name.isNullOrEmpty()
            && !publishedAt.isNullOrEmpty()
            && !urlToImage.isNullOrEmpty()
            && !url.isNullOrEmpty()
            && !content.isNullOrEmpty()

}

@Parcelize
class Source(val name: String?) : Parcelable