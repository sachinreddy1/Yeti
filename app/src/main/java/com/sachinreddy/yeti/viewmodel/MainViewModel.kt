package com.sachinreddy.yeti.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sachinreddy.yeti.adapter.TimelineAdapter
import com.sachinreddy.yeti.data.TimelineItem

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

    fun setOnRefreshListener() {
        data.postValue(
            listOf(
                TimelineItem("1", "", 1),
                TimelineItem("2 :)", "", 2),
                TimelineItem("3", "", 3)
            )
        )
    }
}