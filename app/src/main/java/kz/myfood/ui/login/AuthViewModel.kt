package kz.myfood.ui.login

import androidx.lifecycle.MutableLiveData
import kz.myfood.repositories.ILocalRepository
import kz.myfood.utils.base.BaseViewModel

class AuthViewModel(
    private val localStorage: ILocalRepository
): BaseViewModel() {

    val liveData by lazy {
        MutableLiveData<AuthResult>()
    }

//    init {
//        liveData.value = AuthResult.FirstLaunch(localStorage.isFirstLaunch)
//    }

    fun checkForAuth() {
        liveData.value = AuthResult.UserRegistered(localStorage.isRegistered)
    }

    fun registerUser() {
        localStorage.isRegistered = true
    }

    fun unregisterUser() {
        localStorage.isRegistered = false
    }

    sealed class AuthResult() {
        data class FirstLaunch(val isFirstLaunch: Boolean): AuthResult()
        data class UserRegistered(val isRegistered: Boolean): AuthResult()
    }
}