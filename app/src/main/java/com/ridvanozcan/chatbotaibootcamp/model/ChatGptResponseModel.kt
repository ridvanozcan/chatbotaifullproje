package com.ridvanozcan.chatbotaibootcamp.model

import com.google.gson.annotations.SerializedName

data class ChatGptResponseModel (
    val id: String,
    @SerializedName("object")
    val objectType: String,
    val created: Long,
    val model: String,
    val choices: List<Choices>,
    val usage: Usage
)

data class Choices(
    val index: Int,
    val message: Message,
    @SerializedName("finish_reason")
    val finishReason: String
)

data class Usage(
    @SerializedName("prompt_token")
    val promptToken: Int,
    @SerializedName("cpmpletion_token")
    val completionToken: Int,
    @SerializedName("total_tokens")
    val totalTokens: Int
)

data class ChatGptImageResponseModel(
    val created: Long,
    val data: List<UrlData>
)

data class UrlData(
    val url: String
)