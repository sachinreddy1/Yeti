package com.sachinreddy.yeti.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.sachinreddy.yeti.adapter.TimelineAdapter
import com.sachinreddy.yeti.data.TimelineItem

@BindingAdapter("app:timelineAdapter")
fun setTimelineAdapter(recyclerView: RecyclerView, adapter: TimelineAdapter) {
    recyclerView.adapter = adapter
}

@BindingAdapter("app:timelineItems")
fun setTimelineItems(recyclerView: RecyclerView, items: List<TimelineItem>) {
    (recyclerView.adapter as TimelineAdapter).let {
        it.setItems(items)
    }
}

@BindingAdapter("app:refreshFunc")
fun setSwipeRefreshListener(swipeRefreshLayout: SwipeRefreshLayout, func: () -> Unit) {
    swipeRefreshLayout.setOnRefreshListener {
        func.invoke()
        swipeRefreshLayout.isRefreshing = false
    }
}


