package com.sachinreddy.yeti.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sachinreddy.yeti.adapter.TimelineAdapter
import com.sachinreddy.yeti.databinding.FragmentFirstBinding
import com.sachinreddy.yeti.viewmodel.MainViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFirstBinding.inflate(inflater, container, false)
        val mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        // Make RV Adapter and set items
        val rvAdapter = TimelineAdapter()
        rvAdapter.setItems(mainViewModel.data)

        binding.rvNewsfeed.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = rvAdapter
        }

        // On refresh logic
        binding.swipeRefresh.setOnRefreshListener {
            rvAdapter.apply {
                setItems(mainViewModel.newData)
                notifyDataSetChanged()
            }

            binding.swipeRefresh.isRefreshing = false
        }

        return binding.root
    }
}