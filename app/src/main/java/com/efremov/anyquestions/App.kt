package com.efremov.anyquestions

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        val assetManager = assets
        FontManager.init(assetManager)
    }
}