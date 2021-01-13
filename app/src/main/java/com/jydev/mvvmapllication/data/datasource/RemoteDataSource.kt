package com.jydev.riiidsimapleapllication.data.datasource

import com.jydev.riiidsimapleapllication.model.network.CommentResponse
import com.jydev.riiidsimapleapllication.model.network.PostRequest
import com.jydev.riiidsimapleapllication.model.network.PostResponse
import io.reactivex.Flowable
import retrofit2.Response

interface RemoteDataSource {
    fun getPosts(start : Int , limit : Int ) : Flowable<List<PostResponse>>
    suspend fun getPost(userId : Int) : PostResponse
    fun deletePost(userId : Int) : Flowable<Response<Void>>
    fun updatePost(userId : Int , postRequest: PostRequest) : Flowable<PostResponse>
    suspend fun getComment(userId : Int) : List<CommentResponse>
}