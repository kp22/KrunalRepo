package com.itfaq.adapter

import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.krunalrepo.R
import com.krunalrepo.databinding.ItemUserListBinding
import com.model.Users


class UserAdapter(var list: MutableList<Users>) : RecyclerView.Adapter<UserAdapter.Viewholder>() {
    lateinit var context: Context

    class Viewholder(val bind: ItemUserListBinding) : RecyclerView.ViewHolder(bind.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        context = parent.context
        val binding: ItemUserListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.item_user_list,
            parent,
            false
        )
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.bind.tvUSerName.setText(list.get(position).name)
        val url = list.get(position).image
        if (!TextUtils.isEmpty(url.trim()))
            Glide.with(context).load(url).into(holder.bind.imgUser)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateData(users: List<Users>) {
        list.addAll(users)
        notifyDataSetChanged()
    }

    fun isEvenCount(): Boolean {
        return list.size % 2 == 0
    }

}