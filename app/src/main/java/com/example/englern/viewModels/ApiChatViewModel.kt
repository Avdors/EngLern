package com.example.englern.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.englern.models.ChatResponse
import com.example.englern.models.MessageApi
import com.example.englern.repositories.ApiChatRepository

class ApiChatViewModel(private val apiRepository: ApiChatRepository): ViewModel() {

    fun sendMessagesAndGetResponse(messages: List<MessageApi>): LiveData<ChatResponse> {
        return apiRepository.sendMessagesToServer(messages)
    }
}