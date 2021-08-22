package com.kemanci.yummyfood.ui.restaurant_list_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kemanci.yummyfood.model.ApiRepository
import com.kemanci.yummyfood.model.entity.Restaurant
import com.kemanci.yummyfood.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RestaurantListViewModel @Inject constructor(private val apiRepository: ApiRepository):ViewModel() {
    
    fun getRestaurantByProvince(province:String):LiveData<Resource<ArrayList<Restaurant>>>{
        return apiRepository.getRestaurantByProvince(province)
    }

    fun getRestaurantsList():LiveData<Resource<ArrayList<Restaurant>>>{
        return apiRepository.getRestaurantsList()
    }
}