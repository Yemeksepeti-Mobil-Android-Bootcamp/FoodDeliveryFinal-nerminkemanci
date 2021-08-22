package com.kemanci.yummyfood.model.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class LoginRequest(
    @SerializedName("email")
    var email:String,
    @SerializedName("password")
    var password:String
):Parcelable