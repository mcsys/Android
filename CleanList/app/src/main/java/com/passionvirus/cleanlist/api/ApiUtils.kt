package com.passionvirus.cleanlist.api

class ApiUtils {
    companion object {
        private const val BASE_URL = "https://pokeapi.co/api/v2/"

        @JvmStatic
        fun getSOService(): ApiService {
            return RetrofitClient.getClient(BASE_URL).create(ApiService::class.java)
        }
    }
}