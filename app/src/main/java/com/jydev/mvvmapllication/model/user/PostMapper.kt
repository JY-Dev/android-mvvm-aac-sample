package com.jydev.riiidsimapleapllication.model.user

import android.os.Parcelable
import com.jydev.riiidsimapleapllication.model.network.PostResponse


class PostMapper(val postResponse: PostResponse){
    data class Post(var id : Int , var title: String)
    fun getPost() : Post = Post(postResponse.id,postResponse.title)

}