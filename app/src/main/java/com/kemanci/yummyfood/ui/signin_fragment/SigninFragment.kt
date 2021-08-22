package com.kemanci.yummyfood.ui.signin_fragment

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.kemanci.yummyfood.R
import com.kemanci.yummyfood.databinding.SigninFragmentBinding
import com.kemanci.yummyfood.utils.Common
import com.kemanci.yummyfood.utils.Common.Companion.isEmailValid
import com.kemanci.yummyfood.utils.KeyboardHelper.Companion.hideKeyboard
import com.kemanci.yummyfood.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SigninFragment: Fragment() {
    private lateinit var binding: SigninFragmentBinding
    private val viewModel:SigninViewModel by viewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding =  SigninFragmentBinding.inflate(inflater, container, false)

        // TODO: 21.08.2021 check point
        //findNavController().navigate(SigninFragmentDirections.actionSigninFragmentToHomeFragment())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val successSnackbar: Snackbar = Snackbar.make(this.binding.root,"Giriş Başarılı",Snackbar.LENGTH_SHORT)
        successSnackbar.setBackgroundTint(Color.parseColor("#6C9FA0"))


        val errorSnackbar: Snackbar = Snackbar.make(this.binding.root,"Giriş Başarısız",Snackbar.LENGTH_SHORT)
        errorSnackbar.setBackgroundTint(Color.parseColor("#EE8181"))

        binding.signupButton.setOnClickListener{
            Common.alphaAnim(binding.signupButton)
            findNavController().navigate(SigninFragmentDirections.actionSigninFragmentToSignupFragment())
        }
        binding.signinButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            
            errorHandlingForInputLayout(email,password).let {
                if(!it){
                    return@setOnClickListener
                }
            }
            
            viewModel.login(email = email,password = password).observe(viewLifecycleOwner,{
                when(it.status){
                    Resource.Status.LOADING -> {
                        hideKeyboard(this.activity,binding.root)
                        binding.progressLayout.visibility = View.VISIBLE
                        Log.e("TAG", "onViewCreated: Bekliyoruz")
                    }
                    Resource.Status.SUCCESS -> {
                        binding.progressLayout.visibility = View.GONE
                        successSnackbar.show()
                        viewModel.saveToken(it.data!!.token)
                        Handler(Looper.getMainLooper()).postDelayed({

                            findNavController().navigate(SigninFragmentDirections.actionSigninFragmentToHomeFragment(it.data))
                        },1500)
                    }
                    Resource.Status.ERROR -> {
                        errorSnackbar.show()
                        binding.progressLayout.visibility = View.GONE
                        Log.e("TAG", "onViewCreated: Başarısız" + it.message)
                    }
                }
            })
        }
    }

    fun errorHandlingForInputLayout(email:String,password:String):Boolean{

        var result:Boolean = true
        if(!isEmailValid(email)){
            binding.emailInputLayout.error = "Lütfen email adresinizi doğru giriniz"
            result = false
        }
        if(password.isBlank()){
            binding.passwordInputLayout.error = "Lütfen parolanızı giriniz"
            result = false
        }
        !result && Handler().postDelayed({
            binding.passwordInputLayout.error = ""
            binding.emailInputLayout.error = ""
        },2000)

        return result
    }



}