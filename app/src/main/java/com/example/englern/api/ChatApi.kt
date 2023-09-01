package com.example.englern.api

import com.example.englern.models.ChatRequest
import com.example.englern.models.ChatResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ChatApi {
    @POST("getAnswer.php")
    suspend fun getChatCompletion(@Body request: ChatRequest): ChatResponse
}