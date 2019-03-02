package com.passionvirus.productivesample

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException


class OkGet {
    private val mClient = OkHttpClient()

    @Throws(IOException::class)
    fun run(url : String) : String {
        val req = Request.Builder()
            .url(url)
            .build()

        val response = mClient.newCall(req).execute()

        return response.toString()
    }
}