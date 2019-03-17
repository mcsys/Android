package com.passionvirus.cleanlist.api

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url


interface ApiService {
    @GET
    abstract fun getAbilityList(@Url url : String) : Call<JsonObject>

    @GET
    abstract fun getAbility(@Url url : String) : Call<JsonObject>

}