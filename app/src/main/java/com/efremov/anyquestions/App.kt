package com.efremov.anyquestions

import android.app.Application

class App : Application() {

    companion object {

        var db: QuestionDataBase? = null
    }

    override fun onCreate() {
        super.onCreate()

        val assetManager = assets
        FontManager.init(assetManager)

        db = QuestionDataBase.getInstance(this)
    }
}