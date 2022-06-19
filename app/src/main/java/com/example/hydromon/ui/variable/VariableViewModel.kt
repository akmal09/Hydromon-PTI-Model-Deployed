package com.example.hydromon.ui.variable

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class VariableViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is variable Fragment"
    }
    val text: LiveData<String> = _text
}