package com.passionvirus.productivesample

import retrofit2.Call
import retrofit2.http.*

interface RestService {
    companion object {
        val BASE_URL = "https://api.github.com"
    }

    @GET("users/{id}")
    fun getUser(@Path("id") id: String) : Call<GithubUser>

    @GET("users/{id}/followers")
    fun getFollowers(@Path("id") id: String) : Call<GithubUser>

    @GET("users/{id}/following")
    fun getFollowing(@Path("id") id: String) : Call<GithubUser>

    @FormUrlEncoded
    @POST("/test")
    fun createUser(@Field("email") email : String,
                   @Field("password") password : String,
                   @Field("name") name : String,
                   @Field("term_no") term_no : Int,
                   @Field("agree_term_version") agree_term_version : String)

    @FormUrlEncoded
    @PUT("/test")
    fun updateUser(@Field("current_password") current_password : String,
                   @Field("password") password : String,
                   @Field("name") name : String)
}