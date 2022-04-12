package com.sachinreddy.yeti.api

import com.sachinreddy.yeti.util.YetiConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(YetiConstants.BASE_URL)
            .build()
    }

    val api: JsonPlaceHolderAPI by lazy {
        retrofit.create(JsonPlaceHolderAPI::class.java)
    }
}