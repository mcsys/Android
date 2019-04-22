package com.passionvirus.sample.sample.tddmvvm.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        private lateinit var retrofit: Retrofit
        fun getClient(baseUrl: String): Retrofit {
            if (!::retrofit.isInitialized) {
                retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return retrofit
        }
    }
}