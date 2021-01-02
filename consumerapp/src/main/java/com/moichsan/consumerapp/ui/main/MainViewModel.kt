package com.moichsan.consumerapp.ui.main

import android.content.Context
import androidx.lifecycle.ViewModel
import com.moichsan.consumerapp.data.repository.GithubRepository

class MainViewModel : ViewModel() {
    private val repository: GithubRepository = GithubRepository()

    fun getAllLocalData(context: Context) = repository.getAllLocalData(context)
}