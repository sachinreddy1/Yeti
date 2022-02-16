package com.sachinreddy.yeti.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.sachinreddy.yeti.adapter.ProfileAdapter
import com.sachinreddy.yeti.databinding.FragmentThirdBinding
import com.sachinreddy.yeti.viewmodel.MainViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ThirdFragment : Fragment() {

    private var _binding: FragmentThirdBinding? = null
    private lateinit var viewModel: MainViewModel

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThirdBinding.inflate(inflater, container, false)

//        val retrofit = Retrofit.Builder()
//            .addConverterFactory(GsonConverterFactory.create())
//            .baseUrl("https://jsonplaceholder.typicode.com/")
//            .build()
//            .create(JsonPlaceHolderAPI::class.java)
//
//        val retrofitData = retrofit.getPosts()
//        retrofitData.enqueue(object : Callback<List<Post>?> {
//            override fun onResponse(call: Call<List<Post>?>, response: Response<List<Post>?>) {
//                val responseBody = response.body()!!
//
//                val res = mutableListOf<Post>()
//                for (i in responseBody) {
//                    res.add(i)
//                }
//
//                println(res)
//            }
//
//            override fun onFailure(call: Call<List<Post>?>, t: Throwable) {
//                println(t.message)
//            }
//        })

        // Get the viewModel
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        // Make RV Adapter and set items
        val rvAdapter = ProfileAdapter()
        rvAdapter.setItems(viewModel.data)

        binding.rvProfileFeed.apply {
            layoutManager = GridLayoutManager(context, 3)
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