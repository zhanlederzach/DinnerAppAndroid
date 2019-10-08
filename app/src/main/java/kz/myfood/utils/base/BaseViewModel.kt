package kz.myfood.utils.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel: ViewModel() {

    protected var disposables = CompositeDisposable()

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun removeDisposables(){
        disposables.clear()
    }
}