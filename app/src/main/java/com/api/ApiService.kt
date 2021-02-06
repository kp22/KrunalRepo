package com.kotlindemo.api

import com.model.UserModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @get:GET("users?offset=0&limit=10") val getUserData : Call<UserModel>

}