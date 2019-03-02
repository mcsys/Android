package com.passionvirus.productivesample

import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.io.IOException

class OkPost {
    companion object {
        val JSON = MediaType.parse("application/json; charset=utf-8")
    }

    val mClient = OkHttpClient()

    @Throws(IOException::class)
    fun post(url : String, json : String) : String {
        val body = RequestBody.create(JSON, json)
        val request = Request.Builder()
            .url(url)
            .post(body)
            .build()

        val response = mClient.newCall(request).execute()

        return response.body()!!.string()
    }

    fun buildJson(p1 : String, p2 : String) : String {
        return "{'winCondition':'HIGH_SCORE',"  +
                "'name':'Bowling'," +
                "'round':4," +
                "'lastSaved':1367702411696," +
                "'dateStarted':1367702378785," +
                "'player':[" +
                "{'name':'$p1', 'history':[10,8,6,7,8], 'color':-13388315, 'total':39}," +
                "{'name':'$p2', 'history':[10,8,6,7,8], 'color':-13388315, 'total':39}," +
                "]}"
    }
}