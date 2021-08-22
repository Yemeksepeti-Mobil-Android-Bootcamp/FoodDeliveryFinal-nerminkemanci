package com.kemanci.yummyfood.ui.signin_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kemanci.yummyfood.model.ApiRepository
import com.kemanci.yummyfood.model.entity.Account
import com.kemanci.yummyfood.model.entity.AccountResponse
import com.kemanci.yummyfood.model.entity.LoginRequest
import com.kemanci.yummyfood.utils.Resource
import com.kemanci.yummyfood.utils.SharedPreferencesUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SigninViewModel @Inject constructor(private val apiRepository: ApiRepository):ViewModel(){
    fun login(email:String,password:String):LiveData<Resource<AccountResponse>>{
        val request:LoginRequest = LoginRequest(email,password)
        return apiRepository.login(request)
    }
    fun profile(token:String):LiveData<Resource<Account>>{
        return  apiRepository.profile(token)
    }

    fun saveToken(token:String){
        apiRepository.saveString(SharedPreferencesUtil.TOKEN,token)
    }
}