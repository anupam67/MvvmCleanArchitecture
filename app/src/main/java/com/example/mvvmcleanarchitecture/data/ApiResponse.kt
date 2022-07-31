package com.example.mvvmcleanarchitecture.data

import com.google.gson.annotations.SerializedName

data class ApiResponse(@SerializedName ("posts") val post : ArrayList<Post>)
