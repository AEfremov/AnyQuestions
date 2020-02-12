package com.efremov.anyquestions.di

import com.efremov.anyquestions.App
import com.efremov.anyquestions.di.viewmodel.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(application: App)
}