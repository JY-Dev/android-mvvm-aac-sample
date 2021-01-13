package com.jydev.riiidsimapleapllication.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jydev.riiidsimapleapllication.R
import com.jydev.riiidsimapleapllication.databinding.ItemComentsBinding
import com.jydev.riiidsimapleapllication.model.user.CommentMapper

class DetailCommentAdapter : RecyclerView.Adapter<DetailCommentAdapter.ViewHolder>() {
    var commentList = listOf<CommentMapper.Comment>()

    inner class ViewHolder(private val binding: ItemComentsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : CommentMapper.Comment){
            binding.apply {
                name = "Name : ${item.name}"
                comment = item.comment
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_coments, parent, false))

    override fun getItemCount(): Int = commentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(commentList[position])
    }

}