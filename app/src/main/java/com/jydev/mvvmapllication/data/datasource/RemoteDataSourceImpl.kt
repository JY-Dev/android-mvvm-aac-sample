package com.jydev.riiidsimapleapllication.data.datasource

import com.jydev.riiidsimapleapllication.data.service.NetworkService
import com.jydev.riiidsimapleapllication.model.network.CommentResponse
import com.jydev.riiidsimapleapllication.model.network.PostRequest
import com.jydev.riiidsimapleapllication.model.network.PostResponse
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class RemoteDataSourceImpl(val service: NetworkService) :
    RemoteDataSource {
    override fun getPosts(start: Int, limit: Int): Flowable<List<PostResponse>> =
        service.getPosts(start, limit).setNetworkThread()

    override suspend fun getPost(userId: Int): PostResponse =
        service.getPost(userId)

    override fun deletePost(userId: Int): Flowable<Response<Void>> =
        service.deletePost(userId).setNetworkThread()

    override fun updatePost(userId: Int, postRequest: PostRequest): Flowable<PostResponse> =
        service.updatePost(userId, postRequest).setNetworkThread()

    override suspend fun getComment(userId: Int): List<CommentResponse> =
        service.getComment(userId)



    private fun <T> Flowable<T>.setNetworkThread() = observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
}

