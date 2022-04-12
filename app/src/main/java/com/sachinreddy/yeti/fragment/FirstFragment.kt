package com.sachinreddy.yeti.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sachinreddy.yeti.databinding.FragmentFirstBinding
import com.sachinreddy.yeti.repository.YetiRepository
import com.sachinreddy.yeti.viewmodel.MainViewModel
import com.sachinreddy.yeti.viewmodel.MainViewModelFactory

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    private val yetiRepository = YetiRepository()
    private val mainViewModel: MainViewModel by viewModels { MainViewModelFactory(yetiRepository) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        FragmentFirstBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@FirstFragment
            vm = mainViewModel
            return root
        }
    }
}