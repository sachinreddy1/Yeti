package com.sachinreddy.yeti.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sachinreddy.yeti.R
import com.sachinreddy.yeti.adapter.TimelineAdapter
import com.sachinreddy.yeti.data.TimelineItem
import com.sachinreddy.yeti.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        val data = listOf(
            TimelineItem(
                "sachinreddy96",
                "",
                12
            ),
            TimelineItem(
                "friend :)",
                "",
                54
            ),
            TimelineItem(
                "Harry Potter",
                "",
                3
            )
        )

        val newData = listOf(
            TimelineItem(
                "1",
                "",
                1
            ),
            TimelineItem(
                "2 :)",
                "",
                2
            ),
            TimelineItem(
                "3",
                "",
                3
            )
        )

        val rvAdapter = TimelineAdapter()
        rvAdapter.setItems(data)

        binding.rvNewsfeed.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = rvAdapter
        }

        binding.swipeRefresh.setOnRefreshListener {
            rvAdapter.apply {
                setItems(newData)
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