package com.ydh.pagination

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ydh.pagination.databinding.ItemUserBinding

class UsersAdapter(private val context: Context) : PagedListAdapter<UserModel, UsersAdapter.MyViewHolder>(USER_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding: ItemUserBinding = DataBindingUtil.inflate(inflater, R.layout.item_user,parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemBinding.user = getItem(position)
    }

    class MyViewHolder(val itemBinding: ItemUserBinding) : RecyclerView.ViewHolder(itemBinding.root){

        private var binding : ItemUserBinding? = null

        init {
            this.binding = itemBinding
        }

    }
    companion object {
        private val USER_COMPARATOR = object : DiffUtil.ItemCallback<UserModel>() {
            override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean =
                oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean =
                newItem == oldItem
        }
    }

}