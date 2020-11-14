package com.ydh.pagination

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserResponse(

    @SerializedName("data")
    val listUsers : ArrayList<UserModel>

) : Serializable