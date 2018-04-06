package com.example.baptiste.smack.Controllers

import android.app.Application
import com.example.baptiste.smack.Utilities.SharedPrefs

/**
 * Created by Baptiste on 06/04/2018.
 */
class App : Application() {

    companion object {
        lateinit var prefs : SharedPrefs
    }

    override fun onCreate() {
        prefs = SharedPrefs(applicationContext
        )
        super.onCreate()
    }
}