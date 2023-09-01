package com.example.englern.repositories


import com.example.englern.api.ChatApi
import com.example.englern.models.ChatRequest
import com.example.englern.models.ChatResponse
import com.example.englern.models.MessageApi
import com.example.englern.api.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ApiChatRepository(private val chatApi: ChatApi) {
    suspend fun getChatCompletion(request: ChatRequest): ChatResponse {
        return chatApi.getChatCompletion(request)
    }

    fun sendMessagesToServer(     messages: List<MessageApi>,
                                  onResult: (Resource<ChatResponse>) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val request = ChatRequest(messages)
                val response = chatApi.getChatCompletion(request)
                withContext(Dispatchers.Main) {
                    onResult(Resource.Success(response))
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onResult(Resource.Error(e.localizedMessage ?: "An error occurred", null))
                }
            }
        }
    }
}