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

    private var _binding: FragmentFirstBinding? = null
    private lateinit var viewModel: MainViewModel

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        // Get the viewModel
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        // Make RV Adapter and set items
        val rvAdapter = TimelineAdapter()
        rvAdapter.setItems(viewModel.data)

        binding.rvNewsfeed.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = rvAdapter
        }

        // On refresh logic
        binding.swipeRefresh.setOnRefreshListener {
            rvAdapter.apply {
                setItems(viewModel.newData)
                notifyDataSetChanged()
            }

            binding.swipeRefresh.isRefreshing = false
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}