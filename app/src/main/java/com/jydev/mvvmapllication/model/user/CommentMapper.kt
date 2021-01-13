package com.jydev.riiidsimapleapllication.model.user

import com.jydev.riiidsimapleapllication.model.network.CommentResponse

class CommentMapper(private val commentResponse: CommentResponse){
    data class Comment(val name : String , val comment : String)
    fun getComment() : Comment = Comment(commentResponse.name, commentResponse.body)
}
