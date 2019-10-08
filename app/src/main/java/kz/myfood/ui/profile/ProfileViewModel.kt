package kz.myfood.ui.profile

import androidx.lifecycle.MutableLiveData
import io.reactivex.Single
import kz.myfood.di.applySchedulersSingle
import kz.myfood.model.ProfileInfo
import kz.myfood.model.StatisticsInfo
import kz.myfood.utils.base.BaseViewModel

class ProfileViewModel: BaseViewModel() {

    val liveData by lazy {
        MutableLiveData<ProfileData>()
    }

    fun getProfileInfo(){
        disposables.add(
            dataFromNetwork()
                .compose(applySchedulersSingle())
                .doOnSubscribe{ liveData.value = ProfileData.ShowLoading }
                .doFinally { liveData.value = ProfileData.HideLoading }
                .subscribe(
                    { result -> liveData.value = ProfileData.Result(result) },
                    { error -> liveData.value = ProfileData.Error(error.message) }
                )
        )
    }

    private fun dataFromNetwork(): Single<ProfileInfo> {
        return Single.fromCallable{
            ProfileInfo(1, "Zhanel", StatisticsInfo(1, "Trainings"))
        }
    }

    sealed class ProfileData {
        object HideLoading: ProfileData()
        object ShowLoading: ProfileData()
        data class Result(val profileInfo: ProfileInfo): ProfileData()
        data class Error(val message: String?): ProfileData()
    }
}