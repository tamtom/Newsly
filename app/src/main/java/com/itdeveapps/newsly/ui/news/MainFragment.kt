package com.itdeveapps.newsly.ui.news


import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.google.android.material.tabs.TabLayout

import com.itdeveapps.newsly.R
import com.itdeveapps.newsly.extensions.getColorFromAttr
import com.itdeveapps.newsly.ui.component.ViewPagerAdapter
import com.itdeveapps.newsly.ui.news.all.AllNewsFragment
import com.itdeveapps.newsly.ui.news.top.TopNewsFragment
import kotlinx.android.synthetic.main.fragment_news.*

/**
 * this is the main fragment that responsible of the viewPager that contains two fragments
 * one for all articles the second for the top trend articles
 * mainly UI logic here
 */
class MainFragment : Fragment(), MainContract.View {
    private var currentAccentColor: Int? = null

    override fun changeToolbarTitle(pagePosition: Int) {

        val titleResId: Int = viewPagerAdapter.pageTitleRes[pagePosition]
        textview_newsly_title.text = getString(titleResId)
    }


    lateinit var viewPagerAdapter: ViewPagerAdapter
    private var pagePosition: Int = 0
    lateinit var presenter: MainContract.Presenter

    private val tabLayoutListener = object : TabLayout.OnTabSelectedListener {
        override fun onTabReselected(tab: TabLayout.Tab?) {}
        override fun onTabUnselected(tab: TabLayout.Tab?) {}
        override fun onTabSelected(tab: TabLayout.Tab?) {
            pagePosition = tab!!.position
            presenter.handleTabSelected(pagePosition)
            setAllArticlesColor()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = MainPresenter()
        presenter.takeView(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPagerAdapter = ViewPagerAdapter(childFragmentManager).apply {
            addFragment(
                TopNewsFragment.newInstance(), R.drawable.ic_trending_up_black_24dp,
                R.string.top_head
            )
            addFragment(
                AllNewsFragment.newInstance(), R.drawable.ic_fiber_new_black_24dp, R.string.new_news
            )

        }
        viewPager_newsly.adapter = viewPagerAdapter
        tablayout_newsly_tabs.apply {
            addOnTabSelectedListener(tabLayoutListener)
            setupWithViewPager(viewPager_newsly)
        }
        for (tabIndex in 0 until viewPagerAdapter.count) {
            tablayout_newsly_tabs.getTabAt(tabIndex)?.apply {
                setIcon(viewPagerAdapter.pageIconRes[tabIndex])
            }
        }
        presenter.handleTabSelected(pagePosition)
        setAllArticlesColor()
        iv_settings.setOnClickListener {
            view.findNavController()
                .navigate(MainFragmentDirections.actionMainFragmentToSettingsFragment())
        }

    }

    fun setAllArticlesColor() {
        currentAccentColor = context?.getColorFromAttr(R.attr.themeAllArticlesAccentColor)
        changeAccentColor()
    }

    private fun changeAccentColor() {

        val colorStateList = ColorStateList.valueOf(currentAccentColor!!)

        tablayout_newsly_tabs.apply {
            tabRippleColor = colorStateList
            setSelectedTabIndicatorColor(currentAccentColor!!)
        }

        for (tabIndex in 0 until viewPagerAdapter.count) {
            getTabIconColor(tabIndex, currentAccentColor!!)?.let {
                tablayout_newsly_tabs.getTabAt(tabIndex)?.icon?.setColorFilter(
                    it,
                    PorterDuff.Mode.SRC_IN
                )
            }
        }
    }

    private fun getTabIconColor(tabIndex: Int, currentAccentColor: Int) =
        if (tabIndex != pagePosition) {
            context?.getColorFromAttr(R.attr.themeTopBarIconTintColor)
        } else {
            currentAccentColor
        }

}
