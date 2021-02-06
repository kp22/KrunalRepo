package com.krunalrepo.user

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.callback.UserInterFace
import com.google.gson.Gson
import com.kotlindemo.api.RetroClient
import com.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel(application: Application) : AndroidViewModel(application) {

    lateinit var userInterFace: UserInterFace
    var lastUser:UserModel? = null
    var isLoding: Boolean = false

    fun setInterface(userInterFace: UserInterFace) {
        this.userInterFace = userInterFace
    }

    fun getUsers(offset: String, limit:String) = viewModelScope.launch(Dispatchers.IO) {
      Log.e("TAG","offset = $offset | limit = $limit")
        RetroClient.apiService.getUserData(offset, limit).enqueue(object : Callback<UserModel> {
            override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                Log.e("TAG", "onResponse: " + Gson().toJson(response.body()))
                if (response.isSuccessful) {
                    lastUser = response.body();
                    userInterFace.onUserDataSuccess(response.body())
                } else {
                    userInterFace.onUserDataFailed()
                }
                isLoding=false
            }
            override fun onFailure(call: Call<UserModel>, t: Throwable) {
                Log.e("TAG", "onFailure: error - " + t.message)
                userInterFace.onUserDataFailed()
            }
        })
    }

}