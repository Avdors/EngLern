package com.example.englern.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.englern.models.MessageModel

@Dao
interface MessDao {

    @Insert
    suspend fun insertMess(messageModel: MessageModel)
    @Update
    suspend fun updateMess(messageModel: MessageModel)
    @Query("SELECT * FROM message_data_table")
    fun getAllMesage(): LiveData<List<MessageModel>>
    @Query("SELECT * FROM message_data_table WHERE mess_theme = :them AND theme_data = :dateThem ORDER BY mess_time ASC")
    fun getFilter(them:String, dateThem:String): LiveData<List<MessageModel>>
    @Update
    suspend fun deleteMess(messageModel: MessageModel)
    @Query("DELETE FROM message_data_table")
    suspend fun deleteAllMessage()

}