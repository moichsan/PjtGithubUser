package com.moichsan.githubusers.data.repository

import androidx.lifecycle.MutableLiveData
import com.moichsan.githubusers.base.ApiObserver
import com.moichsan.githubusers.base.BaseResponse
import com.moichsan.githubusers.data.Items
import com.moichsan.githubusers.data.remote.ApiClient
import com.moichsan.githubusers.util.AppSchedulerProviders
import io.reactivex.disposables.CompositeDisposable

class Repository {
    private val apiService = ApiClient.getApiService()
    private val compositeDisposable = CompositeDisposable()
    private val androidSchedulers = AppSchedulerProviders()
    val netState = MutableLiveData<NetState>()

    fun getSearchUsers(onResult: (List<Items>) -> Unit, query: String) {
        netState.postValue(NetState.LOADING)
        apiService.getSearchUser(query)
            .subscribeOn(androidSchedulers.io())
            .observeOn(androidSchedulers.ui())
            .subscribe(object : ApiObserver<BaseResponse<Items>>(compositeDisposable) {
                override fun onApiSuccess(data: BaseResponse<Items>) {
                    if (!data.itemList.isNullOrEmpty()) {
                        netState.postValue(NetState.LOADED)
                        onResult(data.itemList)
                    } else netState.postValue(NetState.EMPTY)
                }

                override fun onApiError(error: Throwable) {
                    netState.postValue(NetState.ERROR)
                }

            })
    }

    fun getUserFoll (onResult: (List<Items>) -> Unit, login: String, type: String) {
        netState.postValue(NetState.LOADING)
        apiService.getUsersFollowersFollowings(login, type)
            .subscribeOn(androidSchedulers.io())
            .observeOn(androidSchedulers.ui())
            .subscribe(object : ApiObserver<List<Items>>(compositeDisposable) {
                override fun onApiSuccess(data: List<Items>) {
                    if (!data.isNullOrEmpty()) {
                        netState.postValue(NetState.LOADED)
                        onResult(data)
                    } else netState.postValue(NetState.EMPTY)
                }

                override fun onApiError(error: Throwable) {
                    netState.postValue(NetState.ERROR)
                }

            })
    }

    fun getDetailsUser(onResult: (Items) -> Unit, login: String) {
        netState.postValue(NetState.LOADING)
        apiService.getDetailsUsers(login)
            .subscribeOn(androidSchedulers.io())
            .observeOn(androidSchedulers.ui())
            .subscribe(object : ApiObserver<Items>(compositeDisposable) {
                override fun onApiSuccess(data: Items) {
                    netState.postValue(NetState.LOADED)
                    onResult(data)
                }

                override fun onApiError(error: Throwable) {
                    netState.postValue(NetState.ERROR)
                }

            })
    }


}