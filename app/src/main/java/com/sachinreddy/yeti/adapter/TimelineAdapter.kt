package com.sachinreddy.yeti.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sachinreddy.yeti.R
import com.sachinreddy.yeti.data.TimelineItem

class TimelineAdapter(private val data: List<TimelineItem>): RecyclerView.Adapter<TimelineAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.userName.text = item.userName
        holder.numLikes.text = item.numLikes.toString()
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val userName: TextView = itemView.findViewById(R.id.userName)
        val newsImage: ImageView = itemView.findViewById(R.id.newsImage)
        val numLikes: TextView = itemView.findViewById(R.id.numLikes)
    }
}