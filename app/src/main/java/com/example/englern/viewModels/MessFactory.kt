package com.example.englern.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.englern.repositories.MessRepository

class MessFactory constructor(private val messRepository: MessRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(messClass: Class<T>): T{
        return if (messClass.isAssignableFrom(ChatViewModel::class.java)){
            ChatViewModel(this.messRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}