package com.passionvirus.cleanlist.viewmodel

import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.passionvirus.cleanlist.adapter.AbilityListViewItem
import com.passionvirus.cleanlist.api.ApiUtils
import com.passionvirus.cleanlist.api.entity.ApiEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AbilityListViewModel {
    companion object {
        private const val RETRY_COUNT = 3
        private var abilityListUrl = "https://pokeapi.co/api/v2/ability/"
        private var tryCount = 1
    }

    val gson = Gson()
    var refreshVisible = ObservableBoolean(false)
    var prevEnabled = ObservableBoolean(false)
    var nextEnabled = ObservableBoolean(false)
    var items = ObservableArrayList<AbilityListViewItem>()

    init {
        getAbilityList()
    }

    fun getAbilityList() {
        getAbilityList(abilityListUrl)

    }

    fun getAbilityList(url : String) {
        if (tryCount < RETRY_COUNT) {
            // Get List Info - Observable
            // If success then change button status and lastPage
            ApiUtils.getSOService().getAbilityList(url).enqueue(object : Callback<JsonObject> {
                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    getAbilityList(url)
                    tryCount++
                }

                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    if (response.isSuccessful) {
                        Log.d("TEST1234", "OK")
                        Log.d("TEST1234", response.body().toString())
                        val loginResult = gson.fromJson(response.body().toString(), ApiEntity.AbilityList::class.java)

                        Log.d("TEST1234", "T: ${loginResult.results[0].name}")

                        items.addAll(loginResult.results)

                        loginResult.previous?.let {
                            prevEnabled.set(true)
                        } ?: run {
                            prevEnabled.set(false)
                        }

                        loginResult.next?.let {
                            nextEnabled.set(true)
                        } ?: run {
                            nextEnabled.set(false)
                        }
                    }
                    else {
                        getAbilityList(url)
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
                getAbility(page)
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
        Log.d("TEST1234", "requestPrev")
//        abilityListUrl = url
//        getAbilityList(abilityListUrl)
    }

    fun requestNext() {
        Log.d("TEST1234", "requestNext")
//        abilityListUrl = url
//        getAbilityList(abilityListUrl)
    }

}
