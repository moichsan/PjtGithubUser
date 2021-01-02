package com.moichsan.githubusers.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import androidx.core.net.toUri
import com.moichsan.githubusers.MyApplication
import com.moichsan.githubusers.data.local.room.AppDatabase
import com.moichsan.githubusers.data.local.room.FavDao
import com.moichsan.githubusers.util.Const.FAVORITE_URI
import com.moichsan.githubusers.util.Const.TABLE_NAME
import com.moichsan.githubusers.util.Const.URI_AUTHORITY
import com.moichsan.githubusers.util.toFavoriteModel

class FavoriteGithubProvider : ContentProvider() {
    private var favoriteDao: FavDao? = null

    init {
        MyApplication.applicationContext()?.let { it ->
            AppDatabase.getInstance(it).let {
                favoriteDao = it?.movieDao()
            }
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val id = when (MATCHER.match(uri)) {
            FAVORITE -> {
                values?.let {
                    favoriteDao?.insertToLocalData(it.toFavoriteModel())
                } ?: 0
            }
            else -> 0
        }
        context?.contentResolver?.notifyChange(FAVORITE_URI.toUri(), null)
        return Uri.parse("$FAVORITE_URI/$id")
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        return when (MATCHER.match(uri)) {
            FAVORITE -> favoriteDao?.getAllLocalData()
            FAVORITE_ID -> uri.lastPathSegment?.toInt()?.let { favoriteDao?.getLocalDataById(it) }
            else -> null
        }
    }

    override fun onCreate(): Boolean = true

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int = 0

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {

        val count: Int = when (MATCHER.match(uri)) {
            FAVORITE_ID -> {
                uri.lastPathSegment?.toInt()?.let {
                    favoriteDao?.deleteFromLocalData(it)
                } ?: 0
            }
            else -> 0
        }
        context?.contentResolver?.notifyChange(FAVORITE_URI.toUri(), null)
        return count
    }

    override fun getType(uri: Uri): String? = null

    companion object {
        private const val FAVORITE = 1
        private const val FAVORITE_ID = 2

        private val MATCHER = UriMatcher(UriMatcher.NO_MATCH)

        init {
            MATCHER.addURI(URI_AUTHORITY, TABLE_NAME, FAVORITE)
            MATCHER.addURI(URI_AUTHORITY, "$TABLE_NAME/#", FAVORITE_ID)
        }
    }

}