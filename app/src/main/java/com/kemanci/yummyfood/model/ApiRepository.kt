package com.kemanci.yummyfood.model

import com.kemanci.yummyfood.model.entity.Account
import com.kemanci.yummyfood.model.entity.LoginRequest
import com.kemanci.yummyfood.model.entity.Order
import com.kemanci.yummyfood.model.local.LocalDataSource
import com.kemanci.yummyfood.model.remote.RemoteDataSource
import com.kemanci.yummyfood.utils.performNetworkOperation
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private var remoteDataSource: RemoteDataSource,
    private var localDataSource: LocalDataSource
    ) {

    // TODO: REMOTE
    fun login(loginRequest: LoginRequest) = performNetworkOperation { remoteDataSource.login(loginRequest) }
    fun signup(account:Account) = performNetworkOperation { remoteDataSource.signup(account) }
    fun profile(token:String) = performNetworkOperation { remoteDataSource.profile(token = "Bearer ".plus(token)) }
    fun deleteAccount(id:String) = performNetworkOperation { remoteDataSource.deleteAccount(id) }

    fun getRestaurantsList() = performNetworkOperation { remoteDataSource.getRestaurantsList() }
    fun getRestaurantByProvince(province:String) = performNetworkOperation { remoteDataSource.getRestaurantByProvince(province) }
    fun getFoodListByRestaurantId(id: String) = performNetworkOperation { remoteDataSource.getFoodListByRestaurantId(id) }

    fun getOrderByAccountId(id: String) = performNetworkOperation { remoteDataSource.getOrderByAccountId(id) }
    fun wishOrder(order: Order) = performNetworkOperation { remoteDataSource.wishOrder(order) }

    fun getRestaurantById(id: String) = performNetworkOperation { remoteDataSource.getRestaurantById(id) }
    fun getFoodById(id: String) = performNetworkOperation { remoteDataSource.getFoodById(id) }

    // TODO: LOCAL
    fun saveString(key: String, data: String) {
        this.localDataSource.saveString(key, data)
    }

    fun getString(key: String): String? = this.localDataSource.getString(key)

    fun saveInt(key: String, data: Int) {
        this.localDataSource.saveInt(key, data)
    }

    fun getInt(key: String): Int = this.localDataSource.getInt(key)

    fun saveBoolean(key: String, data: Boolean) {
        this.localDataSource.saveBoolean(key, data)
    }

    fun getBoolean(key: String): Boolean = this.localDataSource.getBoolean(key)
}