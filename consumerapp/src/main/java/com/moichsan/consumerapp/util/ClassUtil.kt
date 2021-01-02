package com.moichsan.consumerapp.util

import android.content.ContentValues
import android.database.Cursor
import com.moichsan.consumerapp.data.Items
import com.moichsan.consumerapp.util.Const.COLUMN_AVATAR
import com.moichsan.consumerapp.util.Const.COLUMN_FOLLOWERS
import com.moichsan.consumerapp.util.Const.COLUMN_FOLLOWING
import com.moichsan.consumerapp.util.Const.COLUMN_ID
import com.moichsan.consumerapp.util.Const.COLUMN_ID_GITHUB
import com.moichsan.consumerapp.util.Const.COLUMN_NAME
import com.moichsan.consumerapp.util.Const.COLUMN_URL

import java.util.*

fun Items.toContentValues(): ContentValues =
    ContentValues().apply {
        put(COLUMN_AVATAR, avatar_url)
        put(COLUMN_FOLLOWERS, followers)
        put(COLUMN_FOLLOWING, following)
        put(COLUMN_ID, id)
        put(COLUMN_ID_GITHUB, idGithub)
        put(COLUMN_NAME, login)
        put(COLUMN_URL, url)
    }

fun Cursor.toListFavoriteModel(): ArrayList<Items> {
    val listFavorite = ArrayList<Items>()

    apply {
        while (moveToNext()) {
            listFavorite.add(
                this.toFavoriteModel()
            )
        }
    }

    return listFavorite
}

fun Cursor.toFavoriteModel(): Items =
    Items(
        idGithub = getInt(getColumnIndexOrThrow(COLUMN_ID_GITHUB)),
        id = getInt(getColumnIndexOrThrow(COLUMN_ID)),
        login = getString(getColumnIndexOrThrow(COLUMN_NAME)),
        avatar_url = getString(getColumnIndexOrThrow(COLUMN_AVATAR)),
        url = getString(getColumnIndexOrThrow(COLUMN_URL)),
        followers = getInt(getColumnIndexOrThrow(COLUMN_FOLLOWERS)),
        following = getInt(getColumnIndexOrThrow(COLUMN_FOLLOWERS))
    )