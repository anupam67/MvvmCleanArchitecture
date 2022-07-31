package com.example.mvvmcleanarchitecture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmcleanarchitecture.ResponseHandlerUtil.Resource
import com.example.mvvmcleanarchitecture.data.Post
import com.example.mvvmcleanarchitecture.databinding.ActivityMainBinding
import com.example.mvvmcleanarchitecture.view.Adapter.PostAdapter
import com.example.mvvmcleanarchitecture.view.PostViewModel
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
        postViewModel.postData.observe(this){
            response ->
            when (response) {
                is Resource.Success -> { response.data?.let {
                    postAdapter.setData(it.post)
                }
                }
                is Resource.Error -> {
                }

                is Resource.Loading -> {

                }
            }
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