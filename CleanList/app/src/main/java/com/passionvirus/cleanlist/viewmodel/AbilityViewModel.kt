package com.passionvirus.cleanlist.viewmodel

import android.util.Log
import com.google.gson.JsonObject
import com.passionvirus.cleanlist.api.ApiUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AbilityViewModel(name : String, url : String) {

    private val RETRY_COUNT = 3
    private var tryCount = 1
    private val name = name
    private val url = url
    init {
        getAbility(url)
    }

    fun getAbility(url : String) {
        Log.d("TEST1234", "getAbi")
        ApiUtils.getSOService().getAbility(url).enqueue(object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                getAbility(url)
                tryCount++
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.isSuccessful) {
                    Log.d("TEST1234", response.body().toString())

//                    val loginResult = gson.fromJson(response.body().toString(), AbilityList::class.java)

                }
                else {
                    getAbility(url)
                    tryCount++
                }
            }
        })
    }
}