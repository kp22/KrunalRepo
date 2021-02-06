package com.model
import com.google.gson.annotations.SerializedName

open class UserData (
	@SerializedName("users") val users : List<Users>,
	@SerializedName("has_more") val has_more : Boolean
)