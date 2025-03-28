package com.example.demogallery.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _grid = MutableLiveData<Int>().apply { value = 1 }
    val grid: LiveData<Int> get() = _grid

    fun updateData(newGrid: Int) {
        _grid.value = newGrid
    }
}