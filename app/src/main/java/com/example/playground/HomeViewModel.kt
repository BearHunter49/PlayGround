package com.example.playground

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.playground.data.Toy
import com.example.playground.data.ConstData
import kotlinx.coroutines.launch

class HomeViewModel(dataSource: ConstData) : ViewModel(){
    private val database = dataSource
    lateinit var toys: List<Toy>

    init {
        initializeToyList()
    }

    private fun initializeToyList() {
        viewModelScope.launch {
            toys = database.toyListDummyData
        }
    }
}