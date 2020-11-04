package com.lumi.surfeducationproject

import android.app.Application
import android.content.Context
import com.lumi.surfeducationproject.di.components.ActivityComponent
import com.lumi.surfeducationproject.di.components.AppComponent
import com.lumi.surfeducationproject.di.components.DaggerAppComponent
import com.lumi.surfeducationproject.di.components.FragmentComponent
import com.lumi.surfeducationproject.di.modules.*

class App : Application() {

    private lateinit var appComponent: AppComponent
    private var activityComponent: ActivityComponent? = null
    private var fragmentComponent: FragmentComponent? = null


    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .networkModule(NetworkModule())
            .serviceModule(ServiceModule())
            .storageModule(StorageModule())
            .repositoryModule(RepositoryModule())
            .build()
    }

    companion object {
        lateinit var instance: App;
    }

    fun startActivityComponent(activity: Context): ActivityComponent {
        if (activityComponent == null) {
            activityComponent = appComponent.addActivityComponent(
                NavigationMainModule(activity),
                UiManagerModule(activity)
            )
        }
        return activityComponent!!
    }

    fun clearActivityComponent() {
        activityComponent = null
    }

    fun startFragmentComponent(): FragmentComponent {
        if (fragmentComponent == null) {
            fragmentComponent = activityComponent?.addFragmentComponent(
                PresenterModule(),
                AdapterUtilsModule()
            )
        }
        return fragmentComponent!!
    }

    fun clearFragmentComponent() {
        fragmentComponent = null
    }

}