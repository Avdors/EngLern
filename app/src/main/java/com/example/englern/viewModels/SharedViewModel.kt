package com.example.englern.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class SharedViewModel : ViewModel()  {
    private val _selectedTheme = MutableLiveData<String>()
    private val _currentDateTime = MutableLiveData<String>()
    val currentDateTime: LiveData<String> get()= _currentDateTime
    val selectedTheme: LiveData<String> get() = _selectedTheme

    fun updateSelectedTheme(theme: String, date: String) {
        _selectedTheme.value = theme
        _currentDateTime.value = date
    }


}