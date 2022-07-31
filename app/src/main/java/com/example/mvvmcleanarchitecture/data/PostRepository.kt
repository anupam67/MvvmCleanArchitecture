package com.example.mvvmcleanarchitecture.data

import com.example.mvvmcleanarchitecture.ResponseHandlerUtil.Resource
import retrofit2.Response
import javax.inject.Inject

class PostRepository @Inject constructor(private val postApiService:PostApiService) {

    suspend fun getPostList() : Resource<ArrayList<Post>>{
      return responseToResource(postApiService.getPosts())
    }
    suspend fun getPostById(id:Int) : Resource<Post>{
        return responseToResourcePost(postApiService.getPostById(id))
    }

    private fun responseToResource(response: Response<ArrayList<Post>>): Resource<ArrayList<Post>>{
      if(response.isSuccessful){
         response.body()?.let { result  ->
             return Resource.Success(result) }
      }
      return Resource.Error(response.message())
    }
    private fun responseToResourcePost(response: Response<Post>): Resource<Post> {
        if(response.isSuccessful){
            response.body()?.let { result  ->
                return Resource.Success(result) }
        }
        return Resource.Error(response.message())
    }
}