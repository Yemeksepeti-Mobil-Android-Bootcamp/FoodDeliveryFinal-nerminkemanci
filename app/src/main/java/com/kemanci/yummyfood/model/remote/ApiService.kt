package com.kemanci.yummyfood.model.remote

import com.kemanci.yummyfood.model.entity.*
import retrofit2.Response
import retrofit2.http.*
import java.util.*
import kotlin.collections.ArrayList

interface ApiService {

    // TODO: ACCOUNT REQ
    @Headers("Content-Type: application/json")
    @POST("/account/login")
    suspend fun login(@Body loginRequest: LoginRequest):Response<AccountResponse>

    @POST("/account")
    suspend fun signup(@Body account:Account):Response<Account>

    @GET("account/profile")
    suspend fun profile(@Header("Authorization") token:String):Response<Account>

    @DELETE("account")
    suspend fun deleteAccount(@Path("id") id:String):Response<Objects>



    // TODO: RESTAURANT REQ
    @GET("restaurant/all")
    suspend fun getRestaurantsList():Response<ArrayList<Restaurant>>

    @GET("restaurant")
    suspend fun getRestaurantByProvince(@Query("province") province:String):Response<ArrayList<Restaurant>>


    // TODO: FOOD REQ
    @GET("food")
    suspend fun getFoodListByRestaurantId(@Query("restaurant_id") restaurant_id:String):Response<ArrayList<Food>>

    // TODO: ORDERS REQ
    @GET("order")
    suspend fun  getOrderByAccountId(@Query("account_id") account_id:String):Response<ArrayList<Order>>

    @POST("order")
    suspend fun wishOrder(@Body order: Order):Response<Order>

    @GET("restaurant/{id}")
    suspend fun getRestaurantById(@Path("id") id:String):Response<Restaurant>

    @GET("food/{id}")
    suspend fun getFoodById(@Path("id") id:String):Response<Food>
}