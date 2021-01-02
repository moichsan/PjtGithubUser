package com.moichsan.githubusers.ui.favorite

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.moichsan.githubusers.data.Items
import com.moichsan.githubusers.data.repository.LocalRepository

class FavoriteViewModel : ViewModel() {
    private val localRepository = LocalRepository()

    fun getAllLocalData(context: Context): LiveData<List<Items>> =
        localRepository.getAllLocalData(context)

}