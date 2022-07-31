package com.example.mvvmcleanarchitecture.view.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmcleanarchitecture.data.Post
import com.example.mvvmcleanarchitecture.databinding.PostItemBinding

class PostAdapter : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    private var postDetails = ArrayList<Post>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder.from(
            parent
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item = postDetails[position]
        holder.bind(item, position)
    }

    fun setData(newData: ArrayList<Post>) {
        val diffUtil =
            PostDiffUtil(postDetails, newData)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtil)
        postDetails = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }
    override fun getItemCount(): Int {
        return postDetails.size
    }

    class PostViewHolder(private val postItemBinding: PostItemBinding): RecyclerView.ViewHolder(postItemBinding.root){
        fun bind(postDetails: Post, position: Int) {
            postItemBinding.postDetails = postDetails
            postItemBinding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): PostViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =PostItemBinding.inflate(layoutInflater, parent, false)
                return PostViewHolder(
                    binding
                )
            }
        }

    }
}