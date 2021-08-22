package com.kemanci.yummyfood.model.entity

import android.os.Parcelable
import androidx.versionedparcelable.ParcelImpl
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Food(
    @SerializedName("_id")
    var _id:String,
    @SerializedName("name")
    var name:String,
    @SerializedName("price")
    var price:String,
    @SerializedName("restaurant_id")
    var restaurant_id:String,
    @SerializedName("content")
    var content:String,
    @SerializedName("image_url")
    var image_url:String
): Parcelable