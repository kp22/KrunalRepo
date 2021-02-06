package com.itfaq.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.krunalrepo.R
import com.krunalrepo.databinding.ItemUserListBinding
import com.model.UserModel

class UserAdapter(var list: List<UserModel>) : RecyclerView.Adapter<UserAdapter.Viewholder>() {
    lateinit var context: Context
    val TAG = UserAdapter::class.java.simpleName

    class Viewholder(val bind: ItemUserListBinding) : RecyclerView.ViewHolder(bind.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        context = parent.context
        val binding: ItemUserListBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_user_list, parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        Log.e(TAG, "onBindViewHolder: items pos  => $position")
    }


    override fun getItemCount(): Int {
        return 10
    }

}