package com.ydh.pagination.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.ydh.pagination.R
import com.ydh.pagination.viewmodel.UserViewModel
import com.ydh.pagination.viewmodel.UserViewModelFactory
import com.ydh.pagination.ui.adapter.UsersAdapter
import com.ydh.pagination.databinding.FragmentListBinding
import com.ydh.pagination.model.UserModel

class ListFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentListBinding>(inflater,
            R.layout.fragment_list, container, false)
        val myadapter = context?.let { UsersAdapter(it) }
        binding.rvListUser.run {

            layoutManager = LinearLayoutManager(context)
            adapter = myadapter
        }
        val userViewModel = ViewModelProviders.of(this, UserViewModelFactory(this.context)).get(
            UserViewModel::class.java)
        userViewModel.getData().observe(viewLifecycleOwner, object : Observer<PagedList<UserModel>> {
            override fun onChanged(t: PagedList<UserModel>?) {
                myadapter?.submitList(t)
            }
        })

        return binding.root

    }


}