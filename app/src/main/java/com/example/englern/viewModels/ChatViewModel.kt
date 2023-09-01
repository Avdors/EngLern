package com.example.englern.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.englern.models.MessageModel
import com.example.englern.repositories.MessRepository
import com.example.englern.toInt
import kotlinx.coroutines.launch


class ChatViewModel(private val messRepository: MessRepository) : ViewModel() {

    // это стартовый код, до добавления room
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
//стартовый

    val message = messRepository.message

    fun getFilter(them:String, dateThem:String): LiveData<List<MessageModel>>{
       val result = messRepository.getFilter(them,dateThem)
        Log.d("DatabaseData", "Fetched data: ${result.value?.joinToString()}")
        return messRepository.getFilter(them,dateThem)
    }

    fun startInsert(textMess: String,isUserMessage:Boolean, time: String, theme:String,
                    dataTheme:String) {
        insertMess(MessageModel(0, textMess, isUserMessage.toInt(), time, theme, dataTheme))
    }

    private fun insertMess(messageModel: MessageModel) = viewModelScope.launch {
        messRepository.insertMess(messageModel)
    }

    fun startUpdateMess(textMess: String,isUserMessage:Boolean, time: String, theme:String,
                        dataTheme:String){
        updateMess(MessageModel(0, textMess, isUserMessage.toInt(), time, theme, dataTheme))
    }

    private fun updateMess(messageModel: MessageModel) = viewModelScope.launch  {
        messRepository.updateMess(messageModel)
    }

    fun deleteMess(messageModel: MessageModel) = viewModelScope.launch{
        messRepository.deleteMess(messageModel)
    }

    fun deleteAllMess() = viewModelScope.launch{
        messRepository.deleteAllMess()
    }
}


