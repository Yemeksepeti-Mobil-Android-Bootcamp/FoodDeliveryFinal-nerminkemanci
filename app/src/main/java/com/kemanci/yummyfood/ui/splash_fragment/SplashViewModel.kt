package com.kemanci.yummyfood.ui.splash_fragment

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kemanci.yummyfood.model.ApiRepository
import com.kemanci.yummyfood.model.entity.Account
import com.kemanci.yummyfood.model.local.SharedPrefManager
import com.kemanci.yummyfood.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val apiRepository: ApiRepository) : ViewModel() {
    private var token: String? = apiRepository.getString(SharedPrefManager.TOKEN)
    private var firstLaunch: MutableLiveData<Boolean> = MutableLiveData()

    fun getToken(): String? = this.token
    fun isFirstLaunch(): LiveData<Boolean> = this.firstLaunch



    fun profile(token:String):LiveData<Resource<Account>>{
        return  apiRepository.profile(token)
    }

    fun saveFirstLaunch() {
        apiRepository.saveBoolean(SharedPrefManager.FIRST_LAUNCH, false)
    }

    fun navigationDone() {
        this.firstLaunch.value = null
    }
}