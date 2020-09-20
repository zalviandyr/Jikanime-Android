package com.zukron.jikanime.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/10/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
class ViewPagerAdapter(
        fragmentManager: FragmentManager,
        lifecycle: Lifecycle,
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    var fragmentList: List<Fragment>? = null

    override fun getItemCount(): Int {
        return fragmentList?.size ?: 0
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList!![position]
    }

}