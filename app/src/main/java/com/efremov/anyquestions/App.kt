package com.efremov.anyquestions

import android.app.Application
import com.efremov.anyquestions.core.FontManager
import com.efremov.anyquestions.di.ApplicationComponent
import com.efremov.anyquestions.di.ApplicationModule
import com.efremov.anyquestions.di.DaggerApplicationComponent
import com.efremov.anyquestions.features.questions.QuestionDataBase

class App : Application() {

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    companion object {

        var db: QuestionDataBase? = null
        var questionsCount = 0
        var questionForAnswerCount = 0
    }

    override fun onCreate() {
        super.onCreate()
        this.injectMembers()

        val assetManager = assets
        FontManager.init(assetManager)

        db = QuestionDataBase.getInstance(this)
    }

    private fun injectMembers() = appComponent.inject(this)
}