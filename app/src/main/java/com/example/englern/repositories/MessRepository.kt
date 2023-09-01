package com.example.englern.repositories

import androidx.lifecycle.LiveData
import com.example.englern.data.MessDao
import com.example.englern.models.MessageModel

class MessRepository(private val messDao: MessDao) {
    val message = messDao.getAllMesage()

    fun getFilter(them:String, dateThem:String): LiveData<List<MessageModel>>{
        return messDao.getFilter(them,dateThem)
    }

    suspend fun insertMess(messageModel: MessageModel){
        messDao.insertMess(messageModel)
    }
    suspend fun updateMess(messageModel: MessageModel){
        messDao.updateMess(messageModel)
    }

    suspend fun deleteMess(messageModel: MessageModel){
        messDao.deleteMess(messageModel)
    }

    suspend fun deleteAllMess(){
        messDao.deleteAllMessage()
    }
}