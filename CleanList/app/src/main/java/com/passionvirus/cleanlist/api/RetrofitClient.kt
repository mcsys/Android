package com.passionvirus.cleanlist.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder
import com.google.gson.Gson



class RetrofitClient {
    companion object {
        private lateinit var retrofit: Retrofit

        @JvmStatic
        fun getClient(baseUrl: String): Retrofit {
            // Code - V2_1
//            val gson = GsonBuilder()
//                .registerTypeAdapter(GithubRepo::class.java, GithubRepoDeserializer())
//                .create()

            if (!::retrofit.isInitialized) {
                retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    // Code - V1
//                    .addConverterFactory(GsonConverterFactory.create(gson))
                    // Code - V2
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            }

            return retrofit
        }
    }
}