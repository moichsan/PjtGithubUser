package com.moichsan.githubusers.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moichsan.githubusers.data.Items
import com.moichsan.githubusers.data.repository.NetState
import com.moichsan.githubusers.data.repository.Repository

class MainViewModel : ViewModel() {
    private val repository = Repository()
    val onSearchSuccess = MutableLiveData<List<Items>>()
    val networkStates: LiveData<NetState> by lazy {
        repository.netState
    }

    fun getSearchUser(query: String) {
        repository.getSearchUsers({
            onSearchSuccess.postValue(it)
        }, query)
    }
}