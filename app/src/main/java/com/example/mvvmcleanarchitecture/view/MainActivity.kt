package com.example.mvvmcleanarchitecture.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmcleanarchitecture.R
import com.example.mvvmcleanarchitecture.ResponseHandlerUtil.Resource
import com.example.mvvmcleanarchitecture.data.Post
import com.example.mvvmcleanarchitecture.databinding.ActivityMainBinding
import com.example.mvvmcleanarchitecture.view.Adapter.PostAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var postAdapter: PostAdapter
    private val postViewModel: PostViewModel by lazy {
        ViewModelProvider(this)[PostViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initializeView()
        initRecyclerView()
        binding.searchET.doAfterTextChanged {
            it?.let {
                if (it.isNotEmpty()) {
                    getPostSearchData(it.toString().toInt())
                }
            }
        }
        binding.clearSearchIV.setOnClickListener {
            binding.searchET.text.clear()
            postViewModel.getPostValue()

        }
        postViewModel.getPostValue()
        postViewModel.postData.observe(this){
            response ->
            when (response) {
                is Resource.Success -> { response.data?.let {
                    Log.e("Response","Resource Success $it")
                    postAdapter.setData(it)
                }
                }
                is Resource.Error -> {
                    Log.e("Response","Resource Error ${response.message}")
                }

                is Resource.Loading -> {
                    Log.e("Response","Resource Loading")
                }
            }
        }
        postViewModel.postDataId.observe(this){
                response ->
            when (response) {
                is Resource.Success -> { response.data?.let {
                    Log.e("Response","Resource Success 2 $it")
                    val postList = ArrayList<Post>()
                    postList.add(it)
                    postAdapter.setData(postList)
                }
                }
                is Resource.Error -> {
                    Log.e("Response","Resource Error 2 ${response.message}")
                }

                is Resource.Loading -> {
                    Log.e("Response","Resource Loading 2")
                }
            }
        }

    }

    private fun getPostSearchData(id: Int) {
        try {
            postViewModel.getPostById(id)
        } catch (e: Exception) {
        }
    }

    private fun initializeView() {
        supportActionBar?.show()
        window?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }
    private fun initRecyclerView() {
        postAdapter = PostAdapter()
        binding.postRV.apply {
            adapter = postAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}