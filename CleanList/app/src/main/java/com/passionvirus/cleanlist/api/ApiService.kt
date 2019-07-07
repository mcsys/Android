package com.passionvirus.cleanlist.api

import com.google.gson.JsonObject
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url


interface ApiService {
    @GET
    // Code - V1
//    abstract fun getAbilityList(@Url url : String) : Call<JsonObject>
    // Code - V2
    abstract fun getAbilityList(@Url url : String) : Single<JsonObject>

    @GET
    abstract fun getAbility(@Url url : String) : Call<JsonObject>

}