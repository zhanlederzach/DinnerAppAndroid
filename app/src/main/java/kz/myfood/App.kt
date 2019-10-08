package kz.myfood

import androidx.multidex.MultiDexApplication
import kz.myfood.di.networkModule
import kz.myfood.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : MultiDexApplication() {

    val appModules = listOf(networkModule, viewModelModule)

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModules)
        }
    }

}