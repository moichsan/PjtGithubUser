package com.moichsan.githubusers.ui.detail

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moichsan.githubusers.data.Items
import com.moichsan.githubusers.data.repository.LocalRepository
import com.moichsan.githubusers.data.repository.NetState
import com.moichsan.githubusers.data.repository.Repository

class DetailFollViewModel : ViewModel() {
    private val localRepository = LocalRepository()
    private val repository = Repository()
    val onDetail = MutableLiveData<Items>()
    val onFollowing = MutableLiveData<List<Items>>()
    val onFollowers = MutableLiveData<List<Items>>()
    val netState :LiveData<NetState> by lazy {
        repository.netState
    }

    fun getDetailUser(login: String) {
        repository.getDetailsUser({
            onDetail.postValue(it)
        }, login)
    }

    fun getUserFollwing(login: String) {
        repository.getUserFoll({
            onFollowing.postValue(it)
        }, login, "following")
    }

    fun getUserFollwers(login: String) {
        repository.getUserFoll({
            onFollowers.postValue(it)
        }, login, "followers")
    }

    fun checkFavorite(githubId: Int, context: Context): Boolean {
        var isFavorite = false
        localRepository.getLocalDataById(githubId, context) {
            isFavorite = it.id != null
        }
        return isFavorite
    }

    fun addToFavorite(items: Items, context: Context) {
        localRepository.insertToLocalData(items, context)
    }

    fun removeFavorite(id: Int, context: Context) {
        localRepository.deleteFromLocalData(id, context)
    }

}