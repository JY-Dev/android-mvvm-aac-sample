package com.jydev.riiidsimapleapllication.bind

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jydev.riiidsimapleapllication.model.user.CommentMapper
import com.jydev.riiidsimapleapllication.ui.detail.adapter.DetailCommentAdapter
import com.jydev.riiidsimapleapllication.ui.main.adapter.MainPostsAdapter
import com.jydev.riiidsimapleapllication.ui.main.MainViewModel

@BindingAdapter("bind:detailItems")
fun setDetailAdapter(view: RecyclerView , comments : List<CommentMapper.Comment>?) {
    view.adapter = DetailCommentAdapter()
        .apply {
        comments?.run {
            commentList = this
            notifyDataSetChanged()
        }
    }
}

@BindingAdapter("bind:mainAdapter","bind:mainViewModel")
fun setMainAdapter(view: RecyclerView, mainAdapter: MainPostsAdapter, viewModel: MainViewModel) {
    view.adapter = mainAdapter.apply {
        setAddScrollListener(view){
            viewModel.getPosts(it)
        }
    }
}

@BindingAdapter("bind:itemDecoration")
fun setItemDecoration(view : RecyclerView, itemDecoration: RecyclerView.ItemDecoration){
    view.addItemDecoration(itemDecoration)
}