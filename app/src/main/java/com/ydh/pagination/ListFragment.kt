package com.ydh.pagination

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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
        binding.rvListComment.layoutManager = LinearLayoutManager(context)
//        rv_list_comment.layoutManager = LinearLayoutManager(context)
//        val adapter = context?.let { UsersAdapter(it) }
//        rv_list_comment.adapter = adapter

//        val userViewModel = ViewModelProviders.(this,UserViewModelFactory(context)).get(UserViewModel::class.java)
//        userViewModel.getData().observe(this, object : Observer<PagedList<User>> {
//            override fun onChanged(t: PagedList<User>?) {
//                adapter.submitList(t)
//            }
//        })

        return binding.root

    }


}