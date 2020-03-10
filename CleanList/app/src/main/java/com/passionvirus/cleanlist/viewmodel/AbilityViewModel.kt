package com.passionvirus.cleanlist.viewmodel

import android.util.Log
import com.google.gson.JsonObject
import com.passionvirus.cleanlist.api.ApiUtils
import com.passionvirus.cleanlist.api.entity.ApiEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AbilityViewModel(private val name: String, private val url: String) {

    companion object {
        private const val RETRY_COUNT = 3
        private var tryCount = 1
    }

    init {
        getAbility(url)
    }

    fun getAbility(url : String) {
        ApiUtils.getSOService().getAbility(url).enqueue(object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                getAbility(url)
                tryCount++
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.isSuccessful) {
                    // Todo
//                    val result = gson.fromJson(response.body().toString(), ApiEntity.AbilityList::class.java)
                }
                else {
                    if (tryCount < RETRY_COUNT) {
                        getAbility(url)
                        tryCount++
                    }
                    else {
                        tryCount = 1;
                    }
                }
            }
        })
    }
}