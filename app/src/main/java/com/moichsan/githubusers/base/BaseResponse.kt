package com.moichsan.githubusers.base

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("items") var itemList: List<T>
)