package com.sachinreddy.yeti.data

import com.google.gson.annotations.SerializedName

data class YetiPost (
    val userId: Int,
    val id: Int,
    val title: String,
    @SerializedName("body")
    val body: String
)