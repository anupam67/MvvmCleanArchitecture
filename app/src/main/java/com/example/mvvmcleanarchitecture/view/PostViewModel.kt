package com.example.mvvmcleanarchitecture.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmcleanarchitecture.ResponseHandlerUtil.Resource
import com.example.mvvmcleanarchitecture.data.ApiResponse
import com.example.mvvmcleanarchitecture.data.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class PostViewModel @Inject constructor(private val postRepository: PostRepository) : ViewModel(){

    val postData : MutableLiveData<Resource<ApiResponse>> = MutableLiveData()

    fun getPostValue() = viewModelScope.launch(Dispatchers.IO) {
        postData.postValue(Resource.Loading())
        try {
            val apiResult = postRepository.getPostList()
            postData.postValue(apiResult)
        }catch (e: Exception){
            postData.postValue(Resource.Error(e.message.toString()))
        }
    }


}