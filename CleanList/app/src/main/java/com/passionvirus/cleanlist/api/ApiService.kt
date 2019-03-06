package com.passionvirus.cleanlist.api

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {
    @GET("ability")
    abstract fun getPages() : Call<JsonObject>

    @GET("ability/{page}")
    abstract fun getDataByPage(@Path("page") page : Int) : Call<JsonObject>

}