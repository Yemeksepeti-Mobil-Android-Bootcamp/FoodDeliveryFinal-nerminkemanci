package com.kemanci.yummyfood.ui.home_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kemanci.yummyfood.model.ApiRepository
import com.kemanci.yummyfood.model.entity.Account
import com.kemanci.yummyfood.model.entity.Food
import com.kemanci.yummyfood.model.entity.Restaurant
import com.kemanci.yummyfood.model.local.SharedPrefManager
import com.kemanci.yummyfood.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val  apiRepository: ApiRepository):ViewModel() {
    private var token: String = apiRepository.getString(SharedPrefManager.TOKEN).toString()
    fun getToken(): String = this.token
    
    fun profile(token:String): LiveData<Resource<Account>> {
        return  apiRepository.profile(token)
    }

    fun getRestaurantById(id:String):LiveData<Resource<Restaurant>>{
        return apiRepository.getRestaurantById(id)
    }

    fun getFoodById(id: String):LiveData<Resource<Food>>{
        return apiRepository.getFoodById(id)
    }
}