package com.kemanci.yummyfood.ui.splash_fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kemanci.yummyfood.databinding.SplashFragmentBinding
import com.kemanci.yummyfood.model.entity.AccountResponse
import com.kemanci.yummyfood.ui.onboarding_fragment.MainOnBoardingFragment
import com.kemanci.yummyfood.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment:Fragment() {
    private lateinit var binding: SplashFragmentBinding
    private val viewModel:SplashViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding =  SplashFragmentBinding.inflate(inflater, container, false)
        setObservers()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

     private fun setObservers() {
        val token:String? = viewModel.getToken()
        Log.e("TOKEN", "setObservers: "+ token)
        if(token == "-1") {
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToOnboardingFragment())
            return
        }else{
            viewModel.profile(token!!).observe(viewLifecycleOwner,{
                if(it.status== Resource.Status.SUCCESS){
                    val accountResponse:AccountResponse = AccountResponse(account = it.data!!,token = token)
                    findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment(accountResponse))
                }
            })

        }

    }


}