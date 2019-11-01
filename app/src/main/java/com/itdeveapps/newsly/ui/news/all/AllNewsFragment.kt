package com.itdeveapps.newsly.ui.news.all


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

import com.itdeveapps.newsly.R
import com.itdeveapps.newsly.mapper.HumanDate
import com.itdeveapps.newsly.model.Article
import com.itdeveapps.newsly.ui.ARTICLE
import com.itdeveapps.newsly.ui.DetailsActivity
import com.itdeveapps.newsly.ui.component.NewsAdapter
import com.itdeveapps.newsly.ui.news.top.NewsContract
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_all_news.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class AllNewsFragment : DaggerFragment(), NewsContract.ViewAll {
    override fun onError(message: String) {
        view?.let { Snackbar.make(it, message, BaseTransientBottomBar.LENGTH_INDEFINITE) }

    }

    override fun loadMoreData(items: List<Article>) {
        newsAdapter.addData(items)
    }

    override fun showNews(items: List<Article>) {
        newsAdapter = context?.let { HumanDate(it) }?.let { NewsAdapter(items, it) }!!
        recyclerview_allarticles.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(context)
        }
        newsAdapter.setOnLoadMoreListener(
            { presnenterImpl.loadMoreNews() },
            recyclerview_allarticles
        )
        newsAdapter.setOnItemClickListener { adapter, view, position ->
            val intent = Intent(activity, DetailsActivity::class.java)
            intent.putExtra(ARTICLE, adapter.getItem(position) as Article)
            startActivity(intent)
        }

    }

    override fun loadMoreComplete() {
        newsAdapter.loadMoreComplete()
    }

    override fun loadMoreEnd() {
        newsAdapter.loadMoreEnd(true)
    }

    override fun setLoadingIndicator(show: Boolean) {
        progress_loader.visibility = if (show) View.VISIBLE else View.GONE

    }

    @Inject
    lateinit var presnenterImpl: AllNewsPresnenterImpl
    lateinit var newsAdapter: NewsAdapter

    companion object {
        @JvmStatic
        fun newInstance() =
            AllNewsFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presnenterImpl.takeView(this)
        presnenterImpl.loadAllNews()
    }

}
