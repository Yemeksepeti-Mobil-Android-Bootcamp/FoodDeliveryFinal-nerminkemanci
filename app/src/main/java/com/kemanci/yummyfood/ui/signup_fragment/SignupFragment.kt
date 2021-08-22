package com.kemanci.yummyfood.ui.signup_fragment
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.kemanci.yummyfood.databinding.SignupFragmentBinding
import com.kemanci.yummyfood.model.entity.Account
import com.kemanci.yummyfood.utils.Common
import com.kemanci.yummyfood.utils.KeyboardHelper
import com.kemanci.yummyfood.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupFragment : Fragment() {
    private lateinit var binding: SignupFragmentBinding
    private val viewModel:SignupViewModel by viewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding =  SignupFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val successSnackbar: Snackbar = Snackbar.make(this.binding.root,"Kayıt Başarılı", Snackbar.LENGTH_SHORT)
        successSnackbar.setBackgroundTint(Color.parseColor("#6C9FA0"))
        val errorSnackbar: Snackbar = Snackbar.make(this.binding.root,"Kayıt Başarısız", Snackbar.LENGTH_SHORT)
        errorSnackbar.setBackgroundTint(Color.parseColor("#EE8181"))

        binding.loginButton.setOnClickListener{
            Common.alphaAnim(binding.loginButton)
            findNavController().navigate(SignupFragmentDirections.actionSignupFragmentToSigninFragment())
        }

        binding.signupButton.setOnClickListener {
            var account:Account? = null
            formValidateHandler().let {
                if (it==null){
                    return@setOnClickListener
                }
                account = it
            }

            viewModel.signup(account = account!!).observe(viewLifecycleOwner,{
                when(it.status) {
                    Resource.Status.LOADING -> {
                        KeyboardHelper.hideKeyboard(this.activity, binding.root)
                        binding.progressLayout.visibility = View.VISIBLE
                    }
                    Resource.Status.SUCCESS -> {
                        binding.progressLayout.visibility = View.GONE
                        successSnackbar.show()
                        Handler(Looper.getMainLooper()).postDelayed({
                            findNavController().navigate(SignupFragmentDirections.actionSignupFragmentToSigninFragment())
                        }, 1500)
                    }
                    Resource.Status.ERROR -> {
                        errorSnackbar.show()
                        binding.progressLayout.visibility = View.GONE
                    }
                }

            })

        }
    }

    fun formValidateHandler():Account?{
        var result:Boolean = true
        val generalErrorText = "Lütfen bu alanı doldurun"
        val name = binding.nameTextLayout.editText?.text.toString()
        val surname = binding.surnameTextLayout.editText?.text.toString()
        val phoneNumber = binding.phoneEditText.text.toString()
        val address = binding.addressEditText.text.toString()
        val province = binding.provinceTextLayout.editText?.text.toString()
        val county = binding.countyTextLayout.editText?.text.toString()
        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()

        if(name.isBlank()){
            binding.nameTextLayout.error = generalErrorText
            result  = false
        }
        if(surname.isBlank()){
            binding.surnameTextLayout.error = generalErrorText
            result  = false
        }
        if (phoneNumber.isBlank()) {
            binding.phoneInputLayout.error = generalErrorText
            result  = false
        }
        if (address.isBlank()){
            binding.addressInputLayout.error = generalErrorText
            result  = false
        }
        if (province.isBlank()){
            binding.provinceTextLayout.error = generalErrorText
            result  = false
        }
        if (county.isBlank()) {
            binding.countyTextLayout.error = generalErrorText
            result  = false
        }
        if (!Common.isEmailValid(email)){
            binding.emailInputLayout.error =  "Lütfen email adresinizi doğru giriniz"
            result  = false
        }
        if (password.isBlank()){
            binding.passwordInputLayout.error = generalErrorText
            result  = false
        }

        Handler(Looper.getMainLooper()).postDelayed({
            binding.nameTextLayout.error =""
            binding.surnameTextLayout.error =""
            binding.emailInputLayout.error =""
            binding.passwordInputLayout.error =""
            binding.provinceTextLayout.error =""
            binding.countyTextLayout.error =""
            binding.addressInputLayout.error =""
            binding.phoneInputLayout.error =""

        },2000)
        result && return Account(
            name=name,
            surname=surname,
            phone_number= phoneNumber,
            address = address,
            email = email,
            province = province,
            county = county,
            password = password,
            orders = null,
            _id = null,
        )
        return null
    }
}