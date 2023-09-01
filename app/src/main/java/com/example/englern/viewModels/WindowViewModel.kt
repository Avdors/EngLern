package com.example.englern.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.englern.models.MessageModel

class WimdowViewModel(): ViewModel() {
    private val _messages = MutableLiveData<MutableList<MessageModel>>(mutableListOf())
    val messages: LiveData<MutableList<MessageModel>> = _messages


    fun addMessage(message: MessageModel) {
        val updatedMessages = _messages.value.orEmpty().toMutableList()
        updatedMessages.add(message)
        _messages.value = updatedMessages
    }

    fun clearMessages() {
        _messages.value = mutableListOf()
    }

}