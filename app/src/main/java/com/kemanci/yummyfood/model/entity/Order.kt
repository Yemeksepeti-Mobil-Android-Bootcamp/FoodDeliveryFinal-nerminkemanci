package com.kemanci.yummyfood.model.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class Order(
    @SerializedName("_id")
    var _id:String?,
    @SerializedName("restaurant_id")
    var restaurant_id:String,
    @SerializedName("food_id")
    var food_id:String,
    @SerializedName("account_id")
    var account_id:String,
    @SerializedName("order_date")
    var order_date:String?,
    var foodName:String,
    var foodPrice:String,
    var restaurantName:String,
    var restaurantPoint:String
):Parcelable