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
    private val TAG = AbilityListViewModel::class.java.simpleName
    companion object {
        private const val RETRY_COUNT = 3
        private var abilityListUrl = "https://pokeapi.co/api/v2/ability/"
        private var tryCount = 1
    }

    val gson = Gson()
    var refreshVisible = ObservableBoolean(false)
    // Code - V1
//    var prevEnabled = ObservableBoolean(false)
//    var nextEnabled = ObservableBoolean(false)
    // Code - V2
    var prevEnabled = ObservableBoolean(true)
    var nextEnabled = ObservableBoolean(true)
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
        Log.d("TEST1234", "req - getAbilityList")
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

//                        Log.d("TEST1234", "T: ${loginResult.results[0].name}")

                        items.takeIf { it.size > 0 }
                            .run {
                                items.clear()
                            }
                        items.addAll(loginResult.results)

                        loginResult.previous?.let {
                            prevUrl = loginResult.previous
                            // Code - V1 Only
//                            prevEnabled.set(true)
                        } ?: run {
                            prevUrl = ""
                            // Code - V1 Only
//                            prevEnabled.set(false)
                        }

                        loginResult.next?.let {
                            nextUrl = loginResult.next
                            // Code - V1 Only
//                            nextEnabled.set(true)
                        } ?: run {
                            nextUrl = ""
                            // Code - V1 Only
//                            nextEnabled.set(false)
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

    // Code - V1
    /*
    fun requestPrev() {
        Log.d(TAG, "Req-Prev : $prevUrl")
        prevUrl.takeIf { it.isNotEmpty() }
            .run {
                tryCount = 1
                prevEnabled.set(false)
                nextEnabled.set(false)
                getAbilityList(prevUrl)
            }
    }
    */

    // Code - V2 RxKotlin : Move Fragment
    /*
    fun onClickPrev(view: View) {
        Log.d("TEST1234", "Req-Prev : $prevUrl")
        val disposable = view.clicks()
            .throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
            .filter { prevUrl.isNotEmpty() }
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe{ getAbilityList(prevUrl) }
    }
    */

    // Code - V1
    /*
    fun requestNext() {
        Log.d(TAG, "Req-Next : $prevUrl")
        nextUrl.takeIf { it.isNotEmpty() }
            .run {
                tryCount = 1
                prevEnabled.set(false)
                nextEnabled.set(false)
                getAbilityList(nextUrl)
            }
    }
    */

//    https://medium.com/mindorks/rxandroid-retrofit-2fff4f89fa85
//    https://tiii.tistory.com/11
//    https://nittaku.tistory.com/179

    // Code - V2 RxKotlin : Move Fragment
    // https://gogorchg.tistory.com/entry/KotlinDatabinding-OnClick-%EC%8B%9C-%EC%97%90%EB%9F%AC-%EB%82%98%EC%8B%9C%EB%8A%94-%EB%B6%84-%EC%B0%B8%EA%B3%A0
    /*
    fun onClickNext(view: View) {
        val disposable = view.clicks()
            .throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
            .filter { nextUrl.isNotEmpty() }
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe{ getAbilityList(nextUrl) }
    }
    */
}
