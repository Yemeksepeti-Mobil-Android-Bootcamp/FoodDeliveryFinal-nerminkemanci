package com.kemanci.yummyfood.ui.onboarding_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kemanci.yummyfood.databinding.OnboardingFragmentThreeBinding

class OnBoardingFragmentThree : Fragment() {
    private lateinit var binding: OnboardingFragmentThreeBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding =  OnboardingFragmentThreeBinding.inflate(inflater, container, false)
        return binding.root
    }
}