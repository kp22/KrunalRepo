package com.krunalrepo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.itfaq.adapter.UserAdapter
import com.krunalrepo.databinding.ActivityMainBinding
import com.model.UserModel

class MainActivity : AppCompatActivity() {

    lateinit var userAdapter: UserAdapter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initObject();
    }

    private fun initObject() {
        val list = arrayListOf<UserModel>()
        userAdapter = UserAdapter(list)
        binding.rvUser.adapter = userAdapter
    }
}