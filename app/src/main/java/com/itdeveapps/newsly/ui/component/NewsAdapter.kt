package com.itdeveapps.newsly.ui.component

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.itdeveapps.newsly.R
import com.itdeveapps.newsly.mapper.HumanDate
import com.itdeveapps.newsly.model.Article

/**
 * Created by omaraltamimi on 01,November,2019
 */
class NewsAdapter(list: List<Article>,val humanDate: HumanDate) :
    BaseQuickAdapter<Article, BaseViewHolder>(R.layout.item_article, list) {

    override fun convert(helper: BaseViewHolder?, item: Article?) {
        helper?.let {
            item?.let {
                Glide.with(mContext).load(item.urlToImage)
                    .transition(DrawableTransitionOptions().crossFade())
                    .into(helper.getView(R.id.imageView_article_thumbnail))
                helper.setText(R.id.textView_article_title, item.title)
                helper.setText(R.id.textview_author_username, item.author)
                helper.setText(R.id.chip_author_badge, item.source?.name)
                helper.setText(
                    R.id.textview_author_details,
                    humanDate.timePeriod(item.publishedAt!!)
                )
            }
        }

    }
}