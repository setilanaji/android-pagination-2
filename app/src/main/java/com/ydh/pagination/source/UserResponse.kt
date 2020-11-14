package com.ydh.pagination.source

import com.google.gson.annotations.SerializedName
import com.ydh.pagination.model.UserModel
import java.io.Serializable

data class UserResponse(

    @SerializedName("data")
    val listUsers : ArrayList<UserModel>

) : Serializable