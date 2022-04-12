package com.sachinreddy.yeti.repository

import com.sachinreddy.yeti.api.RetrofitInstance
import com.sachinreddy.yeti.data.YetiPost

class YetiRepository {
    suspend fun getPosts(): List<YetiPost> {
        return RetrofitInstance.api.getPosts()
    }
}