package com.example.mvvmcleanarchitecture.ResponseHandlerUtil
import androidx.recyclerview.widget.DiffUtil
import com.example.mvvmcleanarchitecture.data.Post


class PostDiffUtil(
    private val oldList: ArrayList<Post>,
    private val newList: ArrayList<Post>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }
}