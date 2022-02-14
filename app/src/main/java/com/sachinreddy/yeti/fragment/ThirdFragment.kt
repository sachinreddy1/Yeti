package com.sachinreddy.yeti.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sachinreddy.yeti.api.JsonPlaceHolderAPI
import com.sachinreddy.yeti.data.Post
import com.sachinreddy.yeti.databinding.FragmentThirdBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ThirdFragment : Fragment() {

    private var _binding: FragmentThirdBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThirdBinding.inflate(inflater, container, false)

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

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}