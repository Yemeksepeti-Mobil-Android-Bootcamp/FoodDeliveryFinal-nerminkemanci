package com.kemanci.yummyfood

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class YummyFoodApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}