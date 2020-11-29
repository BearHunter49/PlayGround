package com.example.playground.databinding

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataBindingViewModel : ViewModel() {
//    var inputText = ObservableField("")
    private val _inputText = MutableLiveData<String>()
    val inputText: LiveData<String> = _inputText

    fun btnClick(text: String){
        _inputText.value = (text + "1")
    }
}