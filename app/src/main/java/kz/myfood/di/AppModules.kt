package kz.myfood.di

import android.content.Context
import android.content.SharedPreferences
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kz.myfood.repositories.ILocalRepository
import kz.myfood.repositories.LocalStorageImpl
import kz.myfood.ui.login.AuthViewModel
import kz.myfood.ui.profile.ProfileViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {
    single { createSharedPreference(androidContext()) }

    single { LocalStorageImpl(get()) as ILocalRepository }
}

val viewModelModule = module (override = true) {
    viewModel { ProfileViewModel() }
    viewModel { AuthViewModel(get()) }
}

fun <T> applySchedulersSingle(): SingleTransformer<T, T> {
    return object : SingleTransformer<T, T> {
        override fun apply(upstream: Single<T>): SingleSource<T> {
            return upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }
}

fun createSharedPreference(context: Context): SharedPreferences {
    val sharedPreferences = context.getSharedPreferences("localDB", Context.MODE_PRIVATE)
    return sharedPreferences
}

val appModules = listOf(networkModule, viewModelModule)

