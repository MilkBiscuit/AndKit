package com.cheng.httpproject.ui

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
        val config = RealmConfiguration.Builder()
                .name("database.realm")
                .build()
        Realm.setDefaultConfiguration(config)
    }

}