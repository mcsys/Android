package com.passionvirus.sample.sample.tddmvvm.api

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.POST

interface ApiService {
    @POST
    abstract fun getContributors(): Call<JsonObject>


}