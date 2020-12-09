package com.moichsan.githubusers.data.remote

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.moichsan.githubusers.BuildConfig.BASE_URL
import com.moichsan.githubusers.BuildConfig.TOKEN
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    private fun provideInterceptor() = Interceptor.invoke { chain ->
        val url = chain.request().url.newBuilder().build()
        val request = chain.request().newBuilder().addHeader("Authorization", TOKEN).url(url).build()
        chain.proceed(request)
    }

    private fun provideOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(provideInterceptor())
        .retryOnConnectionFailure(true)
        .connectTimeout(60, TimeUnit.SECONDS)
        .build()

    fun getApiService(): ApiService = Retrofit.Builder()
        .client(provideOkHttpClient())
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(ApiService::class.java)
}