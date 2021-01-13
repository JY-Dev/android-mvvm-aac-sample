package com.jydev.riiidsimapleapllication.data.repository

import com.jydev.riiidsimapleapllication.data.datasource.RemoteDataSource
import com.jydev.riiidsimapleapllication.data.repository.Repository
import com.jydev.riiidsimapleapllication.model.network.PostRequest
import com.jydev.riiidsimapleapllication.model.network.PostResponse
import com.jydev.riiidsimapleapllication.model.user.CommentMapper
import com.jydev.riiidsimapleapllication.model.user.PostMapper
import io.reactivex.Flowable
import retrofit2.Response

class RepositoryImpl(val source: RemoteDataSource) :
    Repository {
    override fun getPosts(start: Int, limit: Int): Flowable<List<PostMapper.Post>> =
        source.getPosts(start, limit).flatMap { list ->
            Flowable.fromIterable(list)
                .map{ item-> PostMapper(item).getPost() }
                .toList()
                .toFlowable()
    }

    override suspend fun getPost(userId: Int): PostMapper.Post =
        PostMapper(source.getPost(userId)).getPost()

    override fun deletePost(userId: Int): Flowable<Response<Void>> =
        source.deletePost(userId)

    override fun updatePost(userId: Int, postRequest: PostRequest): Flowable<PostMapper.Post> =
        source.updatePost(userId,postRequest).map { item -> PostMapper(item).getPost() }

    override suspend fun getComment(userId: Int): List<CommentMapper.Comment> =
        source.getComment(userId).map { item -> CommentMapper(item).getComment()}

}