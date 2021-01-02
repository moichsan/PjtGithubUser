package com.moichsan.consumerapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Items(
    val idGithub: Int? = null,
    var id: Int? = 0,
    val login: String? = null,
    val avatar_url: String? = null,
    val url: String? = null,
    val followers: Int? = null,
    val following: Int? = null
) : Parcelable