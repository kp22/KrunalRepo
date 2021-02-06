package com.krunalrepo

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.callback.UserInterFace
import com.itfaq.adapter.UserAdapter
import com.krunalrepo.databinding.ActivityUserBinding
import com.krunalrepo.user.UserViewModel
import com.model.UserModel
import com.model.Users


class UserActivity : AppCompatActivity(), UserInterFace {

    private lateinit var userAdapter: UserAdapter
    private lateinit var binding: ActivityUserBinding
    private lateinit var userViewModel: UserViewModel
    private var offset: Int = 0
    private var limit: Int = 10
    private var itemCount: Int = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user)
        initObject()
        initListner()
    }

    private fun initObject() {
        userAdapter = UserAdapter(mutableListOf<Users>())
        binding.rvUser.adapter = userAdapter
        userAdapter.notifyDataSetChanged()
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.setInterface(this)
        userViewModel.getUsers(offset.toString(), limit.toString());
    }

    private fun initListner() {
        binding.rvUser.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    val manager: LinearLayoutManager =
                        binding.rvUser.layoutManager as LinearLayoutManager
                    val visibleItemCount: Int = manager.getChildCount()
                    val totalItemCount: Int = manager.getItemCount()
                    val pastVisiblesItems: Int = manager.findFirstVisibleItemPosition()
                    if (!userViewModel.isLoding) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            userViewModel.isLoding = true
                            loadNextData();
                        }
                    }
                }
            }
        })
    }

    private fun loadNextData() {
        if (userViewModel.lastUser != null && userViewModel.lastUser?.userData?.has_more == true) {
            binding.progressBar.visibility = View.VISIBLE
            offset += itemCount
            userViewModel.getUsers(offset.toString(), itemCount.toString());
        }
    }

    override fun onUserDataSuccess(model: UserModel?) {
        binding.progressBar.visibility = View.GONE
        model?.userData?.users?.let {
            userAdapter.updateData(it)
            checkEvenData(userAdapter.isEvenCount())
        }
    }


    override fun onUserDataFailed() {
        binding.progressBar.visibility = View.GONE
        Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show()
    }

    private fun checkEvenData(isEven: Boolean) {
        val layoutManager = binding.rvUser.layoutManager as GridLayoutManager
        if (isEven) {
            layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return 1
                }
            }
        } else {
            layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    when (position) {
                        0 -> return 2
                        else -> return 1
                    }
                }
            }
        }
    }

}