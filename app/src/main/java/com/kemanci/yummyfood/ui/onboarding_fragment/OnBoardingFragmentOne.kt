package com.kemanci.yummyfood.ui.onboarding_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kemanci.yummyfood.databinding.OnboardingFragmentOneBinding

class OnBoardingFragmentOne : Fragment() {
    private lateinit var binding: OnboardingFragmentOneBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding =  OnboardingFragmentOneBinding.inflate(inflater, container, false)
        return binding.root
    }
}