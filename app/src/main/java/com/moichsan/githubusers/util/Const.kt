package com.moichsan.githubusers.util

object Const {
    const val CT_FOLLOWING = 1
    const val CT_FOLLOWERS = 2

    //Type
    const val TYPE_FOLLOWERS = "followers"
    const val TYPE_FOLLOWING = "following"

    //Room
    const val TABLE_NAME = "favorite_table"
    const val COLUMN_ID = "id"
    const val COLUMN_ID_GITHUB = "id_github"
    const val COLUMN_NAME = "name"
    const val COLUMN_URL = "url"
    const val COLUMN_AVATAR = "avatar_url"
    const val COLUMN_FOLLOWERS = "followers"
    const val COLUMN_FOLLOWING = "following"

    //URI Content Provider
    const val URI_AUTHORITY = "com.moichsan.githubusers"
    private const val SCHEME = "content"
    const val FAVORITE_URI = "$SCHEME://$URI_AUTHORITY/$TABLE_NAME"
}