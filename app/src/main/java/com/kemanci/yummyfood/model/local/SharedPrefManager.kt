package com.kemanci.yummyfood.model.local

import android.content.Context
import android.content.SharedPreferences


class SharedPrefManager(context: Context) {
    companion object {
        const val TOKEN = "com.kemanci.yummyfood.TOKEN"
        const val FIRST_LAUNCH = "com.kemanci.yummyfood.first_launch"
    }

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("sharedPreferencesUtil", Context.MODE_PRIVATE)

    fun saveString(key: String, data: String) {
        sharedPreferences.edit().putString(key, data).apply()
    }

    fun getString(key: String): String? = sharedPreferences.getString(key, "-1")

    fun saveInt(key: String, data: Int) {
        sharedPreferences.edit().putInt(key, data).apply()
    }

    fun getInt(key: String): Int = sharedPreferences.getInt(key, -1)

    fun saveBoolean(key: String, data: Boolean) {
        sharedPreferences.edit().putBoolean(key, data).apply()
    }

    fun getBoolean(key: String): Boolean = sharedPreferences.getBoolean(key, true)
}