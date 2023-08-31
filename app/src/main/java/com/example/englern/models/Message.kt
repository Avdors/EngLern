package com.example.englern.models

data class Message(
    val text: String,
    val isUserMessage: Boolean,
    val time: String,
    val theme: String
)