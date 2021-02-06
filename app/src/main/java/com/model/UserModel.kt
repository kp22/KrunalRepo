package com.model
import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("status") val status : Boolean,
    @SerializedName("message") val message : String,
    @SerializedName("data") val userData: UserData)
