package com.example.englern.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "message_data_table")
data class MessageModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "mess_id")
    var id: Int,
    @ColumnInfo(name = "mess_text")
    var text: String,
    @ColumnInfo(name = "user_mess")
    var isUserMessage: Int,
    @ColumnInfo(name = "mess_time")
    var time: String,
    @ColumnInfo(name = "mess_theme")
    var theme: String,
    @ColumnInfo(name = "theme_data")
    var dataTheme: String
)