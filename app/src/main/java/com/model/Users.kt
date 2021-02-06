package com.model
import com.google.gson.annotations.SerializedName

open class Users(
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: String,
    @SerializedName("items") val items: List<String>
)