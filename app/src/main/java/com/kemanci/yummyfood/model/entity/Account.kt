package com.kemanci.yummyfood.model.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class Account(
    @SerializedName("_id")
    var _id:String?,

    @SerializedName("name")
    var name:String,

    @SerializedName("surname")
    var surname:String,

    @SerializedName("email")
    var email:String,

    @SerializedName("password")
    var password:String,

    @SerializedName("orders")
    var orders:ArrayList<Order>?,

    @SerializedName("province")
    var province:String,

    @SerializedName("county")
    var county:String,

    @SerializedName("address")
    var address:String,

    @SerializedName("phone_number")
    var phone_number:String
):Parcelable