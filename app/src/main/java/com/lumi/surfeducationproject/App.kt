package com.lumi.surfeducationproject

import android.app.Application
import com.lumi.surfeducationproject.di.components.AppComponent

class App: Application() {

    private var appComponent: AppComponent? = null;

    companion object {
        lateinit var instance: App;
    }

    override fun onCreate() {
        super.onCreate()
        instance = this;
    }


}