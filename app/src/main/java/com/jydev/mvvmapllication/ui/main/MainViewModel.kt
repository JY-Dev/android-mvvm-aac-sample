package com.jydev.riiidsimapleapllication.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jydev.riiidsimapleapllication.data.repository.Repository
import com.jydev.riiidsimapleapllication.model.network.PostRequest
import com.jydev.riiidsimapleapllication.model.user.PostMapper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

class MainViewModel(private val repository: Repository) : ViewModel() {
    private val composite = CompositeDisposable()
    val loading = MutableLiveData<Boolean>()
    val postLiveData = MutableLiveData<List<PostMapper.Post>>()
    val statusLiveData = MutableLiveData<MainActivity.ItemStatus>()

    companion object {
        private const val LIMIT = 10
    }

    /**
     * Post 데이터 삭제
     */
    fun deletePost(status: MainActivity.ItemStatus.Delete) {
        loading.value = true
        repository.deletePost(status.post.id)
                .throttleFirst(1000L,TimeUnit.MILLISECONDS).subscribe {
            if (it.isSuccessful)
                statusLiveData.value = status
            loading.value = false
        }.addComposite()
    }

    /**
     * Post 데이터 Update
     */
    fun updatePost(status: MainActivity.ItemStatus.Update) {
        loading.value = true
        repository.updatePost(status.post.id, PostRequest(status.post.title)).subscribe {
            statusLiveData.value = MainActivity.ItemStatus.Update(it)
            loading.value = false
        }.addComposite()
    }

    /**
     * 10개의 Post 데이터 요청
     */
    fun getPosts(start: Int) {
        loading.value = true
        repository.getPosts(start, LIMIT).subscribe {
            postLiveData.value = it
            loading.value = false
        }.addComposite()
    }


    fun dispose() = composite.dispose()
    private fun Disposable.addComposite() = composite.add(this)
}