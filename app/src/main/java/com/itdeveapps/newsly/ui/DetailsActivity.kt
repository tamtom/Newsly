package com.itdeveapps.newsly.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.itdeveapps.newsly.R
import com.itdeveapps.newsly.mapper.HumanDate
import com.itdeveapps.newsly.model.Article
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.include_all_author.*
import android.content.Intent
import android.net.Uri


const val ARTICLE = "article"

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val article = intent.getParcelableExtra<Article>(ARTICLE)

        tv_title.text = article.title
        Glide.with(this).load(article.urlToImage).into(iv_image)
        description.text = article.description
        chip_author_badge.text = article.source?.name
        content.text = article.content
        textview_author_username.text = article.author
        textview_author_details.text = HumanDate(this).timePeriod(article.publishedAt!!)
        button.setOnClickListener {
            val i = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(article.url)
            )
            startActivity(i)
        }
    }
}
