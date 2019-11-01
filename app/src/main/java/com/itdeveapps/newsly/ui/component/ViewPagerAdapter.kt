package com.itdeveapps.newsly.ui.component

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
/**
 * Created by omaraltamimi on 01,November,2019
 */
class ViewPagerAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    val pages = mutableListOf<Fragment>()
    val pageIconRes = mutableListOf<Int>()
    val pageTitleRes = mutableListOf<Int>()

    fun addFragment(fragment: Fragment, iconResId: Int, titleResId: Int) {
        pages.add(fragment)
        pageIconRes.add(iconResId)
        pageTitleRes.add(titleResId)
        notifyDataSetChanged()
    }


    override fun getItem(position: Int): Fragment = pages[position]

    override fun getCount(): Int = pages.count()

    override fun getPageTitle(position: Int): CharSequence? = null
}