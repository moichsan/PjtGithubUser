package com.moichsan.consumerapp.data.repository

import android.content.Context
import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.moichsan.consumerapp.base.ApiObserver
import com.moichsan.consumerapp.data.Items
import com.moichsan.consumerapp.data.remote.ApiClient
import com.moichsan.consumerapp.util.AppSchedulerProviders
import com.moichsan.consumerapp.util.Const.FAVORITE_URI
import com.moichsan.consumerapp.util.toContentValues
import com.moichsan.consumerapp.util.toListFavoriteModel
import io.reactivex.disposables.CompositeDisposable

class GithubRepository {
    private val apiService = ApiClient.getApiService()
    private val compositeDisposable = CompositeDisposable()
    private val androidSchedulers = AppSchedulerProviders()
    val networkState = MutableLiveData<NetworkState>()

    fun getAllLocalData(context: Context): LiveData<List<Items>> {
        val liveData = MutableLiveData<List<Items>>()
        val cursor = context.contentResolver.query(FAVORITE_URI.toUri(), null, null, null, null)
        cursor?.let {
            Log.i("status", "sukses")
            liveData.postValue(it.toListFavoriteModel())
            cursor.close()
        }
        return liveData
    }

    fun insertToLocalData(items: Items, context: Context) {
        context.contentResolver.insert(FAVORITE_URI.toUri(), items.toContentValues())
    }

    fun deleteFromLocalData(id: Int, context: Context) {
        val uri = "$FAVORITE_URI/$id".toUri()
        context.contentResolver.delete(uri, null, null)
    }

    fun getUserFollowersFollowing(login: String, type: String): LiveData<List<Items>> {
        val items = MutableLiveData<List<Items>>()
        networkState.postValue(NetworkState.LOADING)
        apiService.getUsersFollowersFollowings(login, type)
            .subscribeOn(androidSchedulers.io())
            .observeOn(androidSchedulers.ui())
            .subscribe(object : ApiObserver<List<Items>>(compositeDisposable) {
                override fun onApiSuccess(data: List<Items>) {
                    if (!data.isNullOrEmpty()) {
                        networkState.postValue(NetworkState.LOADED)
                        items.postValue(data)
                    } else networkState.postValue(NetworkState.EMPTY)
                }

                override fun onApiError(error: Throwable) {
                    networkState.postValue(NetworkState.ERROR)
                }

            })
        return items
    }

}