package com.moichsan.consumerapp.ui.detail

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.moichsan.consumerapp.data.Items
import com.moichsan.consumerapp.data.repository.GithubRepository
import com.moichsan.consumerapp.data.repository.NetworkState
import com.moichsan.consumerapp.util.Const.TYPE_FOLLOWERS
import com.moichsan.consumerapp.util.Const.TYPE_FOLLOWING


class DetailViewModel : ViewModel() {
    private val repository = GithubRepository()
    val networkStates: LiveData<NetworkState> by lazy {
        repository.networkState
    }

    fun getUsersFollowers(login: String) =
        repository.getUserFollowersFollowing(login, TYPE_FOLLOWERS)

    fun getUsersFollowing(login: String) =
        repository.getUserFollowersFollowing(login, TYPE_FOLLOWING)

    fun addToFavorite(items: Items, context: Context) {
        repository.insertToLocalData(items, context)
    }

    fun removeFavorite(id: Int, context: Context) {
        repository.deleteFromLocalData(id, context)
    }

}