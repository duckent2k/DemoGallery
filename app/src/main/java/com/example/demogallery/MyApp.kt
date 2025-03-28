package com.example.demogallery

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        val sharedPreferenceManager = SharedPreferenceManager(this)
        AppCompatDelegate.setDefaultNightMode(sharedPreferenceManager.themeFlag[sharedPreferenceManager.theme])
    }
}