package com.efremov.anyquestions.di

import android.content.Context
import com.efremov.anyquestions.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: App) {

    @Provides @Singleton fun provideApplicationContext() : Context = application
}