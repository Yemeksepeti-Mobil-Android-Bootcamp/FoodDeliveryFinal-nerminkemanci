package com.kemanci.yummyfood.ui.settings_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kemanci.yummyfood.model.ApiRepository
import com.kemanci.yummyfood.model.entity.Account
import com.kemanci.yummyfood.model.local.SharedPrefManager
import com.kemanci.yummyfood.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val apiRepository: ApiRepository):ViewModel() {

    private var token: String = apiRepository.getString(SharedPrefManager.TOKEN).toString()
    fun getToken(): String = this.token

    fun updateProfile(id:String,account: Account):LiveData<Resource<Account>>{
       apiRepository.deleteAccount(id)
       val accountResponse = apiRepository.signup(account = account)
       return apiRepository.signup(account = account)
    }
}