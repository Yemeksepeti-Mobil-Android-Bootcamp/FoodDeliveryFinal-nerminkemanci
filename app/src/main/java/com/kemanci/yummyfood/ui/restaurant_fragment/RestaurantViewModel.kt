package com.kemanci.yummyfood.ui.restaurant_fragment

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kemanci.yummyfood.model.ApiRepository
import com.kemanci.yummyfood.model.entity.Order
import com.kemanci.yummyfood.model.entity.Restaurant
import com.kemanci.yummyfood.utils.Resource
import com.kemanci.yummyfood.utils.SharedPreferencesUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(private val apiRepository: ApiRepository):ViewModel() {

    private val token:String? = apiRepository.getString(SharedPreferencesUtil.TOKEN)
    fun getToken():String? = this.token

    fun getRestaurantById(id:String):LiveData<Resource<Restaurant>>{
        return apiRepository.getRestaurantById(id)
    }

    fun wishOrder(account_id:String,restaurant_id:String,food_id:String):LiveData<Resource<Order>>{
        val order:Order = Order(
            _id = null,
            account_id=account_id,
            restaurant_id  = restaurant_id,
            food_id = food_id,
            foodName = "",
            foodPrice = "",
            restaurantName = "",
            restaurantPoint = "",
            order_date = null
        )
        return apiRepository.wishOrder(order)
    }
}