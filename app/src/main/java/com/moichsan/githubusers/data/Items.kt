package com.moichsan.githubusers.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Items(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("login") val login: String? = null,
    @SerializedName("avatar_url") val avatar: String? = null,
    @SerializedName("followers_url") val followers_url: String? = null,
    @SerializedName("url") val url: String? = null,
    @SerializedName("followers") val followers: String? = null,
    @SerializedName("following") val following: String? = null,
    @SerializedName("following_url") val following_url: String? = null
) : Parcelable