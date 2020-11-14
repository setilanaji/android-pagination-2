package com.ydh.pagination.data

import android.content.Context
import androidx.paging.PageKeyedDataSource
import com.ydh.pagination.source.remote.ApiClient
import com.ydh.pagination.source.UserResponse
import com.ydh.pagination.Util
import com.ydh.pagination.Util.isInternetAvailable
import com.ydh.pagination.Util.showProgressBar
import com.ydh.pagination.model.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDataSource(private val context: Context) : PageKeyedDataSource<Int, UserModel>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, UserModel>) {
        if (context.isInternetAvailable()) {
            getUsers(callback)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, UserModel>) {
        if (context.isInternetAvailable()) {
            val nextPageNo = params.key + 1
            getMoreUsers(params.key, nextPageNo, callback)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, UserModel>) {
        if (context.isInternetAvailable()) {
            val previousPageNo = if (params.key > 1) params.key - 1 else 0
            getMoreUsers(params.key, previousPageNo, callback)
        }
    }

    private fun getUsers(callback: LoadInitialCallback<Int, UserModel>) {

        context.showProgressBar()

        ApiClient.apiService.getUsers(1).enqueue(object : Callback<UserResponse> {
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Util.hideProgressBar()
            }

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                Util.hideProgressBar()
                val userResponse = response.body()
                val listUsers = userResponse?.listUsers
                listUsers?.let { callback.onResult(it, null, 2) }
            }

        })

    }

    private fun getMoreUsers(pageNo: Int, previousOrNextPageNo: Int, callback: LoadCallback<Int, UserModel>) {

        ApiClient.apiService.getUsers(pageNo).enqueue(object : Callback<UserResponse> {
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                val userResponse = response.body()
                val listUsers = userResponse?.listUsers
                listUsers?.let { callback.onResult(it, previousOrNextPageNo) }
            }

        })

    }

}