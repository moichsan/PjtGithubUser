package com.moichsan.githubusers.data.repository

import android.content.Context
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.moichsan.githubusers.MyApplication
import com.moichsan.githubusers.data.Items
import com.moichsan.githubusers.data.local.room.AppDatabase
import com.moichsan.githubusers.data.local.room.FavDao
import com.moichsan.githubusers.util.Const.FAVORITE_URI
import com.moichsan.githubusers.util.toContentValues
import com.moichsan.githubusers.util.toFavoriteModel
import com.moichsan.githubusers.util.toListFavoriteModel

class LocalRepository {
    private var favoriteDao: FavDao? = null

    init {
        MyApplication.applicationContext()?.let { it ->
            AppDatabase.getInstance(it).let {
                favoriteDao = it?.movieDao()
            }
        }
    }

    fun insertToLocalData(items: Items, context: Context) {
        context.contentResolver.insert(FAVORITE_URI.toUri(), items.toContentValues())
    }

    fun getAllLocalData(context: Context): LiveData<List<Items>> {
        val liveData = MutableLiveData<List<Items>>()
        val cursor = context.contentResolver.query(FAVORITE_URI.toUri(), null, null, null, null)
        cursor?.let {
            liveData.postValue(it.toListFavoriteModel())
            cursor.close()
        }
        return liveData
    }

    fun getLocalDataById(user_id: Int, context: Context, items: (Items) -> Unit) {
        val uri = "$FAVORITE_URI/$user_id".toUri()
        val cursor = context.contentResolver.query(uri, null, null, null, null)
        cursor?.let {
            if (cursor.moveToFirst()) {
                items(it.toFavoriteModel())
            }
            cursor.close()
        }
    }

    fun deleteFromLocalData(id: Int, context: Context) {
        val uri = "$FAVORITE_URI/$id".toUri()
        context.contentResolver.delete(uri, null, null)
    }
}