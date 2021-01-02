package com.moichsan.consumerapp.ui.detail

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.moichsan.consumerapp.R

private val TAB_TITLES = arrayOf(
    R.string.tab_text_following,
    R.string.tab_text_followers
)

class ViewPagerAdapter(
    private val context: Context?,
    private val fragments: List<Fragment>,
    fragmentManager: FragmentManager
) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int) = fragments[position]

    override fun getCount() = fragments.size

    override fun getPageTitle(position: Int) = context?.resources?.getString(TAB_TITLES[position])

}