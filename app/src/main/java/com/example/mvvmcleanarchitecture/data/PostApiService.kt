package com.example.mvvmcleanarchitecture.data

import retrofit2.Response
import retrofit2.http.GET

interface PostApiService {
 @GET("/")
 suspend fun getPosts() : Response<ApiResponse>
}