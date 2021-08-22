package com.kemanci.yummyfood.ui.onboarding_fragment

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnBoardingViewPagerAdapter (activity: AppCompatActivity, private val fragmentList: ArrayList<Fragment>) : FragmentStateAdapter(activity) {
    private val FRAGMENT_COUNT : Int = fragmentList.size

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
    override fun getItemCount(): Int {
        return FRAGMENT_COUNT
    }

}

