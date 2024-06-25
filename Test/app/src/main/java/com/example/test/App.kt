package com.example.test

import android.app.Application
import com.example.test.di.AppComponent
import com.example.test.di.DaggerAppComponent

class App : Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}