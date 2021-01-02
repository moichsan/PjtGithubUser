package com.moichsan.githubusers.util

import android.content.ContentValues
import android.database.Cursor
import com.moichsan.githubusers.data.Items
import com.moichsan.githubusers.util.Const.COLUMN_AVATAR
import com.moichsan.githubusers.util.Const.COLUMN_FOLLOWERS
import com.moichsan.githubusers.util.Const.COLUMN_FOLLOWING
import com.moichsan.githubusers.util.Const.COLUMN_ID
import com.moichsan.githubusers.util.Const.COLUMN_ID_GITHUB
import com.moichsan.githubusers.util.Const.COLUMN_NAME
import com.moichsan.githubusers.util.Const.COLUMN_URL
import java.util.ArrayList

fun ContentValues.toFavoriteModel(): Items =
    Items(
        idGithub = getAsInteger(COLUMN_ID_GITHUB),
        id = getAsInteger(COLUMN_ID),
        login = getAsString(COLUMN_NAME),
        avatar_url = getAsString(COLUMN_AVATAR),
        url = getAsString(COLUMN_URL),
        followers = getAsInteger(COLUMN_FOLLOWERS),
        following = getAsInteger(COLUMN_FOLLOWING)
    )

fun Items.toContentValues(): ContentValues =
    android.content.ContentValues().apply {
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