package com.ydh.pagination

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.ydh.pagination.databinding.FragmentListBinding
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentListBinding>(inflater, R.layout.fragment_list, container, false)
        val myadapter = context?.let { UsersAdapter(it) }
        binding.rvListComment.run {

            layoutManager = LinearLayoutManager(context)
            adapter = myadapter
        }
//        rv_list_comment.layoutManager = LinearLayoutManager(context)
//        val adapter = context?.let { UsersAdapter(it) }
//        rv_list_comment.adapter = adapter

        val userViewModel = ViewModelProviders.of(this, UserViewModelFactory(this.context)).get(UserViewModel::class.java)
//        val userViewModel = ViewModelProvider.(this,UserViewModelFactory(context)).get(UserViewModel::class.java)
        userViewModel.getData().observe(viewLifecycleOwner, object : Observer<PagedList<UserModel>> {
            override fun onChanged(t: PagedList<UserModel>?) {
                myadapter?.submitList(t)
            }
        })

        return binding.root

    }


}