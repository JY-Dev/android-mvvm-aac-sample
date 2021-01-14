package com.jydev.mvvmapllication.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.jydev.riiidsimapleapllication.data.repository.Repository
import com.jydev.riiidsimapleapllication.model.user.CommentMapper
import com.jydev.riiidsimapleapllication.model.user.PostMapper
import kotlinx.coroutines.Dispatchers

class DetailViewModel(userId : Int,private val repository: Repository) : ViewModel() {
    // Post Data
    val postData : LiveData<PostMapper.Post> = liveData(Dispatchers.IO) {
        loading.postValue(true)
        emit(repository.getPost(userId))
    }
    // Comment Data
    val commentData : LiveData<List<CommentMapper.Comment>> = liveData(Dispatchers.IO) {
        emit(repository.getComment(userId))
        loading.postValue(false)
    }

    val loading = MutableLiveData<Boolean>()

}