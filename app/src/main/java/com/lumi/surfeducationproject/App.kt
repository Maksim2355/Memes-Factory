package com.lumi.surfeducationproject

import android.app.Application
import android.content.Context
import com.lumi.surfeducationproject.di.components.*
import com.lumi.surfeducationproject.di.modules.activity_modules.ActivityModule
import com.lumi.surfeducationproject.di.modules.activity_modules.NavigationMainModule
import com.lumi.surfeducationproject.di.modules.app_modules.*
import com.lumi.surfeducationproject.di.modules.auth_modules.PresenterAuthModule
import com.lumi.surfeducationproject.di.modules.content_modules.AdapterUtilsModule
import com.lumi.surfeducationproject.di.modules.content_modules.PresenterContentModule
import com.lumi.surfeducationproject.di.modules.content_modules.RepositoryContentModule
import com.lumi.surfeducationproject.di.modules.content_modules.TabModule
import com.lumi.surfeducationproject.ui.TabFragment

class App : Application() {

    companion object {
        lateinit var instance: App;
    }

    private lateinit var appComponent: AppComponent
    private var activityComponent: ActivityComponent? = null
    private var fragmentContentComponent: FragmentContentComponent? = null
    private var fragmentAuthComponent: FragmentAuthComponent? = null

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

    fun startActivityComponent(activity: Context): ActivityComponent {
        println("вызов метода startActivity")
        if (activityComponent == null) {
            println("AcitivtyNull создаем компонент активности")
            activityComponent = appComponent.addActivityComponent(
                NavigationMainModule(activity),
                ActivityModule(activity)
            )
        }
        return activityComponent!!
    }

    fun clearActivityComponent() {
        println("очищаем комопнент активности")
        activityComponent = null
    }


    fun getFragmentAuthComponentOrCreateIfNull(): FragmentAuthComponent {
        println("вызов метода комонента авторизации")
        if (fragmentAuthComponent == null) {
            println("Создание компонента авторизации")
            fragmentAuthComponent = activityComponent?.addFragmentAuthComponent(
                PresenterAuthModule()
            )
        }
        return fragmentAuthComponent!!
    }

    fun clearFragmentAuthComponent(){
        println("Очистка компонента авторизации")
        fragmentAuthComponent = null
    }

    fun getFragmentContentComponentOrCreateIfNull(tabFragment: TabFragment? = null): FragmentContentComponent {
        if (fragmentContentComponent == null && tabFragment != null) {
            fragmentContentComponent = activityComponent?.addFragmentContentComponent(
                PresenterContentModule(),
                AdapterUtilsModule(),
                RepositoryContentModule(),
                TabModule(tabFragment)
            )
        }
        return fragmentContentComponent!!
    }

    fun clearFragmentContentComponent() {
        fragmentContentComponent = null
    }

}