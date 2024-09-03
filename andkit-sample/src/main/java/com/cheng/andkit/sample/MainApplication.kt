package com.cheng.andkit.sample

import android.app.Application
import android.util.Log
import com.cheng.andkit.AndKit

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        AndKit.init(applicationContext, Log.VERBOSE)
    }

}
