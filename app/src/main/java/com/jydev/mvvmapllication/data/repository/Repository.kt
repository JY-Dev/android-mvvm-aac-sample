package com.jydev.riiidsimapleapllication.data.repository

import com.jydev.riiidsimapleapllication.model.network.PostRequest
import com.jydev.riiidsimapleapllication.model.network.PostResponse
import com.jydev.riiidsimapleapllication.model.user.CommentMapper
import com.jydev.riiidsimapleapllication.model.user.PostMapper
import io.reactivex.Flowable
import retrofit2.Response

interface Repository {
    fun getPosts(start: Int, limit: Int): Flowable<List<PostMapper.Post>>
    suspend fun getPost(userId : Int) : PostMapper.Post
    fun deletePost(userId: Int) : Flowable<Response<Void>>
    fun updatePost(userId: Int , postRequest: PostRequest) : Flowable<PostMapper.Post>
    suspend fun getComment(userId: Int) : List<CommentMapper.Comment>
}