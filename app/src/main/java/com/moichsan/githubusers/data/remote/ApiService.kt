package com.moichsan.githubusers.data.remote

import com.moichsan.githubusers.base.BaseResponse
import com.moichsan.githubusers.data.Items
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    fun getSearchUser(@Query("q")query: String): Observable<BaseResponse<Items>>

    @GET("users/{login}")
    fun getDetailsUsers(@Path("login") login: String): Observable<Items>

    @GET("users/{login}/{type}")
    fun getUsersFollowersFollowings(
        @Path("login") login: String,
        @Path("type") type: String
    ): Observable<List<Items>>


}