package com.moichsan.consumerapp.data.remote


import com.moichsan.consumerapp.data.Items
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*

interface ApiService {

    @GET("users/{login}/{type}")
    fun getUsersFollowersFollowings(
        @Path("login") login: String,
        @Path("type") type: String
    ): Observable<List<Items>>

}