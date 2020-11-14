package com.ydh.pagination.data

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.ydh.pagination.data.UserDataSource
import com.ydh.pagination.model.UserModel

class DataSourceFactory(private val context: Context) : DataSource.Factory<Int, UserModel>() {

    val mutableLiveData = MutableLiveData<UserDataSource>()

    override fun create(): DataSource<Int, UserModel> {
        val userDataSource = UserDataSource(context)
        mutableLiveData.postValue(userDataSource)
        return userDataSource
    }

}