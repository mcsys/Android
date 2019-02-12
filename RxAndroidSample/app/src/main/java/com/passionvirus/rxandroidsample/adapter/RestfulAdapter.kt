package com.passionvirus.rxandroidsample.adapter

import com.passionvirus.rxandroidsample.api.GitHubServiceApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RestfulAdapter {
    private val BASE_URI = "https://api.github.com"

    fun getSimpleApi() : GitHubServiceApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URI)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create<GitHubServiceApi>(GitHubServiceApi::class.java)
    }

    fun getServiceApi() : GitHubServiceApi {
        val logInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(logInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl(BASE_URI)
            .build()

        return retrofit.create(GitHubServiceApi::class.java)
    }
}