package com.sachinreddy.yeti.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sachinreddy.yeti.repository.YetiRepository

class MainViewModelFactory(
    private val repository: YetiRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}