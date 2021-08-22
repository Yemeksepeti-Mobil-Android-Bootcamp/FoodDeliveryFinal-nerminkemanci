package com.kemanci.yummyfood.ui.onboarding_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.kemanci.yummyfood.databinding.MainOnboardingFragmentBinding
import com.kemanci.yummyfood.ui.splash_fragment.SplashFragmentDirections
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainOnBoardingFragment : Fragment() {
    private lateinit var binding: MainOnboardingFragmentBinding
    private lateinit var onBoardingViewPagerAdapter: OnBoardingViewPagerAdapter
    private lateinit var indicatorLayout:TabLayout
    private lateinit var onboardingViewPager:ViewPager2


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding =  MainOnboardingFragmentBinding.inflate(inflater, container, false)
        indicatorLayout = binding.indicatorLayout
        onboardingViewPager = binding.viewPager
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val fragmentList:ArrayList<Fragment> = ArrayList<Fragment>()
        val frag1:Fragment = OnBoardingFragmentOne()
        val frag2:Fragment = OnBoardingFragmentTwo()
        val frag3:Fragment = OnBoardingFragmentThree()
        fragmentList.add(frag1)
        fragmentList.add(frag2)
        fragmentList.add(frag3)
        onBoardingViewPagerAdapter = OnBoardingViewPagerAdapter(activity as AppCompatActivity,fragmentList = fragmentList )
        onboardingViewPager.adapter = onBoardingViewPagerAdapter
        TabLayoutMediator(indicatorLayout,onboardingViewPager){tab, position -> }.attach()


        binding.signinButton.setOnClickListener{
            findNavController().navigate(MainOnBoardingFragmentDirections.actionMainOnboardingFragmentToSigninFragment())
        }

        binding.signupButton.setOnClickListener {
            findNavController().navigate(MainOnBoardingFragmentDirections.actionMainOnboardingFragmentToSignupFragment())
        }
        super.onViewCreated(view, savedInstanceState)
    }
}