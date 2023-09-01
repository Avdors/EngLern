package com.example.englern.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.englern.models.MessageModel

class WindowViewModel(): ViewModel() {
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

    fun removeLastMessage() {
        _messages.value?.let {
            if (it.isNotEmpty()) {
                it.removeAt(it.size - 1)
                _messages.value = it
            }
        }
    }

}