package com.example.mvvmcleanarchitecture.data

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Path

interface PostApiService {
 @GET(".")
 suspend fun getPosts() : Response<ArrayList<Post>>

 @GET("{id}")
 suspend fun getPostById(@Path("id") id :Int) : Response<Post>
}