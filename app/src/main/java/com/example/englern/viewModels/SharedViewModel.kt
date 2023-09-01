package com.example.englern.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.englern.models.MessageApi
import java.text.SimpleDateFormat
import java.util.*

class SharedViewModel : ViewModel()  {
    private val _selectedTheme = MutableLiveData<String>()
    private val _currentDateTime = MutableLiveData<String>()
    private val _currentMessages = MutableLiveData<MutableList<MessageApi>>(mutableListOf())
    val currentDateTime: LiveData<String> get()= _currentDateTime
    val selectedTheme: LiveData<String> get() = _selectedTheme
    val currentMessages: LiveData<MutableList<MessageApi>> get() = _currentMessages

    fun updateSelectedTheme(theme: String, date: String) {
        _selectedTheme.value = theme
        _currentDateTime.value = date
    }
    fun addMessageToCurrentMessages(message: MessageApi) {
        val updatedList = _currentMessages.value?.toMutableList()
        updatedList?.add(message)
        _currentMessages.value = updatedList!!
    }

    fun clearCurrentMessages() {
        _currentMessages.value?.clear()
    }

}