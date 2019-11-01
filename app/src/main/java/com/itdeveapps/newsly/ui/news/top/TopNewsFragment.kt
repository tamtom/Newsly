package com.itdeveapps.newsly.ui.news.top


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

import com.itdeveapps.newsly.R
import com.itdeveapps.newsly.mapper.HumanDate
import com.itdeveapps.newsly.model.Article
import com.itdeveapps.newsly.ui.ARTICLE
import com.itdeveapps.newsly.ui.DetailsActivity
import com.itdeveapps.newsly.ui.component.NewsAdapter
import com.itdeveapps.newsly.ui.news.MainFragmentDirections
import com.itdeveapps.newsly.ui.settings.Country
import com.itdeveapps.newsly.ui.settings.SettingsPrefrences
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_top_news.*
import javax.inject.Inject

class TopNewsFragment : DaggerFragment(), NewsContract.View {
    override fun onError(message: String) {
        view?.let { Snackbar.make(it, message, BaseTransientBottomBar.LENGTH_INDEFINITE) }
    }

    override fun loadMoreData(items: List<Article>) {
        newsAdapter.addData(items)
    }

    override fun setLoadingIndicator(show: Boolean) {
        progress.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun showNews(items: List<Article>) {
        setUpAdapter(items)
    }

    private fun setUpAdapter(items: List<Article>) {
        newsAdapter = context?.let { HumanDate(it) }?.let { NewsAdapter(items, it) }!!
        recyclerview_topheadlines.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(context)
        }
        newsAdapter.setOnLoadMoreListener(
            { topNewsPresenterImpl.getMoreNews(settingsPrefrences.getCountry(Country.USA).code, "") },
            recyclerview_topheadlines
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
        newsAdapter.loadMoreEnd()
        newsAdapter.setEnableLoadMore(false)
    }

    @Inject
    lateinit var topNewsPresenterImpl: TopNewsPresenterImpl
    lateinit var newsAdapter: NewsAdapter
    @Inject
    lateinit var settingsPrefrences: SettingsPrefrences

    companion object {
        @JvmStatic
        fun newInstance() =
            TopNewsFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        topNewsPresenterImpl.takeView(this)
        topNewsPresenterImpl.loadNews(settingsPrefrences.getCountry(Country.USA).code, "")
    }


}
