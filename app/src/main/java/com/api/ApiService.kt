package com.kotlindemo.api

import com.model.UserModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    fun getUserData(@Query("offset") offset: String, @Query("limit") limit: String ): Call<UserModel>
}