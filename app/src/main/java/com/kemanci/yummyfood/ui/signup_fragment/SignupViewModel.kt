package com.kemanci.yummyfood.ui.signup_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kemanci.yummyfood.model.ApiRepository
import com.kemanci.yummyfood.model.entity.Account
import com.kemanci.yummyfood.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(private val apiRepository: ApiRepository):ViewModel() {
    fun signup(account: Account):LiveData<Resource<Account>>{
       return apiRepository.signup(account)
    }
}