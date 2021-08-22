package com.kemanci.yummyfood.model.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AccountResponse(
    @SerializedName("account")
    var account:Account,
    @SerializedName("token")
    var token:String
):Parcelable