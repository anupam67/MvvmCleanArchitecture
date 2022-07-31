package com.example.mvvmcleanarchitecture.data

import com.example.mvvmcleanarchitecture.ResponseHandlerUtil.Resource
import retrofit2.Response
import javax.inject.Inject

class PostRepository @Inject constructor(private val postApiService:PostApiService) {

    suspend fun getPostList() : Resource<ApiResponse>{
      return responseToResource(postApiService.getPosts())
    }

    private fun responseToResource(response: Response<ApiResponse>): Resource<ApiResponse> {
      if(response.isSuccessful){
         response.body()?.let { result  ->
             return Resource.Success(result) }
      }
      return Resource.Error(response.message())
    }
}