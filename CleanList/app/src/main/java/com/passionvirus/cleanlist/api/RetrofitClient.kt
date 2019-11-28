package com.passionvirus.cleanlist.api

import android.util.Log
import com.google.gson.*
import com.passionvirus.cleanlist.api.entity.ApiEntity
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type


class RetrofitClient {
    companion object {
        private lateinit var retrofit: Retrofit

        @JvmStatic
        fun getClient(baseUrl: String): Retrofit {
            // Code - V2_1


            if (!::retrofit.isInitialized) {
                val gson = GsonBuilder()
                    .registerTypeAdapter(ApiEntity.AbilityList::class.java, AbilityListDeserializer())
                    .create()

                retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
//                    .addConverterFactory(GsonConverterFactory.create())
                    // Code - V1
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    // Code - V2
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            }

            return retrofit
        }
    }

    private class AbilityListDeserializer : JsonDeserializer<ApiEntity.AbilityList> {
        //@Throws(JsonParseException::class)
        override fun deserialize(json: JsonElement?, type: Type?, context: JsonDeserializationContext?): ApiEntity.AbilityList {
            val entity = ApiEntity.AbilityList()

            return entity
        }
    }
}