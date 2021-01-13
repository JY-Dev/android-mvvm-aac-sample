package com.jydev.riiidsimapleapllication.data.service

import com.jydev.riiidsimapleapllication.model.network.CommentResponse
import com.jydev.riiidsimapleapllication.model.network.PostRequest
import com.jydev.riiidsimapleapllication.model.network.PostResponse
import io.reactivex.Flowable
import retrofit2.Response
import retrofit2.http.*

interface NetworkService {
    @GET("/posts")
    fun getPosts(@Query("_start") start : Int,
                         @Query("_limit") limit : Int) : Flowable<List<PostResponse>>

    @GET("/posts/{userId}")
    suspend fun getPost(@Path("userId") userId : Int) : PostResponse

    @DELETE("/posts/{userId}")
    fun deletePost(@Path("userId") userId : Int) : Flowable<Response<Void>>

    @PATCH("/posts/{userId}")
    fun updatePost(@Path("userId") userId: Int,
                           @Body postRequest : PostRequest
    ) : Flowable<PostResponse>

    @GET("/posts/{userId}/comments")
    suspend fun getComment(@Path("userId") userId : Int) : List<CommentResponse>

}