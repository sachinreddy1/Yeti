package com.sachinreddy.yeti.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sachinreddy.yeti.adapter.ProfileAdapter
import com.sachinreddy.yeti.adapter.TimelineAdapter
import com.sachinreddy.yeti.api.JsonPlaceHolderAPI
import com.sachinreddy.yeti.data.Post
import com.sachinreddy.yeti.data.TimelineItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel : ViewModel() {
    val data: MutableLiveData<List<TimelineItem>> = MutableLiveData(
        listOf(
            TimelineItem("sachinreddy96", "", 12),
            TimelineItem("friend :)", "", 54),
            TimelineItem("Harry Potter", "", 3),
            TimelineItem("sachinreddy96", "", 12),
            TimelineItem("friend :)", "", 54),
            TimelineItem("Harry Potter", "", 3),
            TimelineItem("sachinreddy96", "", 12),
            TimelineItem("friend :)", "", 54),
            TimelineItem("Harry Potter", "", 3),
            TimelineItem("sachinreddy96", "", 12),
            TimelineItem("friend :)", "", 54),
            TimelineItem("Harry Potter", "", 3),
        )
    )

    val timelineAdapter: TimelineAdapter = TimelineAdapter()
    val profileAdapter: ProfileAdapter = ProfileAdapter()

    fun setOnRefreshListener() {
        data.postValue(
            listOf(
                TimelineItem("1", "", 1),
                TimelineItem("2 :)", "", 2),
                TimelineItem("3", "", 3)
            )
        )

        testAPICall()
    }

    fun testAPICall() {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .build()
            .create(JsonPlaceHolderAPI::class.java)

        val retrofitData = retrofit.getPosts()
        retrofitData.enqueue(object : Callback<List<Post>?> {
            override fun onResponse(call: Call<List<Post>?>, response: Response<List<Post>?>) {
                val responseBody = response.body()!!

                val res = mutableListOf<Post>()
                for (i in responseBody) {
                    res.add(i)
                }

                println(res)
            }

            override fun onFailure(call: Call<List<Post>?>, t: Throwable) {
                println(t.message)
            }
        })
    }
}