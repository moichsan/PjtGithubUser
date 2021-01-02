package com.moichsan.githubusers.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.moichsan.githubusers.util.Const.COLUMN_AVATAR
import com.moichsan.githubusers.util.Const.COLUMN_FOLLOWERS
import com.moichsan.githubusers.util.Const.COLUMN_FOLLOWING
import com.moichsan.githubusers.util.Const.COLUMN_ID
import com.moichsan.githubusers.util.Const.COLUMN_ID_GITHUB
import com.moichsan.githubusers.util.Const.COLUMN_NAME
import com.moichsan.githubusers.util.Const.COLUMN_URL
import com.moichsan.githubusers.util.Const.TABLE_NAME
import kotlinx.android.parcel.Parcelize

@Entity(tableName = TABLE_NAME)
@Parcelize
data class Items(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID_GITHUB)
    val idGithub: Int? = null,
    @ColumnInfo(name = COLUMN_ID)
    var id: Int? = 0,
    @ColumnInfo(name = COLUMN_NAME)
    val login: String? = null,
    @ColumnInfo(name = COLUMN_AVATAR)
    val avatar_url: String? = null,
    @ColumnInfo(name = COLUMN_URL)
    val url: String? = null,
    @ColumnInfo(name = COLUMN_FOLLOWERS)
    val followers: Int? = null,
    @ColumnInfo(name = COLUMN_FOLLOWING)
    val following: Int? = null
) : Parcelable