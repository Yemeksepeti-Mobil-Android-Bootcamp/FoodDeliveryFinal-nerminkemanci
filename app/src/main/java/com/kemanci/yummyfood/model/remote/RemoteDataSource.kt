package com.kemanci.yummyfood.model.remote

import com.kemanci.yummyfood.model.entity.Account
import com.kemanci.yummyfood.model.entity.LoginRequest
import com.kemanci.yummyfood.model.entity.Order
import com.kemanci.yummyfood.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) : BaseDataSource(){
    suspend fun login(loginRequest: LoginRequest) = getResult { apiService.login(loginRequest) }
    suspend fun signup(account: Account) = getResult { apiService.signup(account = account) }
    suspend fun profile(token:String) = getResult { apiService.profile(token) }
    suspend fun deleteAccount(id:String) = getResult { apiService.deleteAccount(id) }

    suspend fun getRestaurantsList() = getResult { apiService.getRestaurantsList() }
    suspend fun getRestaurantByProvince(province:String) = getResult { apiService.getRestaurantByProvince(province) }

    suspend fun getFoodListByRestaurantId(id:String) = getResult { apiService.getFoodListByRestaurantId(id) }
    suspend fun getOrderByAccountId(id: String) = getResult { apiService.getOrderByAccountId(id) }
    suspend fun wishOrder(order: Order) = getResult { apiService.wishOrder(order) }

    suspend fun getRestaurantById(id:String) = getResult { apiService.getRestaurantById(id) }
    suspend fun getFoodById(id: String) = getResult { apiService.getFoodById(id) }
}