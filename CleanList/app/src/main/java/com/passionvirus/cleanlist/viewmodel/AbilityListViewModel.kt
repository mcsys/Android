package com.passionvirus.cleanlist.viewmodel

import android.util.Log
import androidx.databinding.ObservableBoolean
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.passionvirus.cleanlist.api.ApiUtils
import com.passionvirus.cleanlist.api.entity.ApiEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AbilityListViewModel {
    companion object {
        private const val RETRY_COUNT = 3
        private var tryCount = 1
        private var startPage = 1
        private var lastPage = 0
        private var pageNo = startPage
    }

    val gson = Gson()
//    var refreshVisible = false
//    var prevEnabled = false
//    var nextEnabled = false

    var refreshVisible = ObservableBoolean(false)
    var prevEnabled = ObservableBoolean(false)
    var nextEnabled = ObservableBoolean(false)
//    var refreshVisible : ObservableBoolean = false
//    var prevEnabled : ObservableBoolean = false
//    var nextEnabled : ObservableBoolean = false

    init {
        getAbilityList()
    }

    fun getAbilityList() {
        if (tryCount < RETRY_COUNT) {
            // Get List Info - Observable
            // If success then change button status and lastPage
            ApiUtils.getSOService().getAllAbility().enqueue(object : Callback<JsonObject> {
                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    getAbilityList()
                    tryCount++
                }

                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    if (response.isSuccessful) {
                        val loginResult = gson.fromJson(response.body().toString(), ApiEntity.AbilityList::class.java)
                        Log.d("TEST1234", "Count: ${loginResult.count}")
                        Log.d("TEST1234", "T: ${loginResult.results[0].name}")

                        lastPage = loginResult.count
                        prevEnabled.set(true)
                        nextEnabled.set(true)
                    }
                    else {
                        getAbilityList()
                        tryCount++
                    }
                }
            })

        }
        else {
            tryCount = 1
            refreshVisible.set(true)
        }
    }

    fun getAbility(page : Int) {
        ApiUtils.getSOService().getAbility(page).enqueue(object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                getAbilityList()
                tryCount++
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.isSuccessful) {
                    Log.d("TEST1234", response.body().toString())

//                    val loginResult = gson.fromJson(response.body().toString(), AbilityList::class.java)

                }
                else {
                    getAbility(page)
                    tryCount++
                }
            }
        })
    }

    fun requestPrev() {
        if (pageNo > startPage) {
            pageNo--
            getAbility(pageNo)
        }

        if (pageNo == startPage) {
            prevEnabled.set(false)
        }
    }

    fun requestNext() {
        if (pageNo < lastPage) {
            pageNo++
            getAbility(pageNo)
        }

        if (pageNo == lastPage) {
            nextEnabled.set(false)
        }
    }

}
