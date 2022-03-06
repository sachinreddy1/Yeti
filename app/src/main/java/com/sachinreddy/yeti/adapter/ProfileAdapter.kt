package com.sachinreddy.yeti.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sachinreddy.yeti.R
import com.sachinreddy.yeti.data.TimelineItem

class ProfileAdapter: RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {
    private var items: List<TimelineItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.profile_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
//        holder.profileImage.setImageURI(item.image)
        holder.profileNumLikes.text = item.numLikes.toString()
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val profileImage: ImageView = itemView.findViewById(R.id.profileImage)
        val profileNumLikes: TextView = itemView.findViewById(R.id.profileNumLikes)
    }

    fun setItems(data: List<TimelineItem>) {
        this.items = data
        notifyDataSetChanged()
    }
}