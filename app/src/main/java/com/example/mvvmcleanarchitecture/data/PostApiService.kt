package com.example.mvvmcleanarchitecture.data

import retrofit2.http.GET

interface PostApiService {
 @GET("/")
 suspend fun getPosts() : List<ApiResponse>
}