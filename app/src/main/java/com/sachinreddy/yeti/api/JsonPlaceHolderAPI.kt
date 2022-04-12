package com.sachinreddy.yeti.api

import com.sachinreddy.yeti.data.YetiPost
import retrofit2.http.GET

interface JsonPlaceHolderAPI  {
    @GET("/posts")
    suspend fun getPosts(): List<YetiPost>
}