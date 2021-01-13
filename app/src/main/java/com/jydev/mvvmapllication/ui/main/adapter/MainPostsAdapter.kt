package com.jydev.riiidsimapleapllication.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jydev.riiidsimapleapllication.R
import com.jydev.riiidsimapleapllication.databinding.ItemPostsBinding
import com.jydev.riiidsimapleapllication.model.user.PostMapper
import com.jydev.riiidsimapleapllication.ui.main.MainActivity

class MainPostsAdapter(val selectItem : (post : MainActivity.ItemStatus) -> Unit) : RecyclerView.Adapter<MainPostsAdapter.ViewHolder>() {
    var postList = mutableListOf<PostMapper.Post>()
    var isLoding = false
    inner class ViewHolder(private val binding: ItemPostsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post : PostMapper.Post){
            binding.apply {
                item = post.title
                id = "UserId : ${post.id}"
                root.setOnClickListener {
                    selectItem(MainActivity.ItemStatus.Info(post))
                }
                deleteBtn.setOnClickListener {
                    selectItem(MainActivity.ItemStatus.Delete(post))
                }
                modifyBtn.setOnClickListener {
                    selectItem(MainActivity.ItemStatus.Update(post))
                }
            }
        }
    }

    /**
     * 스크롤 하단에 있을때 데이터 요청
     */
    fun setAddScrollListener(view : RecyclerView , requestItem : (lastUserId : Int) -> Unit){
        val layoutManager = view.layoutManager as LinearLayoutManager
        view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                layoutManager.findLastCompletelyVisibleItemPosition().run {
                    if(!isLoding && this == layoutManager.itemCount-1){
                        requestItem(postList[this].id)
                        isLoding = true
                    }
                }
            }
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_posts, parent, false))

    override fun getItemCount(): Int =
        postList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(postList[position])




}