package com.ridvanozcan.chatbotaibootcamp.model

import com.google.gson.annotations.SerializedName

data class ChatGptRequestModel (
    val model: String,
    val messages: List<Message>,
    @SerializedName("max_tokens")
    val maxTokens: Int
)

data class Message(
    val role: String,
    val content: String
)


data class ChatGptImageRequestModel (
    val model: String,
    val prompt: String,
    val n: Int,
    val size: String
)