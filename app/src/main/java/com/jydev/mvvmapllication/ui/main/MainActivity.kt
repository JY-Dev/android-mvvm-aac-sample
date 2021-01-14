package com.jydev.riiidsimapleapllication.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.jydev.riiidsimapleapllication.R
import com.jydev.riiidsimapleapllication.base.BaseActivity
import com.jydev.riiidsimapleapllication.databinding.ActivityMainBinding
import com.jydev.riiidsimapleapllication.model.user.PostMapper
import com.jydev.riiidsimapleapllication.ui.detail.DetailActivity
import com.jydev.riiidsimapleapllication.ui.main.adapter.MainPostsAdapter
import com.jydev.riiidsimapleapllication.ui.main.adapter.PostItemDecoration
import com.jydev.riiidsimapleapllication.ui.main.dialog.ModifyDialog
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.util.concurrent.TimeUnit

class MainActivity : BaseActivity() {
    private val viewModel: MainViewModel by viewModel { parametersOf() }
    private val clickSubject = PublishSubject.create<ItemStatus>().apply {
        throttleFirst(1,TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    when (it) {
                        is ItemStatus.Info -> startActivity(
                                Intent(this@MainActivity, DetailActivity::class.java).apply {
                                    putExtra(KEY, it.post.id)
                                })
                        is ItemStatus.Delete -> {
                            viewModel.deletePost(it)
                        }
                        is ItemStatus.Update -> ModifyDialog(this@MainActivity,it.post.title) {newTitle ->
                            viewModel.updatePost(it.apply {
                                post.title = newTitle
                            })
                        }.show()
                    }
                }
    }
    val bind by binding<ActivityMainBinding>(R.layout.activity_main)
    private var mainAdapter = MainPostsAdapter {
        clickSubject.onNext(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind.run {
            lifecycleOwner = this@MainActivity
            mainViewModel = viewModel
            itemDecoration = PostItemDecoration()
            viewModel.getPosts(0)
            adapter = mainAdapter
        }

        /**
         * 요청한 Data ADD
         * Progress Off
         */
        viewModel.postLiveData.observe(this, Observer { items ->
            mainAdapter.apply {
                postList.apply {
                    val positionStart = postList.size
                    if (items != null && items.isNotEmpty()){
                        postList.addAll(items)
                        resources.getString(R.string.load_data).showShortToast()
                    }
                    else
                        resources.getString(R.string.no_data).showShortToast()
                    isLoding = false
                    notifyItemRangeChanged(positionStart, postList.size - 1)
                }
            }
        })

        /**
         * Post Data Update Or Delete
         */
        viewModel.statusLiveData.observe(this, Observer { status ->
            when (status) {
                is ItemStatus.Delete -> mainAdapter.apply {
                    postList.apply {
                        val itemIndex = indexOf(postList.filter { it.id == status.post.id }[0])
                        removeAt(itemIndex)
                        notifyItemRemoved(itemIndex)
                        resources.getString(R.string.delete_data).showShortToast()
                    }
                }
                is ItemStatus.Update -> mainAdapter.apply {
                    postList.apply {
                        val itemIndex = indexOf(postList.filter { it.id == status.post.id }[0])
                        set(itemIndex, status.post)
                        notifyItemChanged(itemIndex)
                        resources.getString(R.string.update_data).showShortToast()
                    }
                }
                else -> return@Observer
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.dispose()
    }

    /**
     * Info : Post 상세보기
     * Delete : Post 삭제
     * Update : Post 업데이트
     */
    sealed class ItemStatus {
        data class Info(val post: PostMapper.Post) : ItemStatus()
        data class Delete(val post: PostMapper.Post) : ItemStatus()
        data class Update(val post: PostMapper.Post) : ItemStatus()
    }
}