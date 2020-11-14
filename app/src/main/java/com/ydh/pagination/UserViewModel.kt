package com.ydh.pagination

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

class UserViewModel(private val context: Context) : ViewModel() {

    private var listUsers: LiveData<PagedList<UserModel>> = MutableLiveData<PagedList<UserModel>>()
    private var mutableLiveData = MutableLiveData<UserDataSource>()

    init {
        val factory: DataSourceFactory by lazy {
            DataSourceFactory(context)
        }
        mutableLiveData = factory.mutableLiveData

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(6)
            .build()

        listUsers = LivePagedListBuilder(factory, config)
            .build()

    }

    fun getData(): LiveData<PagedList<UserModel>> {
        return listUsers
    }

}