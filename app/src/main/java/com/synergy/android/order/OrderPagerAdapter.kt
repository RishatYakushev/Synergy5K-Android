package com.synergy.android.order

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.synergy.android.R

class OrderPagerAdapter(fm: FragmentManager, val context: Context) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val orderWaitFragment by lazy { OrderWaitFragment.newInstance() }
    private val orderDoneFragment by lazy { OrderDoneFragment.newInstance() }

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> orderWaitFragment
        else -> orderDoneFragment
    }

    override fun getCount() = 2

    override fun getPageTitle(position: Int): CharSequence? = when (position) {
        0 -> context.getString(R.string.order_wait_tab_title)
        else -> context.getString(R.string.order_done_tab_title)
    }
}