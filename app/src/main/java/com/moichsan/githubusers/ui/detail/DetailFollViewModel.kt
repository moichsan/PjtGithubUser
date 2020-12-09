package com.moichsan.githubusers.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moichsan.githubusers.data.Items
import com.moichsan.githubusers.data.repository.NetState
import com.moichsan.githubusers.data.repository.Repository

class DetailFollViewModel : ViewModel() {
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

}