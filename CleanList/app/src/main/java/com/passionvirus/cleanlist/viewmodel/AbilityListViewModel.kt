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
import io.reactivex.schedulers.Schedulers




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
    var prevUrl = ""
    var nextUrl = ""

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

                        items.takeIf { it.size > 0 }
                            .run {
                                items.clear()
                            }
                        items.addAll(loginResult.results)

                        loginResult.previous?.let {
                            prevUrl = loginResult.previous
                            prevEnabled.set(true)
                        } ?: run {
                            prevUrl = ""
                            prevEnabled.set(false)
                        }

                        loginResult.next?.let {
                            nextUrl = loginResult.next
                            nextEnabled.set(true)
                        } ?: run {
                            nextUrl = ""
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

    fun requestPrev() {
        prevUrl.takeIf { it.isNotEmpty() }
            .run {
                tryCount = 1
                prevEnabled.set(false)
                nextEnabled.set(false)
                getAbilityList(prevUrl)
            }
    }

    fun requestNext() {
        nextUrl.takeIf { it.isNotEmpty() }
            .run {
                tryCount = 1
                prevEnabled.set(false)
                nextEnabled.set(false)
                getAbilityList(nextUrl)

//                https://medium.com/mindorks/rxandroid-retrofit-2fff4f89fa85
//                https://tiii.tistory.com/11
//                https://nittaku.tistory.com/179
                // Temporary Add - for test
                ApiUtils.getSOService().getAbilityList(nextUrl)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe({ it -> Log.d("TEST", "1234")})

            }
    }

}
